/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms;

import Entity.Transport;
import Services.TransportService;
import com.codename1.components.MultiButton;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import java.io.IOException;

/**
 *
 * @author DELL
 */
public class MyTransport {
    Form form;
    SpanLabel spanlabel;
    Label label ;
    Container container ;
    
    public MyTransport() throws IOException {
        form = new Form("Mes offre");
        spanlabel = new SpanLabel("");
        label = new Label("");
        container = new Container();
        
        form.add(spanlabel);
        form.add(container);
        form.add(label);
        
        TransportService serviceTransport = new TransportService();
        java.util.List<Transport> listTransport =  serviceTransport.getTransportList() ;
        System.out.println(listTransport.toString());
        for (Transport t : serviceTransport.getTransportList()) {
            if (t.getId_user() == 9){
                MultiButton mb = new MultiButton(t.getDepartName());
            mb.setTextLine2(t.getArriveName());
            
            mb.addActionListener((al)->{
            DetailsTransport a = new DetailsTransport(t.getIdTransport());
            a.getForm().show();
            });
            container.add(mb);
            }
        }
        form.getToolbar().addCommandToRightBar("back", null,(ev)->{HomeForm h=new HomeForm();
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
