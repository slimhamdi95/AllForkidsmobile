/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author FATNASSI
 */
public class Etablissement {
    
    private int id_etablissement;
    private int id_user;
    private String nom;
    private String type;
    private String region;
    private String ville;
    private String description;
    private String image;
    private String verification;

    public Etablissement() {
    }

    public Etablissement(String nom, String type, String region, String ville, String description, String image) {
        this.nom = nom;
        this.type = type;
        this.region = region;
        this.ville = ville;
        this.description = description;
        this.image = image;
    }
    

    public Etablissement(int id_etablissement, int id_user, String nom, String type, String region, String ville, String description, String image, String verification) {
        this.id_etablissement = id_etablissement;
        this.id_user = id_user;
        this.nom = nom;
        this.type = type;
        this.region = region;
        this.ville = ville;
        this.description = description;
        this.image = image;
        this.verification = verification;
    }

    public Etablissement(String nom, String type, String region, String ville, String description, String image, String verification) {
        this.nom = nom;
        this.type = type;
        this.region = region;
        this.ville = ville;
        this.description = description;
        this.image = image;
        this.verification = verification;
    }
    public Etablissement(String nom, String type, String region, String ville, String description) {
        this.nom = nom;
        this.type = type;
        this.region = region;
        this.ville = ville;
        this.description = description;
    }

    public int getId_etablissement() {
        return id_etablissement;
    }

    public void setId_etablissement(int id_etablissement) {
        this.id_etablissement = id_etablissement;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getVerification() {
        return verification;
    }

    public void setVerification(String verification) {
        this.verification = verification;
    }

    @Override
    public String toString() {
        return "Etablissement{" + "id_etablissement=" + id_etablissement + ", id_user=" + id_user + ", nom=" + nom + ", type=" + type + ", region=" + region + ", ville=" + ville + ", description=" + description + ", image=" + image + ", verification=" + verification + '}';
    }
    
    
    
    
    
    
    
}

