/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms;

import Entity.Evenement;
import Services.EvenementService;
import com.codename1.capture.Capture;
import com.codename1.messaging.Message;
import com.codename1.ui.Button;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import java.io.IOException;



/**
 *
 * @author sana
 */
public class HomeForm {

    Form f;
   

    Button btnajout,btnaff,btnajoutcov,btnaffcov,btnajoutetab,btnaffetab,btnlogin,btnsingin,modifierUser;

    Button btntof,mail;

   // Button btnajout,btnaff,btnajoutcov,btnaffcov,btnajoutetab,btnaffetab;


    public HomeForm() {
        f = new Form("home");
       
        btnajout = new Button("ajouter");
        btntof = new Button("photo");
        btnaff=new Button("Affichage");
        btnajout = new Button("ajouterEvenet");
        btnaff=new Button("AffichageEvent");
       
        btnajoutetab = new Button("ajouterEtab");
        btnaffetab=new Button("AffichageEtab");
        
        btnajoutcov = new Button("ajouterCovoiturage");
        btnaffcov=new Button("Affichagecovoiturage");
        btnlogin=new Button("Login");
        btnsingin=new Button("SingIn");
        mail=new Button("mail");
        modifierUser=new Button("modifierUser");
        f.add(btnajout);
        f.add(btntof);
        f.add(btnaff);
        f.add(btnlogin);
        f.add(btnsingin);
f.add(mail);
f.add(modifierUser);
         mail.addActionListener((e) -> {
         

               Message m = new Message("<html><body>Check out <a href=\"https://www.codenameone.com/\">Codename One</a></body></html>");
             m.setMimeType(Message.MIME_HTML);
Display.getInstance().sendMessage(new String[] {"salemen.fatnassi@gmail.com"}, "Subject of message", m);

// notice that we provide a plain text alternative as well in the send method

        });
         
          modifierUser.addActionListener((e) -> {
         
ModifierUser i = new ModifierUser();
            i.getF().show();

        });
        f.add(btnajoutcov);
        f.add(btnaffcov);
        
        f.add(btnajoutetab);
        f.add(btnaffetab);
        
        
        btnajoutetab.addActionListener((e) -> {
           AjouterEtablissement se= new AjouterEtablissement();
           se.getF().show();
        });
        
        btnaffetab.addActionListener((e)->{
        AffichageEtablissement a=new AffichageEtablissement();
        a.getForm().show();
        });
        
        
        btnajout.addActionListener((e) -> {
           
        });
        
        
        btnlogin.addActionListener((e) -> {
              Login a=new Login();
                  a.getF().show();
        });
        btnaff.addActionListener((e)->{
        AffichageEvenement a = null ;
            try {
                a = new AffichageEvenement();
            } catch (IOException ex) {
                System.out.println("exception");
            }
        a.getF().show();
        });
        btnsingin.addActionListener((e) -> {
            Inscrit i = new Inscrit();
            i.getF().show();
        });
        
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

  

}
