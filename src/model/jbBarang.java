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
public class jbBarang {
    private String tanggal;
    private String kode_barang;
    private String nama_barang;
    private int stock;
    private String id_kategori;
    private String nama_kategori;
    private int no_rak;
    
    public jbBarang(String tanggal,String kode_barang,String nama_barang,String id_kategori,String nama_kategori,int no_rak,int stock){
        this.tanggal        = tanggal;
        this.kode_barang    = kode_barang;
        this.nama_barang    = nama_barang;
        this.id_kategori    = id_kategori;
        this.nama_kategori  = nama_kategori;
        this.no_rak         = no_rak;
        this.stock          = stock;
    } 

    public jbBarang(int aInt, String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
     public void setkodebarang(String kode_barang){
            this.kode_barang = kode_barang;
    }
    
    public String getkodebarang(){
        return kode_barang;
    }
    
    public void setnamabarang(String nama_barang){
            this.nama_barang = nama_barang;
    }
    
    public String getnamabarang(){
        return nama_barang;
    }

    
     public void setstockbarang(int stock){
            this.stock = stock;
    }
    
    public int getstockbarang(){
        return stock;
    }
    
    public void setidkategori(String id_kategori){
            this.id_kategori = id_kategori;
    }
    
    public String getidkategori(){
        return id_kategori;
    }

    public void setnamakategori(String nama_kategori){
            this.nama_kategori = nama_kategori;
    }
    
    public String getnamakategori(){
        return nama_kategori;
    }

    public void setnorak(int no_rak){
            this.no_rak = no_rak;
    }
    
    public int getnorak(){
        return no_rak;
    }
    
public void setTanggal(){
    this.tanggal = tanggal;
}

public String getTanggal(){
    return tanggal;
}


    
}
