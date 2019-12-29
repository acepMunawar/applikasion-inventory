/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package master;

import connection.db_conn;
import java.awt.Color;
import static java.awt.Color.white;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import master.karyawan;
import model.jbBarang;
import model.jbKategori;
import userView.home;
/**
 *
 * @author acepmunawar
 */
public class barang extends javax.swing.JFrame {

    /**
     * Creates new form barang
     */
    public barang() {
        initComponents();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        fillTable();
        auto_number();
        fillTableK();
        fld_kdBarang.setEditable(false);        
        updateSupplayer.setEnabled(false);
        btn_deletekary.setEnabled(false);
    }
    
    
      public ArrayList<jbBarang> getAllBarangData(){
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

public void fillTable(){
        ArrayList<jbBarang> al = getAllBarangData();
        DefaultTableModel model = (DefaultTableModel)tableBarang.getModel();
        model.setRowCount(0);
        auto_number();
        Object[] row = new Object[10];
        for(int i =0 ; i < al.size(); i++){
            row[0] = al.get(i).getkodebarang();
            row[1] = al.get(i).getTanggal();
            row[2] = al.get(i).getnamabarang();
            row[3] = al.get(i).getidkategori();
            row[4] = al.get(i).getnamakategori();
            row[5] = al.get(i).getnorak();
            row[6] = al.get(i).getstockbarang();
            model.addRow(row);        
        }
    }
     
    public void showItem(int index){
         
        fld_kdBarang.setText(getAllBarangData().get(index).getkodebarang());
       
                    try{
            java.util.Date date = null;
            date = new SimpleDateFormat("yyyy-MM-dd").parse((String)getAllBarangData().get(index).getTanggal());
            jDateChooser1.setDate(date);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Error show item to fields teacher()" + e);
        }       
        fld_kategori.setText(getAllBarangData().get(index).getidkategori());
        fld_namaBarang.setText(getAllBarangData().get(index).getnamabarang());
        fld_stock.setText(Integer.toString(getAllBarangData().get(index).getstockbarang()));
      
    }
    
    private void auto_number(){
        try{
            db_conn conn = new db_conn(); 
            String sql = "SELECT MAX(right(kode_barang,2)) AS no FROM barang";
             Statement st = conn.getConn().createStatement();
           ResultSet rs = st.executeQuery(sql);
           while (rs.next()){
               if(rs.first() == false){
                   fld_kdBarang.setText("B001");
               
               }else{
                   rs.last();
                   int auto_id = rs.getInt(1) + 1;
                   String no = String.valueOf(auto_id);
                   int noLong = no.length();
                        for(int a = 0; a < 3 - noLong; a++){
                            no = "0" + no;
                        }
                   fld_kdBarang.setText("B"+ no);     
               }
           }
        }catch(Exception e){
        
            JOptionPane.showMessageDialog(this,"error: \n" + e.toString(), "kelasahan", JOptionPane.WARNING_MESSAGE); 
        
        }
    
    }
    
    
      
      public ArrayList<jbKategori> getAllkategoriK(){
    ArrayList<jbKategori> al = null;
    al = new ArrayList<jbKategori>();
    try{
    db_conn conn = new db_conn();     
           String qry = "Select * from kategori";
           Statement st = conn.getConn().createStatement();
           ResultSet rs = st.executeQuery(qry);
    jbKategori bean;
        while(rs.next()){
              bean = new jbKategori(
                                           rs.getString("id_kategori"),
                                           rs.getString("nama_kategori"),
                          Integer.parseInt(rs.getString("no_rak"))
              );                                                   
              al.add(bean); 
     }
    }catch(Exception e){
    
    System.out.print("sory your wrong");   
    }
    return al;
}

public void fillTableK(){
        ArrayList<jbKategori> al = getAllkategoriK();
        DefaultTableModel model = (DefaultTableModel)tablekategori.getModel();
        model.setRowCount(0);
        auto_number();
        Object[] row = new Object[10];
        for(int i =0 ; i < al.size(); i++){
            row[0] = al.get(i).getidkategori();       
            row[1] = al.get(i).getnamakategori();
            row[2] = al.get(i).getRak();
            model.addRow(row);
            
        }
    }
     
    public void showItemK(int index){
        fld_kategori.setText(getAllkategoriK().get(index).getidkategori());
       
    }
    
    
      public void noAcc(){            
           try{
                db_conn conn = new db_conn();                
                String qry = "delete from permintaan_barang where id=?";
                PreparedStatement ps = conn.getConn().prepareStatement(qry);
                ps.setInt(1,Integer.parseInt(fld_kdBarang.getText().toString()));
                int result = ps.executeUpdate();
                fillTable();                            
           }catch(Exception e){
               JOptionPane.showMessageDialog(null, "masalah pada dbms : "+ e);                         
           }
        }
        
    
    public void clear(){
                 fld_kdBarang.setText("");
          fld_namaBarang.setText("");    
          fld_kategori.setText("");
          fld_stock.setText("");
    
    
    }
      
      
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnKaryawan = new javax.swing.JButton();
        btnSupplier = new javax.swing.JButton();
        btnBarang = new javax.swing.JButton();
        btnKategori = new javax.swing.JButton();
        btnKategori1 = new javax.swing.JButton();
        btnLogOut = new javax.swing.JButton();
        btnHome = new javax.swing.JButton();
        fld_kdBarang = new javax.swing.JTextField();
        fld_namaBarang = new javax.swing.JTextField();
        fld_stock = new javax.swing.JTextField();
        fld_kategori = new javax.swing.JTextField();
        fld_searchBarang = new javax.swing.JTextField();
        id_karya2 = new javax.swing.JLabel();
        id_karya6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableBarang = new javax.swing.JTable();
        btn_deletekary = new javax.swing.JButton();
        updateSupplayer = new javax.swing.JButton();
        jButton5_Save = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablekategori = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(0, 153, 204));

        jLabel2.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("PT GARMAK MOTOR");

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/master/gm2.jpg"))); // NOI18N
        jLabel3.setText("jLabel3");

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
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addComponent(jLabel2))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(108, 108, 108)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(64, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(71, 71, 71)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnKaryawan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnSupplier, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnLogOut, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnBarang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnKategori, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(btnKategori1, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnHome, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(71, Short.MAX_VALUE)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(jLabel2)
                .addGap(16, 16, 16)
                .addComponent(jLabel3)
                .addContainerGap(791, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(234, 234, 234)
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
                    .addContainerGap(235, Short.MAX_VALUE)))
        );

        fld_kdBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fld_kdBarangActionPerformed(evt);
            }
        });

        fld_namaBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fld_namaBarangActionPerformed(evt);
            }
        });

        fld_stock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fld_stockActionPerformed(evt);
            }
        });

        fld_kategori.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fld_kategoriActionPerformed(evt);
            }
        });
        fld_kategori.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                fld_kategoriKeyReleased(evt);
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

        id_karya2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        id_karya2.setText("KODE ");

        id_karya6.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        id_karya6.setText("STOCK");

        tableBarang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "kode barang", "tanggal", "nama barang", "id kategori", "kategori", "nomor rak", "stock"
            }
        ));
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

        btn_deletekary.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Delete-32.png"))); // NOI18N
        btn_deletekary.setText("Delete");
        btn_deletekary.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_deletekaryActionPerformed(evt);
            }
        });

        updateSupplayer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Modify.png"))); // NOI18N
        updateSupplayer.setText("Update");
        updateSupplayer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateSupplayerActionPerformed(evt);
            }
        });

        jButton5_Save.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Add.png"))); // NOI18N
        jButton5_Save.setText("Save");
        jButton5_Save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5_SaveActionPerformed(evt);
            }
        });

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Refresh-32.png"))); // NOI18N
        jButton3.setText("Clear");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Verdana", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(60, 63, 65));
        jLabel1.setText("DATA BARANG");

        jDateChooser1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jDateChooser1PropertyChange(evt);
            }
        });

        tablekategori.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "KODE", "NAMA", "RAK"
            }
        ));
        tablekategori.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablekategoriMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tablekategori);

        jLabel4.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel4.setText("SEARCH");

        jLabel5.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel5.setText("NAMA");

        jLabel6.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel6.setText("KODE KATEGORI");

        jLabel7.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel7.setText("TANGGAL");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(95, 95, 95)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel5)
                                            .addComponent(id_karya2))
                                        .addGap(50, 50, 50)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(fld_namaBarang)
                                            .addComponent(fld_kdBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel7)
                                        .addGroup(layout.createSequentialGroup()
                                            .addGap(96, 96, 96)
                                            .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                            .addComponent(id_karya6)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(fld_stock, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(29, 29, 29)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(fld_kategori, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel4)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(98, 98, 98)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 724, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(fld_searchBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 722, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(134, 134, 134)
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton5_Save, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(160, 160, 160)
                                .addComponent(updateSupplayer, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btn_deletekary, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(402, 402, 402)
                        .addComponent(jLabel1)))
                .addContainerGap(508, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel1)
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fld_kdBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fld_kategori, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(id_karya2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(fld_namaBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(9, 9, 9)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(fld_stock, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(id_karya6))))
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(fld_searchBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton5_Save, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_deletekary, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(updateSupplayer, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void fld_kdBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fld_kdBarangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fld_kdBarangActionPerformed

    private void fld_namaBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fld_namaBarangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fld_namaBarangActionPerformed

    private void fld_stockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fld_stockActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fld_stockActionPerformed

    private void fld_kategoriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fld_kategoriActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fld_kategoriActionPerformed

    private void fld_searchBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fld_searchBarangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fld_searchBarangActionPerformed

    private void tableBarangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableBarangMouseClicked
        // TODO add your handling code here:

        jButton5_Save.setEnabled(false);
         updateSupplayer.setEnabled(true);
        btn_deletekary.setEnabled(true);
        
                int ind = tableBarang.getSelectedRow();
        showItem(ind);

    }//GEN-LAST:event_tableBarangMouseClicked

    private void btn_deletekaryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_deletekaryActionPerformed
         int okay = JOptionPane.showConfirmDialog(null,"Apakah Yakin Menghapus data ini???", "Ijinkan",JOptionPane.YES_NO_CANCEL_OPTION);        
               if(
               fld_kdBarang.getText().toString().equals("") ||
               fld_namaBarang.getText().toString().equals("") ||
               fld_stock.getText().toString().equals("") ||
               fld_kategori.getText().toString().equals("") 
               )
       {
           JOptionPane.showMessageDialog(null, "kolom ada yang belum di isi .....");
       }else{
               if(okay == 0){    
           try{
                db_conn conn = new db_conn();
                String qry = "delete from barang where kode_barang=?";
                PreparedStatement ps = conn.getConn().prepareStatement(qry);
                ps.setString(1,fld_kdBarang.getText().toString());
                int result = ps.executeUpdate();
                fillTable();
                if(result >= 1){
                    jButton3.setEnabled(true);
        jButton5_Save.setEnabled(true);
         updateSupplayer.setEnabled(false);
        btn_deletekary.setEnabled(false);
        
                    JOptionPane.showMessageDialog(null,result + "data berhasil di hapus "+ fld_kdBarang.getText().toString());
                    
                }else{
                    JOptionPane.showMessageDialog(null,"data gagal di hapus coba lagi .......");        
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
               fld_namaBarang.setText("");
               fld_kategori.setText("");
               fld_stock.setText("");              
       } 
    }//GEN-LAST:event_btn_deletekaryActionPerformed

    private void updateSupplayerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateSupplayerActionPerformed
        // TODO add your handling code here:
          if(
         
               fld_namaBarang.getText().toString().equals("") ||
               fld_kategori.getText().toString().equals("") ||
               fld_stock.getText().toString().equals("") 
               )
       {
           JOptionPane.showMessageDialog(null, "all fields are compulsory .....");
       }else{
           try{
                db_conn conn = new db_conn();
                String qry = "update barang set tanggal=?, nama_barang=?, stock=?, id_kat=? where kode_barang=?";
                PreparedStatement ps = conn.getConn().prepareStatement(qry);
                 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String date1 = sdf.format(jDateChooser1.getDate());
                ps.setString(1,date1.toString());
                ps.setString(2,fld_namaBarang.getText().toString());
                ps.setInt(3,Integer.parseInt(fld_stock.getText().toString()));
                ps.setString(4,fld_kategori.getText().toString());
                ps.setString(5,fld_kdBarang.getText().toString());
                int result = ps.executeUpdate();
                    fillTable();
                if(result >= 1){
                      JOptionPane.showMessageDialog(null,result + "update berhasil .... "+ fld_kdBarang.getText().toString());
                      jButton3.setEnabled(true);
                      jButton5_Save.setEnabled(true);
                      updateSupplayer.setEnabled(false);
                      btn_deletekary.setEnabled(false);
        
                }else{
                    JOptionPane.showMessageDialog(null,"update gagal coba lagi .......");        
                 if (ps!=null){
                    ps.close();
                }                    
                }if (conn != null){
                    conn.getConn().close();
                }        
           }catch(Exception e){
               
               noAcc();
           }
               //clear the filds
               jDateChooser1.setDate(null);
               fld_namaBarang.setText("");
               fld_kategori.setText("");
               fld_stock.setText("");
       }
       
    }//GEN-LAST:event_updateSupplayerActionPerformed

    private void jButton5_SaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5_SaveActionPerformed
            
         // tombol simpan
            if(
               jDateChooser1.equals("") || 
               fld_namaBarang.getText().toString().equals("") ||
               fld_kategori.getText().toString().equals("") ||     
               fld_stock.getText().toString().equals("")     
               )
       {
           JOptionPane.showMessageDialog(null, "all fields are compulsory .....");
       }else{
           try{
               
                db_conn conn = new db_conn();
                String qry = "insert into barang(kode_barang,tanggal,nama_barang,stock,id_kat) values(?,?,?,?,?)";
                PreparedStatement ps = conn.getConn().prepareStatement(qry);
                ps.setString(1,fld_kdBarang.getText().toString());
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String date1 = sdf.format(jDateChooser1.getDate());
                ps.setString(2,date1.toString());
                ps.setString(3,fld_namaBarang.getText().toString()); 
                ps.setString(4,fld_stock.getText().toString());                
                ps.setString(5,fld_kategori.getText().toString());
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
                  //clear the filds
               jDateChooser1.setDate(null);
               fld_namaBarang.setText("");
               fld_kategori.setText("");
               fld_stock.setText("");
        }    
       
    }//GEN-LAST:event_jButton5_SaveActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:

          barang br = new barang();
          br.setVisible(true);
          this.setVisible(false);
          
       
    }//GEN-LAST:event_jButton3ActionPerformed

    private void tableBarangKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tableBarangKeyReleased
        // TODO add your handling code here:
      
    }//GEN-LAST:event_tableBarangKeyReleased

    private void fld_searchBarangKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fld_searchBarangKeyReleased
        // TODO add your handling code here:
                ArrayList<jbBarang> al = null;
        al = new ArrayList<jbBarang>();
        String cari = fld_searchBarang.getText().toString();
        
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
              
                   DefaultTableModel model = (DefaultTableModel)tableBarang.getModel();
        model.setRowCount(0);
        Object[] row = new Object[8];
        for(int i =0 ; i < al.size(); i++){
            row[0] = al.get(i).getkodebarang();
            row[1] = al.get(i).getTanggal();
            row[2] = al.get(i).getnamabarang();
            row[3] = al.get(i).getidkategori();
            row[4] = al.get(i).getnamakategori();
            row[5] = al.get(i).getnorak();
            row[6] = al.get(i).getstockbarang();
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
        
        
    }//GEN-LAST:event_fld_searchBarangKeyReleased

    private void jDateChooser1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jDateChooser1PropertyChange
        // TODO add your handling code here:
        jDateChooser1.setDateFormatString("yyyy-MM-dd");
    }//GEN-LAST:event_jDateChooser1PropertyChange

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
        login.login logout = new login.login();
        logout.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnLogOutActionPerformed

    private void btnHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHomeActionPerformed
        // btnBmasuk
        home bm = new home();
        bm.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnHomeActionPerformed

    private void fld_kategoriKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fld_kategoriKeyReleased
        // TODO add your handling code here:
        
            ArrayList<jbKategori> al = null;
        al = new ArrayList<jbKategori>();
        String val = fld_kategori.getText().toString();
        
        try{
            db_conn conn = new db_conn();
            String qry = "SELECT * FROM kategori WHERE id_kategori like '%" + val +"%'"+"or nama_kategori like '%"+ val +"%'"+"or no_rak like '%"+ val +"%'" ;
            Statement st = conn.getConn().createStatement();
            ResultSet rs = st.executeQuery(qry);
            jbKategori teacher;
             jbKategori bean;
        while(rs.next()){
              bean = new jbKategori(
                          rs.getString(1),
                          rs.getString(2),
                          Integer.parseInt(rs.getString(3)));                                                   
              al.add(bean);
     }
              
                   DefaultTableModel model = (DefaultTableModel)tablekategori.getModel();
        model.setRowCount(0);
        Object[] row = new Object[6];
        for(int i =0 ; i < al.size(); i++){
            row[0] = al.get(i).getidkategori();       
            row[1] = al.get(i).getnamakategori();
            row[2] = al.get(i).getRak();
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
    }//GEN-LAST:event_fld_kategoriKeyReleased

    private void tablekategoriMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablekategoriMouseClicked
        
       
        
          int ind = tablekategori.getSelectedRow();
        showItemK(ind);
       
        








        // TODO add your handling code here:
    }//GEN-LAST:event_tablekategoriMouseClicked

    
  
    
    
    
    
    
    
    
    
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
            java.util.logging.Logger.getLogger(barang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(barang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(barang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(barang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new barang().setVisible(true);
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
    private javax.swing.JButton btn_deletekary;
    private javax.swing.JTextField fld_kategori;
    private javax.swing.JTextField fld_kdBarang;
    private javax.swing.JTextField fld_namaBarang;
    private javax.swing.JTextField fld_searchBarang;
    private javax.swing.JTextField fld_stock;
    private javax.swing.JLabel id_karya2;
    private javax.swing.JLabel id_karya6;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5_Save;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tableBarang;
    private javax.swing.JTable tablekategori;
    private javax.swing.JButton updateSupplayer;
    // End of variables declaration//GEN-END:variables
}
