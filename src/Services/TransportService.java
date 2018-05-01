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
            System.out.println(json);
            JSONParser j = new JSONParser();
            Map<String, Object> transports = j.parseJSON(new CharArrayReader(json.toCharArray()));
            System.out.println(transports);
            List<Map<String, Object>> list = (List<Map<String, Object>>) transports.get("root");
            
            for (Map<String, Object> obj : list) {
                /*Transport t = new Transport();

                Transport t = new Transport();
                // System.out.println(obj.get("id"));
                float idTransport = Float.parseFloat(obj.get("idTransport").toString());
                System.out.println(idTransport);
                t.setIdTransport((int) idTransport);
                t.setPlace(obj.get("place").toString());
                t.setTelephone(obj.get("telephone").toString());
                System.out.println(t);
                listTransport.add(t);
*/
            }
            } catch (IOException ex) {
        }
        System.out.println(listTransport);
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
    
}
