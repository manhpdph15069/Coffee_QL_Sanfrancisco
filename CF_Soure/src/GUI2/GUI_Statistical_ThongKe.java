/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI2;

import BUS_IServices.IQLStatistical_Service;
import BUS_Services.QLStatistical_Service;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


/**
 *
 * @author notak
 */
public class GUI_Statistical_ThongKe extends javax.swing.JPanel {

    SimpleDateFormat formatNam = new SimpleDateFormat("yyyy");
    SimpleDateFormat format= new SimpleDateFormat("hh");
    IQLStatistical_Service dao = null;

    /**
     * Creates new form GUI_Statistical_ThongKe
     */
    public GUI_Statistical_ThongKe() {
        initComponents();
        init();
    }

    void init() {
        dao = new QLStatistical_Service();
        jDateNBD.setDate(now());
        JDateNKT.setDate(now());
        lblTong.setText("0");
        lblHD.setText("0");
        lblTM.setText("0");
    }

    public Date now() {
        return new Date();
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

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel3.setText(":");

        pnlNgay.setBackground(new java.awt.Color(0, 102, 255));
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
                    .addGroup(pnlNgayLayout.createSequentialGroup()
                        .addGroup(pnlNgayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(pnlNgayLayout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addGap(36, 36, 36))
                            .addGroup(pnlNgayLayout.createSequentialGroup()
                                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(24, 24, 24)))
                        .addGroup(pnlNgayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 579, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10)))
                    .addGroup(pnlNgayLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel13)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 113, Short.MAX_VALUE)
                .addGroup(pnlNgayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        pnlNgayLayout.setVerticalGroup(
            pnlNgayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNgayLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel11)
                .addGap(329, 329, 329))
            .addGroup(pnlNgayLayout.createSequentialGroup()
                .addGroup(pnlNgayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlNgayLayout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel13))
                    .addGroup(pnlNgayLayout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel10)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.setBackground(new java.awt.Color(255, 153, 153));

        JDateNKT.setDateFormatString("yyyy-MM-dd");
        JDateNKT.setFocusable(false);

        jDateNBD.setToolTipText("Show Ngày, Show Tháng, Show Năm");
        jDateNBD.setDateFormatString("yyyy-MM-dd");
        jDateNBD.setFocusable(false);

        btnKhoang.setBackground(new java.awt.Color(255, 102, 51));
        btnKhoang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/khoang.png"))); // NOI18N
        btnKhoang.setText("Khoảng");
        btnKhoang.setToolTipText("Chọn date ở cả 2 ô");
        btnKhoang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKhoangActionPerformed(evt);
            }
        });

        btnNgay.setBackground(new java.awt.Color(255, 102, 51));
        btnNgay.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/ngay.png"))); // NOI18N
        btnNgay.setText("Show Ngày");
        btnNgay.setToolTipText("Chọn ở ô date thứ nhất");
        btnNgay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNgayActionPerformed(evt);
            }
        });

        btnThang.setBackground(new java.awt.Color(255, 102, 51));
        btnThang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/thang.PNG"))); // NOI18N
        btnThang.setText("Show Tháng");
        btnThang.setToolTipText("Chọn ở ô date thứ nhất");
        btnThang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThangActionPerformed(evt);
            }
        });

        btnNam.setBackground(new java.awt.Color(255, 102, 51));
        btnNam.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/nam.png"))); // NOI18N
        btnNam.setText("Show Năm");
        btnNam.setToolTipText("Chọn ở ô date thứ nhất");
        btnNam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNamActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 153, 0));
        jLabel2.setText("Tổng món:");

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel4.setText(":");

        lblTong.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblTong.setText("00000");

        jLabel5.setBackground(new java.awt.Color(51, 102, 0));
        jLabel5.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(51, 102, 0));
        jLabel5.setText("Từ Ngày");

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(51, 102, 0));
        jLabel6.setText("Đến Ngày");

        jLabel7.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(51, 153, 0));
        jLabel7.setText("Tổng:");

        lblTM.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblTM.setText("00000");

        jLabel9.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(51, 153, 0));
        jLabel9.setText("Tổng BILL:");

        lblHD.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
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
                        .addGap(42, 42, 42)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblTM)
                        .addGap(29, 29, 29)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblHD)
                        .addGap(26, 26, 26)
                        .addComponent(jLabel7)
                        .addGap(18, 18, 18)
                        .addComponent(lblTong, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)
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
                .addContainerGap(21, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlNgay, javax.swing.GroupLayout.DEFAULT_SIZE, 1700, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 1046, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnKhoangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKhoangActionPerformed
        try {
            dao.setDataKhoang(pnlNgay, jDateNBD.getDate(), JDateNKT.getDate());
            dao.setTongMonKhoang(lblTM, lblHD, jDateNBD.getDate(), JDateNKT.getDate());
            float tong = 0;
            List<Object[]> list = dao.getListByTKKhoangList(jDateNBD.getDate(), JDateNKT.getDate());
            for (Object[] o : list) {
                tong += Float.valueOf(String.valueOf(o[0]));;
            }
            setLbl(tong);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnKhoangActionPerformed

    private void btnNgayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNgayActionPerformed
        try {
            dao.setDataNgay(pnlNgay, jDateNBD.getDate());
            dao.setTongMonNgay(lblTM, lblHD, jDateNBD.getDate());

            float tong = 0;
            List<Object[]> list = dao.getListByTKNgay(jDateNBD.getDate());
            for (Object[] o : list) {
                tong += Float.valueOf(String.valueOf(o[0]));;
            }
            setLbl(tong);

        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnNgayActionPerformed

    private void btnThangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThangActionPerformed
        try {
            dao.setDataThang(pnlNgay, jDateNBD.getDate().getMonth() + 1);
            dao.setTongMonThang(lblTM, lblHD, (jDateNBD.getDate().getMonth() + 1));

            float tong = 0;
            int thang = Integer.valueOf(jDateNBD.getDate().getMonth() + 1);
            List<Object[]> list = dao.getListByTKThang(thang);
            for (Object[] o : list) {
                tong += Float.valueOf(String.valueOf(o[0]));;
            }
            setLbl(tong);
            // System.out.println(""+jDateNBD.getDate().getMonth()+1);
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnThangActionPerformed

    private void btnNamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNamActionPerformed
        try {
            int nam = Integer.valueOf(formatNam.format(jDateNBD.getDate()));
            System.out.println("" + nam);
            dao.setDataNam(pnlNgay, nam);
            dao.setTongMonNam(lblTM, lblHD, nam);

            float tong = 0;
            List<Object[]> list = dao.getListByTKNam(nam);
            for (Object[] o : list) {
                tong += Float.valueOf(String.valueOf(o[0]));;
            }
            setLbl(tong);
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
    
    

    void setLbl(float tong) {
        lblTong.setText(String.valueOf(tong));
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
    private javax.swing.JButton jButton1;
    private com.toedter.calendar.JDateChooser jDateNBD;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblHD;
    private javax.swing.JLabel lblTM;
    private javax.swing.JLabel lblTong;
    private javax.swing.JPanel pnlNgay;
    // End of variables declaration//GEN-END:variables
}
