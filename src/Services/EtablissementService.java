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
    private static int userId = 13; //Session.getId();

    public void addEtablissement(Etablissement etab) {
        connectionRequest = new ConnectionRequest() {
            @Override
            protected void postResponse() {
                Dialog.show("Succes", "Ajout effectué avec succes", "ok", null);
            }
        };
        connectionRequest.setUrl("");
        NetworkManager.getInstance().addToQueue(connectionRequest);
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
                e.setDescription(obj.get("descriptionn").toString());
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
        con.setUrl("http://http://localhost/Allforkids/web/app_dev.php/Etab/all1");
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
}
