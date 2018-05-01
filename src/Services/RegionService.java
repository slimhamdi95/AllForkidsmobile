/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entity.Etablissement;
import Entity.Region;
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
public class RegionService {
    
     public ArrayList<Region> getRegion(String json) {

        ArrayList<Region> listReg = new ArrayList<>();

        try {
            System.out.println(json);
            JSONParser j = new JSONParser();

            Map<String, Object> regs = j.parseJSON(new CharArrayReader(json.toCharArray()));
            System.out.println(regs);

            List<Map<String, Object>> list = (List<Map<String, Object>>) regs.get("root");

            for (Map<String, Object> obj : list) {
                Region r = new Region();

                // System.out.println(obj.get("id"));
                float idRegion = Float.parseFloat(obj.get("idRegion").toString());
                System.out.println(idRegion);
                r.setId_region((int) idRegion);
               
                r.setNom_region(obj.get("nomRegion").toString());
                System.out.println(r);
                listReg.add(r);

            }
            } catch (IOException ex) {
        }
        System.out.println(listReg);
        return listReg;

    }
     
            ArrayList<Region> listRegion = new ArrayList<>();
    
    public ArrayList<Region> getList2() {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/Allforkids/web/app_dev.php/Etab/allregion");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                RegionService ser = new RegionService();
                listRegion = ser.getRegion(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listRegion;
    }
public Region showDetail(String nom ){
       // Region r2 = null ;
        Region r = new Region();
      ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/Allforkids/web/app_dev.php/Etab/findidregion/"+nom);
         con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                RegionService ser = new RegionService();
              //   r2 = new Region(ser.getRegion2(new String(con.getResponseData())).getId_region(),
              //  ser.getRegion2(new String(con.getResponseData())).getNom_region());
                 r.setId_region(ser.getRegion2(new String(con.getResponseData())).getId_region());
             // a=r.getId_region();
                 r.setNom_region(ser.getRegion2(new String(con.getResponseData())).getNom_region());
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
      System.out.println("bbbbbbbbbbbbbbbb"+r.getId_region());
        return  r ; 
        
    }

     public Region getRegion2(String json) {
      Region r = new Region(); 

        try {
            System.out.println(json);
            JSONParser j = new JSONParser();

            Map<String, Object> events = j.parseJSON(new CharArrayReader(json.toCharArray()));
            System.out.println(events); 
                float idRegion = Float.parseFloat(events.get("idRegion").toString());
                System.out.println(idRegion);
                r.setId_region((int) idRegion);
               
                r.setNom_region(events.get("nomRegion").toString());
                 
            
            } catch (IOException ex) {
        }
       return r ;

    }
}

