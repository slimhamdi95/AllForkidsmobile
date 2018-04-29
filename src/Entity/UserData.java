/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author casa-net
 */
public interface UserData {
    public String getName();
    public String getId();
    public String getImage();
    public void fetchData(String token, Runnable callback); 
}
