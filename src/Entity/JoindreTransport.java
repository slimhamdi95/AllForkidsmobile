/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author DELL
 */
public class JoindreTransport {
    private int idJoindreTransport ;
    private int UserId ;
    private int TransportId ;

    public int getIdJoindreTransport() {
        return idJoindreTransport;
    }

    public void setIdJoindreTransport(int idJoindreTransport) {
        this.idJoindreTransport = idJoindreTransport;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int UserId) {
        this.UserId = UserId;
    }

    public int getTransportId() {
        return TransportId;
    }

    public void setTransportId(int TransportId) {
        this.TransportId = TransportId;
    }

    public JoindreTransport(int idJoindreTransport, int UserId, int TransportId) {
        this.idJoindreTransport = idJoindreTransport;
        this.UserId = UserId;
        this.TransportId = TransportId;
    }

    @Override
    public String toString() {
        return "JoindreTransport{" + "idJoindreTransport=" + idJoindreTransport + ", UserId=" + UserId + ", TransportId=" + TransportId + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + this.idJoindreTransport;
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
        final JoindreTransport other = (JoindreTransport) obj;
        if (this.idJoindreTransport != other.idJoindreTransport) {
            return false;
        }
        return true;
    }
    
}
