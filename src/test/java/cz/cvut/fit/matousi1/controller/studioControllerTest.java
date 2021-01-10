package cz.cvut.fit.matousi1.controller;

import cz.cvut.fit.matousi1.dto.locationDTO;
import cz.cvut.fit.matousi1.dto.studioCreateDTO;
import cz.cvut.fit.matousi1.dto.studioDTO;
import cz.cvut.fit.matousi1.entities.location;
import cz.cvut.fit.matousi1.entities.studio;
import cz.cvut.fit.matousi1.service.studioService;
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
class studioControllerTest {


    @Autowired
    studioController StudioController;

    @MockBean
    private studioService StudioService;



    @Test
    void readAll() {
        locationDTO LocationDTO = new locationDTO(0,"TestState","TestCity","TestAddress");
        List<studioDTO> ListStudDTO = new ArrayList<studioDTO>();
        ListStudDTO.add(new studioDTO(0,"TestName",new Timestamp(1980-01-01),LocationDTO.getId()));
        ListStudDTO.add(new studioDTO(1,"TestName",new Timestamp(1980-01-01),LocationDTO.getId()));
        ListStudDTO.add(new studioDTO(2,"TestName",new Timestamp(1980-01-01),LocationDTO.getId()));
        BDDMockito.given(StudioService.findAll()).willReturn(ListStudDTO);
        Assertions.assertEquals(ListStudDTO,StudioController.readAll());
        Mockito.verify(StudioService, Mockito.atLeastOnce()).findAll();
    }

    @Test
    void readById() {
        locationDTO LocationDTO = new locationDTO(0,"TestState","TestCity","TestAddress");
        studioDTO StudioDTO = new studioDTO(0,"TestName",new Timestamp(1980-01-01),LocationDTO.getId());
        BDDMockito.given(StudioService.findByIdAsDTO(StudioDTO.getId())).willReturn(Optional.of(StudioDTO));
        Assertions.assertEquals(StudioDTO,StudioController.readById(StudioDTO.getId()));
        Mockito.verify(StudioService, Mockito.atLeastOnce()).findByIdAsDTO(StudioDTO.getId());
    }

    @Test
    void create() throws Exception {
        locationDTO LocationDTO = new locationDTO(0,"TestState","TestCity","TestAddress");
        studioCreateDTO StudioCreateDTO = new studioCreateDTO("TestName",new Timestamp(1980-01-01),LocationDTO.getId());
        studioDTO StudioDTO = new studioDTO(0,StudioCreateDTO.getName(),StudioCreateDTO.getFounding_date(),StudioCreateDTO.getLocation_id());

        BDDMockito.given(StudioService.create(StudioCreateDTO)).willReturn(StudioDTO);
        Assertions.assertEquals(StudioDTO,StudioController.create(StudioCreateDTO));
        Mockito.verify(StudioService, Mockito.atLeastOnce()).create(StudioCreateDTO);
    }

    @Test
    void update() throws Exception {
        locationDTO LocationDTO = new locationDTO(0,"TestState","TestCity","TestAddress");
        studioCreateDTO StudioCreateDTO = new studioCreateDTO("TestName",new Timestamp(1980-01-01),LocationDTO.getId());
        studioDTO StudioDTO = new studioDTO(0,StudioCreateDTO.getName(),StudioCreateDTO.getFounding_date(),StudioCreateDTO.getLocation_id());

        BDDMockito.given(StudioService.update(StudioDTO.getId(),StudioCreateDTO)).willReturn(StudioDTO);

        
        Assertions.assertEquals(StudioDTO,StudioController.update(StudioDTO.getId(),StudioCreateDTO));

        Mockito.verify(StudioService, Mockito.atLeastOnce()).update(StudioDTO.getId(),StudioCreateDTO);

        BDDMockito.given(StudioService.update(StudioDTO.getId(),StudioCreateDTO)).willThrow(Exception.class);
        Assertions.assertThrows(Exception.class,()->StudioController.update(StudioDTO.getId(),StudioCreateDTO));
    }

    @Test
    void delete() throws Exception {
        location Location = new location("TestState","TestCity","TestAddress");
        studio TestStudio = new studio("TestName",new Timestamp(1980-01-01),Location);
        StudioController.delete(0);
        Mockito.verify(StudioService, Mockito.atLeastOnce()).delete(TestStudio.getId());
    }
}