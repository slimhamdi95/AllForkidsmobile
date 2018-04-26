/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms;

import Entity.Evenement;
import Services.EvenementService;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Form;
import com.codename1.ui.Label;

/**
 *
 * @author slim
 */
public class AffichageEvenement {
     Form f;
    SpanLabel lb;
    Label nom ;
    public AffichageEvenement() {
        
        f = new Form();
        lb = new SpanLabel("");
        nom = new Label();
        f.add(lb);
        f.add(nom);
        EvenementService serviceevent=new EvenementService();
        for (Evenement e : serviceevent.getList2()) {
            
        }
        lb.setText(serviceevent.getList2().toString());
       
          f.getToolbar().addCommandToRightBar("back", null, (ev)->{HomeForm h=new HomeForm();
          h.getF().show();
          });
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
}
