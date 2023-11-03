package com.example.agence_voyage_back.Controllers;

import com.example.agence_voyage_back.Entities.Annonce;
import com.example.agence_voyage_back.Entities.AnnonceDTO;
import com.example.agence_voyage_back.Services.AnnonceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@CrossOrigin("*")
@RequestMapping("annonce")
@RestController
public class AnnonceController {
    @Autowired
    AnnonceService annonceService;
    @PostMapping()
     public ResponseEntity<Object>ajouterAnnonce(@RequestBody AnnonceDTO annonceDTO){
      //  System.out.println(annonce.toString());
        return annonceService.ajouterAnnonce(annonceDTO);
    }


    @PutMapping("{id}")
    public ResponseEntity<Object>ModifierAnnonce(@RequestBody AnnonceDTO annonce,@PathVariable Long id){
        return annonceService.modifierAnnonce(annonce,id);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object>supprimerAnnonce(@PathVariable Long id){
        return annonceService.supprimerAnnonce(id);
    }
    @GetMapping("{id}")
    public ResponseEntity<Object>afficherAnnonce(@PathVariable  Long id ){
        return annonceService.afficherAnnonce(id);
    }
    @GetMapping()
    public List<Annonce> annonces(){
        return annonceService.annonces();
    }
}
