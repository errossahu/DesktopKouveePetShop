/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;
import DAO.AdminDao ;
import Model.JenisHewan;
import Model.Layanan;
import Model.Pegawai;
import Model.Produk;
import Model.Suplier;
import Model.UkuranHewan;
import java.util.List;
/**
 *
 * @author ACER
 */
public class AdminControl {
private final AdminDao aDAO = new AdminDao();
  
    public void tambahProduk(Produk P )
    {
        aDAO.makeConnection(); 
        aDAO.tambahProduk(P);
        aDAO.closeConnection();
        
    }
    public void deleteProduk(String cari)
    {
        aDAO.makeConnection();
        aDAO.deleteProduk(cari);
        aDAO.closeConnection();
    }
    public void tambahLayanan(Layanan L)
    {
        aDAO.makeConnection(); 
        aDAO.tambahLayanan(L);
        aDAO.closeConnection();
    }
    public void editPegawai( Pegawai P,String userName )
    {

        aDAO.makeConnection(); 
        aDAO.editPegawai(P, userName);
        aDAO.closeConnection();
    }
    public void tambahPegawai(Pegawai P)
    {
        aDAO.makeConnection();
        aDAO.tambahPegawai(P);
        aDAO.closeConnection();
        
    }
    public void deletePegawai(String cari)
    {
        aDAO.makeConnection();
        aDAO.deletePegawai(cari);
        aDAO.closeConnection();
    }
    public void deleteLayanan(String cari)
    {
        aDAO.makeConnection(); 
        aDAO.deleteLayanan(cari);
        aDAO.closeConnection();
    }
    public void deleteJenisHewan(String cari)
    {
        aDAO.makeConnection();
        aDAO.deleteJenisHewan(cari);
        aDAO.closeConnection(); 
    }
    public void deleteSup(String cari )
    {
        aDAO.makeConnection();
        aDAO.deleteSuplier(cari);
        aDAO.closeConnection();
        
    }
    public void tambahJenisHewan(JenisHewan jh)
    {
        aDAO.makeConnection();
        aDAO.tambahJenisHewan(jh);
        aDAO.closeConnection(); 
    }
    public void tambahSuplier(Suplier S)
    {
        aDAO.makeConnection(); 
        aDAO.tambahSuplier(S);
        aDAO.closeConnection(); 
    }
    public void tambahUkuranHewan(UkuranHewan uh )
    {
        aDAO.makeConnection(); 
        aDAO.tambahUkuranHewan(uh);
        aDAO.closeConnection(); 
        
    }
     
    public Pegawai getPegawai()
    {
        Pegawai adm=null;
        aDAO.makeConnection();
        adm=aDAO.getPegawai();
        aDAO.closeConnection();
        return adm;
        
    }
    public Produk searchPro(String namaProd)
    {
        Produk p= null ;
        aDAO.makeConnection(); 
        p= aDAO.searchProduk(namaProd);
        aDAO.closeConnection();
        return p ;
    }
    public Suplier searchSup(String namaSup )
    {
        Suplier sp = null ;
        aDAO.makeConnection(); 
        sp= aDAO.searchSupplier(namaSup);
        aDAO.closeConnection();
        return sp ;
                
    }
    public JenisHewan searchJenisHewan(String nama)
    {
        JenisHewan sp = null ;
        aDAO.makeConnection();
        sp= aDAO.searchJenisHewan(nama);
        aDAO.closeConnection();
        return sp ;
    }
    public UkuranHewan searchUkuran(String ukuran)
    {
        UkuranHewan uh = null;
        aDAO.makeConnection(); 
        uh = aDAO.searchUkuranHewan(ukuran);
        aDAO.closeConnection(); 
        return uh ;
                
    }
    public Pegawai searchPegawai(String userName)
    {
        Pegawai pgw=null;
        aDAO.makeConnection();
        pgw= aDAO.searchPegawai(userName);
        aDAO.closeConnection();
        return pgw;
    
    }
    public List<Produk>tampilComboBoxNama()
    {
        aDAO.makeConnection();
        List<Produk> P= aDAO.tampilComboNamaProduk();
        aDAO.closeConnection();
        return  P ;
    }
    public List<JenisHewan>tampilJenisHewan()
    {
        aDAO.makeConnection(); 
        List<JenisHewan> J = aDAO.tampilJenisHewan();
        aDAO.closeConnection(); 
        return J ;
    }
    public List<Layanan> tampilLayanan()
    {
        aDAO.makeConnection();
        List<Layanan> L = aDAO.tampilLayanan();
        aDAO.closeConnection();
        return L ;
    }
    public List<Suplier> tampilDataSup()
    {
        aDAO.makeConnection();
        List<Suplier> S= aDAO.tampilSuplier();
        aDAO.closeConnection();
        return S ;
    }
    public List<Pegawai> tampilDataPegawai()
    {
        aDAO.makeConnection();
        List<Pegawai> P =aDAO.TampilPegawai();
        aDAO.closeConnection();
        return P;
        
    }
    public List<Produk>tampilDataProduk()
    {
        aDAO.makeConnection();
        List<Produk> P= aDAO.tampilProduk();
        aDAO.closeConnection();
        return P ;
    }
   
    public Layanan searchLayanan(String namaLayanan)
    {
        Layanan lyn = null ;
        aDAO.makeConnection();
        lyn = aDAO.searchLayanan(namaLayanan);
        aDAO.closeConnection();
        return lyn ;
    }
    
   
        
}
  
