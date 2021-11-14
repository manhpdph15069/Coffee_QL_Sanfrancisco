/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI2;

import GUI.GUI_Login;
import GUI.GUI_ResetPassword;
import Utils.ThongBao;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author phamd
 */
public class GUI_MAIN extends javax.swing.JFrame {

    /**
     * Creates new form GUI_MAIN
     */
    public GUI_MAIN() {
        initComponents();
        setExtendedState(JFrame.MAXIMIZED_BOTH);    //cho toàn màn hình 
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        From = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        Fromch = new javax.swing.JPanel();
        jToolBar1 = new javax.swing.JToolBar();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        btnQLNV = new javax.swing.JButton();
        jSeparator8 = new javax.swing.JToolBar.Separator();
        btnQLOrder = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        btnQLMenu = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JToolBar.Separator();
        btnQLBan = new javax.swing.JButton();
        jSeparator4 = new javax.swing.JToolBar.Separator();
        btnQLTK = new javax.swing.JButton();
        jSeparator5 = new javax.swing.JToolBar.Separator();
        btnQLVip = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jSeparator6 = new javax.swing.JPopupMenu.Separator();
        jMenuItem2 = new javax.swing.JMenuItem();
        jSeparator7 = new javax.swing.JPopupMenu.Separator();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        From.setBackground(new java.awt.Color(255, 204, 255));

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 102, 0));
        jLabel1.setText("00:00:00");

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/Button-Info-icon.png"))); // NOI18N

        Fromch.setBackground(new java.awt.Color(0, 51, 204));
        Fromch.setLayout(new javax.swing.BoxLayout(Fromch, javax.swing.BoxLayout.LINE_AXIS));

        javax.swing.GroupLayout FromLayout = new javax.swing.GroupLayout(From);
        From.setLayout(FromLayout);
        FromLayout.setHorizontalGroup(
            FromLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Fromch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(FromLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 849, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addContainerGap())
        );
        FromLayout.setVerticalGroup(
            FromLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FromLayout.createSequentialGroup()
                .addComponent(Fromch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(FromLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel1)))
        );

        jToolBar1.setBackground(new java.awt.Color(102, 102, 102));
        jToolBar1.setBorder(null);
        jToolBar1.setForeground(new java.awt.Color(0, 102, 102));
        jToolBar1.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jToolBar1.setRollover(true);
        jToolBar1.add(jSeparator1);

        btnQLNV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/User-Group-icon.png"))); // NOI18N
        btnQLNV.setText("Quản lý nhân viên");
        btnQLNV.setFocusable(false);
        btnQLNV.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnQLNV.setMaximumSize(new java.awt.Dimension(100, 30));
        btnQLNV.setMinimumSize(new java.awt.Dimension(100, 30));
        btnQLNV.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnQLNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQLNVActionPerformed(evt);
            }
        });
        jToolBar1.add(btnQLNV);
        jToolBar1.add(jSeparator8);

        btnQLOrder.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/Order-history-icon.png"))); // NOI18N
        btnQLOrder.setText("Quản lý order");
        btnQLOrder.setFocusable(false);
        btnQLOrder.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnQLOrder.setMaximumSize(new java.awt.Dimension(100, 30));
        btnQLOrder.setMinimumSize(new java.awt.Dimension(100, 30));
        btnQLOrder.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnQLOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQLOrderActionPerformed(evt);
            }
        });
        jToolBar1.add(btnQLOrder);
        jToolBar1.add(jSeparator2);

        btnQLMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/drink-4-icon.png"))); // NOI18N
        btnQLMenu.setText("Quản lý menu");
        btnQLMenu.setFocusable(false);
        btnQLMenu.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnQLMenu.setMaximumSize(new java.awt.Dimension(100, 30));
        btnQLMenu.setMinimumSize(new java.awt.Dimension(100, 30));
        btnQLMenu.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(btnQLMenu);
        jToolBar1.add(jSeparator3);

        btnQLBan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/qlban-icon.png"))); // NOI18N
        btnQLBan.setText("Quản lý Bàn");
        btnQLBan.setFocusable(false);
        btnQLBan.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnQLBan.setMaximumSize(new java.awt.Dimension(100, 30));
        btnQLBan.setMinimumSize(new java.awt.Dimension(100, 30));
        btnQLBan.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnQLBan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQLBanActionPerformed(evt);
            }
        });
        jToolBar1.add(btnQLBan);
        jToolBar1.add(jSeparator4);

        btnQLTK.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/cash-icon.png"))); // NOI18N
        btnQLTK.setText("Quản lý TK DT");
        btnQLTK.setFocusable(false);
        btnQLTK.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnQLTK.setMaximumSize(new java.awt.Dimension(100, 30));
        btnQLTK.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnQLTK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQLTKActionPerformed(evt);
            }
        });
        jToolBar1.add(btnQLTK);
        jToolBar1.add(jSeparator5);

        btnQLVip.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/vip-icon.png"))); // NOI18N
        btnQLVip.setText("Quản lý KH vip");
        btnQLVip.setFocusable(false);
        btnQLVip.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnQLVip.setMaximumSize(new java.awt.Dimension(100, 30));
        btnQLVip.setMinimumSize(new java.awt.Dimension(100, 30));
        btnQLVip.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnQLVip.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQLVipActionPerformed(evt);
            }
        });
        jToolBar1.add(btnQLVip);

        jMenuBar1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jMenu1.setText("Tài Khoản");

        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/man-icon.png"))); // NOI18N
        jMenuItem1.setText("Thông tin tài khoản");
        jMenu1.add(jMenuItem1);
        jMenu1.add(jSeparator6);

        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/doimk-icon.png"))); // NOI18N
        jMenuItem2.setText("Đổi Mật Khẩu");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);
        jMenu1.add(jSeparator7);

        jMenuItem3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/Log-Out-icon.png"))); // NOI18N
        jMenuItem3.setText("Đăng Xuất");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Thông Tin");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(From, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(From, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 528, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnQLNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQLNVActionPerformed
        // TODO add your handling code here:
        GUI2.GUI_Employee_NhanVien nel = new GUI2.GUI_Employee_NhanVien();
        goiPan(nel);
    }//GEN-LAST:event_btnQLNVActionPerformed

    private void btnQLOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQLOrderActionPerformed
        GUI2.GUI_QL_Order nel = new GUI2.GUI_QL_Order();
        goiPan(nel);
    }//GEN-LAST:event_btnQLOrderActionPerformed

    private void btnQLVipActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQLVipActionPerformed
        // TODO add your handling code here:
        GUI2.GUI_Customer_KhachHang kh = new GUI2.GUI_Customer_KhachHang();
        goiPan(kh);
    }//GEN-LAST:event_btnQLVipActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        new GUI_ResetPassword(this, true).setVisible(true);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void btnQLTKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQLTKActionPerformed
        // TODO add your handling code here:
        GUI2.GUI_Statistical_ThongKe nel = new GUI2.GUI_Statistical_ThongKe();
        goiPan(nel);
    }//GEN-LAST:event_btnQLTKActionPerformed

    private void btnQLBanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQLBanActionPerformed
        GUI2.GUI_Table table = new GUI2.GUI_Table();
        goiPan(table);
    }//GEN-LAST:event_btnQLBanActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        if (ThongBao.comfirm(this, "Bạn chắc chắn muốn đăng xuất")) {
            new GUI_Login(this, true).setVisible(true);
        }
    }//GEN-LAST:event_jMenuItem3ActionPerformed
    
    public void goiPan(JPanel nel) {
        nel.setVisible(true);
        this.Fromch.removeAll();
        this.Fromch.add(nel);
        revalidate();
    }

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
            java.util.logging.Logger.getLogger(GUI_MAIN.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI_MAIN.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI_MAIN.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI_MAIN.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUI_MAIN().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel From;
    private javax.swing.JPanel Fromch;
    private javax.swing.JButton btnQLBan;
    private javax.swing.JButton btnQLMenu;
    private javax.swing.JButton btnQLNV;
    private javax.swing.JButton btnQLOrder;
    private javax.swing.JButton btnQLTK;
    private javax.swing.JButton btnQLVip;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JToolBar.Separator jSeparator3;
    private javax.swing.JToolBar.Separator jSeparator4;
    private javax.swing.JToolBar.Separator jSeparator5;
    private javax.swing.JPopupMenu.Separator jSeparator6;
    private javax.swing.JPopupMenu.Separator jSeparator7;
    private javax.swing.JToolBar.Separator jSeparator8;
    private javax.swing.JToolBar jToolBar1;
    // End of variables declaration//GEN-END:variables
}
