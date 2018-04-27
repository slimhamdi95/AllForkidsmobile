/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author casa-net
 */
public class User {
    private    int id;
      private    String userName;
      private    String email;
      private    String password;
      private    int enabled;
      private    String roles;
      private    String nom;
      private    String prenom;
      private String photo;

    public User() {
    }

    public User(int id, String userName, String email, String password, int enabled, String roles, String nom, String prenom, String photo) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.enabled = enabled;
        this.roles = roles;
        this.nom = nom;
        this.prenom = prenom;
        this.photo = photo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", userName=" + userName + ", email=" + email + ", password=" + password + ", enabled=" + enabled + ", roles=" + roles + ", nom=" + nom + ", prenom=" + prenom + ", photo=" + photo + '}';
    }
      
      
}
