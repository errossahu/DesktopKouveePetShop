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
import com.placeholder.PlaceHolder;
import exception.CekAngka;
import exception.CekHuruf;
import exception.PanjangData;
import java.awt.EventQueue;
import java.awt.event.KeyEvent;
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
public class MenuReportTransaksi extends javax.swing.JFrame {

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
    public MenuReportTransaksi() {
            
        initComponents();

        tabelModel = (DefaultTableModel) tableTransaksiLayanan.getModel();
        tabelModel2 = (DefaultTableModel) tableProduk.getModel();
        
        atur(tableTransaksiLayanan, new int []{100,200,100,150,150,150,200,150,200,150,150,150,150} );
        atur(tableProduk, new int []{100,200,100,150,150,150,200,150,200,150,150,150,150} );
    
          tampilTransaksi();
          tampilTransaksiProduk();
}
   public final void tampilTransaksiProduk()
 {
     AD.makeConnection();

     String sql ="SELECT A.ID_TRANSAKSI_produk,A.created_At, A.created_by, A.modified_by, A.modified_At ,A.subtotal,A.diskon,A.TOTAL,A.STATUS, b.NAMA , C.NAMA  , f.NAMA FROM transaksi_produk as A LEFT OUTER JOIN pegawai AS b on a.ID_CUSTOMER_SERVICE = b.ID_PEGAWAI LEFT OUTER join hewan c USING(ID_HEWAN) LEFT OUTER join pelanggan f USING (id_pelanggan) where a.status like 'Lunas'";
    try
    {
        
       Statement stm= con.createStatement();
       ResultSet rs= stm.executeQuery(sql);
   
        
        if (rs!=null) {
            
            while(rs.next())
            {
         
                String idTransaksiLayanan=rs.getString("A.ID_TRANSAKSI_produk");
                String createdAt= rs.getString("A.created_At");
                String created_by= rs.getString("A.created_by");
                String modified_by= rs.getString("A.MODIFIED_bY");
                String modified_At= rs.getString("Modified_AT");
                String subtotal = String.valueOf(rs.getString("A.SUBtOTAL"));
                String diskon = String.valueOf(rs.getString("A.diskon"));
                String total = String.valueOf(rs.getString("A.total"));
                String Status= rs.getString("A.status");
                String namaPegawai= rs.getString("b.nama");
                String namaKaka= rs.getString("c.Nama");
                String namaPelanggan =rs.getString("f.nama");

                
                String[] dataField={idTransaksiLayanan,namaPegawai,namaKaka,namaPelanggan,subtotal,diskon,total,Status,created_by,createdAt,modified_by,modified_At};
                tabelModel2.addRow(dataField);
                
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
  
    
 public void tampilTransaksi()
 {
     AD.makeConnection();

     String sql ="SELECT A.progress, A.ID_TRANSAKSI_LAYANAN,A.created_At, A.created_by, A.modified_by, A.modified_At ,A.subtotal,A.diskon,A.TOTAL,A.STATUS, b.NAMA , C.NAMA  , f.NAMA FROM transaksi_layanan as A LEFT OUTER JOIN pegawai AS b on a.ID_CUSTOMER_SERVICE = b.ID_PEGAWAI LEFT OUTER join hewan c USING(ID_HEWAN) LEFT OUTER join pelanggan f USING (id_pelanggan) where a.status='Lunas'";
    try
    {
        
       Statement stm= con.createStatement();
       ResultSet rs= stm.executeQuery(sql);
   
        
        if (rs!=null) {
            
            while(rs.next())
            {
         
                String idTransaksiLayanan=rs.getString("A.ID_TRANSAKSI_LAYANAN");
                String createdAt= rs.getString("A.created_At");
                String created_by= rs.getString("A.created_by");
                String modified_by= rs.getString("A.MODIFIED_bY");
                String modified_At= rs.getString("Modified_AT");
                String subtotal = String.valueOf(rs.getString("A.SUBtOTAL"));
                String diskon = String.valueOf(rs.getString("A.diskon"));
                String total = String.valueOf(rs.getString("A.total"));
                String Status= rs.getString("A.status");
                String namaPegawai= rs.getString("b.nama");
                String namaKaka= rs.getString("c.Nama");
                String namaPelanggan =rs.getString("f.nama");
                String progress = rs.getString("A.Progress");
                
                
                String[] dataField={idTransaksiLayanan,namaPegawai,namaKaka,namaPelanggan,subtotal,diskon,total,progress,Status,created_by,createdAt,modified_by,modified_At};
                tabelModel.addRow(dataField);
                
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setIconImage(
            new javax.swing.ImageIcon(getClass().getResource("/icon/dogg.png")).getImage());

        mainPane.setBackground(new java.awt.Color(255, 255, 255));

        tableTransaksiLayanan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID_Transaksi", "Nama CS", "Nama Hewan", "Nama Pelanggan", "SubTotal", "Diskon", "Total", "Progrees", "Status", "Created_By", "Creted_At ", "Modified_BY", "Modified_AT"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, true, false, false, false, false, false
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
            tableTransaksiLayanan.getColumnModel().getColumn(1).setResizable(false);
            tableTransaksiLayanan.getColumnModel().getColumn(2).setResizable(false);
            tableTransaksiLayanan.getColumnModel().getColumn(3).setResizable(false);
            tableTransaksiLayanan.getColumnModel().getColumn(4).setResizable(false);
            tableTransaksiLayanan.getColumnModel().getColumn(5).setResizable(false);
            tableTransaksiLayanan.getColumnModel().getColumn(6).setResizable(false);
            tableTransaksiLayanan.getColumnModel().getColumn(7).setResizable(false);
            tableTransaksiLayanan.getColumnModel().getColumn(8).setResizable(false);
            tableTransaksiLayanan.getColumnModel().getColumn(9).setResizable(false);
            tableTransaksiLayanan.getColumnModel().getColumn(10).setResizable(false);
            tableTransaksiLayanan.getColumnModel().getColumn(12).setResizable(false);
        }

        jPanel3.setBackground(new java.awt.Color(255, 139, 36));

        jLabel18.setFont(new java.awt.Font("Elephant", 1, 24)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(0, 0, 0));
        jLabel18.setText("REPORT TRANSAKSI LUNAS");

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
                        .addContainerGap(505, Short.MAX_VALUE))
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
        jLabel20.setText("REPORT  TRANSAKSI LAYANAN");

        jLabel21.setFont(new java.awt.Font("Elephant", 1, 14)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(0, 0, 0));
        jLabel21.setText("REPORT  TRANSAKSI PRODUK");

        tableProduk.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID_Transaksi", "Nama CS", "Nama Hewan", "Nama Pelanggan", "SubTotal", "Diskon", "Total", "Status", "Created_By", "Creted_At ", "Modified_BY", "Modified_AT"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableProduk.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableProdukMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tableProduk);

        javax.swing.GroupLayout mainPaneLayout = new javax.swing.GroupLayout(mainPane);
        mainPane.setLayout(mainPaneLayout);
        mainPaneLayout.setHorizontalGroup(
            mainPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(mainPaneLayout.createSequentialGroup()
                .addGroup(mainPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addComponent(jScrollPane3)
                    .addGroup(mainPaneLayout.createSequentialGroup()
                        .addGap(244, 244, 244)
                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(mainPaneLayout.createSequentialGroup()
                .addGap(243, 243, 243)
                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        mainPaneLayout.setVerticalGroup(
            mainPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPaneLayout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19)
                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 233, Short.MAX_VALUE)
                .addGap(160, 160, 160))
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
            java.util.logging.Logger.getLogger(MenuReportTransaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MenuReportTransaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MenuReportTransaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MenuReportTransaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MenuReportTransaksi().setVisible(true);
          
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JPanel mainPane;
    private javax.swing.JTable tableProduk;
    private javax.swing.JTable tableTransaksiLayanan;
    // End of variables declaration//GEN-END:variables
}
