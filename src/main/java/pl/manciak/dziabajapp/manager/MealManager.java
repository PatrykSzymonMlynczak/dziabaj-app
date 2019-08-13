package pl.manciak.dziabajapp.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import pl.manciak.dziabajapp.dao.MealRepo;
import pl.manciak.dziabajapp.dao.entity.Meal;

import java.util.Optional;

@Service
public class MealManager {

    private MealRepo mealRepo;

    @Autowired
    MealManager(MealRepo mealRepo){
        this.mealRepo = mealRepo;
    }

    public Optional<Meal> findById(Long id){ return mealRepo.findById(id);}

    public Optional<Meal> findByName(String name){ return mealRepo.findByName(name);}

    public Iterable<Meal> findAll(){return mealRepo.findAll();}

    public void deleteById(Long id){
        mealRepo.deleteById(id);
    }

    public Meal save (Meal meal){
        return mealRepo.save(meal);
    }

   // public Meal update(Meal meal) {return mealRepo.saveOrUpdate(meal);}


    @EventListener(ApplicationReadyEvent.class)
    public void fillDB(){

        //save(new Meal("Salatka",);
       // save(new Meal( "Koktajl", "1|4|3", "200|200|300"));
    }

}
