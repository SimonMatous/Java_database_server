package cz.cvut.fit.matousi1.repository;

import cz.cvut.fit.matousi1.entities.location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface locationRepository extends JpaRepository<location,Integer> {
}
