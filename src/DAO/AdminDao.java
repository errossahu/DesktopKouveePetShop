/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;
import Model.Layanan;
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
  
    public void tambahLayanan(Layanan L)
    {
        String sql = "insert into Layanan(nama,CREATED_BY)"
                +"values('"+L.getNamaLayanan()+"','"+"ADMIN"+"')";
           System.out.println("Adding Layanan ..");
           
           try
           {
               Statement stm= con.createStatement();
               int Result = stm.executeUpdate(sql);
               System.out.println("Added" +Result+"Layanan \n");
               stm.close();
           }
           catch (Exception e)
           {
               System.out.println("Gagal Menambahkan Layanan");
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
    public Layanan searchLayanan(String namaLayanan)
    {
        String sql = "Select * from Layanan where nama = '"+namaLayanan+"'";
        System.out.println("Mencari Nama Layanan ");
        Layanan lyn = null ; 
        try
        {
            Statement stm = con.createStatement() ;
            ResultSet rs = stm.executeQuery(sql);
            if(rs!=null)
            {
                while(rs.next())
                {
                    lyn = new Layanan(rs.getString("NAMA"));
                    
                }
            }
            rs.close();
            stm.close();
        }
        
        catch(Exception e)
        {
            System.out.println("Error Mencari Layanan \n");
            System.out.println(e);
            
        }
        
        return lyn ;
    }
    public Pegawai searchPegawai (String userName){
        String sql="SELECT * FROM PEGAWAI where AKTIF=1  AND USERNAME= '"+userName+"'" ;

        System.out.println("Me     ncari user Name Pegawai . . .");
        
        Pegawai pgw = null;
        try{
            Statement statement = con.createStatement();
            ResultSet rs=statement.executeQuery(sql);
            if(rs!=null){
                while(rs.next()){
                    
                pgw = new Pegawai (rs.getString("nama"),rs.getString("Tanggal_Lahir"),rs.getString("role"),
                rs.getString("TELP"),rs.getString("alamat"),rs.getString("USERNAME"),rs.getString("password"),Integer.parseInt(rs.getString("id_Pegawai")),
                rs.getString("Created_at"),rs.getString("CREATED_BY"),rs.getString("Modified_at"),rs.getString("Modified_by"),rs.getString("delete_at"),rs.getString("delete_by"),Integer.parseInt(rs.getString("AKTIF")));                   
                   

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
    
    
    
    ///////////////////////LIST PEGAWAI///////////////////////
       public List<Pegawai> TampilPegawai()
    {
        String sql = "SELECT * FROM  PEGAWAI WHERE AKTIF=1";
        System.out.println("Daftar Pegawai. . .");
        Pegawai P ;
        List<Pegawai> list = new ArrayList<>();

        try
        {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            if(rs != null)
            {
                while(rs.next())
                {

                P = new Pegawai (rs.getString("nama"),rs.getString("Tanggal_Lahir"),rs.getString("role"),
                rs.getString("TELP"),rs.getString("alamat"),rs.getString("USERNAME"),rs.getString("password"),Integer.parseInt(rs.getString("id_Pegawai")),
                rs.getString("Created_at"),rs.getString("CREATED_BY"),rs.getString("Modified_at"),rs.getString("Modified_by"),rs.getString("delete_at"),rs.getString("delete_by"),Integer.parseInt(rs.getString("AKTIF")));                   
                   
                    list.add(P);
                    System.out.println(P.getUserName());
                    
                }
            }
            rs.close();
            statement.close();
        }

        catch(Exception e)
        {
            System.out.println("Gagal Membaca Database...\n");
            System.out.println(e);
        }

        return list;
    }
    
    
    
}
