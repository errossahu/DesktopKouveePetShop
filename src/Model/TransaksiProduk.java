/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;

/**
 *
 * @author ACER
 */
public class TransaksiProduk {
//  
//Full texts	
//ID_TRANSAKSI_PRODUK
//ID_CUSTOMER_SERVICE
//ID_KASIR
//ID_HEWAN
//SUBTOTAL
//DISKON
//TOTAL
//STATUS
//TANGGAL_LUNAS
//CREATED_AT
//CREATED_BY
//MODIFIED_AT
//MODIFIED_BY
   private String id_transaksi_produk ;
   private String  id_customer_service ;
   private String id_kasir ;
   private String id_hewan ; 
   private int subTotal ;
   private int diskon ;
   private int total ;
   private String status ;
   private String tglLunas ;
   private String created_At ;
   private String created_By ;
   private String modified_by ;
   private String modified_at ;
    
    
   public TransaksiProduk()
   {
       
   }
   public TransaksiProduk(String id_transaksi , String idCs , String idKasir , String idHewan ,int subTotal , int diskon ,
   int total ,  String status , String tglLunas , String created_at , String created_by , String modified_by ,String modified_At)
   {
       this.total = total;
       this.id_transaksi_produk= id_transaksi;
       this.id_customer_service = idCs ;
       this.id_kasir =idKasir ;
       this.id_hewan = idHewan ;
       this.subTotal = subTotal ;
       this.diskon= diskon ;
       this.status = status;
       this.tglLunas = tglLunas ; 
       this.created_By = created_by ; 
       this.created_At = created_at ;
       this.modified_by = modified_by ; 
       this.modified_at = modified_At ;
   }
   public void setTotal(int a)
   {
       this.total = a ;
   }
   public int getTotal()
   {
       return total ;
   }
   public void setModified_by(String a)
   {
       this.modified_by = a ;
   }
   public String getModif_by()
   {
       return modified_by ;
   }
   public void setModified_At(String a)
   {
       this.modified_at = a ;
   }
   public String getModified_At ()
   {
       return modified_at ;
   }
   public void setCreated_by(String a)
   {
       this.created_By = a ;
   }
   public String getCreated_BY()
   {
       return created_By ;
   }
   public void setcreated_At(String a )
   {
       this.created_At = a ;
   }
   public String geteCreated_at()
   {
       return created_At;
   }
   public void setTglLunas(String a)
   {
       this.tglLunas = a ;
   }
   public String getTglLunas()
   {
       return tglLunas ;
   }
   public void setStatus(String a)
   {
       this.status = a ;
   }
  
   public String getStatus()
   {
       return status;
   }
   public void setDiskon(int a)
   {
       this.diskon = a;
       
   }
   public int getDiskon()
   {
       return diskon;
   }
   
   public void setSubTotal(int a)
   {
       this.subTotal = a ;
   }
   public int getSubTotal()
   {
       return subTotal ;
   }
   public void setIdHewan(String a)
   {
       this.id_hewan = a ;
   }
   public String getIdHewan()
   {
       return id_hewan;
   }
   public void setIdKasir(String a)
   {
       this.id_kasir = a;
   }
   public String getIdKasir()
   {
       return id_kasir ;
   }
   public void setIdCs(String a )
   {
       this.id_customer_service= a;
   }
   public String getIDcs()
   {
       return id_customer_service;
   }
   
   public void setIdTrasaksaksiProduk(String a)
   {
       this.id_transaksi_produk = a;
   }
   public String getIdTrasaksiProduk()
   {
       return id_transaksi_produk;
   }
   
   
   
}
