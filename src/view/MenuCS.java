/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package view;
import Controller.AdminControl;
import Controller.CSControl ;
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
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.table. DefaultTableModel;
import javax.swing.table.TableModel;


/**
 *
 * @author ACER
 */
public class MenuCS extends javax.swing.JFrame {

    public Pelanggan P ;
    public  DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MMMM/dd ");  
    public  LocalDateTime now = LocalDateTime.now();
    CSControl CS ;
    public ArrayList namaHewan = new  ArrayList();
    public ArrayList nama= new ArrayList();
    public AdminControl AC = new AdminControl();
    public CsDAO CA = new CsDAO();
    public AdminDao AD = new AdminDao();
    
    public  JenisHewan jh ;
    public dataHewan Dh ;
    /**
     * Creates new form MenuCS
     */
        DefaultTableModel tabelModel , tabelModel2 ;
    public MenuCS() {
        
      
        initComponents();
        
        txtTanggalHariIni.setText(dtf.format(now));
        txtUserKasir.setText(LoginSession.getNama());
        P= new Pelanggan();
        AC= new AdminControl();
        CS = new CSControl();
        tabelModel2 =(DefaultTableModel) tabelHewan.getModel();
        tabelModel = (DefaultTableModel) tabelPelanggan.getModel();
     
        PlaceHolder holder0 = new PlaceHolder(txtNamaPelanggan,"Masukan Nama Pelanggan");
        PlaceHolder holder3 = new PlaceHolder(cariPelanggan, "Masukan Nama Pelanggan");
        PlaceHolder holder1 = new PlaceHolder(txtAlamatPelanggan, "Masukan Alamat Pelanggan");
        PlaceHolder holder2 = new PlaceHolder(txtTelpPelanggan, "Masukan No Telphone");
        PlaceHolder holder4 = new PlaceHolder(cariNamaHewan,"Masukan Nama Hewan");
        jComboBoxidPelanggan();
        tampilNamaPelanggan();
        tampilJenisHewan();
        tampilPelanggan();
        tampilHewan();
        tampilTextNamaPelanggan();
        tampilTextNamaHewan();
    }
    public void tampilTextNamaHewan()
    {
        AD.makeConnection();
        CA.makeConnection();
        
        try 
        {
            String sql="Select nama from Hewan where aktif =1";
            Class.forName("com.mysql.jdbc.Driver");
            Statement stm = AD.GETcon().createStatement();
            ResultSet rs= stm.executeQuery(sql);
            while (rs.next())
                    {
                        String Nama = rs.getString("nama");
                        namaHewan.add(Nama);
                       
                    }
            rs.close();
            stm.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        
    }
    public void tampilTextNamaPelanggan()
    {
        AD.makeConnection();
        try
        {
            
            String sql = "Select nama from Pelanggan where aktif = 1 ";
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
    public void autoCompletNamaHewan(String nama)
    {
        String complet = " ";
        int start = nama.length();
        int last = nama.length();
        int a ;
        for(a=0 ; a<namaHewan.size(); a++)
        {
            if(namaHewan.get(a).toString().startsWith(nama))
            {
                complet= namaHewan.get(a).toString();
                last = complet.length() ;
                break ;
            }
        }
        if(last>start)
        {
            cariNamaHewan.setText(complet);
            cariNamaHewan.setCaretPosition(last);
            cariNamaHewan.moveCaretPosition(start);
        }
    }
    public void autoCompletNamaPelanggan(String Nama)
    {
        String compelet = "";
        int start = Nama.length();
        int last = Nama.length();
        int a ;
        for(a= 0; a<nama.size(); a++)
        {
            if (nama.get(a).toString().startsWith(Nama)) {
                
                compelet = nama.get(a).toString();
                last = compelet.length() ;
                break ;
            }
        }
        
        if (last > start ) {
            
            cariPelanggan.setText(compelet);
            cariPelanggan.setCaretPosition(last);
            cariPelanggan.moveCaretPosition(start);
        }
    }
   public void addTablePelanggan(Pelanggan P)
   {
       Vector data = new Vector();
       data.add(P.getId_pelanggan());
       data.add(P.getNama());
       data.add(P.getAlamat());
              data.add(P.getTglLahir());


       data.add(P.getTelp());
       data.add(P.getCreated_by());
       data.add(P.getCreated_At());
       tabelModel.addRow(data);
   }
   public void tampilHewan()
    {
        int a =tabelModel2.getRowCount();
        for (int i = 0; i < a; i++) {
            tabelModel2.removeRow(0);
            
        }
        try
        {
        CA.makeConnection();
        AD.makeConnection();
            String sql ="Select A.nama as jenis_hewan , b.nama as nama_Pelanggan, C.id_Hewan as id,C.CREATED_BY ,C.created_At,C.nama as namaHewan, C.tanggal_lahir AS tgl_lahir from hewan as C INNER JOIN jenis_hewan AS A on C.ID_JENIS_HEWAN = A.ID_JENIS_HEWAN INNER JOIN pelanggan as B on B.ID_PELANGGAN = C.ID_PELANGGAN WHERE C.AKTIF = 1";

            Statement stm= con.createStatement();
            ResultSet rs= stm.executeQuery(sql);

        
        if (rs!=null) {
            
            while(rs.next())
            {
         
                String id = rs.getString("id");
                String jenisHewan = rs.getString("jenis_hewan");
                String namaPelanggan = rs.getString("nama_pelanggan");
                String tgl_lahir = rs.getString("tgl_lahir");
                String namaH = rs.getString("namaHewan");
                String createBy = rs.getString("C.CREATED_BY");
                String created_At = rs.getString("C.created_at");
                String idx = String.valueOf(id);
                String[] dataField={id,jenisHewan,namaPelanggan,namaH,tgl_lahir,createBy, created_At};
                tabelModel2.addRow(dataField);
                            
            }
            rs.close();
            stm.close();
        }
        

     CA.closeConnection();

    }
   
    catch(Exception e)
    {
        System.out.println("Gagal ");
        System.out.println(e);
    }
    
    
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
            P.setCreated_by(M.get(i).getCreated_by());
            P.setCreated_At(M.get(i).getCreated_At());
            addTablePelanggan(P);
        }
   }
    
   public void tampilJenisHewan()
   {
       CA.makeConnection(); 
 
       try
       {
           
           String sql = "Select nama from jenis_hewan where aktif = 1 ";
           Class.forName("com.mysql.jdbc.Driver");
           Statement stm = CA.GETcon().createStatement() ;
           ResultSet rs =  stm.executeQuery(sql);
           while (rs.next())
           {
               jComboBoxJenisHewan.addItem(rs.getString("nama"));
           }
        
       }
       
       catch(Exception e)
       {
           System.out.println(e);
           System.out.println("Gagal Tampil");
           
       }
       CA.closeConnection();
   }
   public void tampilNamaPelanggan()
   {
       CA.makeConnection(); 
       try
       {
         String sql = "Select nama from pelanggan where aktif = 1 ";
         Class.forName("com.mysql.jdbc.Driver");
         Statement st = CA.GETcon().createStatement();
         ResultSet rs = st.executeQuery(sql);
         while(rs.next())
         {
             jComboBoxNamaPelanggan.addItem(rs.getString("nama"));
         }
    
         
       }catch(Exception e)
       {
           System.out.println(e);
       }
       CA.closeConnection();
   }
    public void jComboBoxidPelanggan()
    {
        CA.makeConnection();
       
        try
        {
            String sql = "Select NAMA from Pelanggan where aktif = 1 ";
            Class.forName("com.mysql.jdbc.Driver"); 
            Statement st = CA.GETcon().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
           

            }

            
        }
        catch(Exception e)
        {
            System.out.println("Gagal Menampilkan Pelanggan");
            System.out.println(e);
        }
        CA.closeConnection();
            
        
    }
    
    public dataHewan cariNamaHewan(String nama)
    {
         dataHewan dh = null ;

        try {
                
            AD.makeConnection();
            CA.makeConnection();
            String sql ="Select A.nama as jenis_hewan, b.nama as Nama_Pelanggan,C.tanggal_lahir as tgl_Lahir,C.nama as NAMA,C.ID_HEWAN from hewan as C inner join jenis_hewan as A on C.ID_JENIS_HEWAN=A.ID_JENIS_HEWAN inner join pelanggan AS b on C.ID_PELANGGAN =b.ID_PELANGGAN where C.aktif=1 AND A.aktif=1 AND B.AKTIF=1 And C.NAMA='"+nama+"'"; 
            System.out.println("Tampil Harga Layanan");
           
            Statement stm = con.createStatement() ;
            ResultSet rs = stm.executeQuery(sql);
        if(rs!=null)
        {
            while(rs.next())
            {

              
               dh = new dataHewan();
               jenisHewan.setText(rs.getString("jenis_hewan"));
               namaPelanggan.setText(rs.getString("Nama_Pelanggan"));
               namaHewan1.setText(rs.getString("NAMA"));
               tglLahir.setText(rs.getString("tgl_Lahir"));
            }
        }
        else
        {
            JOptionPane.showMessageDialog(this, "Data ID Tidak Ditemukan");
        }
        rs.close();
        stm.close();
        }
        catch (Exception e)
        {
            System.out.println(e);
            System.out.println(" Gagal ");
        }
    return dh;
            
      
            
       

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
        btnPegawai1 = new javax.swing.JButton();
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
        jLabel60 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tabelPelanggan = new javax.swing.JTable();
        cariPegawai = new javax.swing.JPanel();
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
        cariPelanggan = new javax.swing.JTextField();
        btnSearchPelanggan = new javax.swing.JLabel();
        bantuan = new javax.swing.JPanel();
        tampilSeluruh = new javax.swing.JPanel();
        dataHewan = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        btnTambah1 = new javax.swing.JButton();
        btnHelp1 = new javax.swing.JButton();
        btnTampil1 = new javax.swing.JButton();
        btnCari1 = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        mainPanel3 = new javax.swing.JPanel();
        tambahPelanggan1 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        btnSimpanPelanggan1 = new javax.swing.JButton();
        btlSimpan1 = new javax.swing.JButton();
        jLabel62 = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        txtNamaHewan = new javax.swing.JTextField();
        txtTanggalLahirHewan = new com.toedter.calendar.JDateChooser();
        jLabel66 = new javax.swing.JLabel();
        jComboBoxJenisHewan = new javax.swing.JComboBox<>();
        jComboBoxNamaPelanggan = new javax.swing.JComboBox<>();
        jScrollPane5 = new javax.swing.JScrollPane();
        tabelHewan = new javax.swing.JTable();
        cariPegawai1 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        btnSimpanProduk3 = new javax.swing.JButton();
        btlHapusSup1 = new javax.swing.JButton();
        namaPelanggan = new javax.swing.JTextField();
        jenisHewan = new javax.swing.JTextField();
        jLabel74 = new javax.swing.JLabel();
        jLabel75 = new javax.swing.JLabel();
        jLabel76 = new javax.swing.JLabel();
        tglLahir = new javax.swing.JTextField();
        jLabel77 = new javax.swing.JLabel();
        cariNamaHewan = new javax.swing.JTextField();
        btnSearchPelanggan1 = new javax.swing.JLabel();
        namaHewan1 = new javax.swing.JTextField();
        bantuan1 = new javax.swing.JPanel();
        tampilSeluruh1 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setIconImage(
            new javax.swing.ImageIcon(getClass().getResource("/icon/dogg.png")).getImage());

        background.setBackground(new java.awt.Color(255, 255, 255));
        background.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        menuHome.setBackground(new java.awt.Color(255, 139, 36));
        menuHome.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        judul.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        judul.setForeground(new java.awt.Color(0, 0, 0));
        judul.setText("MENU CS");

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
        jLabel24.setText("HAI      :");

        txtUserKasir.setFont(new java.awt.Font("Times New Roman", 3, 12)); // NOI18N
        txtUserKasir.setForeground(new java.awt.Color(0, 0, 0));
        txtUserKasir.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        btnPegawai1.setFont(new java.awt.Font("Serif", 1, 12)); // NOI18N
        btnPegawai1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icon.png"))); // NOI18N
        btnPegawai1.setText("Tambah Hewan");
        btnPegawai1.setHideActionText(true);
        btnPegawai1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnPegawai1.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnPegawai1.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btnPegawai1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPegawai1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout menuHomeLayout = new javax.swing.GroupLayout(menuHome);
        menuHome.setLayout(menuHomeLayout);
        menuHomeLayout.setHorizontalGroup(
            menuHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuHomeLayout.createSequentialGroup()
                .addGroup(menuHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, menuHomeLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(judul, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(menuHomeLayout.createSequentialGroup()
                        .addGroup(menuHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(menuHomeLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(btnLog, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(menuHomeLayout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addGroup(menuHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(menuHomeLayout.createSequentialGroup()
                                        .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtUserKasir, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(menuHomeLayout.createSequentialGroup()
                                        .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtTanggalHariIni, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(menuHomeLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(menuHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnPegawai1)
                    .addComponent(btnPegawai))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        menuHomeLayout.setVerticalGroup(
            menuHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuHomeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(menuHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel24, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtUserKasir, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(menuHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTanggalHariIni, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(menuHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(menuHomeLayout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(judul)))
                .addGap(33, 33, 33)
                .addComponent(btnPegawai)
                .addGap(18, 18, 18)
                .addComponent(btnPegawai1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnLog)
                .addContainerGap())
        );

        MainPanel.setBackground(new java.awt.Color(168, 238, 244));
        MainPanel.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createEtchedBorder(), javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED)));
        MainPanel.setLayout(new java.awt.CardLayout());

        dataPelanggan.setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 139, 36));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btnTambah.setBackground(new java.awt.Color(51, 255, 255));
        btnTambah.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnTambah.setForeground(new java.awt.Color(0, 0, 0));
        btnTambah.setText("Tambah Pelanggan");
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
        btnCari.setText("Cari Pelanggan");
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTambah)
                    .addComponent(btnCari)
                    .addComponent(btnHelp)
                    .addComponent(btnTampil))
                .addContainerGap())
        );

        mainPanel2.setLayout(new java.awt.CardLayout());

        tambahPelanggan.setBackground(new java.awt.Color(255, 255, 255));
        tambahPelanggan.setForeground(new java.awt.Color(0, 0, 0));

        jPanel9.setBackground(new java.awt.Color(255, 244, 203));
        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11))); // NOI18N

        btnSimpanPelanggan.setText("SIMPAN");
        btnSimpanPelanggan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanPelangganActionPerformed(evt);
            }
        });

        btlSimpan.setText("BATAL");
        btlSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btlSimpanActionPerformed(evt);
            }
        });

        txtAlamatPelanggan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtAlamatPelangganKeyPressed(evt);
            }
        });

        txtNamaPelanggan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNamaPelangganActionPerformed(evt);
            }
        });
        txtNamaPelanggan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNamaPelangganKeyPressed(evt);
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

        jLabel60.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        jLabel60.setForeground(new java.awt.Color(0, 0, 0));
        jLabel60.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/pelanggan.png"))); // NOI18N
        jLabel60.setText("TAMBAH PELANGGAN");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(btlSimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSimpanPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel60, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 368, Short.MAX_VALUE)
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
                                        .addComponent(txtTanggalLahirPelanggan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                        .addGap(35, 35, 35))))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel60)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
                .addGap(69, 69, 69)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSimpanPelanggan)
                    .addComponent(btlSimpan))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tabelPelanggan.setBackground(new java.awt.Color(255, 255, 255));
        tabelPelanggan.setFont(new java.awt.Font("Geometr212 BkCn BT", 1, 12)); // NOI18N
        tabelPelanggan.setForeground(new java.awt.Color(0, 0, 0));
        tabelPelanggan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ID_Pelanggan", "Nama ", "Alamat", "Tanggal Lahir", "Telp", "Create By", "Create At"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelPelanggan.setGridColor(new java.awt.Color(0, 0, 0));
        jScrollPane4.setViewportView(tabelPelanggan);
        if (tabelPelanggan.getColumnModel().getColumnCount() > 0) {
            tabelPelanggan.getColumnModel().getColumn(5).setHeaderValue("Create By");
            tabelPelanggan.getColumnModel().getColumn(6).setResizable(false);
            tabelPelanggan.getColumnModel().getColumn(6).setHeaderValue("Create At");
        }

        javax.swing.GroupLayout tambahPelangganLayout = new javax.swing.GroupLayout(tambahPelanggan);
        tambahPelanggan.setLayout(tambahPelangganLayout);
        tambahPelangganLayout.setHorizontalGroup(
            tambahPelangganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tambahPelangganLayout.createSequentialGroup()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 709, Short.MAX_VALUE))
        );
        tambahPelangganLayout.setVerticalGroup(
            tambahPelangganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 580, Short.MAX_VALUE)
        );

        mainPanel2.add(tambahPelanggan, "card2");

        cariPegawai.setBackground(new java.awt.Color(255, 244, 203));

        jPanel12.setBackground(new java.awt.Color(255, 244, 203));
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

        cariPelanggan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cariPelangganActionPerformed(evt);
            }
        });
        cariPelanggan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cariPelangganKeyPressed(evt);
            }
        });

        btnSearchPelanggan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/search-icon.png"))); // NOI18N
        btnSearchPelanggan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSearchPelangganMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnSearchPelanggan)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(btlHapusSup, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnSimpanProduk2, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel12Layout.createSequentialGroup()
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
                                    .addComponent(txtCariNamaPelanggan, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(cariPelanggan))
                        .addGap(35, 35, 35))))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(btnSearchPelanggan)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(cariPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 157, Short.MAX_VALUE)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btlHapusSup, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSimpanProduk2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        javax.swing.GroupLayout cariPegawaiLayout = new javax.swing.GroupLayout(cariPegawai);
        cariPegawai.setLayout(cariPegawaiLayout);
        cariPegawaiLayout.setHorizontalGroup(
            cariPegawaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cariPegawaiLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(664, Short.MAX_VALUE))
        );
        cariPegawaiLayout.setVerticalGroup(
            cariPegawaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cariPegawaiLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(93, Short.MAX_VALUE))
        );

        mainPanel2.add(cariPegawai, "card3");

        bantuan.setBackground(new java.awt.Color(99, 175, 241));

        javax.swing.GroupLayout bantuanLayout = new javax.swing.GroupLayout(bantuan);
        bantuan.setLayout(bantuanLayout);
        bantuanLayout.setHorizontalGroup(
            bantuanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1144, Short.MAX_VALUE)
        );
        bantuanLayout.setVerticalGroup(
            bantuanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 580, Short.MAX_VALUE)
        );

        mainPanel2.add(bantuan, "card5");

        tampilSeluruh.setBackground(new java.awt.Color(99, 175, 241));

        javax.swing.GroupLayout tampilSeluruhLayout = new javax.swing.GroupLayout(tampilSeluruh);
        tampilSeluruh.setLayout(tampilSeluruhLayout);
        tampilSeluruhLayout.setHorizontalGroup(
            tampilSeluruhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1144, Short.MAX_VALUE)
        );
        tampilSeluruhLayout.setVerticalGroup(
            tampilSeluruhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 580, Short.MAX_VALUE)
        );

        mainPanel2.add(tampilSeluruh, "card4");

        javax.swing.GroupLayout dataPelangganLayout = new javax.swing.GroupLayout(dataPelanggan);
        dataPelanggan.setLayout(dataPelangganLayout);
        dataPelangganLayout.setHorizontalGroup(
            dataPelangganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dataPelangganLayout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(dataPelangganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(mainPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

        dataHewan.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 139, 36));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btnTambah1.setBackground(new java.awt.Color(51, 255, 255));
        btnTambah1.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnTambah1.setForeground(new java.awt.Color(0, 0, 0));
        btnTambah1.setText("Tambah Hewan");
        btnTambah1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTambah1ActionPerformed(evt);
            }
        });

        btnHelp1.setBackground(new java.awt.Color(51, 255, 255));
        btnHelp1.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnHelp1.setForeground(new java.awt.Color(0, 0, 0));
        btnHelp1.setText("Help");
        btnHelp1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHelp1ActionPerformed(evt);
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
        btnCari1.setText("Cari Hewan");
        btnCari1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCari1ActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Bodoni MT Condensed", 0, 24)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("MENGELOLA HEWAN KOEVEE PET SHOP");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnTambah1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnCari1, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnTampil1)
                        .addGap(49, 49, 49)
                        .addComponent(btnHelp1))
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 484, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTambah1)
                    .addComponent(btnCari1)
                    .addComponent(btnHelp1)
                    .addComponent(btnTampil1))
                .addContainerGap())
        );

        mainPanel3.setLayout(new java.awt.CardLayout());

        tambahPelanggan1.setBackground(new java.awt.Color(255, 255, 255));
        tambahPelanggan1.setForeground(new java.awt.Color(0, 0, 0));

        jPanel10.setBackground(new java.awt.Color(255, 244, 203));
        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11))); // NOI18N

        btnSimpanPelanggan1.setText("SIMPAN");
        btnSimpanPelanggan1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanPelanggan1ActionPerformed(evt);
            }
        });

        btlSimpan1.setText("BATAL");
        btlSimpan1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btlSimpan1ActionPerformed(evt);
            }
        });

        jLabel62.setFont(new java.awt.Font("Clarendon BT", 0, 14)); // NOI18N
        jLabel62.setForeground(new java.awt.Color(0, 0, 0));
        jLabel62.setText("Jenis Hewan");

        jLabel63.setFont(new java.awt.Font("Clarendon BT", 0, 14)); // NOI18N
        jLabel63.setForeground(new java.awt.Color(0, 0, 0));
        jLabel63.setText("Nama Pelanggan");

        jLabel64.setFont(new java.awt.Font("Clarendon BT", 0, 14)); // NOI18N
        jLabel64.setForeground(new java.awt.Color(0, 0, 0));
        jLabel64.setText("Tanggal Lahir");

        jLabel65.setFont(new java.awt.Font("Clarendon BT", 0, 14)); // NOI18N
        jLabel65.setForeground(new java.awt.Color(0, 0, 0));
        jLabel65.setText("Nama Hewan");

        txtNamaHewan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNamaHewanActionPerformed(evt);
            }
        });

        txtTanggalLahirHewan.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        txtTanggalLahirHewan.setDateFormatString("yyyy-MMMMMM-d");

        jLabel66.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        jLabel66.setForeground(new java.awt.Color(0, 0, 0));
        jLabel66.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/pelanggan.png"))); // NOI18N
        jLabel66.setText("Tambah Hewan");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(btlSimpan1, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSimpanPelanggan1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel66, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 368, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel10Layout.createSequentialGroup()
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel62, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel63, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel65, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel64))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtTanggalLahirHewan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jComboBoxNamaPelanggan, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jComboBoxJenisHewan, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtNamaHewan))))
                        .addGap(35, 35, 35))))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel66)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel62, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                    .addComponent(jComboBoxJenisHewan))
                .addGap(18, 18, 18)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxNamaPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel63, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel65, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNamaHewan, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtTanggalLahirHewan, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel64, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(49, 49, 49)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSimpanPelanggan1)
                    .addComponent(btlSimpan1))
                .addContainerGap(235, Short.MAX_VALUE))
        );

        tabelHewan.setBackground(new java.awt.Color(255, 255, 255));
        tabelHewan.setFont(new java.awt.Font("Geometr212 BkCn BT", 1, 12)); // NOI18N
        tabelHewan.setForeground(new java.awt.Color(0, 0, 0));
        tabelHewan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Jenis Hewan", "Nama Pelanggan", "Nama", "Tgl_Lahir", "Created_By", "Created_at"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelHewan.setGridColor(new java.awt.Color(0, 0, 0));
        jScrollPane5.setViewportView(tabelHewan);

        javax.swing.GroupLayout tambahPelanggan1Layout = new javax.swing.GroupLayout(tambahPelanggan1);
        tambahPelanggan1.setLayout(tambahPelanggan1Layout);
        tambahPelanggan1Layout.setHorizontalGroup(
            tambahPelanggan1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tambahPelanggan1Layout.createSequentialGroup()
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 712, Short.MAX_VALUE))
        );
        tambahPelanggan1Layout.setVerticalGroup(
            tambahPelanggan1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane5)
        );

        mainPanel3.add(tambahPelanggan1, "card2");

        cariPegawai1.setBackground(new java.awt.Color(255, 244, 203));

        jPanel13.setBackground(new java.awt.Color(255, 244, 203));
        jPanel13.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11))); // NOI18N

        btnSimpanProduk3.setText("SIMPAN");
        btnSimpanProduk3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanProduk3ActionPerformed(evt);
            }
        });

        btlHapusSup1.setText("HAPUS");
        btlHapusSup1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btlHapusSup1ActionPerformed(evt);
            }
        });

        jenisHewan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jenisHewanActionPerformed(evt);
            }
        });

        jLabel74.setFont(new java.awt.Font("Clarendon BT", 0, 18)); // NOI18N
        jLabel74.setForeground(new java.awt.Color(0, 0, 0));
        jLabel74.setText("Jenis Hewan");

        jLabel75.setFont(new java.awt.Font("Clarendon BT", 0, 18)); // NOI18N
        jLabel75.setForeground(new java.awt.Color(0, 0, 0));
        jLabel75.setText("Nama_Pelanggan");

        jLabel76.setFont(new java.awt.Font("Clarendon BT", 0, 18)); // NOI18N
        jLabel76.setForeground(new java.awt.Color(0, 0, 0));
        jLabel76.setText("Nama Hewan");

        tglLahir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tglLahirActionPerformed(evt);
            }
        });

        jLabel77.setFont(new java.awt.Font("Clarendon BT", 0, 18)); // NOI18N
        jLabel77.setForeground(new java.awt.Color(0, 0, 0));
        jLabel77.setText("Tanggal Lahir");

        cariNamaHewan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cariNamaHewanActionPerformed(evt);
            }
        });
        cariNamaHewan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cariNamaHewanKeyPressed(evt);
            }
        });

        btnSearchPelanggan1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/search-icon.png"))); // NOI18N
        btnSearchPelanggan1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSearchPelanggan1MouseClicked(evt);
            }
        });
        btnSearchPelanggan1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnSearchPelanggan1KeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addComponent(btnSearchPelanggan1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel74, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel75, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel76, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel77, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jenisHewan)
                            .addComponent(namaPelanggan)
                            .addComponent(tglLahir)
                            .addComponent(namaHewan1, javax.swing.GroupLayout.DEFAULT_SIZE, 244, Short.MAX_VALUE)))
                    .addComponent(cariNamaHewan, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(btlHapusSup1, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnSimpanProduk3, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 213, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnSearchPelanggan1, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                    .addComponent(cariNamaHewan))
                .addGap(32, 32, 32)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jenisHewan, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel74, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel75, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(namaPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel76, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(namaHewan1, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tglLahir, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel77, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 158, Short.MAX_VALUE)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btlHapusSup1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSimpanProduk3, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout cariPegawai1Layout = new javax.swing.GroupLayout(cariPegawai1);
        cariPegawai1.setLayout(cariPegawai1Layout);
        cariPegawai1Layout.setHorizontalGroup(
            cariPegawai1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cariPegawai1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(664, Short.MAX_VALUE))
        );
        cariPegawai1Layout.setVerticalGroup(
            cariPegawai1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cariPegawai1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(116, Short.MAX_VALUE))
        );

        mainPanel3.add(cariPegawai1, "card3");

        bantuan1.setBackground(new java.awt.Color(99, 175, 241));

        javax.swing.GroupLayout bantuan1Layout = new javax.swing.GroupLayout(bantuan1);
        bantuan1.setLayout(bantuan1Layout);
        bantuan1Layout.setHorizontalGroup(
            bantuan1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1147, Short.MAX_VALUE)
        );
        bantuan1Layout.setVerticalGroup(
            bantuan1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 580, Short.MAX_VALUE)
        );

        mainPanel3.add(bantuan1, "card5");

        tampilSeluruh1.setBackground(new java.awt.Color(99, 175, 241));

        javax.swing.GroupLayout tampilSeluruh1Layout = new javax.swing.GroupLayout(tampilSeluruh1);
        tampilSeluruh1.setLayout(tampilSeluruh1Layout);
        tampilSeluruh1Layout.setHorizontalGroup(
            tampilSeluruh1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1147, Short.MAX_VALUE)
        );
        tampilSeluruh1Layout.setVerticalGroup(
            tampilSeluruh1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 580, Short.MAX_VALUE)
        );

        mainPanel3.add(tampilSeluruh1, "card4");

        javax.swing.GroupLayout dataHewanLayout = new javax.swing.GroupLayout(dataHewan);
        dataHewan.setLayout(dataHewanLayout);
        dataHewanLayout.setHorizontalGroup(
            dataHewanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dataHewanLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(dataHewanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(mainPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );
        dataHewanLayout.setVerticalGroup(
            dataHewanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dataHewanLayout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(mainPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        MainPanel.add(dataHewan, "card6");

        javax.swing.GroupLayout backgroundLayout = new javax.swing.GroupLayout(background);
        background.setLayout(backgroundLayout);
        backgroundLayout.setHorizontalGroup(
            backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backgroundLayout.createSequentialGroup()
                .addComponent(menuHome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(MainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        backgroundLayout.setVerticalGroup(
            backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menuHome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(MainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 739, Short.MAX_VALUE)
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
          String cari = cariPelanggan.getText();
        
            P= CS.cariPelanggan(cari);
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
        String cari = cariPelanggan.getText();
        
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
                    CS.hapusPelanggan(cari);

                    JOptionPane.showMessageDialog(this, "Data Berhasil Dihapus..");

                   
                    setTextPelanggan();
                    tampilPelanggan();
                    tampilTextNamaPelanggan();
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
        Pelanggan P = new  Pelanggan();
        try
        {
                 
            if(txtCariNamaPelanggan.getText().length()<1)
            {
                JOptionPane.showMessageDialog(this, "Tidak Ada Data Yang Diedit");
            }
            else
                
            {
                                 P.setNama(txtCariNamaPelanggan.getText());
                 P.setAlamat(txtCariAlaPelanggan.getText());
                 P.setTelp(txtCariTelpPel.getText());
                 P.setTglLahir(txtCariTglLahir.getText());
                 
                 
                 CS.editPelanggan(P, cariPelanggan.getText());
                 JOptionPane.showMessageDialog(this,"Data Berhasil Diedit");
                                  autoCompletNamaPelanggan(txtCariNamaPelanggan.getText());
                 setTextPelanggan();
                 tampilPelanggan();
                 tampilTextNamaPelanggan();

            }

        }
        catch (Exception e)
        {
            System.out.println(e);
        }
   
        
    }//GEN-LAST:event_btnSimpanProduk2ActionPerformed

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
            tampilNamaPelanggan();
            tampilTextNamaPelanggan();
            autoCompletNamaPelanggan(txtNamaPelanggan.getText());
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

    private void txtNamaPelangganKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNamaPelangganKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtAlamatPelanggan.requestFocus();
        }
    }//GEN-LAST:event_txtNamaPelangganKeyPressed

    private void txtAlamatPelangganKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAlamatPelangganKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtTanggalLahirPelanggan.requestFocus();
        }
    }//GEN-LAST:event_txtAlamatPelangganKeyPressed

    private void btlSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btlSimpanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btlSimpanActionPerformed

    private void btnPegawai1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPegawai1ActionPerformed
        // TODO add your handling code here:
        MainPanel.removeAll();
        MainPanel.repaint();
        MainPanel.revalidate();
        MainPanel.add(dataHewan);
        MainPanel.repaint();
        MainPanel.revalidate();
    }//GEN-LAST:event_btnPegawai1ActionPerformed

    private void btnTambah1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambah1ActionPerformed
        // TODO add your handling code here:
         mainPanel3.removeAll();
        mainPanel3.repaint();
        mainPanel3.revalidate();
        mainPanel3.add(tambahPelanggan1);
        mainPanel3.repaint();
        mainPanel3.revalidate();
    }//GEN-LAST:event_btnTambah1ActionPerformed

    private void btnHelp1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHelp1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnHelp1ActionPerformed

    private void btnTampil1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTampil1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnTampil1ActionPerformed

    private void btnCari1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCari1ActionPerformed
        // TODO add your handling code here:
        mainPanel3.removeAll();
        mainPanel3.repaint();
        mainPanel3.revalidate();
        mainPanel3.add(cariPegawai1);
        mainPanel3.repaint();
        mainPanel3.revalidate();

    }//GEN-LAST:event_btnCari1ActionPerformed

    private void btnSimpanPelanggan1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanPelanggan1ActionPerformed
        // TODO add your handling code here:
        String jenisHewan = (String)jComboBoxJenisHewan.getSelectedItem();
        String nama= (String) jComboBoxNamaPelanggan.getSelectedItem();
        String format = "yyyy-MM-dd";
        SimpleDateFormat fm = new SimpleDateFormat(format);
        String tanggal = String.valueOf(fm.format(txtTanggalLahirHewan.getDate()));

           P= CS.cariPakaiNama(nama);
           jh= AC.searchJenisHewan(jenisHewan);
       if(P!=null && jh!=null )
       {
        dataHewan Dh = new dataHewan();
            int idJenisHewan = jh.getId();
           int idPelanggan = P.getId_pelanggan();
           
           Dh.setIdPelanggan(idPelanggan);
           Dh.setIDJenisHewan(idJenisHewan);
           Dh.setTanggaLahir(tanggal);
           Dh.setNamaHewan(txtNamaHewan.getText());
           CS.tambahHewan(Dh);
           tampilHewan();
       }

    }//GEN-LAST:event_btnSimpanPelanggan1ActionPerformed

    private void btlSimpan1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btlSimpan1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btlSimpan1ActionPerformed

    private void txtNamaHewanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNamaHewanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNamaHewanActionPerformed

    private void btnSimpanProduk3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanProduk3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSimpanProduk3ActionPerformed

    private void btlHapusSup1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btlHapusSup1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btlHapusSup1ActionPerformed

    private void jenisHewanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jenisHewanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jenisHewanActionPerformed

    private void tglLahirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tglLahirActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tglLahirActionPerformed

    private void btnSearchPelanggan1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSearchPelanggan1MouseClicked
        // TODO add your handling code here:
        
        cariNamaHewan( cariNamaHewan.getText());
    }//GEN-LAST:event_btnSearchPelanggan1MouseClicked

    private void cariPelangganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cariPelangganActionPerformed
        // TODO add your handling code here:
         
     
    }//GEN-LAST:event_cariPelangganActionPerformed

    private void cariPelangganKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cariPelangganKeyPressed
        // TODO add your handling code here:
        switch(evt.getKeyCode())
        {
            case KeyEvent.VK_BACK_SPACE:
                if (evt.getKeyCode()==KeyEvent.VK_BACK_SPACE) {
                    
                        setTextPelanggan();
                }
                  
                break ;
            case KeyEvent.VK_ENTER:
                cariPelanggan.setText(cariPelanggan.getText());
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String cari = cariPelanggan.getText();
        
            P= CS.cariPelanggan(cari);
            if(P!=null)
            {
                txtCariNamaPelanggan.setText(P.getNama());
                txtCariAlaPelanggan.setText(P.getAlamat());
                txtCariTelpPel.setText(P.getTelp());
                txtCariTglLahir.setText(P.getTglLahir());

            }
        else
        {
            JOptionPane.showMessageDialog(this, "Data Pelanggan Tidak Ada..");
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
                String txt = cariPelanggan.getText();
                autoCompletNamaPelanggan(txt);
                
                }
            });
      }   
    }//GEN-LAST:event_cariPelangganKeyPressed

    private void btnSearchPelanggan1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnSearchPelanggan1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSearchPelanggan1KeyPressed

    private void cariNamaHewanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cariNamaHewanActionPerformed
        // TODO add your handling code here:
        
        
        
    }//GEN-LAST:event_cariNamaHewanActionPerformed

    private void cariNamaHewanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cariNamaHewanKeyPressed
        // TODO add your handling code here:
         switch(evt.getKeyCode())
        {
            case KeyEvent.VK_BACK_SPACE:
                if (evt.getKeyCode()==KeyEvent.VK_BACK_SPACE) {
                    
            
                }
                  
                break ;
            case KeyEvent.VK_ENTER:
                cariNamaHewan.setText(cariNamaHewan.getText());
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
              Dh= cariNamaHewan(cariNamaHewan.getText());
            if(Dh==null)
            {           

                JOptionPane.showMessageDialog(this, "Nama Hewan Tidak Ditemukan");
            
            }

                
         
        }
       
         
                break;
                
            default :
                EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                String txt =cariNamaHewan.getText();
                autoCompletNamaHewan(txt);
                
            }
        });
                
                        
        }
        
    }//GEN-LAST:event_cariNamaHewanKeyPressed

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
    private javax.swing.JPanel bantuan1;
    private javax.swing.JButton btlHapusSup;
    private javax.swing.JButton btlHapusSup1;
    private javax.swing.JButton btlSimpan;
    private javax.swing.JButton btlSimpan1;
    private javax.swing.JButton btnCari;
    private javax.swing.JButton btnCari1;
    private javax.swing.JButton btnHelp;
    private javax.swing.JButton btnHelp1;
    private javax.swing.JLabel btnLog;
    private javax.swing.JButton btnPegawai;
    private javax.swing.JButton btnPegawai1;
    private javax.swing.JLabel btnSearchPelanggan;
    private javax.swing.JLabel btnSearchPelanggan1;
    private javax.swing.JButton btnSimpanPelanggan;
    private javax.swing.JButton btnSimpanPelanggan1;
    private javax.swing.JButton btnSimpanProduk2;
    private javax.swing.JButton btnSimpanProduk3;
    private javax.swing.JButton btnTambah;
    private javax.swing.JButton btnTambah1;
    private javax.swing.JButton btnTampil;
    private javax.swing.JButton btnTampil1;
    private javax.swing.JTextField cariNamaHewan;
    private javax.swing.JPanel cariPegawai;
    private javax.swing.JPanel cariPegawai1;
    private javax.swing.JTextField cariPelanggan;
    private javax.swing.JPanel dataHewan;
    private javax.swing.JPanel dataPelanggan;
    private javax.swing.JComboBox<String> jComboBoxJenisHewan;
    private javax.swing.JComboBox<String> jComboBoxNamaPelanggan;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTextField jenisHewan;
    private javax.swing.JLabel judul;
    private javax.swing.JPanel mainPanel2;
    private javax.swing.JPanel mainPanel3;
    private javax.swing.JPanel menuHome;
    private javax.swing.JTextField namaHewan1;
    private javax.swing.JTextField namaPelanggan;
    private javax.swing.JTable tabelHewan;
    private javax.swing.JTable tabelPelanggan;
    private javax.swing.JPanel tambahPelanggan;
    private javax.swing.JPanel tambahPelanggan1;
    private javax.swing.JPanel tampilSeluruh;
    private javax.swing.JPanel tampilSeluruh1;
    private javax.swing.JTextField tglLahir;
    private javax.swing.JTextField txtAlamatPelanggan;
    private javax.swing.JTextField txtCariAlaPelanggan;
    private javax.swing.JTextField txtCariNamaPelanggan;
    private javax.swing.JTextField txtCariTelpPel;
    private javax.swing.JTextField txtCariTglLahir;
    private javax.swing.JTextField txtNamaHewan;
    private javax.swing.JTextField txtNamaPelanggan;
    private javax.swing.JLabel txtTanggalHariIni;
    private com.toedter.calendar.JDateChooser txtTanggalLahirHewan;
    private com.toedter.calendar.JDateChooser txtTanggalLahirPelanggan;
    private javax.swing.JTextField txtTelpPelanggan;
    private javax.swing.JLabel txtUserKasir;
    // End of variables declaration//GEN-END:variables
}
