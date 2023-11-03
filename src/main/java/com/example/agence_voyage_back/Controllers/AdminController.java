package com.example.agence_voyage_back.Controllers;

import com.example.agence_voyage_back.Entities.Admin;
import com.example.agence_voyage_back.Repository.AdminRepository;
import com.example.agence_voyage_back.Services.AdminService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping(value="/admin")

public class AdminController {
    @Autowired
    AdminService adminService;

    @Autowired
    AdminRepository adminRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @PostMapping()
    public ResponseEntity<Object> AjouterAdmin(@RequestBody Admin admin){
        return adminService.AjouterAdmin(admin);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Object>SupprimerAdmin( @PathVariable Long id){
        return adminService.SupprimerAdmin(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> ModifierAdmin(@RequestBody Admin admin , @PathVariable(value="id") Long ida){
        return adminService.ModifierAdmin(admin,ida);
    }

@GetMapping()
    public List<Admin> Admins(){
        return adminService.AfficherAdmin();
    }
    @GetMapping("{id}")
public ResponseEntity<Object> getOne(@PathVariable Long id){
      return adminService.AfficherParId(id);
}




/* authentification */

@PostMapping("/login")
// String , object : email , mot de passe
public ResponseEntity<Map<String, Object> > loginAdmin(@RequestBody Admin admin) {
    System.out.println("in login-admin" + admin);
    HashMap<String, Object> response = new HashMap<>();

    Admin userFromDB = adminRepository.findAdminByEmail(admin.getEmail());
    System.out.println("userFromDB+admin " + userFromDB);
    if (userFromDB == null) {
        response.put("message", "Admin not found !");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    } else {
        // il crypte votre mot de passe (saisie) et il compare par qui il existe dans la base
       boolean compare = this.bCryptPasswordEncoder.matches(admin.getPassword(), userFromDB.getPassword());
      // boolean compare =admin.getPassword().equals(userFromDB.getPassword());
       System.out.println("mot de dpssse in data base "+userFromDB.getPassword());
        System.out.println("compare" + compare);
        if (!compare) {
            response.put("message", "admin not found !");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } else {
            String token = Jwts.builder()
                    .claim("data", userFromDB)
                    .signWith(SignatureAlgorithm.HS256, "SECRET")
                    .compact();
            response.put("token", token);
            System.out.println("hhh");
            return ResponseEntity.status(HttpStatus.OK).body( response);
        }

    }
}
    @GetMapping("/resetPassword/{email}")
    public ResponseEntity<?> ResetPassword(@PathVariable String email){
        return adminService.resetPassword(email);
    }

}