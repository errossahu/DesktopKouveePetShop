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
public class MenuKasirTransaksiLayanan extends javax.swing.JFrame {

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
    public MenuKasirTransaksiLayanan() {
            
        initComponents();
        tampilComboBoxSupllier();
        tabelModel = (DefaultTableModel) tabelTransaksiProduk.getModel();
        tampilNamaProduk();
        txtTotalSeluruh.setEnabled(false);
        setEnableInput(false);
        txtSubTotal.setEnabled(false);
        txtDiskon.setText("0");
        txtJumlah.setText("0");
        
          atur(tabelTransaksiProduk, new int []{100,300,300,150,150,150,200,150,200} );
    txtHarga.setText("0");
    txtTotal.setText("0");
    tampilUkuran();
    jComboBoxIdTransaksi.setSelectedItem(LoginSession.getIdTransaksi());
        tampilHargaLayanan(LoginSession.getIdTransaksi());
        searchNamaHewan(LoginSession.getIdTransaksi());
        searchNamaPegawai(LoginSession.getIdTransaksi());
        tabelTransaksiProduk.requestFocus();

        //    nomorUrut();
        //
        hitungTotalHarga();
        if(txtNamaPelanggan.getText().length()<=1)
        {
            txtNamaPelanggan.setText("QUEST");
            txtNamaHewan.setText("QUEST");
            txtDiskon.setEnabled(false);
            txtTotalSeluruh.setText(txtSubTotal.getText());
        }
        else
        {
            txtDiskon.setEnabled(true);
            txtTotalSeluruh.setText("");
        }

    }
  private void cetakData()
  {
      
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
   public void nomorUrut(int i)
   {
       int baris = tabelModel.getRowCount();
           for (i = 0; i < baris; i++) {
               String nomor =String.valueOf(i+1);
               tabelModel.setValueAt(nomor +".", i,0);
           
       }
   }
   public void tampilUkuran()
   {
       AD.makeConnection();
        try
        {
            
            String sql = "Select nama from ukuran_hewan where aktif = 1 ";
            Class.forName("com.mysql.jdbc.Driver");
            Statement stm = AD.GETcon().createStatement();
            ResultSet rs= stm.executeQuery(sql);
            while (rs.next())
                    {
                        String Nama = rs.getString("nama");
                        
                        ukuran.add(Nama);
                    }
            rs.close();
            stm.close();
        }
        
        catch(Exception e)
        {
            System.out.println(e);
        }
        AD.closeConnection();
   }
   public void tampilNamaProduk()
    {AD.makeConnection();
        try
        {
            
            String sql = "Select nama from layanan where aktif = 1 ";
            Class.forName("com.mysql.jdbc.Driver");
            Statement stm = AD.GETcon().createStatement();
            ResultSet rs= stm.executeQuery(sql);
            while (rs.next())
                    {
                        String Nama = rs.getString("nama");
                        nama.add(Nama);
                       
                    }
            rs.close();
            stm.close();
        }
        
        catch(Exception e)
        {
            System.out.println(e);
        }
        AD.closeConnection();
    }
 
public void autoCompletNamaProduk(String nama)
    {
        String compelet = "";
        int start = nama.length();
        int last = nama.length();
        int a ;
        for(a= 0; a<this.nama.size(); a++)
        {
            if (this.nama.get(a).toString().startsWith(nama)) {
                
                compelet = this.nama.get(a).toString();
                last = compelet.length() ;
                break ;
            }
        }
        
        if (last > start ) {
            
            txtNamaLayanan.setText(compelet);
            txtNamaLayanan.setCaretPosition(last);
            txtNamaLayanan.moveCaretPosition(start);
        }
    }
public void autoTextCompletUkuranHewan(String nama)
{ String compelet = "";
        int start = nama.length();
        int last = nama.length();
        int a ;
        for(a= 0; a<this.ukuran.size(); a++)
        {
            if (this.ukuran.get(a).toString().startsWith(nama)) {
                
                compelet = this.ukuran.get(a).toString();
                last = compelet.length() ;
                break ;
            }
        }
                if (last > start ) {
            
            txtUkuran.setText(compelet);
            txtUkuran.setCaretPosition(last);
            txtUkuran.moveCaretPosition(start);
        }
    
}
    public void setTextInput()
    {
        txtNamaLayanan.setText("");
        txtJumlah.setText("");
        txtHarga.setText("");
        txtTotal.setText("");
        txtNomor.setText("");
        txtUkuran.setText("");
    }
    public void setEnableInput(boolean  nilai)
    {
        txtNamaLayanan.setEnabled(nilai);
        txtJumlah.setEnabled(nilai);
        txtUkuran.setEnabled(nilai);
    }
   public void tampilComboBoxSupllier()
    {
        AD.makeConnection();
        try
        {
            String sql = "Select id_transaksi_layanan from  transaksi_layanan where status='Menunggu Pembayaran'";
            Class.forName("com.mysql.jdbc.Driver"); 
            Statement st = AD.GETcon().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
           
                jComboBoxIdTransaksi.addItem(rs.getString("id_transaksi_layanan"));

            }
            

            
        }
        catch(Exception e)
        {
            System.out.println("Gagal Menampilkan Supplier");
            System.out.println(e);
        }
        AD.closeConnection();
    }
   public void hitungTotalHarga()
   {
       
           int total = 0;
           int totalSeluruh =0;
           
           int diskon = Integer.parseInt(txtDiskon.getText());
           for (int i =0; i< tabelTransaksiProduk.getRowCount(); i++){
                int amount = Integer.parseInt((String)tabelTransaksiProduk.getValueAt(i, 5));
                 total += amount;
                }
            txtSubTotal.setText(""+total);
           totalSeluruh= total-diskon;
           txtTotalSeluruh.setText(String.valueOf(totalSeluruh));
   }
   public void searchNamaPegawai(String id)
   {
       AD.makeConnection(); 
       String sql = "Select b.nama from transaksi_layanan as a inner join Pegawai as b on a.ID_CUSTOMER_SERVICE= b.ID_PEGAWAI where a.id_transaksi_layanan='"+id+"'";
        try
    {
        
       Statement stm= con.createStatement();
       ResultSet rs= stm.executeQuery(sql);
   
        
        if (rs!=null) {
            
            while(rs.next())
            {
                    String namaCs = rs.getString("b.nama");
                    txtNamaCs.setText(namaCs);
   
                    
            }
            rs.close();
            stm.close();
        }
        

    
    
    }
    catch(SQLException e)
    {
        System.out.println(e);
    }
    

     AD.closeConnection();
   }
   public void searchNamaHewan(String id)
   {
       AD.makeConnection();
       String sql = "SELECT b.nama nama,d.nama , F.NAMA AS jenis_hewan from transaksi_layanan as a INNER JOIN hewan as b on a.ID_HEWAN = b.ID_HEWAN INNER JOIN pelanggan as d on b.ID_PELANGGAN = d.ID_PELANGGAN \n" +
"INNER JOIN jenis_hewan AS F USING(ID_JENIS_HEWAN) WHERE a.ID_TRANSAKSI_layanan ='"+id+"'";
        try
    {
        
       Statement stm= con.createStatement();
       ResultSet rs= stm.executeQuery(sql);
   
        
        if (rs!=null) {
            
            while(rs.next())
            {
                    String namaHewan = rs.getString("nama");
                    txtNamaHewan.setText(namaHewan);
                    String namaPelanggan = rs.getString("d.nama");
                    txtNamaPelanggan.setText(namaPelanggan);
                    txtJenisHewan.setText(rs.getString("Jenis_Hewan"));
            }
            hitungTotalHarga();
            rs.close();
            stm.close();
        }
        

    
    
    }
    catch(SQLException e)
    {
        System.out.println(e);
    }
    

     AD.closeConnection();


       
   }
   public void tampilHargaLayanan(String id )
    {
        
        int a = tabelModel.getRowCount() ;
        for (int i = 0; i < a; i++) {
            tabelModel.removeRow(0);
        }
        AD.makeConnection();
        String sql = "SELECT f.nama as layanan,g.NAMA as ukuran ,b.created_by,b.created_at,b.modified_by, b.modified_At,b.id_detail_transaksi_layanan id ,a.HARGA, b.jumlah,C.subtotal ,b.TOTAL_HARGA  FROM detail_transaksi_layanan as b\n" +
                    "INNER JOIN harga_layanan AS a on a.ID_HARGA_LAYANAN = b.ID_harga_layanan\n" +
                    "inner join layanan f USING (id_layanan)\n" +
                    "INNER JOIN ukuran_hewan g USING(id_ukuran_hewan)\n" +
                    "INNER JOIN transaksi_layanan AS C ON B.ID_TRANSAKSI_layanan= C.ID_TRANSAKSI_layanan  where C.ID_TRANSAKSI_layanan='"+id+"'";

    try
    {
        
       Statement stm= con.createStatement();
       ResultSet rs= stm.executeQuery(sql);
   
        
        if (rs!=null) {
            
            while(rs.next())
            {
                int i = 0;
                String harga = rs.getString("a.harga");
                String jumlah = rs.getString("b.jumlah");
                String total = rs.getString("b.total_harga");
                String idDetail = rs.getString("id");
                String produk = rs.getString("layanan");
                String ukuran = rs.getString("ukuran");
                String crated_by = rs.getString("b.created_by");
                String created_at = rs.getString("b.created_at");
                String modifid_by = rs.getString("b.modified_by");
                String modfied_at = rs.getString ("b.modified_at");
                i++;
//                String[] dataField={};
//                tabelModel7.addRow(dataField);
            String idx = String.valueOf(i);
            int c = 0 ;


            String[] dataField= {idDetail,produk,ukuran,harga,jumlah,total,crated_by,created_at,modifid_by,modfied_at};
            tabelModel.addRow(dataField);
 
            }
                               hitungTotalHarga();
            rs.close();
            stm.close();
        }
        

    
    
    }
    catch(SQLException e)
    {
        System.out.println(e);
    }
    

     AD.closeConnection();

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
        editTransaksiProduk = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        btnHapus = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelTransaksiProduk = new javax.swing.JTable();
        jComboBoxIdTransaksi = new javax.swing.JComboBox<>();
        txtSubTotal = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtJumlah = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        btnCari = new javax.swing.JButton();
        txtNamaLayanan = new javax.swing.JTextField();
        txtDiskon = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtTotalSeluruh = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txtNamaPelanggan = new javax.swing.JLabel();
        txtNamaHewan = new javax.swing.JLabel();
        txtNomor = new javax.swing.JLabel();
        txtHarga = new javax.swing.JLabel();
        txtTotal = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jButton5 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jButton1 = new javax.swing.JButton();
        txtNamaCs = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        txtUkuran = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtJenisHewan = new javax.swing.JLabel();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 32767));
        txtJenisHewan1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setIconImage(
            new javax.swing.ImageIcon(getClass().getResource("/icon/dogg.png")).getImage());

        mainPane.setBackground(new java.awt.Color(255, 255, 255));

        editTransaksiProduk.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setBackground(new java.awt.Color(0, 0, 0));
        jLabel2.setFont(new java.awt.Font("Bell MT", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));

        jLabel4.setBackground(new java.awt.Color(0, 0, 0));
        jLabel4.setFont(new java.awt.Font("Bell MT", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Nama Pelanggan   :");

        jLabel7.setBackground(new java.awt.Color(0, 0, 0));
        jLabel7.setFont(new java.awt.Font("Bell MT", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Jumlah ");

        jLabel8.setBackground(new java.awt.Color(0, 0, 0));
        jLabel8.setFont(new java.awt.Font("Bell MT", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Total");

        jLabel9.setBackground(new java.awt.Color(0, 0, 0));
        jLabel9.setFont(new java.awt.Font("Bell MT", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Harga Jual");

        btnHapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/deleteProduk.png"))); // NOI18N
        btnHapus.setText("HAPUS");
        btnHapus.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnHapus.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnHapus.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/saveTransaksi.png"))); // NOI18N
        jButton2.setText("SIMPAN");
        jButton2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jButton2.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        tabelTransaksiProduk.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No Resi", "Nama Layanan", "Ukuran", "Harga", "Jumlah", "Harga", "Creeated_by", "Created_at", "Modified_By", "Modified_At"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelTransaksiProduk.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelTransaksiProdukMouseClicked(evt);
            }
        });
        tabelTransaksiProduk.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tabelTransaksiProdukKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tabelTransaksiProdukKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tabelTransaksiProduk);
        if (tabelTransaksiProduk.getColumnModel().getColumnCount() > 0) {
            tabelTransaksiProduk.getColumnModel().getColumn(0).setResizable(false);
            tabelTransaksiProduk.getColumnModel().getColumn(1).setResizable(false);
            tabelTransaksiProduk.getColumnModel().getColumn(2).setResizable(false);
            tabelTransaksiProduk.getColumnModel().getColumn(3).setResizable(false);
            tabelTransaksiProduk.getColumnModel().getColumn(4).setResizable(false);
            tabelTransaksiProduk.getColumnModel().getColumn(5).setResizable(false);
            tabelTransaksiProduk.getColumnModel().getColumn(6).setResizable(false);
            tabelTransaksiProduk.getColumnModel().getColumn(7).setResizable(false);
            tabelTransaksiProduk.getColumnModel().getColumn(8).setResizable(false);
            tabelTransaksiProduk.getColumnModel().getColumn(9).setResizable(false);
        }

        jComboBoxIdTransaksi.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-Cari ID Trasaksi" }));
        jComboBoxIdTransaksi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jComboBoxIdTransaksiMouseClicked(evt);
            }
        });
        jComboBoxIdTransaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxIdTransaksiActionPerformed(evt);
            }
        });
        jComboBoxIdTransaksi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jComboBoxIdTransaksiKeyPressed(evt);
            }
        });

        jLabel10.setBackground(new java.awt.Color(0, 0, 0));
        jLabel10.setFont(new java.awt.Font("Bell MT", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Sub Total");

        jLabel11.setBackground(new java.awt.Color(0, 0, 0));
        jLabel11.setFont(new java.awt.Font("Bell MT", 1, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Nomor                    :");

        txtJumlah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtJumlahActionPerformed(evt);
            }
        });
        txtJumlah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtJumlahKeyPressed(evt);
            }
        });

        jLabel12.setBackground(new java.awt.Color(0, 0, 0));
        jLabel12.setFont(new java.awt.Font("Bell MT", 1, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Diskon");

        btnCari.setText("CARI");
        btnCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariActionPerformed(evt);
            }
        });
        btnCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnCariKeyPressed(evt);
            }
        });

        txtNamaLayanan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNamaLayananActionPerformed(evt);
            }
        });
        txtNamaLayanan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNamaLayananKeyPressed(evt);
            }
        });

        txtDiskon.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDiskonKeyPressed(evt);
            }
        });

        jLabel13.setBackground(new java.awt.Color(0, 0, 0));
        jLabel13.setFont(new java.awt.Font("Bell MT", 1, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Nama Hewan         :");

        jLabel14.setBackground(new java.awt.Color(0, 0, 0));
        jLabel14.setFont(new java.awt.Font("Bell MT", 1, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("Total");

        jLabel15.setBackground(new java.awt.Color(0, 0, 0));
        jLabel15.setFont(new java.awt.Font("Bell MT", 1, 12)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("Nama Layanan");

        txtNamaPelanggan.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N

        txtNamaHewan.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N

        txtNomor.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N

        txtHarga.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txtTotal.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "AKSI", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Dialog", 0, 11), new java.awt.Color(0, 0, 0))); // NOI18N

        jButton5.setText("TAMBAH");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton4.setText("EDIT");
        jButton4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton4MouseClicked(evt);
            }
        });
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton5)
                    .addComponent(jButton4))
                .addGap(0, 10, Short.MAX_VALUE))
        );

        jButton1.setText("BAYAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        txtNamaCs.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N

        jLabel16.setBackground(new java.awt.Color(0, 0, 0));
        jLabel16.setFont(new java.awt.Font("Bell MT", 1, 12)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("Nama CS                 :");

        jLabel17.setBackground(new java.awt.Color(0, 0, 0));
        jLabel17.setFont(new java.awt.Font("Bell MT", 1, 12)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setText("Ukuran");

        txtUkuran.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUkuranActionPerformed(evt);
            }
        });
        txtUkuran.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtUkuranKeyPressed(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(255, 139, 36));

        jLabel18.setFont(new java.awt.Font("Elephant", 1, 24)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(0, 0, 0));
        jLabel18.setText("MENU KASIR PEMABAYARAN TRASAKSI");

        jLabel19.setBackground(new java.awt.Color(0, 0, 0));
        jLabel19.setFont(new java.awt.Font("Elephant", 0, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(0, 0, 0));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/arrow-back-icon.png"))); // NOI18N
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
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
                        .addComponent(jLabel5)
                        .addGap(43, 43, 43))))
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
                        .addComponent(jLabel5)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel19))
        );

        txtJenisHewan.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N

        txtJenisHewan1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        txtJenisHewan1.setForeground(new java.awt.Color(0, 0, 0));
        txtJenisHewan1.setText("/");

        javax.swing.GroupLayout editTransaksiProdukLayout = new javax.swing.GroupLayout(editTransaksiProduk);
        editTransaksiProduk.setLayout(editTransaksiProdukLayout);
        editTransaksiProdukLayout.setHorizontalGroup(
            editTransaksiProdukLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editTransaksiProdukLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(editTransaksiProdukLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(editTransaksiProdukLayout.createSequentialGroup()
                        .addGroup(editTransaksiProdukLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(editTransaksiProdukLayout.createSequentialGroup()
                                .addGroup(editTransaksiProdukLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(editTransaksiProdukLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(editTransaksiProdukLayout.createSequentialGroup()
                                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(197, 197, 197))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, editTransaksiProdukLayout.createSequentialGroup()
                                        .addGroup(editTransaksiProdukLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtNamaPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(editTransaksiProdukLayout.createSequentialGroup()
                                                .addComponent(txtNamaHewan, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txtJenisHewan1, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txtJenisHewan, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(270, 270, 270)
                                        .addGroup(editTransaksiProdukLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGroup(editTransaksiProdukLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(editTransaksiProdukLayout.createSequentialGroup()
                                        .addComponent(txtNomor, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(editTransaksiProdukLayout.createSequentialGroup()
                                .addGroup(editTransaksiProdukLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(editTransaksiProdukLayout.createSequentialGroup()
                                        .addGroup(editTransaksiProdukLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtNamaLayanan, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(40, 40, 40)
                                        .addGroup(editTransaksiProdukLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtUkuran, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(28, 28, 28)
                                        .addGroup(editTransaksiProdukLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtJumlah, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(editTransaksiProdukLayout.createSequentialGroup()
                                        .addGap(383, 383, 383)
                                        .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(27, 27, 27)
                                .addGroup(editTransaksiProdukLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(editTransaksiProdukLayout.createSequentialGroup()
                                        .addComponent(txtHarga, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnHapus, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(editTransaksiProdukLayout.createSequentialGroup()
                                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(163, 163, 163)
                                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2))
                    .addGroup(editTransaksiProdukLayout.createSequentialGroup()
                        .addGroup(editTransaksiProdukLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(editTransaksiProdukLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(editTransaksiProdukLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(editTransaksiProdukLayout.createSequentialGroup()
                                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtDiskon))
                                    .addGroup(editTransaksiProdukLayout.createSequentialGroup()
                                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtTotalSeluruh))
                                    .addGroup(editTransaksiProdukLayout.createSequentialGroup()
                                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtSubTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1339, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButton1))
                            .addGroup(editTransaksiProdukLayout.createSequentialGroup()
                                .addComponent(btnCari)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBoxIdTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(390, 390, 390)
                                .addComponent(jLabel16)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(editTransaksiProdukLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtNamaCs, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 39, Short.MAX_VALUE))))
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        editTransaksiProdukLayout.setVerticalGroup(
            editTransaksiProdukLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editTransaksiProdukLayout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(editTransaksiProdukLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(editTransaksiProdukLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(editTransaksiProdukLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(editTransaksiProdukLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnCari, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jComboBoxIdTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(editTransaksiProdukLayout.createSequentialGroup()
                                .addGroup(editTransaksiProdukLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtNamaCs, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(8, 8, 8)
                        .addGroup(editTransaksiProdukLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNamaPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNomor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(editTransaksiProdukLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(editTransaksiProdukLayout.createSequentialGroup()
                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(editTransaksiProdukLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(editTransaksiProdukLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(editTransaksiProdukLayout.createSequentialGroup()
                                            .addGroup(editTransaksiProdukLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(txtNamaHewan, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(txtJenisHewan, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(txtJenisHewan1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(editTransaksiProdukLayout.createSequentialGroup()
                                        .addGap(16, 16, 16)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(40, 40, 40)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(editTransaksiProdukLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton2)
                            .addComponent(btnHapus))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(editTransaksiProdukLayout.createSequentialGroup()
                        .addGap(138, 138, 138)
                        .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39)
                        .addGroup(editTransaksiProdukLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(editTransaksiProdukLayout.createSequentialGroup()
                                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(9, 9, 9)
                                .addComponent(txtNamaLayanan, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(editTransaksiProdukLayout.createSequentialGroup()
                                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtUkuran, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(editTransaksiProdukLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(editTransaksiProdukLayout.createSequentialGroup()
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtJumlah, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(editTransaksiProdukLayout.createSequentialGroup()
                                    .addGroup(editTransaksiProdukLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(editTransaksiProdukLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(txtHarga, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(editTransaksiProdukLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSubTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(editTransaksiProdukLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDiskon, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(editTransaksiProdukLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTotalSeluruh, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jButton1)
                        .addGap(90, 90, 90))))
        );

        javax.swing.GroupLayout mainPaneLayout = new javax.swing.GroupLayout(mainPane);
        mainPane.setLayout(mainPaneLayout);
        mainPaneLayout.setHorizontalGroup(
            mainPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPaneLayout.createSequentialGroup()
                .addComponent(editTransaksiProduk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        mainPaneLayout.setVerticalGroup(
            mainPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPaneLayout.createSequentialGroup()
                .addComponent(editTransaksiProduk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
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

    private void txtUkuranKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUkuranKeyPressed
        // TODO add your handling code here:
        switch(evt.getKeyCode())
        {
            case KeyEvent.VK_BACK_SPACE:
            if (evt.getKeyCode()==KeyEvent.VK_BACK_SPACE) {

            }

            break ;
            case KeyEvent.VK_ENTER:
            txtUkuran.setText(txtUkuran.getText());
            if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                uh=  AC.searchUkuran(txtUkuran.getText());
                if(uh!=null)
                {
                    idUkuran = uh.getID();
                    hl = AC.searchHargaLayanan(idLayanan, idUkuran);
                    if(hl!=null)
                    {
                        idHarga_layanan = hl.getIdHargaLayanan();
                        System.out.println("Idnya Adalah : "+idHarga_layanan);
                        String harga= String.valueOf(hl.getHarga());
                        txtHarga.setText(harga);
                        txtJumlah.requestFocus();
                int harga1= Integer.parseInt(txtHarga.getText());
                int jum = Integer.parseInt(txtJumlah.getText());

                int total = harga1*jum;
                String totalHarga = String.valueOf(total);
                txtTotal.setText(totalHarga);

                    }
                    //                String harga=String.valueOf(L.getHarga());
                    //               txtHarga.setText(harga);

                    hitungTotalHarga();
                }

            }
            else
            {
                JOptionPane.showMessageDialog(this, "Layanan  Tidak Ditemukan");
            }

            break;

            default :
            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    String txt = txtUkuran.getText();
                    autoTextCompletUkuranHewan(txt);

                }
            });

        }
    }//GEN-LAST:event_txtUkuranKeyPressed

    private void txtUkuranActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUkuranActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUkuranActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:

        tl= new TransaksiLayanan();
        String X = JOptionPane.showInputDialog(null,"Jumlah Uang : Rp");
        int uang= Integer.parseInt(X) ;
        int totalHarga=  Integer.parseInt(txtTotalSeluruh.getText());
        if(uang < totalHarga)
        {
            JOptionPane.showMessageDialog(this, "Uang Tidak Cukup");
        }
        else
        {
            int Kembalian = totalHarga-uang;
            JOptionPane.showMessageDialog(this,"Besar Kembalian : Rp"+Kembalian);

        
            tl.setIdKasir(LoginSession.getIduser());
            tl.setSubTotal(Integer.parseInt(txtSubTotal.getText()));
            tl.setDiskon(Integer.parseInt(txtDiskon.getText()));
            tl.setTotal(Integer.parseInt(txtTotalSeluruh.getText()));
            String id = (String) jComboBoxIdTransaksi.getSelectedItem();
            KC.updatePembayaranTransaksi(tl, id);

        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        // TODO add your handling code here:
        setEnableInput(true);

        tambah= "edit" ;
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton4MouseClicked
        // TODO add your handling code here:
        txtNamaLayanan.requestFocus();
    }//GEN-LAST:event_jButton4MouseClicked

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        setEnableInput(true);
        setTextInput();
        tambah= "tambah" ;

    }//GEN-LAST:event_jButton5ActionPerformed

    private void txtDiskonKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDiskonKeyPressed
        // TODO add your handling code here:
        switch(evt.getKeyCode())
        {
            case KeyEvent.VK_BACK_SPACE:
            if (evt.getKeyCode()==KeyEvent.VK_BACK_SPACE) {

                txtJumlah.setText("");
            }

            break ;
            case KeyEvent.VK_ENTER:

            if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

                hitungTotalHarga();
            }
            break;

        }
    }//GEN-LAST:event_txtDiskonKeyPressed

    private void txtNamaLayananKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNamaLayananKeyPressed
        // TODO add your handling code here:
        switch(evt.getKeyCode())
        {
            case KeyEvent.VK_BACK_SPACE:
            if (evt.getKeyCode()==KeyEvent.VK_BACK_SPACE) {

            }

            break ;
            case KeyEvent.VK_ENTER:
            txtNamaLayanan.setText(txtNamaLayanan.getText());
            if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                L=  AC.searchLayanan(txtNamaLayanan.getText());
                if(L!=null)
                {
                    idLayanan = L.getIdLayanan();
                    txtUkuran.requestFocus();
                    //                String harga=String.valueOf(L.getHarga());
                    //               txtHarga.setText(harga);

                }

            }
            else
            {
                JOptionPane.showMessageDialog(this, "Layanan  Tidak Ditemukan");
            }

            break;

            default :
            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    String txt = txtNamaLayanan.getText();
                    autoCompletNamaProduk(txt);

                }
            });

        }
    }//GEN-LAST:event_txtNamaLayananKeyPressed

    private void txtNamaLayananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNamaLayananActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNamaLayananActionPerformed

    private void btnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariActionPerformed
        // TODO add your handling code here:
        String id = (String)jComboBoxIdTransaksi.getSelectedItem();
        tampilHargaLayanan(id);
        searchNamaHewan(id);
        searchNamaPegawai(id);
        tabelTransaksiProduk.requestFocus();

        //    nomorUrut();
        //
        hitungTotalHarga();
        if(txtNamaPelanggan.getText().length()<=1)
        {
            txtNamaPelanggan.setText("QUEST");
            txtNamaHewan.setText("QUEST");
            txtDiskon.setEnabled(false);
            txtTotalSeluruh.setText(txtSubTotal.getText());
        }
        else
        {
            txtDiskon.setEnabled(true);
            txtTotalSeluruh.setText("");
        }

    }//GEN-LAST:event_btnCariActionPerformed

    private void txtJumlahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtJumlahKeyPressed
        // TODO add your handling code here:
        switch(evt.getKeyCode())
        {
            case KeyEvent.VK_BACK_SPACE:
            if (evt.getKeyCode()==KeyEvent.VK_BACK_SPACE) {

            }

            break ;
            case KeyEvent.VK_ENTER:

            if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

                int harga= Integer.parseInt(txtHarga.getText());
                int jum = Integer.parseInt(txtJumlah.getText());

                int total = harga*jum;
                String totalHarga = String.valueOf(total);
                txtTotal.setText(totalHarga);

            }
            break;

        }
    }//GEN-LAST:event_txtJumlahKeyPressed

    private void txtJumlahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtJumlahActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtJumlahActionPerformed

    private void jComboBoxIdTransaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxIdTransaksiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxIdTransaksiActionPerformed

    private void jComboBoxIdTransaksiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboBoxIdTransaksiMouseClicked
        // TODO add your handling code here:
        txtNamaPelanggan.setText("");
        txtNamaHewan.setText("");
        txtNomor.setText("");
        txtNamaCs.setText("");
        txtUkuran.setText("");
        txtSubTotal.setText("");
        txtTotalSeluruh.setText("");
        txtJenisHewan.setText("");

        int a = tabelModel.getRowCount() ;
        for (int i = 0; i < a; i++) {
            tabelModel.removeRow(0);
        }
        setTextInput();
    }//GEN-LAST:event_jComboBoxIdTransaksiMouseClicked

    private void tabelTransaksiProdukMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelTransaksiProdukMouseClicked
        // TODO add your handling code here:
        int index= tabelTransaksiProduk.getSelectedRow();
        String ID       = (String) tabelTransaksiProduk.getValueAt(index, 0);
        String namax = (String) tabelTransaksiProduk.getValueAt(index, 1);
        String ukuran= (String) tabelTransaksiProduk.getValueAt(index,2);
        String Harga = (String) tabelTransaksiProduk.getValueAt(index, 3);
        String jumlah=(String) tabelTransaksiProduk.getValueAt(index, 4);
        String jumlahTotal = (String) tabelTransaksiProduk.getValueAt(index, 5);
        txtNomor.setText(ID);
        txtUkuran.setText(ukuran);
        txtNamaLayanan.setText(namax);
        txtJumlah.setText(jumlah);
        txtHarga.setText(Harga);
        txtTotal.setText(jumlahTotal);
    }//GEN-LAST:event_tabelTransaksiProdukMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        String idDetail = (String) jComboBoxIdTransaksi.getSelectedItem();

        if (tambah=="tambah") {

            Tl = new detailTransaksiLayanan();
            Tl.setIdTransaksi(idDetail);
            Tl.setId_Layanan(idHarga_layanan);

            int Jum = Integer.parseInt(txtJumlah.getText());

            Tl.setJumlah(Jum);
            int total =Integer.parseInt(txtTotal.getText());
            Tl.setTotalHarga(total);
            KC.tambahLayanan(Tl);
            tampilHargaLayanan(idDetail);
            searchNamaHewan(idDetail);
            //    nomorUrut();
            //
            hitungTotalHarga();
            setTextInput();
            setEnableInput(false);

        }
        if (tambah=="edit")
        {

                Tl = new detailTransaksiLayanan();


                Tl.setId_Layanan(idHarga_layanan);
                int Jum = Integer.parseInt(txtJumlah.getText());

                Tl.setJumlah(Jum);
                int total =Integer.parseInt(txtTotal.getText());
                Tl.setTotalHarga(total);
                KC.updateLayanan(Tl, txtNomor.getText());

                //    nomorUrut();
                //

                hitungTotalHarga();
                setTextInput();
                setEnableInput(false);
                tampilHargaLayanan(idDetail);
            
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusActionPerformed
        // TODO add your handling code here:

        KC.deleteLayanan(txtNomor.getText());
        String id = (String) jComboBoxIdTransaksi.getSelectedItem();
        tampilHargaLayanan(id);
        setTextInput();

    }//GEN-LAST:event_btnHapusActionPerformed

    private void tabelTransaksiProdukKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tabelTransaksiProdukKeyPressed
        // TODO add your handling code here:
         switch(evt.getKeyCode())
        {
            case KeyEvent.VK_BACK_SPACE:
            if (evt.getKeyCode()==KeyEvent.VK_BACK_SPACE) {

            }

            break ;
            case KeyEvent.VK_KP_UP:
                if(evt.getKeyCode()== KeyEvent.VK_KP_UP)
                {
                      int index= tabelTransaksiProduk.getSelectedRow();
        String ID       = (String) tabelTransaksiProduk.getValueAt(index, 0);
        String namax = (String) tabelTransaksiProduk.getValueAt(index, 1);
        String ukuran= (String) tabelTransaksiProduk.getValueAt(index,2);
        String Harga = (String) tabelTransaksiProduk.getValueAt(index, 3);
        String jumlah=(String) tabelTransaksiProduk.getValueAt(index, 4);
        String jumlahTotal = (String) tabelTransaksiProduk.getValueAt(index, 5);
        txtNomor.setText(ID);
        txtUkuran.setText(ukuran);
        txtNamaLayanan.setText(namax);
        txtJumlah.setText(jumlah);
        txtHarga.setText(Harga);
        txtTotal.setText(jumlahTotal);
                }
                break ;

        }
    }//GEN-LAST:event_tabelTransaksiProdukKeyPressed

    private void tabelTransaksiProdukKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tabelTransaksiProdukKeyReleased
        // TODO add your handling code here:
        switch(evt.getKeyCode())
        {
            case KeyEvent.VK_BACK_SPACE:
            if (evt.getKeyCode()==KeyEvent.VK_BACK_SPACE) {

            }

            break ;
                        case KeyEvent.VK_ENTER:

            if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                int index= tabelTransaksiProduk.getSelectedRow();
        String ID = (String) tabelTransaksiProduk.getValueAt(index, 0);
        String namax = (String) tabelTransaksiProduk.getValueAt(index, 1);
        String ukuran1= (String) tabelTransaksiProduk.getValueAt(index,2);
        String Harga = (String) tabelTransaksiProduk.getValueAt(index, 3);
        String jumlah=(String) tabelTransaksiProduk.getValueAt(index, 4);
        String jumlahTotal = (String) tabelTransaksiProduk.getValueAt(index, 5);
        txtNomor.setText(ID);
        txtUkuran.setText(ukuran1);
        txtNamaLayanan.setText(namax);
        txtJumlah.setText(jumlah);
        txtHarga.setText(Harga);
        txtTotal.setText(jumlahTotal);
                
            }
            break;

            case KeyEvent.VK_KP_UP:
                if(evt.getKeyCode()== KeyEvent.VK_KP_UP)
                {
                      int index= tabelTransaksiProduk.getSelectedRow();
                    String ID       = (String) tabelTransaksiProduk.getValueAt(index, 0);
                    String namax = (String) tabelTransaksiProduk.getValueAt(index, 1);
                    String ukuran= (String) tabelTransaksiProduk.getValueAt(index,2);
                    String Harga = (String) tabelTransaksiProduk.getValueAt(index, 3);
                    String jumlah=(String) tabelTransaksiProduk.getValueAt(index, 4);
                    String jumlahTotal = (String) tabelTransaksiProduk.getValueAt(index, 5);
                    txtNomor.setText(ID);
                    txtUkuran.setText(ukuran);
                    txtNamaLayanan.setText(namax);
                    txtJumlah.setText(jumlah);
                    txtHarga.setText(Harga);
                    txtTotal.setText(jumlahTotal);
                            }
                break ;
            case  KeyEvent.VK_KP_DOWN:
                 if(evt.getKeyCode()== KeyEvent.VK_KP_DOWN)
                {
                      int index= tabelTransaksiProduk.getSelectedRow();
                    String ID       = (String) tabelTransaksiProduk.getValueAt(index, 0);
                    String namax = (String) tabelTransaksiProduk.getValueAt(index, 1);
                    String ukuran= (String) tabelTransaksiProduk.getValueAt(index,2);
                    String Harga = (String) tabelTransaksiProduk.getValueAt(index, 3);
                    String jumlah=(String) tabelTransaksiProduk.getValueAt(index, 4);
                    String jumlahTotal = (String) tabelTransaksiProduk.getValueAt(index, 5);
                    txtNomor.setText(ID);
                    txtUkuran.setText(ukuran);
                    txtNamaLayanan.setText(namax);
                    txtJumlah.setText(jumlah);
                    txtHarga.setText(Harga);
                    txtTotal.setText(jumlahTotal);
                            }
                break ;
        }
    }//GEN-LAST:event_tabelTransaksiProdukKeyReleased

    private void jComboBoxIdTransaksiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBoxIdTransaksiKeyPressed
        // TODO add your handling code here:
        
      switch(evt.getKeyCode())
      {
          case KeyEvent.VK_ENTER :
              if (evt.getKeyCode()== KeyEvent.VK_ENTER) {
                  
                  btnCari.requestFocus();
              }
      }
        
    }//GEN-LAST:event_jComboBoxIdTransaksiKeyPressed

    private void btnCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnCariKeyPressed
        // TODO add your handling code here:
        switch (evt.getKeyCode())
        {
            case KeyEvent.VK_ENTER:
                if (evt.getKeyCode() == KeyEvent.VK_ENTER)
                {
                    try
                     {
                        String id = (String)jComboBoxIdTransaksi.getSelectedItem();
                        tampilHargaLayanan(id);
                        searchNamaHewan(id);
                        searchNamaPegawai(id);
                        tabelTransaksiProduk.requestFocus();

        //    nomorUrut();
        //
                        hitungTotalHarga();
                        if(txtNamaPelanggan.getText().length()<=1)
                        {
                            txtNamaPelanggan.setText("QUEST");
                            txtNamaHewan.setText("QUEST");
                            txtDiskon.setEnabled(false);
                            txtTotalSeluruh.setText(txtSubTotal.getText());
                        }
                        else
                        {
                            txtDiskon.setEnabled(true);
                            txtTotalSeluruh.setText("");
                        }

                        }
                        catch(Exception e)
                        {
                            System.out.println(e);
                        }
                }
                        break ;
        }

        

       
    }//GEN-LAST:event_btnCariKeyPressed

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        // TODO add your handling code here:
        MenuKasirTampilTransaksiLayanan m = new MenuKasirTampilTransaksiLayanan();
        this.setVisible(false );
        m.setVisible(true);
    }//GEN-LAST:event_jLabel5MouseClicked

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
            java.util.logging.Logger.getLogger(MenuKasirTransaksiLayanan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MenuKasirTransaksiLayanan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MenuKasirTransaksiLayanan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MenuKasirTransaksiLayanan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MenuKasirTransaksiLayanan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCari;
    private javax.swing.JButton btnHapus;
    private javax.swing.JPanel editTransaksiProduk;
    private javax.swing.Box.Filler filler1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JComboBox<String> jComboBoxIdTransaksi;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JPanel mainPane;
    private javax.swing.JTable tabelTransaksiProduk;
    private javax.swing.JTextField txtDiskon;
    private javax.swing.JLabel txtHarga;
    private javax.swing.JLabel txtJenisHewan;
    private javax.swing.JLabel txtJenisHewan1;
    private javax.swing.JTextField txtJumlah;
    private javax.swing.JLabel txtNamaCs;
    private javax.swing.JLabel txtNamaHewan;
    private javax.swing.JTextField txtNamaLayanan;
    private javax.swing.JLabel txtNamaPelanggan;
    private javax.swing.JLabel txtNomor;
    private javax.swing.JTextField txtSubTotal;
    private javax.swing.JLabel txtTotal;
    private javax.swing.JTextField txtTotalSeluruh;
    private javax.swing.JTextField txtUkuran;
    // End of variables declaration//GEN-END:variables
}
