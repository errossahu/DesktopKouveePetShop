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
public class Suplier {
    
    public String nama ;
    public String alamat ;
    public String telp ;
    public String create_at ;
    public String create_by ;
    public String Modified_by ;
    public String Modified_at ;
    public String delete_by ;
    public String delete_at ;
    
    public Suplier(){}
    public Suplier(String nama , String alamat , String telp , String create_by,String create_at ,String Modified_by , 
            String Modifie_at , String delete_by , String delete_at)
    {
        this.nama = nama ;
        this.alamat = alamat ; 
        this.telp= telp ;
        this.create_at = create_at ;
        this.create_by = create_by ;
        this.Modified_by = Modified_by ;
        this.Modified_at =Modifie_at ;
        this.delete_at = delete_at ;
        this.delete_by = this.delete_by;
                
        
    }
    public void setTelp (String telp)
    {
        this.telp = telp ;
    }
    public String  getTelp()
    {
        return telp ;
    }
    public void setNama(String nama )
    {
        this.nama = nama ; 
    }
    public String getNama()
    {
        return nama ; 
    }
    public void setAlamat(String alamat)
    {
        this.alamat= alamat ; 
    }
    public String getAlamat()
    {
        return alamat ; 
    }
    public void setCreateAt(String create_at)
    {       
        this.create_at = create_at ;
    }
    public String getCreateAt()
    {
        return create_at ;
    }
    
    public void setCreateBy(String create_by)
    {
        this.create_by = create_by ;
    }
    public String getCreateBy()
    {
        return create_by ;
    }

    public void setModified_by(String modified_by )
    {
        this.Modified_by = modified_by ;
    }
    public String getModifiedBy()
    {
        return Modified_by ;
    }
    public void setModifiedAt(String modified_at)
    {
        this.Modified_at = modified_at ;
    }
    public String getModified_at()
    {
        return Modified_at ;
    }
    public void setDeletBy(String deletBy )
    {
        this.delete_by = deletBy ;
    }
    public String getDeleteBy()
    {
        return delete_by ;
    }
    public void setDeletAt(String deletAt)
    {
        this.delete_at = deletAt ; 
    }
    public String getDeletAt()
    {
        return delete_at;
    }
    
}