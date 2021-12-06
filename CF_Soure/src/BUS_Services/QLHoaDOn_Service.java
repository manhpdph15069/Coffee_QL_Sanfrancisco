/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS_Services;

import BUS_IServices.IQLEmployee_Service;
import DAL_IServices.IEmployee_Service;
import DAL_Models.ENTITY_Employee;
import DAL_Models.ENTITY_Order;
import DAL_Models.ENTITY_Product;
import DAL_Services.Employee_Service;
import Utils.JDBC;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author phamd
 */
public class QLHoaDOn_Service {

    private NumberFormat n = new DecimalFormat("#,###");

    public List<ENTITY_Product> SelectBySQL(String sql, Object... args) {
        List<ENTITY_Product> list = new ArrayList<>();
        try {
            ResultSet rs = JDBC.query(sql, args);
            while (rs.next()) {
                ENTITY_Product customer = new ENTITY_Product();
                customer.setProductName(rs.getString(1));

                list.add(customer);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<Object[]> getListOfArray(String sql, String[] cols, Object... args) {
        try {
            List<Object[]> list = new ArrayList<>();
            ResultSet rs = JDBC.query(sql, args);
            while (rs.next()) {
                Object[] vals = new Object[cols.length];
                for (int i = 0; i < cols.length; i++) {
                    vals[i] = rs.getObject(cols[i]);
                }
                list.add(vals);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public List<Object[]> getListHoaDon() {
        String sql = "{CALL getListHoaDon}";
        String[] cols = {"IDHD", "NameEMP", "CusName", "NamePromo", "DateOrder", "TimeOder", "Reason", "TongTien", "Status"};
        return this.getListOfArray(sql, cols);
    }

    public List<Object[]> getListHoaDonNgay(Date ngay) {
        String sql = "{CALL getListHoaDonNgay(?)}";
        String[] cols = {"IDHD", "NameEMP", "CusName", "NamePromo", "DateOrder", "TimeOder", "Reason", "TongTien", "Status"};
        return this.getListOfArray(sql, cols, ngay);
    }

    public List<Object[]> getListHoaDonTHANG(int thang) {
        String sql = "{CALL getListHoaDonThang(?)}";
        String[] cols = {"IDHD", "NameEMP", "CusName", "NamePromo", "DateOrder", "TimeOder", "Reason", "TongTien", "Status"};
        return this.getListOfArray(sql, cols, thang);
    }

    public List<ENTITY_Product> getListDoUong(String idHD) {
        String sql = "select ProductName from Product p join OrderDetail od on p.IDProduct=od.IDProduct where od.IDOrder=?";
        return SelectBySQL(sql, idHD);
    }

    public void fillTable(JTable tbl) {
        DefaultTableModel model = (DefaultTableModel) tbl.getModel();
        model.setRowCount(0);
        String tt = null;
        String doUong = "";
        String nv = "";
        List<Object[]> list = getListHoaDon();
        if (list != null) {
            for (Object[] o : list) {
                List<ENTITY_Product> listdoUong = getListDoUong(String.valueOf(o[0]));
                doUong = "";
                for (ENTITY_Product odu : listdoUong) {
                    doUong = doUong + odu.getProductName() + ",";
                }

                int ma = Integer.valueOf(String.valueOf(o[8]));
                if (ma == 1) {
                    tt = "Chưa thanh toán";
                } else if (ma == 2) {
                    tt = "Đã thanh toán";
                } else if (ma == 3) {
                    tt = "Đã hủy";
                }

                Object[] row = new Object[]{
                    o[0],
                    o[1] == null ? "ADMIN" : o[1],
                    o[2] == null ? "Khách vãng lai" : o[2],
                    o[3] == null ? "Không có" : o[3],
                    o[4],
                    o[5],
                    o[6],
                    doUong,
                    n.format(o[7]) + " VNĐ",
                    tt
                };
                model.addRow(row);

            }
        }
    }

    public void fillTableNgay(JTable tbl, Date date) {
        DefaultTableModel model = (DefaultTableModel) tbl.getModel();
        model.setRowCount(0);
        String tt = null;
        String doUong = "";
        String nv = "";
        List<Object[]> list = getListHoaDonNgay(date);
        if (list != null) {
            for (Object[] o : list) {
                List<ENTITY_Product> listdoUong = getListDoUong(String.valueOf(o[0]));
                doUong = "";
                for (ENTITY_Product odu : listdoUong) {
                    doUong = doUong + odu.getProductName() + ",";
                }

                int ma = Integer.valueOf(String.valueOf(o[8]));
                if (ma == 1) {
                    tt = "Chưa thanh toán";
                } else if (ma == 2) {
                    tt = "Đã thanh toán";
                } else if (ma == 3) {
                    tt = "Đã hủy";
                }

                Object[] row = new Object[]{
                    o[0],
                    o[1] == null ? "ADMIN" : o[1],
                    o[2] == null ? "Khách vãng lai" : o[2],
                    o[3] == null ? "Không có" : o[3],
                    o[4],
                    o[5],
                    o[6],
                    doUong,
                    n.format(o[7]) + " VNĐ",
                    tt
                };
                model.addRow(row);

            }
        }
    }

    public void fillTableTHANG2(JTable tbl, int thang) {
        DefaultTableModel model = (DefaultTableModel) tbl.getModel();
        model.setRowCount(0);
        String tt = null;
        String doUong = "";
        String nv = "";
        List<Object[]> list = getListHoaDonTHANG(thang);
        if (list != null) {
            for (Object[] o : list) {
                List<ENTITY_Product> listdoUong = getListDoUong(String.valueOf(o[0]));
                doUong = "";
                for (ENTITY_Product odu : listdoUong) {
                    doUong = doUong + odu.getProductName() + ",";
                }

                int ma = Integer.valueOf(String.valueOf(o[8]));
                if (ma == 1) {
                    tt = "Chưa thanh toán";
                } else if (ma == 2) {
                    tt = "Đã thanh toán";
                } else if (ma == 3) {
                    tt = "Đã hủy";
                }

                Object[] row = new Object[]{
                    o[0],
                    o[1] == null ? "ADMIN" : o[1],
                    o[2] == null ? "Khách vãng lai" : o[2],
                    o[3] == null ? "Không có" : o[3],
                    o[4],
                    o[5],
                    o[6],
                    doUong,
                    n.format(o[7]) + " VNĐ",
                    tt
                };
                model.addRow(row);

            }
        }
    }

    public void huyHoaDon(String lyDo, String idOrder) {
        try {
            String sql = "Update [Order] SET Status=3,Reason=? where IDOrder=?";
            JDBC.update(sql, lyDo, idOrder);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void khoiPhucHD(String lyDo, String idOrder) {
        try {
            String sql = "Update [Order] SET Status=2,Reason=? where IDOrder=?";
            JDBC.update(sql, lyDo, idOrder);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
