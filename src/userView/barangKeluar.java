/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userView;

import connection.db_conn;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import login.login;
import master.master;
import model.jBKaryawan;
import model.jBsupplier;
import model.jbBarang;
import model.jbBarang_K;
/**
 *
 * @author acepmunawar
 */
public class barangKeluar extends javax.swing.JFrame {

    /**
     * Creates new form barangKeluar
     */
    public barangKeluar() {
        initComponents();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        fillTable();
        auto_number();
        fillTableB();
        fillTableK();
        fld_kodebk.setEditable(false);

                            btn_update.setEnabled(false);
                    btn_delete.setEnabled(false);
    }

    public ArrayList<jbBarang_K> getAllBarK() {
        ArrayList<jbBarang_K> al = null;
        al = new ArrayList<jbBarang_K>();
        try {
            db_conn conn = new db_conn();
            String qry = "select id,barang_keluar.tanggal,kode_barang,nama_barang,kd_karyawan,nama_karyawan,jumlah from barang_keluar join barang on kd_barang = kode_barang join karyawan on kd_karyawan = id_karyawan";
            Statement st = conn.getConn().createStatement();
            ResultSet rs = st.executeQuery(qry);
            jbBarang_K bean;
            while (rs.next()) {
                bean = new jbBarang_K(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        Integer.parseInt(rs.getString(7))
                );
                al.add(bean);
            }
        } catch (Exception e) {

            System.out.print("sory your wrong");
        }
        return al;
    }

    public void fillTable() {
        ArrayList<jbBarang_K> al = getAllBarK();
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        auto_number();
        Object[] row = new Object[7];
        for (int i = 0; i < al.size(); i++) {
            row[0] = al.get(i).getId();
            row[1] = al.get(i).getTanggal();
            row[2] = al.get(i).getkdBarang();
            row[3] = al.get(i).getNamaBarang();
            row[4] = al.get(i).getkdKaryawan();
            row[5] = al.get(i).getNamaKaryawan();
            row[6] = al.get(i).getJumlah();
            model.addRow(row);
        }
    }

    public void showItem(int index) {

        fld_kodebk.setText(getAllBarK().get(index).getId());
        try {
            java.util.Date date = null;
            date = new SimpleDateFormat("yyyy-MM-dd").parse((String) getAllBarK().get(index).getTanggal());
            jDateChooser1.setDate(date);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error show item to fields teacher()" + e);
        }
        fld_kdbarang.setText(getAllBarK().get(index).getkdBarang());
        fld_kdkaryawan.setText(getAllBarK().get(index).getkdKaryawan());
        fld_jumlah.setText(Integer.toString(getAllBarK().get(index).getJumlah()));
    }

    
    
    
     private void auto_number(){
        try{
            db_conn conn = new db_conn(); 
            String sql = "SELECT MAX(right(id,2)) AS no FROM barang_keluar";
             Statement st = conn.getConn().createStatement();
           ResultSet rs = st.executeQuery(sql);
           while (rs.next()){
               if(rs.first() == false){
                   fld_kodebk.setText("BK001");
               
               }else{
                   rs.last();
                   int auto_id = rs.getInt(1) + 1;
                   String no = String.valueOf(auto_id);
                   int noLong = no.length();
                        for(int a = 0; a < 3 - noLong; a++){
                            no = "0" + no;
                        }
                   fld_kodebk.setText("BK"+ no);     
               }
           }
        }catch(Exception e){
        
            JOptionPane.showMessageDialog(this,"error: \n" + e.toString(), "kelasahan", JOptionPane.WARNING_MESSAGE); 
        
        }
    
    }
  
    
    
      public ArrayList<jbBarang> getAllBarangDataB(){
    ArrayList<jbBarang> al = null;
    al = new ArrayList<jbBarang>();
    try{
    db_conn conn = new db_conn();     
           String qry = "Select * from barang join kategori on barang. id_kat = kategori.id_kategori ";
           Statement st = conn.getConn().createStatement();
           ResultSet rs = st.executeQuery(qry);
    jbBarang bean;
        while(rs.next()){
              bean = new jbBarang(
                                           rs.getString("tanggal"), 
                                           rs.getString("kode_barang"),
                                           rs.getString("nama_barang"),
                                           rs.getString("id_kategori"),
                                           rs.getString("nama_kategori"),
                          Integer.parseInt(rs.getString("no_rak")),
                          Integer.parseInt(rs.getString("stock"))
              );                                                   
              al.add(bean);         

        }
    }catch(Exception e){    
        System.out.print("sory your wrong");   
    }
    return al;
}


public void fillTableB(){
        ArrayList<jbBarang> al = getAllBarangDataB();
        DefaultTableModel modelB = (DefaultTableModel)tablebarang.getModel();
        modelB.setRowCount(0);
        auto_number();
        Object[] row = new Object[10];
        for(int i =0 ; i < al.size(); i++){
            row[0] = al.get(i).getkodebarang();
            row[1] = al.get(i).getnamabarang();
            row[2] = al.get(i).getnamakategori();
          
            modelB.addRow(row);        
        }
    }
     
    public void showItemB(int index){
         
        fld_kdbarang.setText(getAllBarangDataB().get(index).getkodebarang());
       
      

    }
    
    private void auto_numberB(){
        try{
            db_conn conn = new db_conn(); 
            String sql = "SELECT MAX(right(kode_barang,2)) AS no FROM barang";
             Statement st = conn.getConn().createStatement();
           ResultSet rs = st.executeQuery(sql);
           while (rs.next()){
               if(rs.first() == false){
                   fld_kdbarang.setText("B001");
               
               }else{
                   rs.last();
                   int auto_id = rs.getInt(1) + 1;
                   String no = String.valueOf(auto_id);
                   int noLong = no.length();
                        for(int a = 0; a < 3 - noLong; a++){
                            no = "0" + no;
                        }
                   fld_kdbarang.setText("B"+ no);     
               }
           }
        }catch(Exception e){
        
            JOptionPane.showMessageDialog(this,"error: \n" + e.toString(), "kelasahan", JOptionPane.WARNING_MESSAGE); 
        
        }
    
    } 
        
    
    
    //karyawan
     public ArrayList<jBKaryawan> getAllKaryDataK(){
    ArrayList<jBKaryawan> al = null;
    al = new ArrayList<jBKaryawan>();
    try{
    db_conn con = new db_conn();     
           String qry = "Select * from karyawan ";
           Statement st = con.getConn().createStatement();
           ResultSet rs = st.executeQuery(qry);
    jBKaryawan bean;
        while(rs.next()){
              bean = new jBKaryawan(
                          rs.getString(1),
                          rs.getString(2),
                          rs.getString(3),
                          rs.getString(4));                                                   
              al.add(bean);
     }
    }catch(Exception e){
    
    System.out.print("sory your wrong");   
    }
    return al;
}

public void fillTableK(){
        ArrayList<jBKaryawan> al = getAllKaryDataK();
        DefaultTableModel model = (DefaultTableModel)tablekar.getModel();
        model.setRowCount(0);
        auto_number();
        Object[] row = new Object[5];
        for(int i =0 ; i < al.size(); i++){
            row[0] = al.get(i).getIdKaryawan();       
            row[1] = al.get(i).getNamaKary();
            row[2] = al.get(i).getStaff();
            row[3] = al.get(i).getNoTlp();
            model.addRow(row);        
        }
    }
     
    public void showItemK(int index){
        fld_kdkaryawan.setText(getAllKaryDataK().get(index).getIdKaryawan()); 
        
    }
        
    
      private void auto_numberK(){
        try{
            db_conn conn = new db_conn(); 
            String sql = "SELECT MAX(right(id_karyawan,2)) AS no FROM karyawan";
             Statement st = conn.getConn().createStatement();
           ResultSet rs = st.executeQuery(sql);
           while (rs.next()){
               if(rs.first() == false){
                   fld_kdkaryawan.setText("KR001");
               
               }else{
                   rs.last();
                   int auto_id = rs.getInt(1) + 1;
                   String no = String.valueOf(auto_id);
                   int noLong = no.length();
                        for(int a = 0; a < 3 - noLong; a++){
                            no = "0" + no;
                        }
                   fld_kdkaryawan.setText("KR"+ no);     
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

        menuBar1 = new java.awt.MenuBar();
        menu1 = new java.awt.Menu();
        menu2 = new java.awt.Menu();
        popupMenu1 = new java.awt.PopupMenu();
        jLabel1 = new javax.swing.JLabel();
        btn_save = new javax.swing.JButton();
        btn_clear = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        fld_searchBarang = new javax.swing.JTextField();
        fld_kdbarang = new javax.swing.JTextField();
        fld_jumlah = new javax.swing.JTextField();
        fld_kdkaryawan = new javax.swing.JTextField();
        id_karya2 = new javax.swing.JLabel();
        id_karya3 = new javax.swing.JLabel();
        id_karya4 = new javax.swing.JLabel();
        id_karya5 = new javax.swing.JLabel();
        id_karya6 = new javax.swing.JLabel();
        fld_kodebk = new javax.swing.JTextField();
        id_karya7 = new javax.swing.JLabel();
        btn_update = new javax.swing.JButton();
        btn_delete = new javax.swing.JButton();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablebarang = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablekar = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnHome = new javax.swing.JButton();
        btnBmasuk = new javax.swing.JButton();
        barangKeluar = new javax.swing.JButton();
        btnLogOut = new javax.swing.JButton();
        permintaan = new javax.swing.JButton();
        permintaan2 = new javax.swing.JButton();
        btnStock1 = new javax.swing.JButton();

        menu1.setLabel("File");
        menuBar1.add(menu1);

        menu2.setLabel("Edit");
        menuBar1.add(menu2);

        popupMenu1.setLabel("popupMenu1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Verdana", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(60, 63, 65));
        jLabel1.setText("BARANG KELUAR");

        btn_save.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Add.png"))); // NOI18N
        btn_save.setText("Save");
        btn_save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_saveActionPerformed(evt);
            }
        });

        btn_clear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Refresh-32.png"))); // NOI18N
        btn_clear.setText("Clear");
        btn_clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_clearActionPerformed(evt);
            }
        });

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Kode Keluar", "Tanggal", "Kode barang", "Nama Barang", "Kode karyawan", "Nama Karyawan", "Jumlah"
            }
        ));
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMouseClicked(evt);
            }
        });
        table.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tableKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(table);

        fld_searchBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fld_searchBarangActionPerformed(evt);
            }
        });
        fld_searchBarang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                fld_searchBarangKeyReleased(evt);
            }
        });

        fld_kdbarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fld_kdbarangActionPerformed(evt);
            }
        });
        fld_kdbarang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                fld_kdbarangKeyReleased(evt);
            }
        });

        fld_jumlah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fld_jumlahActionPerformed(evt);
            }
        });

        fld_kdkaryawan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fld_kdkaryawanMouseClicked(evt);
            }
        });
        fld_kdkaryawan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fld_kdkaryawanActionPerformed(evt);
            }
        });
        fld_kdkaryawan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                fld_kdkaryawanKeyReleased(evt);
            }
        });

        id_karya2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        id_karya2.setText("TANGGAL");

        id_karya3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        id_karya3.setText("KODE BARANG");

        id_karya4.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        id_karya4.setText("KODE KARYAWAN");

        id_karya5.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        id_karya5.setText("JUMLAH");

        id_karya6.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        id_karya6.setText("SEARCH");

        fld_kodebk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fld_kodebkActionPerformed(evt);
            }
        });

        id_karya7.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        id_karya7.setText("KODE BK");

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

        tablebarang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID", "NAMA", "KATEGORI"
            }
        ));
        tablebarang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablebarangMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tablebarang);

        tablekar.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID", "NAMA", "STAFF"
            }
        ));
        tablekar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablekarMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tablekar);

        jPanel2.setBackground(new java.awt.Color(0, 153, 204));

        jLabel2.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("PT GARMAK MOTOR");

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/userView/gm2.jpg"))); // NOI18N
        jLabel3.setText("jLabel3");

        btnHome.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        btnHome.setText("HOME");
        btnHome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHomeActionPerformed(evt);
            }
        });

        btnBmasuk.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        btnBmasuk.setText("barang masuk");
        btnBmasuk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBmasukActionPerformed(evt);
            }
        });

        barangKeluar.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        barangKeluar.setText("barang keluar");
        barangKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                barangKeluarActionPerformed(evt);
            }
        });

        btnLogOut.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        btnLogOut.setText("logout");
        btnLogOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogOutActionPerformed(evt);
            }
        });

        permintaan.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        permintaan.setText("permintaan");
        permintaan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                permintaanActionPerformed(evt);
            }
        });

        permintaan2.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        permintaan2.setText("laporan");
        permintaan2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                permintaan2ActionPerformed(evt);
            }
        });

        btnStock1.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        btnStock1.setText("master");
        btnStock1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStock1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(100, 100, 100)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(barangKeluar, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnLogOut, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnBmasuk, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnHome, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(permintaan, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(permintaan2, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnStock1, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(31, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addComponent(jLabel2)
                .addGap(12, 12, 12)
                .addComponent(jLabel3)
                .addGap(41, 41, 41)
                .addComponent(btnHome, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23)
                .addComponent(btnBmasuk, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(barangKeluar, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnStock1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addComponent(permintaan, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(permintaan2, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnLogOut, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(176, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(393, 393, 393)
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(id_karya7)
                                    .addComponent(id_karya6)
                                    .addComponent(id_karya3))
                                .addGap(30, 30, 30)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(fld_kdbarang, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(fld_kodebk, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 283, Short.MAX_VALUE)
                                        .addComponent(fld_searchBarang, javax.swing.GroupLayout.Alignment.LEADING))))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(36, 36, 36)
                                .addComponent(id_karya2)
                                .addGap(76, 76, 76))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(id_karya5)
                                    .addComponent(id_karya4))
                                .addGap(18, 18, 18)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(fld_kdkaryawan)
                            .addComponent(fld_jumlah)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(213, 213, 213)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btn_clear, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(btn_save, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btn_update, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27)
                                .addComponent(btn_delete, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1))))
                .addGap(670, 670, 670))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabel1)
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(fld_kdkaryawan, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(id_karya4)
                            .addComponent(fld_kdbarang, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(fld_kodebk, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(id_karya7)
                            .addComponent(id_karya2))
                        .addGap(18, 18, 18)
                        .addComponent(id_karya3)
                        .addGap(17, 17, 17)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fld_jumlah, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fld_searchBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(id_karya6)
                    .addComponent(id_karya5))
                .addGap(16, 16, 16)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_save, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_clear, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_delete, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_update, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_saveActionPerformed

        if (jDateChooser1.equals("")
                || fld_kdbarang.getText().toString().equals("")
                || fld_kdkaryawan.getText().toString().equals("")
                || fld_jumlah.getText().toString().equals("")) {
            JOptionPane.showMessageDialog(null, "semua kolom harus di isi .....");
        } else {
            try {

                db_conn conn = new db_conn();
                String qry = "insert into barang_keluar(id,tanggal,kd_barang,kd_karyawan,jumlah) values(?,?,?,?,?)";
                PreparedStatement ps = conn.getConn().prepareStatement(qry);
                ps.setString(1, fld_kodebk.getText().toString());
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String date1 = sdf.format(jDateChooser1.getDate());
                ps.setString(2, date1);
                ps.setString(3, fld_kdbarang.getText().toString());
                ps.setString(4, fld_kdkaryawan.getText().toString());
                ps.setInt(5, Integer.parseInt(fld_jumlah.getText().toString()));
                int result = ps.executeUpdate();
                fillTable();
                if (result >= 1) {
                    JOptionPane.showMessageDialog(null, result + "data berhasil disimpan .... ");
                } else {
                    JOptionPane.showMessageDialog(null, result + "gagal menyimpan .......");
                    if (ps != null) {
                        ps.close();
                    }
                }
                if (conn != null) {
                    conn.getConn().close();
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "masalah pada dbms : " + e);
            }
            //clear the filds    
    jDateChooser1.setDate(null);
            fld_kdbarang.setText("");
            fld_kdkaryawan.setText("");
            fld_jumlah.setText("");
        }

    }//GEN-LAST:event_btn_saveActionPerformed

    private void btn_clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_clearActionPerformed
        // TODO add your handling code here:
    barangKeluar bk = new barangKeluar();
    bk.setVisible(true);
    this.setVisible(false);

    }//GEN-LAST:event_btn_clearActionPerformed

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
        // TODO add your handling code here:
  
                    btn_save.setEnabled(false);
                    btn_update.setEnabled(true);
                    btn_delete.setEnabled(true);
        int ind = table.getSelectedRow();
        showItem(ind);

    }//GEN-LAST:event_tableMouseClicked

    private void tableKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tableKeyReleased
        // TODO add your handling code here:

    }//GEN-LAST:event_tableKeyReleased

    private void fld_searchBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fld_searchBarangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fld_searchBarangActionPerformed

    private void fld_kdbarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fld_kdbarangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fld_kdbarangActionPerformed

    private void fld_jumlahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fld_jumlahActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fld_jumlahActionPerformed

    private void fld_kdkaryawanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fld_kdkaryawanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fld_kdkaryawanActionPerformed

    private void fld_kodebkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fld_kodebkActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fld_kodebkActionPerformed

    private void jDateChooser1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jDateChooser1PropertyChange
        // TODO add your handling code here:
        jDateChooser1.setDateFormatString("yyyy-MM-dd");
    }//GEN-LAST:event_jDateChooser1PropertyChange

    private void btn_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_updateActionPerformed
        // TODO add your handling code here:
        if (jDateChooser1.equals("")
                || fld_kdbarang.getText().toString().equals("")
                || fld_kdkaryawan.getText().toString().equals("")
                || fld_jumlah.getText().toString().equals("")) {
            JOptionPane.showMessageDialog(null, "semua data harus di isi .....");
        } else {
            try {
                db_conn conn = new db_conn();
                String qry = "update barang_keluar set tanggal=?, kd_barang=?, kd_karyawan=?, jumlah=? where id=?";
                PreparedStatement ps = conn.getConn().prepareStatement(qry);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String date1 = sdf.format(jDateChooser1.getDate());
                ps.setString(1, date1);
                ps.setString(2, fld_kdbarang.getText().toString());
                ps.setString(3, fld_kdkaryawan.getText().toString());
                ps.setInt(4, Integer.parseInt(fld_jumlah.getText().toString()));
                ps.setString(5, fld_kodebk.getText().toString());
                int result = ps.executeUpdate();
                fillTable();
                if (result >= 1) {
                    btn_clear.setEnabled(true);
                    btn_save.setEnabled(true);
                    btn_update.setEnabled(false);
                    btn_delete.setEnabled(false);
                    
                    JOptionPane.showMessageDialog(null, result + "update berhasil .... " + fld_kodebk.getText().toString());
                } else {
                    JOptionPane.showMessageDialog(null, "update gagal .......");
                    if (ps != null) {
                        ps.close();
                    }
                }
                if (conn != null) {
                    conn.getConn().close();
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "masalah pada dbms : " + e);
            }
            //clear the filds
            jDateChooser1.setDate(null);
            fld_kdbarang.setText("");
            fld_kdkaryawan.setText("");
            fld_jumlah.setText("");
        }

    }//GEN-LAST:event_btn_updateActionPerformed

    private void btn_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_deleteActionPerformed

        int okay = JOptionPane.showConfirmDialog(null, "Apakah Yakin Menghapus data ini???", "Ijinkan", JOptionPane.YES_NO_CANCEL_OPTION);
        if (jDateChooser1.equals("")
                || fld_kdbarang.getText().toString().equals("")
                || fld_kdkaryawan.getText().toString().equals("")
                || fld_jumlah.getText().toString().equals("")) {
            JOptionPane.showMessageDialog(null, "semua kolom harus terisi .....");
        } else {
            if (okay == 0) {
                try {
                    db_conn conn = new db_conn();
                    String qry = "delete from barang_keluar where id=?";
                    PreparedStatement ps = conn.getConn().prepareStatement(qry);
                    ps.setString(1,fld_kodebk.getText().toString());
                    int result = ps.executeUpdate();
                    fillTable();
                    if (result >= 1) {
                           btn_clear.setEnabled(true);
                    btn_save.setEnabled(true);
                    btn_update.setEnabled(false);
                    btn_delete.setEnabled(false);
                        JOptionPane.showMessageDialog(null, result + "data berhasil di hapus " + fld_kodebk.getText().toString());
                    } else {
                        JOptionPane.showMessageDialog(null, "data gagal di hapus .......");
                        if (ps != null) {
                            ps.close();
                        }
                        if (conn != null) {
                            conn.getConn().close();
                        }
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "masalah pada dbms : " + e);
                }
            }
            //clear the filds
    jDateChooser1.setDate(null);
            fld_kdbarang.setText("");
            fld_kdkaryawan.setText("");
            fld_jumlah.setText("");
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_btn_deleteActionPerformed

    private void fld_searchBarangKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fld_searchBarangKeyReleased
        // TODO add your handling code here:
        // TODO add your handling code here:
        ArrayList<jbBarang_K> al = null;
        al = new ArrayList<jbBarang_K>();
        String cari = fld_searchBarang.getText().toString();

        try {
            db_conn conn = new db_conn();
            String sql = "select id,barang_keluar.tanggal,id_karyawan,nama_karyawan,kd_barang,nama_barang,"
                    + "jumlah from barang_keluar join barang on kode_barang = kd_barang join karyawan on id_karyawan = kd_karyawan where barang_keluar.kd_karyawan = '%" + cari + "%'"
                    + "or nama_karyawan like '%" + cari + "%'" + "or kd_barang like '%" + cari + "%'"
                    + "or nama_barang like '%" + cari + "%'" + "or barang_keluar.tanggal like '%" + cari + "%'"
                    + "or id_karyawan like '%" + cari + "%'";
            Statement st = conn.getConn().createStatement();
            ResultSet rs = st.executeQuery(sql);
            jbBarang_K teacher;
            jbBarang_K bean;
            while (rs.next()) {
                bean = new jbBarang_K(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        Integer.parseInt(rs.getString(7))
                );
                al.add(bean);

            }

            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.setRowCount(0);
            Object[] row = new Object[10];
            for (int i = 0; i < al.size(); i++) {
                row[0] = al.get(i).getId();
                row[1] = al.get(i).getTanggal();
                row[2] = al.get(i).getkdBarang();
                row[3] = al.get(i).getNamaBarang();
                row[4] = al.get(i).getkdKaryawan();
                row[5] = al.get(i).getNamaKaryawan();
                row[6] = al.get(i).getJumlah();
                model.addRow(row);
            }

            if (st != null) {
                st.close();

            }
            if (conn != null) {
                conn.getConn().close();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error at JtextField1_searchKeyReleased " + e);

        }


    }//GEN-LAST:event_fld_searchBarangKeyReleased

    private void tablebarangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablebarangMouseClicked
        // TODO add your handling code here:
                 int ind = tablebarang.getSelectedRow();
        showItemB(ind);
    }//GEN-LAST:event_tablebarangMouseClicked

    private void tablekarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablekarMouseClicked
        // TODO add your handling code here:
             int ind = tablekar.getSelectedRow();
        showItemK(ind);
    }//GEN-LAST:event_tablekarMouseClicked

    private void fld_kdkaryawanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fld_kdkaryawanMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_fld_kdkaryawanMouseClicked

    private void fld_kdkaryawanKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fld_kdkaryawanKeyReleased
        // TODO add your handling code here:
            ArrayList<jBKaryawan> al = null;
        al = new ArrayList<jBKaryawan>();
        String val = fld_kdkaryawan.getText().toString();
        
        try{
            db_conn conn = new db_conn();
            String qry = "SELECT * FROM karyawan WHERE id_karyawan like '%" + val +"%'"+"or nama_karyawan like '%"+ val +"%'"+"or staff like '%"+ val +"%'"+"or no_tlp like '%"+ val +"%'" ;
            Statement st = conn.getConn().createStatement();
            ResultSet rs = st.executeQuery(qry);
            jBKaryawan teacher;
             jBKaryawan bean;
        while(rs.next()){
              bean = new jBKaryawan(
                          rs.getString(1),
                          rs.getString(2),
                          rs.getString(3),
                          rs.getString(4));                                                   
              al.add(bean);
     }
              
                   DefaultTableModel model = (DefaultTableModel)tablekar.getModel();
        model.setRowCount(0);
        Object[] row = new Object[6];
        for(int i =0 ; i < al.size(); i++){
            row[0] = al.get(i).getIdKaryawan();       
            row[1] = al.get(i).getNamaKary();
            row[2] = al.get(i).getStaff();
        
            model.addRow(row);             
        }
        
        if (st!=null){
                st.close();
        
        }
        if (conn != null){
                conn.getConn().close();
        }           
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error at JtextField1_searchKeyReleased " + e);
        
        
        }
    }//GEN-LAST:event_fld_kdkaryawanKeyReleased

    private void fld_kdbarangKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fld_kdbarangKeyReleased
        // TODO add your handling code here:
        // TODO add your handling code here:
                 ArrayList<jbBarang> al = null;
        al = new ArrayList<jbBarang>();
        String cari = fld_kdbarang.getText().toString();
        
        try{
            db_conn conn = new db_conn();
            String sql = "SELECT * FROM barang join kategori on kategori.id_kategori = barang.id_kat where barang.nama_barang like '%" + cari +"%'"
                          +"or nama_kategori like '%"+ cari +"%'"+"or kode_barang like '%"+ cari +"%'"
                          +"or no_rak like '%"+ cari +"%'"+"or stock like '%"+ cari +"%'"+"or tanggal like '%"+ cari +"%'" ;
            Statement st = conn.getConn().createStatement();
            ResultSet rs = st.executeQuery(sql);
            jbBarang teacher;
             jbBarang bean;
        while(rs.next()){
              bean = new jbBarang(
                                           rs.getString("tanggal"), 
                                           rs.getString("kode_barang"),
                                           rs.getString("nama_barang"),
                                           rs.getString("id_kategori"),
                                           rs.getString("nama_kategori"),
                          Integer.parseInt(rs.getString("no_rak")),
                          Integer.parseInt(rs.getString("stock")));                                                  
              al.add(bean);
          
     }
              
                   DefaultTableModel modelB = (DefaultTableModel)tablebarang.getModel();
        modelB.setRowCount(0);
        Object[] row = new Object[8];
        for(int i =0 ; i < al.size(); i++){
            row[0] = al.get(i).getkodebarang();        
            row[1] = al.get(i).getnamabarang();          
            row[2] = al.get(i).getnamakategori();
            modelB.addRow(row);             
        }
        
        if (st!=null){
                st.close();
        
        }
        if (conn != null){
                conn.getConn().close();
        }           
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error at JtextField1_searchKeyReleased " + e);
        
        
        }
        


    }//GEN-LAST:event_fld_kdbarangKeyReleased

    private void btnHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHomeActionPerformed
        // btnBmasuk
        home bm = new home();
        bm.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnHomeActionPerformed

    private void btnBmasukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBmasukActionPerformed
        barangMasuk bm = new barangMasuk();
        bm.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnBmasukActionPerformed

    private void barangKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_barangKeluarActionPerformed
        barangKeluar bR = new barangKeluar();
        bR.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_barangKeluarActionPerformed

    private void btnLogOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogOutActionPerformed
        // TODO add your handling code here:
        login logout = new login();
        logout.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnLogOutActionPerformed

    private void permintaanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_permintaanActionPerformed
        permintaan_barang hA = new permintaan_barang();
        hA.setVisible(true);
        this.setVisible(false);

        // TODO add your handling code here:
    }//GEN-LAST:event_permintaanActionPerformed

    private void permintaan2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_permintaan2ActionPerformed
      laporan lap = new laporan();
        lap.setVisible(true);
        this.setVisible(false);        // TODO add your handling code here:
    }//GEN-LAST:event_permintaan2ActionPerformed

    private void btnStock1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStock1ActionPerformed
        // TODO add your handling code here:

        master mas = new master();
        mas.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnStock1ActionPerformed

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
            java.util.logging.Logger.getLogger(barangKeluar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(barangKeluar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(barangKeluar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(barangKeluar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new barangKeluar().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton barangKeluar;
    private javax.swing.JButton btnBmasuk;
    private javax.swing.JButton btnHome;
    private javax.swing.JButton btnLogOut;
    private javax.swing.JButton btnStock1;
    private javax.swing.JButton btn_clear;
    private javax.swing.JButton btn_delete;
    private javax.swing.JButton btn_save;
    private javax.swing.JButton btn_update;
    private javax.swing.JTextField fld_jumlah;
    private javax.swing.JTextField fld_kdbarang;
    private javax.swing.JTextField fld_kdkaryawan;
    private javax.swing.JTextField fld_kodebk;
    private javax.swing.JTextField fld_searchBarang;
    private javax.swing.JLabel id_karya2;
    private javax.swing.JLabel id_karya3;
    private javax.swing.JLabel id_karya4;
    private javax.swing.JLabel id_karya5;
    private javax.swing.JLabel id_karya6;
    private javax.swing.JLabel id_karya7;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private java.awt.Menu menu1;
    private java.awt.Menu menu2;
    private java.awt.MenuBar menuBar1;
    private javax.swing.JButton permintaan;
    private javax.swing.JButton permintaan2;
    private java.awt.PopupMenu popupMenu1;
    private javax.swing.JTable table;
    private javax.swing.JTable tablebarang;
    private javax.swing.JTable tablekar;
    // End of variables declaration//GEN-END:variables
}
