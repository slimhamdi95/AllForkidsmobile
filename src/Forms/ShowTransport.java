/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms;

import Entity.Evenement;
import Entity.Transport;
import Services.TransportService;
import com.codename1.components.MultiButton;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;

/**
 *
 * @author DELL
 */
public class ShowTransport {
    Form form;
    SpanLabel spanlabel;
    Label label ;
    Container container ;
    
    public ShowTransport() {
        form = new Form();
        spanlabel = new SpanLabel("");
        label = new Label("");
        container = new Container();
        
        form.add(spanlabel);
        form.add(container);
        
        TransportService serviceTransport = new TransportService();
        java.util.List<Transport> listTransport =  serviceTransport.getTransportList() ;
        
        for (Transport t : serviceTransport.getTransportList()) {
            label.setText(t.getPlace());
            MultiButton mb = new MultiButton(t.getPlace());
            mb.setTextLine2(t.getTelephone());
        }
        
        form.getToolbar().addCommandToRightBar("back", null, (ev)->{HomeForm h=new HomeForm();
          h.getF().show();
          });
}
    
    public Form getForm() {
        return form;
    }

    public void setForm(Form form) {
        this.form = form;
    }
}
