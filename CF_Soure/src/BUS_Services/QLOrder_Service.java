/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS_Services;

import BUS_IServices.*;
import BUS_Models.BanButtons;
import BUS_Models.LSOrder;
import BUS_Models.SPChiTiet;
import BUS_Models.billCTT;
import DAL_IServices.IArea_Service;
import DAL_IServices.IOrder_Service;
import DAL_IServices.IProduct_Service;
import DAL_IServices.ITable_Service;
import DAL_Models.ENTITY_Area;
import DAL_Models.ENTITY_BILL;
import DAL_Models.ENTITY_Order;
import DAL_Models.ENTITY_Product;
import DAL_Models.ENTITY_Table;
import DAL_Services.Area_Service;
import DAL_Services.Order_Service;
import DAL_Services.Product_Service;
import DAL_Services.Table_Service;
import Utils.JDBC;
import Utils.dateHelper;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Scrollbar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author phamd
 */
public class QLOrder_Service implements IQLOrder_Service {

    private final int ICON_WIDTH = 40;
    private final int ICON_HEIGHT = 40;
    private ITable_Service qlb;
    private IArea_Service qlk;
    private IProduct_Service lqp;
    private IOrder_Service qlo;
    private int dong;

    private Map<JButton, BanButtons> banButtonList = new HashMap<>();
    private JPanel pnlMain;
    private ArrayList<ENTITY_Table> listBan;
    private JButton firstButton;

    private DefaultTableModel model;
    String sql_all = "SELECT [IDProduct],ProductName,Price,Image,Status,TypeName,Size FROM [Product] Join ProductType on Product.IDType = ProductType.IDType";
    String SQL_liSu = "SELECT DISTINCT OrderDetail.IDOrder,TimeOder,EMP.NameEMP,Cus.CusName,OD.[Status] FROM OrderDetail  \n"
            + " JOIN [Order] OD ON OD.IDOrder = OrderDetail.IDOrder\n"
            + " LEFT JOIN Employee EMP ON EMP.UsernameEMP = OD.UsernameEMP\n"
            + " LEFT JOIN Customer Cus ON Cus.IDCust = OD.IDCust\n"
            + " WHERE IDTable = ? AND DateOrder = ?";
    String InsertOderDe = "INSERT INTO OrderDetail(IDOrder,IDProduct,IDTable,Quantity,Note) VALUES (?,?,?,?,?) ";
    String SQL_chuaTT = "SELECT DISTINCT MAX(OrderDetail.IDOrder) FROM OrderDetail \n"
            + "JOIN [Order] OD ON OD.IDOrder = OrderDetail.IDOrder\n"
            + "WHERE IDTable = ? AND DateOrder = ? AND [Status] = 1";
    String billCTT = "SELECT OrderDetail.IDOrder,TimeOder,OrderDetail.IDProduct,PR.ProductName,Size,Quantity,PR.Price,Note,OD.IDCust,EMP.NameEMP FROM OrderDetail\n"
            + "JOIN [Order] OD ON OD.IDOrder = OrderDetail.IDOrder\n"
            + "JOIN Product PR ON PR.IDProduct = OrderDetail.IDProduct\n"
            + " LEFT JOIN Employee EMP ON EMP.UsernameEMP = OD.UsernameEMP\n"
            + "JOIN ProductType on PR.IDType = ProductType.IDType\n"
            + "WHERE OrderDetail.IDOrder = ? ";
    String thanhToan = "UPDATE [Order] SET [Status] = 2 WHERE IDOrder = ?";

    public QLOrder_Service() {
        this.qlb = new Table_Service();
        this.qlk = new Area_Service();
        this.qlo = new Order_Service();
        this.lqp = new Product_Service();
        this.listBan = new ArrayList<ENTITY_Table>();
        this.pnlMain = new JPanel();
    }
    public static final SimpleDateFormat Time_FORMATER = new SimpleDateFormat("hh:mm:ss a");

    @Override
    public void taoTable(JPanel that, JDialog theDialog, int cbbkhu, JButton btnVaoBan, JLabel lblBan, JTable tblOder, JTable tblLichSu, JPanel PanlPanelLS, JPanel Oder, JTextField txtMaHD, JTextField txtMaKH, JTextField txtNameEMP, JLabel TimeOrder, JPopupMenu pmmBtn,JTextField txtTong) {
        this.listBan = (ArrayList<ENTITY_Table>) this.qlb.SQLKhu(cbbkhu);
        ClassLoader classLoader = theDialog.getClass().getClassLoader();
        pnlMain.removeAll();
        pnlMain.setBounds(10, 140, 570, 300);
        pnlMain.setBackground(new java.awt.Color(255, 153, 255));
        pnlMain.setBorder(javax.swing.BorderFactory.createTitledBorder("Danh Sách Bàn"));

//         JScrollPane sn = new JScrollPane();
//         sn.setViewportView(pnlMain);
//         pnlMain.add(sn);
        for (ENTITY_Table ban : listBan) {
            URL imagePath = classLoader.getResource("Icon/" + ban.getLocation() + ".png");
            Image imgBan = new ImageIcon(imagePath).getImage();
            Icon iconBan = new ImageIcon(imgBan.getScaledInstance(ICON_WIDTH, ICON_HEIGHT, imgBan.SCALE_SMOOTH));
            JButton button = new JButton();
            button.setIcon(iconBan);
            button.setBorder(UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"));
            button.setPreferredSize(new Dimension(100, 70));
            int cs = ban.getStatus() ? 1 : 0;
            switch (cs) {
                case 0:
                    button.setBackground(Color.GREEN);
                    break;
                case 1:
                    SimpleDateFormat format = new SimpleDateFormat("hh:mm aa");
                    button.setBackground(Color.red);
                    String s = this.OrderCTT(txtMaHD, ban.getIDTable(), dateHelper.DATE_FORMATER2.format(dateHelper.now()).trim());
                    ArrayList<billCTT> list = (ArrayList<billCTT>) this.SelectBill(billCTT, s);
                    for (billCTT cTT : list) {
                        button.setHorizontalTextPosition(0);
                        button.setFont(new Font("Dialog", 8, 8));
                        String go = format.format(cTT.getTimeOrder());
                        button.setText("Time : " + go);
                        button.setVerticalTextPosition(3);
                    }
                    break;
            }
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    TableSelectedHandler(e, btnVaoBan, lblBan, that, tblOder, tblLichSu, PanlPanelLS, Oder, txtMaHD, txtMaKH, txtNameEMP, TimeOrder,txtTong);
                }
            });
            button.addMouseListener(new MouseAdapter() {
                public void mouseReleased(MouseEvent e) {
                    selectedButtonPopup(e, button, pmmBtn);
                }

                private void selectedButtonPopup(MouseEvent e, JButton button, JPopupMenu pmmBtn) {
                    if (e.getSource().getClass() == button.getClass()) {
                        JButton b = (JButton) e.getSource();
                        if (e.isPopupTrigger()) {
                            pmmBtn.show(e.getComponent(), e.getX(), e.getY());
                            b.setSelected(true);
//                            numberDesk = DAOBAN.selectById(Integer.parseInt(b.getText()));
                        }
                    }
                }
            });
            pnlMain.add(button);
            BanButtons banButton = new BanButtons(button, ban.getIDTable(), cs, ban.getLocation());
            banButtonList.put(button, banButton);
        }
        that.add(pnlMain, new Integer(10), 0);
        // Clearing my JFrame and render again
        that.revalidate();
        that.repaint();
    }

    private void TableSelectedHandler(ActionEvent e, JButton btnVaoBan, JLabel lblBan, JPanel that, JTable tblOder, JTable tblLichSu, JPanel PanlPanelLS, JPanel Oder, JTextField txtMaHD, JTextField txtMaKH, JTextField txtNameEMP, JLabel TimeOrder,JTextField txtTong) {
        if (e.getSource().getClass() == JButton.class) {
            JButton selectedButton = (JButton) e.getSource();
            this.firstButton = selectedButton;
            BanButtons banButton = banButtonList.get(selectedButton);
            lblBan.setText(String.valueOf(banButton.getIDTalbe()));
            model = (DefaultTableModel) tblOder.getModel();
            if (banButton.getStatus() == 0) {//------------------------------Bàn không có khách
                btnVaoBan.setVisible(true);
                Oder.setVisible(false);
                model.getDataVector().removeAllElements();
                model.fireTableDataChanged();
                that.revalidate();
                LichSu(PanlPanelLS, tblLichSu, banButton);
                txtMaHD.setText("");
            } else {//-----------------------------------------Bàn có khách
                model = (DefaultTableModel) tblOder.getModel();
                model.setRowCount(0);
                this.OrderCTT(txtMaHD, banButton.getIDTalbe(), dateHelper.DATE_FORMATER2.format(dateHelper.now()).trim());
                this.bill(txtMaHD, txtNameEMP, txtMaKH, TimeOrder, tblOder);
                this.dong = tblOder.getRowCount();
                btnVaoBan.setVisible(false);
                PanlPanelLS.setVisible(false);
                Oder.setVisible(true);
                this.tongTien(txtTong, tblOder);
            }
        }
    }

    public void LichSu(JPanel PanlPanelLS, JTable tblLichSu, BanButtons banButton) {
        PanlPanelLS.setVisible(true);
        model = (DefaultTableModel) tblLichSu.getModel();
        model.setRowCount(0);
        this.model.setColumnIdentifiers(new Object[]{"IDOrder", "TimeOder", "Name NV", "NameKH", "Trạng Thái"});
        ArrayList<LSOrder> list = this.LichSu(SQL_liSu, banButton.getIDTalbe().trim(), dateHelper.DATE_FORMATER2.format(dateHelper.now()).trim());
        for (LSOrder lSOrder : list) {
            Object[] row = new Object[]{
                lSOrder.getIDOrder(),
                dateHelper.Time_FORMATER.format(lSOrder.getTimeOder()),
                lSOrder.getNameEMP() == null ? "Admin" : lSOrder.getNameEMP(),
                lSOrder.getCusName(), this.StatusOr(lSOrder.getStatus())
            };
            model.addRow(row);
        }
    }

    private String StatusOr(int n) {
        String kh = "Chưa thanh toán";
        if (n == 0) {
            kh = "Đã hủy";
        } else if (n == 1) {
            kh = "Chưa thanh toán";
        } else {
            kh = "Đã thanh toán";
        }
        return kh;
    }

    public ArrayList<SPChiTiet> select() {
        return (ArrayList<SPChiTiet>) this.SelectBySQL(sql_all);
    }

    public ArrayList<LSOrder> LichSu(String sql, Object... args) {
        ArrayList<LSOrder> list = new ArrayList<>();
        try {
            ResultSet rs = JDBC.query(sql, args);
            while (rs.next()) {
                LSOrder ls = new LSOrder();
                ls.setIDOrder(rs.getString("IDOrder"));
                ls.setNameEMP(rs.getString("NameEMP"));
                ls.setCusName(rs.getString("CusName"));
                ls.setStatus(rs.getInt("Status"));
                ls.setTimeOder(rs.getTime("TimeOder"));
                list.add(ls);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public List<SPChiTiet> SelectBySQL(String sql, Object... args) {
        List<SPChiTiet> list = new ArrayList<>();
        try {
            ResultSet rs = JDBC.query(sql, args);
            while (rs.next()) {
                SPChiTiet table = new SPChiTiet();
                table.setIDProduct(rs.getString("IDProduct"));
                table.setImage(rs.getString("Image"));
                table.setPrice(rs.getInt("Price"));
                table.setProductName(rs.getString("ProductName"));
                table.setStatus(rs.getBoolean("Status"));
                table.setSize(rs.getString("Size"));
                table.setTypeName(rs.getString("TypeName"));
                list.add(table);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public List<billCTT> SelectBill(String sql, Object... args) {
        List<billCTT> list = new ArrayList<>();
        try {
            ResultSet rs = JDBC.query(sql, args);
            while (rs.next()) {
                billCTT table = new billCTT();
                table.setIDProduct(rs.getString("IDProduct"));
                table.setIDCust(rs.getString("IDCust"));
                table.setIDOrderString(rs.getString("IDOrder"));
                table.setNote(rs.getString("Note"));
                table.setProductName(rs.getString("ProductName"));
                table.setSize(rs.getString("Size"));
                table.setTimeOrder(rs.getTime("TimeOder"));
                table.setPrice(rs.getInt("Price"));
                table.setQuantity(rs.getInt("Quantity"));
                table.setNameEMP(rs.getString("NameEMP"));
                list.add(table);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public ArrayList<ENTITY_Area> getkhu() {
        return (ArrayList<ENTITY_Area>) this.qlk.select();
    }

    @Override
    public ArrayList<ENTITY_Product> getsp() {
        return (ArrayList<ENTITY_Product>) this.lqp.select();
    }

    @Override
    public void hienTableSP(JTable tbl) {
        this.model = (DefaultTableModel) tbl.getModel();
        model.setRowCount(0);
        this.model.setColumnIdentifiers(new Object[]{"IDSP", "Loại", "Tên SP", "Size", "Giá", ""});
        TableColumnModel columnModel = tbl.getColumnModel();
        columnModel.getColumn(3).setMaxWidth(40);
        columnModel.getColumn(0).setMaxWidth(40);
        columnModel.getColumn(5).setMaxWidth(60);
        try {
            ArrayList<SPChiTiet> list = this.select();
            for (SPChiTiet cd : list) {
                Object[] row = {
                    cd.getIDProduct(),
                    cd.getTypeName(),
                    cd.getProductName(),
                    cd.getSize(),
                    cd.getPrice(),
                    //                    cd.isStatus() ? "Còn" : "Hết",
                    "Thêm"
                };
                model.addRow(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void hienTableOder(JTable tblOD) {
        this.model = (DefaultTableModel) tblOD.getModel();
        this.model.setRowCount(0);
        this.model.setColumnIdentifiers(new Object[]{"IDSP", "Tên SP", "Size", "Giá", "SL", "Ghi chú", ""});
        TableColumnModel columnModel = tblOD.getColumnModel();
        columnModel.getColumn(4).setMaxWidth(30);
        columnModel.getColumn(2).setMaxWidth(40);
        columnModel.getColumn(0).setMaxWidth(40);
        columnModel.getColumn(6).setMaxWidth(60);
    }

    @Override
    public void timSP(JTextField txt, JTable tbl) {
        this.model = (DefaultTableModel) tbl.getModel();
        this.model.fireTableDataChanged();
        TableRowSorter Sorter = new TableRowSorter(this.model);
        tbl.setRowSorter(Sorter);
        Sorter.setRowFilter(RowFilter.regexFilter(txt.getText()));
    }

    @Override
    public void taoHD(JTextField txt) {
        try {
            ResultSet rs = JDBC.query("SELECT MAX(IDOrder) FROM [Order]");
            rs.next();
            rs.getString(1);
            if (rs.getString(1) == null) {
                txt.setText("OD001");
            } else {
                long id = Long.parseLong(rs.getString(1).substring(2, rs.getString(1).length()));
                id++;
                txt.setText("OD" + String.format("%03d", id));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public String OrderCTT(JTextField txt, String IDTable, String DateNow) {
        try {
            ResultSet rs = JDBC.query(SQL_chuaTT, IDTable, DateNow);
            rs.next();
            String s = rs.getString(1);
            txt.setText(s);
            return s;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public void updatebn() {
        String sql = "UPDATE [Table] SET [Status] = ? WHERE IDTable = ?";
        JButton selectedButton = firstButton;
        BanButtons banButton = banButtonList.get(selectedButton);
        int n = banButton.getStatus() == 0 ? 1 : 0;
        try {
            JDBC.update(sql, n, banButton.getIDTalbe());
            firstButton = null;
        } catch (SQLException ex) {
            Logger.getLogger(QLOrder_Service.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void insertOr(JTextField txt) {
        ENTITY_Order or = new ENTITY_Order();
        or.setIDOrder(txt.getText());
        this.qlo.insert(or);
    }

    @Override
    public void insertOderDe(ENTITY_BILL bill) {
        try {
            JDBC.update(InsertOderDe, bill.getIDOrder(), bill.getIDProduct(), bill.getIDTable(), bill.getQuantity(), bill.getNote());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void lichsuOrder(JPanel PanlPanelLS, JTable tblLichSu) {
        BanButtons banButton = banButtonList.get(firstButton);
        this.LichSu(PanlPanelLS, tblLichSu, banButton);
    }

    @Override
    public void thanhToan(JTextField txtMaHD) {
        try {
            JDBC.update(thanhToan, txtMaHD.getText());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int dongC() {
        return dong;
    }

    @Override
    public void bill(JTextField txtMaHD, JTextField txtNameEMP, JTextField txtMaKH, JLabel TimeOrder, JTable tblOrder) {
        model = (DefaultTableModel) tblOrder.getModel();
        model.setRowCount(0);
        ArrayList<billCTT> list = (ArrayList<billCTT>) this.SelectBill(billCTT, txtMaHD.getText());
        for (billCTT cTT : list) {
            txtMaKH.setText(cTT.getIDCust());
            txtNameEMP.setText(cTT.getNameEMP() == null ? "Admin" : cTT.getNameEMP());
            TimeOrder.setText(dateHelper.Time_FORMATER.format(cTT.getTimeOrder()));
            Object[] row = new Object[]{
                cTT.getIDProduct(),
                cTT.getProductName(),
                cTT.getSize(),
                cTT.getPrice(),
                cTT.getQuantity(),
                cTT.getNote(), "Xóa"
            };
            model.addRow(row);
        }
    }

    @Override
    public double tongTien(JTextField txttong, JTable tblOder) {
        double total = 0;
        int line = tblOder.getRowCount();
        for (int i = 0; i < line; i++) {
            double gia = Double.valueOf(tblOder.getValueAt(i, 3).toString());
            int SL = Integer.valueOf(tblOder.getValueAt(i, 4).toString());
            double thanhtien = gia * SL;
            total += thanhtien;
        }
        txttong.setText(total + "VNĐ");
        return total;
    }

}
