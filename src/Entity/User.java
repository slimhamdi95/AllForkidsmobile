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
<<<<<<< HEAD
    private int id;
    private String username;
    private String email;
    private String enabled;
    private String salt;
    private String roles;
    private String cin;
    private String nom;
    private String prenom;
    private String date;
    private String  picture;
    private String  password;

    public User(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public User(int id, String username, String email, String enabled, String salt, String roles, String cin, String nom, String prenom, String date, String picture, String password) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.enabled = enabled;
        this.salt = salt;
        this.roles = roles;
        this.cin = cin;
        this.nom = nom;
        this.prenom = prenom;
        this.date = date;
        this.picture = picture;
        this.password = password;
=======
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
>>>>>>> a4ec22ca8015f371fc0f3b7a468f970ee862791e
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

<<<<<<< HEAD
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
=======
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
>>>>>>> a4ec22ca8015f371fc0f3b7a468f970ee862791e
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

<<<<<<< HEAD
    public String getEnabled() {
        return enabled;
    }

    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
=======
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
>>>>>>> a4ec22ca8015f371fc0f3b7a468f970ee862791e
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

<<<<<<< HEAD
    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

=======
>>>>>>> a4ec22ca8015f371fc0f3b7a468f970ee862791e
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

<<<<<<< HEAD
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
=======
    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
>>>>>>> a4ec22ca8015f371fc0f3b7a468f970ee862791e
    }

    @Override
    public String toString() {
<<<<<<< HEAD
        return "User{" + "username=" + username + ", email=" + email + ", enabled=" + enabled + ", salt=" + salt + ", roles=" + roles + ", cin=" + cin + ", nom=" + nom + ", prenom=" + prenom + ", date=" + date + ", picture=" + picture + ", password=" + password + '}';
    }

    public User() {
    }

    
=======
        return "User{" + "id=" + id + ", userName=" + userName + ", email=" + email + ", password=" + password + ", enabled=" + enabled + ", roles=" + roles + ", nom=" + nom + ", prenom=" + prenom + ", photo=" + photo + '}';
    }
      
      
>>>>>>> a4ec22ca8015f371fc0f3b7a468f970ee862791e
}
