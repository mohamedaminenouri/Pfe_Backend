package com.example.agence_voyage_back.Repository;

import com.example.agence_voyage_back.Entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    Client findByEmail(String email);

    Client findAdminByEmail(String email);
    Client findByEmailIgnoreCase(String emailId);

    Boolean existsByEmail(String email);

    List<Client> findByNomContainingOrPrenomContaining(String query, String query1);
}
