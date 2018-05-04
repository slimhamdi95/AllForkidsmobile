/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms;

import Entity.Evenement;
import Entity.Session;
import Services.EvenementService;
import com.codename1.capture.Capture;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.InteractionDialog;
import com.codename1.components.ToastBar;
import com.codename1.googlemaps.MapContainer;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkManager;
import com.codename1.io.Storage;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.maps.Coord;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Rectangle;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.spinner.NumericSpinner;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.ImageIO;
import java.io.IOException;
import java.io.OutputStream;

import java.util.Date;
import static jdk.nashorn.internal.objects.NativeArray.map;

/**
 *
 * @author slim
 */
public class AjoutEvenement {

    double lat= 0,  lng=0;
    TextField tnom;
    TextField tdescription;
    Picker datePicker;
    Picker timePicker;
    String fichernom="";
    Picker stringPicker;
    Button btnajout, btnimage;
    Form f;
    MapContainer map = new MapContainer("AIzaSyDeptHcVals3Rfz-bAVNxFzttb7DzhQMv4");
    Dialog dd  ;
    public AjoutEvenement() {
        f = new Form("home", BoxLayout.y());
        tnom = new TextField("","Nom");
        tdescription = new TextField("","Description");
        datePicker = new Picker();
        btnimage = new Button("Image");

        NumericSpinner nb = new NumericSpinner();
        nb.setMin(1);
        datePicker.setDate(new Date());
        timePicker = new Picker();
        timePicker.setType(Display.PICKER_TYPE_TIME);
        stringPicker = new Picker();
        stringPicker.setType(Display.PICKER_TYPE_STRINGS);
        stringPicker.setStrings("musique", "cinema", "theatre", "randonnée",
                "magicien", "Parck");
        stringPicker.setSelectedString("Parck");
        btnajout = new Button("ajouter");
        /**
         * **google Map*********
         */
         Coord crd = new Coord(36.862499, 10.195556);
       
       
        Button btnMoveCamera = new Button("Déplacer Caméra");
        btnMoveCamera.addActionListener(e -> {
            map.setCameraPosition(new Coord(36.862499, 10.195556));
        });
        Style s = new Style();
        s.setFgColor(0xff0000);
        s.setBgTransparency(0);
        FontImage markerImg = FontImage.createMaterial(FontImage.MATERIAL_PLACE, s, Display.getInstance().convertToPixels(3));
 map.setCameraPosition(crd);
        map.addTapListener(e -> {
            map.clearMapLayers();

            Coord c = map.getCoordAtPosition(e.getX(), e.getY());
            lat = c.getLatitude();
            lng = c.getLongitude();
            
            map.addMarker(
                    EncodedImage.createFromImage(markerImg, false),
                    map.getCoordAtPosition(e.getX(), e.getY()),
                    "Hi marker",
                    "Optional long description",
                    evt -> {

                        ToastBar.showMessage("Marker", FontImage.MATERIAL_PLACE);
                    }
            );

        });

        Container root = LayeredLayout.encloseIn(
                BorderLayout.center(map),
                BorderLayout.south(
                        FlowLayout.encloseBottom(btnMoveCamera)
                )
        );
        /**
         * ******************
         */
        /**
         * ****Uplode Image *************
         */
        btnimage.addActionListener((ActionEvent e) -> {
            Display.getInstance().openImageGallery(new ActionListener() {
                public void actionPerformed(ActionEvent ev) {
                    if (ev != null && ev.getSource() != null) {
                        String filePath = (String) ev.getSource();
                       
                        try {

                            MultipartRequest cr = new MultipartRequest();
                            cr.setUrl("http://localhost/allforkids/web/imageUpload.php");
                            cr.setPost(true);
                            String mime = "image/png";
                            cr.addData("file", filePath, mime);
                            Date hash = new Date();
                            fichernom = System.currentTimeMillis()+ ".png";
                            cr.setFilename("file", fichernom);

                            InfiniteProgress prog = new InfiniteProgress();
                            Dialog dlg = prog.showInifiniteBlocking();
                            cr.setDisposeOnCompletion(dlg);
                            NetworkManager.getInstance().addToQueueAndWait(cr);
                        } catch (IOException ex) {
                        }

                    }
                }
            });

        });
        /**
         * **********
         */
       
        f.add(tnom);
        f.add(tdescription);
        f.add(nb);
        f.add(datePicker);
        f.add(timePicker);
        f.add(stringPicker);
        f.add(btnimage);
        f.add(root);
        f.add(btnajout);
         map.setCameraPosition(crd);
 map.zoom(crd, 8);
        btnajout.addActionListener((ActionEvent e) -> {
             EvenementService ser = new EvenementService();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd ");

            String strDate = sdf.format(datePicker.getDate());
            //String strtime = stf.format(timePicker.getText());
            System.out.println(timePicker.getText());

            Evenement ev = new Evenement(0, tnom.getText(), tdescription.getText(), strDate, stringPicker.getValue().toString(), (int) nb.getValue(), false, Session.getId(), fichernom, lat, lng, timePicker.getText().toString());
           
            if(validation( ev)){
            ser.addEvenement(ev);
            Dialog dailog = new Dialog("Succes!");
            TextArea popupBody = new TextArea("Evenement Ajouter avec succes", 3, 10);
            popupBody.setUIID("PopupBody");
            popupBody.setEditable(false);
            Button ok = new Button("OK");
           
            ok.addActionListener((ActionEvent ee) -> {
                AffichageEvenement a = null;
                try {
                    a = new AffichageEvenement();
                } catch (IOException ex) {
                    System.out.println("erreuur");
                }
                a.getF().show();
            });
            dailog.setLayout(new BorderLayout());
            dailog.addComponent(BorderLayout.SOUTH, ok);
            dailog.add(BorderLayout.CENTER, popupBody);
            dailog.show();
            }
           
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
public boolean validation(Evenement e){
    boolean a = false ;
    EvenementService es = new EvenementService();
    if(!e.getNom().equals("")){
    for (Evenement ev : es.getList2()) {
        if(e.getNom().equals(ev.getNom())){
         
             dd = new Dialog("Erreur");
            TextArea popupBody = new TextArea("Le Nom existe deja !", 3, 10);
            popupBody.setUIID("PopupBody");
            popupBody.setEditable(false);
            Button close = new Button("OK");
          
            close.addActionListener((ActionEvent ee) -> {
                dd.dispose();
            });
            dd.setLayout(new BorderLayout());
              dd.add( BorderLayout.SOUTH,close);
            dd.add(BorderLayout.CENTER, popupBody);
            dd.show();
              return false ;
        }
    }
    
    }else {
              dd = new Dialog("Erreur");
            TextArea popupBody = new TextArea("Nom ne peut pas etre null !", 3, 10);
            popupBody.setUIID("PopupBody");
            popupBody.setEditable(false);
            Button close = new Button("OK");
          
            close.addActionListener((ActionEvent ee) -> {
                dd.dispose();
            });
            dd.setLayout(new BorderLayout());
            dd.add( BorderLayout.SOUTH,close);
            dd.add(BorderLayout.CENTER, popupBody);
            dd.show();
return false ;
            }
    if(e.getDescriptionn().equals("") ){
         dd = new Dialog("Erreur");
            TextArea popupBody = new TextArea("Description ne peut pas etre null !", 3, 10);
            popupBody.setUIID("PopupBody");
            popupBody.setEditable(false);
            Button close = new Button("OK");
          
            close.addActionListener((ActionEvent ee) -> {
                dd.dispose();
            });
            dd.setLayout(new BorderLayout());
            dd.add( BorderLayout.SOUTH,close);
            dd.add(BorderLayout.CENTER, popupBody);
            dd.show();
            return false ;
    }
    if(fichernom.equals("")){
      dd = new Dialog("Erreur");
            TextArea popupBody = new TextArea("vous devez ajouter une image !", 3, 10);
            popupBody.setUIID("PopupBody");
            popupBody.setEditable(false);
            Button close = new Button("OK");
          
            close.addActionListener((ActionEvent ee) -> {
                dd.dispose();
            });
            dd.setLayout(new BorderLayout());
            dd.add( BorderLayout.SOUTH,close);
            dd.add(BorderLayout.CENTER, popupBody);
            dd.show();
            return false ;
    }
    if(lat==0 && lng==0){
        dd = new Dialog("Erreur");
            TextArea popupBody = new TextArea("vous devez ajouter l'emplacement !", 3, 10);
            popupBody.setUIID("PopupBody");
            popupBody.setEditable(false);
            Button close = new Button("OK");
          
            close.addActionListener((ActionEvent ee) -> {
                dd.dispose();
            });
            dd.setLayout(new BorderLayout());
            dd.add( BorderLayout.SOUTH,close);
            dd.add(BorderLayout.CENTER, popupBody);
            dd.show();
            return false ;
    }

return true ;
} 
}
