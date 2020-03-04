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
    
    
    
    public Produk(String nama , String satuan, int jumlah, int harga , int min_stok )
    {
        this.harga= harga ; 
        this.jumlah= jumlah ;
        this.min_stok = min_stok ; 
        this.nama = nama  ; 
        this.satuan = satuan ; 
        
    }
    public void setHarga(int Harga)
    {
        this.harga = harga ; 
    }
    
    public int getHarga()
    {
        return harga ; 
    }
    public void setJumlah(int jumlah )
    {
        this.jumlah = jumlah ; 
    }
    public int getJumlah (int jumlah )
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
    public String setNama()
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
