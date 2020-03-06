/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.Date;

/**
 *
 * @author ACER
 */
public class Pegawai {
    public String namaPegawai ;
    public String  tglLahir ;
    public String role ;
    public int noHp ;
    public String alamat ;
    public String userName ;
    public String password ;
           
    public Pegawai(String nama , String tglLahir, String role, String userName)
    {
        this.role= role ;
        
    }
    public Pegawai (String namaPegawai , String tglLahir , String role ,int  noHp ,String alamat , String userName , String password )
    {
      this.alamat= alamat ; 
      this.namaPegawai = namaPegawai ; 
      this.noHp = noHp ;
      this.password = password  ;
      this.role = role ; 
      this.tglLahir= tglLahir ;
      this.userName = userName ; 
      
    }
    
    public void setAlamat(String alamat )
    {
        this.alamat= alamat ; 
    }
    public String getAlamat()
    {
        return alamat ; 
    }
    public void setNama(String namaPegawai )
    {
        this.namaPegawai= namaPegawai ;
    }
    public String getNamaPegawai()
    {
        return namaPegawai ;
    }
    public void setNoHp(int noHp)
    {
        this.noHp = noHp ;
    }
    public int getNoHp()
    {
        return  noHp; 
                
    }
    public void setPassword(String password)
    {
        this.password= password ;
    }
    public String getPassword()
    {
        return password  ;
    }
    public void setRole(String role )
    {
        this.role = role ;
    }
    public String getRole( )
    {
        return role ; 
    }
    public void setTglLahir(String tglLahir)
    {
        this.tglLahir = tglLahir  ; 
    }
    public String getTglLahir()
    {
        return  tglLahir ; 
    }
    public void setUserName (String userName )
    {
        this.userName = userName  ;
    }
    public String getUserName ()
    {
        return userName ; 
    }
}
