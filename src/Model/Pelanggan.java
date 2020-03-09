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
    public Pelanggan(int  id_pelanggan , String nama , String alamat, String tgllahir , String telp , 
            String Create_at ,String create_By , String modified_at , String modified_By , String  delete_by, String delete_at )
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
   
}
