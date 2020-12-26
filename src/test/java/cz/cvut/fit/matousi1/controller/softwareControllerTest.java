package cz.cvut.fit.matousi1.controller;

import cz.cvut.fit.matousi1.dto.locationDTO;
import cz.cvut.fit.matousi1.dto.softwareCreateDTO;
import cz.cvut.fit.matousi1.dto.softwareDTO;
import cz.cvut.fit.matousi1.entities.location;
import cz.cvut.fit.matousi1.entities.software;
import cz.cvut.fit.matousi1.service.softwareService;
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
class softwareControllerTest {


    @Autowired
    softwareController SoftwareController;

    @MockBean
    private softwareService SoftwareService;



    @Test
    void readAll() {
        List<softwareDTO> ListsoftDTO = new ArrayList<softwareDTO>();
        ListsoftDTO.add(new softwareDTO(0,"TestName",new Timestamp(1980-01-01)));
        ListsoftDTO.add(new softwareDTO(1,"TestName",new Timestamp(1980-01-01)));
        ListsoftDTO.add(new softwareDTO(2,"TestName",new Timestamp(1980-01-01)));
        BDDMockito.given(SoftwareService.findAll()).willReturn(ListsoftDTO);
        Assertions.assertEquals(ListsoftDTO,SoftwareController.readAll());
        Mockito.verify(SoftwareService, Mockito.atLeastOnce()).findAll();
    }

    @Test
    void readById() {
        softwareDTO SoftwareDTO = new softwareDTO(0,"TestName",new Timestamp(1980-01-01));
        BDDMockito.given(SoftwareService.findByIdAsDTO(SoftwareDTO.getId())).willReturn(Optional.of(SoftwareDTO));
        Assertions.assertEquals(SoftwareDTO,SoftwareController.readById(SoftwareDTO.getId()));
        Mockito.verify(SoftwareService, Mockito.atLeastOnce()).findByIdAsDTO(SoftwareDTO.getId());
    }

    @Test
    void create() throws Exception {
        softwareCreateDTO SoftwareCreateDTO = new softwareCreateDTO("TestName",new Timestamp(1980-01-01));
        softwareDTO SoftwareDTO = new softwareDTO(0,SoftwareCreateDTO.getSoftware_name(),SoftwareCreateDTO.getFounded_in());

        BDDMockito.given(SoftwareService.create(SoftwareCreateDTO)).willReturn(SoftwareDTO);
        Assertions.assertEquals(SoftwareDTO,SoftwareController.create(SoftwareCreateDTO));
        Mockito.verify(SoftwareService, Mockito.atLeastOnce()).create(SoftwareCreateDTO);
    }

    @Test
    void update() throws Exception {
        softwareCreateDTO SoftwareCreateDTO = new softwareCreateDTO("TestName",new Timestamp(1980-01-01));
        softwareDTO SoftwareDTO = new softwareDTO(0,SoftwareCreateDTO.getSoftware_name(),SoftwareCreateDTO.getFounded_in());

        BDDMockito.given(SoftwareService.update(SoftwareDTO.getId(),SoftwareCreateDTO)).willReturn(SoftwareDTO);

        
        Assertions.assertEquals(SoftwareDTO,SoftwareController.update(SoftwareDTO.getId(),SoftwareCreateDTO));

        Mockito.verify(SoftwareService, Mockito.atLeastOnce()).update(SoftwareDTO.getId(),SoftwareCreateDTO);

        BDDMockito.given(SoftwareService.update(SoftwareDTO.getId(),SoftwareCreateDTO)).willThrow(Exception.class);
        Assertions.assertThrows(Exception.class,()->SoftwareController.update(SoftwareDTO.getId(),SoftwareCreateDTO));
    }

    @Test
    void delete() throws Exception {
        location Location = new location("TestState","TestCity","TestAddress");
        software TestSoftware = new software("TestName",new Timestamp(1980-01-01));
        SoftwareController.delete(0);
        Mockito.verify(SoftwareService, Mockito.atLeastOnce()).delete(TestSoftware.getId());
    }
}