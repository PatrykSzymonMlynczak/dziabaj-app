package pl.manciak.dziabajapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.manciak.dziabajapp.dao.entity.Gram;

import java.util.Optional;

public interface GramRepo extends JpaRepository<Gram,Long> {
    Optional<Gram> findTopByOrderByIdDesc();

}
