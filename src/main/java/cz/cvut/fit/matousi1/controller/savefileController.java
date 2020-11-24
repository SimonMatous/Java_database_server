package cz.cvut.fit.matousi1.controller;


import cz.cvut.fit.matousi1.dto.savefileCreateDTO;
import cz.cvut.fit.matousi1.dto.savefileDTO;
import cz.cvut.fit.matousi1.service.savefileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class savefileController {

    private final savefileService SavefileService;

    @Autowired
    public savefileController(savefileService SavefileService) { this.SavefileService = SavefileService; }

    @GetMapping("/savefile/all")
    List<savefileDTO> all() { return SavefileService.findAll(); }

    @GetMapping("/savefile/{id}")
    savefileDTO byId(@PathVariable int id){
        return SavefileService.findByIdAsDTO(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/savefile")
    savefileDTO save(@RequestBody savefileCreateDTO Savefile) throws Exception { return SavefileService.create(Savefile); }

    @PutMapping("/savefile/{id}")
    savefileDTO save(@PathVariable int id, @RequestBody savefileCreateDTO Savefile) throws Exception { return SavefileService.update(id,Savefile); }
}
