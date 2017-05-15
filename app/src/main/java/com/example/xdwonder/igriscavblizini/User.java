package com.example.xdwonder.igriscavblizini;

/**
 * Created by xdwonder on 4.3.2017.
 */

public class User {
   private String idUser;
   private String username;
   private String email;

   public User(String idUser, String username,String email) {
      this.idUser = idUser;
      this.username = username;
      this.email = email;
   }

   public String getIdUser() {
      return idUser;
   }

   public void setIdUser(String idUser) {
      this.idUser = idUser;
   }

   public String getUsername() {
      return username;
   }

   public void setUsername(String username) {
      this.username = username;
   }
}
