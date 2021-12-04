/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI2;

import BUS_IServices.IQLStatistical_Service;
import BUS_Services.QLStatistical_Service;
import GUI_Dialog.GUI_Login;
import GUI_Dialog.GUI_ResetPassword;
import Utils.Auth;
import Utils.ThongBao;
import Utils.XImage;
import Utils.dateHelper;
import Utils.dialogHelper;
import java.awt.Desktop;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author phamd
 */
public class GUI_MAIN extends javax.swing.JFrame {

    IQLStatistical_Service daotk;

    /**
     * Creates new form GUI_MAIN
     */
    public GUI_MAIN() {
        initComponents();
        setExtendedState(JFrame.MAXIMIZED_BOTH);    //cho toàn màn hình
        init();
    }

    void init() {
        setIconImage(XImage.APP_ICON); //đặt icon góc trên trái
        startDongHo();
        new GUI_Login(this, true).setVisible(true);
        if (Auth.isLogin()) {
            lblUsser.setText(Auth.user.getNameEMP());
        } else if (Auth.isAdmin()) {
            lblUsser.setText(Auth.admin.getUsername());
        } else {
            lblUsser.setText("Chưa đăng nhập");
        }
        daotk = new QLStatistical_Service();
    }

    void startDongHo() {
        new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date now = new Date();
                SimpleDateFormat format = new SimpleDateFormat("hh:mm:ss aa");
                String text = format.format(now);
                lblDH.setText(text);
            }
        }).start();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem4 = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JSeparator();
        From = new javax.swing.JPanel();
        lblDH = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        Fromch = new javax.swing.JPanel();
        Home = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        lblUsser = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        lblFacebook = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        btnOrder = new rojerusan.RSButtonIconD();
        btnBan = new rojerusan.RSButtonIconD();
        btnThongKe = new rojerusan.RSButtonIconD();
        btnNhanVien = new rojerusan.RSButtonIconD();
        btnSanPham = new rojerusan.RSButtonIconD();
        btnKhachHang = new rojerusan.RSButtonIconD();
        btnHome = new rojerusan.RSButtonIconD();
        btnThoat = new rojerusan.RSButtonIconD();
        rSButtonIconD1 = new rojerusan.RSButtonIconD();
        rSButtonIconD2 = new rojerusan.RSButtonIconD();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jSeparator6 = new javax.swing.JPopupMenu.Separator();
        jMenuItem5 = new javax.swing.JMenuItem();
        jSeparator8 = new javax.swing.JPopupMenu.Separator();
        jMenuItem2 = new javax.swing.JMenuItem();
        jSeparator7 = new javax.swing.JPopupMenu.Separator();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();

        jMenuItem4.setText("jMenuItem4");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Hệ thống quản lý quán Coffee Sanfrancis");

        From.setBackground(new java.awt.Color(255, 204, 255));

        lblDH.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblDH.setForeground(new java.awt.Color(51, 102, 0));
        lblDH.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/Alarm.png"))); // NOI18N
        lblDH.setText("00:00:00");

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/Button-Info-icon.png"))); // NOI18N
        jLabel3.setMaximumSize(new java.awt.Dimension(20, 20));
        jLabel3.setMinimumSize(new java.awt.Dimension(20, 20));
        jLabel3.setPreferredSize(new java.awt.Dimension(20, 20));

        Fromch.setBackground(new java.awt.Color(0, 51, 204));
        Fromch.setMaximumSize(new java.awt.Dimension(1800, 1000));
        Fromch.setMinimumSize(new java.awt.Dimension(1800, 1000));
        Fromch.setLayout(new javax.swing.BoxLayout(Fromch, javax.swing.BoxLayout.LINE_AXIS));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/cafe_main.gif"))); // NOI18N

        javax.swing.GroupLayout HomeLayout = new javax.swing.GroupLayout(Home);
        Home.setLayout(HomeLayout);
        HomeLayout.setHorizontalGroup(
            HomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1920, Short.MAX_VALUE)
            .addGroup(HomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(HomeLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel2)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        HomeLayout.setVerticalGroup(
            HomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 995, Short.MAX_VALUE)
            .addGroup(HomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(HomeLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 995, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        Fromch.add(Home);

        lblUsser.setBackground(new java.awt.Color(204, 0, 51));
        lblUsser.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblUsser.setForeground(new java.awt.Color(204, 0, 0));
        lblUsser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/Users.png"))); // NOI18N
        lblUsser.setText("Người Dùng");

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 255));
        jLabel1.setText("Hệ thống quản lý ");
        jLabel1.setMaximumSize(new java.awt.Dimension(100, 24));

        lblFacebook.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblFacebook.setForeground(new java.awt.Color(0, 51, 153));
        lblFacebook.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/facebook.png"))); // NOI18N
        lblFacebook.setText("Facebook");
        lblFacebook.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblFacebookMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout FromLayout = new javax.swing.GroupLayout(From);
        From.setLayout(FromLayout);
        FromLayout.setHorizontalGroup(
            FromLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FromLayout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(83, 83, 83)
                .addComponent(lblFacebook, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(99, 99, 99)
                .addComponent(lblUsser)
                .addGap(291, 291, 291)
                .addComponent(lblDH)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(FromLayout.createSequentialGroup()
                .addComponent(Fromch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        FromLayout.setVerticalGroup(
            FromLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FromLayout.createSequentialGroup()
                .addComponent(Fromch, javax.swing.GroupLayout.PREFERRED_SIZE, 995, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(FromLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(FromLayout.createSequentialGroup()
                        .addGroup(FromLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblUsser)
                            .addComponent(lblFacebook)
                            .addComponent(lblDH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(FromLayout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        jPanel3.setBackground(new java.awt.Color(255, 153, 153));

        btnOrder.setBackground(new java.awt.Color(0, 102, 51));
        btnOrder.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/santa-icon.png"))); // NOI18N
        btnOrder.setText("Bán Hàng");
        btnOrder.setColorHover(new java.awt.Color(255, 0, 51));
        btnOrder.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        btnOrder.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnOrderMouseClicked(evt);
            }
        });
        btnOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOrderActionPerformed(evt);
            }
        });

        btnBan.setBackground(new java.awt.Color(0, 102, 51));
        btnBan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/candykane-icon.png"))); // NOI18N
        btnBan.setText("Bàn");
        btnBan.setColorHover(new java.awt.Color(255, 0, 51));
        btnBan.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        btnBan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBanActionPerformed(evt);
            }
        });

        btnThongKe.setBackground(new java.awt.Color(0, 102, 51));
        btnThongKe.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/bell-icon.png"))); // NOI18N
        btnThongKe.setText("Thống Kê");
        btnThongKe.setColorHover(new java.awt.Color(255, 0, 51));
        btnThongKe.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        btnThongKe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThongKeActionPerformed(evt);
            }
        });

        btnNhanVien.setBackground(new java.awt.Color(0, 102, 51));
        btnNhanVien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/christmas-tree-icon.png"))); // NOI18N
        btnNhanVien.setText("Nhân Viên");
        btnNhanVien.setColorHover(new java.awt.Color(255, 0, 51));
        btnNhanVien.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        btnNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNhanVienActionPerformed(evt);
            }
        });

        btnSanPham.setBackground(new java.awt.Color(0, 102, 51));
        btnSanPham.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/sock-icon.png"))); // NOI18N
        btnSanPham.setText("Sản Phẩm");
        btnSanPham.setColorHover(new java.awt.Color(255, 0, 51));
        btnSanPham.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        btnSanPham.setMaximumSize(new java.awt.Dimension(141, 41));
        btnSanPham.setMinimumSize(new java.awt.Dimension(141, 41));
        btnSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSanPhamActionPerformed(evt);
            }
        });

        btnKhachHang.setBackground(new java.awt.Color(0, 102, 51));
        btnKhachHang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/candles-icon.png"))); // NOI18N
        btnKhachHang.setText("K.Hàng");
        btnKhachHang.setColorHover(new java.awt.Color(255, 0, 51));
        btnKhachHang.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        btnKhachHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKhachHangActionPerformed(evt);
            }
        });

        btnHome.setBackground(new java.awt.Color(0, 102, 51));
        btnHome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/home-icon.png"))); // NOI18N
        btnHome.setText("Home");
        btnHome.setColorHover(new java.awt.Color(255, 0, 51));
        btnHome.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        btnHome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHomeActionPerformed(evt);
            }
        });

        btnThoat.setBackground(new java.awt.Color(0, 102, 51));
        btnThoat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/out.png"))); // NOI18N
        btnThoat.setText("Thoát");
        btnThoat.setColorHover(new java.awt.Color(255, 0, 51));
        btnThoat.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        btnThoat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThoatActionPerformed(evt);
            }
        });

        rSButtonIconD1.setBackground(new java.awt.Color(0, 102, 51));
        rSButtonIconD1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/deer-icon.png"))); // NOI18N
        rSButtonIconD1.setText("K.Mãi");
        rSButtonIconD1.setColorHover(new java.awt.Color(255, 0, 51));
        rSButtonIconD1.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        rSButtonIconD1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonIconD1ActionPerformed(evt);
            }
        });

        rSButtonIconD2.setBackground(new java.awt.Color(0, 102, 51));
        rSButtonIconD2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/christmass-ball-icon.png"))); // NOI18N
        rSButtonIconD2.setText("Hóa Đơn");
        rSButtonIconD2.setColorHover(new java.awt.Color(255, 0, 51));
        rSButtonIconD2.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        rSButtonIconD2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonIconD2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnOrder, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
            .addComponent(btnBan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnNhanVien, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnThongKe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnKhachHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnHome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnThoat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(rSButtonIconD1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(rSButtonIconD2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(btnOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rSButtonIconD2, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnThongKe, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rSButtonIconD1, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBan, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnHome, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnThoat, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jMenuBar1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jMenuBar1.setAlignmentX(0.0F);

        jMenu1.setText("Tài Khoản");

        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/man-icon.png"))); // NOI18N
        jMenuItem1.setText("Thông tin tài khoản");
        jMenuItem1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuItem1MouseClicked(evt);
            }
        });
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);
        jMenu1.add(jSeparator6);

        jMenuItem5.setText("Đăng nhập");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem5);
        jMenu1.add(jSeparator8);

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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(From, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(From, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnQLMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQLMenuActionPerformed
        GUI2.GUI_Menu table = new GUI2.GUI_Menu();
        goiPan(table);
    }//GEN-LAST:event_btnQLMenuActionPerformed

    private void btnQLVip2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQLVip2ActionPerformed
        GUI2.GUI_MAIN table = new GUI2.GUI_MAIN();
        table.setVisible(true);
    }//GEN-LAST:event_btnQLVip2ActionPerformed

    private void btnQLVip3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQLVip3ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btnQLVip3ActionPerformed

    private void btnOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOrderActionPerformed

        if (!Auth.isLogin() && !Auth.isAdmin()) {
            dialogHelper.alert(this, "Vui lòng đăng nhập");
        } else {
            GUI2.GUI_QL_Order table = new GUI2.GUI_QL_Order();
            goiPan(table);
        }
    }//GEN-LAST:event_btnOrderActionPerformed

    private void btnOrderMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnOrderMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_btnOrderMouseClicked

    private void btnThongKeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThongKeActionPerformed
        // TODO add your handling code here:

        if (!Auth.isLogin() && !Auth.isAdmin()) {
            dialogHelper.alert(this, "Vui lòng đăng nhập");
        } else {
            if (Auth.isAdmin()) {
                GUI2.GUI_Statistical_ThongKe table = new GUI2.GUI_Statistical_ThongKe();
                goiPan(table);
            } else if (Auth.isLogin()) {
                dialogHelper.alert(this, "Nhân viên không dùng được chức năng này");
            }
        }
    }//GEN-LAST:event_btnThongKeActionPerformed

    private void btnKhachHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKhachHangActionPerformed
        // TODO add your handling code here:

        if (!Auth.isLogin() && !Auth.isAdmin()) {
            dialogHelper.alert(this, "Vui lòng đăng nhập");
        } else {
            if (Auth.isAdmin()) {
                GUI2.GUI_Customer_KhachHang table = new GUI2.GUI_Customer_KhachHang();
                goiPan(table);
            } else if (Auth.isLogin()) {
                dialogHelper.alert(this, "Nhân viên không dùng được chức năng này");
            }
        }
    }//GEN-LAST:event_btnKhachHangActionPerformed

    private void btnNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNhanVienActionPerformed
        // TODO add your handling code here:

        if (!Auth.isLogin() && !Auth.isAdmin()) {
            dialogHelper.alert(this, "Vui lòng đăng nhập");
        } else {
            if (Auth.isAdmin()) {
                GUI2.GUI_Employee_NhanVien table = new GUI2.GUI_Employee_NhanVien();
                goiPan(table);
            } else if (Auth.isLogin()) {
                dialogHelper.alert(this, "Nhân viên không dùng được chức năng này");
            }
        }
    }//GEN-LAST:event_btnNhanVienActionPerformed

    private void btnSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSanPhamActionPerformed
        // TODO add your handling code here:

        if (!Auth.isLogin() && !Auth.isAdmin()) {
            dialogHelper.alert(this, "Vui lòng đăng nhập");
        } else {
            if (Auth.isAdmin()) {
                GUI2.GUI_Menu table = new GUI2.GUI_Menu();
                goiPan(table);
            } else if (Auth.isLogin()) {
                dialogHelper.alert(this, "Nhân viên không dùng được chức năng này");
            }
        }
    }//GEN-LAST:event_btnSanPhamActionPerformed

    private void btnBanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBanActionPerformed
        // TODO add your handling code here:

        if (!Auth.isLogin() && !Auth.isAdmin()) {
            dialogHelper.alert(this, "Vui lòng đăng nhập");
        } else {
            if (Auth.isAdmin()) {
                GUI2.GUI_Table table = new GUI2.GUI_Table();
                goiPan(table);
            } else if (Auth.isLogin()) {
                dialogHelper.alert(this, "Nhân viên không dùng được chức năng này");
            }
        }
    }//GEN-LAST:event_btnBanActionPerformed

    private void btnHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHomeActionPerformed
        // TODO add your handling code here:
        goiPan(Home);
    }//GEN-LAST:event_btnHomeActionPerformed

    private void btnThoatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThoatActionPerformed
        // TODO add your handling code here:
        if (JOptionPane.showConfirmDialog(this, "Bạn muốn đóng ứng dụng?") == 0) {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ss");
            LocalDateTime date1 = LocalDateTime.now();
            LocalDateTime date2 = LocalDateTime.parse("2021-12-04 14:00:00", dtf);
            if (date1.isAfter(date2)) {
                System.out.println("Ngon");
                daotk.guiBCNgay(dateHelper.now());
            }
            System.exit(0);
        }

    }//GEN-LAST:event_btnThoatActionPerformed

    private void rSButtonIconD1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonIconD1ActionPerformed
        // TODO add your handling code here:

        if (!Auth.isLogin() && !Auth.isAdmin()) {
            dialogHelper.alert(this, "Vui lòng đăng nhập");
        } else {
            if (Auth.isAdmin()) {
                GUI2.GUI_Promotions table = new GUI2.GUI_Promotions();
                goiPan(table);
            } else if (Auth.isLogin()) {
                dialogHelper.alert(this, "Nhân viên không dùng được chức năng này");
            }
        }
    }//GEN-LAST:event_rSButtonIconD1ActionPerformed

    private void rSButtonIconD2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonIconD2ActionPerformed
        // TODO add your handling code here:

        if (!Auth.isLogin() && !Auth.isAdmin()) {
            dialogHelper.alert(this, "Vui lòng đăng nhập");
        } else {
            if (Auth.isAdmin()) {
                GUI2.GUI_HoaDon t = new GUI_HoaDon();
                goiPan(t);
            } else if (Auth.isLogin()) {
                dialogHelper.alert(this, "Nhân viên không dùng được chức năng này");
            }
        }
    }//GEN-LAST:event_rSButtonIconD2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        if (ThongBao.comfirm(this, "Bạn chắc chắn muốn đăng xuất")) {
            Auth.clear();
            lblUsser.setText("Chưa đăng nhập");
            new GUI_Login(this, true).setVisible(true);
            if (Auth.isLogin()) {
                lblUsser.setText(Auth.user.getNameEMP());
            } else if (Auth.isAdmin()) {
                lblUsser.setText(Auth.admin.getUsername());
            } else {
                lblUsser.setText("Chưa đăng nhập");
            }
        }
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        if (!Auth.isLogin() && !Auth.isAdmin()) {
            dialogHelper.alert(this, "Vui lòng đăng nhập");
        } else {
            new GUI_ResetPassword(this, true).setVisible(true);
        }
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem1MouseClicked

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        if (!Auth.isLogin() && !Auth.isAdmin()) {
            dialogHelper.alert(this, "Vui lòng đăng nhập");
        } else {
            if (Auth.isAdmin()) {
                dialogHelper.alert(this, "Bạn là " + Auth.admin.getUsername() + " Đẹp Trai");
            } else if (Auth.isLogin()) {
                try {
                    Desktop.getDesktop().browse(URI.create("https://www.facebook.com/ilov3myou.hoangthong/"));
                } catch (IOException ex) {
                    Logger.getLogger(GUI_MAIN.class.getName()).log(Level.SEVERE, null, ex);
                }
                ImageIcon ok = new ImageIcon(new ImageIcon("logos/" + Auth.user.getImage()).getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
                JOptionPane.showMessageDialog(this, "Bạn là " + Auth.user.getUsernameEMP() + "\n" + "Họ Tên : " + Auth.user.getNameEMP(),
                        "Hệ thống quản lý Ƹ̵̡Ӝ̵̨̄Ʒ☆", JOptionPane.INFORMATION_MESSAGE, ok);
            }
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void lblFacebookMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblFacebookMouseClicked
        // TODO add your handling code here:
        try {
            Desktop.getDesktop().browse(URI.create("https://www.facebook.com/profile.php?id=100025310501929"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_lblFacebookMouseClicked

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        // TODO add your handling code here:
        if (!Auth.isLogin() && !Auth.isAdmin()) {
            GUI_Login l = new GUI_Login(this, true);
            l.setVisible(true);
            if (Auth.isLogin()) {
                lblUsser.setText(Auth.user.getNameEMP());

            } else {
                lblUsser.setText(Auth.admin.getUsername());
            }

        } else {
            ThongBao.alert(this, "Đã đăng nhập nếu muốn đăng nhập lại vui lòng đăng xuất trước");
        }
    }//GEN-LAST:event_jMenuItem5ActionPerformed

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
            java.util.logging.Logger.getLogger(GUI_MAIN.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI_MAIN.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI_MAIN.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI_MAIN.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
    private javax.swing.JPanel Home;
    private rojerusan.RSButtonIconD btnBan;
    private rojerusan.RSButtonIconD btnHome;
    private rojerusan.RSButtonIconD btnKhachHang;
    private rojerusan.RSButtonIconD btnNhanVien;
    private rojerusan.RSButtonIconD btnOrder;
    private rojerusan.RSButtonIconD btnSanPham;
    private rojerusan.RSButtonIconD btnThoat;
    private rojerusan.RSButtonIconD btnThongKe;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator6;
    private javax.swing.JPopupMenu.Separator jSeparator7;
    private javax.swing.JPopupMenu.Separator jSeparator8;
    private javax.swing.JLabel lblDH;
    private javax.swing.JLabel lblFacebook;
    private javax.swing.JLabel lblUsser;
    private rojerusan.RSButtonIconD rSButtonIconD1;
    private rojerusan.RSButtonIconD rSButtonIconD2;
    // End of variables declaration//GEN-END:variables
}
