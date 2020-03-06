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
import static sun.misc.MessageUtils.where;

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


        String sql = "insert into Pegawai(NAMA ,Tanggal_Lahir ,ROLE  ,TELP, ALAMAT , USERNAME, PASSWORD,CREATED_BY)"
        +"values('"+P.getNamaPegawai()+"','"+P.getTglLahir()+"','"+P.getRole()+"','"+P.getNoHp()+"','"+P.getAlamat()+"','"+P.getUserName()+"','"+P.getPassword()+"','"+"ADMIN"+"')";
                 
      
        System.out.println("Adding Pegawai..");
        
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
    
    
    public void deletePegawai(String cari)
    {
                String sql="UPDATE  FROM PEGAWAI SET AKTIF=0 where Nama_Pengguna='"+cari+"'";

                  System.out.println("DELETED PEGAWAI...");
                  try
                  {
                      Statement stm = con.createStatement();
                      int result = stm.executeUpdate(sql);
                      System.out.println("added"+result+"Deleted Pegawai");
                      stm.close();
                      
                  }catch(Exception e)
                  {
                      System.out.println("Error Deleted");
                      System.out.println(e);
                  }
      }
    
  
    public Pegawai searchPegawai (String userName){
        String sql="SELECT * FROM PEGAWAI where USERNAME= '"+userName+"'";
        System.out.println("Mencari user Name Pegawai . . .");
        
        Pegawai pgw = null;
        try{
            Statement statement = con.createStatement();
            ResultSet rs=statement.executeQuery(sql);
            if(rs!=null){
                while(rs.next()){
                    
                    pgw = new Pegawai (rs.getString("nama"),rs.getString("Tanggal_Lahir"),rs.getString("role"),
                    Integer.parseInt(rs.getString("TELP")),rs.getString("alamat"),rs.getString("USERNAME"),rs.getString("password"));
                }
            }
            rs.close();
            statement.close();

        }
        catch(Exception Ex){
            System.out.println("Error reading database information...\n");
            System.out.println(Ex);
        }
         return pgw;
    }
}
