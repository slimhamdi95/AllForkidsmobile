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
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

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
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
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
    }

    @Override
    public String toString() {
        return "User{" + "username=" + username + ", email=" + email + ", enabled=" + enabled + ", salt=" + salt + ", roles=" + roles + ", cin=" + cin + ", nom=" + nom + ", prenom=" + prenom + ", date=" + date + ", picture=" + picture + ", password=" + password + '}';
    }

    public User() {
    }

    
}
