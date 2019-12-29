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
public class jbPermintaan {
  private String id;  
  private String tanggal;
  private String nama_barang;
  private String id_kategori;
  private String id_karyawan;
  private String keterangan;
  private int jumlah;
    
    public jbPermintaan(String id,String tanggal,String nama_barang,String id_kategori,String id_karyawan,String keterangan,int jumlah){
        this.id             = id;
        this.tanggal        = tanggal;
        this.nama_barang    = nama_barang;
        this.id_kategori    = id_kategori;
        this.id_karyawan    = id_karyawan;
        this.keterangan     = keterangan;
        this.jumlah         = jumlah;
    } 
    
    public void setId(){
    this.id = id;
    }
    
    public String getId(){
        return id;
    }
    
    public void setTanggal(){
    this.tanggal = tanggal;
}

    public String getTanggal(){
    return tanggal;
    }
        
    public void setnamabarang(String nama_barang){
            this.nama_barang = nama_barang;
    }
    
    public String getnamabarang(){
        return nama_barang;
    }

    public void setketerangan(String keterangan){
            this.keterangan = keterangan;
    }
    
    public String getketerangan(){
        return keterangan;
    }
    
     public void setjumlahbarang(int jumlah){
            this.jumlah = jumlah;
    }
    
    public int getjumlahbarang(){
        return jumlah;
    }
    
    public void setidkategori(String id_kategori){
            this.id_kategori = id_kategori;
    }
    
    public String getidkategori(){
        return id_kategori;
    }
    
    public void setidkaryawan(String id_karyawan){
            this.id_karyawan = id_karyawan;
    }
    
    public String getidkaryawan(){
        return id_karyawan;
    }
    
    
}
