/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Pelanggan;
import Session.LoginSession;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
    String sql = "Insert into PELANGGAN (NAMA,ALAMAT,TANGGAL_LAHIR, TELP, CREATED_AT, CREATED_BY)"
     +"Values('"+P.getNama()+"','"+P.getAlamat()+"','"
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

}
