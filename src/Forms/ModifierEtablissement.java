/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms;

import Entity.Etablissement;
import Entity.Region;
import Entity.Ville;
import Services.EtablissementService;
import Services.RegionService;
import Services.VilleService;
import com.codename1.components.InfiniteProgress;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.list.DefaultListModel;
import java.io.IOException;

/**
 *
 * @author FATNASSI
 */
public class ModifierEtablissement {
    
    TextField tnom;
    ComboBox<String> type;
    ComboBox<String> region;
    ComboBox<String> ville;
    TextField tdescription;
    String imagenom;
    Button btnajout, btnimage;
    Form f;
    
    EtablissementService es = new EtablissementService();
    
    public ModifierEtablissement(Etablissement et) {
        
        imagenom = et.getImage();
        f = new Form("home", BoxLayout.y());
        tnom = new TextField(et.getNom(),"Nom");
        tdescription = new TextField(et.getDescription(),"Descripption");
        
        
        btnimage = new Button("Image");
       
       type = new ComboBox<>();
        type.addItem("Garderie");
        type.addItem("jardin d'enfant");
        type.addItem("ecole primaire");
        type.addItem("colége");
        type.addItem("lycée");
        
        type.setSelectedItem(et.getType());
        region = new ComboBox<>();
        RegionService rs=new RegionService();
        for (Region r : rs.getList2()) {
            region.addItem(r.getNom_region());
         
        }
        region.setSelectedItem(et.getRegion());
        ville =new ComboBox<>();
    
             int id = 0 ;
          
           for(Region r : rs.getList2()){
           if(region.getSelectedItem().equals(r.getNom_region())){
             id= r.getId_region();
           }
           }
           DefaultListModel dlm = (DefaultListModel) ville.getModel();
           dlm.removeAll();
           
           
            VilleService vs = new VilleService();
            for (Ville v : vs.getList2(id)) {
                
                ville.addItem(v.getNom_ville());
            }
       region.addActionListener((ActionEvent e) -> {
         
          //int id = rs.showDetail("tunis").getId_region() ;
          int idtest = 0 ;
          
           for(Region r : rs.getList2()){
           if(region.getSelectedItem().equals(r.getNom_region())){
             idtest = r.getId_region();
           }
           }
          
           dlm.removeAll();
           
           
           
            for (Ville v : vs.getList2(idtest)) {
                
                ville.addItem(v.getNom_ville());
            }
            
      });
        
         
        
            ville.setSelectedItem(et.getVille());
        btnajout = new Button("Modifier");
        

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
            Etablissement ett = new Etablissement(et.getId_etablissement(),et.getId_user(),tnom.getText(),type.getSelectedItem(),region.getSelectedItem(),
            ville.getSelectedItem(),tdescription.getText(),imagenom,et.getVerification());
            ser.UpdateEtablissement(ett);
            Dialog d = new Dialog("Succes!");
            TextArea popupBody = new TextArea("Etablissement Modifier avec succes", 3, 10);
            popupBody.setUIID("PopupBody");
            popupBody.setEditable(false);
            Button ok = new Button("OK");
           
            ok.addActionListener((ActionEvent ee) -> {
                DetailEtablissement a = null;
                
                    a = new DetailEtablissement(et.getId_etablissement());
                
                a.getF().show();
            });
            d.setLayout(new BorderLayout());
            d.addComponent(BorderLayout.SOUTH, ok);
            d.add(BorderLayout.CENTER, popupBody);
            d.show();
            });
        
    }
 
    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

    
    
}
