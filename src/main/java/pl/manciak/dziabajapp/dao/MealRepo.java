package pl.manciak.dziabajapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.manciak.dziabajapp.dao.entity.Meal;
import pl.manciak.dziabajapp.dao.entity.Product;

import java.util.Optional;

@Repository
public interface MealRepo extends JpaRepository< Meal, Long>, CrudRepository<Meal, Long> {
    Optional<Meal> findByName(String name);

    //Meal saveOrUpdate(Meal meal);

}
