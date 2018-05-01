/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entity.Etablissement;
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
 * @author FATNASSI
 */
public class EtablissementService {
    
    private ConnectionRequest connectionRequest;
    public static Form listeEtabs;
    private static int userId = 11; //Session.getId();

    public void addEtablissement(Etablissement etab) {
      // Calendar s = new Calendar();
       ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/Allforkids/web/app_dev.php/Etab/new1/"
                  +userId+ "/" + etab.getNom()+ "/" 
                   +etab.getType()+ "/"+etab.getRegion()+"/"+etab.getVille()
                   + "/" +etab.getDescription()+ "/" +etab.getImage()+ "/"+"Non valide";
        con.setUrl(Url);

        //System.out.println("tt");

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }

    public ArrayList<Etablissement> getEtablissement(String json) {

        ArrayList<Etablissement> listEtab = new ArrayList<>();

        try {
            System.out.println(json);
            JSONParser j = new JSONParser();

            Map<String, Object> etabs = j.parseJSON(new CharArrayReader(json.toCharArray()));
            System.out.println(etabs);

            List<Map<String, Object>> list = (List<Map<String, Object>>) etabs.get("root");

            for (Map<String, Object> obj : list) {
                Etablissement e = new Etablissement();

                // System.out.println(obj.get("id"));
                float idEtablissement = Float.parseFloat(obj.get("idEtablissement").toString());
                System.out.println(idEtablissement);
                e.setId_etablissement((int) idEtablissement);
               
                e.setNom(obj.get("nom").toString());
                e.setType(obj.get("type").toString());
                e.setImage(obj.get("image").toString());;
                System.out.println(e);
                listEtab.add(e);

            }
            } catch (IOException ex) {
        }
        System.out.println(listEtab);
        return listEtab;

    }
            ArrayList<Etablissement> listEtablissement = new ArrayList<>();
    
    

    public ArrayList<Etablissement> getList2() {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/Allforkids/web/app_dev.php/Etab/all1");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                EtablissementService ser = new EtablissementService();
                listEtablissement = ser.getEtablissement(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listEtablissement;
    }
    
    public Etablissement afficheDetail(int id ){
        Etablissement e = new Etablissement();
      ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/Allforkids/web/app_dev.php/Etab/find1/"+id);
         con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                EtablissementService ser = new EtablissementService();
                 e.setId_etablissement(ser.getEtablissement2(new String(con.getResponseData())).getId_etablissement());
                 e.setNom( ser.getEtablissement2(new String(con.getResponseData())).getNom());
                 e.setType(ser.getEtablissement2(new String(con.getResponseData())).getType());
                 e.setImage(ser.getEtablissement2(new String(con.getResponseData())).getImage());
                 e.setRegion(ser.getEtablissement2(new String(con.getResponseData())).getRegion());
                 e.setVille(ser.getEtablissement2(new String(con.getResponseData())).getVille());
                 e.setDescription(ser.getEtablissement2(new String(con.getResponseData())).getDescription());
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
      
        return  e ; 
        
    }
    public Etablissement getEtablissement2(String json) {
      Etablissement e = new Etablissement(); 

        try {
            System.out.println(json);
            JSONParser j = new JSONParser();

            Map<String, Object> events = j.parseJSON(new CharArrayReader(json.toCharArray()));
            System.out.println(events);
           
                float idEtablissement = Float.parseFloat(events.get("idEtablissement").toString());
                System.out.println(idEtablissement);
                e.setId_etablissement((int) idEtablissement);
               
                e.setNom(events.get("nom").toString());
                e.setType(events.get("type").toString());
                e.setImage(events.get("image").toString());
                e.setRegion(events.get("region").toString());
                e.setVille(events.get("ville").toString());
                e.setDescription(events.get("description").toString());
                 
            
            } catch (IOException ex) {
        }
       return e ;

    }
}
