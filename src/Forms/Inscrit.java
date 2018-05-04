/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms;

import Entity.BCrypt;
import Entity.Evenement;
import Entity.UserE;
import Services.EvenementService;
import Services.LoginService;
import com.codename1.capture.Capture;
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
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
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
    Dialog dd;
    Picker stringPicker;
    String fichernom;

    TextField date, Username, nom, prenom, role, email, cin, password, passwordVerif;

    Button valider, vider, pic, camaera,Logout;

    public Inscrit() {

        f = new Form("Iscription", BoxLayout.y());
        Username = new TextField("", "Username");
        nom = new TextField("", "nom");
        prenom = new TextField("", "prenom");
        role = new TextField("", "role");
        email = new TextField("", "email");
        cin = new TextField("", "cin");
        pic = new Button("photo");
        datePicker = new Picker();
        camaera = new Button("camaera");
        password = new TextField("", "password");
        passwordVerif = new TextField("", "password Verif");
        stringPicker = new Picker();
        stringPicker.setType(Display.PICKER_TYPE_STRINGS);
        stringPicker.setStrings("Parent", "Enfant", "Docter", "Respensable");
        stringPicker.setSelectedString("Parent");
        datePicker.setDate(new Date());
        valider = new Button("valider");
        Logout =new Button("Logout");
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

        pic.addActionListener((ActionEvent e) -> {
            Capture.capturePhoto(Display.getInstance().getDisplayWidth(), -1);

        });
         Logout.addActionListener((ActionEvent e) -> {
          Login hi = new Login();
            hi.getF().show();

        });
      f.add(Logout);
        f.add(Username);
        f.add(nom);
        f.add(datePicker);    
        f.add(prenom);
        f.add(email);
        f.add(stringPicker);
        f.add(camaera);
        f.add(pic);
        f.add(cin);      
        f.add(password);
        f.add(passwordVerif);
            
        f.add(valider);

        valider.addActionListener((e) -> {
            LoginService s = new LoginService();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date d = new Date();
            String dd2 = sdf.format(datePicker.getDate());

            UserE u = new UserE(0, Username.getText(), email.getText(), stringPicker.getText(), cin.getText(), nom.getText(), prenom.getText(), dd2, pic.getText(), password.getText());
            if (validation(u)) {

                s.Inscrit(u);
                dd = new Dialog("succus");
                    TextArea popupBody = new TextArea("succus!!!");
                    popupBody.setUIID("PopupBody");
                    popupBody.setEditable(false);
                    Button close = new Button("OK");
            }else 
            {
            
            dd = new Dialog("Erreur");
                    TextArea popupBody = new TextArea("Le user n'existe pas !", 3, 10);
                    popupBody.setUIID("PopupBody");
                    popupBody.setEditable(false);
                    Button close = new Button("OK");
            }
        });

    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

    public boolean validation(UserE e) {
        boolean a = false;
        LoginService es = new LoginService();
        if (!e.getUsername().equals("")) {
            for (UserE ev : es.getList2()) {
                if (e.getNom().equals(ev.getNom())) {

                    dd = new Dialog("Erreur");
                    TextArea popupBody = new TextArea("Le Nom existe deja !", 3, 10);
                    popupBody.setUIID("PopupBody");
                    popupBody.setEditable(false);
                    Button close = new Button("OK");

                    close.addActionListener((ActionEvent ee) -> {
                        dd.dispose();
                    });
                    dd.setLayout(new BorderLayout());
                    dd.add(BorderLayout.SOUTH, close);
                    dd.add(BorderLayout.CENTER, popupBody);
                    dd.show();
                    return false;
                }
            }

        } else {
            dd = new Dialog("Erreur");
            TextArea popupBody = new TextArea("Nom ne peut pas etre null !", 3, 10);
            popupBody.setUIID("PopupBody");
            popupBody.setEditable(false);
            Button close = new Button("OK");

            close.addActionListener((ActionEvent ee) -> {
                dd.dispose();
            });
            dd.setLayout(new BorderLayout());
            dd.add(BorderLayout.SOUTH, close);
            dd.add(BorderLayout.CENTER, popupBody);
            dd.show();
            return false;
        }

        if (!(password.getText().equals(passwordVerif.getText()))) {

            dd = new Dialog("Erreur");
            TextArea popupBody = new TextArea("verifier votre mot de passe  !", 3, 10);
            popupBody.setUIID("PopupBody");
            popupBody.setEditable(false);
            Button close = new Button("OK");

            close.addActionListener((ActionEvent ee) -> {
                dd.dispose();
            });
            dd.setLayout(new BorderLayout());
            dd.add(BorderLayout.SOUTH, close);
            dd.add(BorderLayout.CENTER, popupBody);
            dd.show();
            return false;
        }

        if (fichernom.equals("")) {
            dd = new Dialog("Erreur");
            TextArea popupBody = new TextArea("vous devez ajouter une image !", 3, 10);
            popupBody.setUIID("PopupBody");
            popupBody.setEditable(false);
            Button close = new Button("OK");

            close.addActionListener((ActionEvent ee) -> {
                dd.dispose();
            });
            dd.setLayout(new BorderLayout());
            dd.add(BorderLayout.SOUTH, close);
            dd.add(BorderLayout.CENTER, popupBody);
            dd.show();
            return false;
        }

        if (e.getNom().equals("")) {
            dd = new Dialog("Erreur");
            TextArea popupBody = new TextArea("Ce champ ne peut etre vide !", 3, 10);
            popupBody.setUIID("PopupBody");
            popupBody.setEditable(false);
            Button close = new Button("OK");

            close.addActionListener((ActionEvent ee) -> {
                dd.dispose();
            });
            dd.setLayout(new BorderLayout());
            dd.add(BorderLayout.SOUTH, close);
            dd.add(BorderLayout.CENTER, popupBody);
            dd.show();
            return false;
        }

        if (e.getEmail().equals("")) {
            dd = new Dialog("Erreur");
            TextArea popupBody = new TextArea("Ce champ ne peut etre vide !", 3, 10);
            popupBody.setUIID("PopupBody");
            popupBody.setEditable(false);
            Button close = new Button("OK");

            close.addActionListener((ActionEvent ee) -> {
                dd.dispose();
            });
            dd.setLayout(new BorderLayout());
            dd.add(BorderLayout.SOUTH, close);
            dd.add(BorderLayout.CENTER, popupBody);
            dd.show();
            return false;
        }
        if (e.getPrenom().equals("")) {
            dd = new Dialog("Erreur");
            TextArea popupBody = new TextArea("Ce champ ne peut etre vide !", 3, 10);
            popupBody.setUIID("PopupBody");
            popupBody.setEditable(false);
            Button close = new Button("OK");

            close.addActionListener((ActionEvent ee) -> {
                dd.dispose();
            });
            dd.setLayout(new BorderLayout());
            dd.add(BorderLayout.SOUTH, close);
            dd.add(BorderLayout.CENTER, popupBody);
            dd.show();
            return false;
        }

        if (e.getCin().equals("")) {
            dd = new Dialog("Erreur");
            TextArea popupBody = new TextArea("Ce champ ne peut etre vide !", 3, 10);
            popupBody.setUIID("PopupBody");
            popupBody.setEditable(false);
            Button close = new Button("OK");

            close.addActionListener((ActionEvent ee) -> {
                dd.dispose();
            });
            dd.setLayout(new BorderLayout());
            dd.add(BorderLayout.SOUTH, close);
            dd.add(BorderLayout.CENTER, popupBody);
            dd.show();
            return false;
        }

        if (e.getPassword().equals("")) {
            dd = new Dialog("Erreur");
            TextArea popupBody = new TextArea("Ce champ ne peut etre vide !", 3, 10);
            popupBody.setUIID("PopupBody");
            popupBody.setEditable(false);
            Button close = new Button("OK");

            close.addActionListener((ActionEvent ee) -> {
                dd.dispose();
            });
            dd.setLayout(new BorderLayout());
            dd.add(BorderLayout.SOUTH, close);
            dd.add(BorderLayout.CENTER, popupBody);
            dd.show();
            return false;
        }

        return true;
    }
}
