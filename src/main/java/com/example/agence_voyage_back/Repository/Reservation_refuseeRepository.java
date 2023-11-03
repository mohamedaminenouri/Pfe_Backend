package com.example.agence_voyage_back.Repository;

import com.example.agence_voyage_back.Entities.Reservation_refusee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Reservation_refuseeRepository extends  JpaRepository< Reservation_refusee,Long> {
}
