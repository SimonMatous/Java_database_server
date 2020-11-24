package cz.cvut.fit.matousi1.repository;

import cz.cvut.fit.matousi1.entities.location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface locationRepository extends JpaRepository<location,Integer> {
}
