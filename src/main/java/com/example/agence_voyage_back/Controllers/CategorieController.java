package com.example.agence_voyage_back.Controllers;

import com.example.agence_voyage_back.Entities.Categorie;
import com.example.agence_voyage_back.Services.CategorieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RequestMapping(value="/categorie")
@RestController
public class CategorieController {

    @Autowired
    CategorieService categorieService;
    @PostMapping()
    public ResponseEntity<Object>ajouterCategorie(@RequestBody Categorie categorie){
       return categorieService.ajouterCategorie(categorie);
    }
    @PutMapping("{id}")
    public ResponseEntity<Object>modifierCategorie(@RequestBody Categorie categorie,@PathVariable Long id){
        return categorieService.ModifierCategorie(categorie,id);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Object> supprimerCategorie(@PathVariable Long id){
        return categorieService.SupprimerCategorie(id);
    }
    @GetMapping("{id}")
    public ResponseEntity<Object> afficherCategorie(@PathVariable Long id){
        return categorieService.AfficherCategorie(id);
    }
    @GetMapping()
    public List<Categorie> categories(){
        return categorieService.Categories();
    }

}
