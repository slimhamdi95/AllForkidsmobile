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
public  class Session  {
  private static int Id =0;
         
         
         private static String Username="";

    public static int getId() {
        return Id;
    }

    public static void setId(int Id) {
        Session.Id = Id;
    }

    public static String getUsername() {
        return Username;
    }

    public static void setUsername(String Username) {
        Session.Username = Username;
    }
         
}
