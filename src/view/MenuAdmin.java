/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;
import java.util.Calendar;
import java.util.GregorianCalendar;
import view.LoginAdmin ;
import Controller.AdminControl ;
import Model.Layanan;
import exception.CekHuruf;
import Model.Pemilik;
import java.util.Date;
import javax.swing.JOptionPane;
import Model.Pegawai ;
import Model.Suplier;
import Session.LoginSession;
import com.placeholder.PlaceHolder;
import exception.CekAngka;
import exception.CekHuruf;
import exception.dataSama;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
import static org.hsqldb.lib.tar.TarHeaderField.uid;
/**
 *
 * @author ACER
 */
public class MenuAdmin extends javax.swing.JFrame {

    Suplier S;
    Pegawai  P ;
    Layanan L ;
    AdminControl AC ;
    /**
     * Creates new form MenuAdmin
     */
    DefaultTableModel tabelModel,tabelModel1, tabelModel2 ;
    public MenuAdmin() {
        
        initComponents();
        dissLayanan(false);
        PlaceHolder holder0 = new PlaceHolder(txtNamaLayanan,"Masukan Nama Layanan");
        PlaceHolder holder2 = new PlaceHolder(txtCariLayanan,"Masukan Nama Layanan ");
        PlaceHolder holder1 = new PlaceHolder(txtCari, "Masukkan User Name");
        PlaceHolder holder3 = new PlaceHolder(txtCariSup, "Masukan Nama Supplier");
        holder3.setCursiva(true);
        holder2.setCursiva(true );
        holder1.setCursiva(true);
        holder0.setCursiva(true);
        setTitle("Menu Admin");
        disspegawai(false);
        AC = new AdminControl();
        txtAlamat.getBorder();
        tabelModel = (DefaultTableModel) tablePegawai.getModel();
        tabelModel1 =(DefaultTableModel) tableSupTambahData.getModel();
        tabelModel2 = (DefaultTableModel) tabelLayanan.getModel();
        tampliSuplier();       
        tampilPegawai();
        tampilLayanan();
        dissLayanan(false);
        tablePegawai.setEnabled(false);
        int id = LoginSession.getIduser();
        System.out.println("Idnya: "+id);
       
            
    }
    public void addTableLayanan(Layanan L)
    {
        Vector data = new Vector();
        data.add(L.getIdLayanan());
        data.add(L.getNamaLayanan());
        tabelModel2.addRow(data);
    }
    public void addTableSuplier(Suplier S)
    {
        Vector data = new Vector();
        data.add(S.getId());
        data.add(S.getNama());
        data.add(S.getAlamat());
        data.add(S.getTelp());
        tabelModel1.addRow(data);
    }
    
    public void addTablePegawai(Pegawai P) {
       Vector data = new Vector();

       data.add(P.getId());
       data.add(P.getNamaPegawai());
       data.add(P.getUserName());
       data.add(P.getPassword());
       data.add(P.getRole());



       tabelModel.addRow(data);
   }
     public void setTextDataPencarian()
     {
         txtCariNamaSup.setText(" ");
         txtCariAlaSup.setText(" ");
         txtCariTelpSup.setText(" ");
     }
     public void cekAngka()throws CekAngka
     {
       if(txtNoTelp.getText().matches("[0-9]*")) 
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
         if(txtNamaPegawai.getText().matches("[0-9]*"))
         {
             throw new CekHuruf();
         }
         else
         {
             System.out.println("okk");
         }
     }
    public void tampilLayanan()
    {
        int a = tabelModel2.getRowCount() ;
        for (int i = 0; i < a; i++) {
            tabelModel2.removeRow(0);
        }
        L= new Layanan();
        List<Layanan> M = AC.tampilLayanan();
        for (int i = 0; i < M.size(); i++) {
            
            Layanan L= new Layanan();
            L.setIdLayanan(M.get(i).getIdLayanan());
            L.setNamaLayanan(M.get(i).getNamaLayanan());
            addTableLayanan(L);
        }
    }
    public void tampliSuplier()
    {
        int a = tabelModel1.getRowCount();
        for(int i = 0 ; i<a ; i++)
        {
            tabelModel1.removeRow(0);
        }
        S= new Suplier();
        List<Suplier> M= AC.tampilDataSup();
        for (int i = 0; i < M.size(); i++) {
            Suplier S = new Suplier();
            S.setId(M.get(i).getId());
            S.setNama(M.get(i).getNama());
            S.setAlamat(M.get(i).getAlamat());
            S.setTelp(M.get(i).getTelp());
            addTableSuplier(S);
            
        }
    }
    public void tampilPegawai()
    {
        
        int a = tabelModel.getRowCount();
        for (int i = 0; i < a; i++) {
            tabelModel.removeRow(0);
        }
        P = new Pegawai() ; 
        
        List<Pegawai> M = AC.tampilDataPegawai();
        for (int i = 0; i < M.size(); i++) {
           Pegawai P = new Pegawai();
           P.setNama(M.get(i).getNamaPegawai());
           P.setUserName(M.get(i).getUserName());
           P.setPassword(M.get(i).getPassword());
           P.setRole(M.get(i).getRole());
           P.setID_Pegawai(M.get(i).getId());
              addTablePegawai(P);
 
    M.get(i).getTglLahir() ;
        }
    }
    public void namaLayanan() throws dataSama
    {
        L = AC.searchLayanan(txtNamaLayanan.getText());
        if(L!=null)
        {
            throw  new dataSama();
        }
    }
    public void userNameSama() throws dataSama
    {
         
       P = AC.searchPegawai(txtUserName.getText());
        if(P!=null)
        {
            
           throw new dataSama();
           
        }
        
 
    }
    public void dissLayanan(boolean  nilai )
    {
        txtNamaLayananCari.setEnabled(nilai);
    }
    public void setTextLayanan()
    {
        txtNamaLayanan.setText("");
        txtCariLayanan.setText("");
        txtNamaLayananCari.setText("");
       
    }
    public void setTextCariPegawai()
    {
        txtCari.setText("");
        txtCariAlamat.setText("");
        txtCariNama.setText("");
        txtCariNoTlp.setText("");
        txtCariPassword.setText("");
        txtCariRole.setText("");
        txtCariTanggalLahir.setText("");
        txtCariUserName.setText("");
       
    }
    public void setText()
    {
       
        txtAlamat.setText("");
        txtNamaPegawai.setText("");
        txtNoTelp.setText("");
        txtPassword.setText("");
        txtTanggalLahir.setDate(null);
        txtUserName.setText("");    
        btnRole.clearSelection();
    }
    public void setTextSuplier()
    {
        txtNamSup.setText("");
        txtTelpSup.setText("");
        txtAlamatSup.setText("");
   
                
    }

    public void disspegawai(boolean nilai)
    
    {
       txtCariAlamat.setEnabled(nilai);
       txtCariNama.setEnabled(nilai);
       txtCariUserName.setEnabled(nilai);
       txtCariTanggalLahir.setEnabled(nilai);
       txtCariNoTlp.setEnabled(nilai);
       txtCariPassword.setEnabled(nilai);
      txtCariRole.setEnabled(nilai);

       
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnRole = new javax.swing.ButtonGroup();
        background = new javax.swing.JPanel();
        menuHome = new javax.swing.JPanel();
        judul = new javax.swing.JLabel();
        btnPegawai = new javax.swing.JButton();
        btnLayanan = new javax.swing.JButton();
        btnProduk = new javax.swing.JButton();
        btnSup = new javax.swing.JButton();
        btnHewan = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        MainPanel = new javax.swing.JPanel();
        dataPegawai = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        btnTambah = new javax.swing.JButton();
        btnHelp = new javax.swing.JButton();
        btnTampil = new javax.swing.JButton();
        btnCari = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        mainPanel2 = new javax.swing.JPanel();
        bantuan = new javax.swing.JPanel();
        tambahPegawai = new javax.swing.JPanel();
        txtNamaPegawai = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtNoTelp = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtAlamat = new javax.swing.JTextField();
        txtUserName = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtPassword = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        btnSimpanPegawai = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        txtTanggalLahir = new com.toedter.calendar.JDateChooser();
        rdKasir = new javax.swing.JRadioButton();
        rdCustomerService = new javax.swing.JRadioButton();
        cariPegawai = new javax.swing.JPanel();
        txtCari = new javax.swing.JTextField();
        btnSearch = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        txtCariNama = new javax.swing.JTextField();
        txtCariTanggalLahir = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txtCariNoTlp = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        txtCariAlamat = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        txtCariUserName = new javax.swing.JTextField();
        txtCariPassword = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        txtCariRole = new javax.swing.JTextField();
        btnEdit = new javax.swing.JButton();
        btnSimpanEdit = new javax.swing.JButton();
        btnHapus = new javax.swing.JButton();
        tampilSeluruh = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablePegawai = new javax.swing.JTable();
        dataLayanan = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        btnTambahLyn = new javax.swing.JButton();
        btnHelp1 = new javax.swing.JButton();
        btnTampilLyn = new javax.swing.JButton();
        btnCariLyn = new javax.swing.JButton();
        jLabel25 = new javax.swing.JLabel();
        mainPanel3 = new javax.swing.JPanel();
        tambahLayanan = new javax.swing.JPanel();
        txtNamaLayanan = new javax.swing.JTextField();
        btnTambahLayanan = new javax.swing.JToggleButton();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        tabelLayanan = new javax.swing.JTable();
        cariLayanan = new javax.swing.JPanel();
        txtCariLayanan = new javax.swing.JTextField();
        btnCariLayanan = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        txtNamaLayananCari = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        btnEditLayanan = new javax.swing.JButton();
        btnSimpanEditLayanan = new javax.swing.JButton();
        btnHapusLayanan = new javax.swing.JButton();
        tampilLayanan = new javax.swing.JPanel();
        dataProduk = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        btnTambah1 = new javax.swing.JButton();
        btnHelp2 = new javax.swing.JButton();
        btnTampil1 = new javax.swing.JButton();
        btnCari1 = new javax.swing.JButton();
        jLabel27 = new javax.swing.JLabel();
        dataSupplier = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        btnTambahSup = new javax.swing.JButton();
        btnSupHelp = new javax.swing.JButton();
        btnTampilSup = new javax.swing.JButton();
        btnCariSup = new javax.swing.JButton();
        jLabel29 = new javax.swing.JLabel();
        mainPanel4 = new javax.swing.JPanel();
        CariSuplier = new javax.swing.JPanel();
        jLabel32 = new javax.swing.JLabel();
        txtCariSup = new javax.swing.JTextField();
        btnSearch1 = new javax.swing.JLabel();
        txtCariNamaSup = new javax.swing.JTextField();
        txtCariAlaSup = new javax.swing.JTextField();
        txtCariTelpSup = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        btnHapusSup = new javax.swing.JButton();
        TampilSuplier = new javax.swing.JPanel();
        HelpSuplier = new javax.swing.JPanel();
        TambahSuplier = new javax.swing.JPanel();
        jLabel31 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableSupTambahData = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        btnSimpanSuplier = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        txtTelpSup = new javax.swing.JTextField();
        txtAlamatSup = new javax.swing.JTextField();
        txtNamSup = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        dataHewan = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        btnCariHewan = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImage(new javax.swing.ImageIcon(getClass().getResource("/icon/dogg.png")).getImage());

        background.setBackground(new java.awt.Color(255, 255, 255));
        background.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        menuHome.setBackground(new java.awt.Color(99, 175, 241));
        menuHome.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        judul.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        judul.setForeground(new java.awt.Color(0, 0, 0));
        judul.setText("MENU ADMIN");

        btnPegawai.setFont(new java.awt.Font("Serif", 1, 12)); // NOI18N
        btnPegawai.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icon.png"))); // NOI18N
        btnPegawai.setText("Data Pegawai");
        btnPegawai.setHideActionText(true);
        btnPegawai.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnPegawai.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnPegawai.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btnPegawai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPegawaiActionPerformed(evt);
            }
        });

        btnLayanan.setFont(new java.awt.Font("Serif", 1, 12)); // NOI18N
        btnLayanan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/k-services-icon.png"))); // NOI18N
        btnLayanan.setText("Data Layanan");
        btnLayanan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnLayanan.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnLayanan.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btnLayanan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLayananActionPerformed(evt);
            }
        });

        btnProduk.setFont(new java.awt.Font("Serif", 1, 12)); // NOI18N
        btnProduk.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Box-icon.png"))); // NOI18N
        btnProduk.setText("Data Produk");
        btnProduk.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnProduk.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnProduk.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btnProduk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProdukActionPerformed(evt);
            }
        });

        btnSup.setFont(new java.awt.Font("Serif", 1, 12)); // NOI18N
        btnSup.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/truck.png"))); // NOI18N
        btnSup.setText("Data Supplier");
        btnSup.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnSup.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnSup.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btnSup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSupActionPerformed(evt);
            }
        });

        btnHewan.setFont(new java.awt.Font("Serif", 1, 12)); // NOI18N
        btnHewan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/dogy.png"))); // NOI18N
        btnHewan.setText("Data Hewan");
        btnHewan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnHewan.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnHewan.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btnHewan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHewanActionPerformed(evt);
            }
        });

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/pet-shop-1.png"))); // NOI18N

        javax.swing.GroupLayout menuHomeLayout = new javax.swing.GroupLayout(menuHome);
        menuHome.setLayout(menuHomeLayout);
        menuHomeLayout.setHorizontalGroup(
            menuHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuHomeLayout.createSequentialGroup()
                .addGroup(menuHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(menuHomeLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(menuHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnSup, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnPegawai)
                            .addComponent(btnProduk, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnLayanan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnHewan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(menuHomeLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel13))
                    .addGroup(menuHomeLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(judul)))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        menuHomeLayout.setVerticalGroup(
            menuHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuHomeLayout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(judul)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnPegawai)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnLayanan)
                .addGap(18, 18, 18)
                .addComponent(btnProduk)
                .addGap(18, 18, 18)
                .addComponent(btnSup)
                .addGap(18, 18, 18)
                .addComponent(btnHewan)
                .addContainerGap())
        );

        MainPanel.setBackground(new java.awt.Color(168, 238, 244));
        MainPanel.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createEtchedBorder(), javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED)));
        MainPanel.setLayout(new java.awt.CardLayout());

        dataPegawai.setBackground(new java.awt.Color(255, 255, 255));

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTambah)
                    .addComponent(btnCari)
                    .addComponent(btnHelp)
                    .addComponent(btnTampil))
                .addContainerGap())
        );

        mainPanel2.setLayout(new java.awt.CardLayout());

        bantuan.setBackground(new java.awt.Color(89, 238, 191));

        javax.swing.GroupLayout bantuanLayout = new javax.swing.GroupLayout(bantuan);
        bantuan.setLayout(bantuanLayout);
        bantuanLayout.setHorizontalGroup(
            bantuanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1167, Short.MAX_VALUE)
        );
        bantuanLayout.setVerticalGroup(
            bantuanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 739, Short.MAX_VALUE)
        );

        mainPanel2.add(bantuan, "card5");

        tambahPegawai.setBackground(new java.awt.Color(99, 175, 241));
        tambahPegawai.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tambahPegawai.setForeground(new java.awt.Color(0, 0, 0));

        txtNamaPegawai.setFont(new java.awt.Font("Times New Roman", 1, 11)); // NOI18N
        txtNamaPegawai.setForeground(new java.awt.Color(0, 0, 0));
        txtNamaPegawai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNamaPegawaiActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Felix Titling", 1, 11)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Nama ");

        jLabel2.setFont(new java.awt.Font("Felix Titling", 1, 11)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Tanggal Lahir ");

        txtNoTelp.setFont(new java.awt.Font("Times New Roman", 1, 11)); // NOI18N
        txtNoTelp.setForeground(new java.awt.Color(0, 0, 0));
        txtNoTelp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNoTelpActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Felix Titling", 1, 11)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("No Tlp ");

        jLabel8.setFont(new java.awt.Font("Felix Titling", 1, 11)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Alamat ");

        txtAlamat.setFont(new java.awt.Font("Times New Roman", 1, 11)); // NOI18N
        txtAlamat.setForeground(new java.awt.Color(0, 0, 0));

        txtUserName.setFont(new java.awt.Font("Times New Roman", 1, 11)); // NOI18N
        txtUserName.setForeground(new java.awt.Color(0, 0, 0));

        jLabel9.setFont(new java.awt.Font("Felix Titling", 1, 11)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("User Name ");

        txtPassword.setFont(new java.awt.Font("Times New Roman", 1, 11)); // NOI18N
        txtPassword.setForeground(new java.awt.Color(0, 0, 0));

        jLabel10.setFont(new java.awt.Font("Felix Titling", 1, 11)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Password ");

        jLabel11.setFont(new java.awt.Font("Felix Titling", 1, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Role ");

        btnSimpanPegawai.setBackground(new java.awt.Color(102, 255, 255));
        btnSimpanPegawai.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/save.png"))); // NOI18N
        btnSimpanPegawai.setText("Tambah ");
        btnSimpanPegawai.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        btnSimpanPegawai.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnSimpanPegawai.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btnSimpanPegawai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanPegawaiActionPerformed(evt);
            }
        });

        jLabel6.setBackground(new java.awt.Color(153, 153, 255));
        jLabel6.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/w.png"))); // NOI18N
        jLabel6.setText("Tambah Pegawai ");

        jButton3.setBackground(new java.awt.Color(255, 0, 51));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Misc-Delete-Database-icon.png"))); // NOI18N
        jButton3.setText("Batal");
        jButton3.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        jButton3.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jButton3.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        txtTanggalLahir.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        btnRole.add(rdKasir);
        rdKasir.setText("Kasir");
        rdKasir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdKasirActionPerformed(evt);
            }
        });

        btnRole.add(rdCustomerService);
        rdCustomerService.setText("Customer Service");
        rdCustomerService.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdCustomerServiceActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout tambahPegawaiLayout = new javax.swing.GroupLayout(tambahPegawai);
        tambahPegawai.setLayout(tambahPegawaiLayout);
        tambahPegawaiLayout.setHorizontalGroup(
            tambahPegawaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tambahPegawaiLayout.createSequentialGroup()
                .addGroup(tambahPegawaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tambahPegawaiLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel6))
                    .addGroup(tambahPegawaiLayout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addGroup(tambahPegawaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(tambahPegawaiLayout.createSequentialGroup()
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtAlamat, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(tambahPegawaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(tambahPegawaiLayout.createSequentialGroup()
                                    .addGap(265, 265, 265)
                                    .addComponent(jButton3)
                                    .addGap(18, 18, 18)
                                    .addComponent(btnSimpanPegawai))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, tambahPegawaiLayout.createSequentialGroup()
                                    .addGroup(tambahPegawaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(tambahPegawaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txtNamaPegawai, javax.swing.GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)
                                        .addComponent(txtNoTelp))
                                    .addGap(42, 42, 42)
                                    .addGroup(tambahPegawaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel2)
                                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addGroup(tambahPegawaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txtTanggalLahir, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
                                        .addComponent(txtPassword)
                                        .addComponent(txtUserName))))
                            .addGroup(tambahPegawaiLayout.createSequentialGroup()
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rdCustomerService)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(rdKasir, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        tambahPegawaiLayout.setVerticalGroup(
            tambahPegawaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tambahPegawaiLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addGroup(tambahPegawaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(tambahPegawaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtNamaPegawai, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2))
                    .addComponent(txtTanggalLahir, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(tambahPegawaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtUserName, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(tambahPegawaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtNoTelp, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(tambahPegawaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tambahPegawaiLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(tambahPegawaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtAlamat, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(tambahPegawaiLayout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(tambahPegawaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(txtPassword, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE))))
                .addGap(3, 3, 3)
                .addGroup(tambahPegawaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(rdCustomerService)
                    .addComponent(rdKasir))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(tambahPegawaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSimpanPegawai))
                .addGap(105, 105, 105))
        );

        mainPanel2.add(tambahPegawai, "card2");

        cariPegawai.setBackground(new java.awt.Color(99, 175, 241));
        cariPegawai.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txtCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCariActionPerformed(evt);
            }
        });

        btnSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/search-icon.png"))); // NOI18N
        btnSearch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSearchMouseClicked(evt);
            }
        });

        jLabel21.setBackground(new java.awt.Color(0, 255, 255));
        jLabel21.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(0, 0, 0));
        jLabel21.setText("Cari Pegawai");

        jPanel5.setBackground(new java.awt.Color(99, 175, 241));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Trajan Pro", 0, 18)));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Trajan Pro", 0, 18)));;

        jLabel14.setBackground(new java.awt.Color(0, 255, 255));
        jLabel14.setFont(new java.awt.Font("Clarendon Lt BT", 0, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("Nama");

        txtCariNama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCariNamaActionPerformed(evt);
            }
        });

        jLabel15.setBackground(new java.awt.Color(0, 255, 255));
        jLabel15.setFont(new java.awt.Font("Clarendon Lt BT", 0, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("Tanggal Lahir");

        jLabel16.setBackground(new java.awt.Color(0, 255, 255));
        jLabel16.setFont(new java.awt.Font("Clarendon Lt BT", 0, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("No Tlp");

        txtCariNoTlp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCariNoTlpActionPerformed(evt);
            }
        });

        jLabel17.setBackground(new java.awt.Color(0, 255, 255));
        jLabel17.setFont(new java.awt.Font("Clarendon Lt BT", 0, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setText("Alamat");

        jLabel18.setBackground(new java.awt.Color(0, 255, 255));
        jLabel18.setFont(new java.awt.Font("Clarendon Lt BT", 0, 18)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(0, 0, 0));
        jLabel18.setText("User Name");

        jLabel19.setBackground(new java.awt.Color(0, 255, 255));
        jLabel19.setFont(new java.awt.Font("Clarendon Lt BT", 0, 18)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setText("Password");

        jLabel20.setBackground(new java.awt.Color(0, 255, 255));
        jLabel20.setFont(new java.awt.Font("Clarendon Lt BT", 0, 18)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(0, 0, 0));
        jLabel20.setText("Role");

        txtCariRole.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCariRoleActionPerformed(evt);
            }
        });

        btnEdit.setText("EDIT");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        btnSimpanEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/save.png"))); // NOI18N
        btnSimpanEdit.setText("SIMPAN");
        btnSimpanEdit.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnSimpanEdit.setMargin(new java.awt.Insets(0, 0, 2, 0));
        btnSimpanEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanEditActionPerformed(evt);
            }
        });

        btnHapus.setText("HAPUS");
        btnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSimpanEdit)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnHapus, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtCariRole, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtCariPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtCariUserName, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(24, 24, 24)
                                .addComponent(txtCariTanggalLahir, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(24, 24, 24)
                                .addComponent(txtCariNoTlp, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(24, 24, 24)
                                .addComponent(txtCariAlamat, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtCariNama, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(38, 38, 38))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCariNama, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtCariTanggalLahir)
                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtCariNoTlp)
                    .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCariAlamat, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCariUserName, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(txtCariPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCariRole, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 64, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSimpanEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnHapus, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout cariPegawaiLayout = new javax.swing.GroupLayout(cariPegawai);
        cariPegawai.setLayout(cariPegawaiLayout);
        cariPegawaiLayout.setHorizontalGroup(
            cariPegawaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cariPegawaiLayout.createSequentialGroup()
                .addGroup(cariPegawaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(cariPegawaiLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(cariPegawaiLayout.createSequentialGroup()
                        .addGap(78, 78, 78)
                        .addComponent(btnSearch)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtCari, javax.swing.GroupLayout.PREFERRED_SIZE, 377, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(cariPegawaiLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(624, Short.MAX_VALUE))
        );
        cariPegawaiLayout.setVerticalGroup(
            cariPegawaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, cariPegawaiLayout.createSequentialGroup()
                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addGroup(cariPegawaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtCari, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearch))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(241, Short.MAX_VALUE))
        );

        mainPanel2.add(cariPegawai, "card3");

        tampilSeluruh.setBackground(new java.awt.Color(99, 175, 241));

        tablePegawai.setBackground(new java.awt.Color(255, 255, 255));
        tablePegawai.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        tablePegawai.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        tablePegawai.setForeground(new java.awt.Color(0, 0, 0));
        tablePegawai.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID_PEGAWAI", "Nama Pegawai", "User Name ", "Password", "Role"
            }
        ));
        tablePegawai.setGridColor(new java.awt.Color(0, 0, 0));
        jScrollPane1.setViewportView(tablePegawai);

        javax.swing.GroupLayout tampilSeluruhLayout = new javax.swing.GroupLayout(tampilSeluruh);
        tampilSeluruh.setLayout(tampilSeluruhLayout);
        tampilSeluruhLayout.setHorizontalGroup(
            tampilSeluruhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tampilSeluruhLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1143, Short.MAX_VALUE)
                .addContainerGap())
        );
        tampilSeluruhLayout.setVerticalGroup(
            tampilSeluruhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tampilSeluruhLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(305, Short.MAX_VALUE))
        );

        mainPanel2.add(tampilSeluruh, "card4");

        javax.swing.GroupLayout dataPegawaiLayout = new javax.swing.GroupLayout(dataPegawai);
        dataPegawai.setLayout(dataPegawaiLayout);
        dataPegawaiLayout.setHorizontalGroup(
            dataPegawaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dataPegawaiLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(dataPegawaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(mainPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        dataPegawaiLayout.setVerticalGroup(
            dataPegawaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dataPegawaiLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(mainPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        MainPanel.add(dataPegawai, "card6");

        dataLayanan.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(99, 175, 241));

        btnTambahLyn.setBackground(new java.awt.Color(51, 255, 255));
        btnTambahLyn.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnTambahLyn.setForeground(new java.awt.Color(0, 0, 0));
        btnTambahLyn.setText("Tambah Layanan");
        btnTambahLyn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTambahLynActionPerformed(evt);
            }
        });

        btnHelp1.setText("Help");
        btnHelp1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHelp1ActionPerformed(evt);
            }
        });

        btnTampilLyn.setBackground(new java.awt.Color(51, 255, 255));
        btnTampilLyn.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnTampilLyn.setForeground(new java.awt.Color(0, 0, 0));
        btnTampilLyn.setText("Tampil Seluruh");
        btnTampilLyn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTampilLynActionPerformed(evt);
            }
        });

        btnCariLyn.setBackground(new java.awt.Color(51, 255, 255));
        btnCariLyn.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnCariLyn.setForeground(new java.awt.Color(0, 0, 0));
        btnCariLyn.setText("Cari Layanan");
        btnCariLyn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariLynActionPerformed(evt);
            }
        });

        jLabel25.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 24)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(0, 0, 0));
        jLabel25.setText("MENGELOLA LAYANAN KOEVEE PET SHOP");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnTambahLyn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnCariLyn, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnTampilLyn)
                        .addGap(64, 64, 64)
                        .addComponent(btnHelp1))
                    .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 389, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTambahLyn)
                    .addComponent(btnCariLyn)
                    .addComponent(btnHelp1)
                    .addComponent(btnTampilLyn))
                .addContainerGap())
        );

        mainPanel3.setLayout(new java.awt.CardLayout());

        tambahLayanan.setBackground(new java.awt.Color(99, 175, 241));

        txtNamaLayanan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNamaLayananActionPerformed(evt);
            }
        });

        btnTambahLayanan.setText("Tambah");
        btnTambahLayanan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTambahLayananActionPerformed(evt);
            }
        });

        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/customer-service-woman-icon.png"))); // NOI18N

        jLabel24.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(0, 0, 0));
        jLabel24.setText("TAMBAH LAYANAN");

        jLabel22.setFont(new java.awt.Font("Clarendon BT", 0, 18)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(0, 0, 0));
        jLabel22.setText("Nama Layananan");

        jButton5.setText("Batal");

        tabelLayanan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "ID_Lyn", "Nama Lyn"
            }
        ));
        jScrollPane4.setViewportView(tabelLayanan);

        javax.swing.GroupLayout tambahLayananLayout = new javax.swing.GroupLayout(tambahLayanan);
        tambahLayanan.setLayout(tambahLayananLayout);
        tambahLayananLayout.setHorizontalGroup(
            tambahLayananLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tambahLayananLayout.createSequentialGroup()
                .addGroup(tambahLayananLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tambahLayananLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel23)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(tambahLayananLayout.createSequentialGroup()
                        .addGroup(tambahLayananLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(tambahLayananLayout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel22)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(tambahLayananLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jButton5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnTambahLayanan)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addComponent(txtNamaLayanan, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(135, 135, 135))
        );
        tambahLayananLayout.setVerticalGroup(
            tambahLayananLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tambahLayananLayout.createSequentialGroup()
                .addGroup(tambahLayananLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tambahLayananLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(tambahLayananLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel23)
                            .addGroup(tambahLayananLayout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(43, 43, 43)
                        .addGroup(tambahLayananLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNamaLayanan, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(73, 73, 73)
                        .addGroup(tambahLayananLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnTambahLayanan)
                            .addComponent(jButton5)))
                    .addGroup(tambahLayananLayout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(271, Short.MAX_VALUE))
        );

        mainPanel3.add(tambahLayanan, "card2");

        cariLayanan.setBackground(new java.awt.Color(99, 175, 241));

        txtCariLayanan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCariLayananActionPerformed(evt);
            }
        });

        btnCariLayanan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/search-icon.png"))); // NOI18N
        btnCariLayanan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCariLayananMouseClicked(evt);
            }
        });

        jLabel26.setBackground(new java.awt.Color(0, 255, 255));
        jLabel26.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(0, 255, 255));
        jLabel26.setText("Cari Layanan");

        jLabel28.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(102, 255, 255));
        jLabel28.setText("Nama Layanan");

        btnEditLayanan.setText("EDIT");
        btnEditLayanan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditLayananActionPerformed(evt);
            }
        });

        btnSimpanEditLayanan.setText("SIMPAN");
        btnSimpanEditLayanan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanEditLayananActionPerformed(evt);
            }
        });

        btnHapusLayanan.setText("HAPUS");
        btnHapusLayanan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusLayananActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout cariLayananLayout = new javax.swing.GroupLayout(cariLayanan);
        cariLayanan.setLayout(cariLayananLayout);
        cariLayananLayout.setHorizontalGroup(
            cariLayananLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cariLayananLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(cariLayananLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(cariLayananLayout.createSequentialGroup()
                        .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(95, 811, Short.MAX_VALUE))
                    .addGroup(cariLayananLayout.createSequentialGroup()
                        .addGroup(cariLayananLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(cariLayananLayout.createSequentialGroup()
                                .addComponent(btnEditLayanan)
                                .addGap(18, 18, 18)
                                .addComponent(btnSimpanEditLayanan)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnHapusLayanan))
                            .addGroup(cariLayananLayout.createSequentialGroup()
                                .addGroup(cariLayananLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(cariLayananLayout.createSequentialGroup()
                                        .addGap(24, 24, 24)
                                        .addComponent(btnCariLayanan)))
                                .addGap(1, 1, 1)
                                .addGroup(cariLayananLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtCariLayanan, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtNamaLayananCari, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        cariLayananLayout.setVerticalGroup(
            cariLayananLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cariLayananLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(cariLayananLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtCariLayanan, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCariLayanan))
                .addGap(18, 18, 18)
                .addGroup(cariLayananLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNamaLayananCari, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41)
                .addGroup(cariLayananLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEditLayanan)
                    .addComponent(btnSimpanEditLayanan)
                    .addComponent(btnHapusLayanan))
                .addContainerGap(548, Short.MAX_VALUE))
        );

        mainPanel3.add(cariLayanan, "card3");

        tampilLayanan.setBackground(new java.awt.Color(99, 175, 241));

        javax.swing.GroupLayout tampilLayananLayout = new javax.swing.GroupLayout(tampilLayanan);
        tampilLayanan.setLayout(tampilLayananLayout);
        tampilLayananLayout.setHorizontalGroup(
            tampilLayananLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1041, Short.MAX_VALUE)
        );
        tampilLayananLayout.setVerticalGroup(
            tampilLayananLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 743, Short.MAX_VALUE)
        );

        mainPanel3.add(tampilLayanan, "card4");

        javax.swing.GroupLayout dataLayananLayout = new javax.swing.GroupLayout(dataLayanan);
        dataLayanan.setLayout(dataLayananLayout);
        dataLayananLayout.setHorizontalGroup(
            dataLayananLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(dataLayananLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mainPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        dataLayananLayout.setVerticalGroup(
            dataLayananLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dataLayananLayout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(mainPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        MainPanel.add(dataLayanan, "card3");

        dataProduk.setBackground(new java.awt.Color(255, 255, 255));

        jPanel3.setBackground(new java.awt.Color(99, 175, 241));

        btnTambah1.setBackground(new java.awt.Color(51, 255, 255));
        btnTambah1.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnTambah1.setForeground(new java.awt.Color(0, 0, 0));
        btnTambah1.setText("Tambah Pegawai");
        btnTambah1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTambah1ActionPerformed(evt);
            }
        });

        btnHelp2.setBackground(new java.awt.Color(51, 255, 255));
        btnHelp2.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnHelp2.setForeground(new java.awt.Color(0, 0, 0));
        btnHelp2.setText("Help");
        btnHelp2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHelp2ActionPerformed(evt);
            }
        });

        btnTampil1.setBackground(new java.awt.Color(51, 255, 255));
        btnTampil1.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnTampil1.setForeground(new java.awt.Color(0, 0, 0));
        btnTampil1.setText("Tampil Seluruh");
        btnTampil1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTampil1ActionPerformed(evt);
            }
        });

        btnCari1.setBackground(new java.awt.Color(51, 255, 255));
        btnCari1.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnCari1.setForeground(new java.awt.Color(0, 0, 0));
        btnCari1.setText("Cari Pegawai");
        btnCari1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCari1ActionPerformed(evt);
            }
        });

        jLabel27.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 24)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(0, 153, 153));
        jLabel27.setText("MENGELOLA PEGAWAI KOEVEE PET SHOP");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 389, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(btnTambah1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnCari1, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnTampil1)))
                .addGap(49, 49, 49)
                .addComponent(btnHelp2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTambah1)
                    .addComponent(btnCari1)
                    .addComponent(btnHelp2)
                    .addComponent(btnTampil1))
                .addContainerGap())
        );

        javax.swing.GroupLayout dataProdukLayout = new javax.swing.GroupLayout(dataProduk);
        dataProduk.setLayout(dataProdukLayout);
        dataProdukLayout.setHorizontalGroup(
            dataProdukLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dataProdukLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        dataProdukLayout.setVerticalGroup(
            dataProdukLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dataProdukLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(759, Short.MAX_VALUE))
        );

        MainPanel.add(dataProduk, "card4");

        dataSupplier.setBackground(new java.awt.Color(255, 255, 255));

        jPanel4.setBackground(new java.awt.Color(99, 175, 241));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btnTambahSup.setBackground(new java.awt.Color(255, 255, 255));
        btnTambahSup.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnTambahSup.setForeground(new java.awt.Color(0, 0, 0));
        btnTambahSup.setText("Tambah Suplier");
        btnTambahSup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTambahSupActionPerformed(evt);
            }
        });

        btnSupHelp.setBackground(new java.awt.Color(51, 255, 255));
        btnSupHelp.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnSupHelp.setForeground(new java.awt.Color(0, 0, 0));
        btnSupHelp.setText("Help");
        btnSupHelp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSupHelpActionPerformed(evt);
            }
        });

        btnTampilSup.setBackground(new java.awt.Color(255, 255, 255));
        btnTampilSup.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnTampilSup.setForeground(new java.awt.Color(0, 0, 0));
        btnTampilSup.setText("Tampil Seluruh Suplier");
        btnTampilSup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTampilSupActionPerformed(evt);
            }
        });

        btnCariSup.setBackground(new java.awt.Color(255, 255, 255));
        btnCariSup.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnCariSup.setForeground(new java.awt.Color(0, 0, 0));
        btnCariSup.setText("Cari Suplier");
        btnCariSup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariSupActionPerformed(evt);
            }
        });

        jLabel29.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 24)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(0, 0, 0));
        jLabel29.setText("MENGELOLA SUPLIER KOEVEE PET SHOP");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 389, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(btnTambahSup)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnCariSup, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnTampilSup)))
                .addGap(49, 49, 49)
                .addComponent(btnSupHelp)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTambahSup)
                    .addComponent(btnCariSup)
                    .addComponent(btnSupHelp)
                    .addComponent(btnTampilSup))
                .addContainerGap())
        );

        mainPanel4.setPreferredSize(new java.awt.Dimension(684, 421));
        mainPanel4.setLayout(new java.awt.CardLayout());

        CariSuplier.setBackground(java.awt.Color.darkGray);

        jLabel32.setBackground(new java.awt.Color(0, 255, 255));
        jLabel32.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(0, 255, 255));
        jLabel32.setText("Cari Supplier");

        txtCariSup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCariSupActionPerformed(evt);
            }
        });

        btnSearch1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/search-icon.png"))); // NOI18N
        btnSearch1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSearch1MouseClicked(evt);
            }
        });

        txtCariTelpSup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCariTelpSupActionPerformed(evt);
            }
        });

        jLabel33.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(153, 255, 255));
        jLabel33.setText("Nama Suplier");

        jLabel34.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(153, 255, 255));
        jLabel34.setText("Alamat");

        jLabel35.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(153, 255, 255));
        jLabel35.setText("Telephone");

        jButton1.setText("EDIT");

        jButton4.setText("SIMPAN");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        btnHapusSup.setText("HAPUS");
        btnHapusSup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusSupActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout CariSuplierLayout = new javax.swing.GroupLayout(CariSuplier);
        CariSuplier.setLayout(CariSuplierLayout);
        CariSuplierLayout.setHorizontalGroup(
            CariSuplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CariSuplierLayout.createSequentialGroup()
                .addGroup(CariSuplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(CariSuplierLayout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addGroup(CariSuplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(CariSuplierLayout.createSequentialGroup()
                                .addComponent(btnSearch1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCariSup, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(CariSuplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, CariSuplierLayout.createSequentialGroup()
                                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jButton4)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnHapusSup))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, CariSuplierLayout.createSequentialGroup()
                                    .addGroup(CariSuplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(CariSuplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(jLabel34, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel33, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)))
                                    .addGap(2, 2, 2)
                                    .addGroup(CariSuplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(txtCariTelpSup)
                                        .addComponent(txtCariAlaSup)
                                        .addComponent(txtCariNamaSup, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addGroup(CariSuplierLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(831, Short.MAX_VALUE))
        );
        CariSuplierLayout.setVerticalGroup(
            CariSuplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CariSuplierLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addGroup(CariSuplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtCariSup, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearch1))
                .addGroup(CariSuplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(CariSuplierLayout.createSequentialGroup()
                        .addGroup(CariSuplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(CariSuplierLayout.createSequentialGroup()
                                .addGap(78, 78, 78)
                                .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, CariSuplierLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(93, 93, 93))
                    .addGroup(CariSuplierLayout.createSequentialGroup()
                        .addGap(78, 78, 78)
                        .addComponent(txtCariNamaSup, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCariAlaSup, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCariTelpSup, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(CariSuplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton4)
                    .addComponent(btnHapusSup))
                .addContainerGap(396, Short.MAX_VALUE))
        );

        mainPanel4.add(CariSuplier, "card3");

        javax.swing.GroupLayout TampilSuplierLayout = new javax.swing.GroupLayout(TampilSuplier);
        TampilSuplier.setLayout(TampilSuplierLayout);
        TampilSuplierLayout.setHorizontalGroup(
            TampilSuplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1167, Short.MAX_VALUE)
        );
        TampilSuplierLayout.setVerticalGroup(
            TampilSuplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 733, Short.MAX_VALUE)
        );

        mainPanel4.add(TampilSuplier, "card4");

        javax.swing.GroupLayout HelpSuplierLayout = new javax.swing.GroupLayout(HelpSuplier);
        HelpSuplier.setLayout(HelpSuplierLayout);
        HelpSuplierLayout.setHorizontalGroup(
            HelpSuplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1167, Short.MAX_VALUE)
        );
        HelpSuplierLayout.setVerticalGroup(
            HelpSuplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 733, Short.MAX_VALUE)
        );

        mainPanel4.add(HelpSuplier, "card5");

        TambahSuplier.setBackground(new java.awt.Color(99, 175, 241));
        TambahSuplier.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel31.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(0, 0, 0));
        jLabel31.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/truk2.png"))); // NOI18N
        jLabel31.setText("TAMBAH SUPLIER");

        tableSupTambahData.setBackground(new java.awt.Color(255, 255, 255));
        tableSupTambahData.setFont(new java.awt.Font("Geometr212 BkCn BT", 1, 12)); // NOI18N
        tableSupTambahData.setForeground(new java.awt.Color(0, 0, 0));
        tableSupTambahData.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID_Sup", "Nama Supplier", "Alamat", "Telp"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableSupTambahData.setGridColor(new java.awt.Color(0, 0, 0));
        jScrollPane2.setViewportView(tableSupTambahData);
        if (tableSupTambahData.getColumnModel().getColumnCount() > 0) {
            tableSupTambahData.getColumnModel().getColumn(0).setResizable(false);
            tableSupTambahData.getColumnModel().getColumn(1).setResizable(false);
            tableSupTambahData.getColumnModel().getColumn(2).setResizable(false);
            tableSupTambahData.getColumnModel().getColumn(3).setResizable(false);
        }

        jPanel6.setBackground(new java.awt.Color(99, 175, 241));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11))); // NOI18N

        btnSimpanSuplier.setText("SIMPAN");
        btnSimpanSuplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanSuplierActionPerformed(evt);
            }
        });

        jButton2.setText("BATAL");

        txtNamSup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNamSupActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Clarendon BT", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Nama Suplier");

        jLabel4.setFont(new java.awt.Font("Clarendon BT", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Alamat");

        jLabel30.setFont(new java.awt.Font("Clarendon BT", 0, 18)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(0, 0, 0));
        jLabel30.setText("Telephone");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnSimpanSuplier, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtNamSup, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtAlamatSup, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtTelpSup, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(35, 35, 35))))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNamSup, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAlamatSup, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTelpSup, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 102, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(btnSimpanSuplier))
                .addGap(76, 76, 76))
        );

        javax.swing.GroupLayout TambahSuplierLayout = new javax.swing.GroupLayout(TambahSuplier);
        TambahSuplier.setLayout(TambahSuplierLayout);
        TambahSuplierLayout.setHorizontalGroup(
            TambahSuplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TambahSuplierLayout.createSequentialGroup()
                .addContainerGap(94, Short.MAX_VALUE)
                .addGroup(TambahSuplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(28, 28, 28)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 489, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(148, 148, 148))
        );
        TambahSuplierLayout.setVerticalGroup(
            TambahSuplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TambahSuplierLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(TambahSuplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(223, Short.MAX_VALUE))
        );

        mainPanel4.add(TambahSuplier, "card2");

        javax.swing.GroupLayout dataSupplierLayout = new javax.swing.GroupLayout(dataSupplier);
        dataSupplier.setLayout(dataSupplierLayout);
        dataSupplierLayout.setHorizontalGroup(
            dataSupplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dataSupplierLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(dataSupplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(mainPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 1167, Short.MAX_VALUE))
                .addContainerGap())
        );
        dataSupplierLayout.setVerticalGroup(
            dataSupplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dataSupplierLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(mainPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 733, Short.MAX_VALUE)
                .addContainerGap())
        );

        MainPanel.add(dataSupplier, "card5");

        dataHewan.setBackground(new java.awt.Color(65, 224, 248));

        jLabel5.setText("DATA HEWAN");

        btnCariHewan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/search-icon.png"))); // NOI18N
        btnCariHewan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCariHewanMouseClicked(evt);
            }
        });

        jTextField4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout dataHewanLayout = new javax.swing.GroupLayout(dataHewan);
        dataHewan.setLayout(dataHewanLayout);
        dataHewanLayout.setHorizontalGroup(
            dataHewanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dataHewanLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(dataHewanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addGroup(dataHewanLayout.createSequentialGroup()
                        .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCariHewan)))
                .addContainerGap(907, Short.MAX_VALUE))
        );
        dataHewanLayout.setVerticalGroup(
            dataHewanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dataHewanLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(dataHewanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCariHewan))
                .addContainerGap(813, Short.MAX_VALUE))
        );

        MainPanel.add(dataHewan, "card6");

        javax.swing.GroupLayout backgroundLayout = new javax.swing.GroupLayout(background);
        background.setLayout(backgroundLayout);
        backgroundLayout.setHorizontalGroup(
            backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backgroundLayout.createSequentialGroup()
                .addComponent(menuHome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(MainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        backgroundLayout.setVerticalGroup(
            backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backgroundLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(menuHome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(MainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnCariHewanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCariHewanMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCariHewanMouseClicked

    private void jTextField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField4ActionPerformed

    private void btnTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahActionPerformed
        // TODO add your handling code here:
         mainPanel2.removeAll();
         mainPanel2.repaint();
         mainPanel2.revalidate();
         mainPanel2.add(tambahPegawai);
         mainPanel2.repaint(); 
        mainPanel2.revalidate();
        
    }//GEN-LAST:event_btnTambahActionPerformed

    private void btnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariActionPerformed
        // TODO add your handling code here:
        mainPanel2.removeAll();
        mainPanel2.repaint();
        mainPanel2.revalidate();
        mainPanel2.add(cariPegawai);
        mainPanel2.repaint(); 
        mainPanel2.revalidate();

    }//GEN-LAST:event_btnCariActionPerformed

    private void btnHelpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHelpActionPerformed
        // TODO add your handling code here:
        mainPanel2.removeAll();
        mainPanel2.repaint();
        mainPanel2.revalidate();
        mainPanel2.add(bantuan);
        mainPanel2.repaint(); 
        mainPanel2.revalidate();
        
    }//GEN-LAST:event_btnHelpActionPerformed

    private void btnTampilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTampilActionPerformed
        // TODO add your handling code here:
        mainPanel2.removeAll();
        mainPanel2.repaint();
        mainPanel2.revalidate();
        mainPanel2.add(tampilSeluruh);
        mainPanel2.repaint(); 
        mainPanel2.revalidate();
        tampilPegawai();
        
    }//GEN-LAST:event_btnTampilActionPerformed

    private void btnTambahLynActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahLynActionPerformed
        // TODO add your handling code here:
        mainPanel3.removeAll();
        mainPanel3.repaint();
        mainPanel3.revalidate();
        mainPanel3.add(tambahLayanan);
        mainPanel3.repaint(); 
        mainPanel3.revalidate();
    }//GEN-LAST:event_btnTambahLynActionPerformed

    private void btnHelp1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHelp1ActionPerformed
        // TODO add your handling code here:
        mainPanel3.removeAll();
        mainPanel3.repaint();
        mainPanel3.revalidate();
        mainPanel3.add(tampilLayanan);
        mainPanel3.repaint(); 
        mainPanel3.revalidate();
    }//GEN-LAST:event_btnHelp1ActionPerformed

    private void btnTampilLynActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTampilLynActionPerformed
        // TODO add your handling code here:
        mainPanel3.removeAll();
        mainPanel3.repaint();
        mainPanel3.revalidate();
        mainPanel3.add(tampilLayanan);
        mainPanel3.repaint(); 
        mainPanel3.revalidate();
        
    }//GEN-LAST:event_btnTampilLynActionPerformed

    private void btnCariLynActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariLynActionPerformed
        // TODO add your handling code here:
        
        mainPanel3.removeAll();
        mainPanel3.repaint();
        mainPanel3.revalidate();
        mainPanel3.add(cariLayanan);
        mainPanel3.repaint(); 
        mainPanel3.revalidate();
        setText();

    }//GEN-LAST:event_btnCariLynActionPerformed

    private void btnSearchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSearchMouseClicked
        // TODO add your handling code here:

          
        P=  AC.searchPegawai(txtCari.getText());
        if(P!=null)
        {   
            String noHp = String.valueOf(P.getNoHp());
            txtCariNama.setText(P.getNamaPegawai());
            txtCariNoTlp.setText(noHp);
            txtCariTanggalLahir.setText(P.getTglLahir());
            txtCariAlamat.setText(P.getAlamat());
            txtCariUserName.setText(P.getUserName());
            txtCariPassword.setText(P.getPassword());
            txtCariRole.setText(P.getRole());

        }
        else
        {
            JOptionPane.showMessageDialog(this, "User Name Pegawai Tidak Ditemukan !");
        }
    }//GEN-LAST:event_btnSearchMouseClicked

    private void btnHewanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHewanActionPerformed
        // TODO add your handling code here:
        MainPanel.removeAll();
        MainPanel.repaint();
        MainPanel.revalidate();
        MainPanel.add(dataHewan);
        MainPanel.repaint();
        MainPanel.revalidate();

    }//GEN-LAST:event_btnHewanActionPerformed

    private void btnSupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSupActionPerformed
        // TODO add your handling code here:
        MainPanel.removeAll();
        MainPanel.repaint();
        MainPanel.revalidate();
        MainPanel.add(dataSupplier);
        MainPanel.repaint();
        MainPanel.revalidate();

    }//GEN-LAST:event_btnSupActionPerformed

    private void btnProdukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProdukActionPerformed
        // TODO add your handling code here:
        MainPanel.removeAll();
        MainPanel.repaint();
        MainPanel.revalidate();
        MainPanel.add(dataProduk);
        MainPanel.repaint();
        MainPanel.revalidate();
        setText();
    }//GEN-LAST:event_btnProdukActionPerformed

    private void btnLayananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLayananActionPerformed
        // TODO add your handling code here:
        MainPanel.removeAll();
        MainPanel.repaint();
        MainPanel.revalidate();
        MainPanel.add(dataLayanan);
        MainPanel.repaint();
        MainPanel.revalidate();
        setText();

    }//GEN-LAST:event_btnLayananActionPerformed

    private void btnPegawaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPegawaiActionPerformed
        // TODO add your handling code here:
        MainPanel.removeAll();
        MainPanel.repaint();
        MainPanel.revalidate();
        MainPanel.add(dataPegawai);
        MainPanel.repaint();
        MainPanel.revalidate();
        setText();
    }//GEN-LAST:event_btnPegawaiActionPerformed

    private void txtCariNoTlpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCariNoTlpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCariNoTlpActionPerformed

    private void txtNoTelpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNoTelpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNoTelpActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        setText();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void btnSimpanPegawaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanPegawaiActionPerformed
        // TODO add your handling code here:
        String originalPassword ;
        originalPassword = txtPassword.getText();
        String generatedSecuredPasswordHash;
        BCrypt bcrp=new BCrypt();
        boolean matched;
        if (originalPassword == null || "".equals(originalPassword)) {
        	
             txtPassword.requestFocus();
		return;
	}
        generatedSecuredPasswordHash = bcrp.hashpw(originalPassword, BCrypt.gensalt(12));
        matched= bcrp.checkpw(originalPassword, generatedSecuredPasswordHash);
        String password =Boolean.toString(matched) ;
		
        String format = "yyyy-MM-dd";
        SimpleDateFormat fm = new SimpleDateFormat(format);
        String tanggal = String.valueOf(fm.format(txtTanggalLahir.getDate()));
      
        String role ;
        
        if(rdCustomerService.isSelected())
        {
           
            role="Customer Service";
 
        }
        else
        {
          
            role="Kasir";
        }

        if(txtNoTelp.getText().length()<12)
        {
            JOptionPane.showMessageDialog(this, "NO HP kurang 12");
        }
        
        else
            {
            try
            {
    //            P = new  Pegawai (String namaPegawai , Date tglLahir , String role ,int  noHp ,String alamat , String userName , String password )
                cekAngka();
                cekHuruf();
                userNameSama();
                Pegawai peg = new Pegawai();
                        peg.setAlamat(txtAlamat.getText());
                        peg.setNama(txtNamaPegawai.getText());
                        peg.setNoHp(txtNoTelp.getText());
                        peg.setPassword(generatedSecuredPasswordHash);
                        peg.setTglLahir(tanggal);
                        peg.setUserName(txtUserName.getText());
                        peg.setRole(role);
                AC.tambahPegawai(peg);
                setText();

                JOptionPane.showMessageDialog(this,"Data Pegawai Berhasil Ditambahkan");
            }
            catch(dataSama ds)
            {
                JOptionPane.showMessageDialog(this,ds.dataSama());

            }
            catch(CekAngka cd)
            {
                JOptionPane.showMessageDialog(this,cd.dataHanyaHuruf());
            }
            catch(CekHuruf dc)
            {
                JOptionPane.showMessageDialog(this, dc.cekHuruf());
                
            }
        }
       

    }//GEN-LAST:event_btnSimpanPegawaiActionPerformed

    private void txtNamaPegawaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNamaPegawaiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNamaPegawaiActionPerformed

    private void rdKasirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdKasirActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdKasirActionPerformed

    private void rdCustomerServiceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdCustomerServiceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdCustomerServiceActionPerformed

    private void txtCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCariActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCariActionPerformed

    private void txtCariNamaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCariNamaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCariNamaActionPerformed

    private void txtCariRoleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCariRoleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCariRoleActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        // TODO add your handling code here:
        disspegawai(true);
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnTambahLayananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahLayananActionPerformed
        // TODO add your handling code here:
        
        try
        {
            namaLayanan();
            L= new Layanan();
            L.setNamaLayanan(txtNamaLayanan.getText());
            AC.tambahLayanan(L);
            setTextLayanan();
            JOptionPane.showMessageDialog(this, "Layanan Berhasil Ditambahkan");
            tampilLayanan();
        }catch(dataSama e)
        {
           JOptionPane.showMessageDialog(this, e.layananSama());
        }
    }//GEN-LAST:event_btnTambahLayananActionPerformed

    private void txtNamaLayananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNamaLayananActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNamaLayananActionPerformed

    private void txtCariLayananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCariLayananActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCariLayananActionPerformed

    private void btnCariLayananMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCariLayananMouseClicked
        // TODO add your handling code here:
         L=  AC.searchLayanan(txtCariLayanan.getText());
        if(L!=null)
        {
            txtNamaLayananCari.setText(L.getNamaLayanan());
        }
        else
        {
            JOptionPane.showMessageDialog(this,"Layanan Tida Ditemukan .");
        }
    }//GEN-LAST:event_btnCariLayananMouseClicked

    private void btnSimpanEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanEditActionPerformed
        // TODO add your handling code here:
        String originalPassword ;
        originalPassword = txtPassword.getText();
        String generatedSecuredPasswordHash;
        BCrypt bcrp=new BCrypt();
        boolean matched;
        if (originalPassword == null || "".equals(originalPassword)) {
        	
             txtPassword.requestFocus();
		return;
	}
        generatedSecuredPasswordHash = bcrp.hashpw(originalPassword, BCrypt.gensalt(12));
        matched= bcrp.checkpw(originalPassword, generatedSecuredPasswordHash);
        String password =Boolean.toString(matched) ;
		
        try
        {
           
            Pegawai P = new Pegawai();
            P.setAlamat(txtCariAlamat.getText());
            P.setNama(txtCariNama.getText());
            P.setNoHp(txtCariNoTlp.getText());
            P.setPassword(password);
            P.setTglLahir(txtCariTanggalLahir.getText());
            P.setUserName(txtCariUserName.getText());
            P.setRole(txtCariRole.getText());
            AC.editPegawai(P,txtCari.getText());
            
        }catch(Exception  e)
        {
            System.out.println(e);
        }
    }//GEN-LAST:event_btnSimpanEditActionPerformed

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusActionPerformed
        // TODO add your handling code here:
        setText();
       
       
        
      
        
     try
     {
         String cari = txtCari.getText();
         
         if(txtCariNama.getText().equalsIgnoreCase(""))
         {
             JOptionPane.showMessageDialog(this, "Tidak Ada Yang Di Hapus");
         }
         else
         {
            if (JOptionPane.showConfirmDialog(null, "Yakin Hapus?", "Yakin?",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION)
            {
                AC.deletePegawai(cari);
                JOptionPane.showMessageDialog(this, "Data Berhasil Dihapus..");
                
            }  
         }
            
        
            setTextCariPegawai();

        
     }
     catch(Exception e)
     {
         System.out.println(e);
     }
     
        
        
         
    
     
      
    }//GEN-LAST:event_btnHapusActionPerformed

    private void btnTambah1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambah1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnTambah1ActionPerformed

    private void btnHelp2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHelp2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnHelp2ActionPerformed

    private void btnTampil1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTampil1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnTampil1ActionPerformed

    private void btnCari1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCari1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCari1ActionPerformed

    private void btnEditLayananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditLayananActionPerformed
        // TODO add your handling code here:
        dissLayanan(true);
       
    }//GEN-LAST:event_btnEditLayananActionPerformed

    private void btnSimpanEditLayananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanEditLayananActionPerformed
        // TODO add your handling code here:
        
        try
        {
            
        }catch(Exception e )
        {
            System.out.println(e);
        }
    }//GEN-LAST:event_btnSimpanEditLayananActionPerformed

    private void btnHapusLayananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusLayananActionPerformed
        // TODO add your handling code here:
        if(txtCariLayanan.getText().endsWith(" "))
        {
            JOptionPane.showMessageDialog(this,"Tidak Ada Data Yang Dihapus .");
            

        }
        else
        {

            try   
            {
            String cari = txtCariLayanan.getText();
              if(JOptionPane.showConfirmDialog(null, "Yakin Hapus?", "Yakin?",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION)
              {
                  AC.deleteLayanan(cari);
                  setTextLayanan();
              
                  JOptionPane.showMessageDialog(this, "Data Berhasil Dihapus ..");
              } 
              else
              {
                  setTextLayanan();
                  dissLayanan(false);
              }
            }
            catch(Exception e)
            {
                System.out.println(e);
            }
        }
    }//GEN-LAST:event_btnHapusLayananActionPerformed

    private void btnTambahSupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahSupActionPerformed
        // TODO add your handling code here:
        mainPanel4.removeAll();
        mainPanel4.repaint();
        mainPanel4.revalidate();
        mainPanel4.add(TambahSuplier);
        mainPanel4.repaint();
        mainPanel4.revalidate();
        setText();
    }//GEN-LAST:event_btnTambahSupActionPerformed

    private void btnSupHelpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSupHelpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSupHelpActionPerformed

    private void btnTampilSupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTampilSupActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnTampilSupActionPerformed

    private void btnCariSupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariSupActionPerformed
        // TODO add your handling code here:
         mainPanel4.removeAll();
        mainPanel4.repaint();
        mainPanel4.revalidate();
        mainPanel4.add(CariSuplier);
        mainPanel4.repaint();
        mainPanel4.revalidate();
        setText();
        setTextSuplier();
    }//GEN-LAST:event_btnCariSupActionPerformed

    private void txtCariSupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCariSupActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCariSupActionPerformed

    private void btnSearch1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSearch1MouseClicked
        // TODO add your handling code here:
        
        S= AC.searchSup(txtCariSup.getText());
        if(S!=null)
        {
            txtCariNamaSup.setText(S.getNama());
            txtCariAlaSup.setText(S.getAlamat());
            txtCariTelpSup.setText(S.getTelp());
           
        }
        else
        {
            JOptionPane.showMessageDialog(this, "Data Supplier Tida Ada..");
        }   
            
    }//GEN-LAST:event_btnSearch1MouseClicked

    private void txtCariTelpSupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCariTelpSupActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCariTelpSupActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void btnHapusSupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusSupActionPerformed
        // TODO add your handling code here:
        AC.deleteSup(txtCariSup.getText());
        JOptionPane.showMessageDialog(this,"DATA BERHASIL DIHAPUS");
        setTextSuplier();
        setTextDataPencarian();
        tampliSuplier();
    }//GEN-LAST:event_btnHapusSupActionPerformed

    private void btnSimpanSuplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanSuplierActionPerformed
        // TODO add your handling code here:
        try
        {
            S = new Suplier();
            S.setNama(txtNamSup.getText());
            S.setTelp(txtTelpSup.getText());
            S.setAlamat(txtAlamatSup.getText());
            AC.tambahSuplier(S);
            tampliSuplier();
           
            setTextSuplier();
            JOptionPane.showMessageDialog(this, "Berhasil Menambahkan Suplier");
        }catch(Exception e)
        {
            System.out.println("Gagal Tambah");
            System.out.println(e);
        }
    }//GEN-LAST:event_btnSimpanSuplierActionPerformed

    private void txtNamSupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNamSupActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNamSupActionPerformed

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
            java.util.logging.Logger.getLogger(MenuAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MenuAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MenuAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MenuAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
       
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MenuAdmin().setVisible(true);
                
            }
        });
        
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel CariSuplier;
    private javax.swing.JPanel HelpSuplier;
    private javax.swing.JPanel MainPanel;
    private javax.swing.JPanel TambahSuplier;
    private javax.swing.JPanel TampilSuplier;
    private javax.swing.JPanel background;
    private javax.swing.JPanel bantuan;
    private javax.swing.JButton btnCari;
    private javax.swing.JButton btnCari1;
    private javax.swing.JLabel btnCariHewan;
    private javax.swing.JLabel btnCariLayanan;
    private javax.swing.JButton btnCariLyn;
    private javax.swing.JButton btnCariSup;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnEditLayanan;
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnHapusLayanan;
    private javax.swing.JButton btnHapusSup;
    private javax.swing.JButton btnHelp;
    private javax.swing.JButton btnHelp1;
    private javax.swing.JButton btnHelp2;
    private javax.swing.JButton btnHewan;
    private javax.swing.JButton btnLayanan;
    private javax.swing.JButton btnPegawai;
    private javax.swing.JButton btnProduk;
    private javax.swing.ButtonGroup btnRole;
    private javax.swing.JLabel btnSearch;
    private javax.swing.JLabel btnSearch1;
    private javax.swing.JButton btnSimpanEdit;
    private javax.swing.JButton btnSimpanEditLayanan;
    private javax.swing.JButton btnSimpanPegawai;
    private javax.swing.JButton btnSimpanSuplier;
    private javax.swing.JButton btnSup;
    private javax.swing.JButton btnSupHelp;
    private javax.swing.JButton btnTambah;
    private javax.swing.JButton btnTambah1;
    private javax.swing.JToggleButton btnTambahLayanan;
    private javax.swing.JButton btnTambahLyn;
    private javax.swing.JButton btnTambahSup;
    private javax.swing.JButton btnTampil;
    private javax.swing.JButton btnTampil1;
    private javax.swing.JButton btnTampilLyn;
    private javax.swing.JButton btnTampilSup;
    private javax.swing.JPanel cariLayanan;
    private javax.swing.JPanel cariPegawai;
    private javax.swing.JPanel dataHewan;
    private javax.swing.JPanel dataLayanan;
    private javax.swing.JPanel dataPegawai;
    private javax.swing.JPanel dataProduk;
    private javax.swing.JPanel dataSupplier;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
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
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JLabel judul;
    private javax.swing.JPanel mainPanel2;
    private javax.swing.JPanel mainPanel3;
    private javax.swing.JPanel mainPanel4;
    private javax.swing.JPanel menuHome;
    private javax.swing.JRadioButton rdCustomerService;
    private javax.swing.JRadioButton rdKasir;
    private javax.swing.JTable tabelLayanan;
    private javax.swing.JTable tablePegawai;
    private javax.swing.JTable tableSupTambahData;
    private javax.swing.JPanel tambahLayanan;
    private javax.swing.JPanel tambahPegawai;
    private javax.swing.JPanel tampilLayanan;
    private javax.swing.JPanel tampilSeluruh;
    private javax.swing.JTextField txtAlamat;
    private javax.swing.JTextField txtAlamatSup;
    private javax.swing.JTextField txtCari;
    private javax.swing.JTextField txtCariAlaSup;
    private javax.swing.JTextField txtCariAlamat;
    private javax.swing.JTextField txtCariLayanan;
    private javax.swing.JTextField txtCariNama;
    private javax.swing.JTextField txtCariNamaSup;
    private javax.swing.JTextField txtCariNoTlp;
    private javax.swing.JTextField txtCariPassword;
    private javax.swing.JTextField txtCariRole;
    private javax.swing.JTextField txtCariSup;
    private javax.swing.JTextField txtCariTanggalLahir;
    private javax.swing.JTextField txtCariTelpSup;
    private javax.swing.JTextField txtCariUserName;
    private javax.swing.JTextField txtNamSup;
    private javax.swing.JTextField txtNamaLayanan;
    private javax.swing.JTextField txtNamaLayananCari;
    private javax.swing.JTextField txtNamaPegawai;
    private javax.swing.JTextField txtNoTelp;
    private javax.swing.JTextField txtPassword;
    private com.toedter.calendar.JDateChooser txtTanggalLahir;
    private javax.swing.JTextField txtTelpSup;
    private javax.swing.JTextField txtUserName;
    // End of variables declaration//GEN-END:variables
}
