/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.util.Calendar;
import java.util.GregorianCalendar;

import Controller.AdminControl ;
import DAO.AdminDao;
import static DAO.AdminDao.con;
import Model.Harga_Layanan;
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
import exception.PanjangData;
import exception.dataKosong;
import exception.dataSama;
import java.awt.EventQueue;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;
import org.apache.commons.io.FileUtils;
import static org.hsqldb.lib.tar.TarHeaderField.uid;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import sun.nio.cs.ext.PCK;
/**
 *
 * @author ACER
 */
public class MenuAdmin extends javax.swing.JFrame {
    File file ;
    String filename= null ;
    byte[] person_image=null;
    JFileChooser jfc;
    JenisHewan jh ;
    String tampung ;
    Suplier S;
    Pegawai  P ;
    Layanan L ;
    AdminControl AC ;
    Produk Pr ;
    ArrayList nama = new ArrayList();
    ArrayList namaProduk = new ArrayList();
    ArrayList namaLayanan = new  ArrayList();
    ArrayList jenisHewan = new ArrayList();
    UkuranHewan Uh;
    Harga_Layanan HL;
    public  DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MMMM/dd ");  
    public  LocalDateTime now = LocalDateTime.now();  
    int id =  LoginSession.getIduser()  ;   
    public String idSesion = String.valueOf(id);
    
    public AdminDao AD  = new AdminDao();
    
    public int temp = 0 ;
    private Image image;
    /**
     * Creates new form MenuAdmin
     */
    DefaultTableModel tabelModel,tabelModel1, tabelModel2,tabelModel4,tableModel5,tabelModel6,tabelModel7, tabelModel8,tabelModel9 ;
    public MenuAdmin() {
        
        initComponents();
      tampilNamaProduk();
        PlaceHolder holder0 = new PlaceHolder(txtNamaLayanan,"Masukan Nama Layanan");
        jScrollPane6.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane6.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane3.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane3.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane4.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane8.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        PlaceHolder holder1 = new PlaceHolder(txtCari, "Masukkan User Name");
 
        PlaceHolder holder4 = new PlaceHolder(txtNamaHewan,"Masukan Nama Hewan");
//       jComboBoxCoba.addItem("");
        PlaceHolder holder5 = new PlaceHolder(mencariNamaProduk,"Masukan Nama Produk");
        PlaceHolder holder6 = new PlaceHolder(cariJenisHewan,"Masukan Nama Hewan");
        jComboBoxSupllier.addItem("-Pilih Nama Supplier");
//        jComboBoxHewan.addItem("-Pilih Jenis Hewan");
//        jComboBoxLayanan.addItem("-Pilih Nama Layanan");
        jComboBoxUkuran.addItem("-Pilih Ukuran Hewan");
        jComboBoxNamaLayanan.addItem(("-Pilih Nama Layanan"));
        jComboBoxNamaLayanan1.addItem(" ");
        jComboBoxUkuranHewan1.addItem(" ");
        jComboBoxUkuranHewan.addItem("-Pilih Ukuran Hewan");
      
        txtTanggalHariIni.setText(dtf.format(now));
     
        holder1.setCursiva(true);
        holder0.setCursiva(true);
        holder4.setCursiva(true);
        setTitle("Menu Admin");
        disspegawai(false);

        tampilComboBoxCariLayanan();
        tampilComboBoxSupllier();
        tampilComboBoxUkuran();
        tampilComboBoxCariHewan();
        tambahHargaLayanan();
        tambahHargaUkuran();
        tampilComboBoxHargaLayanan();
        tampilUserNamePegawai();
//        jComboBoxCoba.setVisible(false);
        AC = new AdminControl();
        txtAlamat.getBorder();
        tabelModel = (DefaultTableModel) tablePegawai.getModel();
        tabelModel1 =(DefaultTableModel) tableSupTambahData.getModel();
        tabelModel2 = (DefaultTableModel) tabelLayanan.getModel();
        tabelModel4 = (DefaultTableModel) tabelHewan.getModel() ;
        tableModel5 = (DefaultTableModel) tabelProduk.getModel();
        tabelModel6 = (DefaultTableModel) tabelUkuranHewan.getModel();
        tabelModel7 = (DefaultTableModel) tabelHargaLayanan.getModel();
        tabelModel9 = (DefaultTableModel) tabelProdukTampilSeluruh.getModel();
        tampliSuplier();       
        tampilPegawai();
        tampilLayanan();
        tampilJenisHewan();
        tampilProduk();
        tampilUkuranHewan();
        tampilLayananText();
       
        AutoCompleteDecorator.decorate(jComboBoxNamaLayanan);
        AutoCompleteDecorator.decorate(jComboBoxUkuranHewan);
        
        tampilHargaLayanan();
        tampilProdukSeluruh();
        tampilJenisHewanText();
        atur(tabelUkuranHewan , new int[] {100, 200,100, 150, 100,150});
        atur(tableSupTambahData, new int[]{100,250,200,150, 150,150,150,150});
        atur(tabelLayanan , new int[]{100,150,90,150, 90, 150});
        atur(tablePegawai, new int []{100,250,90,200,100,150,90,150,90,150} );
        atur(tabelHewan , new int[]{100,150,90,150, 90, 150});
        atur(tabelProduk, new int[]{100,150,100, 150, 150, 150, 150, 150, 150, 150});
                atur(tabelProdukTampilSeluruh, new int[]{100,150,100, 150, 150, 150, 150, 150, 150, 150,150,150});
//                        String[] dataField={id,nama_Layanan,Ukuran,harga1,createby , createAt,modifby,modifat};
        atur(tabelHargaLayanan, new int[]{100,150,150,200,150,150,150,150});
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
    public void addTabelHargaLayanan(Harga_Layanan h)
    {
        Vector data= new Vector();
        data.add(h.getLayanan());
        data.add(h.getUkuran());
        data.add(h.getHarga());
        tabelModel7.addRow(data);
    }
    public void addTableTampilSeluruhProduk(Produk Pb)
    {
           Vector data = new Vector();
        data.add(Pb.getIdProduk());
        data.add(Pb.getNama());
        data.add(Pb.getSatuan());
        data.add(Pb.getJumlah());
        data.add(Pb.getHarga());
        data.add(Pb.getMin_stok());
        data.add(Pb.getCreate_by());
        data.add(Pb.getcREATE_aT());
        data.add(Pb.getModified_by());
        data.add(Pb.getModifie_at());
        data.add(Pb.getDeleteBy());
        data.add(Pb.getDeleteat());
        tabelModel9.addRow(data);
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
        data.add(Pb.getCreate_by());
        data.add(Pb.getcREATE_aT());
        data.add(Pb.getModified_by());
        data.add(Pb.getModifie_at());
        tableModel5.addRow(data);
    }
    public void addTabelUkuran(UkuranHewan Uh)
    {
        Vector data = new Vector();
        data.add(Uh.getID());
        data.add(Uh.getNama());
        data.add(Uh.getCreateBy());
        data.add(Uh.getCreateAt());
        data.add(Uh.getModif_by());
                data.add(Uh.getModif_at());
        tabelModel6.addRow(data);
    }
    public void addTableHewa(JenisHewan JH)
    {
       
        Vector data = new Vector();
        data.add(JH.getId());
        data.add(JH.getnNama());
        data.add(JH.getCreateBy());
        data.add(JH.getCreateAt());
                data.add(JH.getModified_by());
        data.add(JH.getModifiedAT());

        tabelModel4.addRow(data);
    }
    public void addTableLayanan(Layanan L)
    {
        
        Vector data = new Vector();
        data.add(L.getIdLayanan());
        data.add(L.getNamaLayanan());
        data.add(L.getCreate_by());
        data.add(L.getCreate_at());
        data.add(L.getModified_by());
        data.add(L.getModified_at());
        tabelModel2.addRow(data);
    }
    public void cekAngkaTelpSup() throws CekAngka
    {
        if(txtTelpSup.getText().matches("[0-9]*"))
        {
            System.out.println("ok");
        }
        else 
        {
            throw  new CekAngka();
        }
    }
    public void cekHurufHewan() throws CekHuruf
    {
        if(txtNamaHewan.getText().matches("[0-9]*"))
         {
             throw new CekHuruf();
         }
         else
         {
             System.out.println("okk");
         }
    }
    public void cekDataHewan()throws dataKosong
    {
        if(txtNamaHewan.getText().length()<1 || txtNamaHewan.getText().equalsIgnoreCase("Masukan Nama Hewan"))
        {
            throw new dataKosong();
        }
    }
    public void noTelpSup() throws PanjangData
    {
        if(txtTelpSup.getText().length()<12 || txtTelpSup.getText().length()>12)
        {
            throw new PanjangData();
        }
        
    }
    public void addTableSuplier(Suplier S)
    {
        Vector data = new Vector();
        data.add(S.getId());
        data.add(S.getNama());
        data.add(S.getAlamat());
        data.add(S.getTelp());
        data.add(S.getCreateBy());
        data.add(S.getCreateAt());
        data.add(S.getModifiedBy());
        data.add(S.getModified_at());
         
        tabelModel1.addRow(data);
    }
    public void exceptionDataLayanan()throws dataKosong
    {
        if(txtNamaLayanan.getText().length()<1 || txtNamaLayanan.getText().equalsIgnoreCase("Masukan Nama Layanan"))
        {
            throw  new dataKosong();
        }
    }
     
    public void ExceptionDataSuplier() throws dataKosong{
        if(txtNamSup.getText().length()<1 || txtAlamatSup.getText().length()<1 || txtTelpSup.getText().length()<1)
        {
            throw  new dataKosong();
        }
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
    
    public void tampilNamaProduk()
    {
        AD.makeConnection();
        try 
        {
            String sql = "Select nama from produk where aktif = 1";
            Class.forName("com.mysql.jdbc.Driver");
            Statement stm = AD.GETcon().createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next())
            {
                String NamaProduk = rs.getString("nama");
                namaProduk.add(NamaProduk);
            }
            rs.close();
            stm.close();
            
        }
        catch(Exception e )
        {
            System.out.println(e);
        }
        AD.closeConnection();
    }
    public void tampilJenisHewanText()
    {
        AD.makeConnection();
        try
        {
            String sql ="Select nama from Jenis_Hewan where aktif = 1 ";
            Class.forName("com.mysql.jdbc.Driver");
            Statement stm = AD.GETcon().createStatement();
            ResultSet rs= stm.executeQuery(sql);
           
                    while(rs.next())
            {
                String jenisHewan = rs.getString("nama");
                this.jenisHewan.add(jenisHewan);
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

    public void tampilLayananText()
    {
        AD.makeConnection();
        try
        {
            String sql = "Select nama from Layanan where aktif = 1";
            Class.forName("com.mysql.jdbc.Driver");
            Statement stm = AD.GETcon().createStatement();
            ResultSet rs= stm.executeQuery(sql);
            while(rs.next())
            {
                String lyn = rs.getString("nama");
                namaLayanan.add(lyn);
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
    public void tampilUserNamePegawai()
    {AD.makeConnection();
        try
        {
            
            String sql = "Select username from Pegawai where aktif = 1 ";
            Class.forName("com.mysql.jdbc.Driver");
            Statement stm = AD.GETcon().createStatement();
            ResultSet rs= stm.executeQuery(sql);
            while (rs.next())
                    {
                        String Nama = rs.getString("username");
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
    
    public void autoCompletNamaLayanan(String nama)
    {
        String compelet = "";
        int start = nama.length();
        int last = nama.length();
        int a ;
        for(a= 0; a<namaLayanan.size(); a++)
        {
            if (namaLayanan.get(a).toString().startsWith(nama)) {
                
                compelet = namaLayanan.get(a).toString();
                last = compelet.length() ;
                break ;
            }
        }
        
        if (last > start ) {
            
            txtCariLayanan.setText(compelet);
            txtCariLayanan.setCaretPosition(last);
            txtCariLayanan.moveCaretPosition(start);
        }
    }
    public void autoCompletJenisHewan (String nama )
    {
        String complet =" ";
        int start = nama.length() ;
        int last = nama.length();
        int a ;
                for(a= 0 ; a<jenisHewan.size() ; a++)
                {
                    if (jenisHewan.get(a).toString().startsWith(nama )) {
                        complet= jenisHewan.get(a).toString();
                        last = complet.length() ;
                        break ;
                    }
                    
                }
                if (last > start ) {
                    cariJenisHewan.setText(complet);
                    cariJenisHewan.setCaretPosition(last);
                    cariJenisHewan.moveCaretPosition(start);
                
        }
            
        
    }
    public void autoCompletNamaProduk(String produ)
    {
        String complete= " ";
        int start = produ.length() ;
        int last = produ.length();
        int a ;
        for (a=0 ; a <namaProduk.size(); a++) {
            if (namaProduk.get(a).toString().startsWith(produ)) {
                complete= namaProduk.get(a).toString();
                last= complete.length();
                break;
                
            }
        
            
        }
        if (last>start ) {
            
            mencariNamaProduk.setText(complete);
            mencariNamaProduk.setCaretPosition(last);
            mencariNamaProduk.moveCaretPosition(start);
        }
    }
    public void autoCompletUserPegawai(String username)
    {
    String complete =" ";
    int start = username.length() ;
    int last = username.length();
    int a ;
        for (a=0 ; a <nama.size(); a++) {
            if (nama.get(a).toString().startsWith(username)) {
                complete= nama.get(a).toString();
                last= complete.length();
                break;
                
            }
        
            
        }
        if (last>start ) {
            
            txtCari.setText(complete);
            txtCari.setCaretPosition(last);
            txtCari.moveCaretPosition(start);
        }
    }
    public void tambahHargaUkuran()
    {
        AD.makeConnection();
        try
        {
            
            String sql = "Select nama from UKURAN_HEWAN where aktif=1";
                Class.forName("com.mysql.jdbc.Driver"); 
                Statement st = AD.GETcon().createStatement();
                ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
           
                jComboBoxUkuranHewan.addItem(rs.getString("nama"));
                jComboBoxUkuranHewan1.addItem(rs.getString("nama"));
            }

            } catch (Exception e) {
            }
        AD.closeConnection();
    
    }
    public void cariHargaLayanan()
    {
        
    }
    public void tambahHargaLayanan()
    {
        AD.makeConnection();
        
            try {
                String sql = "Select nama from Layanan where aktif=1";
                Class.forName("com.mysql.jdbc.Driver"); 
                Statement st = AD.GETcon().createStatement();
                ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
           
                jComboBoxNamaLayanan.addItem(rs.getString("nama"));
                jComboBoxNamaLayanan1.addItem(rs.getString("nama"));
            }

            } catch (Exception e) {
            }
        AD.closeConnection();
    }
    public void tampilComboBoxUkuran()
    {
        AD.makeConnection();
        try
        {
            String sql= "Select nama from Ukuran_Hewan where aktif =1 ";
            Class.forName("com.mysql.jdbc.Driver"); 
            Statement st = AD.GETcon().createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
           
                jComboBoxUkuran.addItem(rs.getString("nama"));

            }

        }
        catch(Exception e)
        {
            System.out.println("Gagal Menampilkan");
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
           
//                jComboBoxNamaLayanan.addItem(rs.getString("nama"));

            }

        }
        catch(Exception e)
        {
            System.out.println("Gagal");
            System.out.println(e);
        }
        AD.closeConnection();
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
           
//                jComboBoxHewan.addItem(rs.getString("nama"));

            }

            
            } catch (Exception e) {
                System.out.println(e);
            }
       AD.closeConnection();
    }
    public void tampilComboBoxHargaLayanan()
    {
        try {
            AD.makeConnection();
            String sql = "SELECT A.ID_HARGA_LAYANAN FROM harga_layanan AS A " +
                        "INNER JOIN layanan AS B ON A.ID_LAYANAN = B.ID_LAYANAN\n" +
                        "WHERE B.AKTIF = 1  AND A.AKTIF=1 ORDER BY ID_HARGA_LAYANAN ASC";
              Class.forName("com.mysql.jdbc.Driver"); 
            Statement st = AD.GETcon().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next())
            {
//               sd
            }
        } catch (Exception e) {
            System.out.println("gagal");
            System.out.println(e);
        }
          AD.closeConnection();
    }
//    public void tampilComboBox()
//    {
//        
//
//       try {
//            AD.makeConnection();
//            String query = "SELECT NAMA FROM PRODUK WHERE AKTIF=1 ORDER BY NAMA ASC";
//            Class.forName("com.mysql.jdbc.Driver"); 
//            Statement st = AD.GETcon().createStatement();
//            ResultSet rs = st.executeQuery(query);
//
//            while (rs.next()) {
//              
//                jComboBoxCoba.addItem(rs.getString(1));
//              
//            }
//
//
//
//            } catch (Exception e) {
//                System.out.println(e);
//            }
//       AD.closeConnection();
//}
    public void addTablePegawai(Pegawai P) {
       Vector data = new Vector();

       data.add(P.getId()); 
       data.add(P.getNamaPegawai());
       data.add(P.getUserName());
       data.add(P.getAlamat());
       data.add(P.getTglLahir());
       data.add(P.getRole());
       data.add(P.getCreateBy());
       data.add(P.GetCreateAt());
       data.add(P.getModified_by());
       data.add(P.getModified_at());


       tabelModel.addRow(data);
   }
    public void ExceptionDataProdu()throws dataKosong 
    {
        
        if(txtNamaProduk.getText().length()<1 || txtSatun.getText().length()<1 || txtJumStok.getText().length()<1 || txtHargaProduk.getText().length()<1
               || txtMinStok.getText().length()<1)
        {
            throw new dataKosong();
        }
    }
    public void exceptiondataKosong() throws dataKosong
    {   
    
        if(btnRole.getSelection()==null|| txtTanggalLahir.getDate()==null||txtNamaPegawai.getText().length()<1 || txtNoTelp.getText().length()<1 || txtAlamat.getText().length()<1 
               || txtUserName.getText().length()<1 ||  txtPassword.getText().length()<1)
        {
            throw new dataKosong();
        }
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
     public void cekMinStok()throws CekAngka
     {
 
         if( txtMinStok.getText().matches("[0-9]*"))
        {
            System.out.println("ok");
        }

       else
       {
           throw new  CekAngka();
       }
        
     }
     public void cekHarha()throws CekAngka
     {
         if(txtHargaProduk.getText().matches("[0-9]*"))
        {
            System.out.println("ok");
        }
        

       else
       {
           throw new  CekAngka();
       }
        
     }
     public void cekJumlahStok() throws CekAngka
     {
        if(txtJumStok.getText().matches("[0-9]*")) 
          {
           System.out.println("ok");
           }
        else if(txtHargaProduk.getText().matches("[0-9]*"))
        {
            System.out.println("ok");
        }
        else if( txtMinStok.getText().matches("[0-9]*"))
        {
            System.out.println("ok");
        }

       else
       {
           throw new  CekAngka();
       }
         
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
            UH.setCreateBy(M.get(i).getCreateBy());
            UH.setCreateAt(M.get(i).getCreateAt());
            UH.setModified_At(M.get(i).getModif_at());
            UH.setModified_By(M.get(i).getModif_by());
            addTabelUkuran(UH);
        }
    }
    public void cekNomorTelephonePegawai() throws PanjangData
    {
        if(txtNoTelp.getText().length()<12 || txtNoTelp.getText().length()>12)
        {
            throw  new PanjangData();
        }
    }
    public void tampilProdukSeluruh()
    {
        int a = tabelModel9.getRowCount();
        
        for (int i = 0; i < a; i++) {
          tabelModel9.removeRow(0);  
            
        }
        Produk P ;
        List<Produk> M = AC.tampilDataProdukSeluruh();
        for (int i = 0; i <M.size(); i++) {
            
            P= new Produk();

            P.setId(M.get(i).getIdProduk());
            P.setNama(M.get(i).getNama());
            P.setSatuan(M.get(i).getSatuan());
            P.setJumlah(M.get(i).getJumlah());
            P.setHarga(M.get(i).getHarga());
            P.setMin_Stok(M.get(i).getMin_stok());
            P.setCreate_By(M.get(i).getCreate_by());
            P.setCreate_AT(M.get(i).getcREATE_aT());
           P.setModified_by(M.get(i).getModified_by());
           P.setModified_at(M.get(i).getModifie_at());
           P.setDelete_at(M.get(i).getDeleteat());
           P.setDelete_by(M.get(i).getDeleteBy());
            addTableTampilSeluruhProduk(P);
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
            P.setCreate_By(M.get(i).getCreate_by());
            P.setCreate_AT(M.get(i).getcREATE_aT());
           P.setModified_by(M.get(i).getModified_by());
           P.setModified_at(M.get(i).getModifie_at());
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
            L.setCreateBy(M.get(i).getCreateBy());
            L.setCreateAt(M.get(i).getCreateAt());
            L.setModified_at(M.get(i).getModifiedAT());
            L.setModified_by(M.get(i).getModified_by());
            addTableHewa(L);
        }
    }
    public void cekNamaSuplier() throws dataSama
    {
        S = AC.searchSup(txtNamSup.getText());
        if (S!=null ) {
            throw new dataSama();
        }
                
    }
    public void cekNamaHewan() throws dataSama
    {
        jh= AC.searchJenisHewan(txtNamaHewan.getText());
        if (jh!=null) {
          throw new dataSama();
            
        }
    }
    public void tampilLayanan()
    {
        int a = tabelModel2.getRowCount() ;
        for (int i = 0; i < a; i++) {
            tabelModel2.removeRow(0);
        }
      
        List<Layanan> M = AC.tampilLayanan();
       
        for (int i = 0; i < M.size(); i++) {
            
            L= new Layanan();
            L.setIdLayanan(M.get(i).getIdLayanan());
            L.setNamaLayanan(M.get(i).getNamaLayanan());
            L.setCreateBY(M.get(i).getCreate_by());
            L.setCreate_at(M.get(i).getCreate_at());
            L.setModified_by(M.get(i).getModified_by());
            L.setModified_at(M.get(i).getModified_at());

            addTableLayanan(L);
        }
    }
    public Harga_Layanan cariHargaLayanan(String id)
    {
         Harga_Layanan hl = null ;

        try {
                
            AD.makeConnection();
            String sql ="Select A.nama as Nama, b.nama as Ukuran,C.Harga as Harga,C.ID_Harga_Layanan from harga_layanan as C inner join layanan as A on C.id_layanan=A.id_Layanan inner join ukuran_hewan b on C.id_ukuran_hewan =b.id_ukuran_hewan where C.aktif=1 AND A.aktif=1 And C.id_harga_layanan= '"+id+"'"; 
            System.out.println("Tampil Harga Layanan");
           
            Statement stm = con.createStatement() ;
            ResultSet rs = stm.executeQuery(sql);
        if(rs!=null)
        {
            while(rs.next())
            {

              
                String namaLayanan= rs.getString("Nama");
                String x = rs.getString("Ukuran");
                hl = new  Harga_Layanan();
               txtNamaLayananHarga2.setText(rs.getString("Harga"));
               jComboBoxNamaLayanan1.setSelectedItem(namaLayanan);
               jComboBoxUkuranHewan1.setSelectedItem(x);
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
    return hl;
            
      
            
       

    }
   
    public void tampilHargaLayanan()
    {
        int a = tabelModel7.getRowCount() ;
        for (int i = 0; i < a; i++) {
            tabelModel7.removeRow(0);
        }
        AD.makeConnection();
          String sql ="Select A.nama as Nama, b.nama as Ukuran,C.Harga as Harga,C.ID_Harga_Layanan,C.created_by,C.modified_by,C.modified_At, C.created_at from harga_layanan as C inner join layanan as A on C.id_layanan=A.id_Layanan inner join ukuran_hewan b on C.id_ukuran_hewan =b.id_ukuran_hewan where C.aktif=1 AND A.aktif=1"; 
          System.out.println("Tampil Harga Layanan");

    
    try
    {
        
       Statement stm= con.createStatement();
       ResultSet rs= stm.executeQuery(sql);
   
        
        if (rs!=null) {
            
            while(rs.next())
            {
         
   
                String id = rs.getString("ID_HARGA_LAYANAN");
                String nama_Layanan = rs.getString("Nama");
                String Ukuran = rs.getString("Ukuran");
                int harga = Integer.parseInt(rs.getString("Harga"));
                String harga1 = String.valueOf(harga);
                String createby = rs.getString("C.created_by");
                String createAt= rs.getString("C.created_At");
                String modifby = rs.getString("C.modified_by");
                String modifat = rs.getString("C.modified_at");
                String[] dataField={id,nama_Layanan,Ukuran,harga1,createby , createAt,modifby,modifat};
                tabelModel7.addRow(dataField);
                            
            }
            rs.close();
            stm.close();
        }
        

     AD.closeConnection();

    }
   
    catch(Exception e)
    {
        System.out.println("Gagal ");
        System.out.println(e);
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
            S.setCreateBy(M.get(i).getCreateBy());
            S.setCreateAt(M.get(i).getCreateAt());
            S.setModified_by(M.get(i).getModifiedBy());
            S.setModifiedAt(M.get(i).getModified_at());
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
            P.setCreated_by(M.get(i).getCreateBy());
            P.setCreateAt(M.get(i).GetCreateAt());
           P.setRole(M.get(i).getRole());
           P.setID_Pegawai(M.get(i).getId());
           P.setTglLahir(M.get(i).getTglLahir());
           P.setModified_By(M.get(i).getModified_by());
           P.setModified_at(M.get(i).getModified_at());
           P.setAlamat(M.get(i).getAlamat());
           addTablePegawai(P);
 
          
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
    public void namaProduk() throws dataSama
    {
        Pr = AC.searchPro(txtNamaProduk.getText());
         if (Pr!=null) {
             throw new dataSama();
            
        }
    }
    public void userNameSama() throws dataSama
    {
         
       P = AC.searchPegawai(txtUserName.getText());
       Pegawai L = new Pegawai();
       L = AC.searchPegawai(txtCariUserName.getText());
       if(L!=null && txtCariUserName.getText().equalsIgnoreCase(txtCari.getText())==false )
       {
           throw  new dataSama();
       }
        if(P!=null)
        {
            
           throw new dataSama();
           
        }
        
 
    }
    public void cekJenisHewan() throws dataSama
    {
        jh = AC.searchJenisHewan(txtNamaHewan.getText());
        if(jh!=null)
        {
            throw new dataSama();
        }
            
    }
    public void cekDataUkuran() throws dataSama
    {
        Uh= AC.searchUkuran(txtUkuranHewan.getText());
        if (Uh!=null) {
            throw new dataSama();
            
        }
    }
 
    public void setTextCariPegawai()
    {
        txtCari.setText("");
        txtCariAlamat.setText("");
        txtCariNama.setText("");
        txtCariNoTlp.setText("");
 
        tglLahirPegawai1.setDate(null);
        txtCariUserName.setText(" ");
       
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
       tglLahirPegawai1.setEnabled(nilai);
       txtCariNoTlp.setEnabled(nilai);
       
       kasir.setEnabled(nilai);
       cs.setEnabled(nilai);

       
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
        cari = new javax.swing.ButtonGroup();
        background = new javax.swing.JPanel();
        menuHome = new javax.swing.JPanel();
        judul = new javax.swing.JLabel();
        btnPegawai = new javax.swing.JButton();
        btnLayanan = new javax.swing.JButton();
        btnProduk = new javax.swing.JButton();
        btnSup = new javax.swing.JButton();
        btnHewan = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        txtTanggalHariIni = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        btnUkuran = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        MainPanel = new javax.swing.JPanel();
        dataPegawai = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        btnTambah = new javax.swing.JButton();
        btnHelp = new javax.swing.JButton();
        btnTampil = new javax.swing.JButton();
        btnCari = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        mainPanel2 = new javax.swing.JPanel();
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
        bantuan = new javax.swing.JPanel();
        cariPegawai = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        txtCariNama = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txtCariNoTlp = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        txtCariAlamat = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        txtCariUserName = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        btnEdit = new javax.swing.JButton();
        btnSimpanEdit = new javax.swing.JButton();
        btnHapus = new javax.swing.JButton();
        txtCari = new javax.swing.JTextField();
        btnSearch = new javax.swing.JLabel();
        kasir = new javax.swing.JRadioButton();
        cs = new javax.swing.JRadioButton();
        tglLahirPegawai1 = new com.toedter.calendar.JDateChooser();
        tampilSeluruh = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tablePegawai = new javax.swing.JTable();
        filterTable = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        dataLayanan = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        btnTambahLyn = new javax.swing.JButton();
        btnHelp1 = new javax.swing.JButton();
        btnTampilLyn = new javax.swing.JButton();
        btnCariLyn = new javax.swing.JButton();
        jLabel25 = new javax.swing.JLabel();
        mainPanel3 = new javax.swing.JPanel();
        tambahLayanan = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        btnSimpanLyn = new javax.swing.JButton();
        btlSimpan1 = new javax.swing.JButton();
        txtNamaLayanan = new javax.swing.JTextField();
        jLabel69 = new javax.swing.JLabel();
        jLabel74 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tabelLayanan = new javax.swing.JTable();
        cariLayanan = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        btnSimpanHewan1 = new javax.swing.JButton();
        btlHapusHewan1 = new javax.swing.JButton();
        txtNamaLayananCari = new javax.swing.JTextField();
        jLabel85 = new javax.swing.JLabel();
        txtCariLayanan = new javax.swing.JTextField();
        btnCariLayanan = new javax.swing.JLabel();
        tampilLayanan = new javax.swing.JPanel();
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
        namaGambar = new javax.swing.JLabel();
        btlSimpan3 = new javax.swing.JButton();
        lblImage = new javax.swing.JLabel();
        jLabel80 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tabelProduk = new javax.swing.JTable();
        cariProduk = new javax.swing.JPanel();
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
        jLabel49 = new javax.swing.JLabel();
        btnSearchProduk = new javax.swing.JLabel();
        tampilGambar = new javax.swing.JLabel();
        jLabel73 = new javax.swing.JLabel();
        mencariNamaProduk = new javax.swing.JTextField();
        btnPilih = new javax.swing.JButton();
        namaGambar1 = new javax.swing.JLabel();
        tampilProduk = new javax.swing.JPanel();
        jScrollPane10 = new javax.swing.JScrollPane();
        tabelProdukTampilSeluruh = new javax.swing.JTable();
        filterTable1 = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        dataSupplier = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        btnTambahSup = new javax.swing.JButton();
        btnSupHelp = new javax.swing.JButton();
        btnTampilSup = new javax.swing.JButton();
        btnCariSup = new javax.swing.JButton();
        jLabel29 = new javax.swing.JLabel();
        mainPanel4 = new javax.swing.JPanel();
        TambahSuplier = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        btnSimpanSuplier = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        txtTelpSup = new javax.swing.JTextField();
        txtAlamatSup = new javax.swing.JTextField();
        txtNamSup = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableSupTambahData = new javax.swing.JTable();
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
        jLabel68 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabelHewan = new javax.swing.JTable();
        CariHewan = new javax.swing.JPanel();
        jLabel37 = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        btnSimpanHewan = new javax.swing.JButton();
        btlHapusHewan = new javax.swing.JButton();
        txtCariNamaHwn = new javax.swing.JTextField();
        jLabel84 = new javax.swing.JLabel();
        cariJenisHewan = new javax.swing.JTextField();
        btnSearchJenisHewan = new javax.swing.JLabel();
        TampilHewan = new javax.swing.JPanel();
        dataUkuran = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        btnTambahUkuran = new javax.swing.JButton();
        btnHelp3 = new javax.swing.JButton();
        btnTampilLyn1 = new javax.swing.JButton();
        btnCariUkuran = new javax.swing.JButton();
        jLabel28 = new javax.swing.JLabel();
        mainPanel10 = new javax.swing.JPanel();
        tambahUkuranHewan = new javax.swing.JPanel();
        jPanel19 = new javax.swing.JPanel();
        btnSimpanUkuran = new javax.swing.JButton();
        btlSimpan2 = new javax.swing.JButton();
        txtUkuranHewan = new javax.swing.JTextField();
        jLabel87 = new javax.swing.JLabel();
        jLabel88 = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        tabelUkuranHewan = new javax.swing.JTable();
        cariUkuranHewan = new javax.swing.JPanel();
        btnCariUkuranHewan = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jComboBoxUkuran = new javax.swing.JComboBox<>();
        jPanel18 = new javax.swing.JPanel();
        btnSimpanHewan2 = new javax.swing.JButton();
        btlHapusUkuranHewan = new javax.swing.JButton();
        txtCariUkuranHewan = new javax.swing.JTextField();
        jLabel86 = new javax.swing.JLabel();
        tampilUkuranHewan = new javax.swing.JPanel();
        tambahHargaLayanan = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        btnTambahLyn1 = new javax.swing.JButton();
        btnHelp4 = new javax.swing.JButton();
        btnTampilLyn2 = new javax.swing.JButton();
        btnCariLyn1 = new javax.swing.JButton();
        jLabel34 = new javax.swing.JLabel();
        mainPanel7 = new javax.swing.JPanel();
        tambahLayanan2 = new javax.swing.JPanel();
        jPanel20 = new javax.swing.JPanel();
        btnSimpanLyn2 = new javax.swing.JButton();
        btlSimpan6 = new javax.swing.JButton();
        jLabel76 = new javax.swing.JLabel();
        jLabel77 = new javax.swing.JLabel();
        jComboBoxNamaLayanan = new javax.swing.JComboBox<>();
        jComboBoxUkuranHewan = new javax.swing.JComboBox<>();
        jLabel78 = new javax.swing.JLabel();
        jLabel79 = new javax.swing.JLabel();
        txthargaLayanan = new javax.swing.JTextField();
        jScrollPane9 = new javax.swing.JScrollPane();
        tabelHargaLayanan = new javax.swing.JTable();
        cariLayanan1 = new javax.swing.JPanel();
        jLabel35 = new javax.swing.JLabel();
        jPanel21 = new javax.swing.JPanel();
        btnSimpanHewan3 = new javax.swing.JButton();
        btlHapusHewan2 = new javax.swing.JButton();
        jLabel89 = new javax.swing.JLabel();
        jLabel90 = new javax.swing.JLabel();
        jLabel91 = new javax.swing.JLabel();
        txtNamaLayananHarga2 = new javax.swing.JTextField();
        btnCariLayanan1 = new javax.swing.JLabel();
        cariIdHargaLyn = new javax.swing.JTextField();
        jComboBoxNamaLayanan1 = new javax.swing.JComboBox<>();
        jComboBoxUkuranHewan1 = new javax.swing.JComboBox<>();
        tampilLayanan1 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImage(new javax.swing.ImageIcon(getClass().getResource("/icon/dogg.png")).getImage());

        background.setBackground(new java.awt.Color(255, 255, 255));
        background.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        menuHome.setBackground(new java.awt.Color(255, 120, 0));
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

        jButton1.setFont(new java.awt.Font("Serif", 1, 12)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Cash-icon.png"))); // NOI18N
        jButton1.setText("Harga Layanan");
        jButton1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jButton1.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton6.setFont(new java.awt.Font("Serif", 1, 12)); // NOI18N
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/fixReport.png"))); // NOI18N
        jButton6.setText("REPORT");
        jButton6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton6.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jButton6.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout menuHomeLayout = new javax.swing.GroupLayout(menuHome);
        menuHome.setLayout(menuHomeLayout);
        menuHomeLayout.setHorizontalGroup(
            menuHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuHomeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(judul)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, menuHomeLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(menuHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(menuHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btnSup, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnProduk, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnLayanan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnHewan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnPegawai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnUkuran, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(menuHomeLayout.createSequentialGroup()
                        .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTanggalHariIni, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(24, 24, 24))
        );
        menuHomeLayout.setVerticalGroup(
            menuHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuHomeLayout.createSequentialGroup()
                .addGap(70, 70, 70)
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
                .addGap(18, 18, 18)
                .addComponent(btnUkuran)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 120, Short.MAX_VALUE)
                .addGroup(menuHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel23, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTanggalHariIni, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButton6)
                .addGap(7, 7, 7))
        );

        MainPanel.setBackground(new java.awt.Color(168, 238, 244));
        MainPanel.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createEtchedBorder(), javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED)));
        MainPanel.setLayout(new java.awt.CardLayout());

        dataPegawai.setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 139, 36));
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

        tambahPegawai.setBackground(new java.awt.Color(255, 244, 203));
        tambahPegawai.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tambahPegawai.setForeground(new java.awt.Color(0, 0, 0));

        txtNamaPegawai.setFont(new java.awt.Font("Times New Roman", 1, 11)); // NOI18N
        txtNamaPegawai.setForeground(new java.awt.Color(0, 0, 0));
        txtNamaPegawai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNamaPegawaiActionPerformed(evt);
            }
        });
        txtNamaPegawai.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNamaPegawaiKeyPressed(evt);
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
        btnSimpanPegawai.setText("SIMPAN");
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
        rdCustomerService.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rdCustomerServiceMouseClicked(evt);
            }
        });
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

        bantuan.setBackground(new java.awt.Color(99, 175, 241));

        javax.swing.GroupLayout bantuanLayout = new javax.swing.GroupLayout(bantuan);
        bantuan.setLayout(bantuanLayout);
        bantuanLayout.setHorizontalGroup(
            bantuanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 525, Short.MAX_VALUE)
        );
        bantuanLayout.setVerticalGroup(
            bantuanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 801, Short.MAX_VALUE)
        );

        mainPanel2.add(bantuan, "card5");

        cariPegawai.setBackground(new java.awt.Color(255, 244, 203));
        cariPegawai.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel21.setBackground(new java.awt.Color(0, 255, 255));
        jLabel21.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(0, 0, 0));
        jLabel21.setText("Cari Pegawai");

        jPanel5.setBackground(new java.awt.Color(255, 244, 203));
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

        jLabel20.setBackground(new java.awt.Color(0, 255, 255));
        jLabel20.setFont(new java.awt.Font("Clarendon Lt BT", 0, 18)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(0, 0, 0));
        jLabel20.setText("Role");

        btnEdit.setText("EDIT");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        btnSimpanEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/save.png"))); // NOI18N
        btnSimpanEdit.setText("SIMPAN");
        btnSimpanEdit.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
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

        txtCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCariActionPerformed(evt);
            }
        });
        txtCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCariKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCariKeyTyped(evt);
            }
        });

        btnSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/search-icon.png"))); // NOI18N
        btnSearch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSearchMouseClicked(evt);
            }
        });
        btnSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnSearchKeyPressed(evt);
            }
        });

        cari.add(kasir);
        kasir.setText("Kasir");
        kasir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kasirActionPerformed(evt);
            }
        });

        cari.add(cs);
        cs.setText("Customer Service");
        cs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                csActionPerformed(evt);
            }
        });

        tglLahirPegawai1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        tglLahirPegawai1.setDateFormatString("yyyy-MMMMMM-d");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(btnSearch)
                                .addGap(15, 15, 15)
                                .addComponent(txtCari))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnSimpanEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnHapus, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(24, 24, 24)
                                .addComponent(txtCariAlamat, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtCariNama, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE))
                                .addGap(24, 24, 24)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtCariNoTlp, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tglLahirPegawai1, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                .addContainerGap(22, Short.MAX_VALUE)
                                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(3, 3, 3)))
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(txtCariUserName))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(kasir)
                                .addGap(129, 129, 129)
                                .addComponent(cs)))))
                .addGap(38, 38, 38))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnSearch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtCari))
                .addGap(29, 29, 29)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCariNama, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                    .addComponent(tglLahirPegawai1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(17, 17, 17)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtCariNoTlp)
                    .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCariAlamat, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCariUserName, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cs)
                    .addComponent(kasir)
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 160, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnSimpanEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                        .addContainerGap()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        cariPegawaiLayout.setVerticalGroup(
            cariPegawaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, cariPegawaiLayout.createSequentialGroup()
                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(244, Short.MAX_VALUE))
        );

        mainPanel2.add(cariPegawai, "card3");

        tampilSeluruh.setBackground(new java.awt.Color(255, 244, 203));

        tablePegawai.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID_Pegawai", "Nama ", "User Name", "Alamat", "Tgl Lahir", "Role", "Created_By", "Created_At", "Modified_By", "Modified_At"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, true, true, false, true, true, true, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane6.setViewportView(tablePegawai);

        filterTable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filterTableActionPerformed(evt);
            }
        });
        filterTable.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                filterTableKeyPressed(evt);
            }
        });

        jButton4.setText("CARI");

        javax.swing.GroupLayout tampilSeluruhLayout = new javax.swing.GroupLayout(tampilSeluruh);
        tampilSeluruh.setLayout(tampilSeluruhLayout);
        tampilSeluruhLayout.setHorizontalGroup(
            tampilSeluruhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tampilSeluruhLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tampilSeluruhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tampilSeluruhLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jButton4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(filterTable, javax.swing.GroupLayout.PREFERRED_SIZE, 356, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(tampilSeluruhLayout.createSequentialGroup()
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 1079, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        tampilSeluruhLayout.setVerticalGroup(
            tampilSeluruhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tampilSeluruhLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(tampilSeluruhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(filterTable, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 528, Short.MAX_VALUE)
                .addContainerGap())
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
                    .addComponent(mainPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
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

        jPanel2.setBackground(new java.awt.Color(255, 139, 36));

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

        tambahLayanan.setBackground(new java.awt.Color(255, 255, 255));

        jPanel10.setBackground(new java.awt.Color(255, 244, 203));
        jPanel10.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        btnSimpanLyn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Save-icon.png"))); // NOI18N
        btnSimpanLyn.setText("SIMPAN");
        btnSimpanLyn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnSimpanLyn.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnSimpanLyn.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btnSimpanLyn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanLynActionPerformed(evt);
            }
        });

        btlSimpan1.setText("BATAL");
        btlSimpan1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btlSimpan1ActionPerformed(evt);
            }
        });

        txtNamaLayanan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNamaLayananActionPerformed(evt);
            }
        });

        jLabel69.setFont(new java.awt.Font("Clarendon BT", 0, 18)); // NOI18N
        jLabel69.setForeground(new java.awt.Color(0, 0, 0));
        jLabel69.setText("Nama Layanan");

        jLabel74.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        jLabel74.setForeground(new java.awt.Color(0, 0, 0));
        jLabel74.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/menuTambahHewan.png"))); // NOI18N
        jLabel74.setText("TAMBAH LAYANAN");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel74, javax.swing.GroupLayout.PREFERRED_SIZE, 426, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btlSimpan1, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btnSimpanLyn, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addComponent(jLabel69, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtNamaLayanan, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(30, 30, 30))))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel74, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel69, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNamaLayanan, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(74, 74, 74)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btlSimpan1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSimpanLyn))
                .addContainerGap(509, Short.MAX_VALUE))
        );

        tabelLayanan.setBackground(new java.awt.Color(255, 255, 255));
        tabelLayanan.setFont(new java.awt.Font("Geometr212 BkCn BT", 1, 12)); // NOI18N
        tabelLayanan.setForeground(new java.awt.Color(0, 0, 0));
        tabelLayanan.setModel(new javax.swing.table.DefaultTableModel(
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
                "ID_Layanan", "Nama Layanan", "Create_By", "Create_At", "Modified_By", "Modified_At"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelLayanan.setGridColor(new java.awt.Color(0, 0, 0));
        tabelLayanan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelLayananMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(tabelLayanan);
        if (tabelLayanan.getColumnModel().getColumnCount() > 0) {
            tabelLayanan.getColumnModel().getColumn(0).setResizable(false);
            tabelLayanan.getColumnModel().getColumn(1).setResizable(false);
        }

        javax.swing.GroupLayout tambahLayananLayout = new javax.swing.GroupLayout(tambahLayanan);
        tambahLayanan.setLayout(tambahLayananLayout);
        tambahLayananLayout.setHorizontalGroup(
            tambahLayananLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tambahLayananLayout.createSequentialGroup()
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 686, Short.MAX_VALUE)
                .addGap(6, 6, 6))
        );
        tambahLayananLayout.setVerticalGroup(
            tambahLayananLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 817, Short.MAX_VALUE)
            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        mainPanel3.add(tambahLayanan, "card2");

        cariLayanan.setBackground(new java.awt.Color(255, 244, 203));

        jLabel26.setBackground(new java.awt.Color(0, 255, 255));
        jLabel26.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(0, 0, 0));
        jLabel26.setText("Cari Layanan");

        jPanel16.setBackground(new java.awt.Color(255, 244, 203));
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

        txtCariLayanan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCariLayananActionPerformed(evt);
            }
        });
        txtCariLayanan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCariLayananKeyPressed(evt);
            }
        });

        btnCariLayanan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/search-icon.png"))); // NOI18N
        btnCariLayanan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCariLayananMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel16Layout.createSequentialGroup()
                            .addComponent(btnCariLayanan)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtCariLayanan, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel16Layout.createSequentialGroup()
                            .addComponent(jLabel85)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(txtNamaLayananCari)))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addComponent(btlHapusHewan1, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSimpanHewan1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtCariLayanan, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCariLayanan))
                .addGap(65, 65, 65)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel85, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNamaLayananCari, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 189, Short.MAX_VALUE)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btlHapusHewan1)
                    .addComponent(btnSimpanHewan1))
                .addGap(46, 46, 46))
        );

        javax.swing.GroupLayout cariLayananLayout = new javax.swing.GroupLayout(cariLayanan);
        cariLayanan.setLayout(cariLayananLayout);
        cariLayananLayout.setHorizontalGroup(
            cariLayananLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cariLayananLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(cariLayananLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(705, Short.MAX_VALUE))
        );
        cariLayananLayout.setVerticalGroup(
            cariLayananLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cariLayananLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(344, Short.MAX_VALUE))
        );

        mainPanel3.add(cariLayanan, "card3");

        tampilLayanan.setBackground(new java.awt.Color(99, 175, 241));

        javax.swing.GroupLayout tampilLayananLayout = new javax.swing.GroupLayout(tampilLayanan);
        tampilLayanan.setLayout(tampilLayananLayout);
        tampilLayananLayout.setHorizontalGroup(
            tampilLayananLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1152, Short.MAX_VALUE)
        );
        tampilLayananLayout.setVerticalGroup(
            tampilLayananLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 817, Short.MAX_VALUE)
        );

        mainPanel3.add(tampilLayanan, "card4");

        javax.swing.GroupLayout dataLayananLayout = new javax.swing.GroupLayout(dataLayanan);
        dataLayanan.setLayout(dataLayananLayout);
        dataLayananLayout.setHorizontalGroup(
            dataLayananLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        dataLayananLayout.setVerticalGroup(
            dataLayananLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dataLayananLayout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(mainPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        MainPanel.add(dataLayanan, "card3");

        dataProduk.setBackground(new java.awt.Color(255, 255, 255));

        jPanel3.setBackground(new java.awt.Color(255, 139, 36));

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

        jLabel27.setBackground(new java.awt.Color(255, 255, 255));
        jLabel27.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 24)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(51, 51, 55));
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
                .addContainerGap(640, Short.MAX_VALUE))
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

        tambahProduk.setBackground(new java.awt.Color(255, 255, 255));
        tambahProduk.setForeground(new java.awt.Color(0, 0, 0));

        jPanel9.setBackground(new java.awt.Color(255, 244, 203));
        jPanel9.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        btnSimpanProduk.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Save-icon.png"))); // NOI18N
        btnSimpanProduk.setText("SIMPAN");
        btnSimpanProduk.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnSimpanProduk.setMargin(new java.awt.Insets(0, 0, 0, 0));
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

        txtSatun.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSatunKeyPressed(evt);
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
        txtHargaProduk.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtHargaProdukKeyPressed(evt);
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
        txtJumStok.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtJumStokKeyPressed(evt);
            }
        });

        jLabel60.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        jLabel60.setForeground(new java.awt.Color(0, 0, 0));
        jLabel60.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Box-icon (1).png"))); // NOI18N
        jLabel60.setText("TAMBAH PRODUK");

        namaGambar.setFont(new java.awt.Font("Clarendon BT", 0, 3)); // NOI18N
        namaGambar.setForeground(new java.awt.Color(255, 244, 203));

        btlSimpan3.setText("Pilih Gambar");
        btlSimpan3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btlSimpan3ActionPerformed(evt);
            }
        });

        lblImage.setFont(new java.awt.Font("Clarendon BT", 0, 18)); // NOI18N
        lblImage.setForeground(new java.awt.Color(0, 0, 0));
        lblImage.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel80.setFont(new java.awt.Font("Clarendon BT", 0, 18)); // NOI18N
        jLabel80.setForeground(new java.awt.Color(0, 0, 0));
        jLabel80.setText("Gambar");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel60, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(0, 4, Short.MAX_VALUE)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                                .addComponent(jLabel57)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtNamaProduk, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel58, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel59, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtSatun, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtJumStok, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel61, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel62, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtHargaProduk)
                                    .addGroup(jPanel9Layout.createSequentialGroup()
                                        .addComponent(txtMinStok, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE)))))
                        .addGap(19, 19, 19))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(namaGambar, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel80, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btlSimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblImage, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(btnSimpanProduk, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btlSimpan3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel60, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
                            .addComponent(txtHargaProduk, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addComponent(namaGambar, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(12, 12, 12)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtMinStok, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel62, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblImage, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel80, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btlSimpan3)
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btlSimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSimpanProduk))
                .addContainerGap(39, Short.MAX_VALUE))
        );

        tabelProduk.setBackground(new java.awt.Color(255, 255, 255));
        tabelProduk.setFont(new java.awt.Font("Geometr212 BkCn BT", 1, 12)); // NOI18N
        tabelProduk.setForeground(new java.awt.Color(0, 0, 0));
        tabelProduk.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID_Produk", "Nama ", "Satuan", "Jumlah Stok", "Harga", "Min Stok", "Create By", "Create At", "Modified_By", "Modified_At"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, true, true
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
            tabelProduk.getColumnModel().getColumn(6).setResizable(false);
            tabelProduk.getColumnModel().getColumn(7).setResizable(false);
        }

        javax.swing.GroupLayout tambahProdukLayout = new javax.swing.GroupLayout(tambahProduk);
        tambahProduk.setLayout(tambahProdukLayout);
        tambahProdukLayout.setHorizontalGroup(
            tambahProdukLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tambahProdukLayout.createSequentialGroup()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 722, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        tambahProdukLayout.setVerticalGroup(
            tambahProdukLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tambahProdukLayout.createSequentialGroup()
                .addGroup(tambahProdukLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane4))
                .addGap(0, 128, Short.MAX_VALUE))
        );

        mainPanel5.add(tambahProduk, "card2");

        cariProduk.setBackground(new java.awt.Color(255, 244, 203));

        jPanel11.setBackground(new java.awt.Color(255, 244, 203));
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

        txtSatunP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSatunPActionPerformed(evt);
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

        jLabel49.setBackground(new java.awt.Color(0, 255, 255));
        jLabel49.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jLabel49.setForeground(new java.awt.Color(0, 0, 0));
        jLabel49.setText("Cari Produk");

        btnSearchProduk.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/search-icon.png"))); // NOI18N
        btnSearchProduk.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSearchProdukMouseClicked(evt);
            }
        });

        tampilGambar.setFont(new java.awt.Font("Clarendon BT", 0, 18)); // NOI18N
        tampilGambar.setForeground(new java.awt.Color(0, 0, 0));
        tampilGambar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel73.setFont(new java.awt.Font("Clarendon BT", 0, 18)); // NOI18N
        jLabel73.setForeground(new java.awt.Color(0, 0, 0));
        jLabel73.setText("Gambar");

        mencariNamaProduk.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                mencariNamaProdukKeyPressed(evt);
            }
        });

        btnPilih.setText("Pilih Gambar");
        btnPilih.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPilihActionPerformed(evt);
            }
        });

        namaGambar1.setFont(new java.awt.Font("Clarendon BT", 0, 3)); // NOI18N
        namaGambar1.setForeground(new java.awt.Color(255, 244, 203));

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(0, 20, Short.MAX_VALUE)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGap(7, 7, 7)
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
                                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(jLabel66, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel67, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel73, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(btlHapusProduk, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtMinStokP, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtHargaProdukp, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(tampilGambar, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(btnSimpanProduk1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(btnPilih, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addComponent(btnSearchProduk)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(mencariNamaProduk, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(27, Short.MAX_VALUE))))
            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel11Layout.createSequentialGroup()
                    .addGap(208, 208, 208)
                    .addComponent(namaGambar1)
                    .addContainerGap(217, Short.MAX_VALUE)))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(mencariNamaProduk)
                    .addComponent(btnSearchProduk, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
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
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tampilGambar, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel73, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addComponent(btnPilih)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btlHapusProduk)
                    .addComponent(btnSimpanProduk1))
                .addGap(93, 93, 93))
            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel11Layout.createSequentialGroup()
                    .addGap(303, 303, 303)
                    .addComponent(namaGambar1, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(304, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout cariProdukLayout = new javax.swing.GroupLayout(cariProduk);
        cariProduk.setLayout(cariProdukLayout);
        cariProdukLayout.setHorizontalGroup(
            cariProdukLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cariProdukLayout.createSequentialGroup()
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(710, Short.MAX_VALUE))
        );
        cariProdukLayout.setVerticalGroup(
            cariProdukLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cariProdukLayout.createSequentialGroup()
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 112, Short.MAX_VALUE))
        );

        mainPanel5.add(cariProduk, "card3");

        tampilProduk.setBackground(new java.awt.Color(99, 175, 241));

        tabelProdukTampilSeluruh.setBackground(new java.awt.Color(255, 255, 255));
        tabelProdukTampilSeluruh.setFont(new java.awt.Font("Geometr212 BkCn BT", 1, 12)); // NOI18N
        tabelProdukTampilSeluruh.setForeground(new java.awt.Color(0, 0, 0));
        tabelProdukTampilSeluruh.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID_Produk", "Nama ", "Satuan", "Jumlah Stok", "Harga", "Min Stok", "Create By", "Create At", "Modified_By", "Modified_At", "Delete_By", "Delete_At"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, true, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelProdukTampilSeluruh.setGridColor(new java.awt.Color(0, 0, 0));
        jScrollPane10.setViewportView(tabelProdukTampilSeluruh);
        if (tabelProdukTampilSeluruh.getColumnModel().getColumnCount() > 0) {
            tabelProdukTampilSeluruh.getColumnModel().getColumn(0).setResizable(false);
            tabelProdukTampilSeluruh.getColumnModel().getColumn(1).setResizable(false);
            tabelProdukTampilSeluruh.getColumnModel().getColumn(2).setResizable(false);
            tabelProdukTampilSeluruh.getColumnModel().getColumn(3).setResizable(false);
            tabelProdukTampilSeluruh.getColumnModel().getColumn(4).setResizable(false);
            tabelProdukTampilSeluruh.getColumnModel().getColumn(5).setResizable(false);
            tabelProdukTampilSeluruh.getColumnModel().getColumn(6).setResizable(false);
            tabelProdukTampilSeluruh.getColumnModel().getColumn(7).setResizable(false);
        }

        filterTable1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filterTable1ActionPerformed(evt);
            }
        });
        filterTable1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                filterTable1KeyPressed(evt);
            }
        });

        jButton5.setText("CARI");

        javax.swing.GroupLayout tampilProdukLayout = new javax.swing.GroupLayout(tampilProduk);
        tampilProduk.setLayout(tampilProdukLayout);
        tampilProdukLayout.setHorizontalGroup(
            tampilProdukLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tampilProdukLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tampilProdukLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 1039, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(tampilProdukLayout.createSequentialGroup()
                        .addComponent(jButton5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(filterTable1, javax.swing.GroupLayout.PREFERRED_SIZE, 356, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(86, Short.MAX_VALUE))
        );
        tampilProdukLayout.setVerticalGroup(
            tampilProdukLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tampilProdukLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(tampilProdukLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(filterTable1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 455, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(228, Short.MAX_VALUE))
        );

        mainPanel5.add(tampilProduk, "card4");

        javax.swing.GroupLayout dataProdukLayout = new javax.swing.GroupLayout(dataProduk);
        dataProduk.setLayout(dataProdukLayout);
        dataProdukLayout.setHorizontalGroup(
            dataProdukLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(dataProdukLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dataProdukLayout.createSequentialGroup()
                    .addComponent(mainPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        dataProdukLayout.setVerticalGroup(
            dataProdukLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dataProdukLayout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(757, Short.MAX_VALUE))
            .addGroup(dataProdukLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(dataProdukLayout.createSequentialGroup()
                    .addGap(137, 137, 137)
                    .addComponent(mainPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 733, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        MainPanel.add(dataProduk, "card4");

        dataSupplier.setBackground(new java.awt.Color(255, 255, 255));

        jPanel4.setBackground(new java.awt.Color(255, 139, 36));
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

        TambahSuplier.setBackground(new java.awt.Color(255, 255, 255));

        jPanel6.setBackground(new java.awt.Color(255, 244, 203));
        jPanel6.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        btnSimpanSuplier.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Save-icon.png"))); // NOI18N
        btnSimpanSuplier.setText("SIMPAN");
        btnSimpanSuplier.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnSimpanSuplier.setMargin(new java.awt.Insets(0, 0, 0, 0));
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

        jLabel31.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(0, 0, 0));
        jLabel31.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/truk2.png"))); // NOI18N
        jLabel31.setText("TAMBAH SUPLIER");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnSimpanSuplier))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
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
                                    .addComponent(txtTelpSup, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel31)))
                .addContainerGap(33, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNamSup, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAlamatSup, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTelpSup, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSimpanSuplier))
                .addGap(155, 155, 155))
        );

        tableSupTambahData.setBackground(new java.awt.Color(255, 255, 255));
        tableSupTambahData.setFont(new java.awt.Font("Geometr212 BkCn BT", 1, 12)); // NOI18N
        tableSupTambahData.setForeground(new java.awt.Color(0, 0, 0));
        tableSupTambahData.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID_Sup", "Nama Supplier", "Alamat", "Telp", "Create By", "Create at", "Modified_By", "Modified_At"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, true, true
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
            tableSupTambahData.getColumnModel().getColumn(4).setResizable(false);
            tableSupTambahData.getColumnModel().getColumn(5).setResizable(false);
        }

        javax.swing.GroupLayout TambahSuplierLayout = new javax.swing.GroupLayout(TambahSuplier);
        TambahSuplier.setLayout(TambahSuplierLayout);
        TambahSuplierLayout.setHorizontalGroup(
            TambahSuplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TambahSuplierLayout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 719, Short.MAX_VALUE))
        );
        TambahSuplierLayout.setVerticalGroup(
            TambahSuplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(TambahSuplierLayout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 615, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        mainPanel4.add(TambahSuplier, "card2");

        CariSuplier.setBackground(new java.awt.Color(255, 244, 203));
        CariSuplier.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        mainPanel4.add(CariSuplier, "card3");

        javax.swing.GroupLayout dataSupplierLayout = new javax.swing.GroupLayout(dataSupplier);
        dataSupplier.setLayout(dataSupplierLayout);
        dataSupplierLayout.setHorizontalGroup(
            dataSupplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 1152, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        dataSupplierLayout.setVerticalGroup(
            dataSupplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dataSupplierLayout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(mainPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 807, Short.MAX_VALUE)
                .addContainerGap())
        );

        MainPanel.add(dataSupplier, "card5");

        dataHewan.setBackground(new java.awt.Color(255, 255, 255));

        jPanel8.setBackground(new java.awt.Color(255, 139, 36));
        jPanel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btnTambahJenis.setBackground(new java.awt.Color(51, 255, 255));
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

        btnTampilSup1.setBackground(new java.awt.Color(51, 255, 255));
        btnTampilSup1.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnTampilSup1.setForeground(new java.awt.Color(0, 0, 0));
        btnTampilSup1.setText("Tampil Seluruh Jenis");
        btnTampilSup1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTampilSup1ActionPerformed(evt);
            }
        });

        btnCariJenis.setBackground(new java.awt.Color(51, 255, 255));
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

        tambahHewan.setBackground(new java.awt.Color(255, 255, 255));
        tambahHewan.setForeground(new java.awt.Color(0, 0, 0));

        jPanel7.setBackground(new java.awt.Color(255, 244, 203));
        jPanel7.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        btnSimpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Save-icon.png"))); // NOI18N
        btnSimpan.setText("SIMPAN");
        btnSimpan.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnSimpan.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanActionPerformed(evt);
            }
        });

        jLabel38.setFont(new java.awt.Font("Clarendon BT", 0, 18)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(0, 0, 0));
        jLabel38.setText("Jenis Hewan");

        txtNamaHewan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNamaHewanActionPerformed(evt);
            }
        });

        jLabel68.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        jLabel68.setForeground(new java.awt.Color(0, 0, 0));
        jLabel68.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/menuTambahHewan.png"))); // NOI18N
        jLabel68.setText("TAMBAH JENIS HEWAN");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel68, javax.swing.GroupLayout.DEFAULT_SIZE, 422, Short.MAX_VALUE)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel38)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnSimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNamaHewan, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel68, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(71, 71, 71)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNamaHewan, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(124, 124, 124)
                .addComponent(btnSimpan)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tabelHewan.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0)));
        tabelHewan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Id_Hewan", "Jenis Hewan", "Create By", "Create At", "Modified_By", "Modified_At"
            }
        ));
        tabelHewan.setGridColor(new java.awt.Color(0, 0, 0));
        jScrollPane3.setViewportView(tabelHewan);

        javax.swing.GroupLayout tambahHewanLayout = new javax.swing.GroupLayout(tambahHewan);
        tambahHewan.setLayout(tambahHewanLayout);
        tambahHewanLayout.setHorizontalGroup(
            tambahHewanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tambahHewanLayout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 683, Short.MAX_VALUE))
        );
        tambahHewanLayout.setVerticalGroup(
            tambahHewanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 614, Short.MAX_VALUE)
        );

        mainPanel8.add(tambahHewan, "card2");

        CariHewan.setBackground(new java.awt.Color(255, 244, 203));
        CariHewan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel37.setBackground(new java.awt.Color(0, 255, 255));
        jLabel37.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(0, 0, 0));
        jLabel37.setText("Cari Jenis Hewan");

        jPanel15.setBackground(new java.awt.Color(255, 244, 203));
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

        cariJenisHewan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cariJenisHewanActionPerformed(evt);
            }
        });
        cariJenisHewan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cariJenisHewanKeyPressed(evt);
            }
        });

        btnSearchJenisHewan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/search-icon.png"))); // NOI18N
        btnSearchJenisHewan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSearchJenisHewanMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap(27, Short.MAX_VALUE)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(btlHapusHewan, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnSimpanHewan, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                        .addComponent(jLabel84)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                        .addComponent(txtCariNamaHwn, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35))))
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addComponent(btnSearchJenisHewan)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cariJenisHewan)
                .addContainerGap())
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cariJenisHewan, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearchJenisHewan))
                .addGap(71, 71, 71)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCariNamaHwn, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel84, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(105, 105, 105)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btlHapusHewan)
                    .addComponent(btnSimpanHewan))
                .addContainerGap(115, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout CariHewanLayout = new javax.swing.GroupLayout(CariHewan);
        CariHewan.setLayout(CariHewanLayout);
        CariHewanLayout.setHorizontalGroup(
            CariHewanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CariHewanLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(CariHewanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(670, Short.MAX_VALUE))
        );
        CariHewanLayout.setVerticalGroup(
            CariHewanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CariHewanLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(170, Short.MAX_VALUE))
        );

        mainPanel8.add(CariHewan, "card3");

        javax.swing.GroupLayout TampilHewanLayout = new javax.swing.GroupLayout(TampilHewan);
        TampilHewan.setLayout(TampilHewanLayout);
        TampilHewanLayout.setHorizontalGroup(
            TampilHewanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1152, Short.MAX_VALUE)
        );
        TampilHewanLayout.setVerticalGroup(
            TampilHewanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 819, Short.MAX_VALUE)
        );

        mainPanel8.add(TampilHewan, "card4");

        javax.swing.GroupLayout dataHewanLayout = new javax.swing.GroupLayout(dataHewan);
        dataHewan.setLayout(dataHewanLayout);
        dataHewanLayout.setHorizontalGroup(
            dataHewanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dataHewanLayout.createSequentialGroup()
                .addGroup(dataHewanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(mainPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );
        dataHewanLayout.setVerticalGroup(
            dataHewanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dataHewanLayout.createSequentialGroup()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(mainPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        MainPanel.add(dataHewan, "card6");

        dataUkuran.setBackground(new java.awt.Color(255, 255, 255));

        jPanel17.setBackground(new java.awt.Color(255, 139, 36));

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

        tambahUkuranHewan.setBackground(new java.awt.Color(255, 255, 255));

        jPanel19.setBackground(new java.awt.Color(255, 244, 203));
        jPanel19.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        btnSimpanUkuran.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Save-icon.png"))); // NOI18N
        btnSimpanUkuran.setText("SIMPAN");
        btnSimpanUkuran.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnSimpanUkuran.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btnSimpanUkuran.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanUkuranActionPerformed(evt);
            }
        });

        btlSimpan2.setText("BATAL");
        btlSimpan2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btlSimpan2ActionPerformed(evt);
            }
        });

        txtUkuranHewan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUkuranHewanActionPerformed(evt);
            }
        });

        jLabel87.setFont(new java.awt.Font("Clarendon BT", 0, 18)); // NOI18N
        jLabel87.setForeground(new java.awt.Color(0, 0, 0));
        jLabel87.setText("Ukuran Hewan");

        jLabel88.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        jLabel88.setForeground(new java.awt.Color(0, 0, 0));
        jLabel88.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/menuTambahHewan.png"))); // NOI18N
        jLabel88.setText("TAMBAH UKURAN");

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addComponent(jLabel87)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtUkuranHewan, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel88, javax.swing.GroupLayout.PREFERRED_SIZE, 426, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addComponent(btlSimpan2, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSimpanUkuran)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel88, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel87, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtUkuranHewan, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(115, 115, 115)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btlSimpan2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSimpanUkuran))
                .addContainerGap(460, Short.MAX_VALUE))
        );

        tabelUkuranHewan.setAutoCreateRowSorter(true);
        tabelUkuranHewan.setBackground(new java.awt.Color(255, 255, 255));
        tabelUkuranHewan.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        tabelUkuranHewan.setFont(new java.awt.Font("Geometr212 BkCn BT", 1, 12)); // NOI18N
        tabelUkuranHewan.setForeground(new java.awt.Color(0, 0, 0));
        tabelUkuranHewan.setModel(new javax.swing.table.DefaultTableModel(
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
                "ID_Ukuran", "Ukuran Hewan", "Create By", "Create At", "Modified_By", "Modified_At"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelUkuranHewan.setDoubleBuffered(true);
        tabelUkuranHewan.setDragEnabled(true);
        tabelUkuranHewan.setGridColor(new java.awt.Color(0, 0, 0));
        jScrollPane8.setViewportView(tabelUkuranHewan);
        if (tabelUkuranHewan.getColumnModel().getColumnCount() > 0) {
            tabelUkuranHewan.getColumnModel().getColumn(0).setResizable(false);
            tabelUkuranHewan.getColumnModel().getColumn(1).setResizable(false);
            tabelUkuranHewan.getColumnModel().getColumn(2).setResizable(false);
            tabelUkuranHewan.getColumnModel().getColumn(3).setResizable(false);
        }

        javax.swing.GroupLayout tambahUkuranHewanLayout = new javax.swing.GroupLayout(tambahUkuranHewan);
        tambahUkuranHewan.setLayout(tambahUkuranHewanLayout);
        tambahUkuranHewanLayout.setHorizontalGroup(
            tambahUkuranHewanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tambahUkuranHewanLayout.createSequentialGroup()
                .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 685, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        tambahUkuranHewanLayout.setVerticalGroup(
            tambahUkuranHewanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(tambahUkuranHewanLayout.createSequentialGroup()
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 339, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        mainPanel10.add(tambahUkuranHewan, "card2");

        cariUkuranHewan.setBackground(new java.awt.Color(255, 244, 203));
        cariUkuranHewan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btnCariUkuranHewan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/search-icon.png"))); // NOI18N
        btnCariUkuranHewan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCariUkuranHewanMouseClicked(evt);
            }
        });

        jLabel33.setBackground(new java.awt.Color(0, 255, 255));
        jLabel33.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(0, 0, 0));
        jLabel33.setText("Cari Ukuran Hewan");

        jComboBoxUkuran.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jComboBoxUkuranMouseClicked(evt);
            }
        });
        jComboBoxUkuran.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxUkuranActionPerformed(evt);
            }
        });

        jPanel18.setBackground(new java.awt.Color(255, 244, 203));
        jPanel18.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11))); // NOI18N

        btnSimpanHewan2.setText("SIMPAN");
        btnSimpanHewan2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanHewan2ActionPerformed(evt);
            }
        });

        btlHapusUkuranHewan.setText("HAPUS");
        btlHapusUkuranHewan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btlHapusUkuranHewanActionPerformed(evt);
            }
        });

        txtCariUkuranHewan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCariUkuranHewanActionPerformed(evt);
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
                        .addComponent(btlHapusUkuranHewan, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnSimpanHewan2, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                        .addComponent(jLabel86)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtCariUkuranHewan, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCariUkuranHewan, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel86, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(170, 170, 170)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btlHapusUkuranHewan)
                    .addComponent(btnSimpanHewan2))
                .addContainerGap(115, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout cariUkuranHewanLayout = new javax.swing.GroupLayout(cariUkuranHewan);
        cariUkuranHewan.setLayout(cariUkuranHewanLayout);
        cariUkuranHewanLayout.setHorizontalGroup(
            cariUkuranHewanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cariUkuranHewanLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(cariUkuranHewanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(cariUkuranHewanLayout.createSequentialGroup()
                        .addComponent(btnCariUkuranHewan)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(cariUkuranHewanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jComboBoxUkuran, javax.swing.GroupLayout.PREFERRED_SIZE, 427, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(645, Short.MAX_VALUE))
        );
        cariUkuranHewanLayout.setVerticalGroup(
            cariUkuranHewanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cariUkuranHewanLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addGroup(cariUkuranHewanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jComboBoxUkuran, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCariUkuranHewan))
                .addGap(18, 18, 18)
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(280, Short.MAX_VALUE))
        );

        mainPanel10.add(cariUkuranHewan, "card3");

        tampilUkuranHewan.setBackground(new java.awt.Color(99, 175, 241));

        javax.swing.GroupLayout tampilUkuranHewanLayout = new javax.swing.GroupLayout(tampilUkuranHewan);
        tampilUkuranHewan.setLayout(tampilUkuranHewanLayout);
        tampilUkuranHewanLayout.setHorizontalGroup(
            tampilUkuranHewanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1152, Short.MAX_VALUE)
        );
        tampilUkuranHewanLayout.setVerticalGroup(
            tampilUkuranHewanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 805, Short.MAX_VALUE)
        );

        mainPanel10.add(tampilUkuranHewan, "card4");

        javax.swing.GroupLayout dataUkuranLayout = new javax.swing.GroupLayout(dataUkuran);
        dataUkuran.setLayout(dataUkuranLayout);
        dataUkuranLayout.setHorizontalGroup(
            dataUkuranLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(mainPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        dataUkuranLayout.setVerticalGroup(
            dataUkuranLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dataUkuranLayout.createSequentialGroup()
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(mainPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, 805, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        MainPanel.add(dataUkuran, "card3");

        tambahHargaLayanan.setBackground(new java.awt.Color(255, 255, 255));

        jPanel14.setBackground(new java.awt.Color(255, 139, 36));

        btnTambahLyn1.setBackground(new java.awt.Color(51, 255, 255));
        btnTambahLyn1.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnTambahLyn1.setForeground(new java.awt.Color(0, 0, 0));
        btnTambahLyn1.setText("Tambah Layanan");
        btnTambahLyn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTambahLyn1ActionPerformed(evt);
            }
        });

        btnHelp4.setText("Help");
        btnHelp4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHelp4ActionPerformed(evt);
            }
        });

        btnTampilLyn2.setBackground(new java.awt.Color(51, 255, 255));
        btnTampilLyn2.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnTampilLyn2.setForeground(new java.awt.Color(0, 0, 0));
        btnTampilLyn2.setText("Tampil Seluruh");
        btnTampilLyn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTampilLyn2ActionPerformed(evt);
            }
        });

        btnCariLyn1.setBackground(new java.awt.Color(51, 255, 255));
        btnCariLyn1.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnCariLyn1.setForeground(new java.awt.Color(0, 0, 0));
        btnCariLyn1.setText("Cari Layanan");
        btnCariLyn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariLyn1ActionPerformed(evt);
            }
        });

        jLabel34.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 24)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(0, 0, 0));
        jLabel34.setText("MENGELOLA HARGA LAYANAN KOEVEE PET SHOP");

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel34)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(btnTambahLyn1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnCariLyn1, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnTampilLyn2)
                        .addGap(64, 64, 64)
                        .addComponent(btnHelp4)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTambahLyn1)
                    .addComponent(btnCariLyn1)
                    .addComponent(btnHelp4)
                    .addComponent(btnTampilLyn2))
                .addContainerGap())
        );

        mainPanel7.setLayout(new java.awt.CardLayout());

        tambahLayanan2.setBackground(new java.awt.Color(255, 255, 255));

        jPanel20.setBackground(new java.awt.Color(255, 244, 203));
        jPanel20.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        btnSimpanLyn2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Save-icon.png"))); // NOI18N
        btnSimpanLyn2.setText("SIMPAN");
        btnSimpanLyn2.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnSimpanLyn2.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btnSimpanLyn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanLyn2ActionPerformed(evt);
            }
        });

        btlSimpan6.setText("BATAL");
        btlSimpan6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btlSimpan6ActionPerformed(evt);
            }
        });

        jLabel76.setFont(new java.awt.Font("Clarendon BT", 0, 18)); // NOI18N
        jLabel76.setForeground(new java.awt.Color(0, 0, 0));
        jLabel76.setText("Nama Layanan");

        jLabel77.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        jLabel77.setForeground(new java.awt.Color(0, 0, 0));
        jLabel77.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/uang1.png"))); // NOI18N
        jLabel77.setText("MENGELOLA HARGA");

        jComboBoxNamaLayanan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jComboBoxNamaLayananMouseClicked(evt);
            }
        });
        jComboBoxNamaLayanan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxNamaLayananActionPerformed(evt);
            }
        });
        jComboBoxNamaLayanan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jComboBoxNamaLayananKeyPressed(evt);
            }
        });

        jComboBoxUkuranHewan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jComboBoxUkuranHewanMouseClicked(evt);
            }
        });
        jComboBoxUkuranHewan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxUkuranHewanActionPerformed(evt);
            }
        });
        jComboBoxUkuranHewan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jComboBoxUkuranHewanKeyPressed(evt);
            }
        });

        jLabel78.setFont(new java.awt.Font("Clarendon BT", 0, 18)); // NOI18N
        jLabel78.setForeground(new java.awt.Color(0, 0, 0));
        jLabel78.setText("Ukuran Hewan");

        jLabel79.setFont(new java.awt.Font("Clarendon BT", 0, 18)); // NOI18N
        jLabel79.setForeground(new java.awt.Color(0, 0, 0));
        jLabel79.setText("Harga Layanan");

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addComponent(jLabel77, javax.swing.GroupLayout.PREFERRED_SIZE, 426, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel20Layout.createSequentialGroup()
                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel76, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel78, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBoxUkuranHewan, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBoxNamaLayanan, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30))
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel20Layout.createSequentialGroup()
                                .addComponent(btlSimpan6, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnSimpanLyn2))
                            .addGroup(jPanel20Layout.createSequentialGroup()
                                .addComponent(jLabel79)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txthargaLayanan, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel20Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel77, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel76, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxNamaLayanan, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxUkuranHewan, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel78, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel79, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txthargaLayanan, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btlSimpan6, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSimpanLyn2))
                .addContainerGap(431, Short.MAX_VALUE))
        );

        tabelHargaLayanan.setBackground(new java.awt.Color(255, 255, 255));
        tabelHargaLayanan.setFont(new java.awt.Font("Geometr212 BkCn BT", 1, 12)); // NOI18N
        tabelHargaLayanan.setForeground(new java.awt.Color(0, 0, 0));
        tabelHargaLayanan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Nama Layanan", "Ukuran", "Harga", "Create_By", "Create_At", "Modified_by", "Modified_At"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, true, true, true, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelHargaLayanan.setGridColor(new java.awt.Color(0, 0, 0));
        jScrollPane9.setViewportView(tabelHargaLayanan);
        if (tabelHargaLayanan.getColumnModel().getColumnCount() > 0) {
            tabelHargaLayanan.getColumnModel().getColumn(1).setResizable(false);
            tabelHargaLayanan.getColumnModel().getColumn(1).setPreferredWidth(200);
            tabelHargaLayanan.getColumnModel().getColumn(6).setHeaderValue("Modified_by");
            tabelHargaLayanan.getColumnModel().getColumn(7).setHeaderValue("Modified_At");
        }

        javax.swing.GroupLayout tambahLayanan2Layout = new javax.swing.GroupLayout(tambahLayanan2);
        tambahLayanan2.setLayout(tambahLayanan2Layout);
        tambahLayanan2Layout.setHorizontalGroup(
            tambahLayanan2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tambahLayanan2Layout.createSequentialGroup()
                .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 686, Short.MAX_VALUE)
                .addGap(6, 6, 6))
        );
        tambahLayanan2Layout.setVerticalGroup(
            tambahLayanan2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 817, Short.MAX_VALUE)
            .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        mainPanel7.add(tambahLayanan2, "card2");

        cariLayanan1.setBackground(new java.awt.Color(255, 255, 255));
        cariLayanan1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel35.setBackground(new java.awt.Color(0, 255, 255));
        jLabel35.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(0, 0, 0));
        jLabel35.setText("Cari Layanan");

        jPanel21.setBackground(new java.awt.Color(255, 255, 255));
        jPanel21.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11))); // NOI18N

        btnSimpanHewan3.setText("SIMPAN");
        btnSimpanHewan3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanHewan3ActionPerformed(evt);
            }
        });

        btlHapusHewan2.setText("HAPUS");
        btlHapusHewan2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btlHapusHewan2ActionPerformed(evt);
            }
        });

        jLabel89.setFont(new java.awt.Font("Clarendon BT", 0, 18)); // NOI18N
        jLabel89.setForeground(new java.awt.Color(0, 0, 0));
        jLabel89.setText("Nama Layanan");

        jLabel90.setFont(new java.awt.Font("Clarendon BT", 0, 18)); // NOI18N
        jLabel90.setForeground(new java.awt.Color(0, 0, 0));
        jLabel90.setText("Ukuran");

        jLabel91.setFont(new java.awt.Font("Clarendon BT", 0, 18)); // NOI18N
        jLabel91.setForeground(new java.awt.Color(0, 0, 0));
        jLabel91.setText("Harga");

        txtNamaLayananHarga2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNamaLayananHarga2ActionPerformed(evt);
            }
        });

        btnCariLayanan1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/search-icon.png"))); // NOI18N
        btnCariLayanan1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCariLayanan1MouseClicked(evt);
            }
        });

        cariIdHargaLyn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cariIdHargaLynActionPerformed(evt);
            }
        });
        cariIdHargaLyn.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cariIdHargaLynKeyPressed(evt);
            }
        });

        jComboBoxNamaLayanan1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jComboBoxNamaLayanan1MouseClicked(evt);
            }
        });
        jComboBoxNamaLayanan1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxNamaLayanan1ActionPerformed(evt);
            }
        });
        jComboBoxNamaLayanan1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jComboBoxNamaLayanan1KeyPressed(evt);
            }
        });

        jComboBoxUkuranHewan1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jComboBoxUkuranHewan1MouseClicked(evt);
            }
        });
        jComboBoxUkuranHewan1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxUkuranHewan1ActionPerformed(evt);
            }
        });
        jComboBoxUkuranHewan1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jComboBoxUkuranHewan1KeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addContainerGap(13, Short.MAX_VALUE)
                        .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel21Layout.createSequentialGroup()
                                .addComponent(btlHapusHewan2, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnSimpanHewan3, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel21Layout.createSequentialGroup()
                                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel90, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel89, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel91, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(jComboBoxUkuranHewan1, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel21Layout.createSequentialGroup()
                                        .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtNamaLayananHarga2, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jComboBoxNamaLayanan1, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(0, 0, Short.MAX_VALUE))))))
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnCariLayanan1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cariIdHargaLyn, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCariLayanan1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cariIdHargaLyn, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel89, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxNamaLayanan1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addComponent(jLabel90, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38)
                        .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel91, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNamaLayananHarga2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(49, 49, 49)
                        .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btlHapusHewan2)
                            .addComponent(btnSimpanHewan3)))
                    .addComponent(jComboBoxUkuranHewan1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(118, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout cariLayanan1Layout = new javax.swing.GroupLayout(cariLayanan1);
        cariLayanan1.setLayout(cariLayanan1Layout);
        cariLayanan1Layout.setHorizontalGroup(
            cariLayanan1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cariLayanan1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(cariLayanan1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(695, Short.MAX_VALUE))
        );
        cariLayanan1Layout.setVerticalGroup(
            cariLayanan1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cariLayanan1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(352, Short.MAX_VALUE))
        );

        mainPanel7.add(cariLayanan1, "card3");

        tampilLayanan1.setBackground(new java.awt.Color(99, 175, 241));

        javax.swing.GroupLayout tampilLayanan1Layout = new javax.swing.GroupLayout(tampilLayanan1);
        tampilLayanan1.setLayout(tampilLayanan1Layout);
        tampilLayanan1Layout.setHorizontalGroup(
            tampilLayanan1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1152, Short.MAX_VALUE)
        );
        tampilLayanan1Layout.setVerticalGroup(
            tampilLayanan1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 817, Short.MAX_VALUE)
        );

        mainPanel7.add(tampilLayanan1, "card4");

        javax.swing.GroupLayout tambahHargaLayananLayout = new javax.swing.GroupLayout(tambahHargaLayanan);
        tambahHargaLayanan.setLayout(tambahHargaLayananLayout);
        tambahHargaLayananLayout.setHorizontalGroup(
            tambahHargaLayananLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        tambahHargaLayananLayout.setVerticalGroup(
            tambahHargaLayananLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tambahHargaLayananLayout.createSequentialGroup()
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(mainPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        MainPanel.add(tambahHargaLayanan, "card3");

        javax.swing.GroupLayout backgroundLayout = new javax.swing.GroupLayout(background);
        background.setLayout(backgroundLayout);
        backgroundLayout.setHorizontalGroup(
            backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backgroundLayout.createSequentialGroup()
                .addComponent(menuHome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(MainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 1135, Short.MAX_VALUE))
        );
        backgroundLayout.setVerticalGroup(
            backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(MainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addComponent(menuHome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

    private void txtCariUkuranHewanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCariUkuranHewanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCariUkuranHewanActionPerformed

    private void btlHapusUkuranHewanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btlHapusUkuranHewanActionPerformed
        // TODO add your handling code here:
        try
        {
            String cari =(String)jComboBoxUkuran.getSelectedItem();
            if(txtCariUkuranHewan.getText().equalsIgnoreCase(" "))
            {
                JOptionPane.showMessageDialog(this, "Tidak Ada Data Yang Dihapus");
            }
            else
            {
                if (JOptionPane.showConfirmDialog(null, "Yakin Hapus?", "Yakin?",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION)
                {

                    AC.deletUkuranHewan(cari);
                    jComboBoxUkuran.removeItem(jComboBoxUkuran.getSelectedItem());
                    jComboBoxUkuranHewan.removeItem(cari);
                    JOptionPane.showMessageDialog(this, "Data Berhasil Dihapus..");

                    jComboBoxUkuran.setSelectedIndex(0);
                    txtCariUkuranHewan.setText(" ");
                    tampilUkuranHewan();
                    tampilHargaLayanan();
                }
            }
        }catch(Exception e)
        {
            System.out.println(e);
        }

    }//GEN-LAST:event_btlHapusUkuranHewanActionPerformed
    public  void cekEditNamaProdukSama() throws dataSama
    {
        
        Pr = AC.searchPro(txtCariNamaP.getText() );
        if (Pr!=null && txtCariNamaP.getText().equalsIgnoreCase(mencariNamaProduk.getText())==false) {
            throw  new dataSama();
            
        }
    
    }
    public void cekEditUkuranDataSama() throws dataSama
    {
        String ukuran=(String) jComboBoxUkuran.getSelectedItem();
        Uh =AC.searchUkuran(txtCariUkuranHewan.getText() );
        if (Uh!=null && txtCariUkuranHewan.getText().equalsIgnoreCase(ukuran)==false) {
            throw  new dataSama();
            
        }

    }
    private void btnSimpanHewan2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanHewan2ActionPerformed
        // TODO add your handling code here:
        String ukuran = (String)jComboBoxUkuran.getSelectedItem();
        try
        {
                cekEditUkuranDataSama();
                Uh= new UkuranHewan();
                Uh.setNama(txtCariUkuranHewan.getText());
                AC.editUkuranHewan(Uh, ukuran);
                JOptionPane.showMessageDialog(this, "Edit Berhasil");
                if (txtCariUkuranHewan.getText().equalsIgnoreCase(ukuran)==false) {
                    jComboBoxUkuran.addItem(txtCariUkuranHewan.getText());
                    jComboBoxUkuran.removeItem(ukuran);
               
            }
                jComboBoxUkuran.setSelectedIndex(0);
                txtCariUkuranHewan.setText(" ");
                tampilUkuranHewan();
                tampilHargaLayanan();
                
        }
        catch(dataSama ds)
        {
            JOptionPane.showMessageDialog(this, ds.dataUkuran());
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
      
    }//GEN-LAST:event_btnSimpanHewan2ActionPerformed

    private void jComboBoxUkuranActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxUkuranActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxUkuranActionPerformed

    private void jComboBoxUkuranMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboBoxUkuranMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxUkuranMouseClicked

    private void btnCariUkuranHewanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCariUkuranHewanMouseClicked
        // TODO add your handling code here:

        String cari= (String)jComboBoxUkuran.getSelectedItem();

        Uh = AC.searchUkuran(cari);
        if(Uh!=null)
        {
            txtCariUkuranHewan.setText(Uh.getNama());
        }
        else
        {
            JOptionPane.showMessageDialog(this, "Data Tidak Ditemukan");
        }

    }//GEN-LAST:event_btnCariUkuranHewanMouseClicked

    private void txtUkuranHewanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUkuranHewanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUkuranHewanActionPerformed

    private void btlSimpan2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btlSimpan2ActionPerformed
        // TODO add your handling code here:
        txtUkuranHewan.setText(" ");
    }//GEN-LAST:event_btlSimpan2ActionPerformed

    private void btnSimpanUkuranActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanUkuranActionPerformed
        // TODO add your handling code here:
        try
        {
            cekDataUkuranKosong();
            cekDataUkuran();
            UkuranHewan uh = new UkuranHewan();

            uh.setNama(txtUkuranHewan.getText());
            AC.tambahUkuranHewan(uh);
            jComboBoxUkuran.addItem(txtUkuranHewan.getText());
            jComboBoxUkuranHewan.addItem(txtUkuranHewan.getText());
            jComboBoxUkuranHewan1.addItem(txtUkuranHewan.getText());    
            setTextDataUkuran();
            tampilUkuranHewan();
            
            
            JOptionPane.showMessageDialog(this, "DATA UKURAN BERHASIL DISIMPAN");
        }
        catch(dataKosong dk)
        {
            JOptionPane.showMessageDialog(this, dk.message());
        }
        catch(dataSama ds)
        {
            JOptionPane.showMessageDialog(this, ds.dataUkuran());
        }
        catch(Exception e)
        {
            System.out.println(e);
        }

    }//GEN-LAST:event_btnSimpanUkuranActionPerformed

    private void btnCariUkuranActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariUkuranActionPerformed
        // TODO add your handling code here:
        mainPanel10.removeAll();
        mainPanel10.repaint();
        mainPanel10.revalidate();
        mainPanel10.add(cariUkuranHewan);
        mainPanel10.repaint();
        mainPanel10.revalidate();
    }//GEN-LAST:event_btnCariUkuranActionPerformed

    private void btnTampilLyn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTampilLyn1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnTampilLyn1ActionPerformed

    private void btnHelp3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHelp3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnHelp3ActionPerformed

    private void btnTambahUkuranActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahUkuranActionPerformed
        // TODO add your handling code here:
        mainPanel10.removeAll();
        mainPanel10.repaint();
        mainPanel10.revalidate();
        mainPanel10.add(tambahUkuranHewan);
        mainPanel10.repaint();
        mainPanel10.revalidate();
    }//GEN-LAST:event_btnTambahUkuranActionPerformed

    private void txtCariNamaHwnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCariNamaHwnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCariNamaHwnActionPerformed

    private void btlHapusHewanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btlHapusHewanActionPerformed
        // TODO add your handling code here:
        try
        {
            String cari = cariJenisHewan.getText();

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
                    tampilJenisHewanText();

                    txtCariNamaHwn.setText(" ");
                    temp= 3 ;
                    tampilJenisHewan();
                                        autoCompletJenisHewan(cariJenisHewan.getText());
                    cariJenisHewan.setText(" ");
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

    public void cekEditJenisHewan() throws dataSama
    {
        String cari = cariJenisHewan.getText();
        

        jh= AC.searchJenisHewan(txtCariNamaHwn.getText());
        if(jh!=null && txtCariNamaHwn.getText().equalsIgnoreCase(cari)==false)
        {
            txtCariNamaHwn.setText(" ");

            throw  new dataSama();
        }
    }
    private void btnSimpanHewanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanHewanActionPerformed
        // TODO add your handling code here:
        String cari = cariJenisHewan.getText();
        try {
            cekEditJenisHewan();
            jh= new JenisHewan();
            jh.setNama(txtCariNamaHwn.getText());
            AC.editJenisHewan(jh, cari);
            if (txtCariNamaHwn.getText().equalsIgnoreCase(cari)==false) {
     
                tampilJenisHewanText();
                autoCompletJenisHewan(txtCariNamaHwn.getText());
            }
            txtCariNamaHwn.setText(" ");

             tampilJenisHewan();
            
        }
        catch(dataSama ds)
        {
            JOptionPane.showMessageDialog(this, ds.dataSama());
        }
        catch (Exception e) {
        }
    }//GEN-LAST:event_btnSimpanHewanActionPerformed

    private void btnSearchJenisHewanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSearchJenisHewanMouseClicked
        // TODO add your handling code here:
        String cari = cariJenisHewan.getText();
        jh = AC.searchJenisHewan(cari);
        if(jh!=null)
        {
            txtCariNamaHwn.setText(jh.getnNama());
            tampilJenisHewan();
        }
    }//GEN-LAST:event_btnSearchJenisHewanMouseClicked

    private void txtNamaHewanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNamaHewanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNamaHewanActionPerformed

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        // TODO add your handling code here:
        try
        {
            cekDataHewan();
            cekHurufHewan();
            cekNamaHewan();

            JenisHewan jhs = new JenisHewan();
            jhs.setNama(txtNamaHewan.getText());
            AC.tambahJenisHewan(jhs);

            JOptionPane.showMessageDialog(this, "Data Hewan Berhasil Di Tambah ..");
                        tampilJenisHewanText();
            autoCompletJenisHewan(txtNamaHewan.getText());
           
            txtNamaHewan.setText(" ");

            tampilJenisHewan();

        }
        catch(dataKosong dk)
        {
            JOptionPane.showMessageDialog(this, dk.message());
        }
        catch(CekHuruf ch)
        {
            JOptionPane.showMessageDialog(this, ch.cekHuruf());
        }
        catch(dataSama ds)
        {
            JOptionPane.showMessageDialog(this, ds.jenisHewan());
        }
        catch(Exception e)
        {
            System.out.println("gagal Menambahkan");
            System.out.println(e);
        }

    }//GEN-LAST:event_btnSimpanActionPerformed

    private void btnCariJenisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariJenisActionPerformed
        // TODO add your handling code here:
        mainPanel8.removeAll();
        mainPanel8.repaint();
        mainPanel8.revalidate();
        mainPanel8.add(CariHewan);
        mainPanel8.repaint();
        mainPanel8.revalidate();

    }//GEN-LAST:event_btnCariJenisActionPerformed

    private void btnTampilSup1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTampilSup1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnTampilSup1ActionPerformed

    private void btnSupHelp1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSupHelp1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSupHelp1ActionPerformed

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

    private void jComboBoxSupllierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxSupllierActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxSupllierActionPerformed

    private void jComboBoxSupllierMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboBoxSupllierMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxSupllierMouseClicked

    private void txtCariTelpSupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCariTelpSupActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCariTelpSupActionPerformed

    private void txtCariNamaSupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCariNamaSupActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCariNamaSupActionPerformed

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
    
    public void cekeditNamaSupllier()throws dataSama
    {
        String cari = (String)jComboBoxSupllier.getSelectedItem();
        
        S= AC.searchSup(txtCariNamaSup.getText());
        if (S!=null && txtCariNamaSup.getText().equalsIgnoreCase(cari)==false) {
            
            throw  new dataSama();
        }
    }
    private void btnSimpanProduk2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanProduk2ActionPerformed
        // TODO add your handling code here:
        
        String cari =(String) jComboBoxSupllier.getSelectedItem();
        try {
            cekeditNamaSupllier();
            S= new Suplier();
            S.setNama(txtCariNamaSup.getText());
            S.setAlamat(txtCariAlaSup.getText());
            S.setTelp(txtCariTelpSup.getText());
            AC.editSupplier(S,cari );
            if (txtCariNamaSup.getText().equalsIgnoreCase(cari)==false) {
                jComboBoxSupllier.addItem(txtCariNamaSup.getText());
                jComboBoxSupllier.removeItem(cari);
                
            }
            txtCariNamaSup.setText(" ");
            txtCariAlaSup.setText(" ");
            txtCariTelpSup.setText(" ");
            jComboBoxSupllier.setSelectedIndex(0);
            tampliSuplier();
        }
        catch(dataSama ds)
        {
            JOptionPane.showMessageDialog(this, ds.layananSama());
        }
        catch (Exception e) {
        }
        
        
    }//GEN-LAST:event_btnSimpanProduk2ActionPerformed

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

    private void txtNamSupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNamSupActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNamSupActionPerformed

    private void txtTelpSupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTelpSupActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTelpSupActionPerformed

    private void btnSimpanSuplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanSuplierActionPerformed
        // TODO add your handling code here:
        try
        {
            ExceptionDataSuplier();
            cekAngkaTelpSup();
            noTelpSup();
            cekNamaSuplier();
            S = new Suplier();
            S.setNama(txtNamSup.getText());
            S.setTelp(txtTelpSup.getText());
            S.setAlamat(txtAlamatSup.getText());
            AC.tambahSuplier(S);
            tampliSuplier();
            jComboBoxSupllier.addItem(txtNamSup.getText());
            setTextSuplier();
            JOptionPane.showMessageDialog(this, "Berhasil Menambahkan Suplier");
        }
        catch (dataKosong dk )
        {
            JOptionPane.showMessageDialog(this,dk.message());
        }
        catch(CekAngka ca)
        {
            JOptionPane.showMessageDialog(this, ca.dataHanyaHuruf());
        }
        catch(PanjangData pd)
        {
            JOptionPane.showMessageDialog(this,pd.message());
        }
        catch(dataSama ds)
        {
            JOptionPane.showMessageDialog(this, ds.suplierSudahTerdaftar());
        }
        catch(Exception e)
        {
            System.out.println("Gagal Tambah");
            System.out.println(e);
        }
    }//GEN-LAST:event_btnSimpanSuplierActionPerformed

    private void btnCari1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCari1ActionPerformed
        // TODO add your handling code here:
        mainPanel5.removeAll();
        mainPanel5.repaint();
        mainPanel5.revalidate();
        mainPanel5.add(cariProduk);
        mainPanel5.repaint();
        mainPanel5.revalidate();
    }//GEN-LAST:event_btnCari1ActionPerformed

    private void btnTampil1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTampil1ActionPerformed
        // TODO add your handling code here:
        mainPanel5.removeAll();
        mainPanel5.repaint();
        mainPanel5.revalidate();
        mainPanel5.add(tampilProduk);
        mainPanel5.repaint();
        mainPanel5.revalidate();
    }//GEN-LAST:event_btnTampil1ActionPerformed

    private void btnHelp2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHelp2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnHelp2ActionPerformed

    private void btnTambah1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambah1ActionPerformed
        // TODO add your handling code here:
        mainPanel5.removeAll();
        mainPanel5.repaint();
        mainPanel5.revalidate();
        mainPanel5.add(tambahProduk);
        mainPanel5.repaint();
        mainPanel5.revalidate();
    }//GEN-LAST:event_btnTambah1ActionPerformed

    private void txtNamaLayananCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNamaLayananCariActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNamaLayananCariActionPerformed

    private void btlHapusHewan1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btlHapusHewan1ActionPerformed
        // TODO add your handling code here:
        try
        {
            String cari = txtCariLayanan.getText();

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
                      txtCariLayanan.setText(" ");

                    tampilLayanan();
                    tampilHargaLayanan();
                    tampilLayananText();
                    autoCompletNamaLayanan(cari);
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

    public void cekEditLayanan() throws dataSama
    {
        String cari =txtNamaLayanan.getText();
        L = AC.searchLayanan(cari);
        if(L!=null && txtNamaLayananCari.getText().equalsIgnoreCase(cari))
        {
           txtNamaLayananCari.setText(" ");
            throw new dataSama();
        }
    }
    private void btnSimpanHewan1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanHewan1ActionPerformed
        // TODO add your handling code here:
        String cari = txtCariLayanan.getText();
        
        try {
            cekEditLayanan();
            L= new Layanan();
            L.setNamaLayanan(txtNamaLayananCari.getText());
            AC.editLayanan(L, cari);
            
             tampilLayanan();
             tampilLayananText();
             autoCompletNamaLayanan(cari);
             txtCariLayanan.setText(" ");
             txtNamaLayananCari.setText(" ");
             JOptionPane.showMessageDialog(this, "Data Layanan Berhasil Di Ubah");
             tampilHargaLayanan();
        }
        catch(dataSama ds)
        {
            JOptionPane.showMessageDialog(this, ds.layananSama());
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_btnSimpanHewan1ActionPerformed

    private void btnCariLayananMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCariLayananMouseClicked
        // TODO add your handling code here:
        String cari =  txtNamaLayanan.getText();
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

    private void txtNamaLayananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNamaLayananActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNamaLayananActionPerformed

    private void btlSimpan1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btlSimpan1ActionPerformed
        // TODO add your handling code here:
        txtNamaLayanan.setText(" ");
    }//GEN-LAST:event_btlSimpan1ActionPerformed

    private void btnSimpanLynActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanLynActionPerformed
        // TODO add your handling code here:
        try
        {
            exceptionDataLayanan();
            namaLayanan();
            L= new Layanan();
            L.setNamaLayanan(txtNamaLayanan.getText());
            
            AC.tambahLayanan(L);

            JOptionPane.showMessageDialog(this, "Layanan Berhasil Ditambahkan");
            tampilLayanan();
            tampilLayananText();
            jComboBoxNamaLayanan.addItem(txtNamaLayanan.getText());
                        jComboBoxNamaLayanan1.addItem(txtNamaLayanan.getText());
            txtNamaLayanan.setText(" ");
                
        }
        catch(dataKosong dk)
        {
            JOptionPane.showMessageDialog(this, dk.message());
        }
        catch(dataSama e)
        {
            JOptionPane.showMessageDialog(this, e.layananSama());
        }
    }//GEN-LAST:event_btnSimpanLynActionPerformed

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

    private void btnTampilLynActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTampilLynActionPerformed
        // TODO add your handling code here:
        mainPanel3.removeAll();
        mainPanel3.repaint();
        mainPanel3.revalidate();
        mainPanel3.add(tampilLayanan);
        mainPanel3.repaint();
        mainPanel3.revalidate();

    }//GEN-LAST:event_btnTampilLynActionPerformed

    private void btnHelp1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHelp1ActionPerformed
        // TODO add your handling code here:
        mainPanel3.removeAll();
        mainPanel3.repaint();
        mainPanel3.revalidate();
        mainPanel3.add(tampilLayanan);
        mainPanel3.repaint();
        mainPanel3.revalidate();
    }//GEN-LAST:event_btnHelp1ActionPerformed

    private void btnTambahLynActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahLynActionPerformed
        // TODO add your handling code here:
        mainPanel3.removeAll();
        mainPanel3.repaint();
        mainPanel3.revalidate();
        mainPanel3.add(tambahLayanan);
        mainPanel3.repaint();
        mainPanel3.revalidate();
    }//GEN-LAST:event_btnTambahLynActionPerformed

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
                    tampilUserNamePegawai();
                }
            }

            setTextCariPegawai();

        }
        catch(Exception e)
        {
            System.out.println(e);
        }

    }//GEN-LAST:event_btnHapusActionPerformed

    private void btnSimpanEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanEditActionPerformed
        // TODO add your handling code here:
//        String originalPassword ;
//        originalPassword = txtPassword.getText();
//        String generatedSecuredPasswordHash;
//        BCrypt bcrp=new BCrypt();
//        boolean matched;
//        if (originalPassword == null || "".equals(originalPassword)) {
//
//            txtPassword.requestFocus();
//            return;
//        }
//        generatedSecuredPasswordHash = bcrp.hashpw(originalPassword, BCrypt.gensalt(12));
//        matched= bcrp.checkpw(originalPassword, generatedSecuredPasswordHash);
//        String password =Boolean.toString(matched) ;

        try
        {
        
                        userNameSama();
            P = new Pegawai();

            P.setAlamat(txtCariAlamat.getText());
            P.setNama(txtCariNama.getText());
            P.setNoHp(txtCariNoTlp.getText());
           
            P.setUserName(txtCariUserName.getText());
            String format = "yyyy-MM-dd";
            SimpleDateFormat fm = new SimpleDateFormat(format);
            String tanggal = String.valueOf(fm.format(tglLahirPegawai1.getDate()));
           P.setTglLahir(tanggal);
            if(kasir.isSelected())
            {
                P.setRole("Kasir");
            }
            else if(cs.isSelected())
            {
                P.setRole("Customer Service");
            }
            AC.editPegawai(P,txtCari.getText());
            tampilUserNamePegawai();
            tampilUserNamePegawai();
            setTextCariPegawai();
            cari.clearSelection();
            JOptionPane.showMessageDialog(this, "Data Berhasil Diedit");

        }
        catch(dataSama c)
        {
            JOptionPane.showMessageDialog(this, c.dataSama());
        }
        catch(Exception  e)
        {
            System.out.println(e);
        }
    }//GEN-LAST:event_btnSimpanEditActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        // TODO add your handling code here:
        disspegawai(true);
    }//GEN-LAST:event_btnEditActionPerformed

    private void txtCariNoTlpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCariNoTlpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCariNoTlpActionPerformed

    private void txtCariNamaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCariNamaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCariNamaActionPerformed

    private void btnSearchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSearchMouseClicked
        // TODO add your handling code here:

        P=  AC.searchPegawai(txtCari.getText());
        if(P!=null)
        {
            
            String noHp = String.valueOf(P.getNoHp());
            txtCariNama.setText(P.getNamaPegawai());
            txtCariNoTlp.setText(noHp);
          
            txtCariAlamat.setText(P.getAlamat());
            txtCariUserName.setText(P.getUserName());
            String tgl = P.getTglLahir();
            
            Date date = null ;
            
                       try {
                     date = new SimpleDateFormat("yyyy-MM-dd").parse(tgl);

                      tglLahirPegawai1.setDate(date);
              } catch (ParseException ex) {
                  Logger.getLogger(MenuCS.class.getName()).log(Level.SEVERE, null, ex);
              }

            
            if(P.getRole().equals("Customer Service"))
            {

                cs.setSelected(true);
            }
            else if(P.getRole().equals("Kasir"))
            {
                kasir.setSelected(true);
            }

        }
        else
        {
            JOptionPane.showMessageDialog(this, "User Name Pegawai Tidak Ditemukan !");
        }
    }//GEN-LAST:event_btnSearchMouseClicked

    private void txtCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCariActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCariActionPerformed

    private void rdCustomerServiceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdCustomerServiceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdCustomerServiceActionPerformed

    private void rdCustomerServiceMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rdCustomerServiceMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_rdCustomerServiceMouseClicked

    private void rdKasirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdKasirActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdKasirActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        setText();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void btnSimpanPegawaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanPegawaiActionPerformed
        // TODO add your handling code here:

        try
        {
            //            P = new  Pegawai (String namaPegawai , Date tglLahir , String role ,int  noHp ,String alamat , String userName , String password )
            exceptiondataKosong();
            cekAngka();
            cekHuruf();
            userNameSama();
            cekNomorTelephonePegawai();
            String role ;
            if(rdCustomerService.isSelected())
            {

                role="Customer Service";

            }
            else
            {

                role="Kasir";
            }

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
        catch(dataKosong dk)
        {
            JOptionPane.showMessageDialog(this, dk.message());
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
        catch(PanjangData pd)
        {
            JOptionPane.showMessageDialog(this, pd.message());
        }

    }//GEN-LAST:event_btnSimpanPegawaiActionPerformed

    private void txtNoTelpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNoTelpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNoTelpActionPerformed

    private void txtNamaPegawaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNamaPegawaiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNamaPegawaiActionPerformed

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
        tampilPegawai();

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
        mainPanel2.add(tambahPegawai);
        mainPanel2.repaint();
        mainPanel2.revalidate();

    }//GEN-LAST:event_btnTambahActionPerformed

    private void btnUkuranActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUkuranActionPerformed
        // TODO add your handling code here:
        MainPanel.removeAll();
        MainPanel.repaint();
        MainPanel.revalidate();
        MainPanel.add(dataUkuran);
        MainPanel.repaint();
        MainPanel.revalidate();
    }//GEN-LAST:event_btnUkuranActionPerformed

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

    private void txtJumStokActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtJumStokActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtJumStokActionPerformed

    private void txtHargaProdukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtHargaProdukActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHargaProdukActionPerformed

    private void txtNamaProdukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNamaProdukActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNamaProdukActionPerformed

    private void txtMinStokActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMinStokActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMinStokActionPerformed

    private void btnSimpanProdukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanProdukActionPerformed
        // TODO add your handling code here:
        try
        {
        Produk         Pr= new Produk();
      
            ExceptionDataProdu();
            cekJumlahStok();
            cekHarha();
            cekMinStok();
            namaProduk();
            
                int Jum= Integer.parseInt(txtJumStok.getText());
                int Harga= Integer.parseInt(txtHargaProduk.getText());
                int minStok= Integer.parseInt(txtMinStok.getText());
                Pr.setNama(txtNamaProduk.getText());
                Pr.setSatuan(txtSatun.getText());
                Pr.setJumlah(Jum);
                Pr.setHarga(Harga);
                Pr.setMin_Stok(minStok);
                Pr.setGambar(namaGambar.getText());
                AC.tambahProduk(Pr);
            try {
            String path=new File(".").getCanonicalPath();
            FileUtils.copyFileToDirectory(file, new File(path+"/image")); //copy file ke folder image
        } catch (IOException ex) {
            Logger.getLogger(MenuAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
            JOptionPane.showMessageDialog(this, "Data Produk Berhasil Ditambahkan");
//            jComboBoxCoba.addItem(txtNamaProduk.getText());
//            jComboBoxNamaLayanan.addItem(txtNamaLayanan.getText());
            tampilProduk();
            tampilNamaProduk();
            setTextDataProduk();
            tampilHargaLayanan();
            
            lblImage.setIcon(null);
        }
        catch(CekAngka ca)
        {
            JOptionPane.showMessageDialog(this, ca.harga());
        }
        catch(dataKosong dk)
        {
            JOptionPane.showMessageDialog(this, dk.message());
        }
        catch(dataSama ds)
        {
            JOptionPane.showMessageDialog(this, ds.namaProduk());

        }
        
    }//GEN-LAST:event_btnSimpanProdukActionPerformed

    private void txtJumStokPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtJumStokPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtJumStokPActionPerformed

    private void txtHargaProdukpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtHargaProdukpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHargaProdukpActionPerformed

    private void txtCariNamaPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCariNamaPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCariNamaPActionPerformed

    private void txtMinStokPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMinStokPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMinStokPActionPerformed

    private void btlHapusProdukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btlHapusProdukActionPerformed
        // TODO add your handling code here:

        try
        {
           
            if(txtCariNamaP.getText().equalsIgnoreCase(""))
            {
                JOptionPane.showMessageDialog(this, "Tidak Ada Yang Di Hapus");
            }
            else
            {
                if (JOptionPane.showConfirmDialog(null, "Yakin Hapus?", "Yakin?",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION)
                {
                    AC.deleteProduk(mencariNamaProduk.getText());

                    JOptionPane.showMessageDialog(this, "Data Berhasil Dihapus..");

                    txtCariNamaP.setText(" ");
                    txtSatunP.setText(" ");
                    txtJumStokP.setText(" ");
                    txtHargaProdukp.setText(" ");
                    txtMinStokP.setText(" ");
                    mencariNamaProduk.setText(" ");
                    tampilGambar.setIcon(null);
                    temp= 1 ;

                    tampilNamaProduk();
                    tampilProduk();
                    String txt = mencariNamaProduk.getText();
                autoCompletNamaProduk(txt);
                    tampilHargaLayanan();
                }
            }

            setTextCariPegawai();

        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }//GEN-LAST:event_btlHapusProdukActionPerformed

    private void btnSimpanProduk1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanProduk1ActionPerformed
        // TODO add your handling code here:

        try
        {
                cekEditNamaProdukSama();
               Pr= new  Produk();
               Pr.setNama(txtCariNamaP.getText());
               Pr.setSatuan(txtSatunP.getText());
               int jumlah  = Integer.parseInt(txtJumStokP.getText());
               Pr.setJumlah(jumlah);
               int harga= Integer.parseInt(txtHargaProdukp.getText());
               Pr.setHarga(harga);
               Pr.setGambar(namaGambar1.getText());
               int min = Integer.parseInt(txtMinStokP.getText());
               Pr.setMin_Stok(min);
               AC.editProduk(Pr, mencariNamaProduk.getText());
                                 txtCariNamaP.setText(" ");
                    txtSatunP.setText(" ");
                    txtJumStokP.setText(" ");
                    txtMinStokP.setText(" ");
                    txtHargaProdukp.setText(" ");
                    tampilGambar.setIcon(null);
                if (txtCariNamaP.getText().equalsIgnoreCase(mencariNamaProduk.getText())==false) {
                    txtCariNamaP.setText(" ");
                    txtSatunP.setText(" ");
                    txtJumStokP.setText(" ");
                    txtMinStokP.setText(" ");
                    txtHargaProdukp.setText(" ");
                    tampilGambar.setIcon(null);
            }
               try {
                String path=new File(".").getCanonicalPath();
                FileUtils.copyFileToDirectory(file, new File(path+"/image")); //copy file ke folder image
                } catch (IOException ex) {
            Logger.getLogger(MenuAdmin.class.getName()).log(Level.SEVERE, null, ex);
            }
               JOptionPane.showMessageDialog(this, "Edit Berhasil");
               
               
                tampilNamaProduk();
                
                tampilProduk();
                
        }
        catch(Exception e)
        {
            System.out.println(e);
            System.out.println("Gagal EDIT ");
        }
    }//GEN-LAST:event_btnSimpanProduk1ActionPerformed

    private void btnSearchProdukMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSearchProdukMouseClicked
        // TODO add your handling code here:


        Pr=  AC.searchPro(mencariNamaProduk.getText());
        if(Pr!=null)
        {           
            
            
              try
              {
                  Toolkit toolkit=Toolkit.getDefaultToolkit();
                    
                    String path=new File(".").getCanonicalPath();
                    Image image=toolkit.getImage(path+"/image/"+Pr.getGambar()); //mengambil gambar dari folder image
                    Image imagedResized=image.getScaledInstance(200, 250, Image.SCALE_DEFAULT); //resize foto sesuai ukuran jlabel
                    ImageIcon icon=new ImageIcon(imagedResized);
                    tampilGambar.setIcon(icon); // memasang gambar pada jlabel
            
              }
              catch(Exception e)
              {
                  System.out.println(e);
              }
             
            
            
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

    private void btnTampilSupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTampilSupActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnTampilSupActionPerformed

    private void btnSupHelpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSupHelpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSupHelpActionPerformed

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

    private void btnTambahLyn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahLyn1ActionPerformed
        // TODO add your handling code here:
        mainPanel7.removeAll();
        mainPanel7.repaint();
        mainPanel7.revalidate();
        mainPanel7.add(tambahLayanan2);
        mainPanel7.repaint();
        mainPanel7.revalidate();
        setText();
    }//GEN-LAST:event_btnTambahLyn1ActionPerformed

    private void btnHelp4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHelp4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnHelp4ActionPerformed

    private void btnTampilLyn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTampilLyn2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnTampilLyn2ActionPerformed

    private void btnCariLyn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariLyn1ActionPerformed
        // TODO add your handling code here:
        mainPanel7.removeAll();
        mainPanel7.repaint();
        mainPanel7.revalidate();
        mainPanel7.add(cariLayanan1);
        mainPanel7.repaint();
        mainPanel7.revalidate();
        setText();
    }//GEN-LAST:event_btnCariLyn1ActionPerformed

    private void btnSimpanLyn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanLyn2ActionPerformed
        // TODO add your handling code here:
        
        
           String cari =  (String)jComboBoxNamaLayanan.getSelectedItem();
           String cari2 = (String)jComboBoxUkuranHewan.getSelectedItem();
           L=  AC.searchLayanan(cari);
           Uh= AC.searchUkuran(cari2);
            if(L!=null && Uh!=null)
            {
            try {
                HL = new Harga_Layanan();
                int  ID= L.getIdLayanan();
                int IDX= Uh.getID();
                int Harga = Integer.parseInt(txthargaLayanan.getText());
                HL.setHarga(Harga);
                HL.setIdLayanan(ID);
                HL.setIdUkuran(IDX);
                AC.tambahHargaLayanan(HL); 
                String id = String.valueOf(IDX);
                cariHargaLayanan(id);
                tampilHargaLayanan();
               JOptionPane.showMessageDialog(this, "Data Berhasil Ditambah");
            } catch (Exception e) {
                System.out.println(e);
            }
            
        }
        else
        {
            JOptionPane.showMessageDialog(this,"Layanan Tida Ditemukan .");
        }
    }//GEN-LAST:event_btnSimpanLyn2ActionPerformed

    private void btlSimpan6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btlSimpan6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btlSimpan6ActionPerformed

    private void btnCariLayanan1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCariLayanan1MouseClicked
        // TODO add your handling code here:
        
    String cari = cariIdHargaLyn.getText();
    Harga_Layanan hl = new Harga_Layanan();
    
    hl=cariHargaLayanan(cari);
    if(hl==null)
    {
        JOptionPane.showMessageDialog(this,"ID Tidak Ditemukan");
    }
        
   
    }//GEN-LAST:event_btnCariLayanan1MouseClicked

    private void btnSimpanHewan3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanHewan3ActionPerformed
        // TODO add your handling code here:
        
           String cari =  (String)jComboBoxNamaLayanan1.getSelectedItem();
           String cari2 = (String)jComboBoxUkuranHewan1.getSelectedItem();
           L=  AC.searchLayanan(cari);
           Uh= AC.searchUkuran(cari2);
            if(L!=null && Uh!=null)
            {
            try {
                HL = new Harga_Layanan();
                int  ID= L.getIdLayanan();
                int IDX= Uh.getID();
                int Harga = Integer.parseInt(txtNamaLayananHarga2.getText());
                HL.setHarga(Harga);
                HL.setIdLayanan(ID);
                HL.setIdUkuran(IDX);
                int idCari = Integer.parseInt(cariIdHargaLyn.getText());
                AC.editHargaLayanan(HL,idCari);
                txtNamaLayananHarga2.setText(" ");
                jComboBoxNamaLayanan1.setSelectedIndex(0);
                jComboBoxUkuranHewan1.setSelectedIndex(0);
                cariIdHargaLyn.setText(" ");
                String id = String.valueOf(IDX);
                
                tampilHargaLayanan();
               JOptionPane.showMessageDialog(this, "Data Berhasil Diubah");
              
            } catch (Exception e) {
                System.out.println(e);
            }
            
        }
        
    }//GEN-LAST:event_btnSimpanHewan3ActionPerformed

    private void btlHapusHewan2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btlHapusHewan2ActionPerformed
        // TODO add your handling code here:
        try
        {
            String cari = cariIdHargaLyn.getText();
            String hapus = (String)jComboBoxNamaLayanan1.getSelectedItem();
            if(hapus.equalsIgnoreCase(""))
            {
                JOptionPane.showMessageDialog(this, "Tidak Ada Yang Di Hapus");
            }
            else
            {
                if (JOptionPane.showConfirmDialog(null, "Yakin Hapus?", "Yakin?",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION)
                {
                    AC.deleteHargaLayanan(cari);

                    JOptionPane.showMessageDialog(this, "Data Berhasil Dihapus..");

                    txtNamaLayananHarga2.setText(" ");
                    temp= 3 ;

                    tampilHargaLayanan();

                }
            }

            setTextCariPegawai();
        }
        catch(Exception e)
        {
            System.out.println("Gagal Hapus");
            System.out.println(e);
        }
    }//GEN-LAST:event_btlHapusHewan2ActionPerformed

    private void jComboBoxNamaLayananMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboBoxNamaLayananMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxNamaLayananMouseClicked

    private void jComboBoxNamaLayananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxNamaLayananActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxNamaLayananActionPerformed

    private void jComboBoxUkuranHewanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboBoxUkuranHewanMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxUkuranHewanMouseClicked

    private void jComboBoxUkuranHewanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxUkuranHewanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxUkuranHewanActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
         MainPanel.removeAll();
        MainPanel.repaint();
        MainPanel.revalidate();
        MainPanel.add(tambahHargaLayanan);
        MainPanel.repaint();
        MainPanel.revalidate();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtNamaLayananHarga2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNamaLayananHarga2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNamaLayananHarga2ActionPerformed

    private void btlSimpan3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btlSimpan3ActionPerformed
        // TODO add your handling code here:
          jfc=new JFileChooser();
        if(jfc.showOpenDialog(lblImage)==JFileChooser.APPROVE_OPTION){
            
            Toolkit toolkit=Toolkit.getDefaultToolkit();
            Image image=toolkit.getImage(jfc.getSelectedFile().getAbsolutePath());
            Image imagedResized=image.getScaledInstance(lblImage.getWidth(), lblImage.getHeight(), Image.SCALE_SMOOTH);
            ImageIcon imageIcon=new ImageIcon(imagedResized);
            
            lblImage.setIcon(imageIcon);
            namaGambar.setText(jfc.getSelectedFile().getName());
            
            file=new File(jfc.getSelectedFile().getPath()); // file untuk dikopi
        }
    }//GEN-LAST:event_btlSimpan3ActionPerformed

    private void txtCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCariKeyPressed

        // TODO add your handling code here:
        switch(evt.getKeyCode())
        {
            case KeyEvent.VK_BACK_SPACE:
                break ;
            case KeyEvent.VK_ENTER:
                txtCari.setText(txtCari.getText());
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            P=  AC.searchPegawai(txtCari.getText());
        if(P!=null)
        {
            String noHp = String.valueOf(P.getNoHp());
            txtCariNama.setText(P.getNamaPegawai());
            txtCariNoTlp.setText(noHp);
  String tgl = P.getTglLahir();
            
            Date date = null ;
            
                       try {
                     date = new SimpleDateFormat("yyyy-MM-dd").parse(tgl);

                      tglLahirPegawai1.setDate(date);
              } catch (ParseException ex) {
                  Logger.getLogger(MenuCS.class.getName()).log(Level.SEVERE, null, ex);
              }

          
            txtCariAlamat.setText(P.getAlamat());
            txtCariUserName.setText(P.getUserName());
     
            
            if(P.getRole().equals("Customer Service"))
            {

                cs.setSelected(true);
            }
            else if(P.getRole().equals("Kasir"))
            {
                kasir.setSelected(true);
            }
        }
        else
        {
            JOptionPane.showMessageDialog(this, "User Name Pegawai Tidak Ditemukan !");
        }

        }            
         
                break;
                
            default :
                EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                String txt = txtCari.getText();
                autoCompletUserPegawai(txt);
                
            }
        });
                
                        
        }
    }//GEN-LAST:event_txtCariKeyPressed

    private void txtNamaProdukKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNamaProdukKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtSatun.requestFocus();
        }
    }//GEN-LAST:event_txtNamaProdukKeyPressed

    private void txtSatunKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSatunKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
           txtJumStok.requestFocus();
        }
    }//GEN-LAST:event_txtSatunKeyPressed

    private void txtJumStokKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtJumStokKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtHargaProduk.requestFocus();
        }
    }//GEN-LAST:event_txtJumStokKeyPressed

    private void txtHargaProdukKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtHargaProdukKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
             txtMinStok.requestFocus();
        }
    }//GEN-LAST:event_txtHargaProdukKeyPressed

    private void jComboBoxUkuranHewanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBoxUkuranHewanKeyPressed
    
        // TODO add your handling code here:
      if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
           jComboBoxUkuranHewan.requestFocus();
        }
    }//GEN-LAST:event_jComboBoxUkuranHewanKeyPressed

    private void jComboBoxNamaLayananKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBoxNamaLayananKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxNamaLayananKeyPressed

    private void mencariNamaProdukKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_mencariNamaProdukKeyPressed
        // TODO add your handling code here:
         switch(evt.getKeyCode())
        {
            case KeyEvent.VK_BACK_SPACE:
                if (evt.getKeyCode()==KeyEvent.VK_BACK_SPACE) {
                    
                txtCariNamaP.setText(" ");
                    txtSatunP.setText(" ");
                    txtJumStokP.setText(" ");
                    txtHargaProdukp.setText(" ");
                    txtMinStokP.setText(" ");
                    
                    tampilGambar.setIcon(null);
                }
                  
                break ;
            case KeyEvent.VK_ENTER:
                mencariNamaProduk.setText(mencariNamaProduk.getText());
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
           Pr=  AC.searchPro(mencariNamaProduk.getText());
        if(Pr!=null)
        {           
            
            
              try
              {
                  Toolkit toolkit=Toolkit.getDefaultToolkit();
                    
                    String path=new File(".").getCanonicalPath();
                    Image image=toolkit.getImage(path+"/image/"+Pr.getGambar()); //mengambil gambar dari folder image
                    Image imagedResized=image.getScaledInstance(200, 250, Image.SCALE_DEFAULT); //resize foto sesuai ukuran jlabel
                    ImageIcon icon=new ImageIcon(imagedResized);
                    tampilGambar.setIcon(icon); // memasang gambar pada jlabel
            
              }
              catch(Exception e)
              {
                  System.out.println(e);
              }
             
            
            
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
        }            
         
                break;
                
            default :
                EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                String txt = mencariNamaProduk.getText();
                autoCompletNamaProduk(txt);
                
            }
        });
                
                        
        }
    }//GEN-LAST:event_mencariNamaProdukKeyPressed

    private void txtCariKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCariKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCariKeyTyped

    private void txtSatunPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSatunPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSatunPActionPerformed

    private void txtCariLayananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCariLayananActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCariLayananActionPerformed

    private void txtCariLayananKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCariLayananKeyPressed
        // TODO add your handling code here:
        switch(evt.getKeyCode())
        {
            case KeyEvent.VK_BACK_SPACE:
                if (evt.getKeyCode()==KeyEvent.VK_BACK_SPACE) {
                    
                   txtNamaLayananCari.setText(" ");
                }
                  
                break ;
            case KeyEvent.VK_ENTER:
                txtCariLayanan.setText(txtCariLayanan.getText());
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
           L=  AC.searchLayanan(txtCariLayanan.getText());
        if(L!=null)
        {           
         
            txtNamaLayananCari.setText(L.getNamaLayanan());
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
                String txt = txtCariLayanan.getText();
                autoCompletNamaLayanan(txt);
                
            }
        });
                
                        
        }
    }//GEN-LAST:event_txtCariLayananKeyPressed

    private void cariIdHargaLynActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cariIdHargaLynActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cariIdHargaLynActionPerformed

    private void cariIdHargaLynKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cariIdHargaLynKeyPressed
        // TODO add your handling code here:
        switch(evt.getKeyCode())
        {
             case KeyEvent.VK_ENTER:
                cariIdHargaLyn.setText(cariIdHargaLyn.getText());
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                    Harga_Layanan hl ;
                    hl=cariHargaLayanan(cariIdHargaLyn.getText());
                    if (hl==null) {
                        JOptionPane.showMessageDialog(this, "Data Id Tidak Ditemukan");
                        
                    }
                }
        }
        
       
    }//GEN-LAST:event_cariIdHargaLynKeyPressed

    private void cariJenisHewanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cariJenisHewanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cariJenisHewanActionPerformed

    private void cariJenisHewanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cariJenisHewanKeyPressed
        // TODO add your handling code here:
        switch(evt.getKeyCode())
        {
            case KeyEvent.VK_BACK_SPACE:
                if (evt.getKeyCode()==KeyEvent.VK_BACK_SPACE) {
                    txtCariNamaHwn.setText(" ");
                }
                  
                break ;
            case KeyEvent.VK_ENTER:
                cariJenisHewan.setText(cariJenisHewan.getText());
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
           jh = AC.searchJenisHewan(cariJenisHewan.getText());
                if(jh!=null)
                {           

                    txtCariNamaHwn.setText(jh.getnNama());

                }
             
                
         

                else
                {
                    JOptionPane.showMessageDialog(this, "Jenis Hewan  Tidak Ditemukan");
                }
                    
         
                break;
                }        
            default :
                EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                String txt =    cariJenisHewan.getText();
                autoCompletJenisHewan(txt);
            }
        });
                
                        
        }

    }//GEN-LAST:event_cariJenisHewanKeyPressed

    private void btnSearchKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnSearchKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSearchKeyPressed

    private void kasirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kasirActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_kasirActionPerformed

    private void csActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_csActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_csActionPerformed

    private void jComboBoxNamaLayanan1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboBoxNamaLayanan1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxNamaLayanan1MouseClicked

    private void jComboBoxNamaLayanan1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxNamaLayanan1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxNamaLayanan1ActionPerformed

    private void jComboBoxNamaLayanan1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBoxNamaLayanan1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxNamaLayanan1KeyPressed

    private void jComboBoxUkuranHewan1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboBoxUkuranHewan1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxUkuranHewan1MouseClicked

    private void jComboBoxUkuranHewan1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxUkuranHewan1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxUkuranHewan1ActionPerformed

    private void jComboBoxUkuranHewan1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBoxUkuranHewan1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxUkuranHewan1KeyPressed

    private void btnPilihActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPilihActionPerformed
        // TODO add your handling code here:
         jfc=new JFileChooser();
        if(jfc.showOpenDialog(tampilGambar)==JFileChooser.APPROVE_OPTION){
            
            Toolkit toolkit=Toolkit.getDefaultToolkit();
            Image image=toolkit.getImage(jfc.getSelectedFile().getAbsolutePath());
            Image imagedResized=image.getScaledInstance(tampilGambar.getWidth(), tampilGambar.getHeight(), Image.SCALE_SMOOTH);
            ImageIcon imageIcon=new ImageIcon(imagedResized);
            
            tampilGambar.setIcon(imageIcon);
            namaGambar1.setText(jfc.getSelectedFile().getName());
            
            file=new File(jfc.getSelectedFile().getPath()); // file untuk dikopi
        }
    }//GEN-LAST:event_btnPilihActionPerformed

    private void txtNamaPegawaiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNamaPegawaiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNamaPegawaiKeyPressed

    private void tabelLayananMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelLayananMouseClicked
        // TODO add your handling code here:
         int index= tabelLayanan.getSelectedRow();
        String namaLayanan       = (String) tabelLayanan.getValueAt(index, 1);
        txtNamaLayanan.setText(namaLayanan);
       
    }//GEN-LAST:event_tabelLayananMouseClicked

    private void filterTableKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_filterTableKeyPressed
        // TODO add your handling code here:
        
        String cari = filterTable.getText();
        TableRowSorter tr = new TableRowSorter(tabelModel);
        tablePegawai.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(cari));
    }//GEN-LAST:event_filterTableKeyPressed

    private void filterTableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filterTableActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_filterTableActionPerformed

    private void filterTable1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filterTable1ActionPerformed
        // TODO add your handling code here:
        String cari = filterTable.getText();
        TableRowSorter tr = new TableRowSorter(tabelModel9);
        tabelProdukTampilSeluruh.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(cari));
        
    }//GEN-LAST:event_filterTable1ActionPerformed

    private void filterTable1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_filterTable1KeyPressed
        // TODO add your handling code here:
                String cari = filterTable1.getText();
        TableRowSorter tr = new TableRowSorter(tabelModel9);
        tabelProdukTampilSeluruh.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(cari));
    }//GEN-LAST:event_filterTable1KeyPressed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        MenuReport MR = new MenuReport();
        this.setVisible(false);
        MR.setVisible(true);
        MR.setExtendedState(JFrame.MAXIMIZED_BOTH);
             
    }//GEN-LAST:event_jButton6ActionPerformed

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
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
       
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
               MenuAdmin MA=  new MenuAdmin();
               MA.setVisible(true);
               MA.setExtendedState(JFrame.MAXIMIZED_BOTH);
                
            }
        });
        
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel CariHewan;
    private javax.swing.JPanel CariSuplier;
    private javax.swing.JPanel MainPanel;
    private javax.swing.JPanel TambahSuplier;
    private javax.swing.JPanel TampilHewan;
    private javax.swing.JPanel background;
    private javax.swing.JPanel bantuan;
    private javax.swing.JButton btlHapusHewan;
    private javax.swing.JButton btlHapusHewan1;
    private javax.swing.JButton btlHapusHewan2;
    private javax.swing.JButton btlHapusProduk;
    private javax.swing.JButton btlHapusSup;
    private javax.swing.JButton btlHapusUkuranHewan;
    private javax.swing.JButton btlSimpan;
    private javax.swing.JButton btlSimpan1;
    private javax.swing.JButton btlSimpan2;
    private javax.swing.JButton btlSimpan3;
    private javax.swing.JButton btlSimpan6;
    private javax.swing.JButton btnCari;
    private javax.swing.JButton btnCari1;
    private javax.swing.JButton btnCariJenis;
    private javax.swing.JLabel btnCariLayanan;
    private javax.swing.JLabel btnCariLayanan1;
    private javax.swing.JButton btnCariLyn;
    private javax.swing.JButton btnCariLyn1;
    private javax.swing.JButton btnCariSup;
    private javax.swing.JButton btnCariUkuran;
    private javax.swing.JLabel btnCariUkuranHewan;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnHelp;
    private javax.swing.JButton btnHelp1;
    private javax.swing.JButton btnHelp2;
    private javax.swing.JButton btnHelp3;
    private javax.swing.JButton btnHelp4;
    private javax.swing.JButton btnHewan;
    private javax.swing.JButton btnLayanan;
    private javax.swing.JButton btnPegawai;
    private javax.swing.JButton btnPilih;
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
    private javax.swing.JButton btnSimpanHewan3;
    private javax.swing.JButton btnSimpanLyn;
    private javax.swing.JButton btnSimpanLyn2;
    private javax.swing.JButton btnSimpanPegawai;
    private javax.swing.JButton btnSimpanProduk;
    private javax.swing.JButton btnSimpanProduk1;
    private javax.swing.JButton btnSimpanProduk2;
    private javax.swing.JButton btnSimpanSuplier;
    private javax.swing.JButton btnSimpanUkuran;
    private javax.swing.JButton btnSup;
    private javax.swing.JButton btnSupHelp;
    private javax.swing.JButton btnSupHelp1;
    private javax.swing.JButton btnTambah;
    private javax.swing.JButton btnTambah1;
    private javax.swing.JButton btnTambahJenis;
    private javax.swing.JButton btnTambahLyn;
    private javax.swing.JButton btnTambahLyn1;
    private javax.swing.JButton btnTambahSup;
    private javax.swing.JButton btnTambahUkuran;
    private javax.swing.JButton btnTampil;
    private javax.swing.JButton btnTampil1;
    private javax.swing.JButton btnTampilLyn;
    private javax.swing.JButton btnTampilLyn1;
    private javax.swing.JButton btnTampilLyn2;
    private javax.swing.JButton btnTampilSup;
    private javax.swing.JButton btnTampilSup1;
    private javax.swing.JButton btnUkuran;
    private javax.swing.ButtonGroup cari;
    private javax.swing.JTextField cariIdHargaLyn;
    private javax.swing.JTextField cariJenisHewan;
    private javax.swing.JPanel cariLayanan;
    private javax.swing.JPanel cariLayanan1;
    private javax.swing.JPanel cariPegawai;
    private javax.swing.JPanel cariProduk;
    private javax.swing.JPanel cariUkuranHewan;
    private javax.swing.JRadioButton cs;
    private javax.swing.JPanel dataHewan;
    private javax.swing.JPanel dataLayanan;
    private javax.swing.JPanel dataPegawai;
    private javax.swing.JPanel dataProduk;
    private javax.swing.JPanel dataSupplier;
    private javax.swing.JPanel dataUkuran;
    private javax.swing.JTextField filterTable;
    private javax.swing.JTextField filterTable1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JComboBox<String> jComboBoxNamaLayanan;
    private javax.swing.JComboBox<String> jComboBoxNamaLayanan1;
    private javax.swing.JComboBox<String> jComboBoxSupllier;
    private javax.swing.JComboBox<String> jComboBoxUkuran;
    private javax.swing.JComboBox<String> jComboBoxUkuranHewan;
    private javax.swing.JComboBox<String> jComboBoxUkuranHewan1;
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
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
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
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel90;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JLabel judul;
    private javax.swing.JRadioButton kasir;
    private javax.swing.JLabel lblImage;
    private javax.swing.JPanel mainPanel10;
    private javax.swing.JPanel mainPanel2;
    private javax.swing.JPanel mainPanel3;
    private javax.swing.JPanel mainPanel4;
    private javax.swing.JPanel mainPanel5;
    private javax.swing.JPanel mainPanel7;
    private javax.swing.JPanel mainPanel8;
    private javax.swing.JTextField mencariNamaProduk;
    private javax.swing.JPanel menuHome;
    private javax.swing.JLabel namaGambar;
    private javax.swing.JLabel namaGambar1;
    private javax.swing.JRadioButton rdCustomerService;
    private javax.swing.JRadioButton rdKasir;
    private javax.swing.JTable tabelHargaLayanan;
    private javax.swing.JTable tabelHewan;
    private javax.swing.JTable tabelLayanan;
    private javax.swing.JTable tabelProduk;
    private javax.swing.JTable tabelProdukTampilSeluruh;
    private javax.swing.JTable tabelUkuranHewan;
    private javax.swing.JTable tablePegawai;
    private javax.swing.JTable tableSupTambahData;
    private javax.swing.JPanel tambahHargaLayanan;
    private javax.swing.JPanel tambahHewan;
    private javax.swing.JPanel tambahLayanan;
    private javax.swing.JPanel tambahLayanan2;
    private javax.swing.JPanel tambahPegawai;
    private javax.swing.JPanel tambahProduk;
    private javax.swing.JPanel tambahUkuranHewan;
    private javax.swing.JLabel tampilGambar;
    private javax.swing.JPanel tampilLayanan;
    private javax.swing.JPanel tampilLayanan1;
    private javax.swing.JPanel tampilProduk;
    private javax.swing.JPanel tampilSeluruh;
    private javax.swing.JPanel tampilUkuranHewan;
    private com.toedter.calendar.JDateChooser tglLahirPegawai1;
    private javax.swing.JTextField txtAlamat;
    private javax.swing.JTextField txtAlamatSup;
    private javax.swing.JTextField txtCari;
    private javax.swing.JTextField txtCariAlaSup;
    private javax.swing.JTextField txtCariAlamat;
    private javax.swing.JTextField txtCariLayanan;
    private javax.swing.JTextField txtCariNama;
    private javax.swing.JTextField txtCariNamaHwn;
    private javax.swing.JTextField txtCariNamaP;
    private javax.swing.JTextField txtCariNamaSup;
    private javax.swing.JTextField txtCariNoTlp;
    private javax.swing.JTextField txtCariTelpSup;
    private javax.swing.JTextField txtCariUkuranHewan;
    private javax.swing.JTextField txtCariUserName;
    private javax.swing.JTextField txtHargaProduk;
    private javax.swing.JTextField txtHargaProdukp;
    private javax.swing.JTextField txtJumStok;
    private javax.swing.JTextField txtJumStokP;
    private javax.swing.JTextField txtMinStok;
    private javax.swing.JTextField txtMinStokP;
    private javax.swing.JTextField txtNamSup;
    private javax.swing.JTextField txtNamaHewan;
    private javax.swing.JTextField txtNamaLayanan;
    private javax.swing.JTextField txtNamaLayananCari;
    private javax.swing.JTextField txtNamaLayananHarga2;
    private javax.swing.JTextField txtNamaPegawai;
    private javax.swing.JTextField txtNamaProduk;
    private javax.swing.JTextField txtNoTelp;
    private javax.swing.JTextField txtPassword;
    private javax.swing.JTextField txtSatun;
    private javax.swing.JTextField txtSatunP;
    private javax.swing.JLabel txtTanggalHariIni;
    private com.toedter.calendar.JDateChooser txtTanggalLahir;
    private javax.swing.JTextField txtTelpSup;
    private javax.swing.JTextField txtUkuranHewan;
    private javax.swing.JTextField txtUserName;
    private javax.swing.JTextField txthargaLayanan;
    // End of variables declaration//GEN-END:variables
}
