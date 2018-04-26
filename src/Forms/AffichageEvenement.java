/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms;

import Services.EvenementService;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Form;

/**
 *
 * @author slim
 */
public class AffichageEvenement {
     Form f;
    SpanLabel lb;
  
    public AffichageEvenement() {
        
        f = new Form();
        lb = new SpanLabel("");
        f.add(lb);
        EvenementService serviceevent=new EvenementService();
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
