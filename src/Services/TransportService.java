/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;


import Entity.Transport;
import com.codename1.io.CharArrayReader;
import com.codename1.io.JSONParser;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author DELL
 */
public class TransportService {
    
        public ArrayList<Transport> getTransport(String json) {
        ArrayList<Transport> listTransport = new ArrayList<>();
        try {
            System.out.println(json);
            JSONParser j = new JSONParser();
            Map<String, Object> events = j.parseJSON(new CharArrayReader(json.toCharArray()));
            System.out.println(events);
            List<Map<String, Object>> list = (List<Map<String, Object>>) events.get("root");
            
            for (Map<String, Object> obj : list) {
<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> 8c2bf337c6652a86968e2f268a65a331efd45809
                /*Transport t = new Transport();

=======
                Transport t = new Transport();
>>>>>>> a4ec22ca8015f371fc0f3b7a468f970ee862791e
                // System.out.println(obj.get("id"));
                float idTransport = Float.parseFloat(obj.get("idTransport").toString());
                System.out.println(idTransport);
                t.setIdTransport((int) idTransport);
                t.setRegion(obj.get("region").toString());
                t.setVille(obj.get("ville").toString());
                System.out.println(t);
                listTransport.add(t);
<<<<<<< HEAD
*/
=======
<<<<<<< HEAD
*/
=======
>>>>>>> a4ec22ca8015f371fc0f3b7a468f970ee862791e
>>>>>>> 8c2bf337c6652a86968e2f268a65a331efd45809
            }
            
            } catch (IOException ex) {
        }
        System.out.println(listTransport);
        return listTransport;
    }
    
}
