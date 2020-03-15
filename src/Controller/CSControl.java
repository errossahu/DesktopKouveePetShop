/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.CsDAO;
import Model.Pegawai;
import Model.Pelanggan;
import Model.Produk;
import java.util.List;

/**
 *
 * @author ACER
 */
public class CSControl {
    
    
    CsDAO csDAO= new  CsDAO();
    
    
    public void tambahPelanggan(Pelanggan P)
    {
        csDAO.makeConnection();
        csDAO.tambahPelanggan(P);
        csDAO.closeConnection(); 
    }
    public void hapusPelanggan(int id)
    {
        csDAO.makeConnection(); 
        csDAO.hapusPelanggan(id);
        csDAO.closeConnection();
    }
    public Pelanggan cariPelanggan(int id)
    {
        Pelanggan p = null ;
        csDAO.makeConnection();
        p= csDAO.searchPelanggan(id);
        csDAO.closeConnection();
        return p ;
               
    }
    public List<Pelanggan>tampilPelanggan()
    {
        csDAO.makeConnection();
        List<Pelanggan> P= csDAO.tampilPelanggan();
        csDAO.closeConnection();
        return  P ;
    }
}
