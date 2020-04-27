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
public class detailTransaksiProduk {
    
    private String id_Detail_Transaksi ;
    private String id_transaksi ;
    private int  id_produk ;
    private int Jumlah ;
    private int totalHarga ;
    private String created_By ;
    private String created_at ;
    private String delete_at  ;
    private String delete_By ;
    private String modified_by ;
    private String modified_at ;
    public detailTransaksiProduk(){}
    public detailTransaksiProduk(String id_detail , String id_trasaksi , int id_produk , int jum , int totalHarga, String created_By ,String created_at,String modified_by, String modified_at)
    {
        this.id_Detail_Transaksi = id_detail ;
        this.id_transaksi = id_trasaksi ;
        this.id_produk = id_produk ;
        this.Jumlah = jum ;
        this.totalHarga = totalHarga ;
        this.created_By = created_By ;
        this.created_at = created_at ;
        this.modified_at = modified_at ;
        this.modified_by = modified_by ;
        
    }
    public void setIdDetailTransaksi(String id )
    {
        this.id_Detail_Transaksi = id ;
    }
    public String  getIdDetailTrasaksi()
    {
        return id_Detail_Transaksi ;
                
    }
    public void setIdTransaksi(String id )
    {
        this.id_transaksi= id;
    }
    public String getIdTransaksi()
    {
        return id_transaksi;
    }
    public  void setId_Produk(int id)
    {
        this.id_produk = id ;
    }
    public int  getId_Produk ()
    {
        return id_produk ;
    }
    public void setJumlah(int jum)
    {
        this.Jumlah = jum ;
    }
    public int getJumlah()
    {
        return Jumlah ;
    }
    public void setTotalHarga(int Harga )
    {
        this.totalHarga = Harga ;
    }
    public int getTotalHarga()
    {
        return totalHarga ;
    }
    public void setCreatedBy(String created)
    {
        this.created_By = created ;
    }
    public String getCreatedBy()
    {
        return created_By ;
    }
    public void setCreatedAt(String created)
    {
        this.created_at = created ;
    }
    public String getCreatedAt()
    {
        return created_at ;
    }
    public void setModified_At (String modif)
    {
        this.modified_at = modif ;
    }
    public String getModified_At()
    {
        return modified_at ;
    }
    public void setModified_by(String modified_by)
    {
        this.modified_by = modified_by ;
    }
    public String getModified_by()
    {
        return modified_at;
    }
//            	
//ID_DETAIL_TRANSAKSI_PRODUK
//ID_TRANSAKSI_PRODUK
//ID_PRODUK
//JUMLAH
//TOTAL_HARGA
//CREATED_AT
//CREATED_BY
//MODIFIED_AT
//MODIFIED_BY
    
}
