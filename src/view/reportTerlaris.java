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
public class reportTerlaris extends javax.swing.JFrame {

    public Pelanggan P ;
    public TransaksiProduk tp ;
    public TransaksiLayanan tl ;
    public detailTransaksiProduk Tp ;
    public detailTransaksiLayanan Tl ;
    public  DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MMMM/dd ");  
    public DateTimeFormatter dtm= DateTimeFormatter.ofPattern("MMMM");
    public DateTimeFormatter dtn= DateTimeFormatter.ofPattern("yyyy");
    public DateTimeFormatter dtt= DateTimeFormatter.ofPattern("dd");
    
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
    public reportTerlaris() {
            
        initComponents();

        tabelModel = (DefaultTableModel) tableTransaksiLayanan.getModel();
        tabelModel2 = (DefaultTableModel) tableProduk.getModel();
        
        atur(tableTransaksiLayanan,new int []{100,300,300,300} );
        atur(tableProduk, new int []{100,300,300,300} );
        noTable();
        tgl.setText(dtf.format(now));
//          hitungTotalHarga();

    }
    private void cetakDataLayanan()
  {

      AD.makeConnection();
      
      try
      {
        String file ="D:\\generete_pdf\\"+"LaporanLayananTerlaris"+".pdf";
        com.itextpdf.text.Document document = new com.itextpdf.text.Document();
        PdfWriter writer= PdfWriter.getInstance(document, new FileOutputStream(file));
        document.open();
              
            Font font1 = new Font(Font.FontFamily.HELVETICA  , 14, Font.BOLD);
                       Font font2 = new Font(Font.FontFamily.HELVETICA  , 12);
                       Font font3 = new Font(Font.FontFamily.HELVETICA  , 20);
                       
                       Image img = Image.getInstance("nota.jpg");
                       
            document.add(img);
            Chunk c1 = new Chunk("LAPORAN LAYANAN  TERLARIS",font1);

            Chunk c3 = new Chunk("Tahun  : "+pilihTahun.getText(),font2);
           
            Paragraph p1 = new Paragraph();
            p1.setAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            p1.add(c1);
            
            
            Paragraph p3 = new Paragraph();
            p3.setAlignment(com.itextpdf.text.Element.ALIGN_LEFT);
            p3.add(c3);
            p3.setSpacingAfter(10);
            
           
           
           document.add(p1);
           document.add(p3);
            
            
            
   
            
           
            

            
           
            PdfPTable table1 = new PdfPTable(4);
            table1.addCell("No");
            table1.addCell("Bulan");
            table1.addCell("Nama Layanan");
            table1.addCell("Jumlah Layanan");
                
   
 
              for (int i = 0; i <tableTransaksiLayanan.getRowCount(); i++) {
              String No = (String)tableTransaksiLayanan.getValueAt(i, 0);
              String Bulan = (String)tableTransaksiLayanan.getValueAt(i,1);
              String lyn= (String)tableTransaksiLayanan.getValueAt(i,2);
              String jum= (String)tableTransaksiLayanan.getValueAt(i,3);

              
              table1.addCell(No);
              table1.addCell(Bulan);
              table1.addCell(lyn);
              table1.addCell(jum);

          }
              
           
           
            document.add(table1);
           
            Paragraph p4 = new Paragraph();
            p4.setSpacingBefore(5);
            document.add(p4);
          
           Chunk tgl = new Chunk("Dicetak Tgl : "+this.tgl.getText(),font2);
             Paragraph ptgl = new Paragraph();
             ptgl.add(tgl)
                ;
             ptgl.setAlignment(com.itextpdf.text.Element.ALIGN_BOTTOM);
             ptgl.setSpacingBefore(20);
             document.add(ptgl);
               
           
            
            
            
            
            
            
            document.close();
            writer.close();
            
            File myFile = new File("D:\\generete_pdf\\"+"LaporanLayananTerlaris"+".pdf");
            Desktop.getDesktop().open(myFile);
           
            
      }
      catch(Exception e)
      {
          System.out.println(e);
      }
       AD.closeConnection();
  }
    private void cetakDataProduk()
  {

      AD.makeConnection();
      
      try
      {
        String file ="F:\\generete_pdf\\"+"LaporanProdukTerlaris"+".pdf";
        com.itextpdf.text.Document document = new com.itextpdf.text.Document();
        PdfWriter writer= PdfWriter.getInstance(document, new FileOutputStream(file));
        document.open();
              
            Font font1 = new Font(Font.FontFamily.HELVETICA  , 14, Font.BOLD);
                       Font font2 = new Font(Font.FontFamily.HELVETICA  , 12);
                       Font font3 = new Font(Font.FontFamily.HELVETICA  , 20);
                       
                       Image img = Image.getInstance("nota.jpg");
                       
            document.add(img);
            Chunk c1 = new Chunk("LAPORAN PRODUK  TERLARIS",font1);

            Chunk c3 = new Chunk("Tahun  : "+pilihTahun.getText(),font2);
           
            Paragraph p1 = new Paragraph();
            p1.setAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            p1.add(c1);
            
            
            Paragraph p3 = new Paragraph();
            p3.setAlignment(com.itextpdf.text.Element.ALIGN_LEFT);
            p3.add(c3);
            p3.setSpacingAfter(10);
            
           
           
           document.add(p1);
           document.add(p3);
            
            
            
   
            
           
            

            
           
            PdfPTable table1 = new PdfPTable(4);
            table1.addCell("No");
            table1.addCell("Bulan");
            table1.addCell("Nama Produk");
            table1.addCell("Jumlah Barang");
                
   
 
              for (int i = 0; i <tableProduk.getRowCount(); i++) {
              String No = (String)tableProduk.getValueAt(i, 0);
              String Bulan = (String)tableProduk.getValueAt(i,1);
              String lyn= (String)tableProduk.getValueAt(i,2);
              String jum = (String)tableProduk.getValueAt(i, 3);
              
              table1.addCell(No);
              table1.addCell(Bulan);
              table1.addCell(lyn);
              table1.addCell(jum);

          }
              
           
           
            document.add(table1);
           
            Paragraph p4 = new Paragraph();
            p4.setSpacingBefore(5);
            document.add(p4);
          
           Chunk tgl = new Chunk("Dicetak Tgl : "+this.tgl.getText(),font2);
             Paragraph ptgl = new Paragraph();
             ptgl.add(tgl)
                ;
             ptgl.setAlignment(com.itextpdf.text.Element.ALIGN_BOTTOM);
             ptgl.setSpacingBefore(20);
             document.add(ptgl);
               
           
            
            
            
            
            
            
            document.close();
            writer.close();
            
            File myFile = new File("F:\\generete_pdf\\"+"LaporanProdukTerlaris"+".pdf");
            Desktop.getDesktop().open(myFile);
           
            
      }
      catch(Exception e)
      {
          System.out.println(e);
      }
       AD.closeConnection();
  }
   public final void tampilTransaksiProduk()
 {
     AD.makeConnection();

     String sql ="SELECT bulan, nama_produk, jumlah FROM (select monthname(A.CREATED_AT) \"bulan\", B.nama \"nama_produk\", SUM(A.JUMLAH) \"jumlah\" from detail_transaksi_produk A INNER JOIN produk B USING(ID_PRODUK) INNER JOIN transaksi_produk C USING(ID_TRANSAKSI_PRODUK) WHERE YEAR(A.CREATED_AT)="+pilihTahun.getText()+" AND C.STATUS='Lunas' GROUP BY month(A.CREATED_AT), A.ID_PRODUK ORDER BY month(A.CREATED_AT) ASC, SUM(A.JUMLAH) DESC) X GROUP BY bulan";
    try
    {
        
       Statement stm= con.createStatement();
       ResultSet rs= stm.executeQuery(sql);
   
        
        if (rs!=null) {
            
            while(rs.next())
            {
                String bulan= rs.getString("bulan");
                String namaProduk = rs.getString("nama_produk");
                String jumlah = rs.getString("jumlah");
                
                    if (bulan.equalsIgnoreCase("May")) {
                    tabelModel2.insertRow(4, new Object[]{"",bulan,namaProduk, jumlah});
                    tabelModel2.removeRow(5);
                }
                    else if(bulan.equalsIgnoreCase("April"))
                    {
                             tabelModel2.insertRow(3, new Object[]{"",bulan,namaProduk, jumlah});
                             tabelModel2.removeRow(4);
                    }
            
            }
            rs.close();
            stm.close();
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
  
    
 public void tampilTransaksi()
 {
     AD.makeConnection();

     String sql ="select * from\n" +
"(select monthname(A.created_at) \"Bulan\", concat(C.nama, ' ', D.nama) \"Nama\", sum(A.jumlah) \"Jumlah_Penjualan\" from detail_transaksi_layanan A\n" +
"inner join harga_layanan B on A.id_harga_layanan=B.id_harga_layanan\n" +
"inner join layanan C on B.id_layanan=.C.id_layanan\n" +
"inner join ukuran_hewan D on B.id_ukuran_hewan=D.id_ukuran_hewan\n" +
"INNER join transaksi_layanan F USING(ID_TRANSAKSI_LAYANAN) \n" +
"WHERE YEAR(A.CREATED_AT)="+pilihTahun.getText()+" aND F.STATUS='Lunas'\n" +
"group by monthname(A.created_at), B.id_harga_layanan\n" +
" ORDER BY month(A.CREATED_AT) ASC, sum(A.jumlah) DESC) X GROUP BY bulan";
    try
    {
        
       Statement stm= con.createStatement();
       ResultSet rs= stm.executeQuery(sql);
   
        
        if (rs!=null) {
            
            while(rs.next())
            {
         
                String bulan  = rs.getString("bulan");
                String nama= rs.getString("nama");
                String jum = rs.getString("jumlah_penjualan");
                if (bulan.equalsIgnoreCase("May")) {
                    tabelModel.insertRow(4, new Object[]{"",bulan,nama, jum});
                    tabelModel.removeRow(5);
                }
                else if (bulan.equalsIgnoreCase("april"))
                {
                        tabelModel.insertRow(3, new Object[]{"",bulan,nama, jum});
                    tabelModel.removeRow(4);
                }
                    
            
            }
            
            
            rs.close();
            stm.close();
        }
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
        pilihTahun = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        tgl = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setIconImage(
            new javax.swing.ImageIcon(getClass().getResource("/icon/dogg.png")).getImage());

        mainPane.setBackground(new java.awt.Color(255, 255, 255));

        tableTransaksiLayanan.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        tableTransaksiLayanan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, "Jan", null, "0"},
                {null, "Feb", null, "0"},
                {null, "Maret", null, "0"},
                {null, "April", null, "0"},
                {null, "Mei", null, "0"},
                {null, "Juni", null, "0"},
                {null, "Juli", null, "0"},
                {null, "Agustus", null, "0"},
                {null, "Sep", null, "0"},
                {null, "Oktober", null, "0"},
                {null, "November", null, "0"},
                {null, "Desember", null, null}
            },
            new String [] {
                "Nomor", "Bulan", "Nama Layanan", "Jumlah Layanan"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true, true
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
                .addGap(36, 36, 36)
                .addComponent(jLabel19))
        );

        jLabel20.setFont(new java.awt.Font("Elephant", 1, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(0, 0, 0));
        jLabel20.setText("LAPORAN  LAYANAN TERLARIS");

        jLabel21.setFont(new java.awt.Font("Elephant", 1, 14)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(0, 0, 0));
        jLabel21.setText("LAPORAN  PRODUK  TERLARIS");

        tableProduk.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        tableProduk.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, "Januari ", null, null},
                {null, "Febuari", null, null},
                {null, "Maret", null, null},
                {null, "April", null, null},
                {null, "Mei", null, null},
                {null, "Juni", null, null},
                {null, "Juli", null, null},
                {null, "Agustus", null, null},
                {null, "Sep", null, null},
                {null, "Oktober", null, null},
                {null, "Nov", null, null},
                {null, "Desember", null, null}
            },
            new String [] {
                "Nomor", "Bulan", "Nama Produk", "Jumlah"
            }
        ));
        tableProduk.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableProdukMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tableProduk);

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(51, 51, 51));
        jLabel7.setText("Tahun");

        jButton1.setText("LAPORAN");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("BUAT PDF");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        tgl.setBackground(new java.awt.Color(0, 0, 0));
        tgl.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        javax.swing.GroupLayout mainPaneLayout = new javax.swing.GroupLayout(mainPane);
        mainPane.setLayout(mainPaneLayout);
        mainPaneLayout.setHorizontalGroup(
            mainPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(mainPaneLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(mainPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2)
                    .addGroup(mainPaneLayout.createSequentialGroup()
                        .addGroup(mainPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 421, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 665, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(mainPaneLayout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(12, 12, 12)
                                .addComponent(pilihTahun, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(23, 23, 23)
                        .addGroup(mainPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tgl, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(mainPaneLayout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 637, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 422, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 32, Short.MAX_VALUE))
        );
        mainPaneLayout.setVerticalGroup(
            mainPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPaneLayout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(mainPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPaneLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tgl, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18))
                    .addGroup(mainPaneLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(mainPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(mainPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(mainPaneLayout.createSequentialGroup()
                                    .addGap(8, 8, 8)
                                    .addComponent(jLabel7))
                                .addComponent(pilihTahun, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(12, 12, 12)))
                .addGroup(mainPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainPaneLayout.createSequentialGroup()
                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(mainPaneLayout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addGroup(mainPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(mainPaneLayout.createSequentialGroup()
                                .addGap(42, 42, 42)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(81, 81, 81)
                .addComponent(jButton2))
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

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        tampilTransaksi();
        tampilTransaksiProduk();
        noTable();
    }//GEN-LAST:event_jButton1ActionPerformed

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

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        // TODO add your handling code here:
        MenuReport m = new MenuReport();
        this.setVisible(false );
        m.setVisible(true);
        m.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }//GEN-LAST:event_jLabel2MouseClicked

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

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:

        cetakDataLayanan();
        cetakDataProduk();
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
            java.util.logging.Logger.getLogger(reportTerlaris.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(reportTerlaris.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(reportTerlaris.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(reportTerlaris.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new reportTerlaris().setVisible(true);
          
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JPanel mainPane;
    private javax.swing.JTextField pilihTahun;
    private javax.swing.JTable tableProduk;
    private javax.swing.JTable tableTransaksiLayanan;
    private javax.swing.JLabel tgl;
    // End of variables declaration//GEN-END:variables
}
