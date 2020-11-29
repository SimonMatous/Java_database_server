package cz.cvut.fit.matousi1.service;


import cz.cvut.fit.matousi1.dto.gameCreateDTO;
import cz.cvut.fit.matousi1.dto.gameDTO;
import cz.cvut.fit.matousi1.entities.game;
import cz.cvut.fit.matousi1.entities.software;
import cz.cvut.fit.matousi1.entities.studio;
import cz.cvut.fit.matousi1.repository.gameRepository;
import cz.cvut.fit.matousi1.repository.softwareRepository;
import cz.cvut.fit.matousi1.repository.studioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class gameService {

    private final gameRepository GameRepository;
    private final studioRepository StudioRepository;
    private final softwareRepository SoftwareRepository;


    public gameService(gameRepository gameRepository, studioRepository studioRepository, softwareRepository softwareRepository) {
        GameRepository = gameRepository;
        StudioRepository = studioRepository;
        SoftwareRepository = softwareRepository;
    }

    public gameDTO create(gameCreateDTO GameCreateDTO) throws Exception {
        List<software> softwares = SoftwareRepository.findAllById(GameCreateDTO.getSoftware_ids());
        if( softwares.size() != GameCreateDTO.getSoftware_ids().size())
            throw new Exception("some softwares not found");

        Optional<studio> Studio = StudioRepository.findById(GameCreateDTO.getStudio_id());
        if ( Studio.isEmpty())
            throw new Exception("studio not found");

        return toDTO(
            GameRepository.save(
                    new game(GameCreateDTO.getName(), GameCreateDTO.getHardware(),GameCreateDTO.getRelease_date(),Studio.get(),softwares)
            )
        );
    }

    @Transactional
    public gameDTO update(int id,gameCreateDTO GameCreateDTO) throws Exception {
        Optional<game> OptionalGame = findById(id);
        if ( OptionalGame.isEmpty())
            throw new Exception("game not found");
        game Game = OptionalGame.get();

        List<software> softwares = SoftwareRepository.findAllById(GameCreateDTO.getSoftware_ids());
        if( softwares.size() != GameCreateDTO.getSoftware_ids().size())
            throw new Exception("some softwares not found");

        Optional<studio> Studio = StudioRepository.findById(GameCreateDTO.getStudio_id());
        if ( Studio.isEmpty())
            throw new Exception("studio not found");

        Game.setName(GameCreateDTO.getName());
        Game.setRelease_date(GameCreateDTO.getRelease_date());
        Game.setHardware(GameCreateDTO.getHardware());
        Game.setStudio(Studio.get());
        Game.setSoftwares(softwares);
        return toDTO(Game);
    }

    @Transactional
    public void delete(int id) throws Exception{
        Optional<game> OptionalGame = findById(id);
        if ( OptionalGame.isEmpty())
            throw new Exception("game not found");
        game Game = OptionalGame.get();

        GameRepository.delete(Game);
    }

    public List<gameDTO> findAll(){
        return GameRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public List<game> findByIds(List<Integer> ids){
        return GameRepository.findAllById(ids);
    }

    public Optional<game> findById (int id){
        return GameRepository.findById(id);
    }
    public Optional<gameDTO> findByIdAsDTO (int id){
        return toDTO(GameRepository.findById(id));
    }

    private gameDTO toDTO(game Game){
        return new gameDTO(
                Game.getId(),
                Game.getName(),
                Game.getHardware(),
                Game.getRelease_date(),
                Game.getSoftwares().stream().map(software::getId).collect(Collectors.toList()),
                Game.getStudio().getId()
        );
    }


    private Optional<gameDTO> toDTO(Optional<game> OptionalGame){
    if ( OptionalGame.isEmpty())
        return  Optional.empty();
    return Optional.of(toDTO(OptionalGame.get()));
    }
}
