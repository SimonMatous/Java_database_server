package cz.cvut.fit.matousi1.repository;

import cz.cvut.fit.matousi1.entities.hra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface hraRepository extends JpaRepository<hra, Integer> {

    Optional<hra> findByNazev(String nazev);



}
