/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entity.Region;
import Entity.Ville;
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
 * @author FATNASSI
 */
public class VilleService {
    
    
    
     public ArrayList<Ville> getVille(String json) {

        ArrayList<Ville> listVil = new ArrayList<>();

        try {
            System.out.println(json);
            JSONParser j = new JSONParser();

            Map<String, Object> regs = j.parseJSON(new CharArrayReader(json.toCharArray()));
            System.out.println(regs);

            List<Map<String, Object>> list = (List<Map<String, Object>>) regs.get("root");

            for (Map<String, Object> obj : list) {
                Ville r = new Ville();

                // System.out.println(obj.get("id"));
                float idVille = Float.parseFloat(obj.get("idVille").toString());
                System.out.println(idVille);
                r.setId_ville((int) idVille);
               
                r.setNom_ville(obj.get("nomVille").toString());
                System.out.println(r);
                listVil.add(r);

            }
            } catch (IOException ex) {
        }
        System.out.println(listVil);
        return listVil;

    }
    
    ArrayList<Ville> listVille = new ArrayList<>();
    
    public ArrayList<Ville> getList2(int id) {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/Allforkids/web/app_dev.php/Etab/findville/"+id);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                VilleService ser = new VilleService();
                listVille = ser.getVille(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listVille;
    }
}

