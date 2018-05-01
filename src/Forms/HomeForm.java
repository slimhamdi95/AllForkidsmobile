/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms;

import Entity.Evenement;
import Services.EvenementService;
import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;


/**
 *
 * @author sana
 */
public class HomeForm {

    Form f;
   
    Button btnajout,btnaff,btnajoutcov,btnaffcov,btnajoutetab,btnaffetab;

    public HomeForm() {
        f = new Form("home");
       
        btnajout = new Button("ajouterEvenet");
        btnaff=new Button("AffichageEvent");
        
        btnajoutetab = new Button("ajouterEtab");
        btnaffetab=new Button("AffichageEtab");
        
        btnajoutcov = new Button("ajouterCovoiturage");
        btnaffcov=new Button("Affichagecovoiturage");
       
        f.add(btnajout);
        f.add(btnaff);
        
        f.add(btnajoutcov);
        f.add(btnaffcov);
        
        f.add(btnajoutetab);
        f.add(btnaffetab);
        
        
        btnajoutetab.addActionListener((e) -> {
           
        });
        
        btnaffetab.addActionListener((e)->{
        AffichageEtablissement a=new AffichageEtablissement();
        a.getForm().show();
        });
        
        
        btnajout.addActionListener((e) -> {
           
        });
        btnaff.addActionListener((e)->{
        AffichageEvenement a=new AffichageEvenement();
        a.getF().show();
        });
        
        btnaffcov.addActionListener((e)->{
        ShowTransport a = new ShowTransport();
        a.getForm().show();
        });
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

  

}