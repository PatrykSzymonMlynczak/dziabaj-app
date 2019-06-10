package pl.manciak.dziabajapp.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.manciak.dziabajapp.dao.entity.Product;

@Repository
public interface ProductRepo extends CrudRepository<Product, Long> {
}
