package com.example.agence_voyage_back.Repository;

import com.example.agence_voyage_back.Entities.Admin;
import com.example.agence_voyage_back.Entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long > {
    Admin findAdminByEmail(String email);

    Client findByEmail(String email);
}
