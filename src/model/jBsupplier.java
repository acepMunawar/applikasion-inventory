/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author acepmunawar
 */
public class jBsupplier {

    private String id_supplier;
    private String namaSupplier;
    private String alamatSupplier;
    private String noSupplier;
    


  //supplayer
    public jBsupplier(String id_supplier, String namaSupplier, String alamatSupplier,String noSupplier){
        this.id_supplier = id_supplier;
        this.namaSupplier = namaSupplier;
        this.alamatSupplier = alamatSupplier;
        this.noSupplier = noSupplier;
    }
 public void setIdSupplier(String id_supplier){
            this.id_supplier = id_supplier;
    }
    
    public String getIdSupplier(){
        return id_supplier;
    }
    
    public void setNamaSupplier(String namaSupplier){
            this.namaSupplier = namaSupplier;
    }
    
    public String getNamaSupplier(){
        return namaSupplier;
    }
    
    public void setAlamatSupplier(String alamatSupplier){
        this.alamatSupplier = alamatSupplier;
    }
    
    public String getAlamatSupplier(){
        return alamatSupplier;
    }
    
    public void setNoSupplier(String noSupplier){
        this.noSupplier = noSupplier;
    }
    
    public String getNoSupplier(){
        return noSupplier;
    }
   






    
}
