package com.example.agence_voyage_back.Services;

import com.example.agence_voyage_back.Entities.Annonce;
import com.example.agence_voyage_back.Entities.AnnonceDTO;
import com.example.agence_voyage_back.Entities.Categorie;
import com.example.agence_voyage_back.Repository.AnnonceRepository;
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
public class AnnonceServiceImp implements AnnonceService{

    @Autowired
    AnnonceRepository annonceRepository;

    @Autowired
    CategorieRepository categorieRepository;

    @Override
    public ResponseEntity<Object> ajouterAnnonce(AnnonceDTO annonceDTO) {
         Annonce annonce= AnnonceDTO.ToEntity(annonceDTO);
               System.out.println("id categorie"+annonceDTO.getIdcategorie());
               Categorie categorie=categorieRepository.findById(annonceDTO.getIdcategorie()).get();
               annonce.setCategorie(categorie);
               annonceRepository.save(annonce);
           return new ResponseEntity<>(annonce,HttpStatus.OK);
      }



    @Override
    public ResponseEntity<Object> modifierAnnonce(AnnonceDTO NewAnnonce, Long id) {
        Optional<Annonce> existAnnonce=annonceRepository.findById(id);
        System.out.println(existAnnonce);
        if (existAnnonce.isPresent())
        {
            Annonce oldAnnonce=existAnnonce.get();
            oldAnnonce.setTitre(NewAnnonce.getTitre());
            oldAnnonce.setDescription((NewAnnonce.getDescription()));
            oldAnnonce.setPrix(NewAnnonce.getPrix());
            oldAnnonce.setLocalisation(NewAnnonce.getLocalisation());
            oldAnnonce.setNb_etoile(NewAnnonce.getNb_etoile());
            oldAnnonce.setImage(NewAnnonce.getImage());
            System.out.println("New Annonce"+NewAnnonce.getIdcategorie());
            oldAnnonce.setCategorie(categorieRepository.findById(NewAnnonce.getIdcategorie()).get());
            annonceRepository.save(oldAnnonce);
             return new ResponseEntity<>("success modification",HttpStatus.CREATED);
        }
        else{
            return new ResponseEntity<>("annonce n''existe pas ",HttpStatus.CONFLICT);
        }

    }

    @Override
    public ResponseEntity<Object> supprimerAnnonce(Long id) {
        Optional<Annonce> existAnnonce=annonceRepository.findById(id);
        if (existAnnonce.isPresent())
        {
            annonceRepository.deleteById(id);
            return new ResponseEntity<>(existAnnonce,HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("non existe",HttpStatus.NOT_FOUND);
        }

    }
    @Override
    public ResponseEntity<Object> afficherAnnonce(Long id) {
        Optional<Annonce> existAnnonce=annonceRepository.findById(id);
        if (existAnnonce.isPresent()){
            return new ResponseEntity<>(existAnnonce.get(),HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("non exist",HttpStatus.CONFLICT);
        }
    }


    @Override
    public List<Annonce> annonces() {
        return annonceRepository.findAll();
    }
}
