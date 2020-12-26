package cz.cvut.fit.matousi1.service;

import cz.cvut.fit.matousi1.dto.softwareCreateDTO;
import cz.cvut.fit.matousi1.dto.softwareDTO;
import cz.cvut.fit.matousi1.entities.software;
import cz.cvut.fit.matousi1.repository.softwareRepository;
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
class softwareServiceTest {
    @Autowired
    private softwareService SoftwareService;
    @MockBean
    private softwareRepository SoftwareRepository;

    @Test
    void create() {
        software TestSoftware = new software("TestSoftwareName",new Timestamp(1980-01-01));

        softwareDTO TestDTO = new softwareDTO(TestSoftware.getId(),TestSoftware.getSoftware_name(),TestSoftware.getFounded_in());
        softwareCreateDTO TestCreateDTO = new softwareCreateDTO(TestSoftware.getSoftware_name(),TestSoftware.getFounded_in());

        BDDMockito.given(SoftwareRepository.save(Mockito.any(software.class))).willReturn(TestSoftware);

        Assertions.assertEquals(TestDTO,SoftwareService.create(TestCreateDTO));

        Mockito.verify(SoftwareRepository,Mockito.atLeastOnce()).save(Mockito.any(software.class));
    }

    @Test
    void update() throws Exception {
        software TestSoftware = new software("TestSoftwareName",new Timestamp(1980-01-01));
        softwareCreateDTO SoftwareCreateDTO = new softwareCreateDTO("OtherTestSoftwareName",new Timestamp(2000-01-01));

        BDDMockito.given(SoftwareRepository.findById(TestSoftware.getId())).willReturn(Optional.of(TestSoftware));
        Assertions.assertNotEquals(TestSoftware.getSoftware_name(),SoftwareCreateDTO.getSoftware_name());

        SoftwareService.update(TestSoftware.getId(),SoftwareCreateDTO);
        Assertions.assertEquals(TestSoftware.getSoftware_name(),SoftwareCreateDTO.getSoftware_name());

        Mockito.verify(SoftwareRepository,Mockito.atLeastOnce()).findById(TestSoftware.getId());

        BDDMockito.given(SoftwareRepository.findById(TestSoftware.getId())).willReturn(Optional.empty());
        Assertions.assertThrows(Exception.class,()->SoftwareService.update(TestSoftware.getId(),SoftwareCreateDTO));
    }

    @Test
    void delete() throws Exception {
        software TestSoftware = new software("TestSoftwareName",new Timestamp(1980-01-01));
        BDDMockito.given(SoftwareRepository.findById(TestSoftware.getId())).willReturn(Optional.of(TestSoftware));
        SoftwareService.delete(TestSoftware.getId());
        Mockito.verify(SoftwareRepository,Mockito.atLeastOnce()).delete(TestSoftware);

        BDDMockito.given(SoftwareRepository.findById(TestSoftware.getId())).willReturn(Optional.empty());
        Assertions.assertThrows(Exception.class,()->SoftwareService.delete(TestSoftware.getId()));
    }
}