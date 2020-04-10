/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author ACER
 */
public class TransaksiLayanan {
    public String idTransaksi ;
    public String idLayanan ;
    public String idHewan ;
    
    
    public TransaksiLayanan(){
        
    }
    public String getIdTransaksi()
    {
        return idTransaksi ;
        
    }
    public void setIdTransaksi( String idTransaksi)
    {
        this.idTransaksi=idTransaksi;
    }
    public String getIdLayanan()
    {
        return idLayanan ;
    }
    public void setIdLayanan(String idLayanan)
    {
        this.idLayanan = idLayanan;
    }
    
}
