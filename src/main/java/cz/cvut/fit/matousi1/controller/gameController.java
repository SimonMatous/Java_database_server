package cz.cvut.fit.matousi1.controller;

import cz.cvut.fit.matousi1.dto.gameCreateDTO;
import cz.cvut.fit.matousi1.dto.gameDTO;
import cz.cvut.fit.matousi1.service.gameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class gameController {
    private final gameService GameService;

    @Autowired
    public gameController(gameService GameService) { this.GameService = GameService; }


    @GetMapping("/game/all")
    List<gameDTO> readAll() { return GameService.findAll(); }

    @GetMapping("/game/{id}")
    gameDTO readById(@PathVariable int id) {
        return GameService.findByIdAsDTO(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
    /** missing funciton in gameService, first must add findAllBySoftwareName() in gameService, then can uncomment */
   // @GetMapping(value = "/game", params = {"software"})
   // List<gameDTO> bySoftwareName(@RequestParam String Software){ return GameService.findAllBySoftwareName(Software); }

    @PostMapping("/game")
    gameDTO create(@RequestBody gameCreateDTO Game) throws Exception { return GameService.create(Game); }

    @PutMapping("/game/{id}")
    gameDTO update(@PathVariable int id, @RequestBody gameCreateDTO Game ) throws Exception { return GameService.update(id,Game); }

    @DeleteMapping("/game/{id}")
    void delete(@PathVariable int id) throws Exception { GameService.delete(id); }
}
