/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI2;

import BUS_IServices.IQLTable_Service;
import BUS_Services.QLTable_Service;
import DAL_Models.ENTITY_Area;
import DAL_Models.ENTITY_Promotion;
import DAL_Models.ENTITY_Table;
import DAL_Services.Area_Service;
import DAL_Services.Promotion_Service;
import DAL_Services.Table_Service;
import Utils.Check;
import Utils.ThongBao;
import Utils.dateHelper;
import Utils.dialogHelper;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author PC
 */
public class GUI_Promotions extends javax.swing.JPanel {

    Area_Service khu;
    Table_Service tbdao;
    IQLTable_Service daotb;

    Table_Service ban = new Table_Service();
    JPopupMenu menu = new JPopupMenu("Popup");
    Promotion_Service dao = new Promotion_Service();
    int row = -1;

    /**
     * Creates new form GUI_Promotions
     */
    public GUI_Promotions() {
        initComponents();
        tblGiamgia.getColumnModel().getColumn(0).setMinWidth(0);
        tblGiamgia.getColumnModel().getColumn(0).setMaxWidth(0);
        init();
//        lblID.setVisible(false);
    }

    void init() {
//        filltoTable();
//        clearForm();
        tbdao = new Table_Service();
        daotb = new QLTable_Service();
        khu = new Area_Service();
        fillComboBoxKhu();
        txtMaBan.setEditable(false);
        daotb.taoIDTable(txtMaBan);
        daotb.fillTable(tblTable);
        lblID.setVisible(false);
        this.xoaform();
    }
//--------------------------------------------------------------------------------------------

    private void fillComboBoxKhu() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) this.cbbKhu.getModel();
        model.removeAllElements();
        List<ENTITY_Area> list = daotb.selectIDArea();

        for (ENTITY_Area cd : list) {
            model.addElement(cd);
        }
    }

    void xoaform() {
        this.txtViTri.setText("");
        this.txtTimKiem.setText("");
        this.cbbKhu.setSelectedIndex(0);
        daotb.taoIDTable(txtMaBan);
        btnThem.setEnabled(true);
        btnSua.setEnabled(false);
        btnXoa.setEnabled(false);
    }

    void deletetb() {
        try {
            this.row = tblTable.getSelectedRow();
            if (this.row >= 0) {
                daotb.deleteTABLE(txtMaBan.getText());
                daotb.fillTable(tblTable);
                xoaform();
                ThongBao.alert(this, "Xóa thành công");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void inserttb() {
        try {
            ENTITY_Table tbl = getModeltb();
            daotb.insertMATABLE(tbl);
            load();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    ENTITY_Table getModeltb() {
        ENTITY_Table tbl = new ENTITY_Table();
        ENTITY_Area tt = (ENTITY_Area) cbbKhu.getSelectedItem();
        tbl.setLocation(Integer.valueOf(txtViTri.getText()));
        tbl.setIDArea(tt.getIDArea());
        System.out.println("" + tt.getIDArea());
        tbl.setIDTable(txtMaBan.getText());
        return tbl;
    }

    void edittb() {
        try {
            String maTB = (String) tblTable.getValueAt(this.row, 0);
            ENTITY_Table ban = this.ban.findById(maTB);
            if (ban != null) {
                this.setformtb(ban);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void setformtb(ENTITY_Table tb) {
        String ma = (String) tblTable.getValueAt(row, 1);
        List<ENTITY_Area> c = khu.findById(ma);
        for (ENTITY_Area e : c) {
            cbbKhu.getModel().setSelectedItem(e);
            String maTB = (String) tblTable.getValueAt(this.row, 0);
            txtMaBan.setText(maTB);
            txtViTri.setText(String.valueOf(tb.getLocation()));
        }

    }

    void updatetb() {
        try {
            ENTITY_Table tbl = getModeltb();
            daotb.updatetTABLE(tbl);
            daotb.fillTable(tblTable);
            xoaform();
            ThongBao.alert(this, "Cập nhập thành công");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void filltoTable() {
        DefaultTableModel model = (DefaultTableModel) tblGiamgia.getModel();
        model.setRowCount(0);
        try {
            List<ENTITY_Promotion> list = dao.select();
            for (ENTITY_Promotion pro : list) {
                String t = "";
                if (pro.getStatus() == 1) {
                    t = "Đang diễn ra";
                } else if (pro.getStatus() == 0) {
                    t = "Đã kết thúc";
                } else if (pro.getStatus() == 2) {
                    t = "";
                }
                Object[] row = {
                    pro.getIDPro(),
                    pro.getName(),
                    pro.getDiscountPromo() + "%",
                    dateHelper.dateToString(pro.getStartPromo(), "dd/MM/yyyy"),
                    dateHelper.dateToString(pro.getEndPromo(), "dd/MM/yyyy"),
                    t};
                model.addRow(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
            dialogHelper.alert(this, "Lỗi truy Vấn dữ liệu");
        }
    }

    void setForm(ENTITY_Promotion pro) {
        lblID.setText(String.valueOf(pro.getIDPro()));
        txtName.setText(pro.getName());
        txtChietKhau.setText(String.valueOf(pro.getDiscountPromo()));
        txtNgayBatdau.setDate(pro.getStartPromo());
        txtNgayKetThuc.setDate(pro.getEndPromo());
        txtMoTa.setText(pro.getDescription());
    }

    ENTITY_Promotion getform() {
        ENTITY_Promotion pro = new ENTITY_Promotion();
        pro.setIDPro(Integer.valueOf(lblID.getText()));
        pro.setName(txtName.getText());
        pro.setDiscountPromo(Integer.valueOf(txtChietKhau.getText()));
        pro.setStartPromo(txtNgayBatdau.getDate());
        pro.setEndPromo(txtNgayKetThuc.getDate());
        pro.setDescription(txtMoTa.getText());
        return pro;
    }

    void clearForm() {
        ENTITY_Promotion pro = new ENTITY_Promotion();
        pro.setStartPromo(dateHelper.now());
        pro.setEndPromo(dateHelper.add(15));
        this.setForm(pro);
        this.row = -1;
        jButton1.setEnabled(true);
        jButton2.setEnabled(false);
        jButton3.setEnabled(false);
    }

    void edit() {
        try {
            int ID = (int) tblGiamgia.getValueAt(row, 0);
            ENTITY_Promotion pro = dao.findById(String.valueOf(ID));
            if (pro != null) {
                this.setForm(pro);
            }
        } catch (Exception e) {
            e.printStackTrace();
            dialogHelper.alert(this, "Lỗi Rồi DM");
        }

    }

    void insert() {
        ENTITY_Promotion pro = this.getform();
        try {
            dao.insert(pro);
            filltoTable();
            dialogHelper.alert(this, "A! Thành Công Rồi");
            clearForm();
        } catch (Exception e) {
            dialogHelper.alert(this, "Thất Bại :D");
            e.printStackTrace();
        }
    }

    void update() {
        ENTITY_Promotion pro = this.getform();
        try {
            dao.update(pro);
            filltoTable();
            dialogHelper.alert(this, "OK");
            clearForm();
        } catch (Exception e) {
            dialogHelper.alert(this, "Thất Bại :D");
            e.printStackTrace();
        }
    }

    void delete() {
        String ID = lblID.getText();
        dialogHelper.confirm(this, "Đừng xóa em mà");
        try {
            dao.delete(ID);
            filltoTable();
            dialogHelper.alert(this, "Xóa cc");
            clearForm();
        } catch (Exception e) {
            dialogHelper.alert(this, "Thất Bại :D");
            e.printStackTrace();
        }

    }

    void khoiphuc() {
        this.row = tblGiamgia.getSelectedRow();
        if (this.row >= 0) {
            int ID = (int) tblGiamgia.getValueAt(this.row, 0);
            ENTITY_Promotion pro = dao.findById(String.valueOf(ID));
            dialogHelper.confirm(this, "Mày Thích Khôi Phục Không?");
            if (pro.getStatus() == 1) {
                dialogHelper.alert(this, "Chương Trình đang diễn ra Khôi Phục cc");
            } else {
                try {
                    dao.khoiphuc(String.valueOf(ID));
                    filltoTable();
                    dialogHelper.alert(this, "Khôi Phục Thành Công");
                } catch (Exception e) {
                    dialogHelper.alert(this, "Thất Bại");
                    e.printStackTrace();
                }
            }
        }
    }

    void xoa() {
        this.row = tblGiamgia.getSelectedRow();
        if (this.row >= 0) {
            int ID = (int) tblGiamgia.getValueAt(this.row, 0);
            ENTITY_Promotion pro = dao.findById(String.valueOf(ID));
            dialogHelper.confirm(this, "Bạn muốn Kết thúc Chương Trình Giảm Giá này?");
            if (pro.getStatus() == 0) {
                dialogHelper.alert(this, "Chương Trình đã kết thúc từ trước");
            } else {
                try {
                    dao.delete(String.valueOf(ID));
                    filltoTable();
                    dialogHelper.alert(this, "Đã kết thúc");
                } catch (Exception e) {
                    dialogHelper.alert(this, "Thất Bại");
                    e.printStackTrace();
                }
            }
        }
    }

    public void Test() {
        menu.removeAll();
        JMenuItem item = new JMenuItem("Khôi Phục");
        JMenuItem item1 = new JMenuItem("Kết Thúc Chương Trình");
        item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                khoiphuc();
            }
        });
        menu.add(item);
        item1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xoa();
            }

        });
        menu.add(item1);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpTB = new javax.swing.JPanel();
        btnThem = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblTable = new javax.swing.JTable();
        txtMaBan = new javax.swing.JTextField();
        txtViTri = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtTimKiem = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        cbbKhu = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        rSButtonIconD1 = new rojerusan.RSButtonIconD();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtMoTa = new javax.swing.JTextArea();
        txtNgayKetThuc = new com.toedter.calendar.JDateChooser();
        txtNgayBatdau = new com.toedter.calendar.JDateChooser();
        lblID = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        txtChietKhau = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblGiamgia = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 153, 153));

        jpTB.setBackground(new java.awt.Color(255, 153, 153));
        jpTB.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jpTB.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jpTBMouseClicked(evt);
            }
        });

        btnThem.setBackground(new java.awt.Color(0, 102, 51));
        btnThem.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/add-item.png"))); // NOI18N
        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnClear.setBackground(new java.awt.Color(0, 102, 51));
        btnClear.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btnClear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/clear_item.png"))); // NOI18N
        btnClear.setText("Clear");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        btnXoa.setBackground(new java.awt.Color(0, 102, 51));
        btnXoa.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btnXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/delete_item.png"))); // NOI18N
        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnSua.setBackground(new java.awt.Color(0, 102, 51));
        btnSua.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btnSua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/edit_item.png"))); // NOI18N
        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel9.setText("Mã bàn");

        jLabel10.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel10.setText("Vị Trí");

        jLabel11.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel11.setText("Tìm Kiếm");

        tblTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Bàn", "Khu", "Vị Trí", "Tình Trạng"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblTableMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblTableMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tblTableMouseReleased(evt);
            }
        });
        jScrollPane3.setViewportView(tblTable);

        txtViTri.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtViTriActionPerformed(evt);
            }
        });

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/tu1.png"))); // NOI18N

        txtTimKiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTimKiemKeyTyped(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(255, 153, 153));
        jPanel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel2MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel2MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jPanel2MouseReleased(evt);
            }
        });

        cbbKhu.setEditable(true);
        cbbKhu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3" }));
        cbbKhu.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        cbbKhu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbbKhuMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                cbbKhuMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cbbKhuMouseReleased(evt);
            }
        });
        cbbKhu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbKhuActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel13.setText("Khu");

        rSButtonIconD1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/add-cart.png"))); // NOI18N
        rSButtonIconD1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonIconD1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                .addComponent(cbbKhu, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(rSButtonIconD1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rSButtonIconD1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel13)
                        .addComponent(cbbKhu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jpTBLayout = new javax.swing.GroupLayout(jpTB);
        jpTB.setLayout(jpTBLayout);
        jpTBLayout.setHorizontalGroup(
            jpTBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpTBLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpTBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jpTBLayout.createSequentialGroup()
                        .addGroup(jpTBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpTBLayout.createSequentialGroup()
                                .addGroup(jpTBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jpTBLayout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addGroup(jpTBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jpTBLayout.createSequentialGroup()
                                                .addComponent(jLabel9)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(txtMaBan, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jpTBLayout.createSequentialGroup()
                                                .addComponent(jLabel11)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(jpTBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(txtViTri)
                                                    .addComponent(txtTimKiem, javax.swing.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE))))))
                                .addGap(110, 110, 110)
                                .addGroup(jpTBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnThem)
                                    .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jpTBLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel10)))
                        .addContainerGap(228, Short.MAX_VALUE))))
            .addGroup(jpTBLayout.createSequentialGroup()
                .addGap(94, 94, 94)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 475, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jpTBLayout.setVerticalGroup(
            jpTBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpTBLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jpTBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpTBLayout.createSequentialGroup()
                        .addGroup(jpTBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(txtMaBan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpTBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(txtViTri, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jpTBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jpTBLayout.createSequentialGroup()
                        .addComponent(btnThem)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnSua)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnXoa)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnClear)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 75, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 581, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel1.setBackground(new java.awt.Color(255, 153, 153));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 0, 0));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/giamgia.png"))); // NOI18N

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel3.setText("Tên Chương Trình");

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel4.setText("Chiết Khấu (%)");

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel5.setText("Ngày Bắt Đầu");

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel6.setText("Ngày Kết Thúc");

        jLabel7.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel7.setText("Mô Tả");

        txtMoTa.setColumns(20);
        txtMoTa.setRows(5);
        jScrollPane1.setViewportView(txtMoTa);

        lblID.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        lblID.setForeground(new java.awt.Color(255, 0, 51));
        lblID.setText("00");

        txtChietKhau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtChietKhauActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(0, 102, 51));
        jButton1.setText("Thêm");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(0, 102, 51));
        jButton2.setText("Sửa");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(0, 102, 51));
        jButton3.setText("Xóa");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(0, 102, 51));
        jButton4.setText("Clear");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        tblGiamgia.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Mã", "Tên Chương Trình", "Chiết Khấu", "Ngày Bắt Đầu", "Ngày Kết Thúc", "Trạng Thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblGiamgia.setRowHeight(25);
        tblGiamgia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblGiamgiaMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblGiamgiaMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tblGiamgiaMouseReleased(evt);
            }
        });
        jScrollPane2.setViewportView(tblGiamgia);

        jLabel8.setFont(new java.awt.Font("Dialog", 1, 17)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(102, 51, 255));
        jLabel8.setText("%");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(432, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jButton3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 821, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addGap(41, 41, 41)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtNgayKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(txtChietKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel8))
                                            .addComponent(txtNgayBatdau, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(lblID)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel1))
                                            .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addComponent(jLabel6)
                                .addComponent(jLabel5))
                            .addGap(0, 26, Short.MAX_VALUE)))
                    .addContainerGap()))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(143, 143, 143)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(118, 118, 118)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2)
                        .addGap(18, 18, 18)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(42, 42, 42)
                            .addComponent(lblID)
                            .addGap(36, 36, 36))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(47, 47, 47)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(txtChietKhau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(47, 47, 47)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(txtNgayBatdau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(53, 53, 53)
                            .addComponent(txtNgayKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel5)
                            .addGap(53, 53, 53)
                            .addComponent(jLabel6)))
                    .addGap(18, 18, 18)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 583, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jButton1, jButton2});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jpTB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jpTB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (Check.checkNullText(txtName)
                && Check.checkNullText(txtChietKhau)) {
            if (Check.checkName(txtName)) {
                insert();
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        if (Check.checkNullText(txtName)
                && Check.checkNullText(txtChietKhau)) {
            if (Check.checkName(txtName)) {
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

    private void tblGiamgiaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblGiamgiaMouseClicked
        try {
            if (evt.getClickCount() == 1) {
                this.row = tblGiamgia.getSelectedRow();
                if (this.row >= 0) {
                    edit();
                    jButton1.setEnabled(false);
                    jButton2.setEnabled(true);
                    jButton3.setEnabled(true);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            dialogHelper.alert(this, "Lỗi :<");
        }
        Test();
    }//GEN-LAST:event_tblGiamgiaMouseClicked

    private void tblGiamgiaMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblGiamgiaMouseReleased
        if (evt.isPopupTrigger()) {
            menu.show(evt.getComponent(), evt.getX(), evt.getY());
        }
    }//GEN-LAST:event_tblGiamgiaMouseReleased

    private void tblGiamgiaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblGiamgiaMousePressed
        if (evt.isPopupTrigger()) {
            menu.show(evt.getComponent(), evt.getX(), evt.getY());
        }
    }//GEN-LAST:event_tblGiamgiaMousePressed

    private void txtChietKhauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtChietKhauActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtChietKhauActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        if (Check.checkNullText(txtViTri)) {
            if (Check.checkTable(txtViTri.getText())) {
        ENTITY_Area tt = (ENTITY_Area) cbbKhu.getSelectedItem();
                int vt = (Integer.parseInt(txtViTri.getText()));
                List<ENTITY_Table> list = tbdao.select_viTri(vt,tt.getIDArea());
                if (list.size() == 0) {
                    if (ThongBao.comfirm(this, "Bạn chắc chắn muốn thêm")) {

                        inserttb();
                    }
                } else {
                    List<ENTITY_Table> ct = tbdao.select_CheckTrung(vt);
                 //   ENTITY_Area tt = (ENTITY_Area) cbbKhu.getSelectedItem();
                    if (ct.size() == 0) {
                        if (ThongBao.comfirm(this, "Bạn chắc chắn muốn thêm")) {

                            tbdao.update_trung(String.valueOf(tt.getIDArea()), vt);
                            load();
                        }
                    } else {
                        ThongBao.alert(this, "Vị trí đã tồn tại");
                    }
                }
            }

        }

    }//GEN-LAST:event_btnThemActionPerformed
    void load() {
        daotb.fillTable(tblTable);
        xoaform();
        ThongBao.alert(this, "Thêm thành công");
    }
    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        xoaform();
        daotb.fillTable(tblTable);
    }//GEN-LAST:event_btnClearActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        if (ThongBao.comfirm(this, "Bạn chắc chắn muốn xóa bàn này")) {
            deletetb();

        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        if (Check.checkNullText(txtViTri)) {
            if (Check.checkTable(txtViTri.getText())) {
                if (ThongBao.comfirm(this, "Bạn chắc chắn muốn sửa")) {
                    updatetb();
                }
            }
        }
    }//GEN-LAST:event_btnSuaActionPerformed

    private void tblTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTableMouseClicked
        if (evt.getClickCount() == 2) {
            this.row = tblTable.getSelectedRow();
            if (this.row >= 0) {
//            ENTITY_Area dd = (ENTITY_Area) cbbKhu.getSelectedItem();
//            System.out.println(""+dd.getIDArea());
                this.edittb();

                //   dao.fillTableIDArea(tblTable, String.valueOf(t.getIDArea()));
                btnThem.setEnabled(false);
                btnSua.setEnabled(true);
                btnXoa.setEnabled(true);
            }
        }
        //Test();
    }//GEN-LAST:event_tblTableMouseClicked

    private void tblTableMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTableMousePressed
        // TODO add your handling code here:
        //        if (evt.isPopupTrigger()) {
        //            menu.show(evt.getComponent(), evt.getX(), evt.getY());
        //        }
    }//GEN-LAST:event_tblTableMousePressed

    private void tblTableMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTableMouseReleased
        // TODO add your handling code here:
        if (evt.isPopupTrigger()) {
            menu.show(evt.getComponent(), evt.getX(), evt.getY());
        }
    }//GEN-LAST:event_tblTableMouseReleased

    private void txtViTriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtViTriActionPerformed

    }//GEN-LAST:event_txtViTriActionPerformed

    private void txtTimKiemKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKeyTyped
        daotb.fillTableByID(tblTable, txtTimKiem);
    }//GEN-LAST:event_txtTimKiemKeyTyped

    private void cbbKhuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbbKhuMouseClicked
        //  xoaKhu();
    }//GEN-LAST:event_cbbKhuMouseClicked

    private void cbbKhuMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbbKhuMousePressed
        // TODO add your handling code here:
        //        if (evt.isPopupTrigger()) {
        //            menu.show(evt.getComponent(), evt.getX(), evt.getY());
        //        }
    }//GEN-LAST:event_cbbKhuMousePressed

    private void cbbKhuMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbbKhuMouseReleased
        //        // TODO add your handling code here:
        //        if (evt.isPopupTrigger()) {
        //            menu.show(evt.getComponent(), evt.getX(), evt.getY());
        //        }
    }//GEN-LAST:event_cbbKhuMouseReleased

    private void cbbKhuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbKhuActionPerformed
        // xoaKhu();
    }//GEN-LAST:event_cbbKhuActionPerformed

    private void rSButtonIconD1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonIconD1ActionPerformed

        String khu = JOptionPane.showInputDialog(this, "Nhập khu bạn muốn thêm");
        List<ENTITY_Area> list = this.khu.findById(khu);
        if (list.size() == 0) {
            if (khu != null) {
                if (ThongBao.comfirm(this, "Bạn chắc chắn muốn thêm")) {

                    this.khu.insert(khu);
                    fillComboBoxKhu();
                    ThongBao.alert(this, "Thêm khu thành công!");
                }
            }
        } else {
            ThongBao.alert(this, "Khu đã tồn tại");
        }
    }//GEN-LAST:event_rSButtonIconD1ActionPerformed

    private void jPanel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MouseClicked
        // TODO add your handling code here:
        if (ThongBao.comfirm(this, "Bạn chắc chắn muốn xóa khu này")) {
            xoaKhu();
        }
    }//GEN-LAST:event_jPanel2MouseClicked
    public void xoaKhu() {
        menu.removeAll();
        JMenuItem item = new JMenuItem("Xóa khu");
        item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                ENTITY_Area tt = (ENTITY_Area) cbbKhu.getSelectedItem();
                if (tt != null) {
                    try {
                        khu.delete(String.valueOf(tt.getIDArea()));
                        System.out.println("" + tt.getIDArea());
                        fillComboBoxKhu();
                        ThongBao.alert(null, "Xóa thành công");
                    } catch (SQLException ex) {
                        ThongBao.alert(null, "Chưa cho chưa xóa những khu đã có hóa đơn, đợi bản cập nhập sau =]]");
                        // Logger.getLogger(GUI_Table.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            }
        });
        menu.add(item);
    }
    private void jPanel2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MousePressed
        // TODO add your handling code here:
        if (evt.isPopupTrigger()) {
            menu.show(evt.getComponent(), evt.getX(), evt.getY());
        }
    }//GEN-LAST:event_jPanel2MousePressed

    private void jPanel2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MouseReleased
        // TODO add your handling code here:
        if (evt.isPopupTrigger()) {
            menu.show(evt.getComponent(), evt.getX(), evt.getY());
        }
    }//GEN-LAST:event_jPanel2MouseReleased

    private void jpTBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpTBMouseClicked

    }//GEN-LAST:event_jpTBMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JComboBox<String> cbbKhu;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JPanel jpTB;
    private javax.swing.JLabel lblID;
    private rojerusan.RSButtonIconD rSButtonIconD1;
    private javax.swing.JTable tblGiamgia;
    private javax.swing.JTable tblTable;
    private javax.swing.JTextField txtChietKhau;
    private javax.swing.JTextField txtMaBan;
    private javax.swing.JTextArea txtMoTa;
    private javax.swing.JTextField txtName;
    private com.toedter.calendar.JDateChooser txtNgayBatdau;
    private com.toedter.calendar.JDateChooser txtNgayKetThuc;
    private javax.swing.JTextField txtTimKiem;
    private javax.swing.JTextField txtViTri;
    // End of variables declaration//GEN-END:variables
}
