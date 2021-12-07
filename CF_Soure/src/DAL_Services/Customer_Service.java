/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL_Services;

import DAL_IServices.ICustomer_Service;
import DAL_Models.*;
import Utils.JDBC;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/*
 * @author : ThongPro
 * @since : 11/6/2021 9:40 PM
 * @description :
 * @update :
 *
 * */
public class Customer_Service implements ICustomer_Service {

    String INSERT_SQL = "INSERT INTO [Customer]([IDCust], [CusName], [DateAdd], [DateEnd],[Phone],[Email],[Discount],[Status]),[CCCD]=? VALUES (?, ?, ?, ?,?,?,?,1,?)";
    String UPDATE_SQL = "UPDATE [Customer] SET [CusName]= ?, [DateAdd] = ?,[DateEnd]=?,[Phone]=?,[Email]=?,[Discount]=?,[CCCD]=? WHERE [IDCust]= ?";
    String DELETE_SQL = "UPDATE [Customer] SET [Status]=0 WHERE [IDCust]= ?";
    String SELECT_ALL_SQL = "SELECT * FROM [Customer]";
    String SELECT_BY_ID_SQL = "SELECT * FROM [Customer] WHERE [IDCust] = ?";
    String up = "Update [Customer] set Status=0 where DateEnd=?";

    @Override
    public void insert(ENTITY_Customer entity) {
        try {
            JDBC.update(INSERT_SQL,
                    entity.getIDCust(),
                    entity.getCusName(),
                    entity.getDateAdd(),
                    entity.getDateEnd(),
                    entity.getPhone(),
                    entity.getEmail(),
                    entity.getDiscount(),
                    entity.getCCCD());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(ENTITY_Customer entity) {
        try {
            JDBC.update(UPDATE_SQL,
                    entity.getCusName(),
                    entity.getDateAdd(),
                    entity.getDateEnd(),
                    entity.getPhone(),
                    entity.getEmail(),
                    entity.getDiscount(),
                    entity.getCCCD(),
                    entity.getIDCust());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String IDCust) throws SQLException {
        JDBC.update(DELETE_SQL, IDCust);
    }

    @Override
    public List<ENTITY_Customer> select() {
        return this.SelectBySQL(SELECT_ALL_SQL);
    }

    @Override
    public ENTITY_Customer findById(String IDCust) {
        List<ENTITY_Customer> list = this.SelectBySQL(SELECT_BY_ID_SQL, IDCust);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<ENTITY_Customer> SelectBySQL(String sql, Object... args) {
        List<ENTITY_Customer> list = new ArrayList<>();
        try {
            ResultSet rs = JDBC.query(sql, args);
            while (rs.next()) {
                ENTITY_Customer customer = new ENTITY_Customer();
                customer.setIDCust(rs.getString("IDCust"));
                customer.setCusName(rs.getString("CusName"));
                customer.setDateAdd(rs.getDate("DateAdd"));
                customer.setDateEnd(rs.getDate("DateEnd"));
                customer.setPhone(rs.getString("Phone"));
                customer.setEmail(rs.getString("Email"));
                customer.setDiscount(rs.getInt("Discount"));
                customer.setStatus(rs.getBoolean("Status"));
                customer.setCCCD(rs.getString("CCCD"));
                list.add(customer);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void up(String DateEnd) throws SQLException {
        JDBC.update(up, DateEnd);
    }

    public void up1(String DateEnd) throws SQLException {
        String sql = "Update [Customer] set Status=1 where DateEnd=?";
        JDBC.update(sql, DateEnd);
    }

    public void giahan(ENTITY_Customer entity) throws SQLException {
        String sql = "Update [Customer] set DateEnd=? where IDCust=?";
        JDBC.update(sql,
                entity.getDateEnd(),
                entity.getIDCust());
    }
}
