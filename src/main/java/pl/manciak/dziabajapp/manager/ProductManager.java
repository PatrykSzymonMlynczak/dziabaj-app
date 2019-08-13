package pl.manciak.dziabajapp.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import pl.manciak.dziabajapp.dao.ProductRepo;
import pl.manciak.dziabajapp.dao.entity.Product;

import java.util.Optional;

@Service
public class ProductManager  {

    private ProductRepo repo;

    @Autowired
    ProductManager(ProductRepo repo){
        this.repo = repo;
    }

    public Optional<Product> findById(long id){
        return repo.findById(id);
    }

    public Optional<Product> findByName(String name){ return repo.findByName(name); }

    public Iterable<Product> findAll(){
        return repo.findAll();
    }

    public void deleteById(Long id){
         repo.deleteById(id);
    }

    public Product save (Product product){
        return repo.save(product);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void fillDB(){

        save(new Product( "Marchew", 200F, 2.22F, 1F,1F,1F));
        save(new Product( "Gruszka", 110F, 3.33F, 1F,1F,1F));
        save(new Product( "Jajko", 220F, 3.33F, 1F,1F,1F));
        save(new Product( "Jabłko", 150F, 3.33F, 1F,1F,1F));
        save(new Product( "Jarmóż", 100F, 3.33F, 1F,1F,1F));
    }

}
