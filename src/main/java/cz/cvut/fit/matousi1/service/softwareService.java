package cz.cvut.fit.matousi1.service;


import cz.cvut.fit.matousi1.dto.softwareCreateDTO;
import cz.cvut.fit.matousi1.dto.softwareDTO;
import cz.cvut.fit.matousi1.entities.software;
import cz.cvut.fit.matousi1.repository.softwareRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class softwareService {

    private final softwareRepository SoftwareRepository;

    @Autowired
    public softwareService(softwareRepository softwareRepository) {
        SoftwareRepository = softwareRepository;
    }

    public List<softwareDTO> findAll(){
        return SoftwareRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public softwareDTO create(softwareCreateDTO SoftwareCreateDTO){
        return toDTO(
            SoftwareRepository.save(
                    new software(SoftwareCreateDTO.getSoftware_nazev(), SoftwareCreateDTO.getDatum_vyniku())
            )
        );

    }
    @Transactional
    public softwareDTO update(int id, softwareCreateDTO SoftwareCreateDTO) throws Exception {
        Optional<software> optionalSoftware = SoftwareRepository.findById(id);
        if ( optionalSoftware.isEmpty())
            throw  new Exception("no such software");
        software Software = optionalSoftware.get();
        Software.setSoftware_nazev(SoftwareCreateDTO.getSoftware_nazev());
        Software.setDatum_vyniku(SoftwareCreateDTO.getDatum_vyniku());
        return toDTO(Software);
    }

    public List<software> findByIds(List<Integer> ids){
        return SoftwareRepository.findAllById(ids);
    }

    public Optional<software> findById (int id){
        return SoftwareRepository.findById(id);
    }
    public Optional<softwareDTO> findByIdAsDTO (int id){
        return toDTO(SoftwareRepository.findById(id));
    }

    private softwareDTO toDTO(software Software){
        return new softwareDTO(Software.getId(),Software.getSoftware_nazev(),Software.getDatum_vyniku());
    }
    private Optional<softwareDTO> toDTO(Optional<software> OptionalSoftware){
    if ( OptionalSoftware.isEmpty())
        return  Optional.empty();
    return Optional.of(toDTO(OptionalSoftware.get()));
    }
}
