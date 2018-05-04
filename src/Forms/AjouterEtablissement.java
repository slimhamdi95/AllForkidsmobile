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
import com.codename1.ui.list.DefaultListModel;
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
    Dialog dd  ;

    public AjouterEtablissement() {
        f = new Form("home", BoxLayout.y());
        tnom = new TextField("","Nom");
        tdescription = new TextField("","Description");
        
        f.getToolbar().addCommandToRightBar("back", null, (et) -> {
            AffichageEtablissement h = new AffichageEtablissement();
            h.getForm().show();
        });
        
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
         int id = 0 ;
          
           for(Region r : rs.getList2()){
           if(region.getSelectedItem().equals(r.getNom_region())){
             id= r.getId_region();
           }
           }
           
           DefaultListModel dlm = (DefaultListModel) ville.getModel();
           dlm.removeAll();
          // ville.getModel().removeItem(1);
          /* ville.getModel().removeItem(2);
           ville.getModel().removeItem(3);*/
           
            VilleService vs=new VilleService();
            for (Ville v : vs.getList2(id)) {
                
                ville.addItem(v.getNom_ville());
            }
        region.addActionListener((ActionEvent e) -> {
         
          //int id = rs.showDetail("tunis").getId_region() ;
          int idtest = 0 ;
          
           for(Region r : rs.getList2()){
           if(region.getSelectedItem().equals(r.getNom_region())){
             idtest= r.getId_region();
           }
           }
           
           dlm.removeAll();
          // ville.getModel().removeItem(1);
          /* ville.getModel().removeItem(2);
           ville.getModel().removeItem(3);*/
           
            for (Ville v : vs.getList2(idtest )) {
                
                ville.addItem(v.getNom_ville());
            }
            
        });
        
         
        
        
        
        
        btnajout = new Button("Ajouter");
        

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
            if(validation( et)){
            ser.addEtablissement(et);
            Dialog d = new Dialog("Succes!");
            TextArea popupBody = new TextArea("Etablissement Ajouter avec succes", 3, 10);
            popupBody.setUIID("PopupBody");
            popupBody.setEditable(false);
            Button ok = new Button("OK");
           
            ok.addActionListener((ActionEvent ee) -> {
                AffichageEtablissement a = null;
               
                    a = new AffichageEtablissement();
                    System.out.println("erreuur");
                a.getForm().show();
            });
            d.setLayout(new BorderLayout());
            d.addComponent(BorderLayout.SOUTH, ok);
            d.add(BorderLayout.CENTER, popupBody);
            d.show();
            }
            });
           
    }
    
    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
public boolean validation(Etablissement e){
    boolean a = false ;
    EtablissementService es = new EtablissementService();
    if(!e.getNom().equals("")){
    for (Etablissement ev : es.getList2()) {
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
    if(e.getDescription().equals("") ){
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
    if(imagenom.equals("")){
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

return true ;
} 

    
}
