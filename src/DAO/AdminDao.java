/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;
import Model.Pegawai;
import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import org.omg.CORBA.PUBLIC_MEMBER;

/**
 *
 * @author ACER
 */

public class AdminDao {
    public static Connection con;
    public static Statement stm;
    
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
  
    
    public void tambahPegawai(Pegawai P)
    {

        String sql = "insert into Pegawai(NAMA , alamat , role ,noHp, alamat , userName, password)"
                +"values('"+P.getNamaPegawai()+"','"+P.getAlamat()+"','"+P.getRole()+"','"+P.getAlamat()+"','"+P.getUserName()+"','"+P.getPassword()+"')";
                 
      
        System.out.println("Adding Admin..");
        
        try
        {
            Statement statement = con.createStatement();
            int result = statement.executeUpdate(sql);
            System.out.println("Added" + result + "Pegawai\n");
            statement.close();
        }
        catch(Exception e)
        {
            System.out.println("Error adding Pegawai..");
            System.out.println(e);
        }
    }
    
    
    public void deletePegawai(Pegawai p )
    {
        
    }
    
}
