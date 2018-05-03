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
                    e.setPrenom(ser.getUser4(new String(con.getResponseData())).getPrenom());
                 e.setUsername( ser.getUser4(new String(con.getResponseData())).getUsername());
                 e.setPassword(ser.getUser4(new String(con.getResponseData())).getPassword());
              
                
                
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
                   e.setNom(events.get("nom").toString());
                   e.setCin(events.get("cin").toString());
                e.setPassword(events.get("password").toString());
              
                 
            
            } catch (IOException ex) {
        }
       return e ;

    }
      
      
      
      public void Inscrit(UserE user) {
      // Calendar s = new Calendar();
       ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/allforkids/web/app_dev.php/ent/Inscrit/"
                  + user.getNom() + "/" + user.getCin()
                + "/" 
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
   


  
    
}
