package cz.cvut.fit.matousi1.controller;

import cz.cvut.fit.matousi1.service.locationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class locationControllerTest {

    @Autowired
    private locationController LocationController;
    @MockBean
    private locationService LocationService;

    @Test
    void readAll() {
    }

    @Test
    void readById() {
    }

    @Test
    void create() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}