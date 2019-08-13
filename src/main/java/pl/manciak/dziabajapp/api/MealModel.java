package pl.manciak.dziabajapp.api;


import java.util.HashMap;

public class MealModel{
    String name;
    HashMap<String,String> map;

    public MealModel(String name, HashMap<String, String> map) {
        this.name = name;
        this.map = map;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HashMap<String, String> getMap() {
        return map;
    }

    public void setMap(HashMap<String, String> map) {
        this.map = map;
    }
}
