/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms;

import Entity.BCrypt;
import Entity.User;
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
    Button valider ;
  //  Password pass ;
    
     public Login() {
        
        f = new Form();
        f1 = new Container();
        lb = new Label("login");
        nom = new Label("pass");
        login = new TextField("");
        pass =new TextField("");
        valider = new Button("valider");
       // pass = new Password();
       f.add(lb);
        f.add(login);
            f.add(nom);     

        f.add(pass);
        f.add(valider);
        
       valider.addActionListener((e) -> {
           LoginService s = new LoginService();
           
           User u = new User();
           u.setUsername(s.showDetail(login.getText()).getUsername());
           u.setPassword(s.showDetail(login.getText()).getPassword());
                      if (BCrypt.checkpw(pass.getText(),u.getPassword())) {
                       HomeForm h = new HomeForm();
                       h.getF().show();
                      }

           System.out.println(u.toString());
        });
     
       
       
      
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
}
