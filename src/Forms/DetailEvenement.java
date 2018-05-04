/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms;

import Entity.Evenement;
import Entity.Session;
import Services.EvenementService;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.googlemaps.MapContainer;
import com.codename1.maps.Coord;
import com.codename1.ui.Button;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import static com.codename1.ui.layouts.BorderLayout.NORTH;
//import static com.codename1.ui.layouts.BorderLayout.CENTER;
import com.codename1.ui.layouts.BoxLayout;
import static com.codename1.ui.layouts.BoxLayout.Y_AXIS;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.layouts.Layout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import java.io.IOException;
import java.util.Date;



/**
 *
 * @author slim
 */
public class DetailEvenement {

    Form f;
    SpanLabel lb;
    Label nom;
    private Resources theme;
    private EncodedImage largePlaceholder;
    private int id;
    Dimension d = new Dimension(500, 500);
    EvenementService es = new EvenementService();
    MapContainer map;
    Button participe = new Button("participe");
    Button anull = new Button("Anuller participation");
    Button modif = new Button("Modifier");
    Button sup = new Button("Supprimer");

    public DetailEvenement(Evenement evenement) {
       
        f = new Form("Evenement", new BoxLayout(Y_AXIS));
        lb = new SpanLabel("");
        nom = new Label(evenement.getNom(), "SecondaryTitle");
        map = new MapContainer("AIzaSyDeptHcVals3Rfz-bAVNxFzttb7DzhQMv4");
        theme = UIManager.initFirstTheme("/theme");
        nom.setAlignment(CENTER);
        nom.setSize(d);
        f.add(nom);
        largePlaceholder = EncodedImage.createFromImage(theme.getImage("TitleArea.borderCenter_2.png"), false);
        largePlaceholder = largePlaceholder.scaledEncoded(200, 200);
        Date hash = new Date();
        Image i = URLImage.createToStorage(largePlaceholder, System.currentTimeMillis()+hash.toString() + evenement.getNom(), "http://localhost/allforkids/web/uploads/images/" + evenement.getPhoto(), URLImage.RESIZE_SCALE_TO_FILL);
        /**
         * **google Map*********
         */
        System.out.println("lat:"+evenement.getLatitude()+"lng"+evenement.getLongitude());
        Coord crd = new Coord(evenement.getLatitude(), evenement.getLongitude());
      
       
        map.zoom(crd, 12);
 Button btnMoveCamera = new Button("Déplacer Caméra");
        btnMoveCamera.addActionListener(e -> {
            map.setCameraPosition(crd);
        });
          map.setCameraPosition(crd);
        Style s = new Style();
        s.setFgColor(0xff0000);
        s.setBgTransparency(0);
        FontImage markerImg = FontImage.createMaterial(FontImage.MATERIAL_PLACE, s, Display.getInstance().convertToPixels(3));

        map.addMarker(
                EncodedImage.createFromImage(markerImg, false),
                crd,
                "Emplacement",
                "Optional long description",
                evt -> {
                    ToastBar.showMessage("You clicked the marker", FontImage.MATERIAL_PLACE);
                }
        );
Container root = LayeredLayout.encloseIn(
                BorderLayout.center(map),
                BorderLayout.south(
                FlowLayout.encloseBottom(btnMoveCamera)
                )
        );
       
        /**
         * ******************
         */
        f.add(i);
        f.add(new Label("DESCRIPTION :"));
        f.add(new Label(evenement.getDescriptionn()));
        f.add(new Label(evenement.getDate() + "  " + evenement.getTemp()));
        f.add(new Label("Nombre de place disponible :"));
        int nbr = evenement.getNbr_participation() - es.getNbparticipent(evenement.getId_evenement());
        f.add(new Label("" + nbr));
        f.add(root);
        if (evenement.getId_user() ==Session.getId() ) { //==Session.getId() fiiblaset !=1
            f.add(modif);
            f.add(sup);
        } else {
            if ((nbr > 0) && (es.getPparticipent(id, Session.getId()) == 0)) {
                f.add(participe);
            } else if ((nbr > 0) && (es.getPparticipent(id, Session.getId()) == 1)) {
                f.add(anull);
            } else {
                f.add(new Label("Complet"));
            }
        }
       participe.addActionListener((al)->{
          es.addEvenementParticipent(id, Session.getId());
          Dialog dailog = new Dialog("Succes!");
            TextArea popupBody = new TextArea("vous êtes inscrit ", 3, 10);
            popupBody.setUIID("PopupBody");
            popupBody.setEditable(false);
            Button ok = new Button("OK");
             ok.addActionListener((ActionEvent ee) -> {
                 DetailEvenement a = new DetailEvenement(evenement);
           
            a.getF().show();
            });
    
            dailog.setLayout(new BorderLayout());
            dailog.addComponent(BorderLayout.SOUTH, ok);
            dailog.add(BorderLayout.CENTER, popupBody);
            dailog.show();
       
       });
        anull.addActionListener((al)->{
          
        boolean df =  Dialog.show("Suppression","Vous les vous vraiment Annuler participation?","Oui","Non");
          if(df){
            es.anullEvenementParticipent(id, Session.getId());
              DetailEvenement a = new DetailEvenement(evenement);
          
            a.getF().show();
          }
       });
         sup.addActionListener((al)->{
          
        boolean df =  Dialog.show("Suppression","Vous les vous vraiment Supprimer L'evenement ?","Oui","Non");
          if(df){
            es.DeleteEvenement(id);
                 AffichageEvenement a = null;
                try {
                    a = new AffichageEvenement();
                } catch (IOException ex) {
                    System.out.println("erreuur");
                }
                a.getF().show();
          }
       });
            modif.addActionListener((al)->{
             UpdateEvenement a = new UpdateEvenement(evenement);
             a.getF().show();
       });
         
       
        f.getToolbar().addCommandToRightBar("back", null, (ev) -> {
            AffichageEvenement h = null;
            try {
                h = new AffichageEvenement();
            } catch (IOException ex) {
                System.out.println("ereor");
            }
            h.getF().show();
        });
         map.setCameraPosition(crd);
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
