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
import Model.dataHewan;
import java.util.List;

/**
 *
 * @author ACER
 */
public class CSControl {
    
    
    CsDAO csDAO= new  CsDAO();
    
    public void hapusHewan(String cari )
    {
        csDAO.makeConnection();
        csDAO.hapusHewan(cari);
        csDAO.closeConnection();
    }
    public void editHewan(dataHewan dh ,String nama )
    {
        csDAO.makeConnection(); 
        csDAO.editHewan(dh, nama);
        csDAO.closeConnection();
    }
    public void tambahPelanggan(Pelanggan P)
    {
        csDAO.makeConnection();
        csDAO.tambahPelanggan(P);
        csDAO.closeConnection(); 
    }
    public void tambahHewan(dataHewan dh)
    {
        csDAO.makeConnection(); 
        csDAO.tambahHewan(dh);
        csDAO.closeConnection(); 
    }
    public void editPelanggan(Pelanggan P , String nama)
    {
        csDAO.makeConnection();
        csDAO.editPelangggan(P, nama);
        csDAO.closeConnection();
    }
    public void hapusPelanggan(String nama)
    {
        csDAO.makeConnection(); 
        csDAO.hapusPelanggan(nama );
        csDAO.closeConnection();
    }
    public Pelanggan cariPelanggan(String nama)
    {
        Pelanggan p = null ;
        csDAO.makeConnection();
        p= csDAO.searchPelanggan(nama);
        csDAO.closeConnection();
        return p ;
               
    }
    public Pelanggan cariPakaiNama(String nama)
    {
        Pelanggan p = null ;
        csDAO.makeConnection(); 
        p= csDAO.searchPelangganPakaiNama(nama);
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
