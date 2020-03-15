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
public class UkuranHewan {
    
    public int id ;
    public String nama ;
    public String created_at ;
    public String Created_by ;
    public String modified_at ;
    public String modified_by ;
    public String delete_At ;
    public String delete_By ;
    
    
    public UkuranHewan(){}
    public UkuranHewan(int id,String nama )
    {
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
