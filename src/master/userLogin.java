/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package master;

import connection.db_conn;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import login.login;
import master.karyawan;
import model.jBuser;
import userView.home;

/**
 *
 * @author Administrator
 */
public class userLogin extends javax.swing.JFrame {
    
    /**
     * Creates new form userLogin
     */
    public userLogin() {
        initComponents();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        fillTable();
        auto_number();
        fld_idlogin.setEditable(false);
        btn_delete.setEnabled(false);
        btn_update.setEnabled(false);
    }

      public ArrayList<jBuser> getAllKaryData(){
    ArrayList<jBuser> al = null;
    al = new ArrayList<jBuser>();
    try{
    db_conn con = new db_conn();     
           String qry = "Select * from login ";
           Statement st = con.getConn().createStatement();
           ResultSet rs = st.executeQuery(qry);
    jBuser bean;
        while(rs.next()){
              bean = new jBuser(
                      
                          rs.getString(1),
                          rs.getString(2),
                          rs.getString(3),
                          rs.getString(4)
              );                                                   
              al.add(bean);

     }
    }catch(Exception e){
    
    System.out.print("sory your wrong call");   
    }
    return al;
}

public void fillTable(){
        ArrayList<jBuser> al = getAllKaryData();
        DefaultTableModel model = (DefaultTableModel)table.getModel();
        model.setRowCount(0);
        auto_number();
        Object[] row = new Object[8];
        for(int i =0 ; i < al.size(); i++){
            row[0] = al.get(i).getId();       
            row[1] = al.get(i).getNama();
            row[2] = al.get(i).getUsname();
            row[3] = al.get(i).getPass();

            model.addRow(row);        
        }
    }
     
    public void showItem(int index){
        fld_idlogin.setText(getAllKaryData().get(index).getId()); 
        fld_nama.setText(getAllKaryData().get(index).getNama());
        fld_username.setText(getAllKaryData().get(index).getUsname());
        fld_password.setText(getAllKaryData().get(index).getPass());

    }
        

   private void auto_number(){
        try{
            db_conn conn = new db_conn(); 
            String sql = "SELECT MAX(right(id,2)) AS no FROM login";
             Statement st = conn.getConn().createStatement();
           ResultSet rs = st.executeQuery(sql);
           while (rs.next()){
               if(rs.first() == false){
                   fld_idlogin.setText("US001");
               
               }else{
                   rs.last();
                   int auto_id = rs.getInt(1) + 1;
                   String no = String.valueOf(auto_id);
                   int noLong = no.length();
                        for(int a = 0; a < 3 - noLong; a++){
                            no = "0" + no;
                        }
                   fld_idlogin.setText("US"+ no);     
               }
           }
        }catch(Exception e){
        
            JOptionPane.showMessageDialog(this,"error: \n" + e.toString(), "kelasahan", JOptionPane.WARNING_MESSAGE); 
        
        }
    
    }
  

    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        btn_clear = new javax.swing.JButton();
        btn_Save = new javax.swing.JButton();
        btn_update = new javax.swing.JButton();
        btn_delete = new javax.swing.JButton();
        fld_password = new javax.swing.JTextField();
        fld_username = new javax.swing.JTextField();
        fld_nama = new javax.swing.JTextField();
        fld_idlogin = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        btnKaryawan = new javax.swing.JButton();
        btnSupplier = new javax.swing.JButton();
        btnBarang = new javax.swing.JButton();
        btnKategori = new javax.swing.JButton();
        btnKategori1 = new javax.swing.JButton();
        btnLogOut = new javax.swing.JButton();
        btnHome = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Id", "Nama", "Username", "Password"
            }
        ));
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table);

        btn_clear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Refresh-32.png"))); // NOI18N
        btn_clear.setText("Clear");
        btn_clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_clearActionPerformed(evt);
            }
        });

        btn_Save.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Add.png"))); // NOI18N
        btn_Save.setText("Save");
        btn_Save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_SaveActionPerformed(evt);
            }
        });

        btn_update.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Modify.png"))); // NOI18N
        btn_update.setText("Update");
        btn_update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_updateActionPerformed(evt);
            }
        });

        btn_delete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Delete-32.png"))); // NOI18N
        btn_delete.setText("Delete");
        btn_delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_deleteActionPerformed(evt);
            }
        });

        fld_password.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fld_passwordActionPerformed(evt);
            }
        });

        fld_username.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fld_usernameActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Verdana", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(60, 63, 65));
        jLabel1.setText("DATA USER");

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel6.setText("ID");

        jLabel7.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel7.setText("Nama");

        jLabel8.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel8.setText("Username");

        jLabel9.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel9.setText("Password");

        jPanel2.setBackground(new java.awt.Color(0, 153, 204));

        jLabel3.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("PT GARMAK MOTOR");

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/master/gm2.jpg"))); // NOI18N
        jLabel10.setText("jLabel3");

        btnKaryawan.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        btnKaryawan.setText("Karyawan");
        btnKaryawan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKaryawanActionPerformed(evt);
            }
        });

        btnSupplier.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        btnSupplier.setText("Supplier");
        btnSupplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSupplierActionPerformed(evt);
            }
        });

        btnBarang.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        btnBarang.setText("Barang");
        btnBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBarangActionPerformed(evt);
            }
        });

        btnKategori.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        btnKategori.setText("Kategori");
        btnKategori.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKategoriActionPerformed(evt);
            }
        });

        btnKategori1.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        btnKategori1.setText("User ");
        btnKategori1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKategori1ActionPerformed(evt);
            }
        });

        btnLogOut.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        btnLogOut.setText("logout");
        btnLogOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogOutActionPerformed(evt);
            }
        });

        btnHome.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        btnHome.setText("HOME");
        btnHome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHomeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnKaryawan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnSupplier, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnLogOut, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnBarang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnKategori, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(btnKategori1, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnHome, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(98, 98, 98)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(51, 51, 51)
                            .addComponent(jLabel3))))
                .addContainerGap(54, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(jLabel10)
                .addGap(27, 27, 27)
                .addComponent(btnHome, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnKaryawan, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnKategori, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnKategori1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnLogOut, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(255, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(388, 388, 388)
                        .addComponent(jLabel1)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(185, 185, 185)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel9))
                                .addGap(82, 82, 82)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(fld_idlogin, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 577, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(fld_nama, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 577, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(fld_username, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 577, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(fld_password, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 577, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 730, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(78, 78, 78)
                                    .addComponent(btn_clear, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(btn_Save, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(btn_update, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(btn_delete, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 550, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addComponent(jLabel1)
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(fld_idlogin, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(fld_nama, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(fld_username, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(fld_password, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(42, 42, 42)
                        .addComponent(jLabel7)
                        .addGap(32, 32, 32)
                        .addComponent(jLabel8)
                        .addGap(35, 35, 35)
                        .addComponent(jLabel9)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_delete, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_update, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_Save, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_clear, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 671, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void fld_usernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fld_usernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fld_usernameActionPerformed

    private void fld_passwordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fld_passwordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fld_passwordActionPerformed

    private void btn_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_deleteActionPerformed
        // TODO add your handling code here:
        int okay = JOptionPane.showConfirmDialog(null,"Apakah Yakin Menghapus data ini???", "Ijinkan",JOptionPane.YES_NO_CANCEL_OPTION);
        if(
            fld_idlogin.getText().toString().equals("") ||
            fld_nama.getText().toString().equals("") ||
            fld_username.getText().toString().equals("") ||
            fld_password.getText().toString().equals("")
        )
        {
            JOptionPane.showMessageDialog(null, "semua kolom harus di isi .....");
        }else{
            if(okay == 0){
                try{
                    db_conn conn = new db_conn();
                    String qry = "delete from login where id=?";
                    PreparedStatement ps = conn.getConn().prepareStatement(qry);
                    ps.setString(1,fld_idlogin.getText().toString());
                    int result = ps.executeUpdate();
                    fillTable();
                    if(result >= 1){
                        btn_Save.setEnabled(true);
        btn_clear.setEnabled(true);
        btn_delete.setEnabled(false);
        btn_update.setEnabled(false);
                        JOptionPane.showMessageDialog(null,result + "data berhasil di hapus "+ fld_idlogin.getText().toString());
                    }else{
                        JOptionPane.showMessageDialog(null,"data gagal di hapus .......");
                        if (ps!=null){
                            ps.close();
                        }
                        if (conn != null){
                            conn.getConn().close();
                        }
                    }
                }catch(Exception e){
                    JOptionPane.showMessageDialog(null, "masalah pada dbms : "+ e);
                }
            }
            //clear the filds

            fld_nama.setText("");
            fld_username.setText("");
            fld_password.setText("");
        }
    }//GEN-LAST:event_btn_deleteActionPerformed

    private void btn_SaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SaveActionPerformed
        // TODO add your handling code here:
        if(

            fld_nama.getText().toString().equals("") ||
            fld_username.getText().toString().equals("") ||
            fld_password.getText().toString().equals("")
        )
        {
            JOptionPane.showMessageDialog(null, "semua kolom harus terisi .....");
        }else{
            try{

                db_conn conn = new db_conn();
                String qry = "insert into login(id,nama,username,password) values(?,?,?,?)";
                PreparedStatement ps = conn.getConn().prepareStatement(qry);
                ps.setString(1,fld_idlogin.getText().toString());
                ps.setString(2,fld_nama.getText().toString());
                ps.setString(3,fld_username.getText().toString());
                ps.setString(4,fld_password.getText().toString());
                int result = ps.executeUpdate();
                fillTable();
                if(result >= 1){
                    JOptionPane.showMessageDialog(null,result + "data berhasil disimpan .... ");
                }else{
                    JOptionPane.showMessageDialog(null, result + "gagal menyimpan .......");
                    if (ps!=null){
                        ps.close();
                    }
                }if (conn != null){
                    conn.getConn().close();
                }
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, "masalah pada dbms insert: "+ e);
            }
            //clear the filds
           fld_nama.setText("");
            fld_username.setText("");
            fld_password.setText("");
        }
    }//GEN-LAST:event_btn_SaveActionPerformed

    private void btn_clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_clearActionPerformed
        // TODO add your handling code here:
      userLogin ug = new userLogin();
      ug.setVisible(true);
      this.setVisible(false);

    }//GEN-LAST:event_btn_clearActionPerformed

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
        btn_Save.setEnabled(false);

        btn_delete.setEnabled(true);
        btn_update.setEnabled(true);
        int ind = table.getSelectedRow();
        showItem(ind);   
    }//GEN-LAST:event_tableMouseClicked

    private void btn_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_updateActionPerformed
        // TODO add your handling code here:
        if(

            fld_nama.getText().toString().equals("") ||
            fld_username.getText().toString().equals("") ||
            fld_password.getText().toString().equals("")
        )
        {
            JOptionPane.showMessageDialog(null, "semua kolom di isi .....");
        }else{
            try{
                db_conn conn = new db_conn();
                String qry = "update login set nama=?, username=?, password=? where id=?";
                PreparedStatement ps = conn.getConn().prepareStatement(qry);
                ps.setString(1,fld_nama.getText().toString());
                ps.setString(2,fld_username.getText().toString());
                ps.setString(3,fld_password.getText().toString());

                ps.setString(4,fld_idlogin.getText().toString());
                int result = ps.executeUpdate();
                fillTable();
                if(result >= 1){
                     btn_Save.setEnabled(true);
        btn_clear.setEnabled(true);
        btn_delete.setEnabled(false);
        btn_update.setEnabled(false);
                    JOptionPane.showMessageDialog(null,result + "update sukses .... "+ fld_idlogin.getText().toString());
                }else{
                    JOptionPane.showMessageDialog(null,"update gagal .......");
                    if (ps!=null){
                        ps.close();
                    }
                }if (conn != null){
                    conn.getConn().close();
                }
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, "masalah pada dbms : "+ e);
            }
            //clear the filds
            fld_nama.setText("");
            fld_username.setText("");
            fld_password.setText("");
        }
    }//GEN-LAST:event_btn_updateActionPerformed

    private void btnKaryawanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKaryawanActionPerformed
        karyawan kr = new karyawan();
        kr.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnKaryawanActionPerformed

    private void btnSupplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSupplierActionPerformed
        supplier supplier = new supplier();
        supplier.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnSupplierActionPerformed

    private void btnBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBarangActionPerformed
        barang barang = new barang();
        barang.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnBarangActionPerformed

    private void btnKategoriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKategoriActionPerformed
        kategori kategori = new kategori();
        kategori.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnKategoriActionPerformed

    private void btnKategori1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKategori1ActionPerformed
        // TODO add your handling code here:
        userLogin ug = new userLogin();
        ug.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnKategori1ActionPerformed

    private void btnLogOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogOutActionPerformed
        login logout = new login();
        logout.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnLogOutActionPerformed

    private void btnHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHomeActionPerformed
        // btnBmasuk
        home bm = new home();
        bm.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnHomeActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(userLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(userLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(userLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(userLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new userLogin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBarang;
    private javax.swing.JButton btnHome;
    private javax.swing.JButton btnKaryawan;
    private javax.swing.JButton btnKategori;
    private javax.swing.JButton btnKategori1;
    private javax.swing.JButton btnLogOut;
    private javax.swing.JButton btnSupplier;
    private javax.swing.JButton btn_Save;
    private javax.swing.JButton btn_clear;
    private javax.swing.JButton btn_delete;
    private javax.swing.JButton btn_update;
    private javax.swing.JTextField fld_idlogin;
    private javax.swing.JTextField fld_nama;
    private javax.swing.JTextField fld_password;
    private javax.swing.JTextField fld_username;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables
}