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
   
    Button btnajout,btnaff,btntof;

    public HomeForm() {
        f = new Form("home");
       
        btnajout = new Button("ajouter");
        btntof = new Button("photo");
        btnaff=new Button("Affichage");
       
        f.add(btnajout);
        f.add(btntof);
        f.add(btnaff);
        
         btntof.addActionListener((e) -> {
           
            Authenticfication b = new Authenticfication();
           b.getF1().show();

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
