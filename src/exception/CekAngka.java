/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exception;
import  view.MenuAdmin ;
/**
 *
 * @author ACER
 */
public class CekAngka extends Exception{
   
  public String dataHanyaHuruf()
  {
      return "No TELP Hanya Angka" ;
  }
  public String harga()
  {
      return "Harga , Minstok dan Jumlah Stok Hanya Berupa Angka";
  }

  
}
