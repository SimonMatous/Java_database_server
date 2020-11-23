package cz.cvut.fit.matousi1.repository;

import cz.cvut.fit.matousi1.entities.software;
import org.springframework.data.jpa.repository.JpaRepository;

public interface softwareRepository extends JpaRepository<software,Integer> {
}
