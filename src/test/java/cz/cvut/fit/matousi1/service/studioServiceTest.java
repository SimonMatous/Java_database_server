package cz.cvut.fit.matousi1.service;

import cz.cvut.fit.matousi1.dto.studioCreateDTO;
import cz.cvut.fit.matousi1.dto.studioDTO;
import cz.cvut.fit.matousi1.entities.location;
import cz.cvut.fit.matousi1.entities.studio;
import cz.cvut.fit.matousi1.repository.locationRepository;
import cz.cvut.fit.matousi1.repository.studioRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.sql.Timestamp;
import java.util.Optional;

@SpringBootTest
class studioServiceTest {
    @Autowired
    private studioService StudioService;
    @MockBean
    private studioRepository StudioRepository;
    @MockBean
    private locationRepository LocationRepository;

    @Test
    void create() throws Exception {
        location TestLocation = new location("TestState","TestTown","TestAddress");
        studio TestStudio = new studio("TestName",new Timestamp(1980-01-01),TestLocation);

        studioDTO TestDTO = new studioDTO(TestStudio.getId(),TestStudio.getName(),TestStudio.getFounding_date(),TestStudio.getLocation().getId());
        studioCreateDTO TestCreateDTO = new studioCreateDTO(TestStudio.getName(),TestStudio.getFounding_date(),TestStudio.getLocation().getId());

        BDDMockito.given(LocationRepository.findById(TestLocation.getId())).willReturn(Optional.of(TestLocation));
        BDDMockito.given(StudioRepository.save(Mockito.any(studio.class))).willReturn(TestStudio);

        Assertions.assertEquals(TestDTO,StudioService.create(TestCreateDTO));
        Mockito.verify(StudioRepository,Mockito.atLeastOnce()).save(Mockito.any(studio.class));

        BDDMockito.given(LocationRepository.findById(TestLocation.getId())).willReturn(Optional.empty());
        Assertions.assertThrows(Exception.class,()->StudioService.create(TestCreateDTO));
    }

    @Test
    void update() throws Exception {
        location TestLocation = new location("state","town","address");
        studio TestStudio = new studio("TestName",new Timestamp(1980-01-01),TestLocation);
        studioCreateDTO StudioCreateDTO = new studioCreateDTO("OtherTestName",new Timestamp(1999-01-01),TestLocation.getId());

        BDDMockito.given(LocationRepository.findById(TestLocation.getId())).willReturn(Optional.of(TestLocation));
        BDDMockito.given(StudioRepository.findById(TestStudio.getId())).willReturn(Optional.of(TestStudio));
        Assertions.assertNotEquals(TestStudio.getName(),StudioCreateDTO.getName());

        StudioService.update(TestStudio.getId(),StudioCreateDTO);
        Assertions.assertEquals(TestStudio.getName(),StudioCreateDTO.getName());
        Assertions.assertEquals(TestStudio.getFounding_date(),StudioCreateDTO.getFounding_date());

        Mockito.verify(StudioRepository,Mockito.atLeastOnce()).findById(TestStudio.getId());

        BDDMockito.given(StudioRepository.findById(TestLocation.getId())).willReturn(Optional.empty());
        Assertions.assertThrows(Exception.class,()->StudioService.update(TestStudio.getId(),StudioCreateDTO));

        BDDMockito.given(LocationRepository.findById(TestLocation.getId())).willReturn(Optional.empty());
        BDDMockito.given(StudioRepository.findById(TestStudio.getId())).willReturn(Optional.of(TestStudio));
        Assertions.assertThrows(Exception.class,()->StudioService.update(TestStudio.getId(),StudioCreateDTO));
    }

    @Test
    void delete() throws Exception {
        location TestLocation = new location("state","town","address");
        studio TestStudio = new studio("TestName", new Timestamp(1980-01-01),TestLocation);
        BDDMockito.given(StudioRepository.findById(TestStudio.getId())).willReturn(Optional.of(TestStudio));
        StudioService.delete(TestStudio.getId());
        Mockito.verify(StudioRepository,Mockito.atLeastOnce()).delete(TestStudio);

        BDDMockito.given(StudioRepository.findById(TestLocation.getId())).willReturn(Optional.empty());
        Assertions.assertThrows(Exception.class,()->StudioService.delete(TestStudio.getId()));
    }

}