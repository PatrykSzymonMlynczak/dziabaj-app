package pl.manciak.dziabajapp.api;

import org.springframework.web.bind.annotation.*;

////////////////PIERDOŁY/////////////////
////////////////PIERDOŁY/////////////////

@RestController
public class DziabajApi {

    @GetMapping("/siema")
    public String siema(){ return "Eloszka";  }


    @GetMapping("/cent")
    public float silnia(){
        float silnia = 0.01F;
        for (int i=0; i<31; i++){
            silnia *= 2;
        }
        return  silnia;
    }


}
