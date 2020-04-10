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
public class Produk {
    public String nama ;
    public String satuan ;
    public int jumlah  ; 
    public int harga ; 
    public int min_stok ;
    public String  gambar ;    
    public String Create_at ;
    public String Create_by ;
    public String delete_by ;
    public String delete_at ;
    public String modified_at ;
    public String modified_by ;
    
    public int id;
   
    public Produk(){}
    
    public Produk(String nama , String satuan, int jumlah, int harga , int min_stok,String Create_by ,String Create_At , 
            String modified_by , String modified_at , String delete_at , String delete_by ,int Id, String  gambar )
    {
        this.gambar= gambar;
        this.Create_at = Create_At ;
        this.Create_by= Create_by ;
        this.delete_at = delete_at;
        this.delete_by = delete_by ;
        this.modified_at = modified_at ;
        this.modified_by = modified_by ;
        
        this.harga= harga ; 
        this.jumlah= jumlah ;
        this.min_stok = min_stok ; 
        this.nama = nama  ; 
        this.satuan = satuan ; 
        this.id= Id ;
    }
    public void setGambar(String gambar )
    {
        this.gambar = gambar ;
    }
    public String  getGambar()
    {
        return gambar ;
    }
    public void setId(int idProduk)
    {
        this.id = idProduk ;
    }
    public int getIdProduk()
    {
        return id ;
    }
    public void setCreate_By (String create_By)
    {
        this.Create_by = create_By ;
    }
    public String getCreate_by()
    {
        return Create_by ;
    }
    public void setModified_at(String modified_at )
    {
        this.modified_at = modified_at;
    }
    public String getModifie_at()
    {
        return modified_at;
    }
    public void setModified_by (String modified_by)
    {
        this.modified_by =modified_by;
    }
    public String getModified_at()
    {
        return modified_by ;
    }
    public String getcREATE_aT()
    {
        return  Create_at ;
    }
    public void setCreate_AT(String  create_at)
    {
        this.Create_at = create_at ;
    }
    public void setDelete_at(String delete_at)
    {
        this.delete_at = delete_at;
    }
    
    public void setHarga(int Harga)
    {
        this.harga = Harga ; 
    }
    
    public int getHarga()
    {
        return harga ; 
    }
    public void setJumlah(int jumlah )
    {
        this.jumlah = jumlah ; 
    }
    public int getJumlah ()
    {
        return jumlah ; 
    }
    public void setMin_Stok (int min_stok)
    {
        this.min_stok= min_stok ; 
    }
    public int getMin_stok()
    {
        return min_stok ;
    }
    
    public void setNama(String nama)
    {
        this.nama = nama ;
        
    }
    public String getNama()
    {
       
        return nama ;
    }
    
    public void setSatuan(String satuan)
    {
        this.satuan= satuan ;
    }
    public String getSatuan()
    {
        return satuan ;
    }
    
    
    
    
    
    
      
    
}
