/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms;

import Entity.BCrypt;
import Entity.Evenement;
import Entity.Session;
import Entity.UserE;
import Services.EvenementService;
import Services.LoginService;
import com.codename1.components.MultiButton;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author casa-net
 */
public class Login {

    Form f;
    Dialog dd;
    Label lb;
    Label nom;
    Container f1;
    TextField login, pass;
    Button valider, facebook;

    public Login() {

        f = new Form("LogIn", BoxLayout.y());
        f1 = new Container();
        lb = new Label("login");
        nom = new Label("pass");
        login = new TextField("");
        pass = new TextField("");
        valider = new Button("valider");
        facebook = new Button("facebook");

        f.add(lb);
        f.add(login);
        f.add(nom);
        f.add(pass);
        f.add(valider);
        f.add(facebook);

        valider.addActionListener((ActionEvent e) -> {
            LoginService s = new LoginService();

            UserE u = new UserE();
            u.setUsername(s.showDetail(login.getText()).getUsername());
            u.setPassword(s.showDetail(login.getText()).getPassword());

            u.setId(s.showDetail(login.getText()).getId());
            int id12 = u.getId();
            if (id12 == 0) {
                dd = new Dialog("Erreur");
                TextArea popupBody = new TextArea("User name invalide !", 3, 10);
                popupBody.setUIID("PopupBody");
                popupBody.setEditable(false);
                Button close = new Button("OK");

                close.addActionListener((ActionEvent ee) -> {
                    dd.dispose();
                });
                dd.setLayout(new BorderLayout());
                dd.add(BorderLayout.SOUTH, close);
                dd.add(BorderLayout.CENTER, popupBody);
                dd.show();
            }

            if (BCrypt.checkpw(pass.getText(), u.getPassword())) {
                Session.setId(s.showDetail(login.getText()).getId());
                System.out.println("ghvgjj" + u.getId());
                /*Session.setUsername(s.showDetail(login.getText()).getUsername());
                ModifierUser m = new ModifierUser();
                m.getF().show();*/
                HomeForm h = new HomeForm();
                h.getF().show();
            }else{
               dd = new Dialog("Erreur");
                TextArea popupBody = new TextArea("Mot de passe invalide !", 3, 10);
                popupBody.setUIID("PopupBody");
                popupBody.setEditable(false);
                Button close = new Button("OK");

                close.addActionListener((ActionEvent ee) -> {
                    dd.dispose();
                });
                dd.setLayout(new BorderLayout());
                dd.add(BorderLayout.SOUTH, close);
                dd.add(BorderLayout.CENTER, popupBody);
                dd.show();
            }

        });

        facebook.addActionListener((e) -> {
            UserForm hi = new UserForm();
            hi.show();
        });

    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

}
