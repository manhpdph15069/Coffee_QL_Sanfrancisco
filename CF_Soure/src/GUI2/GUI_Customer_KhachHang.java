/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI2;

import DAL_Models.ENTITY_Customer;
import DAL_Services.Customer_Service;
import Utils.Check;
import Utils.JDBC;
import Utils.dateHelper;
import Utils.dialogHelper;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author notak
 */
public class GUI_Customer_KhachHang extends javax.swing.JPanel {

    JPopupMenu menu = new JPopupMenu("Popup");
    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
    Customer_Service dao = new Customer_Service();
    int row = -1;

    /**
     * Creates new form GUI_Customer_KhachHang
     */
    public GUI_Customer_KhachHang() {
        initComponents();
//        tblKhachHang.getColumnModel().getColumn(0).setMinWidth(0);
//        tblKhachHang.getColumnModel().getColumn(0).setMaxWidth(0);
        init();
    }

    void init() {
        date();
        filltoTable();
        this.clearForm();
        taoID();
    }

    void date() {
        try {
            SimpleDateFormat fo = new SimpleDateFormat("yyyy-MM-dd");
            List<ENTITY_Customer> list = dao.select();
            for (ENTITY_Customer kh : list) {
                String date1 = fo.format(dateHelper.now());
                String date2 = fo.format(kh.getDateEnd());
                System.out.println(date1);
                System.out.println(date2 + "\n");
                if (date1.compareTo(date2) > 0) { //neu DateEnd < Today
                    dao.up(String.valueOf(kh.getDateEnd()));
                    kh.setStatus(false);
                } else {
                    dao.up1(String.valueOf(kh.getDateEnd()));
                }
            }
        } catch (Exception e) {
            dialogHelper.alert(this, " Lỗi Date");
            e.printStackTrace();
        }
    }

    void filltoTable() {
        DefaultTableModel model = (DefaultTableModel) tblKhachHang.getModel();
        model.setRowCount(0);
        try {
            List<ENTITY_Customer> list = dao.select();
            for (ENTITY_Customer kh : list) {
                Object[] row = {
                    kh.getIDCust(),
                    kh.getCCCD(),
                    kh.getCusName(),
                    dateHelper.dateToString(kh.getDateAdd(), "dd/MM/yyyy"),
                    dateHelper.dateToString(kh.getDateEnd(), "dd/MM/yyyy"),
                    kh.getPhone(),
                    kh.getEmail(),
                    kh.getDiscount() + "%",
                    kh.getStatus() ? "Đang hoạt động" : "Hết Hạn"};
                model.addRow(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
            dialogHelper.alert(this, "Loi Truy Van Du Lieu");
        }
    }

    ENTITY_Customer getForm() { //lấy ra nv từ thông tin nhập trên Form
        ENTITY_Customer kh = new ENTITY_Customer();
        kh.setIDCust(lblID.getText());
        kh.setCCCD(txtCCCD.getText());
        kh.setCusName(txtTenKH.getText());
        kh.setDateAdd(dateHelper.toDate(txtNgayMo.getText(), "dd/MM/yyyy"));
        kh.setDateEnd(txtNgayHetHan.getDate());
        kh.setPhone(txtSDT.getText());
        kh.setEmail(txtEmail.getText());
        kh.setDiscount(Integer.valueOf(txtGiamGia.getText()));
        return kh;
    }

    void setForm(ENTITY_Customer nv) { //lấy thông tin 1 nv có sẵn hiện thị len Forrm
        lblID.setText(nv.getIDCust());
        txtCCCD.setText(nv.getCCCD());
        txtTenKH.setText(nv.getCusName());
        txtNgayMo.setText(dateHelper.dateToString(nv.getDateAdd(), "dd/MM/yyyy"));
        txtNgayHetHan.setDate(nv.getDateEnd());
        txtSDT.setText(nv.getPhone());
        txtEmail.setText(nv.getEmail());
        txtGiamGia.setText(String.valueOf(nv.getDiscount()));
    }

    void clearForm() {
        ENTITY_Customer nv = new ENTITY_Customer();
        nv.setDateAdd(dateHelper.now());
        nv.setDateEnd(dateHelper.add(30));
        this.setForm(nv);
        this.row = -1;
        taoID();
        jButton1.setEnabled(true);
        jButton2.setEnabled(false);
        jButton3.setEnabled(false);
    }

    public void taoID() {
        try {
            ResultSet rs = JDBC.query("Select MAX(IDCust) From Customer");
            rs.next();
            rs.getString(1);
            if (rs.getString(1) == null) {
                lblID.setText("KH01");
            } else {
                long id = Long.parseLong(rs.getString(1).substring(2, rs.getString(1).length()));
                id++;
                lblID.setText("KH" + String.format("%3d", id).trim());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void insert() {
        ENTITY_Customer kh = this.getForm();
        try {
            int p = JOptionPane.showConfirmDialog(this, "Bạn chắc chắn muốn Thêm Khách Hàng này?", "Hệ thống quản lý Ƹ̵̡Ӝ̵̨̄Ʒ☆", JOptionPane.YES_NO_OPTION);
            if (p == JOptionPane.YES_OPTION) {
                dao.insert(kh);
                date();
                filltoTable();
                dialogHelper.alert(this, "Thêm Khách Hàng Thành Công");
                clearForm();
                taoID();
            }
        } catch (Exception e) {
            dialogHelper.alert(this, "Lỗi Khi Thêm");
            e.printStackTrace();
        }
    }

    private void update() {
        ENTITY_Customer kh = this.getForm();
        try {
            int p = JOptionPane.showConfirmDialog(this, "Bạn chắc chắn muốn Sửa khách hàng này?", "Hệ thống quản lý Ƹ̵̡Ӝ̵̨̄Ʒ☆", JOptionPane.YES_NO_OPTION);
            if (p == JOptionPane.YES_OPTION) {
                dao.update(kh);
                date();
                filltoTable();
                dialogHelper.alert(this, "Sửa Thành Công");
                clearForm();
                taoID();
            }
        } catch (Exception e) {
            dialogHelper.alert(this, "Sửa Thất Bại");
            e.printStackTrace();
        }
    }

    private void delete() {
        String IDCust = lblID.getText();
        int p = JOptionPane.showConfirmDialog(this, "Bạn chắc chắn muốn Xóa khách hàng này?", "Hệ thống quản lý Ƹ̵̡Ӝ̵̨̄Ʒ☆", JOptionPane.YES_NO_OPTION);
        if (p == JOptionPane.YES_OPTION) {
            try {
                dao.delete(IDCust);
                filltoTable();
                dialogHelper.alert(this, "Xoá Thành Công");
                clearForm();
                taoID();
            } catch (Exception e) {
                dialogHelper.alert(this, "Xóa Thất Bại");
                e.printStackTrace();
            }
        }
    }

    void edit() {
        try {
            String ID = (String) tblKhachHang.getValueAt(this.row, 0); //lay giá trị hàng hiện tại & cột 0
            ENTITY_Customer cus = dao.findById(ID); //lay thong tin sp tuong ung trong csdl
            if (cus != null) {
                this.setForm(cus);
            }
        } catch (Exception e) {
            dialogHelper.alert(this, "Lỗi");
        }

    }
    DefaultTableModel model;

    public void timSP() {
        this.model = (DefaultTableModel) tblKhachHang.getModel();
        this.model.fireTableDataChanged();
        TableRowSorter Sorter = new TableRowSorter(this.model);
        tblKhachHang.setRowSorter(Sorter);
        Sorter.setRowFilter(RowFilter.regexFilter(txtTimKiem.getText()));
    }

    public void Giahanthe() {
        menu.removeAll();
        JMenuItem item = new JMenuItem("Gia Hạn Thẻ");
        item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SimpleDateFormat fo = new SimpleDateFormat("dd-MM-yyyy");
                row = tblKhachHang.getSelectedRow();
                if (row >= 0) {
                    String ID = (String) tblKhachHang.getValueAt(row, 0);
                    ENTITY_Customer pro = dao.findById(ID);
                    String date1 = fo.format(dateHelper.now());
                    String date2 = fo.format(pro.getDateEnd());
                    String songay = dialogHelper.prompt(null, "Nhập số ngày muốn gia hạn thẻ");
                    if (songay == null) {
                    } else {
                        if (date1.compareTo(date2) > 0) { //neu dateEnd<today
                            pro.setDateEnd(dateHelper.add(Integer.valueOf(songay)));
                        } else if (date1.compareTo(date2) < 0) {
                            pro.setDateEnd(dateHelper.addDays(pro.getDateEnd(), Integer.valueOf(songay)));
                        } else {
                            pro.setDateEnd(dateHelper.addDays(pro.getDateEnd(), Integer.valueOf(songay)));
                        }
                        try {
                            dao.giahan(pro);
                            date();
                            filltoTable();
                            dialogHelper.alert(null, "Gia Hạn Thành Công");
                            dialogHelper.alert(null,
                                    "Mã Thẻ : "+pro.getIDCust()
                                    +"\n"+"Tên Khách Hàng : "+pro.getCusName()
                                    +"\n"+"Ngày Mở Thẻ  : "+fo.format(pro.getDateAdd())
                                    +"\n"+"Ngày Hết Hạn : "+fo.format(pro.getDateEnd()));
                        } catch (SQLException ex) {
                            dialogHelper.alert(null, "Gia Hạn Thất Bại");
                            Logger.getLogger(GUI_Customer_KhachHang.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        System.out.println(fo.format(pro.getDateEnd()));
                        System.out.println(songay);
                    }
                }
            }
        });
        menu.add(item);
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
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        txtTenKH = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        txtNgayMo = new javax.swing.JTextField();
        txtGiamGia = new javax.swing.JTextField();
        txtSDT = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblKhachHang = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        lblID = new javax.swing.JLabel();
        txtTimKiem = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtNgayHetHan = new com.toedter.calendar.JDateChooser();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtCCCD = new javax.swing.JTextField();

        jPanel1.setBackground(new java.awt.Color(255, 153, 153));

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel2.setText("Ngày hết hạn");

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel4.setText("Điện Thoại");

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel5.setText("Ngày mở");

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel6.setText("Chiết Khấu");

        jLabel7.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel7.setText("Email");

        jButton1.setBackground(new java.awt.Color(0, 102, 51));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/add_KH.png"))); // NOI18N
        jButton1.setText("Thêm KH");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(0, 102, 51));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/edit_item.png"))); // NOI18N
        jButton2.setText("Sửa KH");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(0, 102, 51));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/remove-icon.png"))); // NOI18N
        jButton3.setText("Xóa ");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(0, 102, 51));
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/clear.png"))); // NOI18N
        jButton4.setText("Clear");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel8.setText("Tên Khách Hàng");

        txtEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmailActionPerformed(evt);
            }
        });

        txtNgayMo.setEditable(false);

        tblKhachHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Thẻ", "Số CMND", "Tên Khách Hàng", "Ngày Đăng Ký", "Ngày Hết Hạn", "Số Điện Thoại", "Email", "Chiết Khấu", "Trạng Thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblKhachHang.setRowHeight(25);
        tblKhachHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblKhachHangMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblKhachHangMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tblKhachHangMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tblKhachHang);

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel1.setText("Mã Thẻ");

        lblID.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblID.setForeground(new java.awt.Color(255, 0, 0));
        lblID.setText("00");

        txtTimKiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTimKiemKeyPressed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel3.setText("Tìm Kiếm");

        txtNgayHetHan.setDateFormatString("dd/MM/yyyy");

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/VIP.gif"))); // NOI18N

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/KHVIP.png"))); // NOI18N

        jLabel11.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel11.setText("CMND");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(156, 156, 156)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(jLabel8)
                            .addComponent(jLabel1)
                            .addComponent(jLabel4))
                        .addGap(50, 50, 50)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblID)
                            .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCCCD, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(195, 195, 195)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel6)
                            .addComponent(jLabel2)
                            .addComponent(jLabel5))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(45, 45, 45)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtEmail, javax.swing.GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE)
                                    .addComponent(txtGiamGia, javax.swing.GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtNgayHetHan, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtNgayMo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(29, 29, 29)
                                .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 441, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(308, 308, 308))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 901, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(57, 57, 57)))))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(135, 135, 135)
                        .addComponent(jLabel9))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(50, 50, 50))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(298, 298, 298)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 639, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 98, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtNgayMo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblID)
                    .addComponent(jLabel1))
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNgayHetHan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtCCCD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel11)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addGap(15, 15, 15)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtGiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)))
                    .addComponent(jLabel2))
                .addGap(59, 59, 59)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(129, 129, 129))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(246, 246, 246)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(38, 38, 38)
                .addComponent(jButton2)
                .addGap(39, 39, 39)
                .addComponent(jButton3)
                .addGap(35, 35, 35)
                .addComponent(jButton4)
                .addGap(226, 226, 226))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmailActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (Check.checkNullText(txtSDT)
                && Check.checkNullText(txtEmail)
                && Check.checkNullText(txtGiamGia)
                && Check.checkNullText(txtNgayMo)
                && Check.checkNullText(txtTenKH)) {
            if (Check.checkEmail(txtEmail)
                    && Check.checkSDT(txtSDT)) {
                insert();
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if (Check.checkNullText(txtSDT)
                && Check.checkNullText(txtEmail)
                && Check.checkNullText(txtGiamGia)
                && Check.checkNullText(txtNgayMo)
                && Check.checkNullText(txtTenKH)) {
            if (Check.checkEmail(txtEmail)
                    && Check.checkSDT(txtSDT)) {
                update();
            }
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        delete();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        clearForm();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void txtTimKiemKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKeyPressed
        timSP();
    }//GEN-LAST:event_txtTimKiemKeyPressed

    private void tblKhachHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKhachHangMouseClicked

        if (evt.getClickCount() == 1) {
            this.row = tblKhachHang.getSelectedRow();
            if (this.row >= 0) {
                this.edit();
                jButton2.setEnabled(true);
                jButton3.setEnabled(true);
                jButton1.setEnabled(false);
            }
        }
        Giahanthe();
    }//GEN-LAST:event_tblKhachHangMouseClicked

    private void tblKhachHangMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKhachHangMousePressed
        if (evt.isPopupTrigger()) {
            menu.show(evt.getComponent(), evt.getX(), evt.getY());
        }
    }//GEN-LAST:event_tblKhachHangMousePressed

    private void tblKhachHangMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKhachHangMouseReleased
        if (evt.isPopupTrigger()) {
            menu.show(evt.getComponent(), evt.getX(), evt.getY());
        }
    }//GEN-LAST:event_tblKhachHangMouseReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblID;
    private javax.swing.JTable tblKhachHang;
    private javax.swing.JTextField txtCCCD;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtGiamGia;
    private com.toedter.calendar.JDateChooser txtNgayHetHan;
    private javax.swing.JTextField txtNgayMo;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtTenKH;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
