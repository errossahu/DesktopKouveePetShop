/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import DAO.NetClientGet;

/**
 *
 * @author ACER
 */
public class JenisHewan {

    public int id_jenisHewan ;
    public String nama ;
    public String create_at ;
    public String crated_by ;
    public String modified_by ;
    public String modified_at ;
    public String delete_at ;
    public String delete_by ;
    
    public JenisHewan(){}
    public JenisHewan(int id ,String nama , String created_at , String created_by , String modified_by , 
            String modified_at , String deleteat , String deletBy  )
    {
        this.id_jenisHewan= id ;
        this.nama = nama ;
        this.create_at = created_at ;
        this.crated_by = created_by ;
        this.modified_by = modified_by ;
        this.modified_at = modified_at ;
        this.delete_at = deleteat ;
        this.delete_by = deletBy ;
    }
    
    public void setId(int id )
    {
        this.id_jenisHewan = id ;
    }
    public int getId()
    {
        return id_jenisHewan ;
    }
    public void setNama(String nama)
    {
        this.nama = nama ;
    }
    public String getnNama()
    {
        return nama ;
    }
    public void setCreateAt(String created_at)
    {
        this.create_at = created_at ;
    }
    public String getCreateAt ()
    {
        return create_at ;
    }
    public void setCreateBy(String created_by)
    {
        this.crated_by = created_by ;
    }
    public String getCreateBy ()
    {
       return crated_by ; 
    
       
    }
    public void setModified_by (String modified_by)
    {
        this.modified_by = modified_by;
    }
    public String getModified_by  ()
    {
        return modified_by ;
    }
    public void setModified_at(String modified)
    {
        this.modified_at = modified ;
    }
    public String getModifiedAT()
    {
        return modified_at ;
    }
    public void  setDeleteBy(String deleteBY){
       this.delete_by = deleteBY ; 
        
    }
    public String getDeleteBy()
    {
        return delete_by;
    }
    public void setDeleteAt(String deleteAt)
    {
        this.delete_at =deleteAt ;
    }
    public String getDeleteAt()
    {
        return delete_at ;
    }
            
    
        
        
}
