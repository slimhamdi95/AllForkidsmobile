/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms;

import Entity.Etablissement;
import Entity.Evenement;
import Entity.Region;
import Entity.Ville;
import Services.EtablissementService;
import Services.EvenementService;
import Services.RegionService;
import Services.VilleService;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.ToastBar;
import com.codename1.googlemaps.MapContainer;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkManager;
import com.codename1.maps.Coord;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
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
import com.codename1.ui.plaf.Style;
import com.codename1.ui.spinner.NumericSpinner;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author FATNASSI
 */
public class AjouterEtablissement {
    
    TextField tnom;
    ComboBox<String> type;
    ComboBox<String> region;
    ComboBox<String> ville;
    TextField tdescription;
    String imagenom;
    Button btnajout, btnimage;
    Form f;

    public AjouterEtablissement() {
        f = new Form("home", BoxLayout.y());
        tnom = new TextField();
        tdescription = new TextField();
        
        
        btnimage = new Button("Image");
       
       type = new ComboBox<>();
        type.addItem("Garderie");
        type.addItem("jardin d'enfant");
        type.addItem("ecole primaire");
        type.addItem("colége");
        type.addItem("lycée");
        
        region = new ComboBox<>();
        RegionService rs=new RegionService();
        for (Region r : rs.getList2()) {
            region.addItem(r.getNom_region());
         
        }
        ville =new ComboBox<>();
        region.addActionListener((ActionEvent e) -> {
         
          //int id = rs.showDetail("tunis").getId_region() ;
          int id = 0 ;
          
           for(Region r : rs.getList2()){
           if(region.getSelectedItem().equals(r.getNom_region())){
             id= r.getId_region();
           }
           
           }
            VilleService vs=new VilleService();
            for (Ville v : vs.getList2(id)) {
                ville.addItem(v.getNom_ville());
            }
        });
         
        
        
        
        
/*
        ttype = new Picker();
        ttype.setType(Display.PICKER_TYPE_STRINGS);
        ttype.setStrings("Garderie","jardin d'enfant","ecole primaire","colége","lycée");
        ttype.setSelectedString("Type");
        region = new Picker();
        region.setType(Display.PICKER_TYPE_STRINGS);
        region.setStrings("A Game of Thrones", "A Clash Of Kings", "A Storm Of Swords", "A Feast For Crows",
                "A Dance With Dragons", "The Winds of Winter", "A Dream of Spring");
        region.setSelectedString("Region");
        ville = new Picker();
        ville.setType(Display.PICKER_TYPE_STRINGS);
        ville.setStrings("A Game of Thrones", "A Clash Of Kings", "A Storm Of Swords", "A Feast For Crows",
                "A Dance With Dragons", "The Winds of Winter", "A Dream of Spring");
        ville.setSelectedString("Ville");*/
        btnajout = new Button("ajouter");
        

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
                            imagenom = System.currentTimeMillis() + ".png";
                            cr.setFilename("file", imagenom);

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
        f.add(type);
        f.add(region);
        f.add(ville);
        f.add(tdescription);
        f.add(btnimage);
        f.add(btnajout);

        btnajout.addActionListener((ActionEvent e) -> {
            EtablissementService ser = new EtablissementService();
            Etablissement et = new Etablissement(tnom.getText(),type.getSelectedItem(),region.getSelectedItem(),
            ville.getSelectedItem(),tdescription.getText(),imagenom);
            ser.addEtablissement(et);
            Dialog d = new Dialog("Succes!");
            TextArea popupBody = new TextArea("Etablissement Ajouter avec succes");
            popupBody.setUIID("PopupBody");
            popupBody.setEditable(false);
            d.setLayout(new BorderLayout());
            d.add(BorderLayout.CENTER, popupBody);
            d.show();
            d.setTimeout(2000);
            AffichageEtablissement a = null;
           
                a = new AffichageEtablissement();
            
            a.getForm().show();
        });
        
    }
 
    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

    
}
