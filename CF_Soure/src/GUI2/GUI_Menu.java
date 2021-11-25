/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI2;

import BUS_IServices.IQLMenu_Service;
import BUS_Models.SanPham;
import BUS_Services.QLMenu_Service;
import DAL_Models.ENTITY_ProductType;
import Utils.Check;
import Utils.ThongBao;
import Utils.XImage;
import Utils.dialogHelper;
import static java.awt.Color.pink;
import static java.awt.Color.white;
import java.awt.Menu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author phamd
 */
public class GUI_Menu extends javax.swing.JPanel {

    JPopupMenu menu = new JPopupMenu("Popup");
    int row = -1;
    JFileChooser fileChooser = new JFileChooser();
    private IQLMenu_Service qlsp;
    QLMenu_Service dao = new QLMenu_Service();

    /**
     * Creates new form GUI_Menu
     */
    public GUI_Menu() {
        initComponents();
        this.qlsp = (IQLMenu_Service) new QLMenu_Service();
//        dao.select();
        tblSanPham.getColumnModel().getColumn(0).setMinWidth(0);
        tblSanPham.getColumnModel().getColumn(0).setMaxWidth(0);
        lblType.setVisible(false);
        init();
//        dao.taoIDType(lblType);
    }

    void init() {
        dao.fillToTable(tblSanPham);
        dao.selectTypeName();
        dao.loadComboTypeName(cboLoai);
        dao.taoID(lblID);
        this.clear();
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
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lblHinh = new javax.swing.JLabel();
        txtTen = new javax.swing.JTextField();
        txtGia = new javax.swing.JTextField();
        cboLoai = new javax.swing.JComboBox<>();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnMoi = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblSanPham = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        btn1 = new javax.swing.JButton();
        btn3 = new javax.swing.JButton();
        btn2 = new javax.swing.JButton();
        btn4 = new javax.swing.JButton();
        txtTimKiem = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        cboSize = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lblID = new javax.swing.JLabel();
        lblType = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();

        jPanel1.setBackground(new java.awt.Color(0, 153, 153));

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel1.setText("Tên");

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel3.setText("Giá");

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel4.setText("Loại");

        lblHinh.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        lblHinh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblHinhMouseClicked(evt);
            }
        });

        cboLoai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cafe", "Nước canh", "Nước mắm" }));
        cboLoai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cboLoaiMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                cboLoaiMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cboLoaiMouseReleased(evt);
            }
        });
        cboLoai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboLoaiActionPerformed(evt);
            }
        });

        btnThem.setBackground(new java.awt.Color(0, 102, 51));
        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/add-item.png"))); // NOI18N
        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnSua.setBackground(new java.awt.Color(0, 102, 51));
        btnSua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/edit_item.png"))); // NOI18N
        btnSua.setText("Sửa ");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnXoa.setBackground(new java.awt.Color(0, 102, 51));
        btnXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/delete_item.png"))); // NOI18N
        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnMoi.setBackground(new java.awt.Color(0, 102, 51));
        btnMoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/clear_item.png"))); // NOI18N
        btnMoi.setText("Clear");
        btnMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMoiActionPerformed(evt);
            }
        });

        tblSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID SP", "Tên Sản Phẩm", "Size", "Giá", "Trạng Thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblSanPham.setRowHeight(25);
        tblSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSanPhamMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblSanPhamMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tblSanPhamMouseReleased(evt);
            }
        });
        jScrollPane2.setViewportView(tblSanPham);

        jLabel6.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/cuthong1.png"))); // NOI18N

        btn1.setBackground(new java.awt.Color(0, 102, 51));
        btn1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/first.png"))); // NOI18N

        btn3.setBackground(new java.awt.Color(0, 102, 51));
        btn3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/next.png"))); // NOI18N

        btn2.setBackground(new java.awt.Color(0, 102, 51));
        btn2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/back.png"))); // NOI18N
        btn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn2ActionPerformed(evt);
            }
        });

        btn4.setBackground(new java.awt.Color(0, 102, 51));
        btn4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/last.png"))); // NOI18N

        txtTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimKiemActionPerformed(evt);
            }
        });
        txtTimKiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTimKiemKeyPressed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel7.setText("Tìm kiếm");

        cboSize.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboSize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboSizeActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel5.setText("Size");

        jLabel8.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel8.setText("ID");

        lblID.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblID.setForeground(new java.awt.Color(51, 255, 51));
        lblID.setText("jLabel9");

        lblType.setText("jLabel9");

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/add-cart.png"))); // NOI18N
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/phahoa.gif"))); // NOI18N

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/phahoa.gif"))); // NOI18N

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/phaohoa2.gif"))); // NOI18N

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/phaohoa2.gif"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(391, 391, 391)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(5, 5, 5)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel3)
                                            .addComponent(jLabel1)
                                            .addComponent(jLabel8)
                                            .addComponent(jLabel4)))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(8, 8, 8)
                                        .addComponent(jLabel5)))
                                .addGap(61, 61, 61)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(txtGia, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(232, 232, 232))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(cboLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(lblType)
                                                .addGap(132, 132, 132)))
                                        .addComponent(lblHinh, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(cboSize, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(75, 75, 75)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblID)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 352, Short.MAX_VALUE)
                                        .addComponent(jLabel10)))))
                        .addGap(91, 91, 91))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(btn1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btn2)
                                        .addGap(18, 18, 18)
                                        .addComponent(btn3)
                                        .addGap(18, 18, 18)
                                        .addComponent(btn4))
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 797, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                            .addGap(95, 95, 95)
                                            .addComponent(jLabel7)
                                            .addGap(18, 18, 18)
                                            .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(284, 284, 284))))
                                .addGap(34, 34, 34)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnXoa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnThem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnMoi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addGap(80, 80, 80))))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(111, 111, 111)
                    .addComponent(jLabel9)
                    .addContainerGap(1429, Short.MAX_VALUE)))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(41, 41, 41)
                    .addComponent(jLabel11)
                    .addContainerGap(1459, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jLabel10)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(lblID))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(cboLoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4)))
                            .addComponent(lblType))
                        .addGap(29, 29, 29)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(36, 36, 36)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel2)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(lblHinh, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(cboSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel5))
                            .addGap(3, 3, 3)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(btn2)
                                .addComponent(btn1)
                                .addComponent(btn3)
                                .addComponent(btn4))
                            .addGap(30, 30, 30)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel7))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(btnThem)
                                    .addGap(33, 33, 33)
                                    .addComponent(btnSua)
                                    .addGap(37, 37, 37)
                                    .addComponent(btnXoa)
                                    .addGap(42, 42, 42)
                                    .addComponent(btnMoi))
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 371, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(68, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                    .addContainerGap(693, Short.MAX_VALUE)
                    .addComponent(jLabel9)
                    .addGap(64, 64, 64)))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(86, 86, 86)
                    .addComponent(jLabel11)
                    .addContainerGap(725, Short.MAX_VALUE)))
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

    private void lblHinhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHinhMouseClicked
        dao.chonAnh(lblHinh);
    }//GEN-LAST:event_lblHinhMouseClicked

    private void cboLoaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboLoaiActionPerformed

        if (cboLoai.getSelectedItem() != null) {
            if (!cboLoai.getSelectedItem().equals(cboLoai.getSelectedItem().toString())) {
                ENTITY_ProductType sp = (ENTITY_ProductType) cboLoai.getSelectedItem();
                lblType.setText("" + sp.getIDType());
                System.out.println("----" + sp.getIDType());
                if (sp != null) {
                    dao.loadComboSize(cboSize, String.valueOf(sp.getTypeName()), lblType);
                }
            }
        }

    }//GEN-LAST:event_cboLoaiActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        if (Check.checkNullText(txtTen)
                && Check.checkNullText(txtGia)) {
            if (Check.checkName(txtTen)) {
                SanPham sp = this.getForm();
                try {
                    dao.insert(sp);
                    dao.fillToTable(tblSanPham);
                    dialogHelper.alert(this, "A! Thành Công Rồi");
                    clear();
                    dao.taoID(lblID);
                } catch (Exception e) {
                    dialogHelper.alert(this, "Đã được đíu đâu mà thêm");
                    e.printStackTrace();
                }

            }
        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        if (Check.checkNullText(txtTen)
                && Check.checkNullText(txtGia)) {
            if (Check.checkName(txtTen)) {
                SanPham sp = getForm();
                try {
                    dao.updateSP(sp);
                    dao.fillToTable(tblSanPham);
                    clear();
                    dialogHelper.alert(this, "A! Thành Công Rồi");
                    dao.taoID(lblID);
                } catch (Exception e) {
                    dialogHelper.alert(this, "Bố Đíu cho Sửa đấy");
                    e.printStackTrace();
                }
            }
        }
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        String IDProduct = lblID.getText();
        SanPham sp = dao.findById(IDProduct);
        dialogHelper.confirm(this, "Mày Thích Xóa Không?");
        try {
            if (sp.isStatus() == false) {
                dialogHelper.alert(this, "Sản phẩm không sử dụng mà Pro");
            } else {
                dao.delete(IDProduct);
                dao.fillToTable(tblSanPham);
                dialogHelper.alert(this, "Xoá CMM");
                clear();
                dao.taoID(lblID);
            }
        } catch (Exception e) {
            dialogHelper.alert(this, "Bố éo cho Xóa");
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMoiActionPerformed
        clear();
    }//GEN-LAST:event_btnMoiActionPerformed

    private void tblSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamMouseClicked
        try {
            if (evt.getClickCount() == 2) {
                this.row = tblSanPham.getSelectedRow();
                System.out.println(row);
                if (this.row >= 0) {
                    this.edit();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Test();
    }//GEN-LAST:event_tblSanPhamMouseClicked

    private void btn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn2ActionPerformed

    private void txtTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKiemActionPerformed

    }//GEN-LAST:event_txtTimKiemActionPerformed

    private void txtTimKiemKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKeyPressed
        dao.timSP(txtTimKiem, tblSanPham);
    }//GEN-LAST:event_txtTimKiemKeyPressed

    private void cboSizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboSizeActionPerformed

        if (cboSize.getSelectedItem() != null) {
            if (!cboSize.getSelectedItem().equals(cboSize.getSelectedItem().toString())) {
                SanPham sp = (SanPham) cboSize.getSelectedItem();
                lblType.setText("" + sp.getIDType());
            }
        }
    }//GEN-LAST:event_cboSizeActionPerformed

    private void tblSanPhamMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamMousePressed
        if (evt.isPopupTrigger()) {
            menu.show(evt.getComponent(), evt.getX(), evt.getY());
        }
    }//GEN-LAST:event_tblSanPhamMousePressed

    private void tblSanPhamMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamMouseReleased
        if (evt.isPopupTrigger()) {
            menu.show(evt.getComponent(), evt.getX(), evt.getY());
        }
    }//GEN-LAST:event_tblSanPhamMouseReleased

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        GUI2.JDialog_LoaiSP table = new GUI2.JDialog_LoaiSP(null, true, cboLoai);
        table.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked

    }//GEN-LAST:event_jButton2MouseClicked

    private void cboLoaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cboLoaiMouseClicked
        this.row = tblSanPham.getSelectedRow();
        this.row = -1;
        Refresh();
    }//GEN-LAST:event_cboLoaiMouseClicked

    private void cboLoaiMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cboLoaiMousePressed
        if (evt.isPopupTrigger()) {
            menu.show(evt.getComponent(), evt.getX(), evt.getY());
        }
    }//GEN-LAST:event_cboLoaiMousePressed

    private void cboLoaiMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cboLoaiMouseReleased
        if (evt.isPopupTrigger()) {
            menu.show(evt.getComponent(), evt.getX(), evt.getY());
        }
    }//GEN-LAST:event_cboLoaiMouseReleased

    private void setForm(SanPham sp) {
        lblID.setText(sp.getIDProduct());
        txtTen.setText(sp.getProductName());
        txtGia.setText(String.valueOf(sp.getPrice()));
        cboLoai.getModel().setSelectedItem(sp.getTypeName());
        cboSize.getModel().setSelectedItem(sp.getSize());
        if (sp.getImage() != null) {
            lblHinh.setToolTipText(sp.getImage()); //lay ra ten file trong tooltip 
            lblHinh.setIcon(XImage.read(sp.getImage())); //doc file trong tooltip va hien thi len lable
        } else {
            lblHinh.setIcon(XImage.read("no_image.jpg"));
        }
        lblType.setText(String.valueOf(sp.getIDType()));
//       cboLoai.setToolTipText(String.valueOf(sp.getIDType()));
    }

    SanPham getForm() {
        SanPham sp = new SanPham();
        sp.setIDProduct(lblID.getText());
        sp.setProductName(txtTen.getText());
        sp.setPrice(Float.valueOf(txtGia.getText()));
        sp.setSize((String) cboSize.getSelectedItem().toString());
        sp.setTypeName((String) cboLoai.getSelectedItem().toString());
        sp.setImage(lblHinh.getToolTipText());
        sp.setIDType(Integer.valueOf(lblType.getText()));
        return sp;
    }

    public void Test() {
        menu.removeAll();
        //  List<ENTITY_Area> area = tbdao.selectIDArea();
        JMenuItem item = new JMenuItem("Xem chi tiết");
        JMenuItem item1 = new JMenuItem("Khôi Phục");
        JMenuItem item2 = new JMenuItem("Xóa");
        item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GUI2.JDialog_Xemchitiet table = new GUI2.JDialog_Xemchitiet(null, true, tblSanPham);
                table.setVisible(true);
            }
        });
        menu.add(item);
        item1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                khoiphuc();
            }

        });
        menu.add(item1);
        item2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xoa();
            }
        });
        menu.add(item2);
    }

    public void Refresh() {
        menu.removeAll();
        //  List<ENTITY_Area> area = tbdao.selectIDArea();
        JMenuItem item = new JMenuItem("Refresh");
        item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                init();
            }
        });
        menu.add(item);
    }

    void edit() {
        try {
            cboSize.removeAllItems();
            String ID = (String) tblSanPham.getValueAt(this.row, 0); //lay giá trị hàng hiện tại & cột 0
            SanPham sp = dao.findById(ID); //lay thong tin sp tuong ung trong csdl
//            cboSize.removeAllItems();
            if (sp != null) {
                this.setForm(sp);
            }
        } catch (Exception e) {
            dialogHelper.alert(this, "Lỗi");
            e.printStackTrace();
        }

    }

    void clear() {
        SanPham sp = new SanPham();
        this.setForm(sp);
        cboLoai.setSelectedIndex(0);
        lblHinh.setToolTipText(sp.getImage());
        lblHinh.setIcon(XImage.read("no_image.jpg"));
        dao.taoID(lblID);
        this.row = -1;
    }

    void khoiphuc() {
        this.row = tblSanPham.getSelectedRow();
        if (this.row >= 0) {
            String IDProduct = (String) tblSanPham.getValueAt(this.row, 0);
            SanPham sp = dao.findById(IDProduct);
            dialogHelper.confirm(this, "Mày Thích Khôi Phục Không?");
            if (sp.isStatus() == true) {
                dialogHelper.alert(this, "Sản Phẩm còn sử dụng Khôi Phục cc");
            } else {
                try {
                    dao.khoiphuc(IDProduct);
                    dao.fillToTable(tblSanPham);
                    dialogHelper.alert(this, "Khôi Phục Thành Công");
                } catch (Exception e) {
                    dialogHelper.alert(this, "Thất Bại");
                    e.printStackTrace();
                }
            }
        }
    }

    void xoa() {
        this.row = tblSanPham.getSelectedRow();
        if (this.row >= 0) {
            String IDProduct = (String) tblSanPham.getValueAt(this.row, 0);
            SanPham sp = dao.findById(IDProduct);
            dialogHelper.confirm(this, "Sản Phẩm Này Không Còn Sử Dụng?");
            if (sp.isStatus() == false) {
                dialogHelper.alert(this, "Sản Phẩm Không sử dụng Xóa cc");
            } else {
                try {
                    dao.delete(IDProduct);
                    dao.fillToTable(tblSanPham);
                    dialogHelper.alert(this, "Xóa Thành Công");
                } catch (Exception e) {
                    dialogHelper.alert(this, "Thất Bại");
                    e.printStackTrace();
                }
            }
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn1;
    private javax.swing.JButton btn2;
    private javax.swing.JButton btn3;
    private javax.swing.JButton btn4;
    private javax.swing.JButton btnMoi;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JComboBox<String> cboLoai;
    private javax.swing.JComboBox<String> cboSize;
    private javax.swing.JButton jButton2;
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
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblHinh;
    private javax.swing.JLabel lblID;
    private javax.swing.JLabel lblType;
    private javax.swing.JTable tblSanPham;
    private javax.swing.JTextField txtGia;
    private javax.swing.JTextField txtTen;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
