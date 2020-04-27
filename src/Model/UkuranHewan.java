/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ACER
 */
public class UkuranHewan {
    
    public int id ;
    public String nama ;
    public String created_at ;
    public String Created_by ;
    public String modified_at ;
    public String modified_by ;
    public String delete_At ;
    public String delete_By ;
    List<Harga_Layanan> harga=new ArrayList<>();
    
    
     public List<Harga_Layanan> getLayanan() {
        return harga;
    }
 
    public void setDaftarHarga(List<Harga_Layanan> harga) {
        this.harga = harga;
    } 
  
    
    public UkuranHewan(){}
    public UkuranHewan(String nama )
    {
        this.nama = nama;
    }
    public UkuranHewan(int id,String nama )
    {
        this.id = id  ;
        this.nama = nama;
    }
    public UkuranHewan(int id,String nama ,String created_By , String created_At,String modif_by,String modif_At)
    {
        this.modified_at = modif_At;
        this.modified_by = modif_by;
        this.Created_by = created_By ;
        this.created_at = created_At;
        this.id = id  ;
        this.nama = nama;
    }
    public UkuranHewan(int id , String nama , String create_at, String created_by, String modified_by , String modifed_at , String delete_AT , String delete_by )
    {
        this.id = id ;
        this.nama = nama ;
        this.created_at =create_at ;
        this.Created_by = created_by ;
        this.modified_at= modifed_at ;
        this.modified_by = modified_by ;
        this.delete_At = delete_AT ;
        this.delete_By = delete_by ;
        
    }
    public void setModified_By(String modif)
    {
        this.modified_by = modif;
    }
    public String getModif_by()
    {
        return modified_by ;
    }
    public void setModified_At (String modif)
    {
        this.modified_at = modif ;
    }
    public String getModif_at ()
    {
        return modified_at ;
    }
    public String getCreateBy()
    {
        return Created_by ;
    }
    public void setCreateBy(String create)
    {
        this.Created_by =  create;
    }
    public void setCreateAt(String create)
    {
        this.created_at = create;
    }
    public String getCreateAt()
    {
        return created_at;
    }
    public void setNama(String nama )
    {
        this.nama = nama ;
    }
    public String getNama()
    {
        return nama;
    }
    public void setID(int id)
    {
        this.id = id ;
    }
    public int getID()
    {
        return id;
    }
            
    
}
