/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI2;

import BUS_IServices.IQLStatistical_Service;
import BUS_Services.QLStatistical_Service;
import BUS_Services.QLTable_Service;
import DAL_Services.Product_Service;
import Utils.ThongBao;
import Utils.dateHelper;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 *
 * @author notak
 */
public class GUI_Statistical_ThongKe extends javax.swing.JPanel {

    SimpleDateFormat formatNam = new SimpleDateFormat("yyyy");
    IQLStatistical_Service dao = null;
    private NumberFormat n = new DecimalFormat("#,###");

    /**
     * Creates new form GUI_Statistical_ThongKe
     */
    public GUI_Statistical_ThongKe() {
        initComponents();
        init();
    }

    void init() {
        dao = new QLStatistical_Service();
        jDateNBD.setDate(dateHelper.now());
        JDateNKT.setDate(dateHelper.now());
        jdateSanPham.setDate(dateHelper.now());
        lblTong.setText("0");
        lblHD.setText("0");
        lblTM.setText("0");
        rdoTC.setSelected(true);
        rdoTCActionPerformed(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        pnlNgay = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        JDateNKT = new com.toedter.calendar.JDateChooser();
        jDateNBD = new com.toedter.calendar.JDateChooser();
        btnKhoang = new javax.swing.JButton();
        btnNgay = new javax.swing.JButton();
        btnThang = new javax.swing.JButton();
        btnNam = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lblTong = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lblTM = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lblHD = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSP = new rojeru_san.complementos.RSTableMetro();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblSP1 = new rojeru_san.complementos.RSTableMetro();
        jPanel4 = new javax.swing.JPanel();
        jdateSanPham = new com.toedter.calendar.JDateChooser();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jRadioButton3 = new javax.swing.JRadioButton();
        rdoTC = new javax.swing.JRadioButton();
        jLabel16 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        lbltkspTT = new javax.swing.JLabel();
        lbltkspTM = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        lbltkspTT1 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        lbltkspTM1 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel3.setText(":");

        jTabbedPane1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        pnlNgay.setBackground(new java.awt.Color(255, 153, 153));
        pnlNgay.setPreferredSize(new java.awt.Dimension(1000, 700));

        jLabel1.setFont(new java.awt.Font("Tw Cen MT", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 204, 204));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/doanh_thu.png"))); // NOI18N

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/meothongke.gif"))); // NOI18N

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/dolar.gif"))); // NOI18N

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/dolar.gif"))); // NOI18N

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/dolar.gif"))); // NOI18N

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/dolar.gif"))); // NOI18N

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/manh2.gif"))); // NOI18N

        javax.swing.GroupLayout pnlNgayLayout = new javax.swing.GroupLayout(pnlNgay);
        pnlNgay.setLayout(pnlNgayLayout);
        pnlNgayLayout.setHorizontalGroup(
            pnlNgayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNgayLayout.createSequentialGroup()
                .addGroup(pnlNgayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 492, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlNgayLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel13)))
                .addGroup(pnlNgayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlNgayLayout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 486, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41)
                        .addComponent(jLabel14)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnlNgayLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel11)
                        .addGap(154, 154, 154))))
        );
        pnlNgayLayout.setVerticalGroup(
            pnlNgayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNgayLayout.createSequentialGroup()
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addComponent(jLabel13)
                .addContainerGap(42, Short.MAX_VALUE))
            .addGroup(pnlNgayLayout.createSequentialGroup()
                .addGroup(pnlNgayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlNgayLayout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlNgayLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel14)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlNgayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlNgayLayout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(81, 81, 81))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlNgayLayout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addGap(54, 54, 54))))
        );

        jPanel1.setBackground(new java.awt.Color(204, 0, 51));

        JDateNKT.setDateFormatString("yyyy-MM-dd");
        JDateNKT.setFocusable(false);

        jDateNBD.setToolTipText("Show Ngày, Show Tháng, Show Năm");
        jDateNBD.setDateFormatString("yyyy-MM-dd");
        jDateNBD.setFocusable(false);

        btnKhoang.setBackground(new java.awt.Color(0, 102, 51));
        btnKhoang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/khoang.png"))); // NOI18N
        btnKhoang.setText("Khoảng");
        btnKhoang.setToolTipText("Chọn date ở cả 2 ô");
        btnKhoang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKhoangActionPerformed(evt);
            }
        });

        btnNgay.setBackground(new java.awt.Color(0, 102, 51));
        btnNgay.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/ngay.png"))); // NOI18N
        btnNgay.setText("Show Ngày");
        btnNgay.setToolTipText("Chọn ở ô date thứ nhất");
        btnNgay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNgayActionPerformed(evt);
            }
        });

        btnThang.setBackground(new java.awt.Color(0, 102, 51));
        btnThang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/thang.PNG"))); // NOI18N
        btnThang.setText("Show Tháng");
        btnThang.setToolTipText("Chọn ở ô date thứ nhất");
        btnThang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThangActionPerformed(evt);
            }
        });

        btnNam.setBackground(new java.awt.Color(0, 102, 51));
        btnNam.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/nam.png"))); // NOI18N
        btnNam.setText("Show Năm");
        btnNam.setToolTipText("Chọn ở ô date thứ nhất");
        btnNam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNamActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 102, 51));
        jLabel2.setText("Tổng món:");

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel4.setText(":");

        lblTong.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        lblTong.setText("00000");

        jLabel5.setBackground(new java.awt.Color(51, 102, 0));
        jLabel5.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 102, 51));
        jLabel5.setText("Từ Ngày");

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 102, 51));
        jLabel6.setText("Đến Ngày");

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 102, 51));
        jLabel7.setText("Tổng:");

        lblTM.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        lblTM.setText("00000");

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 102, 51));
        jLabel9.setText("Tổng BILL:");

        lblHD.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        lblHD.setText("00000");

        jButton1.setText("Gửi báo cáo");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jDateNBD, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(jLabel5)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(66, 66, 66)
                        .addComponent(jLabel6)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(JDateNKT, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnKhoang)
                        .addGap(18, 18, 18)
                        .addComponent(btnNgay)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnThang)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnNam)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1)
                        .addGap(46, 46, 46)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(lblTM)
                        .addGap(29, 29, 29)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblHD)
                        .addGap(26, 26, 26)
                        .addComponent(jLabel7)
                        .addGap(18, 18, 18)
                        .addComponent(lblTong, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnKhoang)
                                    .addComponent(btnNgay)
                                    .addComponent(btnNam)
                                    .addComponent(btnThang)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel4)
                                    .addComponent(lblTong)
                                    .addComponent(jLabel7)
                                    .addComponent(lblTM)
                                    .addComponent(jLabel9)
                                    .addComponent(lblHD)
                                    .addComponent(jButton1)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jDateNBD, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JDateNKT, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1679, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(pnlNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 1648, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 31, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pnlNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 898, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Thống kê doanh thu", jPanel2);

        jPanel3.setBackground(new java.awt.Color(255, 153, 153));

        tblSP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Tên sản phẩm", "Số lượng", "Giá", "Thành tiền", "Trạng thái"
            }
        ));
        tblSP.setColorFilasBackgound2(new java.awt.Color(255, 255, 255));
        tblSP.setColorFilasForeground1(new java.awt.Color(0, 0, 0));
        tblSP.setColorFilasForeground2(new java.awt.Color(0, 0, 0));
        jScrollPane1.setViewportView(tblSP);

        tblSP1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Tên sản phẩm", "Số lượng", "Giá", "Thành tiền", "Lý do", "Trạng thái"
            }
        ));
        tblSP1.setColorFilasBackgound2(new java.awt.Color(255, 255, 255));
        tblSP1.setColorFilasForeground1(new java.awt.Color(0, 0, 0));
        tblSP1.setColorFilasForeground2(new java.awt.Color(0, 0, 0));
        jScrollPane2.setViewportView(tblSP1);

        jPanel4.setBackground(new java.awt.Color(204, 0, 51));

        jdateSanPham.setDateFormatString("yyyy-MM-dd");

        buttonGroup1.add(jRadioButton1);
        jRadioButton1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jRadioButton1.setText("Lọc Ngày");
        jRadioButton1.setBorderPainted(true);
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRadioButton2);
        jRadioButton2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jRadioButton2.setText("Lọc Tháng");
        jRadioButton2.setBorderPainted(true);
        jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton2ActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRadioButton3);
        jRadioButton3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jRadioButton3.setText("Lọc Năm");
        jRadioButton3.setBorderPainted(true);
        jRadioButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton3ActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdoTC);
        rdoTC.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        rdoTC.setText("Tất cả");
        rdoTC.setBorderPainted(true);
        rdoTC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoTCActionPerformed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel16.setText("Chọn ngày");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel16)
                .addGap(29, 29, 29)
                .addComponent(jdateSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jRadioButton1)
                .addGap(18, 18, 18)
                .addComponent(jRadioButton2)
                .addGap(18, 18, 18)
                .addComponent(jRadioButton3)
                .addGap(18, 18, 18)
                .addComponent(rdoTC)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jRadioButton1)
                        .addComponent(jRadioButton2)
                        .addComponent(jRadioButton3)
                        .addComponent(rdoTC))
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel16)
                        .addComponent(jdateSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(29, Short.MAX_VALUE))
        );

        jLabel15.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Tổng tiền");

        lbltkspTT.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        lbltkspTT.setForeground(new java.awt.Color(255, 255, 255));
        lbltkspTT.setText("000000");

        lbltkspTM.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        lbltkspTM.setForeground(new java.awt.Color(255, 255, 255));
        lbltkspTM.setText("0000");

        jLabel18.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Tổng đồ");

        jLabel17.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Tổng tiền");

        lbltkspTT1.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        lbltkspTT1.setForeground(new java.awt.Color(255, 255, 255));
        lbltkspTT1.setText("000000");

        jLabel19.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("Tổng đồ");

        lbltkspTM1.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        lbltkspTM1.setForeground(new java.awt.Color(255, 255, 255));
        lbltkspTM1.setText("0000");

        jLabel20.setText("Sản phẩm bán ra");

        jLabel21.setText("Sản phẩm đã hủy");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 800, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 837, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel21)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(145, 145, 145)
                .addComponent(jLabel15)
                .addGap(18, 18, 18)
                .addComponent(lbltkspTT)
                .addGap(39, 39, 39)
                .addComponent(jLabel18)
                .addGap(18, 18, 18)
                .addComponent(lbltkspTM)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel17)
                .addGap(18, 18, 18)
                .addComponent(lbltkspTT1)
                .addGap(39, 39, 39)
                .addComponent(jLabel19)
                .addGap(18, 18, 18)
                .addComponent(lbltkspTM1)
                .addGap(211, 211, 211))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(lbltkspTT1)
                    .addComponent(jLabel19)
                    .addComponent(lbltkspTM1)
                    .addComponent(jLabel15)
                    .addComponent(lbltkspTT)
                    .addComponent(jLabel18)
                    .addComponent(lbltkspTM))
                .addGap(59, 59, 59)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(jLabel21))
                .addGap(7, 7, 7)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 702, Short.MAX_VALUE)
                    .addComponent(jScrollPane2))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Thống kê sản phẩm", jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1684, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1010, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnKhoangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKhoangActionPerformed
        List<Object[]> list = dao.getListByTKKhoangList(jDateNBD.getDate(), JDateNKT.getDate());
        try {
            if (list.size() != 0) {

                dao.setDataKhoang(pnlNgay, jDateNBD.getDate(), JDateNKT.getDate());
                dao.setTongMonKhoang(lblTM, lblHD, jDateNBD.getDate(), JDateNKT.getDate());
                float tong = 0;
                for (Object[] o : list) {
                    tong += Float.valueOf(String.valueOf(o[0]));;
                }
                setLbl(tong);
                                lblTong.setText(n.format(tong));
            } else {
                ThongBao.alert(this, "Khoảng bạn chọn không có dữ liệu");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnKhoangActionPerformed

    private void btnNgayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNgayActionPerformed
        List<Object[]> list = dao.getListByTKNgay(jDateNBD.getDate());
        try {
            if (list.size() != 0) {

                dao.setDataNgay(pnlNgay, jDateNBD.getDate());
                dao.setTongMonNgay(lblTM, lblHD, jDateNBD.getDate());

                float tong = 0;
                for (Object[] o : list) {
                    tong += Float.valueOf(String.valueOf(o[0]));
                }
                setLbl(tong);
                lblTong.setText(n.format(tong));
            } else {
                ThongBao.alert(this, "Ngày bạn chọn không có dữ liệu");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnNgayActionPerformed

    private void btnThangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThangActionPerformed
        int thang = Integer.valueOf(jDateNBD.getDate().getMonth() + 1);
        List<Object[]> list = dao.getListByTKThang(thang);
        try {
            if (list.size() != 0) {
                dao.setDataThang(pnlNgay, thang);
                dao.setTongMonThang(lblTM, lblHD, thang);

                float tong = 0;

                for (Object[] o : list) {
                    tong += Float.valueOf(String.valueOf(o[0]));;
                }
                setLbl(tong);
                                lblTong.setText(n.format(tong));
            } else {
                ThongBao.alert(this, "Tháng bạn chọn không có dữ liệu");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnThangActionPerformed

    private void btnNamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNamActionPerformed
        try {
            int nam = Integer.valueOf(formatNam.format(jDateNBD.getDate()));
            List<Object[]> list = dao.getListByTKNam(nam);
            if (list.size() != 0) {

                System.out.println("" + nam);
                dao.setDataNam(pnlNgay, nam);
                dao.setTongMonNam(lblTM, lblHD, nam);

                float tong = 0;
                for (Object[] o : list) {
                    tong += Float.valueOf(String.valueOf(o[0]));;
                }
                setLbl(tong);
                                lblTong.setText(n.format(tong));
            } else {
                ThongBao.alert(this, "Năm bạn chọn không có dữ liệu");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnNamActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {

            //  dao.guiBCN(Integer.valueOf(formatNam.format(jDateNBD.getDate())));
            dao.guiBCNgay(jDateNBD.getDate());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void rdoTCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoTCActionPerformed
        // TODO add your handling code here:
        dao.fillTableSanPham(tblSP,tblSP1, lbltkspTT, lbltkspTM,lbltkspTT1,lbltkspTM1);
    }//GEN-LAST:event_rdoTCActionPerformed

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        // TODO add your handling code here:
            SimpleDateFormat formatSanPham = new SimpleDateFormat("yyyy-MM-dd");
     dao.fillTableSanPhamNGAY(formatSanPham.format(jdateSanPham.getDate()),tblSP,tblSP1, lbltkspTT, lbltkspTM,lbltkspTT1,lbltkspTM1);
    }//GEN-LAST:event_jRadioButton1ActionPerformed

    private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton2ActionPerformed
        // TODO add your handling code here:
        dao.fillTableSanPhamTHANG(jdateSanPham.getDate().getMonth()+1,tblSP,tblSP1, lbltkspTT, lbltkspTM,lbltkspTT1,lbltkspTM1);
    }//GEN-LAST:event_jRadioButton2ActionPerformed

    private void jRadioButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton3ActionPerformed
        // TODO add your handling code here:
         int nam = Integer.valueOf(formatNam.format(jdateSanPham.getDate()));
        dao.fillTableSanPhamNAM(nam,tblSP,tblSP1, lbltkspTT, lbltkspTM,lbltkspTT1,lbltkspTM1);
    }//GEN-LAST:event_jRadioButton3ActionPerformed

    void setLbl(float tong) {
//        lblTong.setText(String.valueOf(tong));
        if (lblTong.getText().equals("null") || lblHD.getText().equals("null") || lblTM.getText().equals("null")) {
            lblTong.setText("0");
            lblHD.setText("0");
            lblTM.setText("0");
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser JDateNKT;
    private javax.swing.JButton btnKhoang;
    private javax.swing.JButton btnNam;
    private javax.swing.JButton btnNgay;
    private javax.swing.JButton btnThang;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private com.toedter.calendar.JDateChooser jDateNBD;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private com.toedter.calendar.JDateChooser jdateSanPham;
    private javax.swing.JLabel lblHD;
    private javax.swing.JLabel lblTM;
    private javax.swing.JLabel lblTong;
    private javax.swing.JLabel lbltkspTM;
    private javax.swing.JLabel lbltkspTM1;
    private javax.swing.JLabel lbltkspTT;
    private javax.swing.JLabel lbltkspTT1;
    private javax.swing.JPanel pnlNgay;
    private javax.swing.JRadioButton rdoTC;
    private rojeru_san.complementos.RSTableMetro tblSP;
    private rojeru_san.complementos.RSTableMetro tblSP1;
    // End of variables declaration//GEN-END:variables
}
