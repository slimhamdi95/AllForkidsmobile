/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entity.Evenement;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author slim
 */
public class EvenementService {

    private ConnectionRequest connectionRequest;
    public static Form listeEvents;
    private static int userId = 13; //Session.getId();

    public void addEvenement(Evenement event) {
        connectionRequest = new ConnectionRequest() {
            @Override
            protected void postResponse() {
                Dialog.show("Succes", "Ajout effectué avec succes", "ok", null);
            }
        };
        connectionRequest.setUrl("");
        NetworkManager.getInstance().addToQueue(connectionRequest);
    }

    public ArrayList<Evenement> getEvenement(String json) {

        ArrayList<Evenement> listEvent = new ArrayList<>();

        try {
            System.out.println(json);
            JSONParser j = new JSONParser();

            Map<String, Object> events = j.parseJSON(new CharArrayReader(json.toCharArray()));
            System.out.println(events);

            List<Map<String, Object>> list = (List<Map<String, Object>>) events.get("root");

            for (Map<String, Object> obj : list) {
                Evenement e = new Evenement();

                // System.out.println(obj.get("id"));
                float idEvenement = Float.parseFloat(obj.get("idEvenement").toString());
                System.out.println(idEvenement);
                e.setId_evenement((int) idEvenement);
               
                e.setNom(obj.get("nom").toString());
                e.setDescriptionn(obj.get("descriptionn").toString());
                System.out.println(e);
                listEvent.add(e);

            }
            } catch (IOException ex) {
        }
        System.out.println(listEvent);
        return listEvent;

    }
            ArrayList<Evenement> listEvenement = new ArrayList<>();
    
    

    public ArrayList<Evenement> getList2() {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/allforkids/web/app_dev.php/divertissement/all");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                EvenementService ser = new EvenementService();
                listEvenement = ser.getEvenement(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listEvenement;
    }
}
