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
    public int Id_Pegawai  ;
    public String Created_at ;
    public String Created_by ;
    public String Modified_at ;
    public String Modified_by ;
    public String deleted_at ;
    public String deleted_by ;
    public int Aktif ;
    
            
            
    public String namaPegawai ;
    public String  tglLahir ;
    public String role ;
    public String  noHp ;
    public String alamat ;
    public String userName ;
    public String password ;
           
  
    public Pegawai(){}
    public Pegawai (String namaPegawai , String tglLahir , String role ,String   noHp ,String alamat , String userName , 
            String password,int Id_Pegawai, String Created_at,String Created_by , String Modified_at 
            ,String Modified_by , String deleted_at , String deleted_by, int Aktif  )
    {
       this.Aktif= Aktif ;
      this.Id_Pegawai=Id_Pegawai ;
      this.Created_at=Created_at ;
      this.Created_by= Created_by ;
      this.Modified_at= Modified_at ;
      this.Modified_by =Modified_by ;
      this.deleted_at= deleted_at ;
      this.deleted_by = deleted_by ;
      this.alamat= alamat ; 
      this.namaPegawai = namaPegawai ; 
      this.noHp = noHp ;
      this.password = password  ;
      this.role = role ; 
      this.tglLahir= tglLahir ;
      this.userName = userName ; 
      
    }
    public void setModified_By(String modifiedBy)
    {
        this.Modified_by = modifiedBy;
    }
    public String getModified_by ()
    {
        return Modified_by;
    }
    public void setCreated_by (String creteBy)
    {
        this.Created_by =creteBy;
    }
    public String getCreateBy()
    {
        return Created_by;
    }
    public void setCreateAt(String createAt)
    {
        this.Created_at =createAt ;
    }
    public String GetCreateAt()
    {
        return Created_at ;
    }
    public void setDelelted_by(String delete_by)
    {
        this.deleted_by = delete_by ;
    }
    public String getDeleted_by( )
    {
        return deleted_by ;
    }
    public void setDeleted_at(String delete_at)
    {
        this.deleted_at =delete_at;
    }
    public String getDeleted_at()
    {
        return deleted_at;
    }
    public String getModified_at()
    {
        return Modified_at ;
    }
    public void setModified_at(String Modified_at)
    {
        this.Modified_at= Modified_at ;
    }
    public int getId()
    {
        return Id_Pegawai;
    }
    public void setID_Pegawai(int Id_Pegawai)
    {
        this.Id_Pegawai= Id_Pegawai ;
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
    public void setNoHp(String  noHp)
    {
        this.noHp = noHp ;
    }
    public String  getNoHp()
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
    public int getAktif()
    {
        return Aktif ;
    }
    public void setAktif(int Aktif)
    {
        this.Aktif= Aktif;
    }
}
