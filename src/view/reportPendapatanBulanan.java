/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package view;
import Controller.AdminControl;
import Controller.CSControl ;
import Controller.KasirControl;
import DAO.AdminDao;
import static DAO.AdminDao.con;
import DAO.CsDAO;
import Model.Harga_Layanan;
import Model.JenisHewan;
import Model.Layanan;
import Model.dataHewan;
import Model.dataHewan;
import Model.Pelanggan;
import Model.Produk;
import Model.TransaksiLayanan;
import Model.TransaksiProduk;
import Model.UkuranHewan;
import Model.dataHewan;
import Model.detailTransaksiLayanan;
import Model.detailTransaksiProduk;
import exception.dataKosong;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;
import Session.LoginSession;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.placeholder.PlaceHolder;
import exception.CekAngka;
import exception.CekHuruf;
import exception.PanjangData;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.event.DocumentListener;
import javax.swing.event.UndoableEditListener;
import javax.swing.table. DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.Element;
import javax.swing.text.Position;
import javax.swing.text.Segment;


/**
 *
 * @author ACER
 */
public class reportPendapatanBulanan extends javax.swing.JFrame {

    public Pelanggan P ;
    public TransaksiProduk tp ;
    public TransaksiLayanan tl ;
    public detailTransaksiProduk Tp ;
    public detailTransaksiLayanan Tl ;
    public  DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MMMM/dd ");  
    
    
    public  LocalDateTime now = LocalDateTime.now();
    CSControl CS ;
    public ArrayList namaHewan = new  ArrayList();
    public ArrayList nama= new ArrayList();
    public ArrayList ukuran = new ArrayList();
    public AdminControl AC = new AdminControl();
    public KasirControl KC = new KasirControl();
    public CsDAO CA = new CsDAO();
    public AdminDao AD = new AdminDao();
    public Produk Pr ;
    public Layanan L;
    public  JenisHewan jh ;
    public dataHewan Dh ;
    String tambah=null ;
    int StokProduk = 0;
    int idUkuran ;
    int idLayanan ;
    public UkuranHewan uh ;
    public Harga_Layanan hl ;
    int idHarga_layanan ;
    /**
     * Creates new form MenuCS
     */
        DefaultTableModel tabelModel , tabelModel2 ;
    public reportPendapatanBulanan() {
            
        initComponents();

        tabelModel = (DefaultTableModel) tableTransaksiLayanan.getModel();
        tabelModel2 = (DefaultTableModel) tableProduk.getModel();
        
        atur(tableTransaksiLayanan,new int []{100,300,300} );
        atur(tableProduk, new int []{100,300,300} );
        tgl.setText(dtf.format(now));

   
          
    }
 public void cleanTable()
 {
     int baris = tabelModel.getRowCount();
     int baris2 = tabelModel2.getRowCount();
     for (int i = 0; i < baris2; i++) {
         tabelModel2.removeRow(0);
     }
     for (int i = 0; i <baris; i++) {
         tabelModel.removeRow(0);
     }
     
 }
   public final void tampilTransaksiProduk()
 {
cleanTable();
    AD.makeConnection();

    try
    {
        int bulan =(Integer)jComboBox1.getSelectedIndex();
     if (bulan==1)
     {
        String sql ="select B.nama \"nama_produk\", sum(A.total_harga) \"harga\" from detail_transaksi_produk A inner join produk B on A.id_produk=B.id_produk where month(A.created_at)=1 AND year(A.created_at)="+PilihTahun.getText()+" group by B.nama";
                        
        Statement stm= con.createStatement();
       ResultSet rs= stm.executeQuery(sql);
   
        
        if (rs!=null) {
            
            while(rs.next())
            {
                String nama= rs.getString("nama_produk");
                String total = rs.getString("harga");
                
                String[] dataField={"",nama ,total};
                tabelModel2.addRow(dataField);
                
            }
            rs.close();
            stm.close();
        }
     }
     else if (bulan==2)
     {
        String sql ="select B.nama \"nama_produk\", sum(A.total_harga) \"harga\" from detail_transaksi_produk A inner join produk B on A.id_produk=B.id_produk where month(A.created_at)=2 AND year(A.created_at)="+PilihTahun.getText()+" group by B.nama";
            Statement stm= con.createStatement();
       ResultSet rs= stm.executeQuery(sql);
   
        
        if (rs!=null) {
            
            while(rs.next())
            {
                String nama= rs.getString("nama_produk");
                String total = rs.getString("harga");
                
                String[] dataField={"",nama ,total};
                tabelModel2.addRow(dataField);
                
            }
            rs.close();
            stm.close();
        }
     }
     else if (bulan==3)
     {
        String sql ="select B.nama \"nama_produk\", sum(A.total_harga) \"harga\" from detail_transaksi_produk A inner join produk B on A.id_produk=B.id_produk where month(A.created_at)=3 AND year(A.created_at)="+PilihTahun.getText()+" group by B.nama";
            Statement stm= con.createStatement();
       ResultSet rs= stm.executeQuery(sql);
   
        
        if (rs!=null) {
            
            while(rs.next())
            {
                String nama= rs.getString("nama_produk");
                String total = rs.getString("harga");
                
                String[] dataField={"",nama ,total};
                tabelModel2.addRow(dataField);
                
            }
            rs.close();
            stm.close();
        }
     } 
     else if (bulan==4)
     {
        String sql ="select B.nama \"nama_produk\", sum(A.total_harga) \"harga\" from detail_transaksi_produk A inner join produk B on A.id_produk=B.id_produk where month(A.created_at)=4 AND year(A.created_at)="+PilihTahun.getText()+" group by B.nama";
            Statement stm= con.createStatement();
       ResultSet rs= stm.executeQuery(sql);
   
        
        if (rs!=null) {
            
            while(rs.next())
            {
                String nama= rs.getString("nama_produk");
                String total = rs.getString("harga");
                
                String[] dataField={"",nama ,total};
                tabelModel2.addRow(dataField);
                
            }
            rs.close();
            stm.close();
        }
     }
     else if (bulan==5)
     {
        String sql ="select B.nama \"nama_produk\", sum(A.total_harga) \"harga\" from detail_transaksi_produk A inner join produk B on A.id_produk=B.id_produk where month(A.created_at)=5 AND year(A.created_at)="+PilihTahun.getText()+" group by B.nama";
            Statement stm= con.createStatement();
       ResultSet rs= stm.executeQuery(sql);
   
        
        if (rs!=null) {
            
            while(rs.next())
            {
                String nama= rs.getString("nama_produk");
                String total = rs.getString("harga");
                
                String[] dataField={"",nama ,total};
                tabelModel2.addRow(dataField);
                
            }
            rs.close();
            stm.close();
        }
     }
     else if (bulan==6)
     {
        String sql ="select B.nama \"nama_produk\", sum(A.total_harga) \"harga\" from detail_transaksi_produk A inner join produk B on A.id_produk=B.id_produk where month(A.created_at)=6 AND year(A.created_at)="+PilihTahun.getText()+" group by B.nama";
            Statement stm= con.createStatement();
       ResultSet rs= stm.executeQuery(sql);
   
        
        if (rs!=null) {
            
            while(rs.next())
            {
                String nama= rs.getString("nama_produk");
                String total = rs.getString("harga");
                
                String[] dataField={"",nama ,total};
                tabelModel2.addRow(dataField);
                
            }
            rs.close();
            stm.close();
        }
     }
     else if (bulan==7)
     {
        String sql ="select B.nama \"nama_produk\", sum(A.total_harga) \"harga\" from detail_transaksi_produk A inner join produk B on A.id_produk=B.id_produk where month(A.created_at)=7 AND year(A.created_at)="+PilihTahun.getText()+" group by B.nama";
            Statement stm= con.createStatement();
       ResultSet rs= stm.executeQuery(sql);
   
        
        if (rs!=null) {
            
            while(rs.next())
            {
                String nama= rs.getString("nama_produk");
                String total = rs.getString("harga");
                
                String[] dataField={"",nama ,total};
                tabelModel2.addRow(dataField);
                
            }
            rs.close();
            stm.close();
        }
     }
     if (bulan==8)
     {
        String sql ="select B.nama \"nama_produk\", sum(A.total_harga) \"harga\" from detail_transaksi_produk A inner join produk B on A.id_produk=B.id_produk where month(A.created_at)=8 AND year(A.created_at)="+PilihTahun.getText()+" group by B.nama";
            Statement stm= con.createStatement();
       ResultSet rs= stm.executeQuery(sql);
   
        
        if (rs!=null) {
            
            while(rs.next())
            {
                String nama= rs.getString("nama_produk");
                String total = rs.getString("harga");
                
                String[] dataField={"",nama ,total};
                tabelModel2.addRow(dataField);
                
            }
            rs.close();
            stm.close();
        }
     }
     if (bulan==9)
     {
        String sql ="select B.nama \"nama_produk\", sum(A.total_harga) \"harga\" from detail_transaksi_produk A inner join produk B on A.id_produk=B.id_produk where month(A.created_at)=9 AND year(A.created_at)="+PilihTahun.getText()+" group by B.nama";
            Statement stm= con.createStatement();
       ResultSet rs= stm.executeQuery(sql);
   
        
        if (rs!=null) {
            
            while(rs.next())
            {
                String nama= rs.getString("nama_produk");
                String total = rs.getString("harga");
                
                String[] dataField={"",nama ,total};
                tabelModel2.addRow(dataField);
                
            }
            rs.close();
            stm.close();
        }
     }
     if (bulan==10)
     {
        String sql ="select B.nama \"nama_produk\", sum(A.total_harga) \"harga\" from detail_transaksi_produk A inner join produk B on A.id_produk=B.id_produk where month(A.created_at)=10 AND year(A.created_at)="+PilihTahun.getText()+" group by B.nama";
            Statement stm= con.createStatement();
       ResultSet rs= stm.executeQuery(sql);
   
        
        if (rs!=null) {
            
            while(rs.next())
            {
                String nama= rs.getString("nama_produk");
                String total = rs.getString("harga");
                
                String[] dataField={"",nama ,total};
                tabelModel2.addRow(dataField);
                
            }
            rs.close();
            stm.close();
        }
     }
     if (bulan==11)
     {
        String sql ="select B.nama \"nama_produk\", sum(A.total_harga) \"harga\" from detail_transaksi_produk A inner join produk B on A.id_produk=B.id_produk where month(A.created_at)=11 AND year(A.created_at)="+PilihTahun.getText()+" group by B.nama";
            Statement stm= con.createStatement();
       ResultSet rs= stm.executeQuery(sql);
   
        
        if (rs!=null) {
            
            while(rs.next())
            {
                String nama= rs.getString("nama_produk");
                String total = rs.getString("harga");
                
                String[] dataField={"",nama ,total};
                tabelModel2.addRow(dataField);
                
            }
            rs.close();
            stm.close();
        }
     }
    else if (bulan==12)
     {
        String sql ="select B.nama \"nama_produk\", sum(A.total_harga) \"harga\" from detail_transaksi_produk A inner join produk B on A.id_produk=B.id_produk where month(A.created_at)=12 AND year(A.created_at)="+PilihTahun.getText()+" group by B.nama";
            Statement stm= con.createStatement();
       ResultSet rs= stm.executeQuery(sql);
   
        
        if (rs!=null) {
            
            while(rs.next())
            {
                String nama= rs.getString("nama_produk");
                String total = rs.getString("harga");
                
                String[] dataField={"",nama ,total};
                tabelModel2.addRow(dataField);
                
            }
            rs.close();
            stm.close();
        }
     }
     
        
   
    } 
        
        catch(Exception e)
        {
                System.out.println(e);
        }

 }
public final void  hitungTotalHarga()
   {
       
           long total = 0;
           long total1 = 0 ;
           
           for (int i = 0; i < tableProduk.getRowCount(); i++) {
              int amount = Integer.parseInt((String)tableProduk.getValueAt(i, 2));
                 total1 += amount;
             
       }
           txtTotalLayanan1.setText(""+total1);
           for (int i =0; i< tableTransaksiLayanan.getRowCount(); i++){
                int amount = Integer.parseInt((String)tableTransaksiLayanan.getValueAt(i, 2));
                 total += amount;
                }
            txtTotalLayanan.setText(""+total);
 
   }
public void noTable()
{
    int baris = tabelModel.getRowCount();
    for (int i = 0; i < baris; i++) {
        String nomor = String.valueOf(i+1);
        tabelModel.setValueAt(nomor + ".",i,0);
    }
    int baris2 = tabelModel2.getRowCount();
    for (int i = 0; i < baris2; i++) {
           String nomor = String.valueOf(i+1);
        tabelModel2.setValueAt(nomor + ".",i,0);
        
    }
}
  
 private void cetakData()
  {

      AD.makeConnection();
      
      try
      {
        String file ="F:\\generete_pdf\\"+"LaporanPendapatanBulanan"+".pdf";
        com.itextpdf.text.Document document = new com.itextpdf.text.Document();
        PdfWriter writer= PdfWriter.getInstance(document, new FileOutputStream(file));
        document.open();
        
            Font font1 = new Font(Font.FontFamily.HELVETICA  , 14, Font.BOLD);
                       Font font2 = new Font(Font.FontFamily.HELVETICA  , 12);
                       Font font3 = new Font(Font.FontFamily.HELVETICA  , 20);
                       
                       Image img = Image.getInstance("nota.jpg");
                        img.setAbsolutePosition(0f,0f);
              

            Chunk c1 = new Chunk("LAPORAN PENDAPATAN BULANAN LAYANAN",font1);
            Chunk c2 = new Chunk("Bulan :  "+jComboBox1.getSelectedItem() ,font2);
            Chunk c3 = new Chunk("Tahun  : "+PilihTahun.getText(),font2);
           
            Paragraph p1 = new Paragraph();
            p1.setAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            p1.add(c1);
            
            Paragraph p2 = new Paragraph();
            p2.setAlignment(com.itextpdf.text.Element.ALIGN_LEFT);
            p2.add(c2);
            
            Paragraph p3 = new Paragraph();
            p3.setAlignment(com.itextpdf.text.Element.ALIGN_LEFT);
            p3.add(c3);
            p3.setSpacingAfter(10);
            
           
            PdfPTable table = new PdfPTable(3);
            table.addCell("No");
            table.addCell("Nama Jasa Layanan");
            table.addCell("Harga");

 
              for (int i = 0; i <tableTransaksiLayanan.getRowCount(); i++) {
              String No = (String) tableTransaksiLayanan.getValueAt(i, 0);
              String jasaLyn= (String) tableTransaksiLayanan.getValueAt(i,1);
              String harga = (String) tableTransaksiLayanan.getValueAt(i,2);

              table.addCell(No);
              table.addCell(jasaLyn);
              table.addCell(harga);

          }
           Chunk c10= new Chunk("Total Pendapatan : "+txtTotalLayanan.getText(), font1);
           Paragraph p10 = new Paragraph();
           p10.add(c3);
            
            document.add(p1);
            document.add(p2);
            document.add(p3);
            
            
            
   
            
            Chunk c4 = new Chunk("LAPORAN PENDAPATAN BULANAN PRODUK",font1);
           
            Paragraph p4 = new Paragraph();
            p4.setAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            p4.add(c4);
            p4.setSpacingAfter(10);
            p4.setSpacingBefore(10);
            

            
           
            PdfPTable table1 = new PdfPTable(3);
            table1.addCell("No");
            table1.addCell("Nama Produk");
            table1.addCell("Harga");

 
              for (int i = 0; i <tableProduk.getRowCount(); i++) {
              String No = (String)tableProduk.getValueAt(i, 0);
              String jasaLyn= (String) tableProduk.getValueAt(i,1);
              String harga = (String) tableProduk.getValueAt(i,2);

              table1.addCell(No);
              table1.addCell(jasaLyn);
              table1.addCell(harga);

          }
              
            document.add(table);
            table.setSpacingAfter(10);
            document.add(p4); 
           
            document.add(table1);
            
            
            
            
            
            
            
            document.close();
            writer.close();
            
            File myFile = new File("F:\\generete_pdf\\"+"LaporanPendapatanBulanan"+".pdf");
            Desktop.getDesktop().open(myFile);
           
            
      }
      catch(Exception e)
      {
          System.out.println(e);
      }
       AD.closeConnection();
  }
   
 public void tampilTransaksi()
 {
     AD.makeConnection();

    try
    {
              int bulan =(Integer)jComboBox1.getSelectedIndex();
     if (bulan==1)
     {
     String sql ="select concat(C.nama, ' ', D.nama) \"nama_layanan\", sum(A.TOTAL_HARGA) \"harga\" from detail_transaksi_layanan A\n" +
                "inner join harga_layanan B on A.id_harga_layanan=B.id_harga_layanan\n" +
                "inner join layanan C on B.id_layanan=.C.id_layanan\n" +
                "inner join ukuran_hewan D on B.id_ukuran_hewan=D.id_ukuran_hewan\n" +
                "where month(A.CREATED_AT)=1 and Year(A.CREATED_AT)="+PilihTahun.getText()+" group by B.id_harga_layanan;";
             
                Statement stm= con.createStatement();
                ResultSet rs= stm.executeQuery(sql);


                 if (rs!=null) {

                     while(rs.next())
                     {

                         String nama = rs.getString("nama_layanan");
                         String harga= rs.getString("harga");


                         String[] dataField={"",nama,harga};
                         tabelModel.addRow(dataField);

                     }


                     rs.close();
                     stm.close();
                 }
     }
     else if (bulan==2)
     {
     String sql ="select concat(C.nama, ' ', D.nama) \"nama_layanan\", sum(A.TOTAL_HARGA) \"harga\" from detail_transaksi_layanan A\n" +
                "inner join harga_layanan B on A.id_harga_layanan=B.id_harga_layanan\n" +
                "inner join layanan C on B.id_layanan=.C.id_layanan\n" +
                "inner join ukuran_hewan D on B.id_ukuran_hewan=D.id_ukuran_hewan\n" +
                "where month(A.CREATED_AT)=2 and Year(A.CREATED_AT)="+PilihTahun.getText()+" group by B.id_harga_layanan;";
             
                Statement stm= con.createStatement();
                ResultSet rs= stm.executeQuery(sql);


                 if (rs!=null) {

                     while(rs.next())
                     {

                         String nama = rs.getString("nama_layanan");
                         String harga= rs.getString("harga");


                         String[] dataField={"",nama,harga};
                         tabelModel.addRow(dataField);

                     }


                     rs.close();
                     stm.close();
                 }
  
     }
     else if (bulan==3)
     {
     String sql ="select concat(C.nama, ' ', D.nama) \"nama_layanan\", sum(A.TOTAL_HARGA) \"harga\" from detail_transaksi_layanan A\n" +
                "inner join harga_layanan B on A.id_harga_layanan=B.id_harga_layanan\n" +
                "inner join layanan C on B.id_layanan=.C.id_layanan\n" +
                "inner join ukuran_hewan D on B.id_ukuran_hewan=D.id_ukuran_hewan\n" +
                "where month(A.CREATED_AT)=3 and Year(A.CREATED_AT)="+PilihTahun.getText()+" group by B.id_harga_layanan;";
             
                Statement stm= con.createStatement();
                ResultSet rs= stm.executeQuery(sql);


                 if (rs!=null) {

                     while(rs.next())
                     {

                         String nama = rs.getString("nama_layanan");
                         String harga= rs.getString("harga");


                         String[] dataField={"",nama,harga};
                         tabelModel.addRow(dataField);

                     }


                     rs.close();
                     stm.close();
                 }
  
     } 
     else if (bulan==4)
     {
     String sql ="select concat(C.nama, ' ', D.nama) \"nama_layanan\", sum(A.TOTAL_HARGA) \"harga\" from detail_transaksi_layanan A\n" +
                "inner join harga_layanan B on A.id_harga_layanan=B.id_harga_layanan\n" +
                "inner join layanan C on B.id_layanan=.C.id_layanan\n" +
                "inner join ukuran_hewan D on B.id_ukuran_hewan=D.id_ukuran_hewan\n" +
                "where month(A.CREATED_AT)=4 and Year(A.CREATED_AT)="+PilihTahun.getText()+" group by B.id_harga_layanan;";
             
                Statement stm= con.createStatement();
                ResultSet rs= stm.executeQuery(sql);


                 if (rs!=null) {

                     while(rs.next())
                     {

                         String nama = rs.getString("nama_layanan");
                         String harga= rs.getString("harga");


                         String[] dataField={"",nama,harga};
                         tabelModel.addRow(dataField);

                     }


                     rs.close();
                     stm.close();
                 }
  
     }
     else if (bulan==5)
     {
     String sql ="select concat(C.nama, ' ', D.nama) \"nama_layanan\", sum(A.TOTAL_HARGA) \"harga\" from detail_transaksi_layanan A\n" +
                "inner join harga_layanan B on A.id_harga_layanan=B.id_harga_layanan\n" +
                "inner join layanan C on B.id_layanan=.C.id_layanan\n" +
                "inner join ukuran_hewan D on B.id_ukuran_hewan=D.id_ukuran_hewan\n" +
                "where month(A.CREATED_AT)=5 and Year(A.CREATED_AT)="+PilihTahun.getText()+" group by B.id_harga_layanan;";
             
                Statement stm= con.createStatement();
                ResultSet rs= stm.executeQuery(sql);


                 if (rs!=null) {

                     while(rs.next())
                     {

                         String nama = rs.getString("nama_layanan");
                         String harga= rs.getString("harga");


                         String[] dataField={"",nama,harga};
                         tabelModel.addRow(dataField);

                     }


                     rs.close();
                     stm.close();
                 }
  
     }
     else if (bulan==6)
     {
     String sql ="select concat(C.nama, ' ', D.nama) \"nama_layanan\", sum(A.TOTAL_HARGA) \"harga\" from detail_transaksi_layanan A\n" +
                "inner join harga_layanan B on A.id_harga_layanan=B.id_harga_layanan\n" +
                "inner join layanan C on B.id_layanan=.C.id_layanan\n" +
                "inner join ukuran_hewan D on B.id_ukuran_hewan=D.id_ukuran_hewan\n" +
                "where month(A.CREATED_AT)= 6 and Year(A.CREATED_AT)="+PilihTahun.getText()+" group by B.id_harga_layanan;";
             
                Statement stm= con.createStatement();
                ResultSet rs= stm.executeQuery(sql);


                 if (rs!=null) {

                     while(rs.next())
                     {

                         String nama = rs.getString("nama_layanan");
                         String harga= rs.getString("harga");


                         String[] dataField={"",nama,harga};
                         tabelModel.addRow(dataField);

                     }


                     rs.close();
                     stm.close();
                 }
  
     }
     else if (bulan==7)
     {
     String sql ="select concat(C.nama, ' ', D.nama) \"nama_layanan\", sum(A.TOTAL_HARGA) \"harga\" from detail_transaksi_layanan A\n" +
                "inner join harga_layanan B on A.id_harga_layanan=B.id_harga_layanan\n" +
                "inner join layanan C on B.id_layanan=.C.id_layanan\n" +
                "inner join ukuran_hewan D on B.id_ukuran_hewan=D.id_ukuran_hewan\n" +
                "where month(A.CREATED_AT)=7 and Year(A.CREATED_AT)="+PilihTahun.getText()+" group by B.id_harga_layanan;";
             
                Statement stm= con.createStatement();
                ResultSet rs= stm.executeQuery(sql);


                 if (rs!=null) {

                     while(rs.next())
                     {

                         String nama = rs.getString("nama_layanan");
                         String harga= rs.getString("harga");


                         String[] dataField={"",nama,harga};
                         tabelModel.addRow(dataField);

                     }


                     rs.close();
                     stm.close();
                 }
       }
     if (bulan==8)
     {
     String sql ="select concat(C.nama, ' ', D.nama) \"nama_layanan\", sum(A.TOTAL_HARGA) \"harga\" from detail_transaksi_layanan A\n" +
                "inner join harga_layanan B on A.id_harga_layanan=B.id_harga_layanan\n" +
                "inner join layanan C on B.id_layanan=.C.id_layanan\n" +
                "inner join ukuran_hewan D on B.id_ukuran_hewan=D.id_ukuran_hewan\n" +
                "where month(A.CREATED_AT)= 8 and Year(A.CREATED_AT)="+PilihTahun.getText()+" group by B.id_harga_layanan;";
             
                Statement stm= con.createStatement();
                ResultSet rs= stm.executeQuery(sql);


                 if (rs!=null) {

                     while(rs.next())
                     {

                         String nama = rs.getString("nama_layanan");
                         String harga= rs.getString("harga");


                         String[] dataField={"",nama,harga};
                         tabelModel.addRow(dataField);

                     }


                     rs.close();
                     stm.close();
                 }
  
     }
     if (bulan==9)
     {
     String sql ="select concat(C.nama, ' ', D.nama) \"nama_layanan\", sum(A.TOTAL_HARGA) \"harga\" from detail_transaksi_layanan A\n" +
                "inner join harga_layanan B on A.id_harga_layanan=B.id_harga_layanan\n" +
                "inner join layanan C on B.id_layanan=.C.id_layanan\n" +
                "inner join ukuran_hewan D on B.id_ukuran_hewan=D.id_ukuran_hewan\n" +
                "where month(A.CREATED_AT)=9 and Year(A.CREATED_AT)="+PilihTahun.getText()+" group by B.id_harga_layanan;";
             
                Statement stm= con.createStatement();
                ResultSet rs= stm.executeQuery(sql);


                 if (rs!=null) {

                     while(rs.next())
                     {

                         String nama = rs.getString("nama_layanan");
                         String harga= rs.getString("harga");


                         String[] dataField={"",nama,harga};
                         tabelModel.addRow(dataField);

                     }


                     rs.close();
                     stm.close();
                 }
  
     }
     if (bulan==10)
     {
     String sql ="select concat(C.nama, ' ', D.nama) \"nama_layanan\", sum(A.TOTAL_HARGA) \"harga\" from detail_transaksi_layanan A\n" +
                "inner join harga_layanan B on A.id_harga_layanan=B.id_harga_layanan\n" +
                "inner join layanan C on B.id_layanan=.C.id_layanan\n" +
                "inner join ukuran_hewan D on B.id_ukuran_hewan=D.id_ukuran_hewan\n" +
                "where month(A.CREATED_AT)=10 and Year(A.CREATED_AT)="+PilihTahun.getText()+" group by B.id_harga_layanan;";
             
                Statement stm= con.createStatement();
                ResultSet rs= stm.executeQuery(sql);


                 if (rs!=null) {

                     while(rs.next())
                     {

                         String nama = rs.getString("nama_layanan");
                         String harga= rs.getString("harga");


                         String[] dataField={"",nama,harga};
                         tabelModel.addRow(dataField);

                     }


                     rs.close();
                     stm.close();
                 }
  
     }
     if (bulan==11)
     {
        String sql ="select concat(C.nama, ' ', D.nama) \"nama_layanan\", sum(A.TOTAL_HARGA) \"harga\" from detail_transaksi_layanan A\n" +
                "inner join harga_layanan B on A.id_harga_layanan=B.id_harga_layanan\n" +
                "inner join layanan C on B.id_layanan=.C.id_layanan\n" +
                "inner join ukuran_hewan D on B.id_ukuran_hewan=D.id_ukuran_hewan\n" +
                "where month(A.CREATED_AT)=11 and Year(A.CREATED_AT)="+PilihTahun.getText()+" group by B.id_harga_layanan;";
             
                Statement stm= con.createStatement();
                ResultSet rs= stm.executeQuery(sql);


                 if (rs!=null) {

                     while(rs.next())
                     {

                         String nama = rs.getString("nama_layanan");
                         String harga= rs.getString("harga");


                         String[] dataField={"",nama,harga};
                         tabelModel.addRow(dataField);

                     }


                     rs.close();
                     stm.close();
                 }
  
     }
    else if (bulan==12)
     {
           String sql ="select concat(C.nama, ' ', D.nama) \"nama_layanan\", sum(A.TOTAL_HARGA) \"harga\" from detail_transaksi_layanan A\n" +
                "inner join harga_layanan B on A.id_harga_layanan=B.id_harga_layanan\n" +
                "inner join layanan C on B.id_layanan=.C.id_layanan\n" +
                "inner join ukuran_hewan D on B.id_ukuran_hewan=D.id_ukuran_hewan\n" +
                "where month(A.CREATED_AT)=12  and Year(A.CREATED_AT)="+PilihTahun.getText()+" group by B.id_harga_layanan;";
             
                Statement stm= con.createStatement();
                ResultSet rs= stm.executeQuery(sql);


                 if (rs!=null) {

                     while(rs.next())
                     {

                         String nama = rs.getString("nama_layanan");
                         String harga= rs.getString("harga");


                         String[] dataField={"",nama,harga};
                         tabelModel.addRow(dataField);

                     }


                     rs.close();
                     stm.close();
                 }
  
     }
  
        
        //////////////////
        //////////////////
       
    } 
        
        catch(Exception e)
        {
                System.out.println(e);
        }

 }
  private void atur(JTable lihat,  int lebar[]){
    try
    {
        
        lihat.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        int banyak = lihat.getColumnCount();
        for (int i = 0; i < banyak; i++) {
           TableColumn kolom = lihat.getColumnModel().getColumn(i);
           kolom.setPreferredWidth(lebar[i]);
           lihat.setRowHeight(20);
        }
    } catch (Exception e) {
       
    }
 }
  

 
            
        /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPane = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableTransaksiLayanan = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableProduk = new javax.swing.JTable();
        txtTotalLayanan = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        txtTotalLayanan1 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        txtBulan = new javax.swing.JLabel();
        txtTahun = new javax.swing.JLabel();
        txtTgl = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        PilihTahun = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        tgl = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setIconImage(
            new javax.swing.ImageIcon(getClass().getResource("/icon/dogg.png")).getImage());

        mainPane.setBackground(new java.awt.Color(255, 255, 255));

        tableTransaksiLayanan.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        tableTransaksiLayanan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nomor", "Nama Jasa Layanan", "Harga"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableTransaksiLayanan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableTransaksiLayananMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tableTransaksiLayanan);
        if (tableTransaksiLayanan.getColumnModel().getColumnCount() > 0) {
            tableTransaksiLayanan.getColumnModel().getColumn(0).setResizable(false);
        }

        jPanel3.setBackground(new java.awt.Color(255, 139, 36));

        jLabel18.setFont(new java.awt.Font("Elephant", 1, 24)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(0, 0, 0));
        jLabel18.setText("LAPORAN PENDAPATAN BULANAN");

        jLabel19.setBackground(new java.awt.Color(0, 0, 0));
        jLabel19.setFont(new java.awt.Font("Elephant", 0, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(0, 0, 0));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/arrow-back-icon.png"))); // NOI18N
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 641, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(85, 85, 85)
                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addContainerGap())))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel19))
        );

        jLabel20.setFont(new java.awt.Font("Elephant", 1, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(0, 0, 0));
        jLabel20.setText("LAPORAN  LAYANAN PENDAPATAN  BULANAN");

        jLabel21.setFont(new java.awt.Font("Elephant", 1, 14)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(0, 0, 0));
        jLabel21.setText("LAPORAN  PRODUK  TERLARIS BULANAN");

        tableProduk.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        tableProduk.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nomor", "Nama Produk", "Harga"
            }
        ));
        tableProduk.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableProdukMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tableProduk);

        txtTotalLayanan.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        txtTotalLayanan.setForeground(new java.awt.Color(0, 0, 0));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Total Harga Rp.");

        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Total Harga Rp.");

        txtTotalLayanan1.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        txtTotalLayanan1.setForeground(new java.awt.Color(0, 0, 0));

        jSeparator2.setForeground(new java.awt.Color(0, 0, 0));

        txtBulan.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N

        txtTahun.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N

        txtTgl.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N

        jButton1.setText("BUAT PDF");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih Bulan", "Januari", "Febuari", "Maret", "April", "Mei", "Juni", "Juli", "Agustus", "September", "November", "Desember" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        PilihTahun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PilihTahunActionPerformed(evt);
            }
        });

        jButton2.setText("LAPORAN");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Tahun");

        tgl.setBackground(new java.awt.Color(0, 0, 0));
        tgl.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        javax.swing.GroupLayout mainPaneLayout = new javax.swing.GroupLayout(mainPane);
        mainPane.setLayout(mainPaneLayout);
        mainPaneLayout.setHorizontalGroup(
            mainPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(mainPaneLayout.createSequentialGroup()
                .addGroup(mainPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, mainPaneLayout.createSequentialGroup()
                        .addGroup(mainPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(mainPaneLayout.createSequentialGroup()
                                .addGap(54, 54, 54)
                                .addComponent(txtTahun, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(mainPaneLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(mainPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(mainPaneLayout.createSequentialGroup()
                                        .addGap(1, 1, 1)
                                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtTgl, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(mainPaneLayout.createSequentialGroup()
                                        .addComponent(PilihTahun, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(101, 101, 101)
                                        .addComponent(txtBulan, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(tgl, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(623, 623, 623))
                    .addGroup(mainPaneLayout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 665, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(731, 731, 731))
                    .addGroup(mainPaneLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(mainPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(mainPaneLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(mainPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(mainPaneLayout.createSequentialGroup()
                                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(mainPaneLayout.createSequentialGroup()
                                        .addComponent(txtTotalLayanan, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtTotalLayanan1, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(mainPaneLayout.createSequentialGroup()
                                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 421, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(mainPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 673, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 422, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(mainPaneLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        mainPaneLayout.setVerticalGroup(
            mainPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPaneLayout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mainPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(PilihTahun, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(tgl, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtBulan, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mainPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTgl, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                .addComponent(txtTahun, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mainPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(mainPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mainPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                        .addComponent(txtTotalLayanan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(mainPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtTotalLayanan1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mainPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jSeparator1)
                    .addComponent(jSeparator2))
                .addGap(47, 47, 47)
                .addComponent(jButton1)
                .addContainerGap(228, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(mainPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(2, 2, 2))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tableTransaksiLayananMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableTransaksiLayananMouseClicked
        // TODO add your handling code here:
          int index= tableTransaksiLayanan.getSelectedRow();
           String ID       = (String) tableTransaksiLayanan.getValueAt(index, 0);
           LoginSession.setIdTransaksi(ID);
           System.out.println(ID);
           if (JOptionPane.showConfirmDialog(null, "Lanjut Pembayaran?", "Yakin?",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION)
           {
               MenuKasirTransaksiLayanan mn = new  MenuKasirTransaksiLayanan();
               this.setVisible(false);
               mn.setVisible(true);
               mn.setExtendedState(JFrame.MAXIMIZED_BOTH);
           }
    }//GEN-LAST:event_tableTransaksiLayananMouseClicked

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        // TODO add your handling code here:
        MenuKasir m = new MenuKasir();
        this.setVisible(false );
        m.setVisible(true);
        m.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }//GEN-LAST:event_jLabel2MouseClicked

    private void tableProdukMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableProdukMouseClicked
        // TODO add your handling code here:
        int index= tableTransaksiLayanan.getSelectedRow();
        String ID       = (String) tableTransaksiLayanan.getValueAt(index, 0);
        LoginSession.setIdTransaksi(ID);
        System.out.println(ID);
        if (JOptionPane.showConfirmDialog(null, "Lanjut Pembayaran?", "Yakin?",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION)
        {
            MenuKasirTransaksiProduk mn = new  MenuKasirTransaksiProduk();
            this.setVisible(false);
            mn.setVisible(true);
            mn.setExtendedState(JFrame.MAXIMIZED_BOTH);
        }
    }//GEN-LAST:event_tableProdukMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        
        cetakData();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void PilihTahunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PilihTahunActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PilihTahunActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
 
        tampilTransaksiProduk();
        tampilTransaksi();
        noTable();
        hitungTotalHarga();
   
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(reportPendapatanBulanan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(reportPendapatanBulanan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(reportPendapatanBulanan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(reportPendapatanBulanan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new reportPendapatanBulanan().setVisible(true);
          
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField PilihTahun;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JPanel mainPane;
    private javax.swing.JTable tableProduk;
    private javax.swing.JTable tableTransaksiLayanan;
    private javax.swing.JLabel tgl;
    private javax.swing.JLabel txtBulan;
    private javax.swing.JLabel txtTahun;
    private javax.swing.JLabel txtTgl;
    private javax.swing.JLabel txtTotalLayanan;
    private javax.swing.JLabel txtTotalLayanan1;
    // End of variables declaration//GEN-END:variables
}
