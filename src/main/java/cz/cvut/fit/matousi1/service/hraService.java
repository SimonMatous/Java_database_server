package cz.cvut.fit.matousi1.service;


import cz.cvut.fit.matousi1.dto.hraCreateDTO;
import cz.cvut.fit.matousi1.dto.hraDTO;
import cz.cvut.fit.matousi1.entities.hra;
import cz.cvut.fit.matousi1.entities.software;
import cz.cvut.fit.matousi1.entities.studio;
import cz.cvut.fit.matousi1.repository.hraRepository;
import cz.cvut.fit.matousi1.repository.studioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class hraService {

    private final hraRepository HraRepository;
    private final softwareService SoftwareService;
    private final studioRepository StudioRepository;

    @Autowired
    public hraService(hraRepository hraRepository, softwareService softwareService, studioRepository studioRepository) {
        HraRepository = hraRepository;
        SoftwareService = softwareService;
        StudioRepository = studioRepository;
    }

    public hraDTO create(hraCreateDTO HraCreateDTO) throws Exception {
        List<software> softwares = SoftwareService.findByIds(HraCreateDTO.getSoftware_ids());
        if( softwares.size() != HraCreateDTO.getSoftware_ids().size())
            throw new Exception("some softwares not found");

        Optional<studio> Studio = StudioRepository.findById(HraCreateDTO.getStudio_id());
        if ( Studio.isEmpty())
            throw new Exception("studio not found");

        return toDTO(
            HraRepository.save(
                    new hra(HraCreateDTO.getNazev(), HraCreateDTO.getHardware(),HraCreateDTO.getDatum_vydani(),Studio.get(),softwares)
            )
        );
    }

    @Transactional
    public hraDTO update(int id,hraCreateDTO HraCreateDTO) throws Exception {
        Optional<hra> OptionalHra = findById(id);
        if ( OptionalHra.isEmpty())
            throw new Exception("hra not found");
        hra Hra = OptionalHra.get();

        List<software> softwares = SoftwareService.findByIds(HraCreateDTO.getSoftware_ids());
        if( softwares.size() != HraCreateDTO.getSoftware_ids().size())
            throw new Exception("some softwares not found");

        Optional<studio> Studio = StudioRepository.findById(HraCreateDTO.getStudio_id());
        if ( Studio.isEmpty())
            throw new Exception("studio not found");

        Hra.setNazev(HraCreateDTO.getNazev());
        Hra.setDatum_vydani(HraCreateDTO.getDatum_vydani());
        Hra.setHardware(HraCreateDTO.getHardware());
        Hra.setStudio(Studio.get());
        Hra.setSoftwares(softwares);
        return toDTO(Hra);
    }

    public List<hraDTO> findAll(){
        return HraRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public List<hra> findByIds(List<Integer> ids){
        return HraRepository.findAllById(ids);
    }

    public Optional<hra> findById (int id){
        return HraRepository.findById(id);
    }
    public Optional<hraDTO> findByIdAsDTO (int id){
        return toDTO(HraRepository.findById(id));
    }

    private hraDTO toDTO(hra Hra){
        return new hraDTO(
                Hra.getId(),
                Hra.getNazev(),
                Hra.getHardware(),
                Hra.getDatum_vydani(),
                Hra.getSoftwares().stream().map(software::getId).collect(Collectors.toList()),
                Hra.getStudio().getId()
        );
    }


    private Optional<hraDTO> toDTO(Optional<hra> OptionalHra){
    if ( OptionalHra.isEmpty())
        return  Optional.empty();
    return Optional.of(toDTO(OptionalHra.get()));
    }
}
