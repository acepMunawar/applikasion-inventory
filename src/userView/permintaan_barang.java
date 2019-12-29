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
import model.jbBarang;
import model.jbKategori;
import model.jbPermintaan;
/**
 *
 * @author acepmunawar
 */
public class permintaan_barang extends javax.swing.JFrame {

    /**
     * Creates new form permintaan_barang
     */
    public permintaan_barang() {
        initComponents();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        fillTable();
        fillTableKat();
        fillTableK();
        auto_number();
        fld_idpb.setEditable(false);
       
        btn_acc.setEnabled(false);
        btn_tidakijinkan.setEnabled(false);
    }
    
     public ArrayList<jbPermintaan> getAllpermintaan(){
    ArrayList<jbPermintaan> al = null;
    al = new ArrayList<jbPermintaan>();
    try{
    db_conn conn = new db_conn();     
           String qry = "Select * from permintaan_barang";
           Statement st = conn.getConn().createStatement();
           ResultSet rs = st.executeQuery(qry);
    jbPermintaan bean;
        while(rs.next()){
             bean = new jbPermintaan(
                        
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
    }catch(Exception e){
        System.out.print("sory your wrong");   
    }
    return al;
}

     
public void fillTable(){
        ArrayList<jbPermintaan> al = getAllpermintaan();
        DefaultTableModel model = (DefaultTableModel)tableBarang.getModel();
        model.setRowCount(0);
        Object[] row = new Object[8];
        for(int i =0 ; i < al.size(); i++){
            row[0] = al.get(i).getId();
            row[1] = al.get(i).getTanggal();
            row[2] = al.get(i).getnamabarang();
            row[3] = al.get(i).getidkategori(); 
            row[4] = al.get(i).getidkaryawan();
            row[5] = al.get(i).getketerangan();            
            row[6] = al.get(i).getjumlahbarang();           
            model.addRow(row);        
        }
    }     
     
    public void showItem(int index){
        fld_idpb.setText(getAllpermintaan().get(index).getId());    
        try{
            java.util.Date date = null;
            date = new SimpleDateFormat("yyyy-MM-dd").parse((String)getAllpermintaan().get(index).getTanggal());
            jDateChooser1.setDate(date);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Error show item to fields teacher()" + e);
        }   
        fld_namaBarang.setText(getAllpermintaan().get(index).getnamabarang());
        fld_kdkategori.setText(getAllpermintaan().get(index).getidkategori());
        fld_kdkaryawan.setText(getAllpermintaan().get(index).getidkategori());
        fld_jumlah.setText(Integer.toString(getAllpermintaan().get(index).getjumlahbarang()));
        keterangan.setText(getAllpermintaan().get(index).getketerangan());     
    }
               
      public void noAcc(){            
           try{
                db_conn conn = new db_conn();                
                String qry = "delete from permintaan_barang where id=?";
                PreparedStatement ps = conn.getConn().prepareStatement(qry);
             
                ps.setString(1,fld_idpb.getText().toString());
                int result = ps.executeUpdate();
                fillTable();                            
           }catch(Exception e){
               JOptionPane.showMessageDialog(null, "masalah pada hapus data : "+ e);                         
           }
        }
        
        
      
            
     private void auto_number(){
        try{
            db_conn conn = new db_conn(); 
            String sql = "SELECT MAX(right(id,2)) AS no FROM permintaan_barang";
             Statement st = conn.getConn().createStatement();
           ResultSet rs = st.executeQuery(sql);
           while (rs.next()){
               if(rs.first() == false){
                   fld_idpb.setText("BN001");              
               }else{
                   rs.last();
                   int auto_id = rs.getInt(1) + 1;
                   String no = String.valueOf(auto_id);
                   int noLong = no.length();
                        for(int a = 0; a < 3 - noLong; a++){
                            no = "0" + no;
                        }
                   fld_idpb.setText("BN"+ no);     
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

public void fillTableKat(){
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
     
    public void showItemKat(int index){
        fld_kdkategori.setText(getAllkategoriK().get(index).getidkategori());
       
    }
    
    
    
    
    
    
    
    
      
      
      
      
      
      
      
        
        
        
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        id_karya1 = new javax.swing.JLabel();
        btn_tidakijinkan = new javax.swing.JButton();
        btn_acc = new javax.swing.JButton();
        btn_clear = new javax.swing.JButton();
        id_karya3 = new javax.swing.JLabel();
        btn_save = new javax.swing.JButton();
        id_karya7 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        keterangan = new javax.swing.JTextArea();
        ket = new javax.swing.JLabel();
        fld_idpb = new javax.swing.JTextField();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        fld_namaBarang = new javax.swing.JTextField();
        fld_jumlah = new javax.swing.JTextField();
        id_karya9 = new javax.swing.JLabel();
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
        jScrollPane3 = new javax.swing.JScrollPane();
        tablekategori = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        fld_kdkaryawan = new javax.swing.JTextField();
        id_karya4 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tablekar = new javax.swing.JTable();
        fld_kdkategori = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableBarang = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Verdana", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(60, 63, 65));
        jLabel1.setText("PERMINTAAN BARANG");

        id_karya1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        id_karya1.setText("NAMA BARANG");

        btn_tidakijinkan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Delete-32.png"))); // NOI18N
        btn_tidakijinkan.setText("TIDAK DIIJINKAN");
        btn_tidakijinkan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tidakijinkanActionPerformed(evt);
            }
        });

        btn_acc.setText("IJINKAN");
        btn_acc.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
                btn_accAncestorRemoved(evt);
            }
        });
        btn_acc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_accMousePressed(evt);
            }
        });
        btn_acc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_accActionPerformed(evt);
            }
        });

        btn_clear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Refresh-32.png"))); // NOI18N
        btn_clear.setText("Clear");
        btn_clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_clearActionPerformed(evt);
            }
        });

        id_karya3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        id_karya3.setText("KODE ");

        btn_save.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Add.png"))); // NOI18N
        btn_save.setText("SAVE");
        btn_save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_saveActionPerformed(evt);
            }
        });

        id_karya7.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        id_karya7.setText("TANGGAL");

        keterangan.setColumns(20);
        keterangan.setRows(5);
        jScrollPane2.setViewportView(keterangan);

        ket.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        ket.setText("KETERANGAN");

        fld_idpb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fld_idpbActionPerformed(evt);
            }
        });

        jDateChooser1.setIcon(null);

        id_karya9.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        id_karya9.setText("Jumlah");

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
        jScrollPane3.setViewportView(tablekategori);

        jLabel6.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel6.setText("KODE KATEGORI");

        fld_kdkaryawan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                fld_kdkaryawanKeyReleased(evt);
            }
        });

        id_karya4.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        id_karya4.setText("KODE KARYAWAN");

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
        jScrollPane4.setViewportView(tablekar);

        fld_kdkategori.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fld_kdkategoriActionPerformed(evt);
            }
        });
        fld_kdkategori.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                fld_kdkategoriKeyReleased(evt);
            }
        });

        tableBarang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "id", "tanggal", "nama", "id kategori", "id karyawan", "keterangan", "jumlah"
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(347, 347, 347)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(ket)
                                .addGap(50, 50, 50)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 771, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 774, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(btn_clear, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(28, 28, 28)
                                            .addComponent(btn_save, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(27, 27, 27)
                                            .addComponent(btn_acc, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(btn_tidakijinkan)))))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(id_karya3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(fld_idpb, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(id_karya4)
                                        .addGap(21, 21, 21)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(fld_kdkaryawan, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(id_karya1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(fld_namaBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(54, 54, 54)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel6)
                                            .addComponent(id_karya9))
                                        .addGap(30, 30, 30)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(fld_jumlah, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(fld_kdkategori, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(id_karya7)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                .addContainerGap(659, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(fld_idpb, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(id_karya3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(fld_kdkaryawan, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(id_karya4))
                        .addGap(4, 4, 4)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(fld_kdkategori, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jDateChooser1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(id_karya7, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(fld_jumlah, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(id_karya9)
                            .addComponent(fld_namaBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(id_karya1))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ket, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_clear, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_save, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_acc, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_tidakijinkan, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 317, Short.MAX_VALUE))
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_tidakijinkanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tidakijinkanActionPerformed
        // TODO add your handling code here:
              if(
                  
               fld_namaBarang.getText().toString().equals("") ||
               fld_jumlah.getText().toString().equals("") ||
               fld_kdkategori.getText().toString().equals("") 
               )
       {
           JOptionPane.showMessageDialog(null, "kolom tidak boleh kosong .....");
       }else{
                
           try{
                db_conn conn = new db_conn();
                String qry = "delete from permintaan_barang where id=?";
                PreparedStatement ps = conn.getConn().prepareStatement(qry);
                ps.setString(1,fld_idpb.getText().toString());
                int result = ps.executeUpdate();
                fillTable();
                if(result >= 1){
                   btn_clear.setEnabled(true);
                   btn_save.setEnabled(true);
                    btn_acc.setEnabled(false);
        btn_tidakijinkan.setEnabled(false);
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
               
               //clear the filds

                              jDateChooser1.setDate(null);
                  fld_namaBarang.setText("");
               fld_kdkategori.setText("");
               fld_kdkaryawan.setText("");
               fld_jumlah.setText("");
               keterangan.setText("");
       }   

    }//GEN-LAST:event_btn_tidakijinkanActionPerformed

    private void btn_accActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_accActionPerformed
                // terima permintaan        
        
         // tombol simpan
            if(
               jDateChooser1.equals("") || 
               fld_namaBarang.getText().toString().equals("") ||
               fld_kdkategori.getText().toString().equals("") ||     
               fld_jumlah.getText().toString().equals("")     
               )
       {
           JOptionPane.showMessageDialog(null, "kolom tidak boleh kosong .....");
       }else{
           try{
               
                db_conn conn = new db_conn();
                String qry = "insert into barang(kode_barang,tanggal,nama_barang,stock,id_kat) values(?,?,?,?,?)";
                PreparedStatement ps = conn.getConn().prepareStatement(qry);
                ps.setString(1,fld_idpb.getText().toString());
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String date1 = sdf.format(jDateChooser1.getDate());
                ps.setString(2,date1.toString());
                ps.setString(3,fld_namaBarang.getText().toString()); 
                ps.setString(4,fld_jumlah.getText().toString());                
                ps.setString(5,fld_kdkategori.getText().toString());
               int result = ps.executeUpdate();
               fillTable();
                if(result >= 1){
                               noAcc();
                                                  btn_clear.setEnabled(true);
                   btn_save.setEnabled(true);
                    btn_acc.setEnabled(false);
        btn_tidakijinkan.setEnabled(false);
                    JOptionPane.showMessageDialog(null,result + "data berhasil masuk ke data barang .... ");
                   
                }else{
                    JOptionPane.showMessageDialog(null, result + "gagal menyimpan .......");
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
               fld_kdkategori.setText("");
               fld_kdkaryawan.setText("");
               fld_jumlah.setText("");
               keterangan.setText("");
        }    

    }//GEN-LAST:event_btn_accActionPerformed

    private void btn_clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_clearActionPerformed
       permintaan_barang pb = new permintaan_barang();
       pb.setVisible(true);
       this.setVisible(false);

    }//GEN-LAST:event_btn_clearActionPerformed

    private void btn_saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_saveActionPerformed
            // TODO add your handling code here:       
        if(
                 
               fld_namaBarang.getText().toString().equals("") ||
               fld_kdkategori.getText().toString().equals("") ||
               fld_kdkaryawan.getText().toString().equals("") || 
               fld_jumlah.getText().toString().equals("") ||
               keterangan.getText().toString().equals("")  
               )
       {
           JOptionPane.showMessageDialog(null, "kolom harus terisi semua .....");
       }else{
           try{
               
                db_conn conn = new db_conn();
                String qry = "insert into permintaan_barang(id,tanggal,nama_barang,id_kategori,id_karyawan,jumlah,keterangan) "
                              + "values(?,?,?,?,?,?,?)";
                PreparedStatement ps = conn.getConn().prepareStatement(qry);
                ps.setString(1,fld_idpb.getText().toString());
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String date1 = sdf.format(jDateChooser1.getDate());
                ps.setString(2,date1.toString()); 
                ps.setString(3,fld_namaBarang.getText().toString());
                ps.setString(4,fld_kdkategori.getText().toString());
                ps.setString(5,fld_kdkaryawan.getText().toString());                
                ps.setInt(6,Integer.parseInt(fld_jumlah.getText().toString()));
                ps.setString(7,keterangan.getText().toString());
               int result = ps.executeUpdate();
               fillTable();
                if(result >= 1){
                  
                    JOptionPane.showMessageDialog(null,result + "data berhasil disimpan .... ");
                      permintaan_barang pb = new permintaan_barang();
                    pb.setVisible(true);
                    this.setVisible(false);
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
                  fld_namaBarang.setText("");
               fld_kdkategori.setText("");
               fld_kdkaryawan.setText("");
               fld_jumlah.setText("");
               keterangan.setText("");
        }










        // TODO add your handling code here:
    }//GEN-LAST:event_btn_saveActionPerformed

    private void btn_accAncestorRemoved(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_btn_accAncestorRemoved
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_accAncestorRemoved

    private void fld_idpbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fld_idpbActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fld_idpbActionPerformed

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
        this.setVisible(false);
    }//GEN-LAST:event_permintaan2ActionPerformed

    private void tablekategoriMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablekategoriMouseClicked

        int ind = tablekategori.getSelectedRow();
        showItemKat(ind);

        // TODO add your handling code here:
    }//GEN-LAST:event_tablekategoriMouseClicked

    private void tablekarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablekarMouseClicked
        // TODO add your handling code here:
        int ind = tablekar.getSelectedRow();
        showItemK(ind);
    }//GEN-LAST:event_tablekarMouseClicked

    private void fld_kdkategoriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fld_kdkategoriActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fld_kdkategoriActionPerformed

    private void fld_kdkategoriKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fld_kdkategoriKeyReleased
        // TODO add your handling code here:

        ArrayList<jbKategori> al = null;
        al = new ArrayList<jbKategori>();
        String val = fld_kdkategori.getText().toString();

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
    }//GEN-LAST:event_fld_kdkategoriKeyReleased

    private void tableBarangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableBarangMouseClicked
        // TODO add your handling code here:
                         
                   btn_save.setEnabled(false);
                    btn_acc.setEnabled(true);
        btn_tidakijinkan.setEnabled(true);
        int ind = tableBarang.getSelectedRow();
        showItem(ind);
    }//GEN-LAST:event_tableBarangMouseClicked

    private void tableBarangKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tableBarangKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tableBarangKeyReleased

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

    private void btn_accMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_accMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_accMousePressed

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
            java.util.logging.Logger.getLogger(permintaan_barang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(permintaan_barang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(permintaan_barang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(permintaan_barang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new permintaan_barang().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton barangKeluar;
    private javax.swing.JButton btnBmasuk;
    private javax.swing.JButton btnHome;
    private javax.swing.JButton btnLogOut;
    private javax.swing.JButton btnStock1;
    private javax.swing.JButton btn_acc;
    private javax.swing.JButton btn_clear;
    private javax.swing.JButton btn_save;
    private javax.swing.JButton btn_tidakijinkan;
    private javax.swing.JTextField fld_idpb;
    private javax.swing.JTextField fld_jumlah;
    private javax.swing.JTextField fld_kdkaryawan;
    private javax.swing.JTextField fld_kdkategori;
    private javax.swing.JTextField fld_namaBarang;
    private javax.swing.JLabel id_karya1;
    private javax.swing.JLabel id_karya3;
    private javax.swing.JLabel id_karya4;
    private javax.swing.JLabel id_karya7;
    private javax.swing.JLabel id_karya9;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel ket;
    private javax.swing.JTextArea keterangan;
    private javax.swing.JButton permintaan;
    private javax.swing.JButton permintaan2;
    private javax.swing.JTable tableBarang;
    private javax.swing.JTable tablekar;
    private javax.swing.JTable tablekategori;
    // End of variables declaration//GEN-END:variables
}
