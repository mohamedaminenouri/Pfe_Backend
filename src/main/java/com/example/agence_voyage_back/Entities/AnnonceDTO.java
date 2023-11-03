package com.example.agence_voyage_back.Entities;

import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class AnnonceDTO {
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


    private Long  idcategorie;
    private Date date_deb;
    private Date date_fin;
    private String pension;
    public static Annonce ToEntity(AnnonceDTO annonceDTO){
        if (annonceDTO ==null){
            return null;

        }
        Annonce annonce=new Annonce();
        annonce.setId(annonceDTO.getId());
        annonce.setTitre(annonceDTO.getTitre());
        annonce.setDescription(annonceDTO.getDescription());
        annonce.setPrix(annonceDTO.getPrix());
        annonce.setLocalisation(annonceDTO.getLocalisation());
        annonce.setNb_etoile(annonceDTO.getNb_etoile());
        annonce.setImage(annonceDTO.getImage());
        annonce.setDate_deb(annonceDTO.getDate_deb());
        annonce.setDate_fin(annonceDTO.getDate_fin());

        return annonce;
    }
}
