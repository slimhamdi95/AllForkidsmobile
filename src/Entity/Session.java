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
public class Session {
    private static int id;
    private static String userName;
    private static String email;
    private static String password;
    private static int enabled;
    private static String roles;
    private static String nom;
    private static String prenom;
    
    
    
    public static String facebook;
    public static String photo=" ";
    public static String facebookName;
    
    
    public Session(String userName, String email, String password/*, int enabled, String roles*/, String nom, String prenom) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        //this.enabled = enabled;
       // this.roles = roles;
        this.nom = nom;
        this.prenom = prenom;
    }
     public Session(String userName, String email, String password/*, int enabled, String roles*/, String nom, String prenom, int enabled,int id) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.enabled = enabled;
       // this.roles = roles;
        this.nom = nom;
        this.prenom = prenom;
        this.id=id;
    }

    public Session() {
    }

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        Session.id = id;
    }

    public static String getUserName() {
        return userName;
    }

    public static void setUserName(String userName) {
        Session.userName = userName;
    }

    public static String getEmail() {
        return email;
    }

    public static void setEmail(String email) {
        Session.email = email;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        Session.password = password;
    }

    public static int getEnabled() {
        return enabled;
    }

    public static void setEnabled(int enabled) {
        Session.enabled = enabled;
    }

    public static String getRoles() {
        return roles;
    }

    public static void setRoles(String roles) {
        Session.roles = roles;
    }

    public static String getNom() {
        return nom;
    }

    public static void setNom(String nom) {
        Session.nom = nom;
    }

    public static String getPrenom() {
        return prenom;
    }

    public static void setPrenom(String prenom) {
        Session.prenom = prenom;
    }

   @Override
    public String toString() {
        return "Session{" + "id=" + id + ", userName=" + userName + ", email=" + email + ", password=" + password + ", enabled=" + enabled + ", roles=" + roles + ", nom=" + nom + ", prenom=" + prenom + '}';
    }

    
    

    
}
