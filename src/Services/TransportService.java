/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;


import Entity.Evenement;
import Entity.Transport;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author DELL
 */
public class TransportService {
    
    public ArrayList<Transport> getTransport(String json) {

        ArrayList<Transport> listTransport = new ArrayList<>();

        try {
            JSONParser j = new JSONParser();
            Map<String, Object> transports = j.parseJSON(new CharArrayReader(json.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) transports.get("root");

            for (Map<String, Object> obj : list) {
                Transport t = new Transport();
                float idTransport = Float.parseFloat(obj.get("idTransport").toString());
                t.setIdTransport((int) idTransport);
                t.setDepart(obj.get("depart").toString());
                t.setArrivé(obj.get("arrive").toString());
                t.setDescription((String) obj.get("description"));
                t.setTelephone(obj.get("telephone").toString());
                t.setPlace(obj.get("place").toString());
                t.setFrais(obj.get("frais").toString());
                t.setType(obj.get("type").toString());
                t.setArriveName(obj.get("arrivename").toString());
                t.setDepartName(obj.get("departname").toString());
                
                float idCreateur = Float.parseFloat(obj.get("idCreateur").toString());
                t.setId_user((int) idCreateur);
                listTransport.add(t);
            }
            
            } catch (IOException ex) {
        }
        return listTransport;

    }
    
        public Transport getTransportEntity(String json) {
            Transport transport = new Transport();
            try {
                JSONParser j = new JSONParser();
                Map<String, Object> transports = j.parseJSON(new CharArrayReader(json.toCharArray()));
                
                float idTransport = Float.parseFloat(transports.get("idTransport").toString());
                transport.setIdTransport((int) idTransport);
                transport.setDepart(transports.get("depart").toString());
                transport.setArrivé(transports.get("arrive").toString());
                transport.setDescription((String) transports.get("description"));
                transport.setTelephone(transports.get("telephone").toString());
                transport.setPlace(transports.get("place").toString());
                transport.setFrais(transports.get("frais").toString());
                transport.setType(transports.get("type").toString());
                transport.setArriveName(transports.get("arrivename").toString());
                transport.setDepartName(transports.get("departname").toString());
                
                float idCreateur = Float.parseFloat(transports.get("idCreateur").toString());
                transport.setId_user((int) idCreateur);
                } catch (IOException ex) {
            }
        return transport;

    }
        
    ArrayList<Transport> listTransport = new ArrayList<>();
        
    public ArrayList<Transport> getTransportList() {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/allforkids/web/app_dev.php/transport/all");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                TransportService service = new TransportService();
                listTransport = service.getTransport(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listTransport;
    }
    
    
    public Transport getTransportDetails(int id){
        Transport Transport = new Transport();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/allforkids/web/app_dev.php/transport/find/"+id);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                TransportService service = new TransportService();
                    Transport.setIdTransport(service.getTransportEntity(new String(con.getResponseData())).getIdTransport());
                    Transport.setDepartName(service.getTransportEntity(new String(con.getResponseData())).getDepartName());
                    Transport.setArriveName(service.getTransportEntity(new String(con.getResponseData())).getArriveName());
                    Transport.setDescription(service.getTransportEntity(new String(con.getResponseData())).getDescription());
                    Transport.setTelephone(service.getTransportEntity(new String(con.getResponseData())).getTelephone());
                    Transport.setPlace(service.getTransportEntity(new String(con.getResponseData())).getPlace());
                    Transport.setFrais(service.getTransportEntity(new String(con.getResponseData())).getFrais());
                    Transport.setType(service.getTransportEntity(new String(con.getResponseData())).getType());
                    Transport.setId_user(service.getTransportEntity(new String(con.getResponseData())).getId_user());
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return  Transport ;
    }
    
    public void addTransport (Transport transport){
        ConnectionRequest con = new ConnectionRequest();
        System.out.println(transport);
        String Url = "http://localhost/Allforkids/web/app_dev.php/transport/newMobile/"
                  +9+ "/" + transport.getDescription()+ "/" 
                   +transport.getTelephone()+ "/"+transport.getPlace()+"/"+transport.getFrais()
                   + "/" +transport.getType();
        con.setUrl(Url);
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
    
    public void joindreTransport(int id,int iduser) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/allforkids/web/app_dev.php/transport/joindreMobile/"+id+"/"+iduser;
        con.setUrl(Url);
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
    
    public void DeleteTransport(int id) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/allforkids/web/app_dev.php/transport/deleteMobile/"+id;
        con.setUrl(Url);
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
    
    ArrayList<Transport> listTransportRejoindre = new ArrayList<>();
    
    public ArrayList<Transport> getTransportRejoindreList(int iduser) {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/allforkids/web/app_dev.php/transport/MyrejoindreMobile/"+iduser);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                TransportService service = new TransportService();
                listTransportRejoindre = service.getTransport(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listTransportRejoindre;
    }

}
