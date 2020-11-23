package cz.cvut.fit.matousi1.service;


import cz.cvut.fit.matousi1.entities.hra;
import cz.cvut.fit.matousi1.repository.hraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class hraService {

    private final hraRepository HraRepository;

    @Autowired
    public hraService(hraRepository hraRepository) {
        HraRepository = hraRepository;
    }

    public Optional<hra> findByNazev_id ( String nazev_id){
        return HraRepository.
    }
}
