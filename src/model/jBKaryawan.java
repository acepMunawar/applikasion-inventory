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
public class jBKaryawan {
    //karyawan
    private String idKaryawan;
    private String namaKaryawan;
    private String staff;
    private String no_tlp;
    //supplayer
    
    //karyawan
    public jBKaryawan(String idKaryawan,String namaKaryawan, String staff, String no_tlp) {
        this.idKaryawan = idKaryawan;
        this.namaKaryawan = namaKaryawan;
        this.staff = staff;
        this.no_tlp = no_tlp;
    }  
    
    //karyawan   
    public String getIdKaryawan(){
            return idKaryawan;
    }
    
    public void setIdKaryawan(String idKaryawan){
        this.idKaryawan = idKaryawan;
    }
    

    public String getNamaKary(){
            return namaKaryawan;
    }
    
    public void setNamaKary(String namaKary){
        this.namaKaryawan = namaKary;
    }
    
    public String getStaff(){
            return staff;
    }
    
    public void setStaff(String staff){
        this.staff = staff;
    }
    
    public String getNoTlp(){
            return no_tlp;
    }
    
    public void setNoTlp(String no_tlp){
        this.no_tlp = no_tlp;
    }
    
//supplayer
    
    
    
    
    
}