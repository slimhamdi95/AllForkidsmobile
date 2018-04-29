/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms;

import Entity.Evenement;
import Services.EvenementService;
import com.codename1.components.MultiButton;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.List;

/**
 *
 * @author slim
 */
public class AffichageEvenement {
     Form f;
    SpanLabel lb;
    Label nom ;
    Container f1 ;
   
    public AffichageEvenement() {
        
        f = new Form();
        f1 = new Container();
        lb = new SpanLabel("");
        nom = new Label("");
        
        f.add(lb);
        f.add(f1);
        EvenementService serviceevent=new EvenementService();
        java.util.List<Evenement>   l =  serviceevent.getList2() ;
        System.out.println(l.toString());
        for (Evenement e : serviceevent.getList2()) {
           nom.setText(e.getNom());  
           
            MultiButton mb = new MultiButton(e.getNom());
            mb.setTextLine2(e.getDescriptionn());
           Image i ;
<<<<<<< HEAD
          // i.se
          // mb.setIcon("C:\wamp64\www\Allforkids\web//\//uploads\images"+e.getPhoto());
           // f1.add(mb);
=======
<<<<<<< HEAD
          // i.se
          // mb.setIcon("C:\wamp64\www\Allforkids\web//\//uploads\images"+e.getPhoto());
           // f1.add(mb);
=======
           /*
           i.se
           mb.setIcon("C:\wamp64\www\Allforkids\web\ uploads\images"+e.getPhoto());
            f1.add(mb);
            */
>>>>>>> a4ec22ca8015f371fc0f3b7a468f970ee862791e
>>>>>>> 8c2bf337c6652a86968e2f268a65a331efd45809
        }
       
       
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
