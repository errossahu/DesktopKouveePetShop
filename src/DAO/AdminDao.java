/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package DAO;
import Model.Layanan;
import Model.Pegawai;
import Model.Suplier;
import java.sql.Connection;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;
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
public  DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
public  LocalDateTime now = LocalDateTime.now();  
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
    String sql = "insert into Layanan(nama,CREATED_BY,CREATED_AT)"
            +"values('"+L.getNamaLayanan()+"','"+"ADMIN"+"','"+dtf.format(now)+"')";
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

public void tambahSuplier(Suplier S)
{
    String sql = "Insert INTO SUPPLIER(NAMA ,ALAMAT , TELP , CREATED_AT, CREATED_BY)"
            +"values('"+S.getNama()+"','"+S.getAlamat()+"','"+S.getTelp()+"','"+dtf.format(now)+"','"+
            "ADMIN"+"')";
    System.out.println("ADDING SUPPLIER");
    
    try
    {
        Statement stm = con.createStatement();
        int Result = stm.executeUpdate(sql);
        System.out.println("Added"+Result+"Pegawai\n");
        stm.close();
             
        
    }catch(Exception e)
    {
        System.out.println("Error Adding Supplier ");
        System.out.println(e);
    }
    
}
public void tambahPegawai(Pegawai P)
{


    String sql = "insert into Pegawai(NAMA ,Tanggal_Lahir ,ROLE  ,TELP, ALAMAT , USERNAME, PASSWORD,CREATED_BY,CREATED_AT)"
    +"values('"+P.getNamaPegawai()+"','"+P.getTglLahir()+"','"+P.getRole()+"','"+P.getNoHp()+"','"+P.getAlamat()+"','"+P.getUserName()+"','"+P.getPassword()+"','"+"ADMIN"+"','"+dtf.format(now)+"')";


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
   public Pegawai getPegawai (){
        String sql="SELECT * FROM PEGAWAI";
        System.out.println("Searching Nama Pengguna . . .");
        
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
///////////////////DELETE //////////////////////////
public void deletePegawai(String cari)
{
            String sql="UPDATE   PEGAWAI SET AKTIF=0, DELETE_BY='ADMIN' , DELETE_AT='"+dtf.format(now)+"' where USERNAME='"+cari+"'";

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
public void deleteLayanan(String Cari)
{
    String sql = "UPDATE LAYANAN SET AKTIF=0, DELETE_BY='ADMIN' , DELETE_AT='"+dtf.format(now)+"' WHERE  NAMA='"+Cari+"'";
    System.out.println("Deleted Layanan....");
    try
    {
        Statement stm = con.createStatement() ;
        int result= stm.executeUpdate(sql);
        System.out.println("Added "+result +"Deleted Layanan");
        stm.close();
    }catch(Exception e)
    {
        System.out.println("Deleted Pegawai ");
        System.out.println(e);
    }
}
public void deleteSuplier(String cari )
{
    String sql = "Update Supplier set AKTIF=0 , DELETE_BY='ADMIN',DELETE_AT='"+dtf.format(now)+"' WHERE  NAMA='"+cari+"'";
   try
   {
        Statement stm = con.createStatement();
        int result =stm.executeUpdate(sql);
        System.out.println("DELETE SUP "+result);
   }
   catch(Exception e)
   {
       System.out.println("Gagal Hapus Suplier");
       System.out.println(e);
   }
    
}

/////////////////////////////////SEARCH///////////////////////////////////////
public Suplier searchSupplier(String namaSupplier)
{
    String sql= "Select *from SUPPLIER where AKTIF=1 and nama='"+namaSupplier+"'";
    System.out.println("Mencari Nama Suppler");
    Suplier sp = null ;
    try
    {
        
        Statement stm = con.createStatement();
        ResultSet result = stm.executeQuery(sql);
        if(result!=null)
        {
            while(result.next())
            {
//                  public Suplier(String nama , String alamat , String telp , String create_by,String create_at ,String Modified_by , 
//            String Modifie_at , String delete_by , String delete_at)
                
                sp = new Suplier(result.getString("NAmA"),result.getString("ALAMAT"),result.getString("TELP"),
                result.getString("CREATED_BY"),result.getString("CREATED_AT"),result.getString("MODIFIED_BY"),
                result.getString("MODIFIED_AT"),result.getString("DELETE_BY"),result.getString("DELETE_AT"),Integer.parseInt(result.getString("ID_SUPPLIER")));
            }
            result.close();
            stm.close();
        }
        
    }
    catch(Exception e)
    {
        System.out.println("Gagal Mencari Database");
        System.out.println(e);
    }
    return sp ;
}
public Layanan searchLayanan(String namaLayanan)
{
    String sql = "Select * from Layanan WHERE AKTIF=1 AND  nama = '"+namaLayanan+"'";
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

                lyn = new Layanan(rs.getString("Nama"),Integer.parseInt(rs.getString("ID_LAYANAN")),rs.getString("CREATED_BY"),
                rs.getString("DELETE_BY"),rs.getString("delete_at"),rs.getString("Created_at"),rs.getString("MODIFIED_BY"),rs.getString("MODIFIED_AT"));


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

    System.out.println("Mencari user Name Pegawai . . .");

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

public List<Suplier> tampilSuplier()
{
    String sql = "Select * from SUPPLIER WHERE AKTIF=1";
    System.out.println("Daftar Supplier");
    Suplier s ;
    List<Suplier> sp = new ArrayList<>();
    try
    {
        Statement st = con.createStatement() ;
        ResultSet result = st.executeQuery(sql);
        if(result!=null)
        {
            while(result.next())
            {
                 s = new Suplier(result.getString("NAmA"),result.getString("ALAMAT"),result.getString("TELP"),
                result.getString("CREATED_BY"),result.getString("CREATED_AT"),result.getString("MODIFIED_BY"),
                result.getString("MODIFIED_AT"),result.getString("DELETE_BY"),result.getString("DELETE_AT"),Integer.parseInt(result.getString("ID_SUPPLIER")));
                 
                 sp.add(s);
            }
             result.close();
             stm.close();
            
        }
        
    }catch(Exception e)
    {
        System.out.println(e);
        System.out.println("Gagal Tampil");
    }
    return sp ;
}
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

public List<Layanan> TampilLayanan()
{

    List <Layanan> list = new ArrayList<>();
    return list ;
}

////////////////////////////////EDIT /////////////////
    public void editPegawai(Pegawai P,String userName)
{

        String sql = "UPDATE Pegawai set MODIFIED_BY='ADMIN',"
                +"',Alamat = '"+P.getAlamat()
                +"',Tanggal_Lahir = '"+P.getTglLahir()
                +"',TELP = '"+P.getNoHp()
                +"',USERNAME='"+P.getUserName()
                +"',ROLE ='"+P.getRole()
                +"'NAMA = '"+P.getNamaPegawai()
                +"',MODIFIED_AT='"+dtf.format(now)+"'where userName = '"+userName+"'";



        try
        {
            Statement s= con.createStatement() ;
            int Result = s.executeUpdate(sql);
            System.out.println("Data Updated " +Result+ "\n");

        }catch (Exception e)
        {
            System.out.println("Gagal Updated ");
            System.out.println(e);
        }
}
    public void editLayanan(Layanan L , String namaLayanan)
    {
//                      String sql ="UPDATE Layanan set MODIFIED_BY='ADMIN',"
//                              + ",Nama = '"+L.getNamaLayanan()
//                              +",Modified_at='"
                      
    }

}
