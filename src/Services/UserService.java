/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entity.BCrypt;
import Entity.Session;
import Entity.User;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.mycompany.myapp.MyApplication;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;


/**
 *
 * @author casa-net
 */
public class UserService {
     public int idUser = 1;
    private ConnectionRequest connectionRequest;
Form mainForm = new Form();
    public void creeCompte(User user) {
        connectionRequest = new ConnectionRequest() {
            @Override
            protected void postResponse() {

                Dialog.show("Confirmation", "compte crée ! vous pouvez vous connecter !", "Ok", null);
            }
        };

        connectionRequest.setUrl("http://localhost/MobileService/addUser.php?"
                + "userName=" + user.getUserName()
                + "&email=" + user.getEmail()
                + "&password=" + user.getPassword()
                + "&nom=" + user.getNom()
                + "&prenom=" + user.getPrenom());
        NetworkManager.getInstance().addToQueue(connectionRequest);
    }

    public void getUser(User u, String password) {

        connectionRequest = new ConnectionRequest() {
            @Override
            protected void readResponse(InputStream in) throws IOException {

                JSONParser json = new JSONParser();
                try {
                    Reader reader = new InputStreamReader(in, "UTF-8");

                    java.util.Map<String, Object> data = json.parseJSON(reader);
                    java.util.List<java.util.Map<String, Object>> content = (java.util.List<java.util.Map<String, Object>>) data.get("root");

                    for (java.util.Map<String, Object> obj : content) {

                        u.setId(Integer.parseInt(obj.get("id").toString()));
                        u.setEmail((String) obj.get("email"));
                        u.setPassword((String) obj.get("password"));
                        u.setNom((String) obj.get("nom"));
                        u.setPrenom((String) obj.get("prenom"));
                        u.setEnabled(Integer.parseInt(obj.get("enabled").toString()));
                        u.setRoles((String) obj.get("roles"));
                        u.setPhoto((String) obj.get("photo"));
                        /*System.out.println((String) obj.get("username"));
                       System.out.println((String) obj.get("email"));
                       System.out.println((String) obj.get("password"));
                       System.out.println((String) obj.get("nom"));
                       System.out.println((String) obj.get("prenom"));*/

                        // System.out.println(u.getPassword());
                        //  System.out.println(BCrypt.hashpw(password,"$2y$13$s3ASD/KQ2MM0Q0.G4JHmeu"));
                        String pass = BCrypt.hashpw(password, "$2y$13$s3ASD/KQ2MM0Q0.G4JHmeu");

                        if ((BCrypt.checkpw(password, u.getPassword())) && (u.getEnabled() == 1) && (u.getRoles().equals("a:1:{i:0;s:11:\"ROLE_CLIENT\";}"))) {
                            System.out.println("passé");
                           
                            Session.setId(u.getId());
                            Session.setUserName(u.getUserName());
                            Session.setEmail(u.getEmail());
                            Session.setPassword(u.getPassword());
                            Session.setNom(u.getNom());
                            Session.setPrenom(u.getPrenom());
                            Session.setEnabled(u.getEnabled());
                            Session.setRoles(u.getRoles());
                            Session.photo = u.getPhoto();
                            //System.out.println(Session.getEmail());
                            /*  gestionProfilForm gest=new gestionProfilForm();
                       gest.showProfilForm();*/
                            

                           mainForm.show();

                        }
                       else if ((BCrypt.checkpw(password, u.getPassword())) && (u.getEnabled() == 1) && (!u.getRoles().equals("a:1:{i:0;s:11:\"ROLE_CLIENT\";}"))) {
                            System.out.println("passé");
                           
                            Session.setId(u.getId());
                            Session.setUserName(u.getUserName());
                            Session.setEmail(u.getEmail());
                            Session.setPassword(u.getPassword());
                            Session.setNom(u.getNom());
                            Session.setPrenom(u.getPrenom());
                            Session.setEnabled(u.getEnabled());
                            Session.setRoles(u.getRoles());
                            Session.photo = u.getPhoto();
                            //System.out.println(Session.getEmail());
                            /*  gestionProfilForm gest=new gestionProfilForm();
                       gest.showProfilForm();*/
                            mainForm.show();

                        } else {
                            Dialog.show("login echoué", "verifié vos donnés !", "Ok", null);
                        }

                    }
                } catch (IOException err) {
                }

            }

        };

        connectionRequest.setUrl("http://localhost/MobileService/getUser.php?username=" + u.getUserName()/*+"&password="+password*/);
        NetworkManager.getInstance().addToQueue(connectionRequest);

    }

    public void modifierMail(User user) {
        connectionRequest = new ConnectionRequest() {
            @Override
            protected void postResponse() {

                Dialog.show("Confirmation", "email changer!", "Ok", null);
            }
        };

        connectionRequest.setUrl("http://localhost/MobileService/modifierMail.php?"
                + "userName=" + user.getUserName()
                + "&email=" + user.getEmail());
        NetworkManager.getInstance().addToQueue(connectionRequest);
    }

    public void changermdp(User user) {
        connectionRequest = new ConnectionRequest() {
            @Override
            protected void postResponse() {

                Dialog.show("Confirmation", "mot de passe changer!", "Ok", null);
            }
        };

        connectionRequest.setUrl("http://localhost/MobileService/changermotdepasse.php?"
                + "userName=" + user.getUserName()
                + "&password=" + user.getPassword());
        NetworkManager.getInstance().addToQueue(connectionRequest);
    }

    public void creeComptefb(User user) {
        connectionRequest = new ConnectionRequest() {
            @Override
            protected void postResponse() {

                Dialog.show("Confirmation", "compte crée ! vous pouvez vous connecter !", "Ok", null);
            }
        };

        connectionRequest.setUrl("http://localhost/MobileService/addUserFB.php?"
                + "userName=" + user.getUserName()
                + "&email=" + user.getEmail()
                + "&password=" + user.getPassword()
                + "&nom=" + user.getNom()
                + "&prenom=" + user.getPrenom()
                
                + "&image=" + user.getPhoto());
        NetworkManager.getInstance().addToQueue(connectionRequest);
    }

}
