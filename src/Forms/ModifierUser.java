/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms;

import Entity.Session;
import Entity.UserE;
import Services.LoginService;
import com.codename1.components.InfiniteProgress;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
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
public class ModifierUser {
         Form f;
  
  Picker stringPicker;
String fichernom;
Picker datePicker;
    TextField date,Username, nom , prenom , role ,email , cin , password,passwordVerif;
    
    Button valider , vider ,pic;
   
     public ModifierUser() {
        
        f = new Form("Modifier Profil", BoxLayout.y());
        
        
        
        
        LoginService ls = new LoginService();
        
      
        Username =new TextField(ls.showDetail(Session.getUsername()).getUsername(),"username");
        nom=new TextField(ls.showDetail(Session.getUsername()).getNom());
         prenom =new TextField(ls.showDetail(Session.getUsername()).getPrenom());
          role =new TextField(ls.showDetail(Session.getUsername()).getRoles());
          datePicker = new Picker();
          
          datePicker.setText(ls.showDetail(Session.getUsername()).getDate());
           email =new TextField(ls.showDetail(Session.getUsername()).getEmail());
            cin =new TextField(ls.showDetail(Session.getUsername()).getCin());
             pic = new Button(ls.showDetail(Session.getUsername()).getPicture());
            
             
             
      /*  valider.addActionListener((e) -> {
           LoginService s = new LoginService();
           
           UserE u = new UserE(0,Username.getText(),email.getText(),stringPicker.getText(),cin.getText(),nom.getText(),prenom.getText(),date.getText(),pic.getText(),password.getText());
           s.Inscrit(u);
                    
        });*/
       

        
        valider = new Button("Modifier");
        vider = new Button("vider");
        pic = new Button("pic");
        
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
       f.add(Username);
        
          f.add(nom);
         //  f.add(nom1);
            f.add(prenom);
          //   f.add(prenom1);
              f.add(email);
            f.add(datePicker);
           f.add(pic);
               f.add(cin);
      //  f.add(cin1);
      
        
      
        //f.add(pic1);

       
       
       
       
        f.add(valider);
        
       valider.addActionListener((e) -> {
           LoginService s = new LoginService();
          SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
           Date d = new Date();
           String dd = sdf.format(datePicker.getDate());
           UserE u = new UserE(Session.getId(),Username.getText(),email.getText(),stringPicker.getText(),cin.getText(),nom.getText(),prenom.getText(),dd,pic.getText(),password.getText());
           s.Modifier(u);
                    
        });
     
       
       
      
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
}
