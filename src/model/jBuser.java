/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Administrator
 */
public class jBuser {
private String id;
private String nama;
private String username;
private String password;

public jBuser(String id, String nama,String username,String password){
    this.id = id;
    this.nama = nama;
    this.username = username;
    this.password = password;
    
}

public void setId(String id){
    this.id = id;
}

public String getId(){
    return id;
}

public void setNama(String nama){
    this.nama = nama;
}

public String getNama(){
    return nama;
}

public void setUsname(String nama){
    this.username = nama;
}

public String getUsname(){
    return username;
}

public void setPass(String password){
    this.password = password;
}

public String getPass(){
    return password;
}


}
