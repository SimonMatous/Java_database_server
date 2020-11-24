package cz.cvut.fit.matousi1.controller;


import cz.cvut.fit.matousi1.dto.studioCreateDTO;
import cz.cvut.fit.matousi1.dto.studioDTO;
import cz.cvut.fit.matousi1.service.studioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class studioController {

    private final studioService StudioService;

    @Autowired
    public studioController(studioService StudioService) { this.StudioService = StudioService; }

    @GetMapping("/studio/all")
    List<studioDTO> all() { return StudioService.findAll(); }

    @GetMapping("/studio/{id}")
    studioDTO byId(@PathVariable int id){
        return StudioService.findByIdAsDTO(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/studio")
    studioDTO save(@RequestBody studioCreateDTO Studio) throws Exception { return StudioService.create(Studio); }

    @PutMapping("/studio/{id}")
    studioDTO save(@PathVariable int id, @RequestBody studioCreateDTO Studio) throws Exception { return StudioService.update(id,Studio); }
}
