package pl.manciak.dziabajapp.api;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;
import pl.manciak.dziabajapp.dao.entity.Gram;
import pl.manciak.dziabajapp.dao.entity.Meal;
import pl.manciak.dziabajapp.dao.entity.Product;
import pl.manciak.dziabajapp.manager.GramManager;
import pl.manciak.dziabajapp.manager.MealManager;
import pl.manciak.dziabajapp.manager.ProductManager;

import java.util.*;

@RestController
@RequestMapping("/meal")
public class MealController {

    private ProductManager productManager;
    private MealManager mealManager;
    private GramManager gramManager;

    @Autowired
    public MealController(ProductManager productManager, MealManager mealManager, GramManager gramManager){

        this.productManager = productManager;
        this.mealManager = mealManager;
        this.gramManager =gramManager;
    }

    @PostMapping()
    public String createMeal(@RequestBody  MealModel mealModel){

        if(mealManager.findByName(mealModel.getName()).isPresent() == false) {

            Map<Product, Gram> productsWithGrams = new HashMap<>();

            for (HashMap.Entry<String, String> entry : mealModel.map.entrySet()) {
                Gram weight = new Gram();
                weight.setWeight(Float.parseFloat(entry.getValue()));
                gramManager.save(weight);

                productsWithGrams.put(
                        productManager.findById(Long.parseLong(entry.getKey())).get(),
                        gramManager.findLast().get()
                );
            }

            Meal meal = new Meal(mealModel.name, productsWithGrams);
            mealManager.save(meal);
        }else return "Taki element juz istnieje";

        return mealModel.map.entrySet().toString();
    }


    ////////////////// W TRAKCIE ROZBUDOWY ///////////////////////////////
    /////////////////////////////////////////////////////////////////////
    @PutMapping("/{name}/update")
    public String updateMeal(@RequestBody  MealModel mealModel){

       System.out.println( mealManager.findByName("salatka6").isPresent());
       Meal meal = mealManager.findByName("salatka6").get();

       for(Map.Entry<Product, Gram> mapa : meal.getProductsWithWeight().entrySet())
       {
           if( mapa.getKey().getId() == 1){
               Gram gram = new Gram(999F);
               mapa.setValue(gram);
           }
       }
        mealManager.save(meal);

       /* if(mealManager.findByName(mealModel.getName()).isPresent() == true) {

            Map<Product, Gram> productsWithGrams = new HashMap<>();

            for (HashMap.Entry<String, String> entry : mealModel.map.entrySet()) {
                Gram weight = new Gram();
                weight.setWeight(Float.parseFloat(entry.getValue()));
                gramManager.save(weight);

                productsWithGrams.put(
                        productManager.findById(Long.parseLong(entry.getKey())).get(),
                        gramManager.findLast().get()
                );
            }

           // Meal meal = new Meal(mealModel.name, productsWithGrams);
           // mealManager.save(meal);
        }else return "Nie ma takiego posiłku w bazie";
*/
        return mealModel.map.entrySet().toString();
    }

    @DeleteMapping("/{index}")
    public void deleteMelal(@PathVariable Long index){
        mealManager.deleteById(index);
    }

    @GetMapping
    @ResponseBody
    public Iterable<Meal> showAllMeals(){
        Iterable<Meal> allMeals = mealManager.findAll();
        for (Meal meal : allMeals) {
            System.out.println(meal.toString());
        }
        return allMeals;
    }

    @GetMapping ("/id/{id}/propertiesBy")
    @ResponseBody
    public String showMealById(@PathVariable Long id) {
        Float sumCal = 0F;
        Float sumCarbo = 0F;
        Float sumProt = 0F;
        Float sumFat = 0F;

        Map<Product,Gram> productsFromMeal = mealManager.findById(id).get().getProductsWithWeight();

        for(HashMap.Entry<Product,Gram> entry: productsFromMeal.entrySet())
        {
            sumCal += entry.getKey().getCalories()*entry.getValue().getWeight()/100;
            sumCarbo += entry.getKey().getCarbohydrates()*entry.getValue().getWeight()/100;
            sumProt += entry.getKey().getProtein()*entry.getValue().getWeight()/100;
            sumFat += entry.getKey().getFat()*entry.getValue().getWeight()/100;
        }
        return sumCal+" kcal" +"  "+sumCarbo+" wegli"+"  "+sumProt+"bialka "+sumFat+"tluszczu";
    }

    @GetMapping ("/{name}/properties")
    @ResponseBody
    public String showMealByName(@PathVariable String name) {
        Float sumCal = 0F;
        Float sumCarbo = 0F;
        Float sumProt = 0F;
        Float sumFat = 0F;

        Map<Product,Gram> productsFromMeal = mealManager.findByName(name).get().getProductsWithWeight();

        for(HashMap.Entry<Product,Gram> entry: productsFromMeal.entrySet())
        {
            sumCal += entry.getKey().getCalories()*entry.getValue().getWeight()/100;
            sumCarbo += entry.getKey().getCarbohydrates()*entry.getValue().getWeight()/100;
            sumProt += entry.getKey().getProtein()*entry.getValue().getWeight()/100;
            sumFat += entry.getKey().getFat()*entry.getValue().getWeight()/100;
        }
        return sumCal+" kcal" +"  "+sumCarbo+" wegli"+"  "+sumProt+"bialka "+sumFat+"tluszczu";
    }

}
