/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms;

import Entity.Evenement;
import Services.EvenementService;
import com.codename1.capture.Capture;
import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import java.io.IOException;



/**
 *
 * @author sana
 */
public class HomeForm {

    Form f;
   

    Button btnajout,btnaff,btnajoutcov,btnaffcov,btnajoutetab,btnaffetab,btnlogin;

    Button btntof;

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
       
        f.add(btnajout);
        f.add(btntof);
        f.add(btnaff);
        f.add(btnlogin);

         btntof.addActionListener((e) -> {
           
            
            
          

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
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

  

}
