package com.example.agence_voyage_back.Entities;

import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class Reservation_refusee extends Reservation{
    private EnumCause cause;
}
