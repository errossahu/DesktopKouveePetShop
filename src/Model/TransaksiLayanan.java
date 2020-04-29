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
public class TransaksiLayanan {
//  
//    ID_TRANSAKSI_LAYANAN	ID_CUSTOMER_SERVICE	ID_KASIR	ID_HEWAN	SUBTOTAL	DISKON	TOTAL	PROGRESS	STATUS	TANGGAL_LUNAS	CREATED_AT	CREATED_BY	MODIFIED_AT	MODIFIED_BY	
    private String id_transaksiLayanan ;
    private int id_CS ;
    private int id_Kasir ;
    private int id_hewan ;
    private int  subtotal ;
    private int  diskon ;
    private int total ;
    private String status ;
    private String progress ;
    private String tglLunas ;
    private String createBy ;
    private String Create_AT ;
    private String Modified_By ;
    private String modified_at ;
    public TransaksiLayanan()
    {
        
    }
    public TransaksiLayanan(String id_transaksi, int idcs , int idkasir , int id_hewan , int subtotal , int diskon , String status , 
            String progress, String tglLunas , String created_by, String created_At , String modified_By , String modifid_at,int total)
    {
        this.total =total ;
        this.id_transaksiLayanan = id_transaksi ;
        this.id_CS = idcs ;
        this.id_Kasir = idkasir ;
        this.id_hewan = id_hewan ;
        this.subtotal = subtotal ;
        this.diskon = diskon ;
        this.status = status;
        this.progress = progress ;
        this.tglLunas = tglLunas ;
        this.createBy = created_by ;
        this.Create_AT =created_At ;
        this.Modified_By = modified_By ;
        this.modified_at = modifid_at ;
    }
    public void setTotal(int a)
    {
        this.total =a;
    }
    public int getTotal()
    {
        return total ;
    }
    public void setProgress(String a)
    {
        this.progress = a;
    }
    public String getProgress()
    {
        return progress ;
    }
    public void setTglLunas(String a)
    {
        this.tglLunas = a;
    }
    public String getTglLunas()
    {
        return tglLunas ;
    }
    public void setCreted_by(String a)
    {
        this.createBy= a;
    }
    public String getCreatedbY()
    {
        return createBy ;
    }
    public void setCreated_at (String a)
    {
        this.Create_AT = a;
    }
    public String getCreated_At()
    {
        return Create_AT ;
    }
    public void modified_by(String a)
    {
        this.Modified_By =  a;
    }
    public String getmodified_by ()
    {
        return Modified_By;
    }
    public void setModified_at(String a)
    {
        this.modified_at = a;
    }
    public String getModifiedBy()
    {
        return modified_at ;
    }
    public void setDiskon(int diskon )
    {
        this.diskon =diskon ;
    }
    public int getDiskon()
    {
        return diskon ;
    }
    public void  setStatus(String a )
    {
        this.status = a ;
    }
    public String getStatus()
    {
        return status ;
    }
    public void setSubTotal(int subTotal)
    {
        this.subtotal = subTotal ;
    }
    public int getSubTotal()
    {
        return subtotal ;
    }
    public void setIdKasir(int idKasir)
    {
        this.id_Kasir = idKasir ;
    }
    public int getIdKasir()
    {
        return id_Kasir;
    }
    public void setIdCs (int a)
    {
        this.id_CS = a ;
    }
    public int getIdCs()
    {
        return id_CS ;
    }
    
    public void setIdTransaksiLayanan(String a)
    {
        this.id_transaksiLayanan = a;
    }
    public String getIdTransaksiLayanan()
    {
        return id_transaksiLayanan ;
    }
    
            
    
}
