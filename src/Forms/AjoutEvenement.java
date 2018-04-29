/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms;

import Entity.Evenement;
import Services.EvenementService;
import com.codename1.capture.Capture;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.ToastBar;
import com.codename1.googlemaps.MapContainer;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkManager;
import com.codename1.io.Storage;
import com.codename1.maps.Coord;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.spinner.NumericSpinner;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.ImageIO;
import java.io.IOException;
import java.io.OutputStream;

import java.util.Date;

/**
 *
 * @author slim
 */
public class AjoutEvenement {

    TextField tnom;
    TextField tdescription;
    Picker datePicker;
    Picker timePicker;
    String fichernom;
    Picker stringPicker;
    Button btnajout, btnimage;
    Form f;
    MapContainer map = new MapContainer("AIzaSyDeptHcVals3Rfz-bAVNxFzttb7DzhQMv4");

    public AjoutEvenement() {
        f = new Form("home", BoxLayout.y());
        tnom = new TextField();
        tdescription = new TextField();
        datePicker = new Picker();
        btnimage = new Button("Image");

        NumericSpinner nb = new NumericSpinner();
        nb.setMin(1);
        datePicker.setDate(new Date());
        timePicker = new Picker();
        timePicker.setType(Display.PICKER_TYPE_TIME);
        stringPicker = new Picker();
        stringPicker.setType(Display.PICKER_TYPE_STRINGS);
        stringPicker.setStrings("A Game of Thrones", "A Clash Of Kings", "A Storm Of Swords", "A Feast For Crows",
                "A Dance With Dragons", "The Winds of Winter", "A Dream of Spring");
        stringPicker.setSelectedString("A Game of Thrones");
        btnajout = new Button("ajouter");
        /****google Map**********/
         Button btnMoveCamera = new Button("Move Camera");
         btnMoveCamera.addActionListener(e->{
            map.setCameraPosition(new Coord(-33.867, 151.206));
        });
        Style s = new Style();
        s.setFgColor(0xff0000);
        s.setBgTransparency(0);
        FontImage markerImg = FontImage.createMaterial(FontImage.MATERIAL_PLACE, s, Display.getInstance().convertToPixels(3));

        Button btnAddMarker = new Button("Add Marker");
        btnAddMarker.addActionListener(e->{

            map.setCameraPosition(new Coord(41.889, -87.622));
            map.addMarker(
                    EncodedImage.createFromImage(markerImg, false),
                    map.getCameraPosition(),
                    "Hi marker",
                    "Optional long description",
                     evt -> {
                             ToastBar.showMessage("You clicked the marker", FontImage.MATERIAL_PLACE);
                     }
            );

        });
        f.add(btnAddMarker);

        /*********************/
        /**
         * ****Uplode Image *************
         */
        btnimage.addActionListener((ActionEvent e) -> {
            Display.getInstance().openImageGallery(new ActionListener() {
                public void actionPerformed(ActionEvent ev) {
                    if (ev != null && ev.getSource() != null) {
                        String filePath = (String) ev.getSource();
                        f.add(filePath);
                        try {

                            MultipartRequest cr = new MultipartRequest();
                            cr.setUrl("http://localhost/allforkids/web/imageUpload.php");
                            cr.setPost(true);
                            String mime = "image/png";
                            cr.addData("file", filePath, mime);
                            fichernom = System.currentTimeMillis() + ".png";
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
        f.add(map);
        f.add(btnajout);

        btnajout.addActionListener((ActionEvent e) -> {
            EvenementService ser = new EvenementService();
            Evenement ev = new Evenement(0, tnom.getText(), tdescription.getText());
            ser.addEvenement(ev);
            Dialog d = new Dialog("Succes!");
            TextArea popupBody = new TextArea("Evenement Ajouter avec succes", 3, 10);
            popupBody.setUIID("PopupBody");
            popupBody.setEditable(false);
            d.setLayout(new BorderLayout());
            d.add(BorderLayout.CENTER, popupBody);
            d.show();
            d.setTimeout(2000);
            AffichageEvenement a = null;
            try {
                a = new AffichageEvenement();
            } catch (IOException ex) {
                System.out.println("erreuur");
            }
            a.getF().show();
        });
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

}
