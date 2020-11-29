package cz.cvut.fit.matousi1.service;

import cz.cvut.fit.matousi1.dto.locationCreateDTO;
import cz.cvut.fit.matousi1.dto.locationDTO;
import cz.cvut.fit.matousi1.entities.location;
import cz.cvut.fit.matousi1.repository.locationRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import java.util.Optional;

@SpringBootTest
class locationServiceTest {
    @Autowired
    private locationService LocationService;
    @MockBean
    private locationRepository LocationRepository;

    @Test
    void create() {
        location TestLocation = new location("TestState","TestCity","TestAddress");

        locationDTO TestDTO = new locationDTO(TestLocation.getId(),TestLocation.getState(),TestLocation.getTown(),TestLocation.getAddress());
        locationCreateDTO TestCreateDTO = new locationCreateDTO(TestLocation.getState(),TestLocation.getTown(),TestLocation.getAddress());

        BDDMockito.given(LocationRepository.save(Mockito.any(location.class))).willReturn(TestLocation);

        Assertions.assertEquals(TestDTO,LocationService.create(TestCreateDTO));

       Mockito.verify(LocationRepository,Mockito.atLeastOnce()).save(Mockito.any(location.class));
    }

    @Test
    void update() throws Exception {
        location TestLocation = new location("TestState","TestCity","TestAddress");
        locationCreateDTO LocationCreateDTO = new locationCreateDTO("OtherTestState","OtherTestCity","TestAddress");

        BDDMockito.given(LocationRepository.findById(TestLocation.getId())).willReturn(Optional.of(TestLocation));
        Assertions.assertNotEquals(TestLocation.getState(),LocationCreateDTO.getState());

        LocationService.update(TestLocation.getId(),LocationCreateDTO);
        Assertions.assertEquals(TestLocation.getState(),LocationCreateDTO.getState());
        Assertions.assertEquals(TestLocation.getTown(),LocationCreateDTO.getTown());
        Assertions.assertEquals(TestLocation.getAddress(),LocationCreateDTO.getAddress());

        Mockito.verify(LocationRepository,Mockito.atLeastOnce()).findById(TestLocation.getId());

        BDDMockito.given(LocationRepository.findById(TestLocation.getId())).willReturn(Optional.empty());
        Assertions.assertThrows(Exception.class,()->LocationService.update(TestLocation.getId(),LocationCreateDTO));
    }

    @Test
    void delete() throws Exception {
        location TestLocation = new location("TestState","TestCity","TestAddress");
        BDDMockito.given(LocationRepository.findById(TestLocation.getId())).willReturn(Optional.of(TestLocation));
        LocationService.delete(TestLocation.getId());
        Mockito.verify(LocationRepository,Mockito.atLeastOnce()).delete(TestLocation);

        BDDMockito.given(LocationRepository.findById(TestLocation.getId())).willReturn(Optional.empty());
        Assertions.assertThrows(Exception.class,()->LocationService.delete(TestLocation.getId()));
    }
}