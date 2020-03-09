/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;
import DAO.AdminDao ;
import Model.Layanan;
import Model.Pegawai;
import Model.Suplier;
import java.util.List;
/**
 *
 * @author ACER
 */
public class AdminControl {
private final AdminDao aDAO = new AdminDao();
  
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
    
    public void tambahSuplier(Suplier S)
    {
        aDAO.makeConnection(); 
        aDAO.tambahSuplier(S);
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
    
    public Pegawai searchPegawai(String userName)
    {
        Pegawai pgw=null;
        aDAO.makeConnection();
        pgw= aDAO.searchPegawai(userName);
        aDAO.closeConnection();
        return pgw;
    
    }
    public List<Pegawai> tampilDataPegawai()
    {
        aDAO.makeConnection();
        List<Pegawai> P =aDAO.TampilPegawai();
        aDAO.closeConnection();
        return P;
        
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
  
