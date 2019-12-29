/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author acepmunawar
 */
public class db_conn {
           
        boolean failed_conn = false;
        Statement stmt;
        ResultSet rs;
        
    public Connection getConn(){
		Connection conn = null;
		try {
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ptgarmakmotor","root","");		
                            return conn;
		}catch(Exception ex) {
                                System.out.println("error");
                            return conn;
            }
        }
        
  
	   
 
}
