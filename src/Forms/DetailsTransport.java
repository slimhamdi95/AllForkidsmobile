/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms;

import Entity.Transport;
import Services.TransportService;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import static com.codename1.ui.layouts.BoxLayout.Y_AXIS;
/**
 *
 * @author DELL
 */
public class DetailsTransport {
    Form form;
    SpanLabel spanLabel;
    Label departname,arrivename,description,telephone,place,frais,type;
    
    private  int id ;
    TransportService service = new TransportService();
    
    public DetailsTransport(int id){
        this.id = id ;
        Transport transport = new Transport();
        
        transport.setIdTransport(id);
        transport.setDepartName(service.getTransportDetails(id).getDepartName());
        transport.setArriveName(service.getTransportDetails(id).getArriveName());
        transport.setDescription(service.getTransportDetails(id).getDescription());
        transport.setTelephone(service.getTransportDetails(id).getTelephone());
        transport.setPlace(service.getTransportDetails(id).getPlace());
        transport.setFrais(service.getTransportDetails(id).getFrais());
        transport.setType(service.getTransportDetails(id).getType());
        
        form = new Form("covoiturage",new BoxLayout(Y_AXIS));
        
        form.add(new Label("poit de depart:"));
        form.add(new Label(transport.getDepartName()));
        form.add(new Label("poit d'arrivée:"));
        form.add(new Label(transport.getArriveName()));
        form.add(new Label("description:"));
        form.add(new Label(transport.getDescription()));
        form.add(new Label("numero de telephone:"));
        form.add(new Label(transport.getTelephone()));
        form.add(new Label("nombre de place:"));
        form.add(new Label(transport.getPlace()));
        form.add(new Label("frais par personne:"));
        form.add(new Label(transport.getFrais()));
        form.add(new Label("type de covoiturage:"));
        form.add(new Label(transport.getType()));
        
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
