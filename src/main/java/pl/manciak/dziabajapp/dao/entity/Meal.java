package pl.manciak.dziabajapp.dao.entity;

import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Entity
@Table(name = "meals")
public  class Meal {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )
    private Long idMeal;

    private String name;

    @ManyToMany
    private Map<Product, Gram> productsWithWeight;

    public Meal(){}

    public Meal(String name, Map<Product, Gram> productsWithWeight) {
        this.name = name;
        this.productsWithWeight = productsWithWeight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<Product, Gram> getProductsWithWeight() {
        return productsWithWeight;
    }

    public void setProductsWithWeight(Map<Product, Gram> productsWithWeight) {
        this.productsWithWeight = productsWithWeight;
    }

    @Override
    public String toString() {
        return "Meal{" +
                "meal_ID=" + idMeal +
                ", name='" + name + '\'' +
                ", productsWithWeight=" + productsWithWeight +
                '}';
    }
}

