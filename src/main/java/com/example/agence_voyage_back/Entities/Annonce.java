package com.example.agence_voyage_back.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.sql.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Annonce implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titre;
    private String localisation;
    private Float prix;
    private int nb_etoile;
    // @Lob pour elargir le byte de String de 255 vers >255
    @Lob
    private String image;
    @Lob
    private String description;
    private Date date_deb;
    private Date date_fin;

    @ManyToOne
     private Categorie  categorie;


}
