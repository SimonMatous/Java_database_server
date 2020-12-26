package cz.cvut.fit.matousi1.service;


import cz.cvut.fit.matousi1.dto.locationCreateDTO;
import cz.cvut.fit.matousi1.dto.locationDTO;
import cz.cvut.fit.matousi1.entities.location;
import cz.cvut.fit.matousi1.repository.locationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class locationService {

    private final locationRepository LocationRepository;

    @Autowired
    public locationService(locationRepository locationRepository) {
        LocationRepository = locationRepository;
    }

    public List<locationDTO> findAll(){
        return LocationRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public locationDTO create(locationCreateDTO LocationCreateDTO){
        return toDTO(
            LocationRepository.save(
                    new location(LocationCreateDTO.getState(), LocationCreateDTO.getTown(),LocationCreateDTO.getAddress())
            )
        );

    }
    @Transactional
    public locationDTO update(int id, locationCreateDTO LocationCreateDTO) throws Exception {
        Optional<location> optionalLocation = LocationRepository.findById(id);
        if ( optionalLocation.isEmpty())
            throw  new Exception("no such location");
        location Location = optionalLocation.get();
        Location.setState(LocationCreateDTO.getState());
        Location.setTown(LocationCreateDTO.getTown());
        Location.setAddress(LocationCreateDTO.getAddress());
        return toDTO(Location);
    }

    @Transactional
    public void delete (int id) throws Exception{
        Optional<location> OptionalLocation = findById(id);
        if ( OptionalLocation.isEmpty())
            throw new Exception("location not found");
        location Location = OptionalLocation.get();

        LocationRepository.delete(Location);
    }

    public List<location> findByIds(List<Integer> ids){
        return LocationRepository.findAllById(ids);
    }

    public Optional<location> findById (int id){
        return LocationRepository.findById(id);
    }
    public Optional<locationDTO> findByIdAsDTO (int id){
        return toDTO(LocationRepository.findById(id));
    }

    private locationDTO toDTO(location Location){
        return new locationDTO(Location.getId(),Location.getState(),Location.getTown(),Location.getAddress());
    }
    private Optional<locationDTO> toDTO(Optional<location> OptionalLocation){
    if ( OptionalLocation.isEmpty())
        return  Optional.empty();
    return Optional.of(toDTO(OptionalLocation.get()));
    }
}
