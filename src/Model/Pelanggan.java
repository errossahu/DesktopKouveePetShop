/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author ACER
 */
public class Pelanggan {
    
    public int Id_pelanggan ;
    public String nama ;
    public String alamat ; 
    public String tglLahir ;
    public String telp ; 
    public String Create_at ;
    public String Create_by ;
    public String modified_at ;
    public String modified_by ;
    public String delete_by ; 
    public String delete_at ; 
    public int Aktif ;
    
    
    public Pelanggan(){}
    public Pelanggan(int id )
    {
        this.Id_pelanggan = id ;
    }
     public Pelanggan(int id,String nama , String alamat , String tglLahir, String telp)
    {
        this.Id_pelanggan = id ;
        this.nama = nama ;
        this.alamat = alamat ;
        this.tglLahir  =tglLahir ;
        this.telp = telp;
        
    }
     public Pelanggan(int id,String nama , String alamat , String tglLahir, String telp,String createat, String createby)
    {
        this.Create_at = createat ;
        this.Create_by = createby ;
        this.Id_pelanggan = id ;
        this.nama = nama ;
        this.alamat = alamat ;
        this.tglLahir  =tglLahir ;
        this.telp = telp;
        
    }
    public Pelanggan(String nama , String alamat , String tglLahir, String telp)
    {
        this.nama = nama ;
        this.alamat = alamat ;
        this.tglLahir  =tglLahir ;
        this.telp = telp;
        
    }
    public Pelanggan(int  id_pelanggan , String nama , String alamat, String tgllahir , String telp , 
            String Create_at ,String create_By , String modified_at , String modified_By , 
            String  delete_by, String delete_at )
    {
        this.Id_pelanggan = id_pelanggan ;
        this.Aktif = Aktif ;
        this.Create_at= Create_at ;
        this.Create_by = create_By ;
        this.alamat = alamat ;
        this.delete_at = delete_at ;
        this.delete_by = delete_by ;
        this.modified_at = modified_at ;
        this.modified_by = modified_By ;
        this.nama = nama ;
        this.telp= telp ;
        this.tglLahir = tgllahir ;
    }
    public void setTelp(String telp)
    {
        this.telp = telp ;
    }
    public String getTelp()
    {
        return telp ;
    }
    public void setTglLahir(String tglLahir)
    {
        this.tglLahir = tglLahir;
    }
    public String getTglLahir()
    {
        return tglLahir ;
    }
    public void setNama (String nama)
    {
        this.nama = nama ;
    }
    public String getNama()
    {
        return nama ; 
    }
    public void setAlamat(String Alamat)
    {
        this.alamat = Alamat ;
    }
    public String getAlamat()
    {
        return alamat ;
    }
    public void setCreated_by (String createdby)
    {
        this.Create_by = createdby;
    }
    public String getCreated_by()
    {
        return Create_by ;
    }
    public void setCreated_At(String created_at )
    {
        this.Create_at = created_at ;
    }
    public String  getCreated_At()
    {
        return Create_at ;
    }
    public void setId_pelanggan(int Id_Pelanggan)
    {
        this.Id_pelanggan = Id_Pelanggan ;
    }
    public int getId_pelanggan()
    {
        return Id_pelanggan ;
    }
   
}
