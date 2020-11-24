package cz.cvut.fit.matousi1.controller;


import cz.cvut.fit.matousi1.dto.softwareCreateDTO;
import cz.cvut.fit.matousi1.dto.softwareDTO;
import cz.cvut.fit.matousi1.service.softwareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class softwareController {

    private final softwareService SoftwareService;

    @Autowired
    public softwareController(softwareService SoftwareService) { this.SoftwareService = SoftwareService; }

    @GetMapping("/software/all")
    List<softwareDTO> all() { return SoftwareService.findAll(); }

    @GetMapping("/software/{id}")
    softwareDTO byId(@PathVariable int id){
        return SoftwareService.findByIdAsDTO(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/software")
    softwareDTO save(@RequestBody softwareCreateDTO Software) { return SoftwareService.create(Software); }

    @PutMapping("/software/{id}")
    softwareDTO save(@PathVariable int id, @RequestBody softwareCreateDTO Software) throws Exception { return SoftwareService.update(id,Software); }
}
