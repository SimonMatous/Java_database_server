package cz.cvut.fit.matousi1.service;


import cz.cvut.fit.matousi1.dto.savefileCreateDTO;
import cz.cvut.fit.matousi1.dto.savefileDTO;
import cz.cvut.fit.matousi1.entities.hra;
import cz.cvut.fit.matousi1.entities.savefile;
import cz.cvut.fit.matousi1.repository.hraRepository;
import cz.cvut.fit.matousi1.repository.savefileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class savefileService {

    private final savefileRepository SavefileRepository;
    private final hraRepository HraRepository;


    @Autowired
    public savefileService(savefileRepository savefileRepository, hraRepository hraRepository) {
        SavefileRepository = savefileRepository;
        HraRepository = hraRepository;
    }

    public savefileDTO create(savefileCreateDTO SavefileCreateDTO) throws Exception {
        Optional<hra> Hra = HraRepository.findById(SavefileCreateDTO.getHra_id());
        if ( Hra.isEmpty())
            throw new Exception("savefile not found");

        return toDTO(
            SavefileRepository.save(
                    new savefile(SavefileCreateDTO.getName(), SavefileCreateDTO.getSaved_at(),SavefileCreateDTO.getPercOfGameFinished(),Hra.get())
            )
        );
    }

    @Transactional
    public savefileDTO update(int id,savefileCreateDTO SavefileCreateDTO) throws Exception {
        Optional<savefile> OptionalSavefile = findById(id);
        if ( OptionalSavefile.isEmpty())
            throw new Exception("savefile not found");
        savefile Savefile = OptionalSavefile.get();

        Optional<hra> Hra = HraRepository.findById(SavefileCreateDTO.getHra_id());
        if ( Hra.isEmpty())
            throw new Exception("savefile not found");
        Savefile.setName(SavefileCreateDTO.getName());
        Savefile.setSaved_at(SavefileCreateDTO.getSaved_at());
        Savefile.setPercOfGameFinished(SavefileCreateDTO.getPercOfGameFinished());
        Savefile.setHra(Hra.get());
        return toDTO(Savefile);
    }

    public List<savefileDTO> findAll(){
        return SavefileRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public List<savefile> findByIds(List<Integer> ids){
        return SavefileRepository.findAllById(ids);
    }

    public Optional<savefile> findById (int id){
        return SavefileRepository.findById(id);
    }
    public Optional<savefileDTO> findByIdAsDTO (int id){
        return toDTO(SavefileRepository.findById(id));
    }

    private savefileDTO toDTO(savefile Savefile){
        return new savefileDTO(
                Savefile.getId(),
                Savefile.getName(),
                Savefile.getSaved_at(),
                Savefile.getPercOfGameFinished(),
                Savefile.getHra().getId()
        );
    }


    private Optional<savefileDTO> toDTO(Optional<savefile> OptionalSavefile){
    if ( OptionalSavefile.isEmpty())
        return  Optional.empty();
    return Optional.of(toDTO(OptionalSavefile.get()));
    }
}
