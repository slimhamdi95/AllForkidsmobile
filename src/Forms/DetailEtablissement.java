/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms;

import Entity.Etablissement;
import Entity.Session;
import Services.EtablissementService;
import Services.EvenementService;
import com.codename1.components.SpanLabel;
import com.codename1.messaging.Message;
import com.codename1.ui.Button;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import static com.codename1.ui.layouts.BoxLayout.Y_AXIS;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import java.io.IOException;

/**
 *
 * @author FATNASSI
 */
public class DetailEtablissement {

    Form f;
    SpanLabel lb;
    Label nom;
    Button btndelete;
    Button btnupdate;
    Button btnrejoindre;
    private Resources theme;
    private EncodedImage largePlaceholder;
    private int id;
    Dimension d = new Dimension(500, 500);
    EtablissementService es = new EtablissementService();

    public DetailEtablissement(int id) {
        this.id = id;
btnrejoindre = new Button("Rejoindre");
        Etablissement etablissement = new Etablissement();
        etablissement.setId_etablissement(id);
        etablissement.setNom(es.afficheDetail(id).getNom());
        etablissement.setType(es.afficheDetail(id).getType());
        etablissement.setRegion(es.afficheDetail(id).getRegion());
        etablissement.setVille(es.afficheDetail(id).getVille());
        etablissement.setImage(es.afficheDetail(id).getImage());
        etablissement.setDescription(es.afficheDetail(id).getDescription());
        etablissement.setVerification(es.afficheDetail(id).getNom());
        etablissement.setId_user(es.afficheDetail(id).getId_user());
        etablissement.setVerification(es.afficheDetail(id).getVerification());

        f = new Form("Etablissement", new BoxLayout(Y_AXIS));
        lb = new SpanLabel("");
        nom = new Label(es.afficheDetail(id).getNom(), "SecondaryTitle");

        theme = UIManager.initFirstTheme("/theme");
        nom.setAlignment(CENTER);
        nom.setSize(d);
        f.add(nom);
        largePlaceholder = EncodedImage.createFromImage(theme.getImage("TitleArea.borderCenter_2.png"), false);
        largePlaceholder = largePlaceholder.scaledEncoded(200, 200);
        Image i = URLImage.createToStorage(largePlaceholder, System.currentTimeMillis()+ es.afficheDetail(id).getNom(), "http://localhost/allforkids/web/uploads/images/" + es.afficheDetail(id).getImage(), URLImage.RESIZE_SCALE_TO_FILL);

        f.add(i);
        f.add(new Label("Type :"));
        f.add(new Label(es.afficheDetail(id).getType()));
        f.add(new Label("Region :"));
        f.add(new Label(es.afficheDetail(id).getRegion()));
        f.add(new Label("Ville :"));
        f.add(new Label(es.afficheDetail(id).getVille()));
        f.add(new Label("Description :"));
        f.add(new Label(es.afficheDetail(id).getDescription()));

        f.getToolbar().addCommandToRightBar("back", null, (ev) -> {
            AffichageEtablissement h = null;

            h = new AffichageEtablissement();

            System.out.println("erreur");
            h.getForm().show();
        });

        btndelete = new Button("Effacer");
        btndelete.addActionListener((ActionEvent e) -> {
            EtablissementService ser = new EtablissementService();
            ser.deleteEtablissement(id);
            Dialog d = new Dialog("Succes!");
            TextArea popupBody = new TextArea("Etablissement Supprimer avec succes", 3, 10);
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

        });

        btnupdate = new Button("Modifier");

        btnupdate.addActionListener((ActionEvent e) -> {
            ModifierEtablissement me = new ModifierEtablissement(etablissement);
            me.getF().show();
        });
        btnrejoindre.addActionListener((ActionEvent e) -> {
             es.addEtablissementRejoundre(id, Session.getId());
             ///Mail
              Message m = new Message("L'éléve"+Session.getUsername()+"veut rejoindre votre établissemant");
             m.setMimeType(Message.MIME_HTML);
        Display.getInstance().sendMessage(new String[] {es.getMail(es.afficheDetail(id).getId_user())}, "Demande de rejoindre", m);
        });
        if ((es.getRoles(Session.getId()).equals("[ROLE_RESPONSABLE]")) && (es.afficheDetail(id).getId_user() == Session.getId())) {
            f.add(btndelete);
            f.add(btnupdate);
        }
        if(es.getRoles(Session.getId()).equals("[ROLE_ELEVE]")){
            f.add(btnrejoindre);
        }

    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
