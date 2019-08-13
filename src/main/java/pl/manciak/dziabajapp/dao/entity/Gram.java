package pl.manciak.dziabajapp.dao.entity;

import javax.persistence.*;

@Entity
@Table(name = "grams")
public class Gram {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    Float weight;

    public Gram(){}

    public Gram(Float weight) {
        this.weight = weight;
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }
}
