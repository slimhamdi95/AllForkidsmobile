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
public class ajoutTransport {
    
    Form form;
    
    public ajoutTransport (){
    form = new Form("remplir le formulaire", BoxLayout.y());
    Validator v = new Validator();
    
    form.add(new Label("poit de depart:"));
    TextField depart = new TextField();
    form.add(depart);
    
    form.add(new Label("poit d'arrivé:"));
    TextField arrive = new TextField();
    form.add(arrive);
    
    form.add(new Label("description:"));
    TextField description = new TextField();
    form.add(description);
    
    form.add(new Label("telephone:"));
    TextField telephone = new TextField("", "numero compose de 8 chiffre",8, TextArea.NUMERIC);
        v.addConstraint(telephone,new NumericConstraint(true, 10000000, 99999999, "telephone non valide"));
    form.add(telephone);
    
    form.add(new Label("place:"));
    TextField place = new TextField("", "1",1, TextArea.NUMERIC);
        v.addConstraint(place,new LengthConstraint(1));
        v.addConstraint(place,new NumericConstraint(true));
    form.add(place);
    
    form.add(new Label("frais:"));
    TextField frais = new TextField();
    form.add(frais);
    
    form.add(new Label("type:"));
    ComboBox<Object> type = new ComboBox<>();
        type.addItem("occasionnellement");
        type.addItem("reguliérement");
    form.add(type);
    
    Button valider = new Button("valider");
    form.add(valider);
    
    valider.addActionListener((ActionEvent e) -> {
            TransportService service = new TransportService();
            Transport transport = new Transport(depart.getText(),arrive.getText(),description.getText(),telephone.getText(),place.getText(),frais.getText(),"occasionnellement");
            service.addTransport(transport);
            Dialog d = new Dialog("Succes!");
            TextArea popupBody = new TextArea("votrce offre est valider");
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
   
    }
    
    public Form getForm() {
        return form;
    }

    public void setForm(Form form) {
        this.form = form;
    }
}
