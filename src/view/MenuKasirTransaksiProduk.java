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
import Model.dataHewan;
import Model.dataHewan;
import Model.Pelanggan;
import Model.Produk;
import Model.dataHewan;
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
import javax.swing.table. DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;


/**
 *
 * @author ACER
 */
public class MenuKasirTransaksiProduk extends javax.swing.JFrame {

    public Pelanggan P ;
    public detailTransaksiProduk Tp ;
    public  DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MMMM/dd ");  
    public  LocalDateTime now = LocalDateTime.now();
    CSControl CS ;
    public ArrayList namaHewan = new  ArrayList();
    public ArrayList nama= new ArrayList();
    public AdminControl AC = new AdminControl();
    public KasirControl KC = new KasirControl();
    public CsDAO CA = new CsDAO();
    public AdminDao AD = new AdminDao();
    public Produk Pr ;
    public  JenisHewan jh ;
    public dataHewan Dh ;
    String tambah=null ;
    int StokProduk = 0;
    /**
     * Creates new form MenuCS
     */
        DefaultTableModel tabelModel , tabelModel2 ;
    public MenuKasirTransaksiProduk() {
            
        initComponents();
        tampilComboBoxSupllier();
        tabelModel = (DefaultTableModel) tabelTransaksiProduk.getModel();
        tampilNamaProduk();
        setEnableInput(false);
        txtSubTotal.setEnabled(false);
        txtDiskon.setText("0");
        txtJumlah.setText("0");
          atur(tabelTransaksiProduk, new int []{100,300,300,150,150,150,200,150,200} );
    txtHarga.setText("0");
    txtTotal.setText("0");
        
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
   public void tampilNamaProduk()
    {AD.makeConnection();
        try
        {
            
            String sql = "Select nama from Produk where aktif = 1 ";
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
            
            txtNamaProduk.setText(compelet);
            txtNamaProduk.setCaretPosition(last);
            txtNamaProduk.moveCaretPosition(start);
        }
    }
    public void setTextInput()
    {
        txtNamaProduk.setText("");
        txtJumlah.setText("");
        txtHarga.setText("");
        txtTotal.setText("");
        txtNomor.setText("");
    }
    public void setEnableInput(boolean  nilai)
    {
        txtNamaProduk.setEnabled(nilai);
        txtJumlah.setEnabled(nilai);
    }
   public void tampilComboBoxSupllier()
    {
        AD.makeConnection();
        try
        {
            String sql = "Select id_transaksi_produk from  transaksi_produk where status='Menunggu Pembayaran'";
            Class.forName("com.mysql.jdbc.Driver"); 
            Statement st = AD.GETcon().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
           
                jComboBoxIdTransaksi.addItem(rs.getString("id_transaksi_produk"));

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
                int amount = Integer.parseInt((String)tabelTransaksiProduk.getValueAt(i, 4));
                 total += amount;
                }
            txtSubTotal.setText(""+total);
           totalSeluruh= total-diskon;
           txtTotalSeluruh.setText(String.valueOf(totalSeluruh));
   }
   public void searchNamaPegawai(String id)
   {
       AD.makeConnection(); 
       String sql = "Select b.nama from transaksi_produk as a inner join Pegawai as b on a.ID_CUSTOMER_SERVICE= b.ID_PEGAWAI where a.id_transaksi_produk='"+id+"'";
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
       String sql = "SELECT b.nama nama,d.nama from transaksi_produk as a INNER JOIN hewan as b on a.ID_HEWAN = b.ID_HEWAN INNER JOIN pelanggan as d on b.ID_PELANGGAN = d.ID_PELANGGAN WHERE a.ID_TRANSAKSI_PRODUK ='"+id+"'";
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
        String sql = "SELECT a.nama as produk,b.created_by,b.created_at,b.modified_by, b.modified_At,b.id_detail_transaksi_produk id ,a.HARGA, b.jumlah,C.subtotal ,b.TOTAL_HARGA  FROM detail_transaksi_produk as b INNER JOIN produk AS a on a.ID_PRODUK = b.ID_PRODUK INNER JOIN TRANSAKSI_PRODUK AS C ON B.ID_TRANSAKSI_PRODUK = C.ID_TRANSAKSI_PRODUK where C.ID_TRANSAKSI_PRODUK='"+id+"'";
   
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
                String produk = rs.getString("produk");
                String crated_by = rs.getString("b.created_by");
                String created_at = rs.getString("b.created_at");
                String modifid_by = rs.getString("b.modified_by");
                String modfied_at = rs.getString ("b.modified_at");
                i++;
//                String[] dataField={};
//                tabelModel7.addRow(dataField);
            String idx = String.valueOf(i);
            int c = 0 ;


            String[] dataField= {idDetail,produk,harga,jumlah,total,crated_by,created_at,modifid_by,modfied_at};
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

        background = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
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
        jButton3 = new javax.swing.JButton();
        txtNamaProduk = new javax.swing.JTextField();
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setIconImage(
            new javax.swing.ImageIcon(getClass().getResource("/icon/dogg.png")).getImage());

        background.setBackground(new java.awt.Color(255, 255, 255));
        background.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jPanel1.setBackground(new java.awt.Color(255, 139, 36));

        jLabel6.setFont(new java.awt.Font("Elephant", 1, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("MENU KASIR");

        jLabel5.setBackground(new java.awt.Color(0, 0, 0));
        jLabel5.setFont(new java.awt.Font("Elephant", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 488, Short.MAX_VALUE)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(493, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5))
        );

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
                "No Resi", "Nama Produk", "Harga", "Jumlah", "Harga", "Creeated_by", "Created_at", "Modified_By", "Modified_At"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true, true, true, true
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
        jScrollPane1.setViewportView(tabelTransaksiProduk);
        if (tabelTransaksiProduk.getColumnModel().getColumnCount() > 0) {
            tabelTransaksiProduk.getColumnModel().getColumn(0).setResizable(false);
            tabelTransaksiProduk.getColumnModel().getColumn(1).setResizable(false);
            tabelTransaksiProduk.getColumnModel().getColumn(2).setResizable(false);
            tabelTransaksiProduk.getColumnModel().getColumn(3).setResizable(false);
            tabelTransaksiProduk.getColumnModel().getColumn(4).setResizable(false);
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

        jButton3.setText("CARI");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        txtNamaProduk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNamaProdukActionPerformed(evt);
            }
        });
        txtNamaProduk.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNamaProdukKeyPressed(evt);
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
        jLabel15.setText("Nama Produk");

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
                                            .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtNamaPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtNamaHewan, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(283, 283, 283)
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
                                    .addComponent(txtNamaProduk, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(38, 38, 38)
                                .addGroup(editTransaksiProdukLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtJumlah, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(editTransaksiProdukLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(editTransaksiProdukLayout.createSequentialGroup()
                                        .addComponent(txtHarga, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(editTransaksiProdukLayout.createSequentialGroup()
                                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(editTransaksiProdukLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(editTransaksiProdukLayout.createSequentialGroup()
                                .addGap(620, 620, 620)
                                .addComponent(jLabel2))
                            .addGroup(editTransaksiProdukLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(editTransaksiProdukLayout.createSequentialGroup()
                                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btnHapus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE))))
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
                                .addComponent(jButton3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBoxIdTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(390, 390, 390)
                                .addComponent(jLabel16)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(editTransaksiProdukLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtNamaCs, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        editTransaksiProdukLayout.setVerticalGroup(
            editTransaksiProdukLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editTransaksiProdukLayout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(editTransaksiProdukLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(editTransaksiProdukLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jComboBoxIdTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(editTransaksiProdukLayout.createSequentialGroup()
                        .addGroup(editTransaksiProdukLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNamaCs, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(8, 8, 8)
                .addGroup(editTransaksiProdukLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(editTransaksiProdukLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtNamaPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtNomor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(editTransaksiProdukLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(editTransaksiProdukLayout.createSequentialGroup()
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(editTransaksiProdukLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(editTransaksiProdukLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(editTransaksiProdukLayout.createSequentialGroup()
                                    .addComponent(txtNamaHewan, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(editTransaksiProdukLayout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(10, 10, 10)
                        .addGroup(editTransaksiProdukLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(editTransaksiProdukLayout.createSequentialGroup()
                                .addGroup(editTransaksiProdukLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(editTransaksiProdukLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(editTransaksiProdukLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txtNamaProduk, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtJumlah, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(txtHarga, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(editTransaksiProdukLayout.createSequentialGroup()
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(editTransaksiProdukLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jButton2)
                                    .addComponent(btnHapus))))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
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
                .addGap(90, 90, 90))
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout backgroundLayout = new javax.swing.GroupLayout(background);
        background.setLayout(backgroundLayout);
        backgroundLayout.setHorizontalGroup(
            backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(backgroundLayout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        backgroundLayout.setVerticalGroup(
            backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backgroundLayout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mainPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBoxIdTransaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxIdTransaksiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxIdTransaksiActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
    String id = (String)jComboBoxIdTransaksi.getSelectedItem();
    tampilHargaLayanan(id);
    searchNamaHewan(id);
    searchNamaPegawai(id);
    
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
                    
       
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        // TODO add your handling code here:
        setEnableInput(true);
        
        tambah= "edit" ;
    }//GEN-LAST:event_jButton4ActionPerformed

    private void tabelTransaksiProdukMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelTransaksiProdukMouseClicked
        // TODO add your handling code here:
        int index= tabelTransaksiProduk.getSelectedRow();
        String ID       = (String) tabelTransaksiProduk.getValueAt(index, 0);
        String namax = (String) tabelTransaksiProduk.getValueAt(index, 1);
        String Harga = (String) tabelTransaksiProduk.getValueAt(index, 2);
        String jumlah=(String) tabelTransaksiProduk.getValueAt(index, 3);
        String jumlahTotal = (String) tabelTransaksiProduk.getValueAt(index, 4);
        txtNomor.setText(ID);
        txtNamaProduk.setText(namax);
       txtJumlah.setText(jumlah);
       txtHarga.setText(Harga);
       txtTotal.setText(jumlahTotal);
    }//GEN-LAST:event_tabelTransaksiProdukMouseClicked

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusActionPerformed
        // TODO add your handling code here:
      
        KC.deleteProduk(txtNomor.getText());
        String id = (String) jComboBoxIdTransaksi.getSelectedItem();
        tampilHargaLayanan(id);
        setTextInput();
        
    }//GEN-LAST:event_btnHapusActionPerformed

    private void jComboBoxIdTransaksiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboBoxIdTransaksiMouseClicked
        // TODO add your handling code here:
        txtNamaPelanggan.setText("");
        txtNamaHewan.setText("");
        txtNomor.setText("");
        txtNamaCs.setText("");
        setTextInput();
    }//GEN-LAST:event_jComboBoxIdTransaksiMouseClicked

    private void txtNamaProdukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNamaProdukActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNamaProdukActionPerformed

    private void txtNamaProdukKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNamaProdukKeyPressed
        // TODO add your handling code here:
        switch(evt.getKeyCode())
        {
            case KeyEvent.VK_BACK_SPACE:
                if (evt.getKeyCode()==KeyEvent.VK_BACK_SPACE) {
                    
                   txtNamaProduk.setText(" ");
                }
                  
                break ;
            case KeyEvent.VK_ENTER:
                txtNamaProduk.setText(txtNamaProduk.getText());
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                Pr=  AC.searchPro(txtNamaProduk.getText());
        if(Pr!=null)
        {        
                String harga=String.valueOf(Pr.getHarga());
               txtHarga.setText(harga);
       
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
                String txt = txtNamaProduk.getText();
                autoCompletNamaProduk(txt);
                
            }
        });
                
                        
        }
    }//GEN-LAST:event_txtNamaProdukKeyPressed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        setEnableInput(true);
        setTextInput();
        tambah= "tambah" ;
       
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
          String idDetail = (String) jComboBoxIdTransaksi.getSelectedItem();
        
        if (tambah=="tambah") {
          
        Pr= AC.searchPro(txtNamaProduk.getText());
        if (Pr!=null) {
            Tp = new detailTransaksiProduk();
            Tp.setIdTransaksi(idDetail);
            int id = Pr.getIdProduk() ;
            
            Tp.setId_Produk(id);
            int Jum = Integer.parseInt(txtJumlah.getText());
            
            Tp.setJumlah(Jum);
            int total =Integer.parseInt(txtTotal.getText());
            Tp.setTotalHarga(total);
            KC.tambahProduk(Tp);
            tampilHargaLayanan(idDetail);
            searchNamaHewan(idDetail);
            //    nomorUrut();
            //    
            hitungTotalHarga();
            setTextInput();
                        setEnableInput(false);
                    
        }
            
        }
        if (tambah=="edit")
        {
          Pr= AC.searchPro(txtNamaProduk.getText());
            if (Pr!=null) {
                Tp = new detailTransaksiProduk();

                int id = Pr.getIdProduk() ;

                Tp.setId_Produk(id);
                int Jum = Integer.parseInt(txtJumlah.getText());

                Tp.setJumlah(Jum);
                int total =Integer.parseInt(txtTotal.getText());
                Tp.setTotalHarga(total);
                KC.editProdukTransaksi(Tp, txtNomor.getText());

        //    nomorUrut();
        //    
        
        hitungTotalHarga();
        setTextInput();
        setEnableInput(false);
                tampilHargaLayanan(idDetail);
        }
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void txtJumlahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtJumlahActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtJumlahActionPerformed

    private void txtJumlahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtJumlahKeyPressed
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
                    Pr= AC.searchPro(txtNamaProduk.getText());
                    if (Pr!=null) {
                        int jumStok= Pr.getJumlah();
                         int harga= Integer.parseInt(txtHarga.getText());
                        int jum = Integer.parseInt(txtJumlah.getText());
                        if(jum>jumStok)
                        {
                            JOptionPane.showMessageDialog(this,"Pembelian Produk Melebihi Min Stok !!" );
                        } 
                        else
                        {
                            int total = harga*jum;
                            String totalHarga = String.valueOf(total);
                            txtTotal.setText(totalHarga);
                            StokProduk= jumStok-jum ;
                        }
                            
                        
                    }
                       
                }     
                break;
                
           
                
                        
        }
    }//GEN-LAST:event_txtJumlahKeyPressed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        int uang = Integer.parseInt(JOptionPane.showInputDialog(null,"Jumlah Uang : Rp"));  
        int totalHarga=  Integer.parseInt(txtTotalSeluruh.getText());
        if(uang < totalHarga)
        {
            JOptionPane.showMessageDialog(this, "Uang Tidak Cukup");
        }
        else
        {
            int Kembalian = totalHarga-uang;
            JOptionPane.showMessageDialog(this,"Besar Kembalian : Rp"+Kembalian);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(MenuKasirTransaksiProduk.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MenuKasirTransaksiProduk.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MenuKasirTransaksiProduk.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MenuKasirTransaksiProduk.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
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
                new MenuKasirTransaksiProduk().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel background;
    private javax.swing.JButton btnHapus;
    private javax.swing.JPanel editTransaksiProduk;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
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
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JPanel mainPane;
    private javax.swing.JTable tabelTransaksiProduk;
    private javax.swing.JTextField txtDiskon;
    private javax.swing.JLabel txtHarga;
    private javax.swing.JTextField txtJumlah;
    private javax.swing.JLabel txtNamaCs;
    private javax.swing.JLabel txtNamaHewan;
    private javax.swing.JLabel txtNamaPelanggan;
    private javax.swing.JTextField txtNamaProduk;
    private javax.swing.JLabel txtNomor;
    private javax.swing.JTextField txtSubTotal;
    private javax.swing.JLabel txtTotal;
    private javax.swing.JTextField txtTotalSeluruh;
    // End of variables declaration//GEN-END:variables
}
