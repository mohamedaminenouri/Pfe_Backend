package com.example.agence_voyage_back.Repository;

import com.example.agence_voyage_back.Entities.Reservation_validee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Reservation_valideeRepository extends JpaRepository<Reservation_validee,Long> {
}
