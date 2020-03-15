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
import DAO.AdminDao;
import Model.JenisHewan;
import Model.Layanan;
import exception.CekHuruf;
import Model.Pemilik;
import java.util.Date;
import javax.swing.JOptionPane;
import Model.Pegawai ;
import Model.Produk;
import Model.Suplier;
import Model.UkuranHewan;
import Session.LoginSession;
import com.placeholder.PlaceHolder;
import exception.CekAngka;
import exception.CekHuruf;
import exception.dataKosong;
import exception.dataSama;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Vector;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import static org.hsqldb.lib.tar.TarHeaderField.uid;
import sun.nio.cs.ext.PCK;
/**
 *
 * @author ACER
 */
public class MenuAdmin extends javax.swing.JFrame {
    JenisHewan jh ;
    Suplier S;
    Pegawai  P ;
    Layanan L ;
    AdminControl AC ;
    Produk Pr ;
    public  DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MMMM/dd ");  
    public  LocalDateTime now = LocalDateTime.now();  
    int id = LoginSession.getIduser()  ;   
    public String idSesion = String.valueOf(id);
      
    public AdminDao AD  = new AdminDao();
    public int temp = 0 ;
    /**
     * Creates new form MenuAdmin
     */
    DefaultTableModel tabelModel,tabelModel1, tabelModel2,tabelModel4,tableModel5,tabelModel6 ;
    public MenuAdmin() {
        
        initComponents();
      
        PlaceHolder holder0 = new PlaceHolder(txtNamaLayanan,"Masukan Nama Layanan");
        
        PlaceHolder holder1 = new PlaceHolder(txtCari, "Masukkan User Name");
 
        PlaceHolder holder4 = new PlaceHolder(txtNamaHewan,"Masukan Nama Hewan");
        jComboBoxCoba.addItem("-Pilih Nama Makanan");
        jComboBoxSupllier.addItem("-Pilih Nama Supplier");
        jComboBoxHewan.addItem("-Pilih Jenis Hewan");
        jComboBoxLayanan.addItem("-Pilih Nama Layanan");
        
        txtTanggalHariIni.setText(dtf.format(now));
      
        holder1.setCursiva(true);
        holder0.setCursiva(true);
        holder4.setCursiva(true);
        setTitle("Menu Admin");
        disspegawai(false);
        tampilComboBox();
        tampilComboBoxCariLayanan();
        tampilComboBoxSupllier();
        tampilComboBoxCariHewan();
        
        AC = new AdminControl();
        txtAlamat.getBorder();
        tabelModel = (DefaultTableModel) tablePegawai.getModel();
        tabelModel1 =(DefaultTableModel) tableSupTambahData.getModel();
        tabelModel2 = (DefaultTableModel) tabelLayanan.getModel();
        tabelModel4 = (DefaultTableModel) tabelHewan.getModel() ;
        tableModel5 = (DefaultTableModel) tabelProduk.getModel();
        tabelModel6 = (DefaultTableModel) tabelUkuranHewan.getModel();
        tampliSuplier();       
        tampilPegawai();
        tampilLayanan();
        tampilJenisHewan();
        tampilProduk();
        tampilUkuranHewan();
        tablePegawai.setEnabled(false);
           
    }
    public void cekDataUkuranKosong()throws dataKosong
    {
        if(txtUkuranHewan.getText().length()<1)
        {
            throw  new dataKosong();
        }
    }
    public void setTextDataUkuran()
    {
        txtUkuranHewan.setText(" ");
        
    }
    
    public String getIdSesion()
    {
        return  idSesion;
    }
    public void addTeableProduk(Produk Pb)
    {
        Vector data = new Vector();
        data.add(Pb.getIdProduk());
        data.add(Pb.getNama());
        data.add(Pb.getSatuan());
        data.add(Pb.getJumlah());
        data.add(Pb.getHarga());
        data.add(Pb.getMin_stok());
        tableModel5.addRow(data);
    }
    public void addTabelUkuran(UkuranHewan Uh)
    {
        Vector data = new Vector();
        data.add(Uh.getID());
        data.add(Uh.getNama());
        tabelModel6.addRow(data);
    }
    public void addTableHewa(JenisHewan JH)
    {
       
        Vector data = new Vector();
        data.add(JH.getId());
        data.add(JH.getnNama());
        tabelModel4.addRow(data);
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
    public void tampilComboBoxSupllier()
    {
        AD.makeConnection();
        try
        {
            String sql = "Select nama from supplier where aktif = 1 ";
            Class.forName("com.mysql.jdbc.Driver"); 
            Statement st = AD.GETcon().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
           
                jComboBoxSupllier.addItem(rs.getString("nama"));

            }

            
        }
        catch(Exception e)
        {
            System.out.println("Gagal Menampilkan Supplier");
            System.out.println(e);
        }
        AD.closeConnection();
    }
    public void tampilComboBoxCariLayanan()
    {
        AD.makeConnection();
        try
        {
            String sql= "Select nama from Layanan where aktif =1 ";
            Class.forName("com.mysql.jdbc.Driver"); 
            Statement st = AD.GETcon().createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
           
                jComboBoxLayanan.addItem(rs.getString("nama"));

            }

        }
        catch(Exception e)
        {
            System.out.println("Gagal");
            System.out.println(e);
        }
    }
    public void tampilComboBoxCariHewan()
    {

        AD.makeConnection();
        try
        {
            String sql= "Select nama from jenis_hewan where aktif=1 ORDER BY NAMA ASC";
            Class.forName("com.mysql.jdbc.Driver"); 
            Statement st = AD.GETcon().createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
           
                jComboBoxHewan.addItem(rs.getString("nama"));

            }

            
            } catch (Exception e) {
                System.out.println(e);
            }
       AD.closeConnection();
    }
    public void tampilComboBox()
    {
        

       try {
            AD.makeConnection();
            String query = "SELECT NAMA FROM PRODUK WHERE AKTIF=1";
            Class.forName("com.mysql.jdbc.Driver"); 
            Statement st = AD.GETcon().createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
           
                jComboBoxCoba.addItem(rs.getString(1));
              
            }



            } catch (Exception e) {
                System.out.println(e);
            }
       AD.closeConnection();
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
    public void setTextSupplier()
    {
        txtAlamatSup.setText(" ");
        txtNamSup.setText(" ");
        txtTelpSup.setText(" ");
    }
    public void setTextDataProduk()
    {
        txtNamaProduk.setText(" ");
        txtSatun.setText(" ");
        txtJumStok.setText(" ");
        txtHargaProduk.setText(" ");
        txtMinStok.setText(" ");
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
    public void tampilUkuranHewan()
    {
        int  a = tabelModel6.getRowCount();
        for (int i = 0; i <a; i++) {
            tabelModel6.removeRow(0);
            
        }
        UkuranHewan UH ;
        List<UkuranHewan> M= AC.tampilUkuran();
        for (int i = 0; i < M.size(); i++) {

            UH= new UkuranHewan();
            UH.setID(M.get(i).getID());
            UH.setNama(M.get(i).getNama());
            addTabelUkuran(UH);
        }
    }
    public void tampilProduk()
    {
        int a = tableModel5.getRowCount() ;
        for (int i = 0; i < a; i++) {
            tableModel5.removeRow(0);
        }
        Produk P ;
        List<Produk> M = AC.tampilDataProduk();
        for (int i = 0; i <M.size(); i++) {
            
            P= new Produk();

            P.setId(M.get(i).getIdProduk());
            P.setNama(M.get(i).getNama());
            P.setSatuan(M.get(i).getSatuan());
            P.setJumlah(M.get(i).getJumlah());
            P.setHarga(M.get(i).getHarga());
            P.setMin_Stok(M.get(i).getMin_stok());
            addTeableProduk(P);
        }
    }
    public void tampilJenisHewan()
    {
        int a = tabelModel4.getRowCount() ;
        for (int i = 0; i < a; i++) {
            tabelModel4.removeRow(0);
            
        }
        JenisHewan JH ;
        JH =  new JenisHewan();
        List<JenisHewan>M= AC.tampilJenisHewan();
        for (int i = 0; i < M.size(); i++) {
            JenisHewan L= new JenisHewan();
            L.setId(M.get(i).getId());
            L.setNama(M.get(i).getnNama());
            addTableHewa(L);
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
    public void setTextjJenisHewan()
    {
        txtNamaHewan.setText(" ");
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
        btnLog = new javax.swing.JLabel();
        txtTanggalHariIni = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        btnUkuran = new javax.swing.JButton();
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
        cariLayanan = new javax.swing.JPanel();
        btnCariLayanan = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jComboBoxLayanan = new javax.swing.JComboBox<>();
        jPanel16 = new javax.swing.JPanel();
        btnSimpanHewan1 = new javax.swing.JButton();
        btlHapusHewan1 = new javax.swing.JButton();
        txtNamaLayananCari = new javax.swing.JTextField();
        jLabel85 = new javax.swing.JLabel();
        tampilLayanan = new javax.swing.JPanel();
        tambahLayanan = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        btnSimpanLyn = new javax.swing.JButton();
        btlSimpan1 = new javax.swing.JButton();
        txtNamaLayanan = new javax.swing.JTextField();
        jLabel69 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tabelLayanan = new javax.swing.JTable();
        jLabel74 = new javax.swing.JLabel();
        dataProduk = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        btnTambah1 = new javax.swing.JButton();
        btnHelp2 = new javax.swing.JButton();
        btnTampil1 = new javax.swing.JButton();
        btnCari1 = new javax.swing.JButton();
        jLabel27 = new javax.swing.JLabel();
        mainPanel5 = new javax.swing.JPanel();
        tambahProduk = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        btnSimpanProduk = new javax.swing.JButton();
        btlSimpan = new javax.swing.JButton();
        txtMinStok = new javax.swing.JTextField();
        txtSatun = new javax.swing.JTextField();
        txtNamaProduk = new javax.swing.JTextField();
        jLabel57 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        txtHargaProduk = new javax.swing.JTextField();
        jLabel62 = new javax.swing.JLabel();
        txtJumStok = new javax.swing.JTextField();
        jLabel60 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tabelProduk = new javax.swing.JTable();
        cariProduk = new javax.swing.JPanel();
        btnSearchProduk = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        btnSimpanProduk1 = new javax.swing.JButton();
        btlHapusProduk = new javax.swing.JButton();
        txtMinStokP = new javax.swing.JTextField();
        txtSatunP = new javax.swing.JTextField();
        txtCariNamaP = new javax.swing.JTextField();
        jLabel63 = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        txtHargaProdukp = new javax.swing.JTextField();
        jLabel67 = new javax.swing.JLabel();
        txtJumStokP = new javax.swing.JTextField();
        jComboBoxCoba = new javax.swing.JComboBox<>();
        tampilProduk = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tablePegawai1 = new javax.swing.JTable();
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
        btnSearchSup = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        btnSimpanProduk2 = new javax.swing.JButton();
        btlHapusSup = new javax.swing.JButton();
        txtCariAlaSup = new javax.swing.JTextField();
        txtCariNamaSup = new javax.swing.JTextField();
        jLabel70 = new javax.swing.JLabel();
        jLabel71 = new javax.swing.JLabel();
        jLabel72 = new javax.swing.JLabel();
        txtCariTelpSup = new javax.swing.JTextField();
        jComboBoxSupllier = new javax.swing.JComboBox<>();
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
        jPanel8 = new javax.swing.JPanel();
        btnTambahJenis = new javax.swing.JButton();
        btnSupHelp1 = new javax.swing.JButton();
        btnTampilSup1 = new javax.swing.JButton();
        btnCariJenis = new javax.swing.JButton();
        jLabel42 = new javax.swing.JLabel();
        mainPanel8 = new javax.swing.JPanel();
        tambahHewan = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        btnSimpan = new javax.swing.JButton();
        jLabel38 = new javax.swing.JLabel();
        txtNamaHewan = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabelHewan = new javax.swing.JTable();
        jLabel68 = new javax.swing.JLabel();
        CariHewan = new javax.swing.JPanel();
        jLabel37 = new javax.swing.JLabel();
        btnSearchJenisHewan = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        btnSimpanHewan = new javax.swing.JButton();
        btlHapusHewan = new javax.swing.JButton();
        txtCariNamaHwn = new javax.swing.JTextField();
        jLabel84 = new javax.swing.JLabel();
        jComboBoxHewan = new javax.swing.JComboBox<>();
        TampilHewan = new javax.swing.JPanel();
        dataUkuran = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        btnTambahUkuran = new javax.swing.JButton();
        btnHelp3 = new javax.swing.JButton();
        btnTampilLyn1 = new javax.swing.JButton();
        btnCariUkuran = new javax.swing.JButton();
        jLabel28 = new javax.swing.JLabel();
        mainPanel10 = new javax.swing.JPanel();
        cariUkuranHewan = new javax.swing.JPanel();
        btnCariLayanan1 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jComboBoxLayanan1 = new javax.swing.JComboBox<>();
        jPanel18 = new javax.swing.JPanel();
        btnSimpanHewan2 = new javax.swing.JButton();
        btlHapusHewan2 = new javax.swing.JButton();
        txtNamaLayananCari1 = new javax.swing.JTextField();
        jLabel86 = new javax.swing.JLabel();
        tampilUkuranHewan = new javax.swing.JPanel();
        tambahUkuranHewan = new javax.swing.JPanel();
        jPanel19 = new javax.swing.JPanel();
        btnSimpanUkuran = new javax.swing.JButton();
        btlSimpan2 = new javax.swing.JButton();
        txtUkuranHewan = new javax.swing.JTextField();
        jLabel87 = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        tabelUkuranHewan = new javax.swing.JTable();
        jLabel88 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImage(new javax.swing.ImageIcon(getClass().getResource("/icon/dogg.png")).getImage());

        background.setBackground(new java.awt.Color(255, 255, 255));
        background.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        menuHome.setBackground(new java.awt.Color(99, 175, 241));
        menuHome.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        judul.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
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

        btnLog.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/log-icon (2).png"))); // NOI18N

        txtTanggalHariIni.setFont(new java.awt.Font("Dialog", 2, 12)); // NOI18N
        txtTanggalHariIni.setForeground(new java.awt.Color(0, 0, 0));
        txtTanggalHariIni.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        jLabel23.setFont(new java.awt.Font("Times New Roman", 3, 12)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(0, 0, 0));
        jLabel23.setText("Tanggal :");

        btnUkuran.setFont(new java.awt.Font("Serif", 1, 12)); // NOI18N
        btnUkuran.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Science-Length-icon.png"))); // NOI18N
        btnUkuran.setText("Data Ukuran");
        btnUkuran.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnUkuran.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnUkuran.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btnUkuran.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUkuranActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout menuHomeLayout = new javax.swing.GroupLayout(menuHome);
        menuHome.setLayout(menuHomeLayout);
        menuHomeLayout.setHorizontalGroup(
            menuHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuHomeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(menuHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel13)
                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(menuHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, menuHomeLayout.createSequentialGroup()
                        .addComponent(txtTanggalHariIni, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, menuHomeLayout.createSequentialGroup()
                        .addComponent(judul)
                        .addContainerGap())))
            .addGroup(menuHomeLayout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(btnLog, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, menuHomeLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(menuHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnSup, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnProduk, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnLayanan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnHewan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnPegawai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnUkuran, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(57, 57, 57))
        );
        menuHomeLayout.setVerticalGroup(
            menuHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuHomeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(menuHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTanggalHariIni, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(menuHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(judul))
                .addGap(46, 46, 46)
                .addComponent(btnPegawai)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnLayanan)
                .addGap(18, 18, 18)
                .addComponent(btnProduk)
                .addGap(18, 18, 18)
                .addComponent(btnSup)
                .addGap(18, 18, 18)
                .addComponent(btnHewan)
                .addGap(28, 28, 28)
                .addComponent(btnUkuran)
                .addGap(73, 73, 73)
                .addComponent(btnLog)
                .addContainerGap(331, Short.MAX_VALUE))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTambah)
                    .addComponent(btnCari)
                    .addComponent(btnHelp)
                    .addComponent(btnTampil))
                .addContainerGap())
        );

        mainPanel2.setLayout(new java.awt.CardLayout());

        bantuan.setBackground(new java.awt.Color(99, 175, 241));

        javax.swing.GroupLayout bantuanLayout = new javax.swing.GroupLayout(bantuan);
        bantuan.setLayout(bantuanLayout);
        bantuanLayout.setHorizontalGroup(
            bantuanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1149, Short.MAX_VALUE)
        );
        bantuanLayout.setVerticalGroup(
            bantuanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 800, Short.MAX_VALUE)
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
        btnSimpanPegawai.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        btnSimpanPegawai.setForeground(new java.awt.Color(0, 0, 0));
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
        jButton3.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jButton3.setForeground(new java.awt.Color(0, 0, 0));
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
        txtTanggalLahir.setDateFormatString("yyyy-MMMMMM-d");

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
                .addGap(91, 91, 91)
                .addGroup(tambahPegawaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addGroup(tambahPegawaiLayout.createSequentialGroup()
                        .addGroup(tambahPegawaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(tambahPegawaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(tambahPegawaiLayout.createSequentialGroup()
                                .addGroup(tambahPegawaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtNamaPegawai, javax.swing.GroupLayout.DEFAULT_SIZE, 331, Short.MAX_VALUE)
                                    .addComponent(txtNoTelp)
                                    .addComponent(txtAlamat))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(tambahPegawaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(tambahPegawaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtUserName)
                                    .addComponent(txtPassword)
                                    .addGroup(tambahPegawaiLayout.createSequentialGroup()
                                        .addGroup(tambahPegawaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(tambahPegawaiLayout.createSequentialGroup()
                                                .addComponent(jButton3)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(btnSimpanPegawai))
                                            .addComponent(txtTanggalLahir, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(0, 0, Short.MAX_VALUE))))
                            .addGroup(tambahPegawaiLayout.createSequentialGroup()
                                .addComponent(rdCustomerService)
                                .addGap(105, 105, 105)
                                .addComponent(rdKasir, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(23, 23, 23)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        tambahPegawaiLayout.setVerticalGroup(
            tambahPegawaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tambahPegawaiLayout.createSequentialGroup()
                .addGroup(tambahPegawaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tambahPegawaiLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addGroup(tambahPegawaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNamaPegawai, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tambahPegawaiLayout.createSequentialGroup()
                        .addGap(129, 129, 129)
                        .addComponent(txtTanggalLahir, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(30, 30, 30)
                .addGroup(tambahPegawaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNoTelp, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtUserName, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(tambahPegawaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tambahPegawaiLayout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(tambahPegawaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtAlamat, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tambahPegawaiLayout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(tambahPegawaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(30, 30, 30)
                .addGroup(tambahPegawaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tambahPegawaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSimpanPegawai))
                    .addGroup(tambahPegawaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel11)
                        .addComponent(rdKasir)
                        .addComponent(rdCustomerService)))
                .addGap(184, 184, 184))
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addContainerGap(302, Short.MAX_VALUE))
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1125, Short.MAX_VALUE)
                .addContainerGap())
        );
        tampilSeluruhLayout.setVerticalGroup(
            tampilSeluruhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tampilSeluruhLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(366, Short.MAX_VALUE))
        );

        mainPanel2.add(tampilSeluruh, "card4");

        javax.swing.GroupLayout dataPegawaiLayout = new javax.swing.GroupLayout(dataPegawai);
        dataPegawai.setLayout(dataPegawaiLayout);
        dataPegawaiLayout.setHorizontalGroup(
            dataPegawaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dataPegawaiLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(dataPegawaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(mainPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        dataPegawaiLayout.setVerticalGroup(
            dataPegawaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dataPegawaiLayout.createSequentialGroup()
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

        cariLayanan.setBackground(new java.awt.Color(99, 175, 241));

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

        jComboBoxLayanan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jComboBoxLayananMouseClicked(evt);
            }
        });
        jComboBoxLayanan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxLayananActionPerformed(evt);
            }
        });

        jPanel16.setBackground(new java.awt.Color(99, 175, 241));
        jPanel16.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11))); // NOI18N

        btnSimpanHewan1.setText("SIMPAN");
        btnSimpanHewan1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanHewan1ActionPerformed(evt);
            }
        });

        btlHapusHewan1.setText("HAPUS");
        btlHapusHewan1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btlHapusHewan1ActionPerformed(evt);
            }
        });

        txtNamaLayananCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNamaLayananCariActionPerformed(evt);
            }
        });

        jLabel85.setFont(new java.awt.Font("Clarendon BT", 0, 18)); // NOI18N
        jLabel85.setForeground(new java.awt.Color(0, 0, 0));
        jLabel85.setText("Nama Layanan");

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addComponent(btlHapusHewan1, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnSimpanHewan1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                        .addComponent(jLabel85)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtNamaLayananCari, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNamaLayananCari, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel85, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(170, 170, 170)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btlHapusHewan1)
                    .addComponent(btnSimpanHewan1))
                .addContainerGap(115, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout cariLayananLayout = new javax.swing.GroupLayout(cariLayanan);
        cariLayanan.setLayout(cariLayananLayout);
        cariLayananLayout.setHorizontalGroup(
            cariLayananLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cariLayananLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(95, 919, Short.MAX_VALUE))
            .addGroup(cariLayananLayout.createSequentialGroup()
                .addGap(150, 150, 150)
                .addComponent(btnCariLayanan)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(cariLayananLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBoxLayanan, javax.swing.GroupLayout.PREFERRED_SIZE, 427, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        cariLayananLayout.setVerticalGroup(
            cariLayananLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cariLayananLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(cariLayananLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(cariLayananLayout.createSequentialGroup()
                        .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(56, 56, 56)
                        .addComponent(jComboBoxLayanan, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnCariLayanan))
                .addGap(18, 18, 18)
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(257, Short.MAX_VALUE))
        );

        mainPanel3.add(cariLayanan, "card3");

        tampilLayanan.setBackground(new java.awt.Color(99, 175, 241));

        javax.swing.GroupLayout tampilLayananLayout = new javax.swing.GroupLayout(tampilLayanan);
        tampilLayanan.setLayout(tampilLayananLayout);
        tampilLayananLayout.setHorizontalGroup(
            tampilLayananLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1149, Short.MAX_VALUE)
        );
        tampilLayananLayout.setVerticalGroup(
            tampilLayananLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 804, Short.MAX_VALUE)
        );

        mainPanel3.add(tampilLayanan, "card4");

        tambahLayanan.setBackground(new java.awt.Color(99, 175, 241));

        jPanel10.setBackground(new java.awt.Color(99, 175, 241));
        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11))); // NOI18N

        btnSimpanLyn.setText("SIMPAN");
        btnSimpanLyn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanLynActionPerformed(evt);
            }
        });

        btlSimpan1.setText("BATAL");

        txtNamaLayanan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNamaLayananActionPerformed(evt);
            }
        });

        jLabel69.setFont(new java.awt.Font("Clarendon BT", 0, 18)); // NOI18N
        jLabel69.setForeground(new java.awt.Color(0, 0, 0));
        jLabel69.setText("Nama Layanan");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel69)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNamaLayanan, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(btlSimpan1, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnSimpanLyn, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNamaLayanan, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel69, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 199, Short.MAX_VALUE)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btlSimpan1)
                    .addComponent(btnSimpanLyn))
                .addGap(76, 76, 76))
        );

        tabelLayanan.setBackground(new java.awt.Color(255, 255, 255));
        tabelLayanan.setFont(new java.awt.Font("Geometr212 BkCn BT", 1, 12)); // NOI18N
        tabelLayanan.setForeground(new java.awt.Color(0, 0, 0));
        tabelLayanan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "ID_Layanan", "Nama Layanan"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelLayanan.setGridColor(new java.awt.Color(0, 0, 0));
        jScrollPane7.setViewportView(tabelLayanan);
        if (tabelLayanan.getColumnModel().getColumnCount() > 0) {
            tabelLayanan.getColumnModel().getColumn(0).setResizable(false);
            tabelLayanan.getColumnModel().getColumn(1).setResizable(false);
        }

        jLabel74.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        jLabel74.setForeground(new java.awt.Color(0, 0, 0));
        jLabel74.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/menuTambahHewan.png"))); // NOI18N
        jLabel74.setText("TAMBAH LAYANAN");

        javax.swing.GroupLayout tambahLayananLayout = new javax.swing.GroupLayout(tambahLayanan);
        tambahLayanan.setLayout(tambahLayananLayout);
        tambahLayananLayout.setHorizontalGroup(
            tambahLayananLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tambahLayananLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(tambahLayananLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tambahLayananLayout.createSequentialGroup()
                        .addComponent(jLabel74, javax.swing.GroupLayout.PREFERRED_SIZE, 426, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(tambahLayananLayout.createSequentialGroup()
                        .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(32, 32, 32)
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 489, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(170, 170, 170))))
        );
        tambahLayananLayout.setVerticalGroup(
            tambahLayananLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tambahLayananLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel74, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(tambahLayananLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 384, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(272, Short.MAX_VALUE))
        );

        mainPanel3.add(tambahLayanan, "card2");

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
        btnTambah1.setText("Tambah Produk");
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
        btnCari1.setText("Cari Produk");
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
                        .addGap(6, 6, 6)
                        .addComponent(btnTambah1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCari1, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnTampil1)))
                .addGap(49, 49, 49)
                .addComponent(btnHelp2)
                .addContainerGap(652, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTambah1)
                    .addComponent(btnCari1)
                    .addComponent(btnHelp2)
                    .addComponent(btnTampil1))
                .addContainerGap())
        );

        mainPanel5.setLayout(new java.awt.CardLayout());

        tambahProduk.setBackground(new java.awt.Color(99, 175, 241));
        tambahProduk.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tambahProduk.setForeground(new java.awt.Color(0, 0, 0));

        jPanel9.setBackground(new java.awt.Color(99, 175, 241));
        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11))); // NOI18N

        btnSimpanProduk.setText("SIMPAN");
        btnSimpanProduk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanProdukActionPerformed(evt);
            }
        });

        btlSimpan.setText("BATAL");

        txtMinStok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMinStokActionPerformed(evt);
            }
        });

        txtNamaProduk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNamaProdukActionPerformed(evt);
            }
        });

        jLabel57.setFont(new java.awt.Font("Clarendon BT", 0, 18)); // NOI18N
        jLabel57.setForeground(new java.awt.Color(0, 0, 0));
        jLabel57.setText("Nama Produk");

        jLabel58.setFont(new java.awt.Font("Clarendon BT", 0, 18)); // NOI18N
        jLabel58.setForeground(new java.awt.Color(0, 0, 0));
        jLabel58.setText("Satuan");

        jLabel59.setFont(new java.awt.Font("Clarendon BT", 0, 18)); // NOI18N
        jLabel59.setForeground(new java.awt.Color(0, 0, 0));
        jLabel59.setText("Jumlah Stok");

        jLabel61.setFont(new java.awt.Font("Clarendon BT", 0, 18)); // NOI18N
        jLabel61.setForeground(new java.awt.Color(0, 0, 0));
        jLabel61.setText("Harga");

        txtHargaProduk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtHargaProdukActionPerformed(evt);
            }
        });

        jLabel62.setFont(new java.awt.Font("Clarendon BT", 0, 18)); // NOI18N
        jLabel62.setForeground(new java.awt.Color(0, 0, 0));
        jLabel62.setText("Min Stok");

        txtJumStok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtJumStokActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                                        .addComponent(jLabel57)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtNamaProduk, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel9Layout.createSequentialGroup()
                                        .addComponent(jLabel61, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtHargaProduk))
                                    .addGroup(jPanel9Layout.createSequentialGroup()
                                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel58, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel59, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtSatun, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtJumStok, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(35, 35, 35))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(btlSimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnSimpanProduk, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel62, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtMinStok, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNamaProduk, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel58, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSatun, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel59, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtJumStok, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel61, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtHargaProduk, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel62, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMinStok, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btlSimpan)
                    .addComponent(btnSimpanProduk))
                .addGap(76, 76, 76))
        );

        jLabel60.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        jLabel60.setForeground(new java.awt.Color(0, 0, 0));
        jLabel60.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Box-icon (1).png"))); // NOI18N
        jLabel60.setText("TAMBAH PRODUK");

        tabelProduk.setBackground(new java.awt.Color(255, 255, 255));
        tabelProduk.setFont(new java.awt.Font("Geometr212 BkCn BT", 1, 12)); // NOI18N
        tabelProduk.setForeground(new java.awt.Color(0, 0, 0));
        tabelProduk.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID_Produk", "Nama ", "Satuan", "Jumlah Stok", "Harga", "Min Stok"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelProduk.setGridColor(new java.awt.Color(0, 0, 0));
        jScrollPane4.setViewportView(tabelProduk);
        if (tabelProduk.getColumnModel().getColumnCount() > 0) {
            tabelProduk.getColumnModel().getColumn(0).setResizable(false);
            tabelProduk.getColumnModel().getColumn(1).setResizable(false);
            tabelProduk.getColumnModel().getColumn(2).setResizable(false);
            tabelProduk.getColumnModel().getColumn(3).setResizable(false);
            tabelProduk.getColumnModel().getColumn(4).setResizable(false);
            tabelProduk.getColumnModel().getColumn(5).setResizable(false);
        }

        javax.swing.GroupLayout tambahProdukLayout = new javax.swing.GroupLayout(tambahProduk);
        tambahProduk.setLayout(tambahProdukLayout);
        tambahProdukLayout.setHorizontalGroup(
            tambahProdukLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tambahProdukLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(tambahProdukLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel60, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 567, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(92, 92, 92))
        );
        tambahProdukLayout.setVerticalGroup(
            tambahProdukLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tambahProdukLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel60, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(tambahProdukLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tambahProdukLayout.createSequentialGroup()
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );

        mainPanel5.add(tambahProduk, "card2");

        cariProduk.setBackground(new java.awt.Color(99, 175, 241));
        cariProduk.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btnSearchProduk.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/search-icon.png"))); // NOI18N
        btnSearchProduk.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSearchProdukMouseClicked(evt);
            }
        });

        jLabel49.setBackground(new java.awt.Color(0, 255, 255));
        jLabel49.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jLabel49.setForeground(new java.awt.Color(0, 0, 0));
        jLabel49.setText("Cari Produk");

        jPanel11.setBackground(new java.awt.Color(99, 175, 241));
        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11))); // NOI18N

        btnSimpanProduk1.setText("SIMPAN");
        btnSimpanProduk1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanProduk1ActionPerformed(evt);
            }
        });

        btlHapusProduk.setText("HAPUS");
        btlHapusProduk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btlHapusProdukActionPerformed(evt);
            }
        });

        txtMinStokP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMinStokPActionPerformed(evt);
            }
        });

        txtCariNamaP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCariNamaPActionPerformed(evt);
            }
        });

        jLabel63.setFont(new java.awt.Font("Clarendon BT", 0, 18)); // NOI18N
        jLabel63.setForeground(new java.awt.Color(0, 0, 0));
        jLabel63.setText("Nama Produk");

        jLabel64.setFont(new java.awt.Font("Clarendon BT", 0, 18)); // NOI18N
        jLabel64.setForeground(new java.awt.Color(0, 0, 0));
        jLabel64.setText("Satuan");

        jLabel65.setFont(new java.awt.Font("Clarendon BT", 0, 18)); // NOI18N
        jLabel65.setForeground(new java.awt.Color(0, 0, 0));
        jLabel65.setText("Jumlah Stok");

        jLabel66.setFont(new java.awt.Font("Clarendon BT", 0, 18)); // NOI18N
        jLabel66.setForeground(new java.awt.Color(0, 0, 0));
        jLabel66.setText("Harga");

        txtHargaProdukp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtHargaProdukpActionPerformed(evt);
            }
        });

        jLabel67.setFont(new java.awt.Font("Clarendon BT", 0, 18)); // NOI18N
        jLabel67.setForeground(new java.awt.Color(0, 0, 0));
        jLabel67.setText("Min Stok");

        txtJumStokP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtJumStokPActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap(32, Short.MAX_VALUE)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                                .addComponent(jLabel63)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtCariNamaP, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel64, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel65, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtSatunP, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtJumStokP, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel66, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel67, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtMinStokP, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtHargaProdukp))))
                        .addGap(35, 35, 35))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(btlHapusProduk, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnSimpanProduk1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCariNamaP, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel63, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel64, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSatunP, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel65, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtJumStokP, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel66, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtHargaProdukp, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel67, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMinStokP, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btlHapusProduk)
                    .addComponent(btnSimpanProduk1))
                .addGap(76, 76, 76))
        );

        jComboBoxCoba.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jComboBoxCobaMouseClicked(evt);
            }
        });
        jComboBoxCoba.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxCobaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout cariProdukLayout = new javax.swing.GroupLayout(cariProduk);
        cariProduk.setLayout(cariProdukLayout);
        cariProdukLayout.setHorizontalGroup(
            cariProdukLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cariProdukLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(cariProdukLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(cariProdukLayout.createSequentialGroup()
                        .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(291, 291, 291))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, cariProdukLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(btnSearchProduk)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jComboBoxCoba, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(642, Short.MAX_VALUE))
        );
        cariProdukLayout.setVerticalGroup(
            cariProdukLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, cariProdukLayout.createSequentialGroup()
                .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(cariProdukLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnSearchProduk)
                    .addComponent(jComboBoxCoba, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(230, 230, 230))
        );

        mainPanel5.add(cariProduk, "card3");

        tampilProduk.setBackground(new java.awt.Color(99, 175, 241));

        tablePegawai1.setBackground(new java.awt.Color(255, 255, 255));
        tablePegawai1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        tablePegawai1.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        tablePegawai1.setForeground(new java.awt.Color(0, 0, 0));
        tablePegawai1.setModel(new javax.swing.table.DefaultTableModel(
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
        tablePegawai1.setGridColor(new java.awt.Color(0, 0, 0));
        jScrollPane5.setViewportView(tablePegawai1);

        javax.swing.GroupLayout tampilProdukLayout = new javax.swing.GroupLayout(tampilProduk);
        tampilProduk.setLayout(tampilProdukLayout);
        tampilProdukLayout.setHorizontalGroup(
            tampilProdukLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tampilProdukLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 1125, Short.MAX_VALUE)
                .addContainerGap())
        );
        tampilProdukLayout.setVerticalGroup(
            tampilProdukLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tampilProdukLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(283, Short.MAX_VALUE))
        );

        mainPanel5.add(tampilProduk, "card4");

        javax.swing.GroupLayout dataProdukLayout = new javax.swing.GroupLayout(dataProduk);
        dataProduk.setLayout(dataProdukLayout);
        dataProdukLayout.setHorizontalGroup(
            dataProdukLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dataProdukLayout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(dataProdukLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(dataProdukLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(mainPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        dataProdukLayout.setVerticalGroup(
            dataProdukLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dataProdukLayout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(820, Short.MAX_VALUE))
            .addGroup(dataProdukLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(dataProdukLayout.createSequentialGroup()
                    .addGap(153, 153, 153)
                    .addComponent(mainPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 717, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(75, Short.MAX_VALUE)))
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

        CariSuplier.setBackground(new java.awt.Color(99, 175, 241));

        jLabel32.setBackground(new java.awt.Color(0, 255, 255));
        jLabel32.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(0, 255, 255));
        jLabel32.setText("Cari Supplier");

        btnSearchSup.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/search-icon.png"))); // NOI18N
        btnSearchSup.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSearchSupMouseClicked(evt);
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

        txtCariNamaSup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCariNamaSupActionPerformed(evt);
            }
        });

        jLabel70.setFont(new java.awt.Font("Clarendon BT", 0, 18)); // NOI18N
        jLabel70.setForeground(new java.awt.Color(0, 0, 0));
        jLabel70.setText("Nama Supplier");

        jLabel71.setFont(new java.awt.Font("Clarendon BT", 0, 18)); // NOI18N
        jLabel71.setForeground(new java.awt.Color(0, 0, 0));
        jLabel71.setText("Alamat");

        jLabel72.setFont(new java.awt.Font("Clarendon BT", 0, 18)); // NOI18N
        jLabel72.setForeground(new java.awt.Color(0, 0, 0));
        jLabel72.setText("Telephone");

        txtCariTelpSup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCariTelpSupActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(btlHapusSup, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnSimpanProduk2, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel71, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel72, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtCariAlaSup, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtCariTelpSup, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addComponent(jLabel70)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtCariNamaSup, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(35, 35, 35))))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCariNamaSup, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel70, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel71, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCariAlaSup, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel72, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCariTelpSup, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(79, 79, 79)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btlHapusSup)
                    .addComponent(btnSimpanProduk2))
                .addContainerGap(115, Short.MAX_VALUE))
        );

        jComboBoxSupllier.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jComboBoxSupllierMouseClicked(evt);
            }
        });
        jComboBoxSupllier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxSupllierActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout CariSuplierLayout = new javax.swing.GroupLayout(CariSuplier);
        CariSuplier.setLayout(CariSuplierLayout);
        CariSuplierLayout.setHorizontalGroup(
            CariSuplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CariSuplierLayout.createSequentialGroup()
                .addGroup(CariSuplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(CariSuplierLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(CariSuplierLayout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(btnSearchSup)
                        .addGap(3, 3, 3)
                        .addGroup(CariSuplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jComboBoxSupllier, 0, 427, Short.MAX_VALUE))))
                .addContainerGap(627, Short.MAX_VALUE))
        );
        CariSuplierLayout.setVerticalGroup(
            CariSuplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CariSuplierLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addGroup(CariSuplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnSearchSup)
                    .addComponent(jComboBoxSupllier, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(266, Short.MAX_VALUE))
        );

        mainPanel4.add(CariSuplier, "card3");

        javax.swing.GroupLayout TampilSuplierLayout = new javax.swing.GroupLayout(TampilSuplier);
        TampilSuplier.setLayout(TampilSuplierLayout);
        TampilSuplierLayout.setHorizontalGroup(
            TampilSuplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1149, Short.MAX_VALUE)
        );
        TampilSuplierLayout.setVerticalGroup(
            TampilSuplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 794, Short.MAX_VALUE)
        );

        mainPanel4.add(TampilSuplier, "card4");

        javax.swing.GroupLayout HelpSuplierLayout = new javax.swing.GroupLayout(HelpSuplier);
        HelpSuplier.setLayout(HelpSuplierLayout);
        HelpSuplierLayout.setHorizontalGroup(
            HelpSuplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1149, Short.MAX_VALUE)
        );
        HelpSuplierLayout.setVerticalGroup(
            HelpSuplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 794, Short.MAX_VALUE)
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

        txtTelpSup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTelpSupActionPerformed(evt);
            }
        });

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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                    .addComponent(mainPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 1149, Short.MAX_VALUE))
                .addContainerGap())
        );
        dataSupplierLayout.setVerticalGroup(
            dataSupplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dataSupplierLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(mainPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 794, Short.MAX_VALUE)
                .addContainerGap())
        );

        MainPanel.add(dataSupplier, "card5");

        dataHewan.setBackground(new java.awt.Color(255, 255, 255));

        jPanel8.setBackground(new java.awt.Color(99, 175, 241));
        jPanel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btnTambahJenis.setBackground(new java.awt.Color(255, 255, 255));
        btnTambahJenis.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnTambahJenis.setForeground(new java.awt.Color(0, 0, 0));
        btnTambahJenis.setText("Tambah Hewan");
        btnTambahJenis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTambahJenisActionPerformed(evt);
            }
        });

        btnSupHelp1.setBackground(new java.awt.Color(51, 255, 255));
        btnSupHelp1.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnSupHelp1.setForeground(new java.awt.Color(0, 0, 0));
        btnSupHelp1.setText("Help");
        btnSupHelp1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSupHelp1ActionPerformed(evt);
            }
        });

        btnTampilSup1.setBackground(new java.awt.Color(255, 255, 255));
        btnTampilSup1.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnTampilSup1.setForeground(new java.awt.Color(0, 0, 0));
        btnTampilSup1.setText("Tampil Seluruh Jenis");
        btnTampilSup1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTampilSup1ActionPerformed(evt);
            }
        });

        btnCariJenis.setBackground(new java.awt.Color(255, 255, 255));
        btnCariJenis.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnCariJenis.setForeground(new java.awt.Color(0, 0, 0));
        btnCariJenis.setText("Cari Jenis Hewan");
        btnCariJenis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariJenisActionPerformed(evt);
            }
        });

        jLabel42.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 24)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(0, 0, 0));
        jLabel42.setText("MENGELOLA JENIS HEWAN KOEVEE PET SHOP");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(btnTambahJenis)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnCariJenis, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnTampilSup1))
                    .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 436, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addComponent(btnSupHelp1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTambahJenis)
                    .addComponent(btnCariJenis)
                    .addComponent(btnSupHelp1)
                    .addComponent(btnTampilSup1))
                .addContainerGap())
        );

        mainPanel8.setBackground(java.awt.Color.darkGray);
        mainPanel8.setLayout(new java.awt.CardLayout());

        tambahHewan.setBackground(new java.awt.Color(99, 175, 241));
        tambahHewan.setForeground(new java.awt.Color(0, 0, 0));

        jPanel7.setBackground(new java.awt.Color(99, 175, 241));
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N

        btnSimpan.setText("Simpan");
        btnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanActionPerformed(evt);
            }
        });

        jLabel38.setFont(new java.awt.Font("Clarendon BT", 0, 18)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(0, 0, 0));
        jLabel38.setText("Jenis Hewan");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSimpan)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel38)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtNamaHewan, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(71, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNamaHewan, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 143, Short.MAX_VALUE)
                .addComponent(btnSimpan)
                .addGap(77, 77, 77))
        );

        tabelHewan.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0)));
        tabelHewan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Id_Hewan", "Jenis Hewan"
            }
        ));
        tabelHewan.setGridColor(new java.awt.Color(0, 0, 0));
        jScrollPane3.setViewportView(tabelHewan);

        jLabel68.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        jLabel68.setForeground(new java.awt.Color(0, 0, 0));
        jLabel68.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/menuTambahHewan.png"))); // NOI18N
        jLabel68.setText("TAMBAH Jenis Hewan");

        javax.swing.GroupLayout tambahHewanLayout = new javax.swing.GroupLayout(tambahHewan);
        tambahHewan.setLayout(tambahHewanLayout);
        tambahHewanLayout.setHorizontalGroup(
            tambahHewanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tambahHewanLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tambahHewanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel68, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(171, Short.MAX_VALUE))
        );
        tambahHewanLayout.setVerticalGroup(
            tambahHewanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tambahHewanLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel68, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addGroup(tambahHewanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(290, Short.MAX_VALUE))
        );

        mainPanel8.add(tambahHewan, "card2");

        CariHewan.setBackground(new java.awt.Color(99, 175, 241));

        jLabel37.setBackground(new java.awt.Color(0, 255, 255));
        jLabel37.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(0, 0, 0));
        jLabel37.setText("Cari Jenis Hewan");

        btnSearchJenisHewan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/search-icon.png"))); // NOI18N
        btnSearchJenisHewan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSearchJenisHewanMouseClicked(evt);
            }
        });

        jPanel15.setBackground(new java.awt.Color(99, 175, 241));
        jPanel15.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11))); // NOI18N

        btnSimpanHewan.setText("SIMPAN");
        btnSimpanHewan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanHewanActionPerformed(evt);
            }
        });

        btlHapusHewan.setText("HAPUS");
        btlHapusHewan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btlHapusHewanActionPerformed(evt);
            }
        });

        txtCariNamaHwn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCariNamaHwnActionPerformed(evt);
            }
        });

        jLabel84.setFont(new java.awt.Font("Clarendon BT", 0, 18)); // NOI18N
        jLabel84.setForeground(new java.awt.Color(0, 0, 0));
        jLabel84.setText("Jenis Hewan");

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(btlHapusHewan, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnSimpanHewan, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                        .addComponent(jLabel84)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtCariNamaHwn, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35))))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCariNamaHwn, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel84, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(170, 170, 170)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btlHapusHewan)
                    .addComponent(btnSimpanHewan))
                .addContainerGap(115, Short.MAX_VALUE))
        );

        jComboBoxHewan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jComboBoxHewanMouseClicked(evt);
            }
        });
        jComboBoxHewan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxHewanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout CariHewanLayout = new javax.swing.GroupLayout(CariHewan);
        CariHewan.setLayout(CariHewanLayout);
        CariHewanLayout.setHorizontalGroup(
            CariHewanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CariHewanLayout.createSequentialGroup()
                .addGroup(CariHewanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(CariHewanLayout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(CariHewanLayout.createSequentialGroup()
                        .addGap(117, 117, 117)
                        .addComponent(btnSearchJenisHewan)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(CariHewanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jComboBoxHewan, javax.swing.GroupLayout.PREFERRED_SIZE, 427, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(561, Short.MAX_VALUE))
        );
        CariHewanLayout.setVerticalGroup(
            CariHewanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CariHewanLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(CariHewanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnSearchJenisHewan)
                    .addComponent(jComboBoxHewan, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(290, 290, 290))
        );

        mainPanel8.add(CariHewan, "card3");

        javax.swing.GroupLayout TampilHewanLayout = new javax.swing.GroupLayout(TampilHewan);
        TampilHewan.setLayout(TampilHewanLayout);
        TampilHewanLayout.setHorizontalGroup(
            TampilHewanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1149, Short.MAX_VALUE)
        );
        TampilHewanLayout.setVerticalGroup(
            TampilHewanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 788, Short.MAX_VALUE)
        );

        mainPanel8.add(TampilHewan, "card4");

        javax.swing.GroupLayout dataHewanLayout = new javax.swing.GroupLayout(dataHewan);
        dataHewan.setLayout(dataHewanLayout);
        dataHewanLayout.setHorizontalGroup(
            dataHewanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dataHewanLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(dataHewanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(mainPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        dataHewanLayout.setVerticalGroup(
            dataHewanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dataHewanLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(mainPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        MainPanel.add(dataHewan, "card6");

        dataUkuran.setBackground(new java.awt.Color(255, 255, 255));

        jPanel17.setBackground(new java.awt.Color(99, 175, 241));

        btnTambahUkuran.setBackground(new java.awt.Color(51, 255, 255));
        btnTambahUkuran.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnTambahUkuran.setForeground(new java.awt.Color(0, 0, 0));
        btnTambahUkuran.setText("Tambah Ukuran");
        btnTambahUkuran.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTambahUkuranActionPerformed(evt);
            }
        });

        btnHelp3.setText("Help");
        btnHelp3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHelp3ActionPerformed(evt);
            }
        });

        btnTampilLyn1.setBackground(new java.awt.Color(51, 255, 255));
        btnTampilLyn1.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnTampilLyn1.setForeground(new java.awt.Color(0, 0, 0));
        btnTampilLyn1.setText("Tampil Seluruh");
        btnTampilLyn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTampilLyn1ActionPerformed(evt);
            }
        });

        btnCariUkuran.setBackground(new java.awt.Color(51, 255, 255));
        btnCariUkuran.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnCariUkuran.setForeground(new java.awt.Color(0, 0, 0));
        btnCariUkuran.setText("Cari Ukuran");
        btnCariUkuran.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariUkuranActionPerformed(evt);
            }
        });

        jLabel28.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 24)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(0, 0, 0));
        jLabel28.setText("MENGELOLA UKURAN KOEVEE PET SHOP");

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addComponent(btnTambahUkuran)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnCariUkuran, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnTampilLyn1)
                        .addGap(64, 64, 64)
                        .addComponent(btnHelp3))
                    .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 389, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTambahUkuran)
                    .addComponent(btnCariUkuran)
                    .addComponent(btnHelp3)
                    .addComponent(btnTampilLyn1))
                .addContainerGap())
        );

        mainPanel10.setLayout(new java.awt.CardLayout());

        cariUkuranHewan.setBackground(new java.awt.Color(99, 175, 241));

        btnCariLayanan1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/search-icon.png"))); // NOI18N
        btnCariLayanan1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCariLayanan1MouseClicked(evt);
            }
        });

        jLabel33.setBackground(new java.awt.Color(0, 255, 255));
        jLabel33.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(0, 255, 255));
        jLabel33.setText("Cari Layanan");

        jComboBoxLayanan1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jComboBoxLayanan1MouseClicked(evt);
            }
        });
        jComboBoxLayanan1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxLayanan1ActionPerformed(evt);
            }
        });

        jPanel18.setBackground(new java.awt.Color(99, 175, 241));
        jPanel18.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11))); // NOI18N

        btnSimpanHewan2.setText("SIMPAN");
        btnSimpanHewan2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanHewan2ActionPerformed(evt);
            }
        });

        btlHapusHewan2.setText("HAPUS");
        btlHapusHewan2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btlHapusHewan2ActionPerformed(evt);
            }
        });

        txtNamaLayananCari1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNamaLayananCari1ActionPerformed(evt);
            }
        });

        jLabel86.setFont(new java.awt.Font("Clarendon BT", 0, 18)); // NOI18N
        jLabel86.setForeground(new java.awt.Color(0, 0, 0));
        jLabel86.setText("Nama Layanan");

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addComponent(btlHapusHewan2, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnSimpanHewan2, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                        .addComponent(jLabel86)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtNamaLayananCari1, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNamaLayananCari1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel86, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(170, 170, 170)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btlHapusHewan2)
                    .addComponent(btnSimpanHewan2))
                .addContainerGap(115, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout cariUkuranHewanLayout = new javax.swing.GroupLayout(cariUkuranHewan);
        cariUkuranHewan.setLayout(cariUkuranHewanLayout);
        cariUkuranHewanLayout.setHorizontalGroup(
            cariUkuranHewanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cariUkuranHewanLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(95, 919, Short.MAX_VALUE))
            .addGroup(cariUkuranHewanLayout.createSequentialGroup()
                .addGap(150, 150, 150)
                .addComponent(btnCariLayanan1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(cariUkuranHewanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBoxLayanan1, javax.swing.GroupLayout.PREFERRED_SIZE, 427, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        cariUkuranHewanLayout.setVerticalGroup(
            cariUkuranHewanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cariUkuranHewanLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(cariUkuranHewanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(cariUkuranHewanLayout.createSequentialGroup()
                        .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(56, 56, 56)
                        .addComponent(jComboBoxLayanan1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnCariLayanan1))
                .addGap(18, 18, 18)
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(257, Short.MAX_VALUE))
        );

        mainPanel10.add(cariUkuranHewan, "card3");

        tampilUkuranHewan.setBackground(new java.awt.Color(99, 175, 241));

        javax.swing.GroupLayout tampilUkuranHewanLayout = new javax.swing.GroupLayout(tampilUkuranHewan);
        tampilUkuranHewan.setLayout(tampilUkuranHewanLayout);
        tampilUkuranHewanLayout.setHorizontalGroup(
            tampilUkuranHewanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1149, Short.MAX_VALUE)
        );
        tampilUkuranHewanLayout.setVerticalGroup(
            tampilUkuranHewanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 804, Short.MAX_VALUE)
        );

        mainPanel10.add(tampilUkuranHewan, "card4");

        tambahUkuranHewan.setBackground(new java.awt.Color(99, 175, 241));

        jPanel19.setBackground(new java.awt.Color(99, 175, 241));
        jPanel19.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11))); // NOI18N

        btnSimpanUkuran.setText("SIMPAN");
        btnSimpanUkuran.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanUkuranActionPerformed(evt);
            }
        });

        btlSimpan2.setText("BATAL");

        txtUkuranHewan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUkuranHewanActionPerformed(evt);
            }
        });

        jLabel87.setFont(new java.awt.Font("Clarendon BT", 0, 18)); // NOI18N
        jLabel87.setForeground(new java.awt.Color(0, 0, 0));
        jLabel87.setText("Ukuran Hewan");

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createSequentialGroup()
                        .addComponent(jLabel87)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtUkuranHewan, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26))
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addComponent(btlSimpan2, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnSimpanUkuran, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtUkuranHewan, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel87, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 199, Short.MAX_VALUE)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btlSimpan2)
                    .addComponent(btnSimpanUkuran))
                .addGap(76, 76, 76))
        );

        tabelUkuranHewan.setBackground(new java.awt.Color(255, 255, 255));
        tabelUkuranHewan.setFont(new java.awt.Font("Geometr212 BkCn BT", 1, 12)); // NOI18N
        tabelUkuranHewan.setForeground(new java.awt.Color(0, 0, 0));
        tabelUkuranHewan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "ID_Ukuran", "Ukuran Hewan"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelUkuranHewan.setGridColor(new java.awt.Color(0, 0, 0));
        jScrollPane8.setViewportView(tabelUkuranHewan);
        if (tabelUkuranHewan.getColumnModel().getColumnCount() > 0) {
            tabelUkuranHewan.getColumnModel().getColumn(0).setResizable(false);
            tabelUkuranHewan.getColumnModel().getColumn(1).setResizable(false);
        }

        jLabel88.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        jLabel88.setForeground(new java.awt.Color(0, 0, 0));
        jLabel88.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/menuTambahHewan.png"))); // NOI18N
        jLabel88.setText("TAMBAH UKURAN");

        javax.swing.GroupLayout tambahUkuranHewanLayout = new javax.swing.GroupLayout(tambahUkuranHewan);
        tambahUkuranHewan.setLayout(tambahUkuranHewanLayout);
        tambahUkuranHewanLayout.setHorizontalGroup(
            tambahUkuranHewanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tambahUkuranHewanLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(tambahUkuranHewanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tambahUkuranHewanLayout.createSequentialGroup()
                        .addComponent(jLabel88, javax.swing.GroupLayout.PREFERRED_SIZE, 426, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(tambahUkuranHewanLayout.createSequentialGroup()
                        .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(32, 32, 32)
                        .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 489, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(170, 170, 170))))
        );
        tambahUkuranHewanLayout.setVerticalGroup(
            tambahUkuranHewanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tambahUkuranHewanLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel88, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(tambahUkuranHewanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 384, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(272, Short.MAX_VALUE))
        );

        mainPanel10.add(tambahUkuranHewan, "card2");

        javax.swing.GroupLayout dataUkuranLayout = new javax.swing.GroupLayout(dataUkuran);
        dataUkuran.setLayout(dataUkuranLayout);
        dataUkuranLayout.setHorizontalGroup(
            dataUkuranLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(dataUkuranLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mainPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        dataUkuranLayout.setVerticalGroup(
            dataUkuranLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dataUkuranLayout.createSequentialGroup()
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(mainPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        MainPanel.add(dataUkuran, "card3");

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
                    .addComponent(MainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(backgroundLayout.createSequentialGroup()
                        .addComponent(menuHome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
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

    private void btnCariLayananMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCariLayananMouseClicked
        // TODO add your handling code here:
        String cari =  (String)jComboBoxLayanan.getSelectedItem();
        L=  AC.searchLayanan(cari);
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
         mainPanel5.removeAll();
         mainPanel5.repaint();
         mainPanel5.revalidate();
         mainPanel5.add(tambahProduk);
         mainPanel5.repaint(); 
         mainPanel5.revalidate();
    }//GEN-LAST:event_btnTambah1ActionPerformed

    private void btnHelp2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHelp2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnHelp2ActionPerformed

    private void btnTampil1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTampil1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnTampil1ActionPerformed

    private void btnCari1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCari1ActionPerformed
        // TODO add your handling code here:
         mainPanel5.removeAll();
         mainPanel5.repaint();
         mainPanel5.revalidate();
         mainPanel5.add(cariProduk);
         mainPanel5.repaint(); 
         mainPanel5.revalidate();
    }//GEN-LAST:event_btnCari1ActionPerformed

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

    private void btnSearchSupMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSearchSupMouseClicked
        // TODO add your handling code here:
        String cari = (String)jComboBoxSupllier.getSelectedItem();
        S= AC.searchSup(cari);
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
            
    }//GEN-LAST:event_btnSearchSupMouseClicked

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
            jComboBoxSupllier.addItem(txtNamSup.getText());
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

    private void btnTambahJenisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahJenisActionPerformed
        // TODO add your handling code here:
        mainPanel8.removeAll();
        mainPanel8.repaint();
        mainPanel8.revalidate();
        mainPanel8.add(tambahHewan);
        mainPanel8.repaint();
        mainPanel8.revalidate();
        tampilJenisHewan();

    }//GEN-LAST:event_btnTambahJenisActionPerformed

    private void btnSupHelp1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSupHelp1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSupHelp1ActionPerformed

    private void btnTampilSup1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTampilSup1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnTampilSup1ActionPerformed

    private void btnCariJenisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariJenisActionPerformed
        // TODO add your handling code here:
        mainPanel8.removeAll();
        mainPanel8.repaint();
        mainPanel8.revalidate();
        mainPanel8.add(CariHewan);
        mainPanel8.repaint();
        mainPanel8.revalidate();
        
    }//GEN-LAST:event_btnCariJenisActionPerformed

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        // TODO add your handling code here:
        try
        {
            JenisHewan jhs = new JenisHewan();
            jhs.setNama(txtNamaHewan.getText());
            AC.tambahJenisHewan(jhs);
            
            JOptionPane.showMessageDialog(this, "Data Hewan Berhasil Di Tambah ..");
            jComboBoxHewan.addItem(txtNamaHewan.getText());
            txtNamaHewan.setText(" ");
        
            tampilJenisHewan();
        }
        catch(Exception e)
        {
            System.out.println("gagal Menambahkan");
            System.out.println(e);
        }
        
    }//GEN-LAST:event_btnSimpanActionPerformed

    private void rdCustomerServiceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdCustomerServiceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdCustomerServiceActionPerformed

    private void rdKasirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdKasirActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdKasirActionPerformed

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

    private void txtNoTelpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNoTelpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNoTelpActionPerformed

    private void txtNamaPegawaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNamaPegawaiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNamaPegawaiActionPerformed

    private void btnSearchProdukMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSearchProdukMouseClicked
        // TODO add your handling code here:
        
        String nama = (String)jComboBoxCoba.getSelectedItem();
        Pr=  AC.searchPro(nama);
        if(Pr!=null)
        {
         
            txtCariNamaP.setText(Pr.getNama());
            txtSatunP.setText(Pr.getSatuan());
            String Satuan = String.valueOf(Pr.getJumlah());
            txtJumStokP.setText(Satuan);
            String harga= String.valueOf(Pr.getHarga());
            txtHargaProdukp.setText(harga);
            String minStok = String.valueOf(Pr.getMin_stok());
            txtMinStokP.setText(minStok);
            
        }
        else
        {
            JOptionPane.showMessageDialog(this, "Data Tidak Ditemukan");
        }
    }//GEN-LAST:event_btnSearchProdukMouseClicked

    private void btnSimpanProdukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanProdukActionPerformed
        // TODO add your handling code here:
       Produk Pr= new Produk();
      try
      {
          int Jum= Integer.parseInt(txtJumStok.getText());
          int Harga= Integer.parseInt(txtHargaProduk.getText());
          int minStok= Integer.parseInt(txtMinStok.getText());
          Pr.setNama(txtNamaProduk.getText());
          Pr.setSatuan(txtSatun.getText());
          Pr.setJumlah(Jum);
          Pr.setHarga(Harga);
          Pr.setMin_Stok(minStok);

          AC.tambahProduk(Pr);
        jComboBoxCoba.addItem(txtNamaProduk.getText());
          
          tampilProduk();
          setTextDataProduk();
      }catch(Exception e)
      {
          System.out.println(e);
      }
    }//GEN-LAST:event_btnSimpanProdukActionPerformed

    private void txtNamaProdukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNamaProdukActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNamaProdukActionPerformed

    private void txtMinStokActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMinStokActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMinStokActionPerformed

    private void txtJumStokActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtJumStokActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtJumStokActionPerformed

    private void txtHargaProdukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtHargaProdukActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHargaProdukActionPerformed

    private void btnSimpanProduk1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanProduk1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSimpanProduk1ActionPerformed

    private void txtMinStokPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMinStokPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMinStokPActionPerformed

    private void txtCariNamaPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCariNamaPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCariNamaPActionPerformed

    private void txtHargaProdukpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtHargaProdukpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHargaProdukpActionPerformed

    private void txtJumStokPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtJumStokPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtJumStokPActionPerformed

    private void btlHapusProdukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btlHapusProdukActionPerformed
        // TODO add your handling code here:
           
     try
     {
         String cari = (String)jComboBoxCoba.getSelectedItem();
         
         if(txtCariNamaP.getText().equalsIgnoreCase(""))
         {
             JOptionPane.showMessageDialog(this, "Tidak Ada Yang Di Hapus");
         }
         else
         {
            if (JOptionPane.showConfirmDialog(null, "Yakin Hapus?", "Yakin?",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION)
            {
                AC.deleteProduk(cari);

                JOptionPane.showMessageDialog(this, "Data Berhasil Dihapus..");
               
                txtCariNamaP.setText(" ");
                txtSatunP.setText(" ");
                txtJumStokP.setText(" ");
                txtHargaProdukp.setText(" ");
                txtMinStokP.setText(" ");
                temp= 1 ;
                jComboBoxCoba.removeItem(jComboBoxCoba.getSelectedItem());
                jComboBoxCoba.setSelectedIndex(0);
                tampilProduk();
                
                
                
           
            }  
         }
            
        
        setTextCariPegawai();

        
     }
     catch(Exception e)
     {
         System.out.println(e);
     }
    }//GEN-LAST:event_btlHapusProdukActionPerformed

    private void jComboBoxCobaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxCobaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxCobaActionPerformed

    private void jComboBoxCobaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboBoxCobaMouseClicked
        // TODO add your handling code here:
          
      
    }//GEN-LAST:event_jComboBoxCobaMouseClicked

    private void txtNamaLayananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNamaLayananActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNamaLayananActionPerformed

    private void btnSimpanLynActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanLynActionPerformed
        // TODO add your handling code here:
        try
        {
            namaLayanan();
            L= new Layanan();
            L.setNamaLayanan(txtNamaLayanan.getText());
            AC.tambahLayanan(L);
  
            JOptionPane.showMessageDialog(this, "Layanan Berhasil Ditambahkan");
            tampilLayanan();
            jComboBoxLayanan.addItem(txtNamaLayanan.getText());
        }catch(dataSama e)
        {
            JOptionPane.showMessageDialog(this, e.layananSama());
        }

    }//GEN-LAST:event_btnSimpanLynActionPerformed

    private void txtTelpSupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTelpSupActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTelpSupActionPerformed

    private void btnSimpanProduk2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanProduk2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSimpanProduk2ActionPerformed

    private void btlHapusSupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btlHapusSupActionPerformed
        // TODO add your handling code here:
        try
     {
         String cari = (String)jComboBoxSupllier.getSelectedItem();
         
         if(txtCariNamaSup.getText().equalsIgnoreCase(""))
         {
             JOptionPane.showMessageDialog(this, "Tidak Ada Yang Di Hapus");
         }
         else
         {
            if (JOptionPane.showConfirmDialog(null, "Yakin Hapus?", "Yakin?",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION)
            {
                AC.deleteSup(cari);

                JOptionPane.showMessageDialog(this, "Data Berhasil Dihapus..");
               
                txtCariNamaSup.setText(" ");
                txtCariAlaSup.setText(" ");
                txtCariTelpSup.setText(" ");
                temp= 2 ;
                jComboBoxSupllier.removeItem(jComboBoxSupllier.getSelectedItem());
                jComboBoxSupllier.setSelectedIndex(0);
                tampliSuplier();
                
                
           
            }  
         }
            
        
        setTextCariPegawai();
     }
     catch(Exception e)
     {
         System.out.println("Gagal Hapus");
         System.out.println(e);
     }
    }//GEN-LAST:event_btlHapusSupActionPerformed

    private void txtCariNamaSupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCariNamaSupActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCariNamaSupActionPerformed

    private void txtCariTelpSupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCariTelpSupActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCariTelpSupActionPerformed

    private void jComboBoxSupllierMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboBoxSupllierMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxSupllierMouseClicked

    private void jComboBoxSupllierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxSupllierActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxSupllierActionPerformed

    private void btnSearchJenisHewanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSearchJenisHewanMouseClicked
        // TODO add your handling code here:
        String cari = (String)jComboBoxHewan.getSelectedItem();
        jh = AC.searchJenisHewan(cari);
        if(jh!=null)
        {
            txtCariNamaHwn.setText(jh.getnNama());
            tampilJenisHewan();
        }
    }//GEN-LAST:event_btnSearchJenisHewanMouseClicked

    private void btnSimpanHewanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanHewanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSimpanHewanActionPerformed

    private void btlHapusHewanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btlHapusHewanActionPerformed
        // TODO add your handling code here:
          try
         {
            String cari = (String)jComboBoxHewan.getSelectedItem();
         
         if(txtCariNamaHwn.getText().equalsIgnoreCase(""))
         {
             JOptionPane.showMessageDialog(this, "Tidak Ada Yang Di Hapus");
         }
         else
         {
            if (JOptionPane.showConfirmDialog(null, "Yakin Hapus?", "Yakin?",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION)
            {
                AC.deleteJenisHewan(cari);

                JOptionPane.showMessageDialog(this, "Data Berhasil Dihapus..");
               
                txtCariNamaHwn.setText(" ");
                temp= 3 ;
                jComboBoxHewan.removeItem(jComboBoxHewan.getSelectedItem());
                jComboBoxHewan.setSelectedIndex(0);
                tampilJenisHewan();
                
                
           
            }  
         }
            
        
        setTextCariPegawai();
     }
     catch(Exception e)
     {
         System.out.println("Gagal Hapus");
         System.out.println(e);
     }
        
    }//GEN-LAST:event_btlHapusHewanActionPerformed

    private void txtCariNamaHwnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCariNamaHwnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCariNamaHwnActionPerformed

    private void jComboBoxHewanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboBoxHewanMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxHewanMouseClicked

    private void jComboBoxHewanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxHewanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxHewanActionPerformed

    private void jComboBoxLayananMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboBoxLayananMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxLayananMouseClicked

    private void jComboBoxLayananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxLayananActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxLayananActionPerformed

    private void btnSimpanHewan1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanHewan1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSimpanHewan1ActionPerformed

    private void btlHapusHewan1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btlHapusHewan1ActionPerformed
        // TODO add your handling code here:
          try
         {
         String cari = (String)jComboBoxLayanan.getSelectedItem();
         
         if(txtNamaLayananCari.getText().equalsIgnoreCase(""))
         {
             JOptionPane.showMessageDialog(this, "Tidak Ada Yang Di Hapus");
         }
         else
         {
            if (JOptionPane.showConfirmDialog(null, "Yakin Hapus?", "Yakin?",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION)
            {
                AC.deleteLayanan(cari);

                JOptionPane.showMessageDialog(this, "Data Berhasil Dihapus..");
               
                txtNamaLayananCari.setText(" ");
                temp= 4 ;
                jComboBoxLayanan.removeItem(jComboBoxLayanan.getSelectedItem());
                jComboBoxLayanan.setSelectedIndex(0);
                
                tampilLayanan();
                
                
           
            }  
         }
            
        
        setTextCariPegawai();
     }
     catch(Exception e)
     {
         System.out.println("Gagal Hapus");
         System.out.println(e);
     }
    }//GEN-LAST:event_btlHapusHewan1ActionPerformed

    private void txtNamaLayananCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNamaLayananCariActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNamaLayananCariActionPerformed

    private void btnUkuranActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUkuranActionPerformed
        // TODO add your handling code here:
        MainPanel.removeAll();
        MainPanel.repaint();
        MainPanel.revalidate();
        MainPanel.add(dataUkuran);
        MainPanel.repaint();
        MainPanel.revalidate();
    }//GEN-LAST:event_btnUkuranActionPerformed

    private void btnTambahUkuranActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahUkuranActionPerformed
        // TODO add your handling code here:
         mainPanel10.removeAll();
        mainPanel10.repaint();
        mainPanel10.revalidate();
        mainPanel10.add(tambahUkuranHewan);
        mainPanel10.repaint();
        mainPanel10.revalidate();
    }//GEN-LAST:event_btnTambahUkuranActionPerformed

    private void btnHelp3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHelp3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnHelp3ActionPerformed

    private void btnTampilLyn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTampilLyn1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnTampilLyn1ActionPerformed

    private void btnCariUkuranActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariUkuranActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCariUkuranActionPerformed

    private void btnCariLayanan1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCariLayanan1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCariLayanan1MouseClicked

    private void jComboBoxLayanan1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboBoxLayanan1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxLayanan1MouseClicked

    private void jComboBoxLayanan1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxLayanan1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxLayanan1ActionPerformed

    private void btnSimpanHewan2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanHewan2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSimpanHewan2ActionPerformed

    private void btlHapusHewan2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btlHapusHewan2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btlHapusHewan2ActionPerformed

    private void txtNamaLayananCari1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNamaLayananCari1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNamaLayananCari1ActionPerformed

    private void btnSimpanUkuranActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanUkuranActionPerformed
        // TODO add your handling code here:
         try
        {
            cekDataUkuranKosong();
            UkuranHewan uh = new UkuranHewan();
            
            uh.setNama(txtUkuranHewan.getText());
                
            AC.tambahUkuranHewan(uh);
            setTextDataUkuran();
            tampilUkuranHewan();
            JOptionPane.showMessageDialog(this, "DATA UKURAN BERHASIL DISIMPAN");
        }
         catch(dataKosong dk)
         {
             JOptionPane.showMessageDialog(this, dk.message());
         }
         catch(Exception e)
        {
            System.out.println(e);
        }
       
    }//GEN-LAST:event_btnSimpanUkuranActionPerformed

    private void txtUkuranHewanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUkuranHewanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUkuranHewanActionPerformed

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
    private javax.swing.JPanel CariHewan;
    private javax.swing.JPanel CariSuplier;
    private javax.swing.JPanel HelpSuplier;
    private javax.swing.JPanel MainPanel;
    private javax.swing.JPanel TambahSuplier;
    private javax.swing.JPanel TampilHewan;
    private javax.swing.JPanel TampilSuplier;
    private javax.swing.JPanel background;
    private javax.swing.JPanel bantuan;
    private javax.swing.JButton btlHapusHewan;
    private javax.swing.JButton btlHapusHewan1;
    private javax.swing.JButton btlHapusHewan2;
    private javax.swing.JButton btlHapusProduk;
    private javax.swing.JButton btlHapusProduk1;
    private javax.swing.JButton btlHapusProduk2;
    private javax.swing.JButton btlHapusSup;
    private javax.swing.JButton btlSimpan;
    private javax.swing.JButton btlSimpan1;
    private javax.swing.JButton btlSimpan2;
    private javax.swing.JButton btnCari;
    private javax.swing.JButton btnCari1;
    private javax.swing.JButton btnCariJenis;
    private javax.swing.JLabel btnCariLayanan;
    private javax.swing.JLabel btnCariLayanan1;
    private javax.swing.JButton btnCariLyn;
    private javax.swing.JButton btnCariSup;
    private javax.swing.JButton btnCariUkuran;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnHelp;
    private javax.swing.JButton btnHelp1;
    private javax.swing.JButton btnHelp2;
    private javax.swing.JButton btnHelp3;
    private javax.swing.JButton btnHewan;
    private javax.swing.JButton btnLayanan;
    private javax.swing.JLabel btnLog;
    private javax.swing.JButton btnPegawai;
    private javax.swing.JButton btnProduk;
    private javax.swing.ButtonGroup btnRole;
    private javax.swing.JLabel btnSearch;
    private javax.swing.JLabel btnSearchJenisHewan;
    private javax.swing.JLabel btnSearchProduk;
    private javax.swing.JLabel btnSearchSup;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JButton btnSimpanEdit;
    private javax.swing.JButton btnSimpanHewan;
    private javax.swing.JButton btnSimpanHewan1;
    private javax.swing.JButton btnSimpanHewan2;
    private javax.swing.JButton btnSimpanLyn;
    private javax.swing.JButton btnSimpanPegawai;
    private javax.swing.JButton btnSimpanProduk;
    private javax.swing.JButton btnSimpanProduk1;
    private javax.swing.JButton btnSimpanProduk2;
    private javax.swing.JButton btnSimpanProduk3;
    private javax.swing.JButton btnSimpanProduk4;
    private javax.swing.JButton btnSimpanSuplier;
    private javax.swing.JButton btnSimpanUkuran;
    private javax.swing.JButton btnSup;
    private javax.swing.JButton btnSupHelp;
    private javax.swing.JButton btnSupHelp1;
    private javax.swing.JButton btnTambah;
    private javax.swing.JButton btnTambah1;
    private javax.swing.JButton btnTambahJenis;
    private javax.swing.JButton btnTambahLyn;
    private javax.swing.JButton btnTambahSup;
    private javax.swing.JButton btnTambahUkuran;
    private javax.swing.JButton btnTampil;
    private javax.swing.JButton btnTampil1;
    private javax.swing.JButton btnTampilLyn;
    private javax.swing.JButton btnTampilLyn1;
    private javax.swing.JButton btnTampilSup;
    private javax.swing.JButton btnTampilSup1;
    private javax.swing.JButton btnUkuran;
    private javax.swing.JPanel cariLayanan;
    private javax.swing.JPanel cariPegawai;
    private javax.swing.JPanel cariProduk;
    private javax.swing.JPanel cariUkuranHewan;
    private javax.swing.JPanel dataHewan;
    private javax.swing.JPanel dataLayanan;
    private javax.swing.JPanel dataPegawai;
    private javax.swing.JPanel dataProduk;
    private javax.swing.JPanel dataSupplier;
    private javax.swing.JPanel dataUkuran;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox<String> jComboBoxCoba;
    private javax.swing.JComboBox<String> jComboBoxHewan;
    private javax.swing.JComboBox<String> jComboBoxLayanan;
    private javax.swing.JComboBox<String> jComboBoxLayanan1;
    private javax.swing.JComboBox<String> jComboBoxSupllier;
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
    private javax.swing.JLabel jLabel23;
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
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JLabel judul;
    private javax.swing.JPanel mainPanel10;
    private javax.swing.JPanel mainPanel2;
    private javax.swing.JPanel mainPanel3;
    private javax.swing.JPanel mainPanel4;
    private javax.swing.JPanel mainPanel5;
    private javax.swing.JPanel mainPanel8;
    private javax.swing.JPanel menuHome;
    private javax.swing.JRadioButton rdCustomerService;
    private javax.swing.JRadioButton rdKasir;
    private javax.swing.JTable tabelHewan;
    private javax.swing.JTable tabelLayanan;
    private javax.swing.JTable tabelProduk;
    private javax.swing.JTable tabelUkuranHewan;
    private javax.swing.JTable tablePegawai;
    private javax.swing.JTable tablePegawai1;
    private javax.swing.JTable tableSupTambahData;
    private javax.swing.JPanel tambahHewan;
    private javax.swing.JPanel tambahLayanan;
    private javax.swing.JPanel tambahPegawai;
    private javax.swing.JPanel tambahProduk;
    private javax.swing.JPanel tambahUkuranHewan;
    private javax.swing.JPanel tampilLayanan;
    private javax.swing.JPanel tampilProduk;
    private javax.swing.JPanel tampilSeluruh;
    private javax.swing.JPanel tampilUkuranHewan;
    private javax.swing.JTextField txtAlamat;
    private javax.swing.JTextField txtAlamatSup;
    private javax.swing.JTextField txtCari;
    private javax.swing.JTextField txtCariAlaSup;
    private javax.swing.JTextField txtCariAlamat;
    private javax.swing.JTextField txtCariNama;
    private javax.swing.JTextField txtCariNamaHwn;
    private javax.swing.JTextField txtCariNamaP;
    private javax.swing.JTextField txtCariNamaP1;
    private javax.swing.JTextField txtCariNamaP2;
    private javax.swing.JTextField txtCariNamaSup;
    private javax.swing.JTextField txtCariNoTlp;
    private javax.swing.JTextField txtCariPassword;
    private javax.swing.JTextField txtCariRole;
    private javax.swing.JTextField txtCariTanggalLahir;
    private javax.swing.JTextField txtCariTelpSup;
    private javax.swing.JTextField txtCariUserName;
    private javax.swing.JTextField txtHargaProduk;
    private javax.swing.JTextField txtHargaProdukp;
    private javax.swing.JTextField txtHargaProdukp1;
    private javax.swing.JTextField txtHargaProdukp2;
    private javax.swing.JTextField txtJumStok;
    private javax.swing.JTextField txtJumStokP;
    private javax.swing.JTextField txtJumStokP1;
    private javax.swing.JTextField txtJumStokP2;
    private javax.swing.JTextField txtMinStok;
    private javax.swing.JTextField txtMinStokP;
    private javax.swing.JTextField txtMinStokP1;
    private javax.swing.JTextField txtMinStokP2;
    private javax.swing.JTextField txtNamSup;
    private javax.swing.JTextField txtNamaHewan;
    private javax.swing.JTextField txtNamaLayanan;
    private javax.swing.JTextField txtNamaLayananCari;
    private javax.swing.JTextField txtNamaLayananCari1;
    private javax.swing.JTextField txtNamaPegawai;
    private javax.swing.JTextField txtNamaProduk;
    private javax.swing.JTextField txtNoTelp;
    private javax.swing.JTextField txtPassword;
    private javax.swing.JTextField txtSatun;
    private javax.swing.JTextField txtSatunP;
    private javax.swing.JTextField txtSatunP1;
    private javax.swing.JTextField txtSatunP2;
    private javax.swing.JLabel txtTanggalHariIni;
    private com.toedter.calendar.JDateChooser txtTanggalLahir;
    private javax.swing.JTextField txtTelpSup;
    private javax.swing.JTextField txtUkuranHewan;
    private javax.swing.JTextField txtUserName;
    // End of variables declaration//GEN-END:variables
}
