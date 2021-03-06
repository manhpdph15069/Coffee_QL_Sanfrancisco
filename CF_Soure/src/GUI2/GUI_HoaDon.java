/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI2;

import BUS_Services.QLHoaDOn_Service;
import Utils.ThongBao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author phamd
 */
public class GUI_HoaDon extends javax.swing.JPanel {

    private NumberFormat n = new DecimalFormat("#,###");
    SimpleDateFormat fomat = new SimpleDateFormat("hh:mm | dd-MM-yyyy");
    SimpleDateFormat fomatNAM = new SimpleDateFormat("yyyy");
    JPopupMenu menu = new JPopupMenu("Popup");
    QLHoaDOn_Service dao;

    /**
     * Creates new form TestHD
     */
    public GUI_HoaDon() {
        initComponents();

        init();

    }

    void init() {
        dao = new QLHoaDOn_Service();
        jdate.setDate(now());
        rdoTC.setSelected(true);

        int billH = 0;
        float tien = 0;
        List<Object[]> list = dao.getListHoaDonNgay(jdate.getDate());

        if (list.size() != 0) {
            btnNgayActionPerformed(null);
        } else {
            btnTAtcaActionPerformed(null);

        }
    }

    public Date now() {
        return new Date();
    }

    public void LocTheoMaNV() {
        menu.removeAll();

        JMenuItem item = new JMenuItem("Lọc Nhân Viên");
        item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String ma = JOptionPane.showInputDialog(null, "Nhập tên nhân viên muốn lọc");
                DefaultTableModel dtm = (DefaultTableModel) tbl.getModel();
                dtm.fireTableDataChanged();
                TableRowSorter sorter = new TableRowSorter(dtm);
                tbl.setRowSorter(sorter);
                sorter.setRowFilter(RowFilter.regexFilter(ma, 1));
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jdate = new com.toedter.calendar.JDateChooser();
        btnNgay = new javax.swing.JButton();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jRadioButton3 = new javax.swing.JRadioButton();
        rdoTC = new javax.swing.JRadioButton();
        jButton2 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtTimKiem = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lblTBill = new javax.swing.JLabel();
        lblTBillH = new javax.swing.JLabel();
        lblTien = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lblTienDTT = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lblTienDH = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        lblTBillCTT = new javax.swing.JLabel();
        btnTAtca = new rojerusan.RSButtonMetro();
        jLabel7 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl = new rojeru_san.complementos.RSTableMetro();

        setPreferredSize(new java.awt.Dimension(1700, 1000));

        jPanel1.setBackground(new java.awt.Color(255, 153, 153));

        jdate.setDateFormatString("yyyy-MM-dd");

        btnNgay.setBackground(new java.awt.Color(0, 102, 0));
        btnNgay.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnNgay.setForeground(new java.awt.Color(255, 255, 255));
        btnNgay.setText("Lọc Ngày");
        btnNgay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNgayActionPerformed(evt);
            }
        });

        jRadioButton1.setBackground(new java.awt.Color(255, 153, 153));
        buttonGroup1.add(jRadioButton1);
        jRadioButton1.setFont(new java.awt.Font("Tahoma", 1, 21)); // NOI18N
        jRadioButton1.setForeground(new java.awt.Color(0, 102, 0));
        jRadioButton1.setText("Đã hủy");
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });

        jRadioButton2.setBackground(new java.awt.Color(255, 153, 153));
        buttonGroup1.add(jRadioButton2);
        jRadioButton2.setFont(new java.awt.Font("Tahoma", 1, 21)); // NOI18N
        jRadioButton2.setForeground(new java.awt.Color(0, 102, 0));
        jRadioButton2.setText("Chưa thanh toán");
        jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton2ActionPerformed(evt);
            }
        });

        jRadioButton3.setBackground(new java.awt.Color(255, 153, 153));
        buttonGroup1.add(jRadioButton3);
        jRadioButton3.setFont(new java.awt.Font("Tahoma", 1, 21)); // NOI18N
        jRadioButton3.setForeground(new java.awt.Color(0, 102, 0));
        jRadioButton3.setText("Đã Thanh Toán");
        jRadioButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton3ActionPerformed(evt);
            }
        });

        rdoTC.setBackground(new java.awt.Color(255, 153, 153));
        buttonGroup1.add(rdoTC);
        rdoTC.setFont(new java.awt.Font("Tahoma", 1, 21)); // NOI18N
        rdoTC.setForeground(new java.awt.Color(0, 102, 0));
        rdoTC.setText("Tất cả");
        rdoTC.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rdoTCMouseClicked(evt);
            }
        });
        rdoTC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoTCActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(0, 102, 0));
        jButton2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Lọc Tháng");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel4.setForeground(new java.awt.Color(0, 102, 0));
        jLabel4.setText("Từ ngày");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setText("Tìm kiếm");

        txtTimKiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTimKiemKeyTyped(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(255, 153, 153));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 102, 0));
        jLabel2.setText("Tổng Hóa Đơn");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 102, 0));
        jLabel3.setText("Hóa Đơn Bị Hủy");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 102, 0));
        jLabel6.setText("Tổng Tiền");

        lblTBill.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblTBill.setText("0");

        lblTBillH.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblTBillH.setText("0");

        lblTien.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblTien.setText("000");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 102, 0));
        jLabel8.setText("Tiền đã TT");

        lblTienDTT.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblTienDTT.setText("000");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 102, 0));
        jLabel9.setText("Tiền đã hủy");

        lblTienDH.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblTienDH.setText("000");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 102, 0));
        jLabel10.setText("Hóa Đơn Chưa TT");

        lblTBillCTT.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblTBillCTT.setText("0");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(23, 23, 23)
                        .addComponent(lblTien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(18, 18, 18)
                        .addComponent(lblTienDTT, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblTienDH, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2))
                        .addGap(35, 35, 35)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTBillH)
                            .addComponent(lblTBill)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(18, 18, 18)
                        .addComponent(lblTBillCTT)))
                .addGap(116, 116, 116))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTienDTT)
                    .addComponent(jLabel8)
                    .addComponent(jLabel10)
                    .addComponent(lblTBillCTT))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTienDH)
                    .addComponent(jLabel9)
                    .addComponent(jLabel3)
                    .addComponent(lblTBillH))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 4, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblTien)
                        .addComponent(lblTBill)
                        .addComponent(jLabel2)))
                .addGap(18, 18, 18))
        );

        btnTAtca.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnTAtca.setText("Tất Cả");
        btnTAtca.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btnTAtca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTAtcaActionPerformed(evt);
            }
        });

        jLabel7.setForeground(new java.awt.Color(0, 102, 0));
        jLabel7.setText("Lọc theo trạng thái");

        jButton1.setBackground(new java.awt.Color(0, 102, 0));
        jButton1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Lọc Năm");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jRadioButton1)
                        .addGap(18, 18, 18)
                        .addComponent(jRadioButton2)
                        .addGap(18, 18, 18)
                        .addComponent(jRadioButton3)
                        .addGap(18, 18, 18)
                        .addComponent(rdoTC))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(266, 266, 266)
                        .addComponent(jLabel7))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 529, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jdate, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnNgay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnTAtca, javax.swing.GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(53, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel4)
                        .addGap(5, 5, 5)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jdate, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel5)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jRadioButton1)
                            .addComponent(jRadioButton2)
                            .addComponent(jRadioButton3)
                            .addComponent(rdoTC)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnNgay, javax.swing.GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnTAtca, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setForeground(new java.awt.Color(240, 240, 240));

        tbl.setForeground(new java.awt.Color(255, 255, 0));
        tbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã HD", "Nhân viên", "Khách hàng", "Trương trình", "Ngày order", "Giờ order", "Ghi chú", "Đồ uống", "Tổng tiền", "Trạng thái"
            }
        ));
        tbl.setColorFilasBackgound2(new java.awt.Color(233, 233, 233));
        tbl.setColorFilasForeground1(new java.awt.Color(0, 0, 0));
        tbl.setColorFilasForeground2(new java.awt.Color(0, 0, 0));
        tbl.setFillsViewportHeight(true);
        tbl.setFuenteFilas(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        tbl.setFuenteFilasSelect(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        tbl.setFuenteHead(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        tbl.setMinimumSize(new java.awt.Dimension(1235, 64));
        tbl.setRowHeight(30);
        tbl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tblMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tbl);
        if (tbl.getColumnModel().getColumnCount() > 0) {
            tbl.getColumnModel().getColumn(0).setMinWidth(100);
            tbl.getColumnModel().getColumn(0).setMaxWidth(100);
            tbl.getColumnModel().getColumn(1).setMinWidth(140);
            tbl.getColumnModel().getColumn(1).setMaxWidth(140);
            tbl.getColumnModel().getColumn(4).setMinWidth(130);
            tbl.getColumnModel().getColumn(4).setMaxWidth(150);
            tbl.getColumnModel().getColumn(5).setMinWidth(130);
            tbl.getColumnModel().getColumn(5).setMaxWidth(150);
            tbl.getColumnModel().getColumn(6).setMinWidth(300);
            tbl.getColumnModel().getColumn(6).setMaxWidth(350);
        }

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1646, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 864, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMouseReleased
        // TODO add your handling code here:
        if (evt.isPopupTrigger()) {
            menu.show(evt.getComponent(), evt.getX(), evt.getY());
        }
    }//GEN-LAST:event_tblMouseReleased

    private void tblMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMouseClicked

        //LocTheoMaNV();
    }//GEN-LAST:event_tblMouseClicked

    private void btnNgayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNgayActionPerformed
        List<Object[]> list = dao.getListHoaDonNgay(jdate.getDate());
        if (list.size() == 0) {
            ThongBao.alert(this, "Không có hóa đơn nào trong ngày này");
        } else {
            dao.fillTableNgay(tbl, jdate.getDate(), lblTien, lblTBillH, lblTBill, lblTienDTT, lblTienDH,lblTBillCTT);

        }

    }//GEN-LAST:event_btnNgayActionPerformed

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        // TODO add your handling code here:
        loc("Đã hủy");
    }//GEN-LAST:event_jRadioButton1ActionPerformed

    private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton2ActionPerformed
        // TODO add your handling code here:
        loc("Chưa thanh toán");
    }//GEN-LAST:event_jRadioButton2ActionPerformed

    private void jRadioButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton3ActionPerformed
        // TODO add your handling code here:
        loc("Đã thanh toán");
    }//GEN-LAST:event_jRadioButton3ActionPerformed

    private void rdoTCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoTCActionPerformed
        loc("");
    }//GEN-LAST:event_rdoTCActionPerformed

    private void rdoTCMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rdoTCMouseClicked

    }//GEN-LAST:event_rdoTCMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        
        List<Object[]> list = dao.getListHoaDonTHANG(jdate.getDate().getMonth() + 1, Integer.parseInt(fomatNAM.format(jdate.getDate())));

        if (list.size() == 0) {
            ThongBao.alert(this, "Tháng này không có hóa đơn nào");
        } else {
            dao.fillTableTHANG2(tbl, jdate.getDate().getMonth() + 1, Integer.parseInt(fomatNAM.format(jdate.getDate())), lblTien, lblTBillH, lblTBill, lblTienDTT, lblTienDH,lblTBillCTT);

        }


    }//GEN-LAST:event_jButton2ActionPerformed

    private void tblMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMousePressed
        // TODO add your handling code here:
        if (evt.isPopupTrigger()) {
            menu.show(evt.getComponent(), evt.getX(), evt.getY());
        }
    }//GEN-LAST:event_tblMousePressed

    private void txtTimKiemKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKeyTyped
        // TODO add your handling code here:
        DefaultTableModel m = (DefaultTableModel) tbl.getModel();
        m.fireTableDataChanged();
        TableRowSorter sorter = new TableRowSorter(m);
        tbl.setRowSorter(sorter);
        sorter.setRowFilter(RowFilter.regexFilter(txtTimKiem.getText()));
    }//GEN-LAST:event_txtTimKiemKeyTyped

    private void btnTAtcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTAtcaActionPerformed
        // TODO add your handling code here:

        //   rdoTC.setSelected(true);
        dao.fillTable(tbl, lblTien, lblTBillH, lblTBill, lblTienDTT, lblTienDH,lblTBillCTT);


    }//GEN-LAST:event_btnTAtcaActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
         SimpleDateFormat formatNam = new SimpleDateFormat("yyyy");
         int nam = Integer.valueOf(formatNam.format(jdate.getDate()));
     //    int nam = Integer.valueOf(String.valueOf(jdate.getDate()).substring(0, 4));
        List<Object[]> list = dao.getListHoaDonNAM(nam);
        System.out.println(""+nam);

        if (list.size() == 0) {
            ThongBao.alert(this, "Năm này không có hóa đơn nào");
        } else {
            dao.fillTableNAM(tbl, nam, lblTien, lblTBillH, lblTBill, lblTienDTT, lblTienDH,lblTBillCTT);

        }
    }//GEN-LAST:event_jButton1ActionPerformed

    public void loc(String txt) {
        DefaultTableModel m = (DefaultTableModel) tbl.getModel();
        m.fireTableDataChanged();
        TableRowSorter sorter = new TableRowSorter(m);
        tbl.setRowSorter(sorter);
        sorter.setRowFilter(RowFilter.regexFilter(txt));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnNgay;
    private rojerusan.RSButtonMetro btnTAtca;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
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
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JScrollPane jScrollPane1;
    private com.toedter.calendar.JDateChooser jdate;
    private javax.swing.JLabel lblTBill;
    private javax.swing.JLabel lblTBillCTT;
    private javax.swing.JLabel lblTBillH;
    private javax.swing.JLabel lblTien;
    private javax.swing.JLabel lblTienDH;
    private javax.swing.JLabel lblTienDTT;
    private javax.swing.JRadioButton rdoTC;
    private rojeru_san.complementos.RSTableMetro tbl;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
