package cz.cvut.fit.matousi1.controller;

import cz.cvut.fit.matousi1.dto.gameDTO;
import cz.cvut.fit.matousi1.dto.gameCreateDTO;
import cz.cvut.fit.matousi1.entities.game;
import cz.cvut.fit.matousi1.service.gameService;
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

@AutoConfigureMockMvc
@SpringBootTest
class gameControllerTest {


    @Autowired
    gameController GameController;

    @MockBean
    private gameService GameService;



    @Test
    void readAll() {
        gameDTO GameDTO = new gameDTO(0,"TestName","TestHardware",new Timestamp(1980-01-01),null,0);
        List<gameDTO> ListGameDTO = new ArrayList<gameDTO>();
        ListGameDTO.add(new gameDTO(0,"TestName","TestHardware",new Timestamp(1980-01-01),null,0));
        ListGameDTO.add(new gameDTO(1,"TestName","TestHardware",new Timestamp(1980-01-01),null,0));
        ListGameDTO.add(new gameDTO(2,"TestName","TestHardware",new Timestamp(1980-01-01),null,0));
        BDDMockito.given(GameService.findAll()).willReturn(ListGameDTO);
        Assertions.assertEquals(ListGameDTO,GameController.readAll());
        Mockito.verify(GameService, Mockito.atLeastOnce()).findAll();
    }

    @Test
    void readById() {
        gameDTO GameDTO = new gameDTO(0,"TestName","TestHardware",new Timestamp(1980-01-01),null,0);
        BDDMockito.given(GameService.findByIdAsDTO(GameDTO.getId())).willReturn(Optional.of(GameDTO));
        Assertions.assertEquals(GameDTO,GameController.readById(GameDTO.getId()));
        Mockito.verify(GameService, Mockito.atLeastOnce()).findByIdAsDTO(GameDTO.getId());
    }

    @Test
    void create() throws Exception {
        gameDTO GameDTO = new gameDTO(0,"TestName","TestHardware",new Timestamp(1980-01-01),null,0);
        gameCreateDTO GameCreateDTO = new gameCreateDTO("TestName","TestHardware",new Timestamp(1980-01-01),null,0);

        BDDMockito.given(GameService.create(GameCreateDTO)).willReturn(GameDTO);
        Assertions.assertEquals(GameDTO,GameController.create(GameCreateDTO));
        Mockito.verify(GameService, Mockito.atLeastOnce()).create(GameCreateDTO);
    }

    @Test
    void update() throws Exception {
        gameDTO GameDTO = new gameDTO(0,"TestName","TestHardware",new Timestamp(1980-01-01),null,0);
        gameCreateDTO GameCreateDTO = new gameCreateDTO("TestName","TestHardware",new Timestamp(1980-01-01),null,0);

        BDDMockito.given(GameService.update(GameDTO.getId(),GameCreateDTO)).willReturn(GameDTO);

        
        Assertions.assertEquals(GameDTO,GameController.update(GameDTO.getId(),GameCreateDTO));

        Mockito.verify(GameService, Mockito.atLeastOnce()).update(GameDTO.getId(),GameCreateDTO);

        BDDMockito.given(GameService.update(GameDTO.getId(),GameCreateDTO)).willThrow(Exception.class);
        Assertions.assertThrows(Exception.class,()->GameController.update(GameDTO.getId(),GameCreateDTO));
    }

    @Test
    void delete() throws Exception {
        game Game = new game("TestName","TestHardware",new Timestamp(1980-01-01),null,null);
        GameController.delete(0);
        Mockito.verify(GameService, Mockito.atLeastOnce()).delete(Game.getId());
    }
}