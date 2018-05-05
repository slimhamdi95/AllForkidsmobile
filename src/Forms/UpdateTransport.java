/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms;

import Entity.Transport;
import Services.TransportService;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.validation.LengthConstraint;
import com.codename1.ui.validation.NumericConstraint;
import com.codename1.ui.validation.Validator;
import java.io.IOException;

/**
 *
 * @author DELL
 */
public class UpdateTransport {
    
    Form form;
    public UpdateTransport (Transport transport){
        form = new Form("formulaire modif", BoxLayout.y());
        Validator v = new Validator();
        /*
        form.add(new Label("poit de depart:"));
        TextField depart = new TextField();
        form.add(depart);

        form.add(new Label("poit d'arrivé:"));
        TextField arrivé = new TextField();
        form.add(arrivé);
        */
        form.add(new Label("description:"));
        TextField description = new TextField(transport.getDescription(),"description");
        form.add(description);

        form.add(new Label("telephone:"));
        TextField telephone = new TextField(transport.getTelephone(),"description");
            v.addConstraint(telephone,new NumericConstraint(true, 10000000, 99999999, "telephone non valide"));
        form.add(telephone);

        form.add(new Label("place:"));
        TextField place = new TextField(transport.getPlace(),"place");
            v.addConstraint(place,new LengthConstraint(1));
            v.addConstraint(place,new NumericConstraint(true));
        form.add(place);

        form.add(new Label("frais:"));
        TextField frais = new TextField(transport.getFrais(),"frais");
        form.add(frais);

        form.add(new Label("type:"));
        ComboBox<Object> type = new ComboBox<>();
            type.addItem("occasionnellement");
            type.addItem("reguliérement");
        form.add(type);

        Button valider = new Button("s'auvegarder");
        form.add(valider);
        System.out.println(transport);
        valider.addActionListener((ActionEvent e) -> {
            TransportService service = new TransportService();
            Transport transports = new Transport(transport.getIdTransport(),description.getText(),telephone.getText(),place.getText(),frais.getText(),"occasionnellement");
            System.out.println(transports);
            service.UpdateTransport(transports);
            Dialog d = new Dialog("Succes!");
            TextArea popupBody = new TextArea("votre offre est modifier");
            popupBody.setUIID("PopupBody");
            popupBody.setEditable(false);
            
            Button ok = new Button("OK");
            ok.addActionListener((ActionEvent ee) -> {
                ShowTransport a = null;
                try {
                    a = new ShowTransport();
                } catch (IOException ex) {
                    System.out.println("erreuur");
                }
                a.getForm().show();
            });
            
            d.setLayout(new BorderLayout());
            d.add(BorderLayout.CENTER, popupBody);
            d.add(BorderLayout.SOUTH,ok);
            d.show();
            d.setTimeout(2000);
            
        });
        
        form.getToolbar().addCommandToRightBar("back", null, (ets) -> {
            DetailsTransport a = null;
            a = new DetailsTransport(transport.getIdTransport());
            a.getForm().show();
        });
    }
    
    public Form getForm() {
        return form;
    }

    public void setForm(Form form) {
        this.form = form;
    }
}
