/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms;

import Entity.Evenement;
import Entity.Session;
import Services.EvenementService;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.googlemaps.MapContainer;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.maps.Coord;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.spinner.NumericSpinner;
import com.codename1.ui.spinner.Picker;
import java.io.IOException;
import java.util.Date;
import java.util.Random;


/**
 *
 * @author slim
 */
public class UpdateEvenement {

    Form f;
    double lat = 0, lng = 0;
    TextField tnom;
    String nomvalidation ;
    TextField tdescription;
    Picker datePicker;
    Picker timePicker;
    String fichernom = "";
    Picker stringPicker;
    Button btnajout, btnimage;
    MapContainer map = new MapContainer("AIzaSyDeptHcVals3Rfz-bAVNxFzttb7DzhQMv4");
    Dialog dd;
    EvenementService es = new EvenementService();

    public UpdateEvenement(Evenement ev) {
        lat= ev.getLatitude();
        lng=ev.getLatitude();
        nomvalidation = ev.getNom();
        fichernom = ev.getPhoto();
        f = new Form("Modifier " + ev.getNom(), BoxLayout.y());
        f = new Form("home", BoxLayout.y());
        tnom = new TextField(ev.getNom(), "Nom");
        tdescription = new TextField(ev.getDescriptionn(), "Description");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = sdf.parse(ev.getDate());
        } catch (ParseException ex) {
            System.out.println("erreur date paress");
        }
        datePicker = new Picker();
        datePicker.setDate(date);
        btnimage = new Button("Image");

        NumericSpinner nb = new NumericSpinner();
        nb.setMin(es.getNbparticipent(ev.getId_evenement()));
        nb.setValue(ev.getNbr_participation());
        timePicker = new Picker();
        timePicker.setType(Display.PICKER_TYPE_TIME);
        timePicker.setText(ev.getTemp());
        stringPicker = new Picker();
        stringPicker.setType(Display.PICKER_TYPE_STRINGS);
        stringPicker.setStrings("musique", "cinema", "theatre", "randonnée",
                "magicien", "Parck");
      
        btnajout = new Button("Modifier");
        /**
         * **google Map*********
         */
        Coord crd = new Coord(lat, lng);
      
        
        map.zoom(crd, 5);
        Button btnMoveCamera = new Button("Déplacer Caméra");
        btnMoveCamera.addActionListener(e -> {
            map.setCameraPosition(new Coord(36.862499, 10.195556));
        });
        Style s = new Style();
        s.setFgColor(0xff0000);
        s.setBgTransparency(0);
        FontImage markerImg = FontImage.createMaterial(FontImage.MATERIAL_PLACE, s, Display.getInstance().convertToPixels(3));
          map.setCameraPosition(crd);
        map.addMarker(
                EncodedImage.createFromImage(markerImg, false),
                new Coord(lat, lng),
                "Emplacement",
                "Optional long description",
                evt -> {
                    ToastBar.showMessage("You clicked the marker", FontImage.MATERIAL_PLACE);
                }
        );
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
        System.out.println(ev.getType());
          stringPicker.setSelectedString(ev.getType());
         btnajout.addActionListener((ActionEvent e) -> {
             EvenementService ser = new EvenementService();
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");

            String strDate = sdf1.format(datePicker.getDate());
            //String strtime = stf.format(timePicker.getText());
            System.out.println(timePicker.getText());

            Evenement ev1 = new Evenement(ev.getId_evenement(), tnom.getText(), tdescription.getText(), strDate, stringPicker.getValue().toString()
                                        , (int) nb.getValue(), false, Session.getId(), fichernom, lat, lng, timePicker.getText().toString());
           
            if(validation( ev1)){
            ser.UpdateEvenement(ev1);
            Dialog d = new Dialog("Succes!");
            TextArea popupBody = new TextArea("Evenement Modifier avec succes", 3, 10);
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
            d.setLayout(new BorderLayout());
            d.addComponent(BorderLayout.SOUTH, ok);
            d.add(BorderLayout.CENTER, popupBody);
            d.show();
            }
           
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
        if((e.getNom().equals(ev.getNom()))&&(!(e.getNom().equals(nomvalidation)))){
         
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
