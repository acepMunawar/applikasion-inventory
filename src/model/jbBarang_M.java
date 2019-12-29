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
public class jbBarang_M {
private String id;
private String tanggal;
private String id_supplier;
private String nama_supplier;
private String alamat_supplier;
private String kode_barang;
private String nama_barang;
private int jumlah;

public jbBarang_M(String id,String tanggal,String id_supplier,String nama_supplier,String alamat_supplier,String kode_barang,String nama_barang,int jumlah){
        this.id = id;
        this.tanggal = tanggal;
        this.id_supplier = id_supplier;
        this.nama_supplier = nama_supplier;
        this.kode_barang = kode_barang;
        this.nama_barang = nama_barang;
        this.alamat_supplier = alamat_supplier;
        this.jumlah = jumlah;
}

public void setId(String id){
    this.id= id;

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


public void setIdSupplier(){
    this.id_supplier = id_supplier;
}

public String getIdSupplier(){
    return id_supplier;
}

public void setNama_S(){
    this.nama_supplier= nama_supplier;
}

public String getNama_S(){
    return nama_supplier ;
}

public void setAlamat_S(){
    this.alamat_supplier= alamat_supplier;
}

public String getAlamat_S(){
    return alamat_supplier ;
}

public void setKodeBarang(){
    this.kode_barang= kode_barang;
}

public String getKodeBarang(){
    return kode_barang;
}

public void setNama_B(){
    this.nama_barang= nama_barang;
}

public String getNama_B(){
    return nama_barang;
}



public void setJumlah(){
    this.jumlah = jumlah;
}

public int getJumlah(){
    return jumlah;
}

}
