package pl.manciak.dziabajapp.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.manciak.dziabajapp.dao.entity.Product;
import pl.manciak.dziabajapp.manager.ProductManager;

import java.util.HashMap;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductManager productManager;

    @Autowired
    public ProductController(ProductManager productManager) {
        this.productManager = productManager;
    }

    @GetMapping
    public Iterable<Product> showAllProducts(){
        return productManager.findAll();
    }

    @GetMapping(path = "id/{index}")
    public Optional<Product> getProductById(@PathVariable Long index){
        return productManager.findById(index);
    }

    @GetMapping(path = "/{name}")
    public Optional<Product> getProductByName(@PathVariable String name){
        return productManager.findByName(name);
    }

    @PostMapping()
    public Product addProduct(@RequestBody Product product){
        return productManager.save(product);
    }

    @PutMapping()
    public Product updateProduct(@RequestBody Product product){

        return productManager.save(product);
    }

    @DeleteMapping(path ="/{index}")
    public void deleteById(@PathVariable Long index){
        /*return products.removeIf(element -> element.getId() == index);*/
        productManager.deleteById(index);
    }

    @PostMapping("/sum")
    @ResponseBody
    public String summarizePropertiesById(@RequestBody HashMap<String,String> map) {
        Float sumCal = 0F;
        Float sumCarbo = 0F;
        Float sumProt = 0F;
        Float sumFat = 0F;

        for (HashMap.Entry<String,String> entry : map.entrySet()) {

            sumCal += (productManager.findById(Long.parseLong(entry.getKey()))
                    .map(Product::getCalories).orElse(0F))/100*Integer.parseInt(entry.getValue());
            sumCarbo += (productManager.findById(Long.parseLong(entry.getKey()))
                    .map(Product::getCarbohydrates).orElse(0F))/100*Integer.parseInt(entry.getValue());
            sumProt += (productManager.findById(Long.parseLong(entry.getKey()))
                    .map(Product::getProtein).orElse(0F))/100*Integer.parseInt(entry.getValue());
            sumFat += (productManager.findById(Long.parseLong(entry.getKey()))
                    .map(Product::getFat).orElse(0F))/100*Integer.parseInt(entry.getValue());
        }
        return sumCal+" kcal" +"  "+sumCarbo+" wegli"+"  "+sumProt+"bialka "+sumFat+"tluszczu";
    }
}
