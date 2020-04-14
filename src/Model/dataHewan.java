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
public class dataHewan {
    private int  idHewan;
    private int IdnamaPelanggan ;
    private int   jenisHewan ;
    private String namaHewan ;
    private String tglLahir ;
    private String createAt ;
    private String createBy ;

    private String ModifiedBy ;
    private String modified_At ;
    private  String deleteBy ;
    private String deleteAt ;

    public dataHewan(){}
    public void setCreateAt(String creaete)
    {
        this.createAt =creaete ;
    }
    public String getCreateAt()
    {
        return createAt ;
    }
    public void setCreateBy(String createBy)
    {
        this.createBy = createBy ;
    }
    public String getCreateBy()
    {
        return createBy ;
    }
    public void setTanggaLahir(String tgl)
            
    {
        this.tglLahir = tgl ;
    }
    public String getTgl ()
    {
        return tglLahir ;
    }
    
    public int  getIdHewan()
    {
        return idHewan;
    }
    public void setIntHewan(int  idHewan)
    {
        this.idHewan= idHewan;
    }
    
    public int  getNamaPelanggan()
    {
        return IdnamaPelanggan ;
    }
    
    public void setIdPelanggan( int idPelanggan )
    {
        this.IdnamaPelanggan = idPelanggan ;
    }
    public int getIdPelanggan()
    {
        return IdnamaPelanggan ; 
    }
    public void setIDJenisHewan(int id)
    {
        this.jenisHewan = id;
    }
    public int getIDjENIShewan()
    {
        return  jenisHewan;
    }
   public void setNamaHewan(String namaHewan)
   {
       this.namaHewan = namaHewan;
   }
   public String getNamaHewan()
   {
       return namaHewan ;
   }

} 
