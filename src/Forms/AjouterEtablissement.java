/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms;

import Entity.Etablissement;
import Services.EtablissementService;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;

/**
 *
 * @author FATNASSI
 */
public class AjouterEtablissement {
    

    private TextField Nom;
    private ComboBox Type;
    private TextField Ville;
    private TextField Region;
    private TextField Description;
    private Button Ajouter;

    public void showAjouterEtab(Resources theme, UIBuilder ui) {
        Container cnt1 = ui.createContainer(theme, "AjouterEtablissement");
        Form f1 = (Form) cnt1;

        Nom = (TextField) ui.findByName("Pays", cnt1);
        Type = (ComboBox) ui.findByName("Type", cnt1);
        Ville = (TextField) ui.findByName("Ville", cnt1);
        Region = (TextField) ui.findByName("Region", cnt1);
        Description = (TextField) ui.findByName("Description", cnt1);
        Ajouter = (Button) ui.findByName("Ajouter", cnt1);

        Ajouter.addActionListener(e
                -> {
            Etablissement l = new Etablissement(Nom.getText(), Type.getSelectedItem().toString(), Region.getText(), Ville.getText(), Description.getText());
            EtablissementService sere = new EtablissementService();
            sere.addEtablissement(l);
           
        });
        f1.show();

    }

    
}
