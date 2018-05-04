/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms;

import com.codename1.messaging.Message;

import com.codename1.ui.Button;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import java.io.IOException;



/**
 *
 * @author sana
 */
public class HomeForm {

    Form f;
   


    Button btnajout,btnaff,btnajoutcov,btnaffcov,btnajoutetab,btnaffetab,btnlogin,btnsingin,modifierUser,btnmytransport;

    Button btntof,mail,inscrit;

   // Button btnajout,btnaff,btnajoutcov,btnaffcov,btnajoutetab,btnaffetab;



    public HomeForm() {
        f = new Form("home");
       

        btnajout = new Button("ajouter");
        btntof = new Button("photo");
        btnaff=new Button("Affichage");

        btnajout = new Button("AfficherLivre");
        btnaff=new Button("AffichageEvent");
       
        btnajoutetab = new Button("ajouterEtab");
        btnaffetab=new Button("AffichageEtab");
        
        btnajoutcov = new Button("ajouterCovoiturage");
        btnaffcov=new Button("Affichagecovoiturage");
        btnmytransport=new Button("btnmytransport");

        btnlogin=new Button("Login");
        btnsingin=new Button("SingIn");
        mail=new Button("mail");
        
        modifierUser=new Button("modifierUser");

        f.add(btnajout);
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
        f.add(btnmytransport);
        
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
            AfficherLivre a=new AfficherLivre();
                  a.getF().show();
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
        
        btnaffcov.addActionListener((e)->{
        ShowTransport a = null ;
            try {
                a = new ShowTransport();
            } catch (IOException ex) {
                System.out.println("exception");
            }
        a.getForm().show();
        });
        
        btnmytransport.addActionListener((e)->{
        MyTransport a = null ;
            try {
                a = new MyTransport();
            } catch (IOException ex) {
                System.out.println("exception");
            }
        a.getForm().show();
        });
        
        btnajoutcov.addActionListener((e)->{
        ajoutTransport a = null ;
        a = new ajoutTransport();
        a.getForm().show();
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