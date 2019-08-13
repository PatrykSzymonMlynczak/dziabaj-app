package pl.manciak.dziabajapp.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.manciak.dziabajapp.dao.GramRepo;
import pl.manciak.dziabajapp.dao.entity.Gram;

import java.util.Optional;

@Service
public class GramManager  {

    GramRepo gramRepo;

    @Autowired
    public GramManager(GramRepo gramRepo) { this.gramRepo = gramRepo; }

    public Gram save (Gram gram){
        return gramRepo.save(gram);
    }

    public Optional<Gram> findLast(){ return gramRepo.findTopByOrderByIdDesc();}

}
