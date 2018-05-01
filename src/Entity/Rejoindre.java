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
public class Rejoindre {
    private int id;
    private int id_etablissement;
    private int id_user;
    private String verification;

    public Rejoindre() {
    }

    
    public Rejoindre(int id, int id_etablissement, int id_user, String verification) {
        this.id = id;
        this.id_etablissement = id_etablissement;
        this.id_user = id_user;
        this.verification = verification;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getVerification() {
        return verification;
    }

    public void setVerification(String verification) {
        this.verification = verification;
    }
    
    
    
}
