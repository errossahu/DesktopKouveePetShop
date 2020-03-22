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
public class Harga_Layanan {
    public int id_hargaLayanan;
    public  int id_Layanan ;
    public int id_ukuran ;
    public int hargaLayanan;
    
    public String create_by ;
    public String cretate_at ;
    public String delete_At ;
    public String delete_By ;
    public String modified_by ;
    public String modifed_at ;
      
   public  UkuranHewan uh= new UkuranHewan();
   
   public UkuranHewan Uh ;
   List<Layanan> lyn=new ArrayList<Layanan>();
    
    
       
     public List<Layanan> getLayanan() {
        return lyn;
    }
 
    public void setLayanan(List<Layanan> lyn) {
        this.lyn =lyn;
    }
   public UkuranHewan getUkuran()
   {
       return Uh;
   }
     


     
       public Harga_Layanan(){}

    public Harga_Layanan(int harga)
    {
        this.hargaLayanan= harga;
    }
    public Harga_Layanan(int iD_Harga_layannan , int id_Layaan ,int idUkuran , int Harga  )
    {
        this.id_hargaLayanan = iD_Harga_layannan;
        this.id_Layanan = id_Layaan ;
        this.id_ukuran =idUkuran ;
        this.hargaLayanan = hargaLayanan ;
    }
  
    public void setHarga(int harga)
    {
        this.hargaLayanan = harga;
    }
    public int getHarga()
    {
        return hargaLayanan ;
    }
    public void setIdUkuran(int id)
    {
        this.id_ukuran = id;
    }
    public int getIdUkuran()
    {
        return id_ukuran ;
    }
    
    public void setIdLayanan(int id)
    {
        this.id_Layanan = id ;
    }
    public int getIdLayanan()
    {
        return id_Layanan ;
    }
    public void setIdHargaLayanan()
    {
        this.id_hargaLayanan = id_hargaLayanan ;
    }
    
    
    
    public int  getIdHargaLayanan()
    {
        return id_hargaLayanan ;
    }
    
}
