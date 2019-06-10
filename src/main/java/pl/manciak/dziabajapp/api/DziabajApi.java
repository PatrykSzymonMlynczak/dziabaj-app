package pl.manciak.dziabajapp.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.manciak.dziabajapp.dao.entity.Product;
import pl.manciak.dziabajapp.manager.ProductManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/a")
public class DziabajApi {

/*    private List<Product> products;*/

/*    public DziabajApi(){
        products = new ArrayList<>();
        products.add(new Product(1L, "Marchew", 200));
        products.add(new Product(2L, "Jabłko", 200));
        products.add(new Product(3L, "Kapusta Biała", 200));
    }*/

    private ProductManager productManager;

    @Autowired
    public DziabajApi(ProductManager productManager){
        this.productManager = productManager;
    }

    @GetMapping("/all")
    public Iterable<Product> showAll(){

        return productManager.findAll();
    }

    @GetMapping()
    public Optional <Product> getById(@RequestParam Long index){
/*        Optional<Product> first = products
                .stream()
                .filter(element -> element.getId() == index)
                .findFirst();

        return first.get();*/
        return productManager.findById(index);
    }

    @PostMapping("/all")
    public Product addProduct(@RequestBody Product product){
        return productManager.save(product);
    }

    @DeleteMapping()
    public void deleteById(@RequestParam Long index){
        /*return products.removeIf(element -> element.getId() == index);*/
        productManager.deleteById(index);
    }
}
