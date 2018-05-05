/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.util.Date;

/**
 *
 * @author DELL
 */
public class Transport {
    
    private int idTransport;
    private String region;
    private String ville;
    private String depart;
    private String arrivé;  
    private String description;
    private String telephone;
    private String place;
    private String frais;
    private String type;
    private Date date;
    private String arriveName;
    private String departName;
    private int id_user ;

    public Transport(int idTransport, String region, String ville, String depart, String arrivé, String description, String telephone, String place, String frais, String type, Date date, String arriveName, String departName) {
        this.idTransport = idTransport;
        this.region = region;
        this.ville = ville;
        this.depart = depart;
        this.arrivé = arrivé;
        this.description = description;
        this.telephone = telephone;
        this.place = place;
        this.frais = frais;
        this.type = type;
        this.date = date;
        this.arriveName = arriveName;
        this.departName = departName;
    }

    public Transport(int idTransport, String region, String ville, String depart, String arrivé, String description, String telephone, String place, String frais, String type,Date date, String arriveName, String departName,int id_user) {
        this.idTransport = idTransport;
        this.region = region;
        this.ville = ville;
        this.depart = depart;
        this.arrivé = arrivé;
        this.description = description;
        this.telephone = telephone;
        this.place = place;
        this.frais = frais;
        this.type = type;
        this.date = date;
        this.arriveName = arriveName;
        this.departName = departName;
        this.id_user = id_user;
    }

    public Transport(String region, String ville, String depart, String arrivé, String description, String telephone,
            String place, String frais, String arriveName, String departName,int id_user) {
        this.region = region;
        this.ville = ville;
        this.depart = depart;
        this.arrivé = arrivé;
        this.description = description;
        this.telephone = telephone;
        this.place = place;
        this.frais = frais;
        this.arriveName = arriveName;
        this.departName = departName;
        this.id_user = id_user;
        
    }
    
    public Transport(String region, String ville, String depart, String arrivé, String description, String telephone,
            String place, String frais,Date date, String arriveName, String departName,int id_user) {
        this.region = region;
        this.ville = ville;
        this.depart = depart;
        this.arrivé = arrivé;
        this.description = description;
        this.telephone = telephone;
        this.place = place;
        this.frais = frais;
        this.date = date;
        this.arriveName = arriveName;
        this.departName = departName;
        this.id_user = id_user;
    }
    
    public Transport(String region, String ville, String depart, String arrivé, String description, String telephone,
            String place, String frais,String type,Date date, String arriveName, String departName,int id_user) {
        this.region = region;
        this.ville = ville;
        this.depart = depart;
        this.arrivé = arrivé;
        this.description = description;
        this.telephone = telephone;
        this.place = place;
        this.frais = frais;
        this.type = type;
        this.date = date;
        this.arriveName = arriveName;
        this.departName = departName;
        this.id_user = id_user;
    }

    public Transport() {

    }

    public Transport(String description, String telephone,String place, String frais,String type) {
        this.description = description;
        this.telephone = telephone;
        this.place = place;
        this.frais = frais;
        this.type = type;
    }

    public Transport(int idTransport, String description, String telephone,String place, String frais,String type) {
        this.idTransport = idTransport;
        this.description = description;
        this.telephone = telephone;
        this.place = place;
        this.frais = frais;
        this.type = type;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getId_user() {
        return id_user;
    }

    public void setArriveName(String arriveName) {
        this.arriveName = arriveName;
    }

    public void setDepartName(String departName) {
        this.departName = departName;
    }

    public String getArriveName() {
        return arriveName;
    }

    public String getDepartName() {
        return departName;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }



    
    public String getRegion() {
        return region;
    }

    public String getVille() {
        return ville;
    }

    public String getDepart() {
        return depart;
    }

    public String getArrivé() {
        return arrivé;
    }

    public String getDescription() {
        return description;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getPlace() {
        return place;
    }

    public String getFrais() {
        return frais;
    }

    public String getType() {
        return type;
    }



    public void setRegion(String region) {
        this.region = region;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    public void setArrivé(String arrivé) {
        this.arrivé = arrivé;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public void setFrais(String frais) {
        this.frais = frais;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + this.idTransport;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Transport other = (Transport) obj;
        if (this.idTransport != other.idTransport) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Transport{" + "idTransport=" + idTransport + ", region=" + region + ", ville=" + ville + ", depart=" + depart + ", arriv\u00e9=" + arrivé + ", description=" + description + ", telephone=" + telephone + ", place=" + place + ", frais=" + frais + ", type=" + type + ", date=" + date + ", arriveName=" + arriveName + ", departName=" + departName + ", id_user=" + id_user + '}';
    }

    public int getIdTransport() {
        return idTransport;
    }

    public void setIdTransport(int idTransport) {
        this.idTransport = idTransport;
    }

    

    
    
    
     
    
}
