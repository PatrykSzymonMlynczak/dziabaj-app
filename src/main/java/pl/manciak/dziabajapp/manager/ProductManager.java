package pl.manciak.dziabajapp.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import pl.manciak.dziabajapp.dao.ProductRepo;
import pl.manciak.dziabajapp.dao.entity.Product;

import java.util.Optional;

@Service
public class ProductManager {


    public ProductRepo repo;

    @Autowired
    ProductManager(ProductRepo repo){
        this.repo = repo;
    }

    public Optional<Product> findById(Long id){
        return repo.findById(id);
    }

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
        save(new Product( "Marchew", 200));
        save(new Product( "Jabłko", 200));
        save(new Product( "Kapusta Biała", 200));
    }

}
