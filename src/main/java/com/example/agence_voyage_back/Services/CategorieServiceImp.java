package com.example.agence_voyage_back.Services;

import com.example.agence_voyage_back.Entities.Categorie;
import com.example.agence_voyage_back.Repository.CategorieRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CategorieServiceImp  implements  CategorieService{
    @Autowired
    CategorieRepository categorieRepository;
@Override
    public ResponseEntity<Object>ajouterCategorie(Categorie categorie){
        // test if exist
        Categorie existCategorie=categorieRepository.findByTitre(categorie.getTitre());
           if (existCategorie !=null){
               return new ResponseEntity<>("categorie exist",HttpStatus.CONFLICT);
           }
           else {
               categorieRepository.save(categorie);
           return new ResponseEntity<>(categorie,HttpStatus.CREATED);

           }
    }

@Override
    public ResponseEntity<Object>ModifierCategorie(Categorie categorie,Long id){
        Optional<Categorie> existCategorie =categorieRepository.findById(id);
        if (existCategorie.isPresent()){
         Categorie   odlcategorie=existCategorie.get();
            odlcategorie.setTitre(categorie.getTitre());
            categorieRepository.save(odlcategorie);
            return new ResponseEntity<>("success",HttpStatus.NO_CONTENT);

        }
        else {

            return new ResponseEntity<>("categorie not existed",HttpStatus.NOT_FOUND);
        }

    }


    @Override
    public ResponseEntity<Object>SupprimerCategorie(Long id){
        Categorie categorie=categorieRepository.findById(id).get();
        if (categorie !=null){
            categorieRepository.deleteById(id);
            return new ResponseEntity<>(categorie    ,HttpStatus.NO_CONTENT);

        }
        else {
            return new ResponseEntity<>("non exist",HttpStatus.NOT_FOUND);
        }
    }



    @Override
    public ResponseEntity<Object> AfficherCategorie(Long id) {
        Optional categorie=categorieRepository.findById(id);
        if (categorie.isPresent()){
            return new ResponseEntity<>(categorie.get(),HttpStatus.OK);


        }
        else {
            return new ResponseEntity<>("non exist",HttpStatus.NOT_FOUND);
        }
    }


    @Override
    public List<Categorie> Categories() {
        return categorieRepository.findAll();
    }
}
