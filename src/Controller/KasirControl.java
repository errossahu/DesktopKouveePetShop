/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.AdminDao;
import DAO.KasirDAO;
import Model.detailTransaksiProduk;

/**
 *
 * @author ACER
 */
public class KasirControl {

 
 private final KasirDAO ksDAO = new KasirDAO();
    public void deleteProduk(String id )
    {
        ksDAO.makeConnection();
        ksDAO.deleteProduk(id);
        ksDAO.closeConnection();

    }
    public void tambahProduk(detailTransaksiProduk th)
    {
        ksDAO.makeConnection(); 
        ksDAO.tambahProduk(th);
        ksDAO.closeConnection();
    }
    public void editProdukTransaksi(detailTransaksiProduk th , String id )
    {
        ksDAO.makeConnection();
        ksDAO.updateProduk(th, id);
        ksDAO.closeConnection();
                
    }
    
}
