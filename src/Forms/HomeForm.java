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


/**
 *
 * @author sana
 */
public class HomeForm {

    Form f;
   
<<<<<<< HEAD
    Button btnajout,btnaff,btntof;
=======
    Button btnajout,btnaff,btnajoutcov,btnaffcov,btnajoutetab,btnaffetab;
>>>>>>> f8c88962dc24f77a257418d6f85f6429045072a6

    public HomeForm() {
        f = new Form("home");
       
<<<<<<< HEAD
        btnajout = new Button("ajouter");
        btntof = new Button("photo");
        btnaff=new Button("Affichage");
=======
        btnajout = new Button("ajouterEvenet");
        btnaff=new Button("AffichageEvent");
        
        btnajoutetab = new Button("ajouterEtab");
        btnaffetab=new Button("AffichageEtab");
        
        btnajoutcov = new Button("ajouterCovoiturage");
        btnaffcov=new Button("Affichagecovoiturage");
>>>>>>> f8c88962dc24f77a257418d6f85f6429045072a6
       
        f.add(btnajout);
        f.add(btntof);
        f.add(btnaff);
        
<<<<<<< HEAD
         btntof.addActionListener((e) -> {
           
            Authenticfication b = new Authenticfication();
           b.getF1().show();

=======
        f.add(btnajoutcov);
        f.add(btnaffcov);
        
        f.add(btnajoutetab);
        f.add(btnaffetab);
        
        
        btnajoutetab.addActionListener((e) -> {
           
        });
        
        btnaffetab.addActionListener((e)->{
        AffichageEtablissement a=new AffichageEtablissement();
        a.getForm().show();
>>>>>>> f8c88962dc24f77a257418d6f85f6429045072a6
        });
        
        
        btnajout.addActionListener((e) -> {
           
        });
        btnaff.addActionListener((e)->{
        AffichageEvenement a=new AffichageEvenement();
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
