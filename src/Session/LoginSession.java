/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Session;


/**
 *
 * @author ACER
 */
public class LoginSession {
    public static String nama ;
    public static int idUser ;
    public static String role;
    public static String idTransaksi; 
    
    
    public static void setIdTransaksi(String a)
    {
        LoginSession.idTransaksi= a ;
    }
    public static String getIdTransaksi()
    {
        return idTransaksi ;
    }
    public static void setRole(String role)
    {
        LoginSession.role= role;
    }
    public static  String getRole()
    {
        return role ;
    }
    public static  void setIdUser(int idUser)
    {
      LoginSession.idUser=idUser ;
    }
    public static int getIduser()
    {
        return idUser;
    }
    public static void setNama(String nama )
    {
        LoginSession.nama = nama ;
    }
    public static String getNama()
    {
        return nama  ; 
        
    }
    
    
   
}
