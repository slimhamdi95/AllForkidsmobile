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
import com.codename1.ui.Component;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;


/**
 *
 * @author slim
 */
public class EvenementService {

    private ConnectionRequest connectionRequest;
  
    private static int userId = 13; //Session.getId();

    public void addEvenement(Evenement event) {
      // Calendar s = new Calendar();
       ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/allforkids/web/app_dev.php/divertissement/new/"
                  + event.getNom() + "/" + event.getDescriptionn()+ "/" 
                   +"2018-03-11" + "/" +50+ "/" +"bbf"+ "/" +  "00:00:00"+"/"+"vssv"
                   + "/" +12+ "/" +1.256+ "/" +58.23;
        con.setUrl(Url);

        //System.out.println("tt");

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
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
                e.setPhoto(obj.get("photo").toString());
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
    public Evenement showDetail(int id ){
        Evenement e = new Evenement();
      ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/allforkids/web/app_dev.php/divertissement/showone/"+id);
         con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                EvenementService ser = new EvenementService();
                 e.setId_evenement(ser.getEvenement2(new String(con.getResponseData())).getId_evenement());
                 e.setNom( ser.getEvenement2(new String(con.getResponseData())).getNom());
                 e.setDescriptionn(ser.getEvenement2(new String(con.getResponseData())).getDescriptionn());
                 e.setPhoto(ser.getEvenement2(new String(con.getResponseData())).getPhoto());
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
      
        return  e ; 
        
    }
    ////oumou zebi lena  ///////
     public Evenement getEvenement2(String json) {
      Evenement e = new Evenement();

        try {
            System.out.println(json);
            JSONParser j = new JSONParser();

            Map<String, Object> events = j.parseJSON(new CharArrayReader(json.toCharArray()));
            System.out.println(events);
           
                float idEvenement = Float.parseFloat(events.get("idEvenement").toString());
                System.out.println(idEvenement);
                e.setId_evenement((int) idEvenement);
               
                e.setNom(events.get("nom").toString());
                e.setDescriptionn(events.get("descriptionn").toString());
                e.setPhoto(events.get("photo").toString());
                 
            
            } catch (IOException ex) {
        }
       return e ;

    }
}
