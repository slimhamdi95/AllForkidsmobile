/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms;

import Entity.BCrypt;
import Entity.UserE;
import Services.LoginService;
import com.codename1.components.InfiniteProgress;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import java.io.IOException;
import java.util.Date;

/**
 *
 * @author casa-net
 */
public class Inscrit {
     Form f;
      Picker datePicker;

  Picker stringPicker;
String fichernom;

    TextField date,Username, nom , prenom , role ,email , cin , password,passwordVerif;
    
    Button valider , vider ,pic;
   
     public Inscrit() {
        
        f = new Form("Iscription", BoxLayout.y());
        
        
        
        
        
        
      
        Username =new TextField("","Username");
        nom=new TextField("","nom");
         prenom =new TextField("","prenom");
          role =new TextField("","role");
           email =new TextField("","email");
            cin =new TextField("","cin");
             pic = new Button("photo");
                     datePicker = new Picker();

             password =new TextField("","password"); 
             passwordVerif =new TextField("","password Verif"); 
             
        
        stringPicker = new Picker();
        stringPicker.setType(Display.PICKER_TYPE_STRINGS);
        stringPicker.setStrings("Parent", "Enfant", "Docter","Respensable");
        stringPicker.setSelectedString("Parent");
        datePicker.setDate(new Date());

        
        valider = new Button("valider");
        vider = new Button("vider");
        
       // pass = new Password();
       pic.addActionListener((ActionEvent e) -> {
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
       // f.add(Username1);
         f.add(Username);
          f.add(nom);
          f.add(datePicker);
         //  f.add(nom1);
            f.add(prenom);
          //   f.add(prenom1);
              f.add(email);
              f.add(stringPicker);
           f.add(pic);
               f.add(cin);
      //  f.add(cin1);
      
        f.add(password);
        f.add(passwordVerif); 
       // f.add(password1);
     
     f.add(vider);
      
        //f.add(pic1);

       
       
       
       
        f.add(valider);
        
       valider.addActionListener((e) -> {
           LoginService s = new LoginService();
           SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
           Date d = new Date();
           String dd = sdf.format(datePicker.getDate());
           UserE u = new UserE(0,Username.getText(),email.getText(),stringPicker.getText(),cin.getText(),nom.getText(),prenom.getText(),dd,pic.getText(),password.getText());
           s.Inscrit(u);
                    
        });
     
       
       
      
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
}
