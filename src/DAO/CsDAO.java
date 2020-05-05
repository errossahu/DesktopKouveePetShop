/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import static DAO.AdminDao.con;
import Model.JenisHewan;
import Model.Layanan;
import Model.Pelanggan;
import Model.Produk;
import Model.dataHewan;
import Session.LoginSession;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ACER
 */
public class CsDAO {
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

/////Tambah Data/////////
public void tambahPelanggan(Pelanggan P)
{
    String sql = "Insert into PELANGGAN (id_pelanggan,NAMA,ALAMAT,TANGGAL_LAHIR, TELP, CREATED_AT, CREATED_BY)"
     +"Values('"+P.getId_pelanggan()+"','"+P.getNama()+"','"+P.getAlamat()+"','"
            +P.getTglLahir()+"','" +P.getTelp()+"','"
            +dtf.format(now)+"','"+LoginSession.getNama()+"')";
    System.out.println("Menambah Pegawai");
    try
    {
        Statement stm = con.createStatement();
        int Rs = stm.executeUpdate(sql);
        stm.close();
        System.out.println(Rs);
    }catch(Exception e)
    {
        System.out.println(e);
    }

}
public void editHewan(dataHewan dh  , String nama)
{
//    
//   String sql ="UPDATE Pelanggan set modified_by='"+LoginSession.getNama()+"',"
//           +"modified_at ='"+dtf.format(now)+"' ,"
//           +"nama ='"+P.getNama()+"',"
//           +"Alamat ='"+P.getAlamat()+"',"
//           +"Tanggal_Lahir='"+P.getTglLahir()+"',"
//           +"telp='"+P.getTelp()+"'"
//           +"where nama='"+nama+"'";
    String sql = "update hewan set modified_by = '"+LoginSession.getNama()+"',"
            +"modified_At='"+dtf.format(now)+"',"
            +"id_pelanggan  = '"+dh.getIdPelanggan()+"',"
            +"id_jenis_hewan ='"+dh.getIDjENIShewan()+"',"
            +"nama = '"+dh.getNamaHewan()+"'"
            +"where nama= '"+nama+"'";
            System.out.println("edit Hewan");
            
            try {
                Statement stm = con.createStatement();
                int rs = stm.executeUpdate(sql);
                System.out.println(rs);

            } catch (Exception e) {

                System.out.println("gagal Edit Produk");
                System.out.println(e);
            }
}
public void tambahHewan(dataHewan dh )
{
    String sql ="Insert into Hewan (ID_PELANGGAN , ID_JENIS_HEWAN , NAMA , TANGGAL_Lahir , created_AT , created_by)"
            +"values('"+dh.getIdPelanggan()+"','"+dh.getIDjENIShewan()+"','"+dh.getNamaHewan()+"','"
            +dh.getTgl()+"','"+dtf.format(now)+"','"+LoginSession.getNama()+"')";
    System.out.println("Tambah HEWAN ");
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
//////////////////////HAPUS////////////////////////
public void hapusHewan(String cari )
{
    String sql = "Update Hewan set AKTIF=0,Delete_by ='"+LoginSession.getNama()+"',"
                  +"Delete_at = '"+dtf.format(now)+"'"
                  +"where nama ='"+cari+"'";
    try
    {
        Statement stm = con.createStatement() ;
        int Result = stm.executeUpdate(sql);
        System.out.println(Result);
    }
    catch(Exception e)
    {
        System.out.println("Gagal Menghapus");
        System.out.println(e);
    }                 
}
public void hapusPelanggan(String  cari)
{
    String sql = "update pelanggan set aktif= 0,Delete_by ='"+LoginSession.getNama()
            +"',Delete_AT = '"+dtf.format(now) +"'where nama = '"+cari+"'";
    System.out.println("Mencari id");
    try
    {
        Statement stm = con.createStatement() ;
        int Result = stm.executeUpdate(sql);
        System.out.println(Result);
    }
    catch(Exception e)
    {
        System.out.println("Gagal Menghapus");
        System.out.println(e);
    }
}
///////////////CARI DATA ////////////////
public Pelanggan searchPelangganPakaiNama(String nama)
{
String sql = "Select ID_PELANGGAN from Pelanggan where AKTIF=1 and nama= '"+nama+"'";
    System.out.println("Mencari Nama Pelanggan .. ");
    Pelanggan p = null ;
    try
    {
        Statement stm = con.createStatement() ;
        ResultSet rs = stm.executeQuery(sql);
        if(rs!=null)
        {
            while(rs.next())
            {

                p = new Pelanggan(Integer.parseInt(rs.getString("ID_PELANGGAN")));
            }
            
        }
        
        rs.close();
        stm.close();

    }
    catch (Exception e)
    {
        System.out.println(e);
        System.out.println(" Gagal ");
    }
    return p ;
}
///////////////////Edit Pelanggan..............
public void editPelangggan(Pelanggan P , String nama)
{
   String sql ="UPDATE Pelanggan set modified_by='"+LoginSession.getNama()+"',"
           +"modified_at ='"+dtf.format(now)+"' ,"
           +"nama ='"+P.getNama()+"',"
           +"Alamat ='"+P.getAlamat()+"',"
           +"Tanggal_Lahir='"+P.getTglLahir()+"',"
           +"telp='"+P.getTelp()+"'"
           +"where nama='"+nama+"'";
    System.out.println("Edit Produk");
    try {
        Statement stm = con.createStatement();
        int rs = stm.executeUpdate(sql);
        System.out.println(rs);
        
    } catch (Exception e) {
        
        System.out.println("gagal Edit Produk");
        System.out.println(e);
    }
}
public Pelanggan searchPelanggan(String nama)
{
    String sql = "Select nama, alamat, tanggal_lahir,telp from pelanggan where AKTIF=1 and nama= '"+nama+"'";
    System.out.println("Mencari Nama Jenis Hewan .. ");
    Pelanggan p = null ;
    try
    {
        Statement stm = con.createStatement() ;
        ResultSet rs = stm.executeQuery(sql);
        if(rs!=null)
        {
            while(rs.next())
            {

                p = new Pelanggan(rs.getString("nama"), rs.getString("alamat"), rs.getString("tanggal_lahir"), rs.getString("telp"));
            
            }
        }
        rs.close();
        stm.close();
    }
    catch (Exception e)
    {
        System.out.println(e);
        System.out.println(" Gagal ");
    }
    return p ;
}

///////////Tampil Data /////////////

public List<Pelanggan> tampilPelanggan()
{
    String sql = "Select * FROM Pelanggan WHERE AKTIF=1 ";
            List<Pelanggan> list = new ArrayList<>();

    try
    {
        Pelanggan Pr ;
        Statement stm = con.createStatement();
        ResultSet rs = stm.executeQuery(sql);
        if(rs!=null)
        {
            while(rs.next())
            {
                Pr = new Pelanggan(Integer.parseInt(rs.getString("id_pelanggan")),rs.getString("nama"), rs.getString("Alamat"), rs.getString("tanggal_lahir"), rs.getString("telp"),rs.getString("created_at"),rs.getString("CREATED_bY"),rs.getString("modified_by"),rs.getString("modified_At"));
                list.add(Pr);
            }
            
            rs.close();
            stm.close();
        }

    }
    catch(Exception e)
    {
        System.out.println(e);
        System.out.println("Gagal");
    }
        return list ;
}




}
