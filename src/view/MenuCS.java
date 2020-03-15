/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package view;
import Controller.CSControl ;
import static DAO.AdminDao.con;
import DAO.CsDAO;

import Model.Pelanggan;
import Model.Produk;
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
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;


/**
 *
 * @author ACER
 */
public class MenuCS extends javax.swing.JFrame {

    Pelanggan P ;
    public  DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MMMM/dd ");  
    public  LocalDateTime now = LocalDateTime.now();
    CSControl CS ;
    public CsDAO CA = new CsDAO();
    
    /**
     * Creates new form MenuCS
     */
        DefaultTableModel tabelModel ;
    public MenuCS() {
        
      
        initComponents();
        txtTanggalHariIni.setText(dtf.format(now));
        txtUserKasir.setText(LoginSession.getNama());
        P= new Pelanggan();
        CS = new CSControl();
        tabelModel = (DefaultTableModel) tabelPelanggan.getModel();
        jComboBoxPelanggan.addItem("-Pilih Id Pelanggan");
          PlaceHolder holder0 = new PlaceHolder(txtNamaPelanggan,"Masukan Nama Pelanggan");
          PlaceHolder holder1 = new PlaceHolder(txtAlamatPelanggan, "Masukan Alamat Pelanggan");
          PlaceHolder holder2 = new PlaceHolder(txtTelpPelanggan, "Masukan No Telphone");
        jComboBoxidPelanggan();
        tampilPelanggan();
        

    }

   public void addTablePelanggan(Pelanggan P)
   {
       Vector data = new Vector();
       data.add(P.getId_pelanggan());
       data.add(P.getNama());
       data.add(P.getTelp());
       data.add(P.getTglLahir());
       data.add(P.getTelp());
       tabelModel.addRow(data);
   }
   public void tampilPelanggan()
   {
        int a = tabelModel.getRowCount() ;
        for (int i = 0; i < a; i++) {
        tabelModel.removeRow(0);
        }
        Pelanggan P ;
        List<Pelanggan> M = CS.tampilPelanggan();
        for (int i = 0; i <M.size(); i++) {
            
            P= new Pelanggan();
            P.setId_pelanggan(M.get(i).getId_pelanggan());
            P.setNama(M.get(i).getNama());
            P.setAlamat(M.get(i).getAlamat());
            P.setTglLahir(M.get(i).getTglLahir());
            P.setTelp(M.get(i).getTelp());
            
            addTablePelanggan(P);
        }
   }
    public void jComboBoxidPelanggan()
    {
        CA.makeConnection();
       
        try
        {
            String sql = "Select id_pelanggan from Pelanggan where aktif = 1 ";
            Class.forName("com.mysql.jdbc.Driver"); 
            Statement st = CA.GETcon().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
           
                jComboBoxPelanggan.addItem(rs.getString("id_pelanggan"));

            }

            
        }
        catch(Exception e)
        {
            System.out.println("Gagal Menampilkan Pelanggan");
            System.out.println(e);
        }
        CA.closeConnection();
            
        
    }
    public void inputException() throws dataKosong
    {
        String tanggal = String.valueOf(txtTanggalLahirPelanggan.getDate());
        if(txtNamaPelanggan.getText().length()<1 || txtAlamatPelanggan.getText().length()<1 || tanggal.length()<1 || txtTelpPelanggan.getText().length()<1 )
        {
            throw new dataKosong();
        }
    }
    public void cekAngka()throws CekAngka
     {
       if(txtTelpPelanggan.getText().matches("[0-9]*")) 
       {
           System.out.println("ok");
       }
       else
       {
           throw new  CekAngka();
       }
     }
    public void cekHuruf()throws CekHuruf
     {
         if(txtNamaPelanggan.getText().matches("[0-9]*"))
         {
             throw new CekHuruf();
         }
         else
         {
             System.out.println("okk");
         }
     }
    public void cekPanjangNoTelp() throws PanjangData
    {
        if(txtTelpPelanggan.getText().length()<12)
        {
            throw  new PanjangData();
        }
        
    }
    public void setTextPelanggan()
    {
        txtCariNamaPelanggan.setText(" ");
        txtCariAlaPelanggan.setText(" ");
        txtCariTglLahir.setText(" ");
        txtCariTelpPel.setText(" ");
        txtAlamatPelanggan.setText(" ");
        txtNamaPelanggan.setText(" ");
        txtTelpPelanggan.setText(" ");
        txtTanggalLahirPelanggan.setDate(null);
       
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
        menuHome = new javax.swing.JPanel();
        judul = new javax.swing.JLabel();
        btnPegawai = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        btnLog = new javax.swing.JLabel();
        txtTanggalHariIni = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        txtUserKasir = new javax.swing.JLabel();
        MainPanel = new javax.swing.JPanel();
        dataPelanggan = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        btnTambah = new javax.swing.JButton();
        btnHelp = new javax.swing.JButton();
        btnTampil = new javax.swing.JButton();
        btnCari = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        mainPanel2 = new javax.swing.JPanel();
        tambahPelanggan = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        btnSimpanPelanggan = new javax.swing.JButton();
        btlSimpan = new javax.swing.JButton();
        txtAlamatPelanggan = new javax.swing.JTextField();
        txtNamaPelanggan = new javax.swing.JTextField();
        jLabel57 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        txtTelpPelanggan = new javax.swing.JTextField();
        txtTanggalLahirPelanggan = new com.toedter.calendar.JDateChooser();
        jScrollPane4 = new javax.swing.JScrollPane();
        tabelPelanggan = new javax.swing.JTable();
        jLabel60 = new javax.swing.JLabel();
        cariPegawai = new javax.swing.JPanel();
        jComboBoxPelanggan = new javax.swing.JComboBox<>();
        jPanel12 = new javax.swing.JPanel();
        btnSimpanProduk2 = new javax.swing.JButton();
        btlHapusSup = new javax.swing.JButton();
        txtCariAlaPelanggan = new javax.swing.JTextField();
        txtCariNamaPelanggan = new javax.swing.JTextField();
        jLabel70 = new javax.swing.JLabel();
        jLabel71 = new javax.swing.JLabel();
        jLabel72 = new javax.swing.JLabel();
        txtCariTelpPel = new javax.swing.JTextField();
        txtCariTglLahir = new javax.swing.JTextField();
        jLabel73 = new javax.swing.JLabel();
        btnSearchPelanggan = new javax.swing.JLabel();
        bantuan = new javax.swing.JPanel();
        tampilSeluruh = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setIconImage(
            new javax.swing.ImageIcon(getClass().getResource("/icon/dogg.png")).getImage());

        background.setBackground(new java.awt.Color(255, 255, 255));
        background.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        menuHome.setBackground(new java.awt.Color(99, 175, 241));
        menuHome.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        judul.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        judul.setForeground(new java.awt.Color(0, 0, 0));
        judul.setText("MENU KASIR");

        btnPegawai.setFont(new java.awt.Font("Serif", 1, 12)); // NOI18N
        btnPegawai.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icon.png"))); // NOI18N
        btnPegawai.setText("Data Pelanggan");
        btnPegawai.setHideActionText(true);
        btnPegawai.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnPegawai.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnPegawai.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btnPegawai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPegawaiActionPerformed(evt);
            }
        });

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/pet-shop-1.png"))); // NOI18N

        btnLog.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/log-icon (2).png"))); // NOI18N

        txtTanggalHariIni.setFont(new java.awt.Font("Dialog", 2, 12)); // NOI18N
        txtTanggalHariIni.setForeground(new java.awt.Color(0, 0, 0));
        txtTanggalHariIni.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        jLabel23.setFont(new java.awt.Font("Times New Roman", 3, 12)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(0, 0, 0));
        jLabel23.setText("Tanggal :");

        jLabel24.setFont(new java.awt.Font("Times New Roman", 3, 12)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(0, 0, 0));
        jLabel24.setText("HAI");

        txtUserKasir.setFont(new java.awt.Font("Times New Roman", 3, 12)); // NOI18N
        txtUserKasir.setForeground(new java.awt.Color(0, 0, 0));
        txtUserKasir.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        javax.swing.GroupLayout menuHomeLayout = new javax.swing.GroupLayout(menuHome);
        menuHome.setLayout(menuHomeLayout);
        menuHomeLayout.setHorizontalGroup(
            menuHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, menuHomeLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnPegawai)
                .addGap(57, 57, 57))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, menuHomeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(judul)
                .addContainerGap())
            .addGroup(menuHomeLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(menuHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(menuHomeLayout.createSequentialGroup()
                        .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtUserKasir, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(menuHomeLayout.createSequentialGroup()
                        .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtTanggalHariIni, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44))))
            .addGroup(menuHomeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnLog, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        menuHomeLayout.setVerticalGroup(
            menuHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuHomeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(menuHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTanggalHariIni, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(menuHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtUserKasir, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(menuHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(judul))
                .addGap(26, 26, 26)
                .addComponent(btnPegawai)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnLog)
                .addContainerGap())
        );

        MainPanel.setBackground(new java.awt.Color(168, 238, 244));
        MainPanel.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createEtchedBorder(), javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED)));
        MainPanel.setLayout(new java.awt.CardLayout());

        dataPelanggan.setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(99, 175, 241));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btnTambah.setBackground(new java.awt.Color(51, 255, 255));
        btnTambah.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnTambah.setForeground(new java.awt.Color(0, 0, 0));
        btnTambah.setText("Tambah Pegawai");
        btnTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTambahActionPerformed(evt);
            }
        });

        btnHelp.setBackground(new java.awt.Color(51, 255, 255));
        btnHelp.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnHelp.setForeground(new java.awt.Color(0, 0, 0));
        btnHelp.setText("Help");
        btnHelp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHelpActionPerformed(evt);
            }
        });

        btnTampil.setBackground(new java.awt.Color(51, 255, 255));
        btnTampil.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnTampil.setForeground(new java.awt.Color(0, 0, 0));
        btnTampil.setText("Tampil Seluruh");
        btnTampil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTampilActionPerformed(evt);
            }
        });

        btnCari.setBackground(new java.awt.Color(51, 255, 255));
        btnCari.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnCari.setForeground(new java.awt.Color(0, 0, 0));
        btnCari.setText("Cari Pegawai");
        btnCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Bodoni MT Condensed", 0, 24)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("MENGELOLA PEGAWAI KOEVEE PET SHOP");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnTambah)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnCari, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnTampil)
                        .addGap(49, 49, 49)
                        .addComponent(btnHelp))
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 484, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTambah)
                    .addComponent(btnCari)
                    .addComponent(btnHelp)
                    .addComponent(btnTampil))
                .addContainerGap())
        );

        mainPanel2.setLayout(new java.awt.CardLayout());

        tambahPelanggan.setBackground(new java.awt.Color(99, 175, 241));
        tambahPelanggan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tambahPelanggan.setForeground(new java.awt.Color(0, 0, 0));

        jPanel9.setBackground(new java.awt.Color(99, 175, 241));
        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11))); // NOI18N

        btnSimpanPelanggan.setText("SIMPAN");
        btnSimpanPelanggan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanPelangganActionPerformed(evt);
            }
        });

        btlSimpan.setText("BATAL");

        txtNamaPelanggan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNamaPelangganActionPerformed(evt);
            }
        });

        jLabel57.setFont(new java.awt.Font("Clarendon BT", 0, 18)); // NOI18N
        jLabel57.setForeground(new java.awt.Color(0, 0, 0));
        jLabel57.setText("Nama ");

        jLabel58.setFont(new java.awt.Font("Clarendon BT", 0, 18)); // NOI18N
        jLabel58.setForeground(new java.awt.Color(0, 0, 0));
        jLabel58.setText("Alamat");

        jLabel59.setFont(new java.awt.Font("Clarendon BT", 0, 18)); // NOI18N
        jLabel59.setForeground(new java.awt.Color(0, 0, 0));
        jLabel59.setText("Tanggal Lahir");

        jLabel61.setFont(new java.awt.Font("Clarendon BT", 0, 18)); // NOI18N
        jLabel61.setForeground(new java.awt.Color(0, 0, 0));
        jLabel61.setText("Telp");

        txtTelpPelanggan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTelpPelangganActionPerformed(evt);
            }
        });

        txtTanggalLahirPelanggan.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        txtTanggalLahirPelanggan.setDateFormatString("yyyy-MMMMMM-d");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel61, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel57, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel58, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel59, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtNamaPelanggan)
                                    .addComponent(txtAlamatPelanggan, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE)
                                    .addComponent(txtTelpPelanggan, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtTanggalLahirPelanggan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(35, 35, 35))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(btlSimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnSimpanPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNamaPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel58, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAlamatPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel59, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTanggalLahirPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel61, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTelpPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 71, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btlSimpan)
                    .addComponent(btnSimpanPelanggan))
                .addGap(76, 76, 76))
        );

        tabelPelanggan.setBackground(new java.awt.Color(255, 255, 255));
        tabelPelanggan.setFont(new java.awt.Font("Geometr212 BkCn BT", 1, 12)); // NOI18N
        tabelPelanggan.setForeground(new java.awt.Color(0, 0, 0));
        tabelPelanggan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID_Pelanggan", "Nama ", "Alamat", "Tanggal Lahir", "Telp"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelPelanggan.setGridColor(new java.awt.Color(0, 0, 0));
        jScrollPane4.setViewportView(tabelPelanggan);

        jLabel60.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        jLabel60.setForeground(new java.awt.Color(0, 0, 0));
        jLabel60.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/pelanggan.png"))); // NOI18N
        jLabel60.setText("TAMBAH PELANGGAN");

        javax.swing.GroupLayout tambahPelangganLayout = new javax.swing.GroupLayout(tambahPelanggan);
        tambahPelanggan.setLayout(tambahPelangganLayout);
        tambahPelangganLayout.setHorizontalGroup(
            tambahPelangganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tambahPelangganLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(tambahPelangganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel60, javax.swing.GroupLayout.DEFAULT_SIZE, 417, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 567, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(92, 92, 92))
        );
        tambahPelangganLayout.setVerticalGroup(
            tambahPelangganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tambahPelangganLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel60, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(tambahPelangganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(46, Short.MAX_VALUE))
        );

        mainPanel2.add(tambahPelanggan, "card2");

        cariPegawai.setBackground(new java.awt.Color(99, 175, 241));
        cariPegawai.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jComboBoxPelanggan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jComboBoxPelangganMouseClicked(evt);
            }
        });
        jComboBoxPelanggan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxPelangganActionPerformed(evt);
            }
        });

        jPanel12.setBackground(new java.awt.Color(99, 175, 241));
        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11))); // NOI18N

        btnSimpanProduk2.setText("SIMPAN");
        btnSimpanProduk2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanProduk2ActionPerformed(evt);
            }
        });

        btlHapusSup.setText("HAPUS");
        btlHapusSup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btlHapusSupActionPerformed(evt);
            }
        });

        txtCariNamaPelanggan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCariNamaPelangganActionPerformed(evt);
            }
        });

        jLabel70.setFont(new java.awt.Font("Clarendon BT", 0, 18)); // NOI18N
        jLabel70.setForeground(new java.awt.Color(0, 0, 0));
        jLabel70.setText("Nama ");

        jLabel71.setFont(new java.awt.Font("Clarendon BT", 0, 18)); // NOI18N
        jLabel71.setForeground(new java.awt.Color(0, 0, 0));
        jLabel71.setText("Alamat");

        jLabel72.setFont(new java.awt.Font("Clarendon BT", 0, 18)); // NOI18N
        jLabel72.setForeground(new java.awt.Color(0, 0, 0));
        jLabel72.setText("Telephone");

        txtCariTelpPel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCariTelpPelActionPerformed(evt);
            }
        });

        txtCariTglLahir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCariTglLahirActionPerformed(evt);
            }
        });

        jLabel73.setFont(new java.awt.Font("Clarendon BT", 0, 18)); // NOI18N
        jLabel73.setForeground(new java.awt.Color(0, 0, 0));
        jLabel73.setText("Tanggal Lahir");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap(26, Short.MAX_VALUE)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(btlHapusSup, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnSimpanProduk2, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel70, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel71, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel72, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel73, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCariAlaPelanggan, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCariTelpPel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCariTglLahir, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCariNamaPelanggan, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(35, 35, 35))))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCariNamaPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel70, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel71, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCariAlaPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel72, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCariTelpPel, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCariTglLahir, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel73, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btlHapusSup)
                    .addComponent(btnSimpanProduk2))
                .addContainerGap(115, Short.MAX_VALUE))
        );

        btnSearchPelanggan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/search-icon.png"))); // NOI18N
        btnSearchPelanggan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSearchPelangganMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout cariPegawaiLayout = new javax.swing.GroupLayout(cariPegawai);
        cariPegawai.setLayout(cariPegawaiLayout);
        cariPegawaiLayout.setHorizontalGroup(
            cariPegawaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cariPegawaiLayout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(btnSearchPelanggan)
                .addGap(3, 3, 3)
                .addGroup(cariPegawaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        cariPegawaiLayout.setVerticalGroup(
            cariPegawaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cariPegawaiLayout.createSequentialGroup()
                .addGap(74, 74, 74)
                .addGroup(cariPegawaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnSearchPelanggan)
                    .addComponent(jComboBoxPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(47, Short.MAX_VALUE))
        );

        mainPanel2.add(cariPegawai, "card3");

        bantuan.setBackground(new java.awt.Color(99, 175, 241));

        javax.swing.GroupLayout bantuanLayout = new javax.swing.GroupLayout(bantuan);
        bantuan.setLayout(bantuanLayout);
        bantuanLayout.setHorizontalGroup(
            bantuanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1079, Short.MAX_VALUE)
        );
        bantuanLayout.setVerticalGroup(
            bantuanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 571, Short.MAX_VALUE)
        );

        mainPanel2.add(bantuan, "card5");

        tampilSeluruh.setBackground(new java.awt.Color(99, 175, 241));

        javax.swing.GroupLayout tampilSeluruhLayout = new javax.swing.GroupLayout(tampilSeluruh);
        tampilSeluruh.setLayout(tampilSeluruhLayout);
        tampilSeluruhLayout.setHorizontalGroup(
            tampilSeluruhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1079, Short.MAX_VALUE)
        );
        tampilSeluruhLayout.setVerticalGroup(
            tampilSeluruhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 571, Short.MAX_VALUE)
        );

        mainPanel2.add(tampilSeluruh, "card4");

        javax.swing.GroupLayout dataPelangganLayout = new javax.swing.GroupLayout(dataPelanggan);
        dataPelanggan.setLayout(dataPelangganLayout);
        dataPelangganLayout.setHorizontalGroup(
            dataPelangganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dataPelangganLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(dataPelangganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(mainPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );
        dataPelangganLayout.setVerticalGroup(
            dataPelangganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dataPelangganLayout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(mainPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        MainPanel.add(dataPelanggan, "card6");

        javax.swing.GroupLayout backgroundLayout = new javax.swing.GroupLayout(background);
        background.setLayout(backgroundLayout);
        backgroundLayout.setHorizontalGroup(
            backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backgroundLayout.createSequentialGroup()
                .addComponent(menuHome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(MainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 1099, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
        );
        backgroundLayout.setVerticalGroup(
            backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backgroundLayout.createSequentialGroup()
                .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(MainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(menuHome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(background, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnPegawaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPegawaiActionPerformed
        // TODO add your handling code here:
        MainPanel.removeAll();
        MainPanel.repaint();
        MainPanel.revalidate();
        MainPanel.add(dataPelanggan);
        MainPanel.repaint();
        MainPanel.revalidate();
      
    }//GEN-LAST:event_btnPegawaiActionPerformed

    private void btnSearchPelangganMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSearchPelangganMouseClicked
        // TODO add your handling code here:
        String cari = (String)jComboBoxPelanggan.getSelectedItem();
        int ID= Integer.parseInt(cari);
        P= CS.cariPelanggan(ID);
        if(P!=null)
        {
            txtCariNamaPelanggan.setText(P.getNama());
            txtCariAlaPelanggan.setText(P.getAlamat());
            txtCariTelpPel.setText(P.getTelp());
            txtCariTglLahir.setText(P.getTglLahir());

        }
        else
        {
            JOptionPane.showMessageDialog(this, "Data Supplier Tida Ada..");
        }
    }//GEN-LAST:event_btnSearchPelangganMouseClicked

    private void txtCariTglLahirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCariTglLahirActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCariTglLahirActionPerformed

    private void txtCariTelpPelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCariTelpPelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCariTelpPelActionPerformed

    private void txtCariNamaPelangganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCariNamaPelangganActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCariNamaPelangganActionPerformed

    private void btlHapusSupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btlHapusSupActionPerformed
        // TODO add your handling code here:
        String cari = (String)jComboBoxPelanggan.getSelectedItem();
        int id = Integer.parseInt(cari);
        try
        {

            if(txtCariNamaPelanggan.getText().equalsIgnoreCase(" "))
            {
                JOptionPane.showMessageDialog(this, "Tidak Ada Yang Di Hapus");
            }

            else
            {
                if (JOptionPane.showConfirmDialog(null, "Yakin Hapus?", "Yakin?",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION)
                {
                    CS.hapusPelanggan(id);

                    JOptionPane.showMessageDialog(this, "Data Berhasil Dihapus..");

                    jComboBoxPelanggan.removeItem(jComboBoxPelanggan.getSelectedItem());
                    jComboBoxPelanggan.setSelectedIndex(0);
                    setTextPelanggan();
                    tampilPelanggan();
                }
            }

        }
        catch(Exception e)
        {
            System.out.println("Gagal Hapus");
            System.out.println(e);
        }
    }//GEN-LAST:event_btlHapusSupActionPerformed

    private void btnSimpanProduk2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanProduk2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSimpanProduk2ActionPerformed

    private void jComboBoxPelangganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxPelangganActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxPelangganActionPerformed

    private void jComboBoxPelangganMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboBoxPelangganMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxPelangganMouseClicked

    private void txtTelpPelangganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTelpPelangganActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTelpPelangganActionPerformed

    private void txtNamaPelangganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNamaPelangganActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNamaPelangganActionPerformed

    private void btnSimpanPelangganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanPelangganActionPerformed
        // TODO add your handling code here:

        try
        {
            inputException();
            cekAngka();
            cekHuruf();
            String format = "yyyy-MM-dd";
            SimpleDateFormat fm = new SimpleDateFormat(format);
            String tanggal = String.valueOf(fm.format(txtTanggalLahirPelanggan.getDate()));

            P.setNama(txtNamaPelanggan.getText());
            P.setAlamat(txtAlamatPelanggan.getText());
            P.setTelp(txtTelpPelanggan.getText());
            P.setTglLahir(tanggal);
            CS.tambahPelanggan(P);
            tampilPelanggan();
            CA.makeConnection();

            String sql = "Select id_pelanggan from Pelanggan where aktif = 1 ";
            Class.forName("com.mysql.jdbc.Driver");
            Statement st = CA.GETcon().createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {

                jComboBoxPelanggan.addItem(rs.getString("id_pelanggan"));

            }
            CA.closeConnection();

            setTextPelanggan();
            JOptionPane.showMessageDialog(this,"Berhasil Tambah Data Pelangggan");

        }

        catch(dataKosong d)
        {
            JOptionPane.showMessageDialog(this, d.message());
        }

        catch(CekAngka ca)
        {
            JOptionPane.showMessageDialog(this, ca.dataHanyaHuruf());
        }
        catch(CekHuruf ch)
        {
            JOptionPane.showMessageDialog(this, ch.cekHuruf());
        }
        catch(Exception e)
        {
            System.out.println(e);
        }

    }//GEN-LAST:event_btnSimpanPelangganActionPerformed

    private void btnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariActionPerformed
        // TODO add your handling code here:
        mainPanel2.removeAll();
        mainPanel2.repaint();
        mainPanel2.revalidate();
        mainPanel2.add(cariPegawai);
        mainPanel2.repaint();
        mainPanel2.revalidate();
    }//GEN-LAST:event_btnCariActionPerformed

    private void btnTampilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTampilActionPerformed
        // TODO add your handling code here:
        mainPanel2.removeAll();
        mainPanel2.repaint();
        mainPanel2.revalidate();
        mainPanel2.add(tampilSeluruh);
        mainPanel2.repaint();
        mainPanel2.revalidate();

    }//GEN-LAST:event_btnTampilActionPerformed

    private void btnHelpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHelpActionPerformed
        // TODO add your handling code here:
        mainPanel2.removeAll();
        mainPanel2.repaint();
        mainPanel2.revalidate();
        mainPanel2.add(bantuan);
        mainPanel2.repaint();
        mainPanel2.revalidate();
    }//GEN-LAST:event_btnHelpActionPerformed

    private void btnTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahActionPerformed
        // TODO add your handling code here:
        mainPanel2.removeAll();
        mainPanel2.repaint();
        mainPanel2.revalidate();
        mainPanel2.add(tambahPelanggan);
        mainPanel2.repaint();
        mainPanel2.revalidate();
    }//GEN-LAST:event_btnTambahActionPerformed

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
            java.util.logging.Logger.getLogger(MenuCS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MenuCS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MenuCS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MenuCS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MenuCS().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel MainPanel;
    private javax.swing.JPanel background;
    private javax.swing.JPanel bantuan;
    private javax.swing.JButton btlHapusSup;
    private javax.swing.JButton btlSimpan;
    private javax.swing.JButton btnCari;
    private javax.swing.JButton btnHelp;
    private javax.swing.JLabel btnLog;
    private javax.swing.JButton btnPegawai;
    private javax.swing.JLabel btnSearchPelanggan;
    private javax.swing.JButton btnSimpanPelanggan;
    private javax.swing.JButton btnSimpanProduk2;
    private javax.swing.JButton btnTambah;
    private javax.swing.JButton btnTampil;
    private javax.swing.JPanel cariPegawai;
    private javax.swing.JPanel dataPelanggan;
    private javax.swing.JComboBox<String> jComboBoxPelanggan;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel judul;
    private javax.swing.JPanel mainPanel2;
    private javax.swing.JPanel menuHome;
    private javax.swing.JTable tabelPelanggan;
    private javax.swing.JPanel tambahPelanggan;
    private javax.swing.JPanel tampilSeluruh;
    private javax.swing.JTextField txtAlamatPelanggan;
    private javax.swing.JTextField txtCariAlaPelanggan;
    private javax.swing.JTextField txtCariNamaPelanggan;
    private javax.swing.JTextField txtCariTelpPel;
    private javax.swing.JTextField txtCariTglLahir;
    private javax.swing.JTextField txtNamaPelanggan;
    private javax.swing.JLabel txtTanggalHariIni;
    private com.toedter.calendar.JDateChooser txtTanggalLahirPelanggan;
    private javax.swing.JTextField txtTelpPelanggan;
    private javax.swing.JLabel txtUserKasir;
    // End of variables declaration//GEN-END:variables
}
