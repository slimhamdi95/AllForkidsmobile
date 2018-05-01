/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms;

import Entity.BCrypt;
import Entity.Session;
import Entity.UserE;
import Services.EvenementService;
import Services.LoginService;
import com.codename1.components.MultiButton;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
//import sun.security.util.Password;

/**
 *
 * @author casa-net
 */
public class Login {
    Form f;
    Label lb;
    Label nom ;
    Container f1 ;
    TextField login,pass;
    Button valider , facebook ;
  //  Password pass ;
    
     public Login() {
        
        f = new Form();
        f1 = new Container();
        lb = new Label("login");
        nom = new Label("pass");
        login = new TextField("");
        pass =new TextField("");
        valider = new Button("valider");
          facebook = new Button("facebook");
       // pass = new Password();
       f.add(lb);
        f.add(login);
            f.add(nom);     

        f.add(pass);
        f.add(valider);
        f.add(facebook);
       valider.addActionListener((e) -> {
           LoginService s = new LoginService();
           
           UserE u = new UserE();
           u.setUsername(s.showDetail(login.getText()).getUsername());
           u.setPassword(s.showDetail(login.getText()).getPassword());
           u.setId(s.showDetail(login.getText()).getId());
                      if (BCrypt.checkpw(pass.getText(),u.getPassword())) {
                          Session.setId(s.showDetail(login.getText()).getId());
                          System.out.println("ghvgjj"+u.getId());
                          Session.setUsername(s.showDetail(login.getText()).getUsername());
                      ModifierUser m =new ModifierUser();
                       m.getF().show();
                      }

           System.out.println(u.toString());
        });
       
       facebook.addActionListener((e) -> {
           LoginService s = new LoginService();
        
           UserE u = new UserE();
          s.loginFacebook(f);
        });
     
       
       
      
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
}
