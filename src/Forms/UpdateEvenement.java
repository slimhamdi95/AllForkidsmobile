/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms;

import Entity.Evenement;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.spinner.Picker;

/**
 *
 * @author slim
 */
public class UpdateEvenement {
     Form f;
     double lat= 0,  lng=0;
    TextField tnom;
    TextField tdescription;
    Picker datePicker;
    Picker timePicker;
    String fichernom="";
    Picker stringPicker;
    Button btnajout, btnimage;
    public UpdateEvenement(Evenement ev ) {
        
        
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
}
