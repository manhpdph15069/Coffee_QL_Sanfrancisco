/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS_Services;

import Utils.JDBC;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author phamd
 */
public class QLHoaDOn_Service {

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
        String[] cols = {"IDHD", "UsernameEMP", "DateOrder", "TimeOder", "Reason", "TongTien", "Status"};
        return this.getListOfArray(sql, cols);
    }
    public List<Object[]> getListDoUong(String idHD) {
        String sql = "{CALL getDoUong(?)}";
        String[] cols = {"ProductName"};
        return this.getListOfArray(sql, cols,idHD);
    }

    public void fillTable(JTable tbl) {
        DefaultTableModel model = (DefaultTableModel) tbl.getModel();
        model.setRowCount(0);
                String tt = null;
                String doUong =null;
        List<Object[]> list = getListHoaDon();
        if (list != null) {
            for (Object[] o : list) {
                int ma = Integer.valueOf(String.valueOf(o[6]));
                if (ma==1) {
                    tt="Đã thanh toán";
                }else if (ma==2) {
                    tt="Chưa thanh toán";
                }else if (ma==3) {
                    
                    tt="Đã hủy";
                }

                
                Object[] row = new Object[]{
                    o[0],
                    o[1],
                    o[2],
                    o[3],
                    o[4],
                    doUong,
                    o[5],
                    tt                  
                };
                System.out.println(doUong);
                model.addRow(row);
                
            }
        }
    }
}
