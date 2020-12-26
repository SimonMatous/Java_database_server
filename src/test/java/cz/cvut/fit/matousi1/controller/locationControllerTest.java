package cz.cvut.fit.matousi1.controller;

import cz.cvut.fit.matousi1.dto.locationCreateDTO;
import cz.cvut.fit.matousi1.dto.locationDTO;
import cz.cvut.fit.matousi1.entities.location;
import cz.cvut.fit.matousi1.service.locationService;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@AutoConfigureMockMvc
@SpringBootTest
class locationControllerTest {


    @Autowired
    locationController LocationController;

    @MockBean
    private locationService LocationService;



    @Test
    void readAll() {
        List<locationDTO> ListLocDTO = new ArrayList<locationDTO>();
        ListLocDTO.add(new locationDTO(0,"TestState1","TestCity1","TestAddress1"));
        ListLocDTO.add(new locationDTO(1,"TestState2","TestCity2","TestAddress2"));
        ListLocDTO.add(new locationDTO(2,"TestState3","TestCity3","TestAddress3"));
        BDDMockito.given(LocationService.findAll()).willReturn(ListLocDTO);
        Assertions.assertEquals(ListLocDTO,LocationController.readAll());
        Mockito.verify(LocationService, Mockito.atLeastOnce()).findAll();
    }

    @Test
    void readById() {
        locationDTO LocationDTO = new locationDTO(0,"TestState","TestCity","TestAddress");
        BDDMockito.given(LocationService.findByIdAsDTO(LocationDTO.getId())).willReturn(Optional.of(LocationDTO));
        Assertions.assertEquals(LocationDTO,LocationController.readById(LocationDTO.getId()));
        Mockito.verify(LocationService, Mockito.atLeastOnce()).findByIdAsDTO(LocationDTO.getId());
    }

    @Test
    void create() {
        locationCreateDTO LocationCreateDTO = new locationCreateDTO("TestState","TestCity","TestAddress");
        locationDTO LocationDTO = new locationDTO(0,LocationCreateDTO.getState(),LocationCreateDTO.getTown(),LocationCreateDTO.getAddress());
        BDDMockito.given(LocationService.create(LocationCreateDTO)).willReturn(LocationDTO);
        Assertions.assertEquals(LocationDTO,LocationController.create(LocationCreateDTO));
        Mockito.verify(LocationService, Mockito.atLeastOnce()).create(LocationCreateDTO);
    }

    @Test
    void update() throws Exception {
        locationCreateDTO LocationCreateDTO = new locationCreateDTO("TestState","TestCity","TestAddress");
        locationDTO LocationDTO = new locationDTO(0,"TestState","TestCity","TestAddress");

        BDDMockito.given(LocationService.update(LocationDTO.getId(),LocationCreateDTO)).willReturn(LocationDTO);


        Assertions.assertEquals(LocationDTO,LocationController.update(LocationDTO.getId(),LocationCreateDTO));

        Mockito.verify(LocationService, Mockito.atLeastOnce()).update(LocationDTO.getId(),LocationCreateDTO);

        BDDMockito.given(LocationService.update(LocationDTO.getId(),LocationCreateDTO)).willThrow(Exception.class);
        Assertions.assertThrows(Exception.class,()->LocationController.update(LocationDTO.getId(),LocationCreateDTO));
    }

    @Test
    void delete() throws Exception {
        location TestLocation = new location("TestState","TestCity","TestAddress");
        LocationController.delete(TestLocation.getId());
        Mockito.verify(LocationService, Mockito.atLeastOnce()).delete(TestLocation.getId());
    }
}