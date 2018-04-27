/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms;

import Entity.Etablissement;
import Entity.Evenement;
import Services.EtablissementService;
import Services.EvenementService;
import com.codename1.components.MultiButton;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;

/**
 *
 * @author FATNASSI
 */
public class AffichageEtablissement {
 
    Form form;
    SpanLabel lb;
    Label nom ;
    Container f1 ;
   
    public AffichageEtablissement() {
        
        form = new Form();
        f1 = new Container();
        lb = new SpanLabel("");
        nom = new Label("");
        
        form.add(lb);
        form.add(f1);
        EtablissementService serviceetab=new EtablissementService();
        java.util.List<Etablissement>   l =  serviceetab.getList2() ;
        System.out.println(l.toString());
        for (Etablissement e : serviceetab.getList2()) {
           nom.setText(e.getNom());  
           
            MultiButton mb = new MultiButton(e.getNom());
            mb.setTextLine2(e.getDescription());
          // Image i ;
          /* i.se
           mb.setIcon("C:\wamp64\www\Allforkids\web/*\*//*uploads\images"+e.getPhoto());
            f1.add(mb);*/
        }
       
       
          form.getToolbar().addCommandToRightBar("back", null, (et)->{HomeForm h=new HomeForm();
          h.getF().show();
          });
    }

    public Form getForm() {
        return form;
    }

    public void setForm(Form f) {
        this.form = f;
    }
}
