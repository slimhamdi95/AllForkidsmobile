/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms;

import Entity.Etablissement;
import Entity.Evenement;
import Entity.Session;
import Services.EtablissementService;
import Services.EvenementService;
import com.codename1.components.MultiButton;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.Resources;
import java.io.IOException;

/**
 *
 * @author FATNASSI
 */
public class AffichageEtablissement {

    Form form;
    SpanLabel lb;
    Label nom;
    Container f1;
    private Resources theme;
    EncodedImage enc;

    public AffichageEtablissement() {

        form = new Form();
        f1 = new Container(BoxLayout.y());
        lb = new SpanLabel("");
        nom = new Label("");

        form.add(lb);
        form.add(f1);
        EtablissementService serviceetab = new EtablissementService();
        java.util.List<Etablissement> l = serviceetab.getList2();
        System.out.println(l.toString());
        for (Etablissement e : serviceetab.getList2()) {
            if (serviceetab.getRoles(Session.getId()).equals("[ROLE_RESPONSABLE, ROLE_USER]")) {
                if (e.getId_user() == Session.getId()) {
                    nom.setText(e.getNom());

                    MultiButton mb = new MultiButton(e.getNom());
                    mb.setTextLine2(e.getType());

                    theme = UIManager.initFirstTheme("/theme");
                    enc = EncodedImage.createFromImage(theme.getImage("round.png"), false);
                    Image i = URLImage.createToStorage(enc, System.currentTimeMillis()+e.getNom(), "http://localhost/allforkids/web/uploads/images/" + e.getImage(), URLImage.RESIZE_SCALE);

                    mb.setIcon(i);

                    mb.addActionListener((al) -> {
                        DetailEtablissement a = new DetailEtablissement(e.getId_etablissement());

                        a.getF().show();
                    });

                    form.add(mb);

                }

            } else {
                if (e.getVerification().equals("Valide")) {
                    nom.setText(e.getNom());

                    MultiButton mb = new MultiButton(e.getNom());
                    mb.setTextLine2(e.getType());

                    theme = UIManager.initFirstTheme("/theme");
                    enc = EncodedImage.createFromImage(theme.getImage("round.png"), false);
                    Image i = URLImage.createToStorage(enc, e.getNom(), "http://localhost/allforkids/web/uploads/images/" + e.getImage(), URLImage.RESIZE_SCALE);

                    mb.setIcon(i);

                    mb.addActionListener((al) -> {
                        DetailEtablissement a = new DetailEtablissement(e.getId_etablissement());

                        a.getF().show();
                    });

                    form.add(mb);

                }

            }

        }

        form.getToolbar().addCommandToRightBar("back", null, (et) -> {
            HomeForm h = new HomeForm();
            h.getF().show();
        });
        if ((serviceetab.getRoles(Session.getId()).equals("[ROLE_RESPONSABLE, ROLE_USER]"))){
        form.getToolbar().addCommandToRightBar("Ajouter", null, (ev) -> {
            AjouterEtablissement h = new AjouterEtablissement();
            h.getF().show();    
        });
        }
        
        System.out.println("dfbgfdgdhgtedrggdrgedrgdrgr" + serviceetab.getRoles(1));
    }

    public Form getForm() {
        return form;
    }

    public void setForm(Form f) {
        this.form = f;
    }
}
