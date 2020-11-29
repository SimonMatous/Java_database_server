package cz.cvut.fit.matousi1.service;

import cz.cvut.fit.matousi1.dto.gameCreateDTO;
import cz.cvut.fit.matousi1.dto.gameDTO;
import cz.cvut.fit.matousi1.entities.location;
import cz.cvut.fit.matousi1.entities.game;
import cz.cvut.fit.matousi1.entities.software;
import cz.cvut.fit.matousi1.entities.studio;
import cz.cvut.fit.matousi1.repository.locationRepository;
import cz.cvut.fit.matousi1.repository.gameRepository;
import cz.cvut.fit.matousi1.repository.softwareRepository;
import cz.cvut.fit.matousi1.repository.studioRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class gameServiceTest {
    @Autowired
    private gameService GameService;
    @MockBean
    private gameRepository GameRepository;
    @MockBean
    private softwareRepository SoftwareRepository;
    @MockBean
    private studioRepository StudioRepository;


    @Test
    void create() throws Exception {
        software TestSoftware = new software("TestSoftwareName",new Timestamp(1980-01-01));
        studio TestStudio = new studio("TestName",new Timestamp(1980-01-01),null);
        game TestGame = new game("TestName","TestHardware",new Timestamp(1980-01-01),TestStudio, Collections.singletonList(TestSoftware));

        gameDTO TestDTO = new gameDTO(TestGame.getId(),TestGame.getName(),TestGame.getHardware(),TestGame.getRelease_date(),Collections.singletonList(TestSoftware.getId()),TestGame.getStudio().getId());
        gameCreateDTO TestCreateDTO = new gameCreateDTO(TestGame.getName(),TestGame.getHardware(),TestGame.getRelease_date(),Collections.singletonList(TestSoftware.getId()),TestGame.getStudio().getId());

        BDDMockito.given(StudioRepository.findById(TestStudio.getId())).willReturn(Optional.of(TestStudio));
        BDDMockito.given(SoftwareRepository.findAllById(Collections.singletonList(TestSoftware.getId()))).willReturn(List.of(TestSoftware));
        BDDMockito.given(GameRepository.save(Mockito.any(game.class))).willReturn(TestGame);

        Assertions.assertEquals(TestDTO,GameService.create(TestCreateDTO));
        Mockito.verify(GameRepository,Mockito.atLeastOnce()).save(Mockito.any(game.class));

        BDDMockito.given(StudioRepository.findById(TestStudio.getId())).willReturn(Optional.empty());
        BDDMockito.given(SoftwareRepository.findAllById(Collections.singletonList(TestSoftware.getId()))).willReturn(List.of(TestSoftware));
        Assertions.assertThrows(Exception.class,()->GameService.create(TestCreateDTO));

        List<software> TestListOfSoftware = null;
        BDDMockito.given(StudioRepository.findById(TestStudio.getId())).willReturn(Optional.of(TestStudio));
        BDDMockito.given(SoftwareRepository.findAllById(Collections.singletonList(TestSoftware.getId()))).willReturn(TestListOfSoftware);
        Assertions.assertThrows(Exception.class,()->GameService.create(TestCreateDTO));

    }


    @Test
    void update() throws Exception {
        software TestSoftware = new software("TestSoftwareName",new Timestamp(1980-01-01));
        studio TestStudio = new studio("TestName",new Timestamp(1980-01-01),null);
        game TestGame = new game("TestName","TestHardware",new Timestamp(1980-01-01),TestStudio, Collections.singletonList(TestSoftware));
        gameCreateDTO TestCreateDTO = new gameCreateDTO("OtherTestName","   OtherTestHardware",new Timestamp(2000-01-01),Collections.singletonList(TestSoftware.getId()),TestGame.getStudio().getId());

        BDDMockito.given(StudioRepository.findById(TestStudio.getId())).willReturn(Optional.of(TestStudio));
        BDDMockito.given(SoftwareRepository.findAllById(Collections.singletonList(TestSoftware.getId()))).willReturn(List.of(TestSoftware));
        BDDMockito.given(GameRepository.findById(TestGame.getId())).willReturn(Optional.of(TestGame));
        Assertions.assertNotEquals(TestGame.getName(),TestCreateDTO.getName());

        GameService.update(TestGame.getId(),TestCreateDTO);
        Assertions.assertEquals(TestGame.getName(),TestCreateDTO.getName());
        Assertions.assertEquals(TestGame.getHardware(),TestCreateDTO.getHardware());
        Assertions.assertEquals(TestGame.getRelease_date(),TestCreateDTO.getRelease_date());

        Mockito.verify(GameRepository,Mockito.atLeastOnce()).findById(TestGame.getId());

        BDDMockito.given(GameRepository.findById(TestGame.getId())).willReturn(Optional.empty());
        Assertions.assertThrows(Exception.class,()->GameService.update(TestGame.getId(),TestCreateDTO));

        List<software> TestListOfSoftware = null;
        BDDMockito.given(GameRepository.findById(TestGame.getId())).willReturn(Optional.of(TestGame));
        BDDMockito.given(SoftwareRepository.findAllById(Collections.singletonList(TestSoftware.getId()))).willReturn(TestListOfSoftware);
        Assertions.assertThrows(Exception.class,()->GameService.update(TestGame.getId(),TestCreateDTO));

        BDDMockito.given(StudioRepository.findById(TestStudio.getId())).willReturn(Optional.empty());
        BDDMockito.given(GameRepository.findById(TestGame.getId())).willReturn(Optional.of(TestGame));
        BDDMockito.given(SoftwareRepository.findAllById(Collections.singletonList(TestSoftware.getId()))).willReturn(List.of(TestSoftware));
        Assertions.assertThrows(Exception.class,()->GameService.update(TestGame.getId(),TestCreateDTO));
    }

    @Test
    void delete() throws Exception {
        software TestSoftware = new software("TestSoftwareName",new Timestamp(1980-01-01));
        studio TestStudio = new studio("TestName",new Timestamp(1980-01-01),null);
        game TestGame = new game("TestName","TestHardware",new Timestamp(1980-01-01),TestStudio, Collections.singletonList(TestSoftware));

        BDDMockito.given(GameRepository.findById(TestGame.getId())).willReturn(Optional.of(TestGame));
        GameService.delete(TestGame.getId());
        Mockito.verify(GameRepository,Mockito.atLeastOnce()).delete(TestGame);

        BDDMockito.given(GameRepository.findById(TestGame.getId())).willReturn(Optional.empty());
        Assertions.assertThrows(Exception.class,()->GameService.delete(TestGame.getId()));
    }

}