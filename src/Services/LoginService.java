/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entity.UserE;
import Forms.Login;
import com.codename1.components.ScaleImageLabel;
import com.codename1.facebook.FaceBookAccess;
import com.codename1.facebook.User;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.io.Storage;
import com.codename1.social.FacebookConnect;
import com.codename1.social.LoginCallback;
import com.codename1.ui.Button;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BoxLayout;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 *
 * @author casa-net
 */
public class LoginService  {
     public UserE showDetail(String userName){
        UserE e = new UserE();
      ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/allforkids/web/app_dev.php/ent/by/"+userName);
         con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                LoginService ser = new LoginService();
                 e.setId(ser.getUser4(new String(con.getResponseData())).getId());
                  e.setCin(ser.getUser4(new String(con.getResponseData())).getCin());
                   e.setNom(ser.getUser4(new String(con.getResponseData())).getNom());
                     e.setDate(ser.getUser4(new String(con.getResponseData())).getDate());
                    e.setPrenom(ser.getUser4(new String(con.getResponseData())).getPrenom());
                 e.setUsername( ser.getUser4(new String(con.getResponseData())).getUsername());
                   e.setRoles(ser.getUser4(new String(con.getResponseData())).getRoles());
                     e.setEmail(ser.getUser4(new String(con.getResponseData())).getEmail());
              
              
                
                
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
      
        return  e ; 
        
    }
     
     
      public UserE getUser4(String json) {
      UserE e = new UserE();

        try {
            System.out.println(json);
            JSONParser j = new JSONParser();

            Map<String, Object> events = j.parseJSON(new CharArrayReader(json.toCharArray()));
            System.out.println(events);
           
                float id = Float.parseFloat(events.get("id").toString());
                System.out.println(id);
                e.setId((int) id);
               
                e.setUsername(events.get("username").toString());
                e.setPicture(events.get("picture").toString());
                  e.setPrenom(events.get("prenom").toString());
                  e.setDate(events.get("date").toString());
                   e.setNom(events.get("nom").toString());
                   e.setCin(events.get("cin").toString());
                    e.setEmail(events.get("email").toString());
                e.setPassword(events.get("password").toString());
              
                 e.setRoles(events.get("role").toString());
            
            } catch (IOException ex) {
        }
       return e ;

    }
    public ArrayList<UserE> getListUser(String json) {

        ArrayList<UserE> listUsers = new ArrayList<>();

        try {
            System.out.println(json);
            JSONParser j = new JSONParser();

            Map<String, Object> users = j.parseJSON(new CharArrayReader(json.toCharArray()));
            System.out.println(users);
           
            List<Map<String, Object>> list = (List<Map<String, Object>>) users.get("root");

            for (Map<String, Object> obj : list) {
                UserE u = new UserE();

              
             
                //e.setId(Integer.parseInt(obj.get("id").toString().trim()));
                u.setUsername(obj.get("username").toString());
               // e.setNom(obj.get("name").toString());
                System.out.println(u);
                listUsers.add(u);

            }

        } catch (IOException ex) {
        }
        System.out.println(listUsers);
        return listUsers;

    }
    
    
    ArrayList<UserE> listUsers = new ArrayList<>();
    
    public ArrayList<UserE> getList2(){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/allforkids/web/app_dev.php/ent/AllUser");  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                LoginService ser = new LoginService();
                listUsers= ser.getListUser(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listUsers;
    }

      
      
      
      public void Inscrit(UserE user) {
      // Calendar s = new Calendar();
       ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/allforkids/web/app_dev.php/ent/Inscrit/"
                  + user.getUsername() + "/" + user.getNom()
                + "/" +user.getPrenom() + 
                "/" +user.getRoles()+ "/"
                +user.getEmail() +"/"
        +user.getCin()+"/"+user.getPassword()
                   + "/" +user.getDate()+ "/" +user.getPicture();
        con.setUrl(Url);

        //System.out.println("tt");

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
      public void Modifier(UserE user) {
      // Calendar s = new Calendar();
       ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/allforkids/web/app_dev.php/ent/Modifier/"
                 +user.getId()+"/" + user.getUsername() + "/" + user.getNom()
                + "/" +user.getPrenom() + 
                "/" +user.getRoles()+ "/"
                +user.getEmail() +"/"
        +user.getCin()+"/"+user.getPassword()
                   + "/" +user.getDate()+ "/" +user.getPicture();
        con.setUrl(Url);

        //System.out.println("tt");

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
   


  
    
}
