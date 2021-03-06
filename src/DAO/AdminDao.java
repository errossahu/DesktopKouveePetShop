/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package DAO;
import view.MenuAdmin;
import Model.Harga_Layanan;
import Model.JenisHewan;
import Model.Layanan;
import Model.Pegawai;
import Model.Produk;
import Model.Suplier;
import Model.TransaksiLayanan;
import Model.UkuranHewan;
import java.sql.Blob;
import java.sql.Connection;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import org.omg.CORBA.PUBLIC_MEMBER;


/**
*
* @author ACER
*/

public class AdminDao {
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
        String url ="jdbc:mysql://localhost/smithdev_kouveepetshop";
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

public void tambahUkuranHewan(UkuranHewan UH)
{
    String sql = "Insert into ukuran_hewan(NAMA, Created_by , Created_At)"
            +"values('"+UH.getNama()+"','"+"ADMIN"+"','"+dtf.format(now)+"')";
    System.out.println("Adding Ukuran Hewan");
    try
    {
        Statement stm = con.createStatement() ;
        int Rs = stm.executeUpdate(sql);
        stm.close();
        System.out.println(Rs);
    }catch(Exception e)
    {
        System.out.println(" Gagal ");
        System.out.println(e);
    }
}
public void editHargaLayanan(Harga_Layanan H, int id)
{
    
    String sql= "UPDATE Harga_Layanan SET MODIFIED_BY = 'ADMIN', MODIFIED_AT='"+dtf.format(now)+"',"
                +"ID_layanan ='"+H.getIdLayanan()+"',"
                +"ID_UKURAN_HEWAN ='"+H.getIdUkuran()+"',"
                +"HARGA ='"+H.getHarga()+"'"
                +"WHERE ID_HARGA_LAYANAN='"+id+"'";
    
    try
    {
        Statement stm = con.createStatement();
        int Result = stm.executeUpdate(sql);
        System.out.println("Edit Harga Layanan");
        System.out.println(Result);
    
    }
    catch(Exception e)
    {
        System.out.println("Edit Harga Layanan");
        System.out.println(e);
    }
}
public void tambahHargaLayanan(Harga_Layanan H)
{
    String sql= "Insert Into Harga_Layanan (Id_Layanan, Id_Ukuran_Hewan,Harga, Created_At , Created_by)"
            +"Values('"+H.getIdLayanan()+"','"+H.getIdUkuran()+"','"+H.getHarga()+"','"+dtf.format(now)+"','"+"Admin"+"')";
    System.out.println(sql);
    try
    {
        Statement stm = con.createStatement();
        int Result = stm.executeUpdate(sql);
        System.out.println(Result);
        stm.close();
    }
    catch(Exception e)
    {
        System.out.println("Gagal ");
        System.out.println(e);
    }
    
}
public void tambahJenisHewan(JenisHewan JH)
{
    String sql = "Insert Into jenis_hewan(Nama, Created_by ,Created_at)"
            +"Values('"+JH.getnNama()+"','"+"ADMIN"+"','"+dtf.format(now)+"')";
    System.out.println("Adding Layanan.. ");
    try
    {
        Statement stm =  con.createStatement() ;
        int Result = stm.executeUpdate(sql);
        stm.close();
        System.out.println(Result);
        
    }
    catch(Exception e)
    {
        System.out.println("Gagal Adding Data ..");
        System.out.println(e);
    }
}
public void tambahProduk(Produk Pr)
{
    String sql = "INSERT INTO PRODUK(NAMA,SATUAN,JUMLAH_STOK,HARGA,MIN_STOK,CREATED_AT,CREATED_BY,Gambar)"
            +"VALUES('"+Pr.getNama()+"','"+Pr.getSatuan()+"','"+Pr.getJumlah()+"','"+Pr.getHarga()+"','"+Pr.getMin_stok()+"','"+dtf.format(now)+"','"+"ADMIN"+"','"+Pr.getGambar()+"')";
            
            System.out.println("TAMBAH PRODUK");
            try
            {
                Statement stm = con.createStatement();
                int Result = stm.executeUpdate(sql);
                stm.close();
                System.out.println(Result);
            }catch(Exception e)
            {
                System.out.println("Gagal Menambahkan Produk\n");
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
public void deleteProduk(String cari)
{
    String sql = "UPDATE PRODUK SET AKTIF=0 , DELETE_BY='ADMIN',DELETE_AT='"+dtf.format(now)+"'WHERE NAMA='"+cari+"'";
    System.out.println("Delete Produk ... ");
    try
    {
        Statement stm = con.createStatement();
        int result = stm.executeUpdate(sql);
        System.out.println(result);
        stm.close();
    }
    catch(Exception e)
    {
        
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

public void deleteHargaLayanan(String id)
{
    String sql = "Update Harga_layanan set AKTIF=0 , DELETE_BY='ADMIN',DELETE_AT='"+dtf.format(now)+"' WHERE  id_harga_layanan='"+id+"'";
    try
    {
        Statement stm = con.createStatement();
        int result =stm.executeUpdate(sql);
        System.out.println("DELETE SUP "+result);
    }catch(Exception e)
    {
        System.out.println("Gagal Hapus");
        System.out.println(e);
    }
}
public void deleteJenisHewan(String cari)
{
    String sql = "Update Jenis_Hewan set AKTIF=0 , DELETE_BY='ADMIN',DELETE_AT='"+dtf.format(now)+"' WHERE  NAMA='"+cari+"'";
    try
    {
        Statement stm = con.createStatement();
        int result =stm.executeUpdate(sql);
        System.out.println("DELETE SUP "+result);
    }catch(Exception e)
    {
        System.out.println("Gagal Hapus");
        System.out.println(e);
    }
        
}
public void deleteUkuranHewan(String cari)
{
    String sql="UPDATE UKURAN_HEWAN SET AKTIF=0 ,DELETE_BY='ADMIN',DELETE_AT='"+dtf.format(now)+"' WHERE  NAMA='"+cari+"'";
     try
     {
         Statement stm = con.createStatement() ;
         int Rs= stm.executeUpdate(sql);
         System.out.println(Rs);
     }catch(Exception e)
     {
         System.out.println("Gagal Menampilkan Data");
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

public Produk searchProduk(String namaProduk)
{
    String sql = "Select * from Produk where AKTIF=1 and nama='"+namaProduk+"'";
    System.out.println("Mencari Nama Produk");
    System.out.println(sql);
    Produk P = null ;
    try 
    {
        Statement stm = con.createStatement();
        ResultSet result = stm.executeQuery(sql);
        if(result!=null)
        {
            while(result.next())
            {
//                public Produk(String nama , String satuan, int jumlah, int harga , int min_stok,String Create_by ,String Create_At , 
//               String modified_by , String modified_at , String delete_at , String delete_by )
                P = new Produk(result.getString("nama"),result.getString("satuan"),Integer.parseInt(result.getString("JUMLAH_STOK")),
                Integer.parseInt(result.getString("HARGA")),Integer.parseInt(result.getString("MIN_STOK")),result.getString("CREATED_BY"),result.getString("CREATED_At"),
                result.getString("MODIFIED_BY"), result.getString("MODIFIED_AT"),result.getString("DELETE_aT"),result.getString("Delete_by"),Integer.parseInt(result.getString("ID_PRODUK")),result.getString("GAMBAR"));
                System.out.println(P.getGambar());
                Blob pic;
                            pic=(Blob) result.getBlob("gambar");
            }
            result.close();
            stm.close();

        }
        

    }
    catch(Exception e)
    {
        System.out.println(e);
        System.out.println("Gagal ");
        
    }
    return P ;
}
public UkuranHewan searchUkuranHewan(String ukuran)
{
    String sql ="SELECT ID_ukuran_hewan , NAMA from Ukuran_hewan where AKTIF= 1 AND NAMA='"+ukuran+"'";
    System.out.println("Mencari Ukuran ...");
    UkuranHewan Uh = null; 
    try
    {
        Statement stm = con.createStatement() ;
        ResultSet rs = stm.executeQuery(sql);
        if(rs!=null)
        {
            while(rs.next())
            {
                Uh = new UkuranHewan(Integer.parseInt(rs.getString("id_ukuran_hewan")),rs.getString("nama"));
            }
            rs.close();
            stm.close();
        }
        
        
    }
    catch(Exception E)
    {
        System.out.println(E);
    }
    return Uh;
}
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
public JenisHewan searchJenisHewan(String namaJenis)
{
    String sql = "Select * from jenis_hewan where AKTIF=1 and NAMA= '"+namaJenis+"'";
    System.out.println("Mencari Nama Jenis Hewan .. ");
    JenisHewan js = null ;
    try
    {
        Statement stm = con.createStatement() ;
        ResultSet rs = stm.executeQuery(sql);
        if(rs!=null)
        {
            while(rs.next())
            {

              
                js = new JenisHewan(Integer.parseInt(rs.getString("ID_JENIS_HEWAN")), rs.getString("NAMA"), rs.getString("CREATED_AT"), rs.getString("CREATED_BY"), rs.getString("MODIFIED_BY"), 
                        rs.getString("MODIFIED_AT"), rs.getString("DELETE_AT"), rs.getString("DELETE_BY"));
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
    return js ;
}
public Harga_Layanan searchHargaLayanan(int idLayanan , int idUkuran)
{
    String sql = "Select harga,id_harga_layanan from harga_layanan where id_layanan ='"+idLayanan+"'"
            +"AND ID_UKURAN_HEWAN= '"+idUkuran+"'";
    Harga_Layanan HL = null ;
    try {
        
        Statement stm = con.createStatement() ;
        ResultSet rs = stm.executeQuery(sql);
        if(rs!=null)
        {
            while(rs.next())
            {

                HL = new Harga_Layanan(Integer.parseInt(rs.getString("harga")),Integer.parseInt(rs.getString("id_harga_layanan")));
                
            }
        }
        rs.close();
        stm.close();
    } catch (Exception e) {
        System.out.println(e);
        System.out.println("gagal Mencari ..!!");
    }
    return HL ;
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

public List<Harga_Layanan> tampilHargaLayanan() 
{
        String sql ="Select A.nama as Nama, b.nama as Ukuran,C.Harga as Harga from harga_layanan as C inner join layanan as A on C.id_layanan=A.id_Layanan inner join ukuran_hewan b on C.id_ukuran_hewan =b.id_ukuran_hewan where C.aktif=1"; 
    System.out.println("Tampil Harga Layanan");
     
    List<Harga_Layanan> list = new ArrayList<Harga_Layanan>();
    
    try
    {
        
        Statement stm= con.createStatement();
       ResultSet rs= stm.executeQuery(sql);
   
        
        if (rs!=null) {
            
            while(rs.next())
            {
                
                
                      List<Layanan> l=new ArrayList<>();
                      Layanan lh = new Layanan();
                       lh.setNamaLayanan(rs.getString("Nama"));
                       UkuranHewan Uh= new UkuranHewan();
                       Uh.setNama(rs.getString("Ukuran"));
                       Harga_Layanan  hl = new Harga_Layanan(Integer.parseInt(rs.getString("Harga")));
                       hl.setLayanan(l);
//                      
                       l.add(lh);
                       list.add(hl);
                            
                    
         
                            
            }
            rs.close();
            stm.close();
        }

    }
    catch(SQLException e)
    {
        System.out.println(e);
        
    }
    catch(Exception e)
    {
        System.out.println("Gagal ");
        System.out.println(e);
    }
    
    
    
    return list ;
            
}
public List<Produk> tampilComboNamaProduk()
{
    String sql = "Select NAMA FROM PRODUK WHERE AKTIF=1 ";
            List<Produk> list = new ArrayList<>();

    try
    {
        Produk Pr ;
        Statement stm = con.createStatement();
        ResultSet result = stm.executeQuery(sql);
        if(result!=null)
        {
            while(result.next())
            {
                Pr = new Produk(result.getString("nama"),result.getString("satuan"),Integer.parseInt(result.getString("JUMLAH_STOK")),
                Integer.parseInt(result.getString("HARGA")),Integer.parseInt(result.getString("MIN_STOK")),result.getString("CREATED_BY"),result.getString("CREATED_At"),
                result.getString("MODIFIED_BY"), result.getString("MODIFIED_AT"),result.getString("DELETE_aT"),result.getString("Delete_by"),Integer.parseInt(result.getString("ID_PRODUK")),result.getString("gambar"));
                list.add(Pr);
            }
            
            result.close();
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
public List<Produk>tampilProdukSeluruh()
{
     String sql = "Select * from Produk ";
           
    System.out.println("Daftar Jenis Produk ");
    Produk Pr ;
    List<Produk> list = new ArrayList<>();
    try
    {
        
        Statement stm = con.createStatement();
        ResultSet result = stm.executeQuery(sql);
        if(result!=null)
        {
            while (result.next())
            {
                Pr = new Produk(result.getString("nama"),result.getString("satuan"),Integer.parseInt(result.getString("JUMLAH_STOK")),
                Integer.parseInt(result.getString("HARGA")),Integer.parseInt(result.getString("MIN_STOK")),result.getString("CREATED_BY"),result.getString("CREATED_At"),
                result.getString("MODIFIED_BY"), result.getString("MODIFIED_AT"),result.getString("DELETE_aT"),result.getString("Delete_by"),
                        Integer.parseInt(result.getString("ID_PRODUK")),result.getString("gambar"));
                
                list.add(Pr);
            }
        }
                    result.close();
            stm.close();
    }
   catch(Exception E)
   {
       System.out.println(E);
   }
    return list ;
            
}
public List<Produk> tampilProduk()
{
    String sql = "Select * from Produk where aktif= 1";
           
    System.out.println("Daftar Jenis Produk ");
    Produk Pr ;
    List<Produk> list = new ArrayList<>();
    try
    {
        
        Statement stm = con.createStatement();
        ResultSet result = stm.executeQuery(sql);
        if(result!=null)
        {
            while (result.next())
            {
                Pr = new Produk(result.getString("nama"),result.getString("satuan"),Integer.parseInt(result.getString("JUMLAH_STOK")),
                Integer.parseInt(result.getString("HARGA")),Integer.parseInt(result.getString("MIN_STOK")),result.getString("CREATED_BY"),result.getString("CREATED_At"),
                result.getString("MODIFIED_BY"), result.getString("MODIFIED_AT"),result.getString("DELETE_aT"),result.getString("Delete_by"),
                        Integer.parseInt(result.getString("ID_PRODUK")),result.getString("gambar"));
                
                list.add(Pr);
            }
        }
                    result.close();
            stm.close();
    }
   catch(Exception E)
   {
       System.out.println(E);
   }
    return list ;
}

public List<JenisHewan> tampilJenisHewan()
{
    String sql = "Select * FROM jenis_hewan where AKTIF=1" ;
    System.out.println("Daftar Jenis Hewan ..");
    JenisHewan jh ;
    List<JenisHewan> list = new ArrayList<>();
    try 
    {
        Statement stm = con.createStatement();
        ResultSet rs = stm.executeQuery(sql);
        if(rs!=null)
        {
            while(rs.next())
            {
                
//                //    public JenisHewan(int id ,String nama , String created_at , String created_by , String modified_by , 
//            String modified_at , String deleteat , String deletBy  )
                
                jh = new JenisHewan(Integer.parseInt(rs.getString("ID_JENIS_HEWAN")), rs.getString("NAMA"), rs.getString("CREATED_AT"), rs.getString("CREATED_BY"), rs.getString("MODIFIED_BY"), 
                        rs.getString("MODIFIED_AT"), rs.getString("DELETE_AT"), rs.getString("DELETE_BY"));
               
                list.add(jh);
                
            }
            rs.close();
            stm.close();
            
        }
   
    }
    catch(Exception e)
    {
        System.out.println(e);
        System.out.println("Gagal Menambahkan Data ...");
    }
        return list ; 
}
public List<Layanan> tampilLayanan()
{
    String sql = "Select * FROM LAYANAN WHERE AKTIF=1 " ;
    System.out.println("Daftar Layanan");
    System.out.println(sql);
    Layanan lyn ;
    List<Layanan> list = new ArrayList<>();
    try 
    {
        Statement stm = con.createStatement();
        ResultSet rs = stm.executeQuery(sql);
        if(rs!=null)
        {
            while(rs.next())
            {
                lyn = new Layanan(rs.getString("Nama"),Integer.parseInt(rs.getString("ID_LAYANAN")),rs.getString("CREATED_BY"),
                rs.getString("DELETE_BY"),rs.getString("delete_at"),rs.getString("Created_at"),rs.getString("MODIFIED_BY"),rs.getString("MODIFIED_AT"));
                list.add(lyn);
                
            }
            rs.close();
            stm.close();
            
        }
   
    }
    catch(Exception e)
    {
        System.out.println(e);
        System.out.println("Gagal Menambahkan Data ...");
    }
        return list ;         
}
public List<UkuranHewan>tampilUkuranHewan()
{
    String sql = "Select * from ukuran_hewan where aktif=1";
    System.out.println("Menampilkan ukuran hewan ...");
    UkuranHewan uh ;
    List<UkuranHewan>M = new  ArrayList<>();
    try
    {
        Statement stm = con.createStatement() ;
        ResultSet rs = stm.executeQuery(sql);
        if(rs!=null)
        {
            while(rs.next())
            {
               uh= new UkuranHewan(Integer.parseInt(rs.getString("ID_UKURAN_HEWAN")),rs.getString("NAMA"),rs.getString("Created_by"),rs.getString("Created_at"),rs.getString("Modified_by"),rs.getString("Modified_at") );
            
               M.add(uh);
            }
             rs.close();
             stm.close();
        }
        
    }catch(Exception e)
    {
        System.out.println("Gagal..");
        System.out.println(e);
        
    }
    
    
    return M ;
}

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



////////////////////////////////EDIT /////////////////
 
   
public void editJenisHewan(JenisHewan Jh , String jenis)
{
    String sql = "UPDATE JENIS_HEWAN set modified_by='ADMIN',MODIFIED_AT='"+dtf.format(now)+"',"
            +"NAMA='"+Jh.getnNama()+"'"
            +"where nama ='"+jenis+"'";
    System.out.println("Edit Jenis Hewan");
    try
    {
        Statement stm = con.createStatement();
        int Result = stm.executeUpdate(sql);
        System.out.println("Edit Ukuran");
        System.out.println(Result);
    
    }
    catch(Exception e)
    {
        System.out.println("Edit Ukuran");
        System.out.println(e);
    }
}

public void editSuplier(Suplier s , String nama )
{
    String sql = "UPDATE SUPPLIER SET MODIFIED_BY ='ADMIN ',MODIFIED_at='"+dtf.format(now)+"',"
            +"nama='"+s.getNama()+"',"
            +"telp='"+s.getTelp()+"',"
            +"ALAMAT='"+s.getAlamat()+"'"
            +"where nama = '"+nama+"'";
    
    System.out.println("Edit Supplier ....");
    try {
        
        Statement stm = con.createStatement();
        int Result = stm.executeUpdate(sql);
        System.out.println(Result);
    } catch (Exception e) {
        System.out.println("Gagal Edit  Supplier ");
        System.out.println(e);
    }
    
}


public void editLayanan(Layanan l , String nama )
{
    String sql = "UPDATE LAYANAN SET modified_by='ADMIN',MODIFIED_aT='"+dtf.format(now)+"',"
            +"NAMA= '"+l.getNamaLayanan()+"'"
            +"where nama = '"+nama+"'";
    System.out.println("Edit Layanan");
    try {
        Statement stm = con.createStatement();
        int rs = stm.executeUpdate(sql);
        System.out.println(rs);
        
    } catch (Exception e) {
        System.out.println("Gagal Edit Layanan");
        System.out.println(e);
    }
    
}

//////Edit Produk ////////
public void editProduk(Produk P , String nama)
{
   String sql ="UPDATE Produk set modified_by='ADMIN',modified_at ='"+dtf.format(now)+"' ,"
          +"nama ='"+P.getNama()+"',"
           +"satuan='"+P.getSatuan()+"',"
           +"jumlah_stok='"+P.getJumlah()+"',"
           +"harga='"+P.getHarga()+"',"
           +"gambar='"+P.getGambar()+"',"
           +"min_stok='"+P.getMin_stok()+"'"
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
public void editUkuran(UkuranHewan UH , String ukuran)
{
    String sql = "Update Ukuran_Hewan set modified_by='ADMIN', Modified_At='"+dtf.format(now)+"',"
            +"NAMA='"+UH.getNama()+"'"
            +" WHERE nama='"+ukuran+"'";
    System.out.println("Edit Ukuran ");
//     String sql = "UPDATE Motor SET nomorKendaraan = '"+M.getNomorKendaraan()+"',"
//                    +" Merek= '"+M.getMerek()+"',"
//                    +" tahunPembuatan ='"+M.gettahunPembuatan()+"'"
//                    +" where  nomorKendaraan= '"+nomor+"'";
    
    try
    {
        Statement stm= con.createStatement() ;
        int rs = stm.executeUpdate(sql);
        System.out.println(rs);
    }
    catch(Exception e)
    {
        System.out.println("Gagal edit");
        System.out.println(e);
    }
    
}
   public void editPegawai(Pegawai P,String userName)
{
//       String sql ="UPDATE Produk set modified_by='ADMIN',modified_at ='"+dtf.format(now)+"' ,"
//          +"nama ='"+P.getNama()+"',"
//           +"satuan='"+P.getSatuan()+"',"
//           +"jumlah_stok='"+P.getJumlah()+"',"
//           +"harga='"+P.getHarga()+"',"
//           +"min_stok='"+P.getMin_stok()+"'"
//           +"where nama='"+nama+"'";


        String sql = "UPDATE Pegawai set MODIFIED_BY='ADMIN',modified_at = '"+dtf.format(now)+"',"
                +"Alamat = '"+P.getAlamat()+"',"
                +"Tanggal_Lahir = '"+P.getTglLahir()+"',"
                +"TELP = '"+P.getNoHp()+"',"
                +"USERNAME='"+P.getUserName()+"',"
                +"ROLE ='"+P.getRole()+"',"
                +"NAMA = '"+P.getNamaPegawai()+"'"
                +"where userName = '"+userName+"'";



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

}
