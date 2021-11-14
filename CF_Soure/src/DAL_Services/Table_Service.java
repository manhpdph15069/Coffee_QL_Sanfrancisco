/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL_Services;

import DAL_IServices.ITable_Service;
import DAL_Models.*;
import Utils.JDBC;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/*
* @author : ThongPro
* @since : 11/6/2021 12:07 PM
* @description :
* @update :
*
* */
public class Table_Service implements ITable_Service {
    String INSERT_SQL = "INSERT INTO [Table]([IDTable], [Location],[Status],[IDArea]) VALUES (?, ?,?, ?)";
    String UPDATE_SQL = "UPDATE [Table] SET [Location] = ?, [IDArea] = ?,[Status]=? WHERE [IDTable]=?";
    String DELETE_SQL = "UPDATE [Table] SET [Status]=0 WHERE [IDTable] = ?";
    String SELECT_ALL_SQL = "SELECT DISTINCT * FROM [Table]";
    String Select_IDArea ="SELECT DISTINCT IDArea FROM [Table]";
    String SELECT_BY_ID_SQL = "SELECT * FROM [Table] WHERE [IDTable] = ?";
    String SQL = "SELECT * FROM [Table] WHERE [IDArea] = ? ORDER BY Location";
    String SELECT_BY_ID_Area = "SELECT * FROM [Table] WHERE [IDArea] = ?";
    @Override
    public void insert(ENTITY_Table entity) {
        try {
            JDBC.update(INSERT_SQL,
                    entity.getIDTable(),
                    entity.getLocation(),
                    entity.getStatus(),
                    entity.getIDArea());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(ENTITY_Table entity) {
        try {
            JDBC.update(UPDATE_SQL,
                    entity.getLocation(),
                    entity.getStatus(),
                    entity.getIDArea(),
                    entity.getIDTable());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String IDTable) throws SQLException {
        JDBC.update(DELETE_SQL, IDTable);
    }

    @Override
    public List<ENTITY_Table> select() {
        return this.SelectBySQL(SELECT_ALL_SQL);
    }
    @Override
    public List<ENTITY_Table> selectIDArea() {
        return this.SelectBySQLIDArea(Select_IDArea);
    }
     public List<ENTITY_Table> SelectBySQLIDArea(String sql, Object... args) {
        List<ENTITY_Table> list = new ArrayList<>();
        try {
            ResultSet rs = JDBC.query(sql, args);
            while (rs.next()) {
                ENTITY_Table table = new ENTITY_Table();
                table.setIDArea(rs.getString("IDArea"));
                list.add(table);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public ENTITY_Table findById(String IDTable) {
        List<ENTITY_Table> list = this.SelectBySQL(SELECT_BY_ID_SQL, IDTable);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<ENTITY_Table> SelectBySQL(String sql, Object... args) {
        List<ENTITY_Table> list = new ArrayList<>();
        try {
            ResultSet rs = JDBC.query(sql, args);
            while (rs.next()) {
                ENTITY_Table table = new ENTITY_Table();
                table.setIDTable(rs.getString("IDTable"));
                table.setLocation(rs.getInt("Location"));
                table.setStatus(rs.getInt("Status"));
                table.setIDArea(rs.getString("IDArea"));
                list.add(table);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

        @Override
    public List<ENTITY_Table> findByIdArea(String IDArea) {
        return this.SelectBySQL(SELECT_BY_ID_Area, IDArea);

    }

    @Override
    public List<ENTITY_Table> SQLKhu(int khu) {
    return this.SelectBySQL(SQL, khu); 
    }

}
