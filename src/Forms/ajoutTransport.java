/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms;

import com.codename1.ui.ComboBox;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author DELL
 */
public class ajoutTransport {
    
    Form form;
    
    public ajoutTransport (){
    form = new Form("remplir le formulaire", BoxLayout.y());
    
    form.add(new Label("poit de depart:"));
    TextField depart = new TextField();
    form.add(depart);
    
    form.add(new Label("poit d'arrivé:"));
    TextField arrivé = new TextField();
    form.add(arrivé);
    
    form.add(new Label("description:"));
    TextField description = new TextField();
    form.add(description);
    
    form.add(new Label("telephone:"));
    TextField telephone = new TextField();
    form.add(telephone);
    
    form.add(new Label("place:"));
    TextField place = new TextField();
    form.add(place);
    
    form.add(new Label("frais:"));
    TextField frais = new TextField();
    form.add(frais);
    
    form.add(new Label("type:"));
    ComboBox<Object> type = new ComboBox<>();
        type.addItem("occasionnellement");
        type.addItem("reguliérement");
    form.add(type);
    }
    
    public Form getForm() {
        return form;
    }

    public void setForm(Form form) {
        this.form = form;
    }
}
