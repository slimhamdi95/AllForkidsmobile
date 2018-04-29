/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms;

import Entity.Evenement;
import Services.EvenementService;
import com.codename1.components.SpanLabel;
import static com.codename1.io.Log.e;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import static com.codename1.ui.layouts.BorderLayout.NORTH;
//import static com.codename1.ui.layouts.BorderLayout.CENTER;
import com.codename1.ui.layouts.BoxLayout;
import static com.codename1.ui.layouts.BoxLayout.Y_AXIS;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.Layout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import java.io.IOException;


/**
 *
 * @author slim
 */
public class DetailEvenement {
   Form f;
   SpanLabel lb;
   Label nom ;
    private Resources theme;
    private EncodedImage largePlaceholder;
  private  int id ;
    Dimension d = new Dimension(500, 500);
    EvenementService es = new EvenementService();
    public DetailEvenement(int id) {
        this.id = id ;
        f = new Form("Evenement",new BoxLayout(Y_AXIS));
        lb = new SpanLabel("");
        nom =  new Label(es.showDetail(id).getNom(), "SecondaryTitle");
      
          theme = UIManager.initFirstTheme("/theme");
        nom.setAlignment(CENTER);
        nom.setSize(d);
         f.add(nom );
          largePlaceholder = EncodedImage.createFromImage(theme.getImage("TitleArea.borderCenter_2.png"), false);
         largePlaceholder= largePlaceholder.scaledEncoded(200, 200);
          Image i = URLImage.createToStorage(largePlaceholder,"ff1"+es.showDetail(id).getNom(), "http://localhost/allforkids/web/uploads/images/" + es.showDetail(id).getPhoto(), URLImage.RESIZE_SCALE_TO_FILL);
         
       f.add(i);
        f.add(new Label("DESCRIPTION :"));
       f.add(new Label(es.showDetail(id).getDescriptionn()));
       f.getToolbar().addCommandToRightBar("back", null, (ev) -> {
            AffichageEvenement h = null;
            try {
                h = new AffichageEvenement();
            } catch (IOException ex) {
                System.out.println("ereor");
            }
            h.getF().show();
        });
    }
    
     public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
}
