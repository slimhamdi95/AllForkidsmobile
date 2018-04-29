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
import java.io.IOException;



/**
 *
 * @author sana
 */
public class HomeForm {

    Form f;
   
    Button btnajout,btnaff;

    public HomeForm() {
        f = new Form("home");
       
        btnajout = new Button("ajouterEvenet");
        btnaff=new Button("AffichageEvent");
       
        f.add(btnajout);
        f.add(btnaff);
        btnajout.addActionListener((e) -> {
           
            

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
