/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exception;

/**
 *
 * @author ACER
 */
public class dataSama extends Exception{

    public String dataUkuran()
    {
        return "Ukuran Sudah Terdaftar";
    }
    public String jenisHewan()
    {
        return "Jenis Hewan Sudah Terdaftar";
    }
    public String suplierSudahTerdaftar()
    {
        return  "Supllier Sudah Terdaftar";
    }
    public String dataSama()
    {
      
        return "User Name Sudah Terdaftar" ;
    }
    public String layananSama()
    {
        return "Layanan Sudah Ada";
    }
    public String namaProduk()
    {
        return "Nama Produk Sudah Terdaftar";
    }
    
}

