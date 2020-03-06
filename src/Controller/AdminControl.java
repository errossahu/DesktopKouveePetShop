/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;
import DAO.AdminDao ;
import Model.Pegawai;
/**
 *
 * @author ACER
 */
public class AdminControl {
private final AdminDao aDAO = new AdminDao();
  
    public void tambahPegawai(Pegawai P)
    {
        aDAO.makeConnection();
        aDAO.tambahPegawai(P);
        aDAO.closeConnection();
        
    }
    
    
}
  
