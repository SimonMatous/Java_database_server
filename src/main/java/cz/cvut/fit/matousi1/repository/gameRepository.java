package cz.cvut.fit.matousi1.repository;

import cz.cvut.fit.matousi1.entities.game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface gameRepository extends JpaRepository<game, Integer> {

    Optional<game> findByName(String name);



}
