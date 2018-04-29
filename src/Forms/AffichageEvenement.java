/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms;

import Entity.Evenement;
import Services.EvenementService;
import com.codename1.components.ImageViewer;
import com.codename1.components.MultiButton;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.List;
import com.codename1.ui.URLImage;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import java.io.IOException;

/**
 *
 * @author slim
 */
public class AffichageEvenement {

    Form f;
    SpanLabel lb;
    Label nom;
    Container f1;
    private Resources theme;
    EncodedImage enc;

    public AffichageEvenement() throws IOException {
      
       
        f = new Form("List Evenement");
        f1 = new Container();
        lb = new SpanLabel("");
        nom = new Label("");
        
        f.add(lb);
        f.add(f1);
        EvenementService serviceevent = new EvenementService();
        java.util.List<Evenement> l = serviceevent.getList2();
        System.out.println(l.toString());
        for (Evenement e : serviceevent.getList2()) {
            nom.setText(e.getNom());
 
            MultiButton mb = new MultiButton(e.getNom());
            mb.setTextLine2(e.getDescriptionn());
           
            theme = UIManager.initFirstTheme("/theme");
            enc = EncodedImage.createFromImage(theme.getImage("round.png"), false);
            Image i = URLImage.createToStorage(enc,e.getNom(), "http://localhost/allforkids/web/uploads/images/" + e.getPhoto(), URLImage.RESIZE_SCALE);

            mb.setIcon(i);
            mb.addActionListener((al)->{
            DetailEvenement a = new DetailEvenement(e.getId_evenement());
           
            a.getF().show();
        });
           

  

            f1.add(mb);
            
        }

        f.getToolbar().addCommandToRightBar("back", null, (ev) -> {
            HomeForm h = new HomeForm();
            h.getF().show();
        });
         f.getToolbar().addCommandToRightBar("Ajout", null, (ev) -> {
            AjoutEvenement h = new AjoutEvenement();
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
