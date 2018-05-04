/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms;

import Entity.Livre;
import Services.LivreService;
import com.codename1.components.MultiButton;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import java.util.Date;

/**
 *
 * @author slim
 */
public class AfficherLivre {
     Form f;
    SpanLabel lb;
    Label nom;
    Container f1;
    private Resources theme;
    EncodedImage enc;

    public AfficherLivre() {
         f = new Form("List Livre");
        f1 = new Container(BoxLayout.y());
        lb = new SpanLabel("");
        nom = new Label("");
        
        f.add(lb);
        f.add(f1);
        LivreService ls = new LivreService();
         java.util.List<Livre> l = ls.getList2();
          for (Livre e : ls.getList2()) {
           nom.setText(e.getNom());
             MultiButton mb = new MultiButton(e.getNom());
              mb.setTextLine2(e.getCategorie()+" "+e.getType());
               theme = UIManager.initFirstTheme("/theme");
            enc = EncodedImage.createFromImage(theme.getImage("round.png"), false);
            Date hash = new Date();
              System.out.println(e.getPhoto());
            Image i = URLImage.createToStorage(enc,System.currentTimeMillis()+hash.toString(), "http://localhost/allforkids/web/uploads/images/" + e.getPhoto(), URLImage.RESIZE_SCALE);
  mb.addActionListener((al)->{
            DetailLivre a = new DetailLivre(e);
           
            a.getF().show();
        });
            mb.setIcon(i);
           f.getToolbar().addCommandToRightBar("back", null, (ev) -> {
            HomeForm h = new HomeForm();
            h.getF().show();    
        });
           f.add(mb);
          }
    }
     public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
    
    
}
