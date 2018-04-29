/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms;

import Entity.BCrypt;
import Entity.User;
import Services.LoginService;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;

/**
 *
 * @author casa-net
 */
public class Inscrit {
     Form f;
    Label Username1, nom1 , prenom1 , role1 ,email1 , cin1 , password1,pic1;
  

    TextField Username, nom , prenom , role ,email , cin , password,passwordVerif;
    
    Button valider , vider ,pic;
   
     public Inscrit() {
        
        f = new Form();
        
        
        
        
        
        
      
        Username =new TextField("","Username");
        nom=new TextField("","nom");
         prenom =new TextField("","prenom");
          role =new TextField("","role");
           email =new TextField("","email");
            cin =new TextField("","cin");
             pic = new Button("photo");
             password =new TextField("","password"); 
             passwordVerif =new TextField("","password Verif"); 
             
           
        
        
        valider = new Button("valider");
        vider = new Button("vider");
        
       // pass = new Password();
      
       // f.add(Username1);
         f.add(Username);
          f.add(nom);
         //  f.add(nom1);
            f.add(prenom);
          //   f.add(prenom1);
              f.add(email);
            //   f.add(email1);
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
           
           User u = new User();
                    
        });
     
       
       
      
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
}
