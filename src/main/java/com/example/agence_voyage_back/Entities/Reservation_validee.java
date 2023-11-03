package com.example.agence_voyage_back.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;
@Entity
@Data
public class Reservation_validee extends Reservation {
    private Date date_res;

    private long total;
    @JsonIgnoreProperties({"hibernateLazyInitializer"})
   @OneToOne(targetEntity = Client.class, fetch = FetchType.LAZY,cascade =  { CascadeType.ALL, },orphanRemoval = true)
    @JoinColumn(nullable = true, name = "client_id")
    private Client client;
    @JsonIgnoreProperties({"hibernateLazyInitializer"})
    @OneToOne(targetEntity = Annonce.class, fetch = FetchType.LAZY,cascade =  { CascadeType.ALL, },orphanRemoval = true)
    @JoinColumn(nullable = true, name = "annonce_id")
    private Annonce annonce;

    private String selectedPaymentMethod;
}
