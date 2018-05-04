/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms;

import Entity.Livre;
import Services.EvenementService;
import com.codename1.components.SpanLabel;
import com.codename1.googlemaps.MapContainer;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Util;
import com.codename1.ui.Button;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BoxLayout;
import static com.codename1.ui.layouts.BoxLayout.Y_AXIS;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import java.util.Date;

/**
 *
 * @author slim
 */
public class DetailLivre {

    Form f;
    Label nom;
    private Resources theme;
    private EncodedImage largePlaceholder;
    private int id;
    Dimension d = new Dimension(500, 500);
    EvenementService es = new EvenementService();

    Button aime = new Button("j'aime");

    Button detest = new Button("deteste");
    Button lire = new Button("Lire");

    public DetailLivre(Livre l) {
        f = new Form("Livre", new BoxLayout(Y_AXIS));

        nom = new Label(l.getNom(), "SecondaryTitle");
        theme = UIManager.initFirstTheme("/theme");
        nom.setAlignment(CENTER);
        nom.setSize(d);
        f.add(nom);
        largePlaceholder = EncodedImage.createFromImage(theme.getImage("TitleArea.borderCenter_2.png"), false);
        largePlaceholder = largePlaceholder.scaledEncoded(200, 200);
        Date hash = new Date();
        Image i = URLImage.createToStorage(largePlaceholder, System.currentTimeMillis() + hash.toString() + l.getNom(), "http://localhost/allforkids/web/uploads/images/" + l.getPhoto(), URLImage.RESIZE_SCALE_TO_FILL);
        f.add(i);
          f.add(new Label(l.getCategorie() + "  " + l.getType()));
        f.add(new Label("DESCRIPTION :"));
        f.add(new Label(l.getDescription()));
        f.add(lire);
         lire.addActionListener((al)->{
               FileSystemStorage fs = FileSystemStorage.getInstance();
    String fileName = fs.getAppHomePath() + l.getUrl();
    if(!fs.exists(fileName)) {
        Util.downloadUrlToFile("http://localhost/allforkids/web/uploads/images/"+l.getUrl(), fileName, true);
    }
    Display.getInstance().execute(fileName);
       });
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

}
