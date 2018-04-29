/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entity.User;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.Map;

/**
 *
 * @author casa-net
 */
public class LoginService {
     public User showDetail(String userName){
        User e = new User();
      ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/allforkids/web/app_dev.php/ent/by/"+userName);
         con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                LoginService ser = new LoginService();
                 e.setId(ser.getUser4(new String(con.getResponseData())).getId());
                 e.setUsername( ser.getUser4(new String(con.getResponseData())).getUsername());
                 e.setPassword(ser.getUser4(new String(con.getResponseData())).getPassword());
                
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
      
        return  e ; 
        
    }
     
     
      public User getUser4(String json) {
      User e = new User();

        try {
            System.out.println(json);
            JSONParser j = new JSONParser();

            Map<String, Object> events = j.parseJSON(new CharArrayReader(json.toCharArray()));
            System.out.println(events);
           
                float id = Float.parseFloat(events.get("id").toString());
                System.out.println(id);
                e.setId((int) id);
               
                e.setUsername(events.get("username").toString());
                e.setPassword(events.get("password").toString());
              
                 
            
            } catch (IOException ex) {
        }
       return e ;

    }
}
