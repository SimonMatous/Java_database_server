package cz.cvut.fit.matousi1.controller;

import cz.cvut.fit.matousi1.dto.hraCreateDTO;
import cz.cvut.fit.matousi1.dto.hraDTO;
import cz.cvut.fit.matousi1.service.hraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class hraController {
    private final hraService HraService;

    @Autowired
    public hraController(hraService HraService) { this.HraService = HraService; }


    @GetMapping("/hra/all")
    List<hraDTO> all() { return HraService.findAll(); }

    @GetMapping("/hra/{id}")
    hraDTO byId(@PathVariable int id) {
        return HraService.findByIdAsDTO(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
    /** missing funciton in hraService, first must add findAllBySoftwareName() in hraService, then can uncomment */
   // @GetMapping(value = "/hra", params = {"software"})
   // List<hraDTO> bySoftwareName(@RequestParam String Software){ return HraService.findAllBySoftwareName(Software); }

    @PostMapping("/hra")
    hraDTO save(@RequestBody hraCreateDTO Hra) throws Exception { return HraService.create(Hra); }

    @PutMapping("/hra/{id}")
    hraDTO save(@PathVariable int id, @RequestBody hraCreateDTO Hra ) throws Exception { return HraService.update(id,Hra); }
}
