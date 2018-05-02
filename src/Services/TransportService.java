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
    
    ArrayList<Transport> Transport = new ArrayList<>();
    
    public ArrayList<Transport> getTransportDetails(int id){
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/allforkids/web/app_dev.php/transport/find/"+id);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                TransportService service = new TransportService();
                Transport = service.getTransport(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return  Transport ; 
        
    }
    
}
