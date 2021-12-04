/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI_Dialog;

import DAL_Models.ENTITY_ADMIN;
import DAL_Models.ENTITY_Employee;
import DAL_Services.Admin;
import DAL_Services.Employee_Service;
import Utils.Auth;
import Utils.ThongBao;
import java.awt.Color;
import static java.awt.Color.pink;
import static java.awt.Color.white;
import java.awt.Cursor;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;

/**
 *
 * @author phamd
 */
public class GUI_Login extends javax.swing.JDialog {

    Employee_Service dao = new Employee_Service();
    Admin ad = new Admin();

    /**
     * Creates new form GUI_Login
     */
    public GUI_Login(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnLogin1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        txtUsername = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        lblForgetpass = new javax.swing.JLabel();
        btnLogin = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnLogin2 = new javax.swing.JButton();
        txtPassword = new javax.swing.JPasswordField();

        btnLogin1.setBackground(new java.awt.Color(102, 255, 102));
        btnLogin1.setForeground(new java.awt.Color(255, 255, 255));
        btnLogin1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/Login-icon.png"))); // NOI18N
        btnLogin1.setText("LOGIN");
        btnLogin1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogin1ActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 153, 204));

        txtUsername.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUsernameActionPerformed(evt);
            }
        });
        txtUsername.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtUsernameKeyPressed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 51, 51));
        jLabel1.setText("WELLCOME");

        lblForgetpass.setFont(new java.awt.Font("Dialog", 3, 14)); // NOI18N
        lblForgetpass.setForeground(new java.awt.Color(51, 51, 55));
        lblForgetpass.setText("Forget password");
        lblForgetpass.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblForgetpassMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblForgetpassMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblForgetpassMouseExited(evt);
            }
        });

        btnLogin.setBackground(new java.awt.Color(204, 0, 0));
        btnLogin.setForeground(new java.awt.Color(255, 255, 255));
        btnLogin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/Button-Close-icon.png"))); // NOI18N
        btnLogin.setText("CANCEL");
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/login-logo.gif"))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel2.setText("Username");

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel3.setText("Password");

        btnLogin2.setBackground(new java.awt.Color(102, 255, 102));
        btnLogin2.setForeground(new java.awt.Color(255, 255, 255));
        btnLogin2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/Login-icon.png"))); // NOI18N
        btnLogin2.setText("LOGIN");
        btnLogin2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogin2ActionPerformed(evt);
            }
        });
        btnLogin2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnLogin2KeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                btnLogin2KeyTyped(evt);
            }
        });

        txtPassword.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPasswordKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPasswordKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtUsername, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
                                    .addComponent(txtPassword))
                                .addGap(49, 49, 49))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(92, 92, 92)))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addComponent(btnLogin2)
                            .addGap(26, 26, 26)
                            .addComponent(btnLogin)
                            .addGap(26, 26, 26)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(lblForgetpass)
                        .addGap(85, 85, 85))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(jLabel1)
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(lblForgetpass)
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLogin2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addComponent(jLabel4)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtUsernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUsernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUsernameActionPerformed

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        if (JOptionPane.showConfirmDialog(this, "Bạn Muốn kết thúc ứng dụng?") == 0) {
            System.exit(0);

        }
    }//GEN-LAST:event_btnLoginActionPerformed

    private void btnLogin1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogin1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLogin1ActionPerformed

    private void btnLogin2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogin2ActionPerformed
        txtUsername.setBackground(white);
        txtPassword.setBackground(white);
        if (txtUsername.getText().trim().length() > 0) {
            if (txtPassword.getPassword().length > 0) {
                dangNhap();
            } else {
                txtPassword.setBackground(pink);
                ThongBao.alert(this, "Không được để trống tên mật khẩu");
            }
        } else {
            txtUsername.setBackground(pink);
            ThongBao.alert(this, "Không được để trống tên đăng nhập");
        }
    }//GEN-LAST:event_btnLogin2ActionPerformed

    private void lblForgetpassMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblForgetpassMouseClicked
        // TODO add your handling code here:
        GUI_ForgetPassword1 rs = new GUI_ForgetPassword1();
        rs.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_lblForgetpassMouseClicked

    private void btnLogin2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnLogin2KeyPressed

    }//GEN-LAST:event_btnLogin2KeyPressed

    private void txtPasswordKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPasswordKeyTyped

    }//GEN-LAST:event_txtPasswordKeyTyped

    private void btnLogin2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnLogin2KeyTyped

    }//GEN-LAST:event_btnLogin2KeyTyped

    private void txtPasswordKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPasswordKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnLogin2ActionPerformed(null);
        }
    }//GEN-LAST:event_txtPasswordKeyPressed

    private void txtUsernameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUsernameKeyPressed
        // TODO add your handling code here:
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnLogin2ActionPerformed(null);
        }
    }//GEN-LAST:event_txtUsernameKeyPressed

    private void lblForgetpassMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblForgetpassMouseEntered
        lblForgetpass.setForeground(Color.GREEN);
    }//GEN-LAST:event_lblForgetpassMouseEntered

    private void lblForgetpassMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblForgetpassMouseExited
        lblForgetpass.setForeground(Color.BLACK);
    }//GEN-LAST:event_lblForgetpassMouseExited
    void dangNhap() {
        try {
            int tb = 0;
            String user = txtUsername.getText();
            String pass = new String(txtPassword.getPassword());
            ENTITY_ADMIN ad = this.ad.findById(user);
            ENTITY_Employee nv = dao.findById(user);

            if (nv == null) {
                if (ad == null) {
                    tb++;
                } else if (!pass.equals(ad.getPassword())) {
                    tb++;
                } else {
                    Auth.admin = ad;
                    ThongBao.alert(this, "Đăng nhập thành công");
                    this.dispose();
                }
            } else {
                Auth.user = nv;
                if (!pass.equals(nv.getPassword())) {
                    tb++;
                } else {
                    ThongBao.alert(this, "Đăng nhập thành công");
                    this.dispose();
                }
            }
            if (tb > 0) {
                ThongBao.alert(this, "Tài khoản hoặc mật khẩu không chính xác");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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
            java.util.logging.Logger.getLogger(GUI_Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI_Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI_Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI_Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                GUI_Login dialog = new GUI_Login(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLogin;
    private javax.swing.JButton btnLogin1;
    private javax.swing.JButton btnLogin2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblForgetpass;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}
