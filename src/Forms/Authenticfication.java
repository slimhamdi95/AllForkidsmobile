/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms;

import Entity.Session;
import Entity.UserData;
import com.codename1.facebook.User;
import com.codename1.io.AccessToken;
import com.codename1.io.Preferences;
import com.codename1.social.FacebookConnect;
import com.codename1.social.Login;
import com.codename1.social.LoginCallback;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;

/**
 *
 * @author casa-net
 */
public class Authenticfication {
     private TextField username;
    private TextField password;
    private Button connect;
   
    private Button Creer;
    Form f1;
    
    public  void showAuthentificationForm(Resources theme,UIBuilder ui){
     Container cnt1 = ui.createContainer(theme, "authentification");
         //Form f1 =(Form) cnt1;
         Form f1 = new Form();
         f1.add(username);
         f1.add(password);
         f1.add(connect);
         f1.add(Creer);
         
         
         
         
     
           Creer=(Button) ui.findByName("Creer" , cnt1);
     username=(TextField) ui.findByName("username", cnt1);
     password=(TextField) ui.findByName("password", cnt1);
     connect=(Button) ui.findByName("connect" , cnt1);
   

     
     
     

    
}
private String fullName;
private String uniqueId;
private String imageURL;
String tokenPrefix="facebook";
   void doLogin(Login lg, UserData data, boolean forceLogin) {
    System.out.println("etape 1");
    if(!forceLogin) {
        if(lg.isUserLoggedIn()) {
                                System.out.println("etape 7");
                                Session.facebook=data.getId();
                                Session.photo=data.getImage();
                                Session.facebookName=data.getName();
                                        
           
            return;
        }

        // if the user already logged in previously and we have a token
            System.out.println("etape 2");

        String t = Preferences.get(tokenPrefix + "token", (String)null);    System.out.println(t);
       
        
        if(t != null) { 
                System.out.println("etape 3");

            // we check the expiration of the token which we previously stored as System time
            long tokenExpires = Preferences.get(tokenPrefix + "tokenExpires", (long)-1);
            if(tokenExpires < 0 || tokenExpires > System.currentTimeMillis()) {
                // we are still logged in
                    System.out.println("etape 4");

                System.out.println("etape 7");
                                Session.facebook=data.getId();
                                Session.photo=data.getImage();
                                Session.facebookName=data.getName();
                                        
           
                return;
            }
        }
    }

    lg.setCallback(new LoginCallback() {
        @Override
        public void loginFailed(String errorMessage) {
                                System.out.println("etape 5");

            Dialog.show("Error Logging In", "There was an error logging in: " + errorMessage, "OK", null);
        }

        @Override
        public void loginSuccessful() {
            // when login is successful we fetch the full data
        //    System.out.println(lg.getAccessToken().getToken());
            data.fetchData(lg.getAccessToken().getToken(), ()-> {
                // we store the values of result into local variables
                uniqueId = data.getId();
                fullName = data.getName();
                imageURL = data.getImage();

                // we then store the data into local cached storage so they will be around when we run the app next time
                Preferences.set("fullName", fullName);
                Preferences.set("uniqueId", uniqueId);
                Preferences.set("imageURL", imageURL);
                Preferences.set(tokenPrefix + "token", lg.getAccessToken().getToken());

                // token expiration is in seconds from the current time, we convert it to a System.currentTimeMillis value so we can
                // reference it in the future to check expiration
                Preferences.set(tokenPrefix + "tokenExpires", tokenExpirationInMillis(lg.getAccessToken()));
                System.out.println("etape 7");
                                Session.facebook=data.getId();
                                Session.photo=data.getImage();
                                Session.facebookName=data.getName();
                                    
           
           });
        }

        long tokenExpirationInMillis(AccessToken token) {
    String expires = token.getExpires();
    if(expires != null && expires.length() > 0) {
        try {
            // when it will expire in seconds
            long l = (long)(Float.parseFloat(expires) * 1000);
            return System.currentTimeMillis() + l;
        } catch(NumberFormatException err) {
            // ignore invalid input
        }
    }
    return -1;
}
    });
       System.out.println(lg);
    lg.doLogin();
   
}

    public TextField getUsername() {
        return username;
    }

    public void setUsername(TextField username) {
        this.username = username;
    }

    public TextField getPassword() {
        return password;
    }

    public void setPassword(TextField password) {
        this.password = password;
    }

    public Button getConnect() {
        return connect;
    }

    public void setConnect(Button connect) {
        this.connect = connect;
    }

    public Button getCreer() {
        return Creer;
    }

    public void setCreer(Button Creer) {
        this.Creer = Creer;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getTokenPrefix() {
        return tokenPrefix;
    }

    public void setTokenPrefix(String tokenPrefix) {
        this.tokenPrefix = tokenPrefix;
    }

    public Form getF1() {
        return f1;
    }

    public void setF1(Form f1) {
        this.f1 = f1;
    }
    
}
