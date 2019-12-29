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
public class jbKategori {
private String id_kategori;
private String nama_kategori;
private int rak;

public jbKategori(String id_kategori,String nama_kategori, int rak){
    this.id_kategori    = id_kategori;
    this.nama_kategori  = nama_kategori;
    this.rak            = rak;
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
    
public void setRak(int rak){
        this.rak = rak;

}    

public int getRak(){
        return rak;
}

}
