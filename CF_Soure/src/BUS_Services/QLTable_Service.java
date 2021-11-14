/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS_Services;

import BUS_IServices.*;
import DAL_Models.ENTITY_Area;
import DAL_Models.ENTITY_Table;
import DAL_Services.Table_Service;
import Utils.JDBC;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author phamd
 */
public class QLTable_Service implements IQLTable_Service {

    Table_Service dao = new Table_Service();

    @Override
    public void fillTable(JTable tbl) {
        try {
            DefaultTableModel d = (DefaultTableModel) tbl.getModel();
            d.setRowCount(0);
            List<ENTITY_Table> list = (List<ENTITY_Table>) dao.select();
            for (ENTITY_Table t : list) {

                Object[] row = new Object[]{
                    t.getIDTable(),
                    "Khu " + t.getIDArea(),
                    t.getLocation(),
                    "Hoạt động"
                };
                d.addRow(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void taoIDTable(JTextField maTable) {
        try {
            String sql = "Select MAX([IDTable]) from [Table]";
            ResultSet rs = JDBC.query(sql);
            rs.next();
            rs.getString(1);
            if (rs.getString(1) == null) {
                maTable.setText("TB001");
            } else {
                long id = Long.parseLong(rs.getString(1).substring(2, rs.getString(1).length()));
                id++;
                maTable.setText("TB" + String.format("%03d", id));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insertMATABLE(ENTITY_Table entity) {
        dao.insert(entity);
    }

    @Override
    public void updatetTABLE(ENTITY_Table entity) {
        dao.update(entity);
    }

    @Override
    public void deleteTABLE(String ma) {
        try {
            dao.delete(ma);
        } catch (SQLException ex) {
            Logger.getLogger(QLTable_Service.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<ENTITY_Area> selectIDArea() {
        return dao.selectIDArea();
    }

    @Override
    public void fillTableByID(JTable tbl, JTextField id) {
        DefaultTableModel m = (DefaultTableModel) tbl.getModel();
        m.fireTableDataChanged();
        TableRowSorter sorter = new TableRowSorter(m);
        tbl.setRowSorter(sorter);
        sorter.setRowFilter(RowFilter.regexFilter(id.getText()));

    }
}
