package cz.cvut.fit.matousi1.service;


import cz.cvut.fit.matousi1.dto.studioCreateDTO;
import cz.cvut.fit.matousi1.dto.studioDTO;
import cz.cvut.fit.matousi1.entities.location;
import cz.cvut.fit.matousi1.entities.studio;
import cz.cvut.fit.matousi1.repository.locationRepository;
import cz.cvut.fit.matousi1.repository.studioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class studioService {

    private final studioRepository StudioRepository;
    private final locationRepository LocationRepository;


    @Autowired
    public studioService(studioRepository studioRepository, locationRepository locationRepository) {
        StudioRepository = studioRepository;
        LocationRepository = locationRepository;
    }

    public studioDTO create(studioCreateDTO StudioCreateDTO) throws Exception {
        Optional<location> Location = LocationRepository.findById(StudioCreateDTO.getLocation_id());
        if ( Location.isEmpty())
            throw new Exception("Location must exist");

        return toDTO(
            StudioRepository.save(
                    new studio(StudioCreateDTO.getName(), StudioCreateDTO.getFounding_date(),Location.get())
            )
        );
    }

    @Transactional
    public studioDTO update(int id,studioCreateDTO StudioCreateDTO) throws Exception {
        Optional<studio> OptionalStudio = findById(id);
        if ( OptionalStudio.isEmpty())
            throw new Exception("studio not found");
        studio Studio = OptionalStudio.get();

        Optional<location> Location = LocationRepository.findById(StudioCreateDTO.getLocation_id());
        if ( Location.isEmpty())
            throw new Exception("studio not found");

        Studio.setName(StudioCreateDTO.getName());
        Studio.setFounding_date(StudioCreateDTO.getFounding_date());
        Studio.setLocation(Location.get());
        return toDTO(Studio);
    }

    @Transactional
    public void delete (int id) throws Exception{
        Optional<studio> OptionalStudio = findById(id);
        if ( OptionalStudio.isEmpty())
            throw new Exception("studio not found");
        studio Studio = OptionalStudio.get();

        StudioRepository.delete(Studio);
    }

    public List<studioDTO> findAll(){
        return StudioRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public List<studio> findByIds(List<Integer> ids){
        return StudioRepository.findAllById(ids);
    }

    public Optional<studio> findById (int id){
        return StudioRepository.findById(id);
    }
    public Optional<studioDTO> findByIdAsDTO (int id){
        return toDTO(StudioRepository.findById(id));
    }

    private studioDTO toDTO(studio Studio){
        return new studioDTO(
                Studio.getId(),
                Studio.getName(),
                Studio.getFounding_date(),
                Studio.getLocation().getId()
        );
    }


    private Optional<studioDTO> toDTO(Optional<studio> OptionalStudio){
    if ( OptionalStudio.isEmpty())
        return  Optional.empty();
    return Optional.of(toDTO(OptionalStudio.get()));
    }
}
