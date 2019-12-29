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
import javax.swing.table.TableColumn;
import login.login;
import master.master;
import model.jBsupplier;
import model.jbBarang;
import model.jbBarang_M;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
/**
 *
 * @author acepmunawar
 */
public class barangMasuk extends javax.swing.JFrame {

    /**
     * Creates new form barangMasuk
     */
    public barangMasuk() {
        initComponents();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        fillTable();
        lebarKolom();
        auto_number();
        fillTableS();
        fillTableB();
        fld_idbm.setEditable(false);
       
        btn_delete.setEnabled(false);
        btn_update.setEnabled(false);
    }
    
    public void lebarKolom(){ 
        TableColumn column;
        tableBarang.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF); 
        column = tableBarang.getColumnModel().getColumn(0); 
        column.setPreferredWidth(50);
        column = tableBarang.getColumnModel().getColumn(1); 
        column.setPreferredWidth(90); 
        column = tableBarang.getColumnModel().getColumn(2); 
        column.setPreferredWidth(60); 
        column = tableBarang.getColumnModel().getColumn(3); 
        column.setPreferredWidth(150);
        column = tableBarang.getColumnModel().getColumn(4); 
        column.setPreferredWidth(150);
        column = tableBarang.getColumnModel().getColumn(5); 
        column.setPreferredWidth(70);
        column = tableBarang.getColumnModel().getColumn(6); 
        column.setPreferredWidth(100);
        column = tableBarang.getColumnModel().getColumn(7); 
        column.setPreferredWidth(80);
        
    }
    
     public ArrayList<jbBarang_M> getAllBarM(){
    ArrayList<jbBarang_M> al = null;
    al = new ArrayList<jbBarang_M>();
    try{
    db_conn conn = new db_conn();     
           String qry = "select id,barang_masuk.tanggal,id_supplier,namaSupplier,alamatSupplier,kd_barang,nama_barang,jumlah from barang_masuk join barang on kode_barang = kd_barang join supplier on kd_supplier = id_supplier";
           Statement st = conn.getConn().createStatement();
           ResultSet rs = st.executeQuery(qry);
    jbBarang_M bean;
        while(rs.next()){
              bean = new jbBarang_M(
                          rs.getString(1),
                          rs.getString(2),
                          rs.getString(3),
                          rs.getString(4),
                          rs.getString(5),
                          rs.getString(6),
                          
                          rs.getString(7),                        
                          Integer.parseInt(rs.getString(8))
                        
              );                                                   
              al.add(bean);
      
        }
    }catch(Exception e){
    
    System.out.print("sory your wrong table barang masuk");   
    }
    return al;
}

public void fillTable(){
        ArrayList<jbBarang_M> al = getAllBarM();
        DefaultTableModel model = (DefaultTableModel)tableBarang.getModel();
        model.setRowCount(0);
        auto_number();
        Object[] row = new Object[8];
        for(int i =0 ; i < al.size(); i++){            
            row[0] = al.get(i).getId();
            row[1] = al.get(i).getTanggal();
            row[2] = al.get(i).getIdSupplier();
            row[3] = al.get(i).getNama_S();
            row[4] = al.get(i).getAlamat_S();
            row[5] = al.get(i).getKodeBarang();
            row[6] = al.get(i).getNama_B();
            row[7] = al.get(i).getJumlah();
            model.addRow(row);        
        }
    }
    
        public void showItem(int index){
        
        fld_idbm.setText(getAllBarM().get(index).getId());    
        try{
            java.util.Date date = null;
            date = new SimpleDateFormat("yyyy-MM-dd").parse((String)getAllBarM().get(index).getTanggal());
            jDateChooser1.setDate(date);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Error show item to fields teacher()" + e);
        }
        fld_idsupplier.setText(getAllBarM().get(index).getIdSupplier());
        fld_kodebarang.setText(getAllBarM().get(index).getKodeBarang());
        fld_jumlah.setText(Integer.toString(getAllBarM().get(index).getJumlah()));
    }

        
        
        
     private void auto_number(){
        try{
            db_conn conn = new db_conn(); 
            String sql = "SELECT MAX(right(id,2)) AS no FROM barang_masuk";
             Statement st = conn.getConn().createStatement();
           ResultSet rs = st.executeQuery(sql);
           while (rs.next()){
               if(rs.first() == false){
                   fld_idbm.setText("BM001");
               
               }else{
                   rs.last();
                   int auto_id = rs.getInt(1) + 1;
                   String no = String.valueOf(auto_id);
                   int noLong = no.length();
                        for(int a = 0; a < 3 - noLong; a++){
                            no = "0" + no;
                        }
                   fld_idbm.setText("BM"+ no);     
               }
           }
        }catch(Exception e){
        
            JOptionPane.showMessageDialog(this,"error: \n" + e.toString(), "kelasahan", JOptionPane.WARNING_MESSAGE); 
        
        }
    
    }
        
        
     
     
  public ArrayList<jBsupplier> getAllSupData(){
    ArrayList<jBsupplier> al = null;
    al = new ArrayList<jBsupplier>();
    try{
    db_conn db = new db_conn();     
           String qry = "Select * from supplier ";
           Statement st = db.getConn().createStatement();
           ResultSet rs = st.executeQuery(qry);
    jBsupplier beanS;
        while(rs.next()){
              beanS = new jBsupplier(
                          rs.getString(1),
                          rs.getString(2),
                          rs.getString(3),
                          rs.getString(4));                                                   
              al.add(beanS);

     }
    }catch(Exception e){
    
    System.out.print("sory your wrong");   
    }
    return al;
}
    
public void fillTableS(){
        ArrayList<jBsupplier> al = getAllSupData();
        DefaultTableModel modelS = (DefaultTableModel)tablesupplier.getModel();
        modelS.setRowCount(0);
        auto_number();
        Object[] row = new Object[5];
        for(int i =0 ; i < al.size(); i++){
            row[0] = al.get(i).getIdSupplier();       
            row[1] = al.get(i).getNamaSupplier();
            row[2] = al.get(i).getAlamatSupplier();
            row[3] = al.get(i).getNoSupplier();
            modelS.addRow(row);        
        }
    }
        
    public void showItemS(int index){      
        fld_idsupplier.setText(getAllSupData().get(index).getIdSupplier()); 

    }
           
    
    
          private void auto_numberS(){
        try{
            db_conn conn = new db_conn(); 
            String sql = "SELECT MAX(right(id_supplier,2)) AS no FROM supplier";
             Statement st = conn.getConn().createStatement();
           ResultSet rs = st.executeQuery(sql);
           while (rs.next()){
               if(rs.first() == false){
                   fld_idsupplier.setText("SU001");
               
               }else{
                   rs.last();
                   int auto_id = rs.getInt(1) + 1;
                   String no = String.valueOf(auto_id);
                   int noLong = no.length();
                        for(int a = 0; a < 3 - noLong; a++){
                            no = "0" + no;
                        }
                   fld_idsupplier.setText("SU"+ no);     
               }
           }
        }catch(Exception e){
        
            JOptionPane.showMessageDialog(this,"error: \n" + e.toString(), "kelasahan", JOptionPane.WARNING_MESSAGE); 
        
        }
    
    }
  
  // barang       
        
      
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
         
        fld_kodebarang.setText(getAllBarangDataB().get(index).getkodebarang());
       
      

    }
    
    private void auto_numberB(){
        try{
            db_conn conn = new db_conn(); 
            String sql = "SELECT MAX(right(kode_barang,2)) AS no FROM barang";
             Statement st = conn.getConn().createStatement();
           ResultSet rs = st.executeQuery(sql);
           while (rs.next()){
               if(rs.first() == false){
                   fld_kodebarang.setText("B001");
               
               }else{
                   rs.last();
                   int auto_id = rs.getInt(1) + 1;
                   String no = String.valueOf(auto_id);
                   int noLong = no.length();
                        for(int a = 0; a < 3 - noLong; a++){
                            no = "0" + no;
                        }
                   fld_kodebarang.setText("B"+ no);     
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

        fld_idsupplier = new javax.swing.JTextField();
        fld_kodebarang = new javax.swing.JTextField();
        fld_jumlah = new javax.swing.JTextField();
        fld_searchBarang = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableBarang = new javax.swing.JTable();
        btn_update = new javax.swing.JButton();
        btn_delete = new javax.swing.JButton();
        btn_save = new javax.swing.JButton();
        btn_clear = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        id_karya3 = new javax.swing.JLabel();
        id_karya4 = new javax.swing.JLabel();
        id_karya5 = new javax.swing.JLabel();
        id_karya6 = new javax.swing.JLabel();
        id_karya7 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        fld_idbm = new javax.swing.JTextField();
        id_karya8 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablebarang = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablesupplier = new javax.swing.JTable();
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        fld_idsupplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fld_idsupplierActionPerformed(evt);
            }
        });
        fld_idsupplier.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                fld_idsupplierKeyReleased(evt);
            }
        });

        fld_kodebarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fld_kodebarangActionPerformed(evt);
            }
        });
        fld_kodebarang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                fld_kodebarangKeyReleased(evt);
            }
        });

        fld_jumlah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fld_jumlahActionPerformed(evt);
            }
        });

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

        tableBarang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "kode", "tanggal", "id supplier", "nama supplier", "alamat supplier", "kode barang", "nama barang", "jumlah"
            }
        ));
        tableBarang.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentAdded(java.awt.event.ContainerEvent evt) {
                tableBarangComponentAdded(evt);
            }
        });
        tableBarang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableBarangMouseClicked(evt);
            }
        });
        tableBarang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tableBarangKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tableBarang);

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

        jLabel1.setFont(new java.awt.Font("Verdana", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(60, 63, 65));
        jLabel1.setText("BARANG MASUK");

        id_karya3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        id_karya3.setText("TANGGAL");

        id_karya4.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        id_karya4.setText("ID SUPPLIER");

        id_karya5.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        id_karya5.setText("SEARCH");

        id_karya6.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        id_karya6.setText("KODE BARANG");

        id_karya7.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        id_karya7.setText("JUMLAH");

        jDateChooser1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jDateChooser1PropertyChange(evt);
            }
        });

        fld_idbm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fld_idbmActionPerformed(evt);
            }
        });

        id_karya8.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        id_karya8.setText("KODE BM");

        tablebarang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID", "NAMA", "Kategori"
            }
        ));
        tablebarang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablebarangMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tablebarang);

        tablesupplier.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID", "NAMA ", "ALAMAT"
            }
        ));
        tablesupplier.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablesupplierMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tablesupplier);

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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(87, 87, 87)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(id_karya4)
                                .addComponent(id_karya8, javax.swing.GroupLayout.Alignment.LEADING))
                            .addComponent(id_karya5))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btn_clear, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(btn_save, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btn_update, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btn_delete, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                    .addComponent(fld_idsupplier, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(fld_idbm)
                                    .addComponent(fld_searchBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(32, 32, 32)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(id_karya7)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(fld_jumlah, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(id_karya3)
                                                .addComponent(id_karya6))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(fld_kodebarang, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 749, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(408, 408, 408)
                        .addComponent(jLabel1)))
                .addContainerGap(1298, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 197, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(fld_kodebarang, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(id_karya6)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(fld_idbm, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(id_karya8)
                            .addComponent(id_karya3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(fld_idsupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(id_karya4))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(id_karya5)
                            .addComponent(fld_searchBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(fld_jumlah, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(id_karya7))
                        .addGap(212, 212, 212))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btn_save, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn_clear, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btn_delete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_update, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(99, 99, 99))
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void fld_idsupplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fld_idsupplierActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fld_idsupplierActionPerformed

    private void fld_kodebarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fld_kodebarangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fld_kodebarangActionPerformed

    private void fld_jumlahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fld_jumlahActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fld_jumlahActionPerformed

    private void fld_searchBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fld_searchBarangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fld_searchBarangActionPerformed

    private void tableBarangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableBarangMouseClicked
        // TODO add your handling code here:
                  
                    btn_save.setEnabled(false);
                    btn_update.setEnabled(true);
                    btn_delete.setEnabled(true);
             int ind = tableBarang.getSelectedRow();
        showItem(ind);
    }//GEN-LAST:event_tableBarangMouseClicked

    private void tableBarangKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tableBarangKeyReleased
        // TODO add your handling code here:

    }//GEN-LAST:event_tableBarangKeyReleased

    private void btn_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_updateActionPerformed

             if(

                                    jDateChooser1.equals("") ||
               fld_idsupplier.getText().toString().equals("") ||
               fld_kodebarang.getText().toString().equals("") ||         
               fld_jumlah.getText().toString().equals("") 
               )
                    
               
       {
           JOptionPane.showMessageDialog(null, "kolom harus di isi semua .....");
       }else{
           try{
                db_conn conn = new db_conn();
                String qry = "update barang_masuk set tanggal=?, kd_supplier=?, kd_barang=?, jumlah=? where id=?";
                PreparedStatement ps = conn.getConn().prepareStatement(qry);       
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String date1 = sdf.format(jDateChooser1.getDate());
                 ps.setString(1,date1); 
                ps.setString(2,fld_idsupplier.getText().toString());
                ps.setString(3,fld_kodebarang.getText().toString());
                ps.setInt(4,Integer.parseInt(fld_jumlah.getText().toString()));
                ps.setString(5,fld_idbm.getText().toString());
                int result = ps.executeUpdate();
                    fillTable();
                if(result >= 1){
                    btn_clear.setEnabled(true);
                    btn_save.setEnabled(true);
                    btn_update.setEnabled(false);
                    btn_delete.setEnabled(false);
                      JOptionPane.showMessageDialog(null,result + "update sukses .... "+ fld_idbm.getText().toString());
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
                              jDateChooser1.setDate(null);
               fld_idsupplier.setText("");
               fld_kodebarang.setText("");
               fld_jumlah.setText("");
       }
        
        
    }//GEN-LAST:event_btn_updateActionPerformed

    private void btn_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_deleteActionPerformed

         // TODO add your handling code here:
        int okay = JOptionPane.showConfirmDialog(null,"Apakah Yakin Menghapus data ini???", "Ijinkan",JOptionPane.YES_NO_CANCEL_OPTION);        
               if(
               fld_idbm.equals("") ||
               jDateChooser1.equals("") ||
               fld_idsupplier.getText().toString().equals("") ||
               fld_kodebarang.getText().toString().equals("") ||         
               fld_jumlah.getText().toString().equals("") 
                       
                       
               )
       {
           JOptionPane.showMessageDialog(null, "semua kolom harus terisi .....");
       }else{
               if(okay == 0){    
           try{
                db_conn conn = new db_conn();
                String qry = "delete from barang_masuk where id=?";
                PreparedStatement ps = conn.getConn().prepareStatement(qry);
                ps.setString(1,fld_idbm.getText().toString());
                int result = ps.executeUpdate();
                fillTable();
                if(result >= 1){
                                        btn_clear.setEnabled(true);
                    btn_save.setEnabled(true);
                    btn_update.setEnabled(false);
                    btn_delete.setEnabled(false);
                    JOptionPane.showMessageDialog(null,result + "data berhasil di hapus "+ fld_idbm.getText().toString());
                }else{
                    JOptionPane.showMessageDialog(null,"data deletion Failde Try again .......");        
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
                              jDateChooser1.setDate(null);
               fld_idsupplier.setText("");
               fld_kodebarang.setText("");
               fld_jumlah.setText("");             
       }
        
        
        
        
        
        
    }//GEN-LAST:event_btn_deleteActionPerformed

    private void btn_saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_saveActionPerformed
        
                   if(
               
            
               jDateChooser1.equals("") ||
               fld_idsupplier.getText().toString().equals("") ||
               fld_kodebarang.getText().toString().equals("") ||         
               fld_jumlah.getText().toString().equals("") 
               )
       {
           JOptionPane.showMessageDialog(null, "semua kolom harus terisi .....");
       }else{
           try{
               
                db_conn conn = new db_conn();
                String qry = "insert into barang_masuk(id,tanggal,kd_supplier,kd_barang,jumlah) values(?,?,?,?,?)";
                PreparedStatement ps = conn.getConn().prepareStatement(qry);
                ps.setString(1,fld_idbm.getText().toString());
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String date1 = sdf.format(jDateChooser1.getDate());
                ps.setString(2,date1); 
                ps.setString(3,fld_idsupplier.getText().toString());
                ps.setString(4,fld_kodebarang.getText().toString());
                ps.setInt(5,Integer.parseInt(fld_jumlah.getText().toString()));
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
               JOptionPane.showMessageDialog(null, "masalah pada dbms : "+ e);                       
           }
               //clear the filds    
               jDateChooser1.setDate(null);
               fld_idsupplier.setText("");
               fld_kodebarang.setText("");
               fld_jumlah.setText("");
        }
          
        // tombol simpan
     

    }//GEN-LAST:event_btn_saveActionPerformed

    private void btn_clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_clearActionPerformed
        // TODO add your handling code here:
barangMasuk bm = new barangMasuk();
bm.setVisible(true);
this.setVisible(false);


    }//GEN-LAST:event_btn_clearActionPerformed

    private void fld_idbmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fld_idbmActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fld_idbmActionPerformed

    private void tableBarangComponentAdded(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_tableBarangComponentAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_tableBarangComponentAdded

    private void jDateChooser1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jDateChooser1PropertyChange
        // TODO add your handling code here:
        jDateChooser1.setDateFormatString("yyyy-MM-dd");
    }//GEN-LAST:event_jDateChooser1PropertyChange

    private void fld_searchBarangKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fld_searchBarangKeyReleased
               // TODO add your handling code here:
                ArrayList<jbBarang_M> al = null;
        al = new ArrayList<jbBarang_M>();
        String cari = fld_searchBarang.getText().toString();
        
        try{
            db_conn conn = new db_conn();
            String sql = "select id,barang_masuk.tanggal,id_supplier,namaSupplier,alamatSupplier,kd_barang,"
                    + "nama_barang,jumlah from barang_masuk join barang on kode_barang = kd_barang join supplier on kd_supplier = id_supplier where kd_supplier = '%" + cari +"%'"
                          +"or namaSupplier like '%"+ cari +"%'"+"or alamatSupplier like '%"+ cari +"%'"
                          +"or nama_barang like '%"+ cari +"%'"+"or barang_masuk.tanggal like '%"+ cari +"%'"+"or supplier.id_supplier like '%"+ cari+"%'" ;
            Statement st = conn.getConn().createStatement();
            ResultSet rs = st.executeQuery(sql);
           
             jbBarang_M bean;
        while(rs.next()){
              bean = new jbBarang_M(
                          rs.getString(1),
                          rs.getString(2),
                          rs.getString(3),
                          rs.getString(4),
                          rs.getString(5),
                          rs.getString(6),
                          
                          rs.getString(7),                        
                          Integer.parseInt(rs.getString(8))
                        
              );                                                  
              al.add(bean);

     }
              
                   DefaultTableModel model = (DefaultTableModel)tableBarang.getModel();
        model.setRowCount(0);
        Object[] row = new Object[10];
        for(int i =0 ; i < al.size(); i++){
              row[0] = al.get(i).getId();
            row[1] = al.get(i).getTanggal();
            row[2] = al.get(i).getIdSupplier();
            row[3] = al.get(i).getNama_S();
            row[4] = al.get(i).getAlamat_S();
            row[5] = al.get(i).getKodeBarang();
            row[6] = al.get(i).getNama_B();
            row[7] = al.get(i).getJumlah();
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




        // TODO add your handling code here:
    }//GEN-LAST:event_fld_searchBarangKeyReleased

    private void fld_idsupplierKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fld_idsupplierKeyReleased
        // TODO add your handling code here:
         ArrayList<jBsupplier> al = null;
        al = new ArrayList<jBsupplier>();
        String val = fld_idsupplier.getText().toString();
        
        try{
            db_conn conn = new db_conn();
            String qry = "SELECT * FROM supplier WHERE id_supplier like '%" + val +"%'"+"or namaSupplier like '%"+ val +"%'"+"or alamatSupplier like '%"+ val +"%'"+"or noSupplier like '%"+ val +"%'" ;
            Statement st = conn.getConn().createStatement();
            ResultSet rs = st.executeQuery(qry);
            jBsupplier teacher;
      jBsupplier beanS;
        while(rs.next()){
              beanS = new jBsupplier(
                          rs.getString(1),
                          rs.getString(2),
                          rs.getString(3),
                          rs.getString(4));                                                   
              al.add(beanS);

     }
              
                   DefaultTableModel modelS = (DefaultTableModel)tablesupplier.getModel();
        modelS.setRowCount(0);
        Object[] row = new Object[6];
        for(int i =0 ; i < al.size(); i++){
            row[0] = al.get(i).getIdSupplier();       
            row[1] = al.get(i).getNamaSupplier();
            row[2] = al.get(i).getAlamatSupplier();
            row[3] = al.get(i).getNoSupplier();
            modelS.addRow(row);             
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




    }//GEN-LAST:event_fld_idsupplierKeyReleased

    private void tablesupplierMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablesupplierMouseClicked
        // TODO add your handling code here:
        
            int ind = tablesupplier.getSelectedRow();
        showItemS(ind);
    }//GEN-LAST:event_tablesupplierMouseClicked

    private void tablebarangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablebarangMouseClicked
        // TODO add your handling code here:
             int ind = tablebarang.getSelectedRow();
        showItemB(ind);
    }//GEN-LAST:event_tablebarangMouseClicked

    private void fld_kodebarangKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fld_kodebarangKeyReleased
        // TODO add your handling code here:
                 ArrayList<jbBarang> al = null;
        al = new ArrayList<jbBarang>();
        String cari = fld_kodebarang.getText().toString();
        
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
        
        
      
    }//GEN-LAST:event_fld_kodebarangKeyReleased

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
        this.setVisible(false);    }//GEN-LAST:event_permintaan2ActionPerformed

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
            java.util.logging.Logger.getLogger(barangMasuk.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(barangMasuk.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(barangMasuk.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(barangMasuk.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new barangMasuk().setVisible(true);
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
    private javax.swing.JTextField fld_idbm;
    private javax.swing.JTextField fld_idsupplier;
    private javax.swing.JTextField fld_jumlah;
    private javax.swing.JTextField fld_kodebarang;
    private javax.swing.JTextField fld_searchBarang;
    private javax.swing.JLabel id_karya3;
    private javax.swing.JLabel id_karya4;
    private javax.swing.JLabel id_karya5;
    private javax.swing.JLabel id_karya6;
    private javax.swing.JLabel id_karya7;
    private javax.swing.JLabel id_karya8;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JButton permintaan;
    private javax.swing.JButton permintaan2;
    private javax.swing.JTable tableBarang;
    private javax.swing.JTable tablebarang;
    private javax.swing.JTable tablesupplier;
    // End of variables declaration//GEN-END:variables
}
