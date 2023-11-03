package com.example.agence_voyage_back.Repository;

import com.example.agence_voyage_back.Entities.Client;
import com.example.agence_voyage_back.Entities.ConfirmationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken
        ,Long> {
    void deleteByClient(Client client);
    
    ConfirmationToken findByConfirmationToken(String confirmationToken);

    ConfirmationToken findByClient(Long id);
}
