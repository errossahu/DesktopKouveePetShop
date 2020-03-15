/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import static DAO.AdminDao.con;
import Model.JenisHewan;
import Model.Pelanggan;
import Model.Produk;
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
//////////////////////HAPUS////////////////////////
public void hapusPelanggan(int cari)
{
    String sql = "update pelanggan set aktif= 0,Delete_by ='"+LoginSession.getNama()
            +"',Delete_AT = '"+dtf.format(now) +"'where id_pelanggan = '"+cari+"'";
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
public Pelanggan searchPelanggan(int id)
{
    String sql = "Select nama, alamat, tanggal_lahir,telp from pelanggan where AKTIF=1 and id_pelanggan= '"+id+"'";
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
    String sql = "Select id_pelanggan, NAMA,Alamat, Tanggal_lahir, Telp  FROM Pelanggan WHERE AKTIF=1 ";
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
                Pr = new Pelanggan(Integer.parseInt(rs.getString("id_pelanggan")),rs.getString("nama"), rs.getString("alamat"), rs.getString("tanggal_lahir"), rs.getString("telp"));
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
