/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ACER
 */
public class Layanan {
   public String namaLayanan  ;
   
   public int Id_layanan;
   public String Create_by ;
   public String delete_by ;
   public String Create_at ;
   public String delete_at ; 
   public String modified_by ;
   public String modified_at ;
    
  
    public Layanan(String namaLay )
    {
        this.namaLayanan = namaLay;
    }
    public Layanan(){}

   
   
   public Layanan(String namaLayanan ,int Id_Layanan , String create_by , String delete_by, 
          String delete_at , String Create_at , String modified_by,String modified_at)
   {
       this.Create_at= Create_at ;
       this.Create_by= create_by ;
       this.Id_layanan = Id_Layanan ;
       this.delete_at= delete_at ;
       this.delete_by= delete_by;
       this.modified_at= modified_at ;
       this.modified_by =modified_by ;
       this.namaLayanan =namaLayanan ;
   }
   public void setIdLayanan(int  layanan)
   {
       this.Id_layanan = layanan ;
   }
   public int getIdLayanan()
   {
       return Id_layanan ;
   }
   public void setCreateBY(String Create_by)
   {
       this.Create_by =Create_by ;
       
    }
   public String getCreate_by()
   {
       return Create_by  ;
   }
   public void setDeleteBy(String delete_by)
   {
       this.delete_by= delete_by ;
   }
   public String getCreateBy ()
   {
       return delete_by ;
   }
   public void setCreate_at(String create_at)
   {
       this.Create_at= create_at;
   }
   public String getCreate_at()
   {
       return Create_at;
   }
   public void setDeleteAT(String delete_At)
   {
       this.delete_at = delete_At ;
   }
   public String getDelete_At()
   {
       return delete_at ;
   }
   public void setModified_by(String modified_by)
   {
       this.modified_by = modified_by ;
   }
   public String getModified_by()
   {
       return modified_by ;
   }
   public void setModified_at(String modified_by)
   {
       this.modified_at = modified_at ;
   }
   public String getModified_at()
   {
       return modified_at ;
   }
   public void setNamaLayanan (String namaLayanan)
   {
       this.namaLayanan = namaLayanan ;
   }
   public String getNamaLayanan()
   {
       return namaLayanan ; 
   }
   
   
   
         
}
