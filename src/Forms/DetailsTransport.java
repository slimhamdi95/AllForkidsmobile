/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms;

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
        form = new Form("covoiturage",new BoxLayout(Y_AXIS));
        departname =  new Label(service.getTransportDetails(id).getDepartName(), "SecondaryTitle");
    }
    
}
