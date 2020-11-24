package cz.cvut.fit.matousi1.controller;

import cz.cvut.fit.matousi1.dto.locationCreateDTO;
import cz.cvut.fit.matousi1.dto.locationDTO;
import cz.cvut.fit.matousi1.service.locationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class locationController {
    private final locationService LocationService;

    @Autowired
    public locationController(locationService LocationService) { this.LocationService = LocationService; }


    @GetMapping("/location/all")
    List<locationDTO> all() { return LocationService.findAll(); }

    @GetMapping("/location/{id}")
    locationDTO byId(@PathVariable int id) {
        return LocationService.findByIdAsDTO(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
    /** missing funciton in locationService, first must add findAllBySoftwareName() in locationService, then can uncomment */
   // @GetMapping(value = "/location", params = {"software"})
   // List<locationDTO> bySoftwareName(@RequestParam String Software){ return LocationService.findAllBySoftwareName(Software); }

    @PostMapping("/location")
    locationDTO save(@RequestBody locationCreateDTO Location) throws Exception { return LocationService.create(Location); }

    @PutMapping("/location/{id}")
    locationDTO save(@PathVariable int id, @RequestBody locationCreateDTO Location ) throws Exception { return LocationService.update(id,Location); }
}
