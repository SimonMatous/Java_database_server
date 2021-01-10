package cz.cvut.fit.matousi1.controller;

import cz.cvut.fit.matousi1.dto.gameDTO;
import cz.cvut.fit.matousi1.dto.savefileCreateDTO;
import cz.cvut.fit.matousi1.dto.savefileDTO;
import cz.cvut.fit.matousi1.entities.game;
import cz.cvut.fit.matousi1.entities.savefile;
import cz.cvut.fit.matousi1.service.savefileService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@SpringBootTest
class savefileControllerTest {


    @Autowired
    savefileController SavefileController;

    @MockBean
    private savefileService SavefileService;



    @Test
    void readAll() {

        //gameDTO GameDTO = new gameDTO(0,"TestName","TestHardware",new Timestamp(1970-01-01),null,0);
        List<savefileDTO> ListSaveDTO = new ArrayList<savefileDTO>();
        ListSaveDTO.add(new savefileDTO(0,"TestName",new Timestamp(1980-01-01),50,0));
        ListSaveDTO.add(new savefileDTO(1,"TestName",new Timestamp(1980-01-01),20,0));
        ListSaveDTO.add(new savefileDTO(2,"TestName",new Timestamp(1980-01-01),30,0));
        BDDMockito.given(SavefileService.findAll()).willReturn(ListSaveDTO);
        Assertions.assertEquals(ListSaveDTO,SavefileController.readAll());
        Mockito.verify(SavefileService, Mockito.atLeastOnce()).findAll();
    }

    @Test
    void readById() {
        //gameDTO GameDTO = new gameDTO(0,"TestState","TestCity","TestAddress");
        savefileDTO SavefileDTO = new savefileDTO(0,"TestName",new Timestamp(1980-01-01),50,0);
        BDDMockito.given(SavefileService.findByIdAsDTO(SavefileDTO.getId())).willReturn(Optional.of(SavefileDTO));
        Assertions.assertEquals(SavefileDTO,SavefileController.readById(SavefileDTO.getId()));
        Mockito.verify(SavefileService, Mockito.atLeastOnce()).findByIdAsDTO(SavefileDTO.getId());
    }

    @Test
    void create() throws Exception {
        //gameDTO GameDTO = new gameDTO(0,"TestState","TestCity","TestAddress");
        savefileCreateDTO SavefileCreateDTO = new savefileCreateDTO("TestName",new Timestamp(1980-01-01),50,0);
        savefileDTO SavefileDTO = new savefileDTO(0,"TestName",new Timestamp(1980-01-01),50,0);

        BDDMockito.given(SavefileService.create(SavefileCreateDTO)).willReturn(SavefileDTO);
        Assertions.assertEquals(SavefileDTO,SavefileController.create(SavefileCreateDTO));
        Mockito.verify(SavefileService, Mockito.atLeastOnce()).create(SavefileCreateDTO);
    }

    @Test
    void update() throws Exception {
        savefileCreateDTO SavefileCreateDTO = new savefileCreateDTO("TestName",new Timestamp(1980-01-01),50,0);
        savefileDTO SavefileDTO = new savefileDTO(0,"TestName",new Timestamp(1980-01-01),50,0);

        BDDMockito.given(SavefileService.update(SavefileDTO.getId(),SavefileCreateDTO)).willReturn(SavefileDTO);

        
        Assertions.assertEquals(SavefileDTO,SavefileController.update(SavefileDTO.getId(),SavefileCreateDTO));

        Mockito.verify(SavefileService, Mockito.atLeastOnce()).update(SavefileDTO.getId(),SavefileCreateDTO);

        BDDMockito.given(SavefileService.update(SavefileDTO.getId(),SavefileCreateDTO)).willThrow(Exception.class);
        Assertions.assertThrows(Exception.class,()->SavefileController.update(SavefileDTO.getId(),SavefileCreateDTO));
    }

    @Test
    void delete() throws Exception {
        game Game = new game("TestName","TestHardware",new Timestamp(1970-01-01),null,null);
        savefile TestSavefile = new savefile("TestName",new Timestamp(1980-01-01),50,Game);
        SavefileController.delete(0);
        Mockito.verify(SavefileService, Mockito.atLeastOnce()).delete(TestSavefile.getId());
    }
}