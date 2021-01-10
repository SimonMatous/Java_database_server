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
    List<studioDTO> readAll() { return StudioService.findAll(); }

    @GetMapping("/studio/{id}")
    studioDTO readById(@PathVariable int id){
        return StudioService.findByIdAsDTO(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/studio")
    studioDTO create(@RequestBody studioCreateDTO Studio) throws Exception { return StudioService.create(Studio); }

    @PutMapping("/studio/{id}")
    studioDTO update(@PathVariable int id, @RequestBody studioCreateDTO Studio) throws Exception { return StudioService.update(id,Studio); }

    @DeleteMapping("/studio/{id}")
    void delete(@PathVariable int id) throws Exception { StudioService.delete(id); }
}
