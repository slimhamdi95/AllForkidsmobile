/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms;

import Services.EtablissementService;
import Services.EvenementService;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BoxLayout;
import static com.codename1.ui.layouts.BoxLayout.Y_AXIS;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import java.io.IOException;

/**
 *
 * @author FATNASSI
 */
public class DetailEtablissement {
    Form f;
   SpanLabel lb;
   Label nom ;
   Button btndelete ;
    private Resources theme;
    private EncodedImage largePlaceholder;
  private  int id ;
    Dimension d = new Dimension(500, 500);
    EtablissementService es = new EtablissementService();
    public DetailEtablissement(int id) {
        this.id = id ;
        f = new Form("Etablissement",new BoxLayout(Y_AXIS));
        lb = new SpanLabel("");
        nom =  new Label(es.afficheDetail(id).getNom(), "SecondaryTitle");
      
          theme = UIManager.initFirstTheme("/theme");
        nom.setAlignment(CENTER);
        nom.setSize(d);
         f.add(nom );
          largePlaceholder = EncodedImage.createFromImage(theme.getImage("TitleArea.borderCenter_2.png"), false);
         largePlaceholder= largePlaceholder.scaledEncoded(200, 200);
          Image i = URLImage.createToStorage(largePlaceholder,"ff1"+es.afficheDetail(id).getNom(), "http://localhost/allforkids/web/uploads/images/" + es.afficheDetail(id).getImage(), URLImage.RESIZE_SCALE_TO_FILL);
         
       f.add(i);
        f.add(new Label("Type :"));
       f.add(new Label(es.afficheDetail(id).getType()));
        f.add(new Label("Region :"));
       f.add(new Label(es.afficheDetail(id).getRegion()));
        f.add(new Label("Ville :"));
       f.add(new Label(es.afficheDetail(id).getVille()));
        f.add(new Label("Description :"));
       f.add(new Label(es.afficheDetail(id).getDescription()));
       
       f.getToolbar().addCommandToRightBar("back", null, (ev) -> {
            AffichageEtablissement h = null;
            
                h = new AffichageEtablissement();
                
                System.out.println("erreur");
            h.getForm().show();
        });
       
      
        btndelete = new Button("Effacer"); 
        btndelete.addActionListener((ActionEvent e) -> {
        
            EtablissementService ser = new EtablissementService();
            ser.deleteEtablissement(id);
        
        });
        f.add(btndelete);
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
