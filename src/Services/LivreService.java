/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entity.Livre;
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
 * @author slim
 */
public class LivreService {

    public ArrayList<Livre> getListLivre(String json) {

        ArrayList<Livre> listLivre = new ArrayList<>();

        try {
            System.out.println(json);
            JSONParser j = new JSONParser();

            Map<String, Object> livres = j.parseJSON(new CharArrayReader(json.toCharArray()));
            System.out.println(livres);

            List<Map<String, Object>> list = (List<Map<String, Object>>) livres.get("root");

            for (Map<String, Object> obj : list) {
                Livre e = new Livre();

                // System.out.println(obj.get("id"));
                float id = Float.parseFloat(obj.get("idLivre").toString());
                float good = Float.parseFloat(obj.get("good").toString());
                float bad = Float.parseFloat(obj.get("bad").toString());
                System.out.println(id);
                e.setId_livre((int) id);
                //e.setId(Integer.parseInt(obj.get("id").toString().trim()));
                e.setNom(obj.get("nom").toString());
                e.setDescription(obj.get("description").toString());
                e.setCategorie(obj.get("categorie").toString());
                e.setPhoto(obj.get("categorie").toString());
                e.setType(obj.get("type").toString());
                e.setGood((int) good);
                e.setBad((int) bad);
                e.setUrl(obj.get("url").toString());
                System.out.println(e);
                listLivre.add(e);

            }

        } catch (IOException ex) {
        }

        return listLivre;

    }

    ArrayList<Livre> listLivres = new ArrayList<>();

    public ArrayList<Livre> getList2() {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/allforkids/web/app_dev.php/Liver/Alllivre");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                LivreService ser = new LivreService();
                listLivres = ser.getListLivre(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listLivres;
    }

    public Livre GoodOrBad(int id,int good , int bad) {
        Livre e = new Livre();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/allforkids/web/app_dev.php/Liver/AddGodOrBad/" + id+"/"+good+"/"+bad);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                LivreService ser = new LivreService();
                e.setId_livre(ser.getLivre2(new String(con.getResponseData())).getId_livre());

                e.setNom(ser.getLivre2(new String(con.getResponseData())).getNom());
                e.setDescription(ser.getLivre2(new String(con.getResponseData())).getDescription());
                e.setCategorie(ser.getLivre2(new String(con.getResponseData())).getCategorie());
                e.setPhoto(ser.getLivre2(new String(con.getResponseData())).getPhoto());
                e.setType(ser.getLivre2(new String(con.getResponseData())).getType());
                e.setGood(ser.getLivre2(new String(con.getResponseData())).getGood());
                e.setBad(ser.getLivre2(new String(con.getResponseData())).getBad());
                e.setUrl(ser.getLivre2(new String(con.getResponseData())).getUrl());
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);

        return e;

    }

    public Livre getLivre2(String json) {
        Livre e = new Livre();

        try {
            System.out.println(json);
            JSONParser j = new JSONParser();

            Map<String, Object> obj = j.parseJSON(new CharArrayReader(json.toCharArray()));

            float id = Float.parseFloat(obj.get("idLivre").toString());
            float good = Float.parseFloat(obj.get("good").toString());
            float bad = Float.parseFloat(obj.get("bad").toString());
            System.out.println(id);
            e.setId_livre((int) id);

            e.setNom(obj.get("nom").toString());
            e.setDescription(obj.get("description").toString());
            e.setCategorie(obj.get("categorie").toString());
            e.setPhoto(obj.get("categorie").toString());
            e.setType(obj.get("type").toString());
            e.setGood((int) good);
            e.setBad((int) bad);
            e.setUrl(obj.get("url").toString());

        } catch (IOException ex) {
        }
        return e;

    }
}
