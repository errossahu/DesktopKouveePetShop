/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import static DAO.AdminDao.con;
import static DAO.CsDAO.con;
import Model.TransaksiLayanan;
import Model.TransaksiProduk;
import Model.dataHewan;
import Model.detailTransaksiLayanan;
import Model.detailTransaksiProduk;
import Session.LoginSession;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.time.LocalDateTime;
import static java.time.LocalDateTime.now;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author ACER
 */
public class KasirDAO {
    public static Connection con;
    public static Statement stm;
public  DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
public  LocalDateTime now = LocalDateTime.now();  

    public Connection GETcon()
        {
            return con ;
        }
public void makeConnection(){
try {
    String url ="jdbc:mysql://localhost/db9393";
    String user="root";
    String pass="";

    Class.forName("com.mysql.jdbc.Driver");
    con =DriverManager.getConnection(url,user,pass);

        System.out.println("koneksi berhasil;");
    } catch (Exception e) {
        System.err.println("koneksi gagal" +e.getMessage());
    }
}
public void tambahLayanan(detailTransaksiLayanan tp)
{
    String sql= "Insert into detail_transaksi_layanan (id_transaksi_layanan,id_harga_layanan,jumlah, total_harga, created_by, created_at)"
            +"values('"+tp.getIdTransaksi()+"','"+tp.getId_hargaLayanan()+"','"+tp.getJumlah()+"','"+tp.getTotalHarga()+"','"+LoginSession.getNama()+"','"+dtf.format(now)+"')";
      System.out.println("Tambah Produk ");
    try {
        Statement stm = con.createStatement() ;
        int Rs = stm.executeUpdate(sql);
        stm.close();
        System.out.println(Rs);
        
    } catch (Exception e) {
        System.out.println(e);
        System.out.println("Gagal Menambahkan hewan ");
    }
}
public void tambahProduk(detailTransaksiProduk th)
{
    String sql ="Insert into detail_Transaksi_Produk (ID_TRANSAKSI_PRODUK,ID_PRODUK,JUMLAH,TOTAL_HARGA,CREATED_BY,CREATED_AT)"
            +"values('"+th.getIdTransaksi()+"','"+th.getId_Produk()+"','"
            +th.getJumlah()+"','"+th.getTotalHarga()+"','"+LoginSession.getNama()+"','"+dtf.format(now)+"')";
    System.out.println("Tambah Produk ");
    try {
        Statement stm = con.createStatement() ;
        int Rs = stm.executeUpdate(sql);
        stm.close();
        System.out.println(Rs);
        
    } catch (Exception e) {
        System.out.println(e);
        System.out.println("Gagal Menambahkan hewan ");
    }
}
public void updatePembayaranLayanan(TransaksiLayanan Tl , String id )
{
    String sql ="Update Transaksi_Layanan set Status= 'Lunas', tanggal_Lunas='"+dtf.format(now)+"',"
            +"id_kasir= '"+Tl.getIdKasir()+"',"
            +"subtotal = '"+Tl.getSubTotal()+"',"
            +"Diskon = '"+Tl.getDiskon()+"',"
            +"Total = '"+Tl.getTotal()+"',"
            +"Progress='Layanan Selesai' where id_transaksi_layanan='"+id+"'";
    System.out.println("Update layanan");
    try {
        Statement stm = con.createStatement();
        int rs = stm.executeUpdate(sql);
        System.out.println(rs);
        
    } catch (Exception e) {
        
        System.out.println("gagal Edit Produk");
        System.out.println(e);
    }
}
public void updateTrasaksi(TransaksiProduk tp,String id )
{
     
//    String sql="Update detail_transaksi_produk  set  modified_by='"+LoginSession.getNama()+"',"
//            +"modified_at ='"+dtf.format(now)+"',"
//            +"id_produk='"+th.getId_Produk()+"',"
//            +"jumlah='"+th.getJumlah()+"',"
//            +"total_harga='"+th.getTotalHarga()+"'"
//            +"where id_detail_transaksi_produk='"+id+"'";
//    
    String sql ="Update transaksi_produk set STATUS= 'LUNAS',Tanggal_Lunas='"+dtf.format(now)+"',"
                +"id_kasir= '"+tp.getIdKasir()+"',"
                +"SUBTOTAL ='"+tp.getSubTotal()+"',"
                +"Diskon ='"+tp.getDiskon()+"',"
                +"TOTAL ='"+tp.getTotal()+"'"
                +"where id_transaksi_produk= '"+id+"'";
    System.out.println("Edit Produk...");
    try {
        Statement stm = con.createStatement();
        int rs = stm.executeUpdate(sql);
        System.out.println(rs);
        
    } catch (Exception e) {
        
        System.out.println("gagal Edit Produk");
        System.out.println(e);
    }
}
public void updateLayanan(detailTransaksiLayanan tp,String id)
{

    
    String sql="Update detail_transaksi_LAYANAN  set  modified_by='"+LoginSession.getNama()+"',"
            +"modified_at ='"+dtf.format(now)+"',"
            +"id_HARGA_LAYANAN='"+tp.getId_hargaLayanan()+"',"
            +"jumlah='"+tp.getJumlah()+"',"
            +"total_harga='"+tp.getTotalHarga()+"'"
            +"where id_detail_transaksi_LAYANAN='"+id+"'";
    
    
    System.out.println("Edit Produk...");
    try {
        Statement stm = con.createStatement();
        int rs = stm.executeUpdate(sql);
        System.out.println(rs);
        
    } catch (Exception e) {
        
        System.out.println("gagal Edit Produk");
        System.out.println(e);
    }
}
public void updateProduk(detailTransaksiProduk th,String id)
{

    
    String sql="Update detail_transaksi_produk  set  modified_by='"+LoginSession.getNama()+"',"
            +"modified_at ='"+dtf.format(now)+"',"
            +"id_produk='"+th.getId_Produk()+"',"
            +"jumlah='"+th.getJumlah()+"',"
            +"total_harga='"+th.getTotalHarga()+"'"
            +"where id_detail_transaksi_produk='"+id+"'";
    
    
    System.out.println("Edit Produk...");
    try {
        Statement stm = con.createStatement();
        int rs = stm.executeUpdate(sql);
        System.out.println(rs);
        
    } catch (Exception e) {
        
        System.out.println("gagal Edit Produk");
        System.out.println(e);
    }
}
public void closeConnection()
{
    System.out.println("Closing database. . . .");
    try
    {
        con.close();
        System.out.println("Success...\n");
    }catch (Exception e)
    {
        System.out.println("Error closing the database. . .");
        System.out.println(e);
    }
}
public void deleteLayanan(String id)
{
    String sql = "Delete from detail_transaksi_layanan where id_detail_transaksi_layanan='"+id+"'";
        System.out.println("Hewan dihapus..");
        
        try
        {
            Statement statement = con.createStatement();
            int result = statement.executeUpdate(sql);
            System.out.println("Hapus " + result+ " Admin\n");
            statement.close();
        }
        catch(Exception e)
        {
            System.out.println("Gagal Hapus Admin..");
            System.out.println(e);
        }
   
    
}
public void deleteProduk(String id )
   {
           String sql = "DELETE FROM detail_transaksi_produk where id_detail_transaksi_produk = '"+id+"'";
        System.out.println("Hewan dihapus..");
        
        try
        {
            Statement statement = con.createStatement();
            int result = statement.executeUpdate(sql);
            System.out.println("Hapus " + result+ " Admin\n");
            statement.close();
        }
        catch(Exception e)
        {
            System.out.println("Gagal Hapus Admin..");
            System.out.println(e);
        }
   }
}
