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
public class jbBarang_K {
private String id;
private String tanggal;
private String kd_barang;
private String nama_barang;
private String kd_karyawan;
private String nama_karyawan;
private String staff;
private int jumlah;

public jbBarang_K(String id,String tanggal,String kd_barang,String nama_barang,String kd_karyawan,String nama_karyawan,int jumlah){
    this.id = id;
    this.tanggal = tanggal;
    this.kd_barang = kd_barang;
    this.nama_barang = nama_barang;
    this.kd_karyawan = kd_karyawan;
    this.nama_karyawan = nama_karyawan;
    this.jumlah = jumlah;
}



public void setId(String id){
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

public void setKdBarang(){
    this.kd_barang = kd_barang;
}

public String getkdBarang(){
    return kd_barang;
}

public void setNamaBarang(){
    this.nama_barang = nama_barang;
}

public String getNamaBarang(){
    return nama_barang;
}

public void setKdKaryawan(){
    this.kd_karyawan = kd_karyawan;
}

public String getkdKaryawan(){
    return kd_karyawan;
}

public void setNamaKaryawan(){
    this.nama_karyawan = nama_karyawan;
}

public String getNamaKaryawan(){
    return nama_karyawan;
}

public void setJumlah(){
    this.jumlah = jumlah;
}

public int getJumlah(){
    return jumlah;
}


}
