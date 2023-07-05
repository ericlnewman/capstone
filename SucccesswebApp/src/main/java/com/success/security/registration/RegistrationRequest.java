package com.success.security.registration;

import org.hibernate.annotations.NaturalId;



public record RegistrationRequest(
        String email,
        String password,
        String role) {
   
   public String getEmail() {
       return email;
   }

   public String getPassword() {
       return password;
   }

   public String getRole() {
       return role;
   }
}
