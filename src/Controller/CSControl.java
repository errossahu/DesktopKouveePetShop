/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.CsDAO;
import Model.Pegawai;
import Model.Pelanggan;

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
}
