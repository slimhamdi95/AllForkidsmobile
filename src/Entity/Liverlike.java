/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author slim
 */
public class Liverlike {
     private int id_liver ;
     private int id_user ;
     private int good ;
     private int bad ;

    public Liverlike(int id_liver, int id_user, int good, int bad) {
        this.id_liver = id_liver;
        this.id_user = id_user;
        this.good = good;
        this.bad = bad;
    }

    public Liverlike() {
    }

    public int getId_liver() {
        return id_liver;
    }

    public void setId_liver(int id_liver) {
        this.id_liver = id_liver;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getGood() {
        return good;
    }

    public void setGood(int good) {
        this.good = good;
    }

    public int getBad() {
        return bad;
    }

    public void setBad(int bad) {
        this.bad = bad;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + this.id_liver;
        hash = 53 * hash + this.id_user;
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
        final Liverlike other = (Liverlike) obj;
        if (this.id_liver != other.id_liver) {
            return false;
        }
        if (this.id_user != other.id_user) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Liverlike{" + "id_liver=" + id_liver + ", id_user=" + id_user + ", good=" + good + ", bad=" + bad + '}';
    }
     
}
