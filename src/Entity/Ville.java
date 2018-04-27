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
public class Ville {
    
    private int id_ville;
    private int id_region;
    private String nom_ville;

    public Ville() {
    }

    public Ville(int id_ville, int id_region, String nom_ville) {
        this.id_ville = id_ville;
        this.id_region = id_region;
        this.nom_ville = nom_ville;
    }

    public int getId_ville() {
        return id_ville;
    }

    public void setId_ville(int id_ville) {
        this.id_ville = id_ville;
    }

    public int getId_region() {
        return id_region;
    }

    public void setId_region(int id_region) {
        this.id_region = id_region;
    }

    public String getNom_ville() {
        return nom_ville;
    }

    public void setNom_ville(String nom_ville) {
        this.nom_ville = nom_ville;
    }
    
    
    
}
