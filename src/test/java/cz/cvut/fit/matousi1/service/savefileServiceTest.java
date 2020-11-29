package cz.cvut.fit.matousi1.service;

import cz.cvut.fit.matousi1.dto.savefileCreateDTO;
import cz.cvut.fit.matousi1.dto.savefileDTO;
import cz.cvut.fit.matousi1.entities.game;
import cz.cvut.fit.matousi1.entities.location;
import cz.cvut.fit.matousi1.entities.savefile;
import cz.cvut.fit.matousi1.repository.gameRepository;
import cz.cvut.fit.matousi1.repository.savefileRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.xml.stream.Location;
import java.sql.Timestamp;
import java.util.Optional;

@SpringBootTest
class savefileServiceTest {
    @Autowired
    private savefileService SavefileService;
    @MockBean
    private savefileRepository SavefileRepository;
    @MockBean
    private gameRepository GameRepository;

    @Test
    void create() throws Exception {
        game TestGame = new game("TestName","TestHardware",new Timestamp(1980-01-01),null,null);
        savefile TestSavefile = new savefile("TestName",new Timestamp(1980-01-01),50,TestGame);

        savefileDTO TestDTO = new savefileDTO(TestSavefile.getId(),TestSavefile.getName(),TestSavefile.getSaved_at(),TestSavefile.getPercOfGameFinished(),TestSavefile.getGame().getId());
        savefileCreateDTO TestCreateDTO = new savefileCreateDTO(TestSavefile.getName(),TestSavefile.getSaved_at(),TestSavefile.getPercOfGameFinished(),TestSavefile.getGame().getId());

        BDDMockito.given(GameRepository.findById(TestGame.getId())).willReturn(Optional.of(TestGame));
        BDDMockito.given(SavefileRepository.save(Mockito.any(savefile.class))).willReturn(TestSavefile);

        Assertions.assertEquals(TestDTO,SavefileService.create(TestCreateDTO));
        Mockito.verify(SavefileRepository,Mockito.atLeastOnce()).save(Mockito.any(savefile.class));

        BDDMockito.given(GameRepository.findById(TestGame.getId())).willReturn(Optional.empty());
        Assertions.assertThrows(Exception.class,()->SavefileService.create(TestCreateDTO));
    }

    @Test
    void update() throws Exception {
        game TestGame = new game("TestName","TestHardware",new Timestamp(1980-01-01),null,null);
        savefile TestSavefile = new savefile("TestName",new Timestamp(1980-01-01),50,TestGame);
        savefileCreateDTO SavefileCreateDTO = new savefileCreateDTO("OtherTestName",new Timestamp(1980-01-01),1,TestGame.getId());

        BDDMockito.given(GameRepository.findById(TestGame.getId())).willReturn(Optional.of(TestGame));
        BDDMockito.given(SavefileRepository.findById(TestSavefile.getId())).willReturn(Optional.of(TestSavefile));
        Assertions.assertNotEquals(TestSavefile.getName(),SavefileCreateDTO.getName());

        SavefileService.update(TestSavefile.getId(),SavefileCreateDTO);
        Assertions.assertEquals(TestSavefile.getName(),SavefileCreateDTO.getName());
        Assertions.assertEquals(TestSavefile.getPercOfGameFinished(),SavefileCreateDTO.getPercOfGameFinished());

        Mockito.verify(SavefileRepository,Mockito.atLeastOnce()).findById(TestSavefile.getId());

        BDDMockito.given(SavefileRepository.findById(TestSavefile.getId())).willReturn(Optional.empty());
        Assertions.assertThrows(Exception.class,()->SavefileService.update(TestSavefile.getId(),SavefileCreateDTO));

        BDDMockito.given(GameRepository.findById(TestGame.getId())).willReturn(Optional.empty());
        BDDMockito.given(SavefileRepository.findById(TestSavefile.getId())).willReturn(Optional.of(TestSavefile));
        Assertions.assertThrows(Exception.class,()->SavefileService.update(TestSavefile.getId(),SavefileCreateDTO));
    }

    @Test
    void delete() throws Exception {
        game TestGame = new game("TestName","TestHardware",new Timestamp(1980-01-01),null,null);
        savefile TestSavefile = new savefile("TestName",new Timestamp(1980-01-01),50,TestGame);

        BDDMockito.given(SavefileRepository.findById(TestSavefile.getId())).willReturn(Optional.of(TestSavefile));
        SavefileService.delete(TestSavefile.getId());
        Mockito.verify(SavefileRepository,Mockito.atLeastOnce()).delete(TestSavefile);

        BDDMockito.given(SavefileRepository.findById(TestGame.getId())).willReturn(Optional.empty());
        Assertions.assertThrows(Exception.class,()->SavefileService.delete(TestSavefile.getId()));
    }
}