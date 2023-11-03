package com.example.agence_voyage_back.Repository;

import com.example.agence_voyage_back.Entities.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategorieRepository extends JpaRepository<Categorie,Long> {
    Categorie findByTitre(String titre);
}
