/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL_Services;

import DAL_IServices.IEmployee_Service;
import DAL_Models.*;
import Utils.JDBC;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Employee_Service implements IEmployee_Service {
    String INSERT_SQL = "INSERT INTO [Employee]([UsernameEMP], [Password], [NameEMP],[Phone],[Birthday],[Address],[Sex],[Email],[Image],[Status]) VALUES (?, ?, ?, ?,?,?,?,?,?,1)";
    String UPDATE_SQL = "UPDATE [Employee] SET [Password]= ?, [NameEMP] = ?,[Phone]=?,[Birthday]=?,[Address]=?,[Sex]=?,[Email]=?,[Image]=? WHERE [UsernameEMP]= ?";
    String DELETE_SQL = "UPDATE [Employee] SET [Status]=0 WHERE [UsernameEMP]= ?";
    String SELECT_ALL_SQL = "SELECT * FROM [Employee]";
    String SELECT_BY_ID_SQL = "SELECT * FROM [Employee] WHERE [UsernameEMP] = ?";

    @Override
    public void insert(ENTITY_Employee entity) {
        try {
            JDBC.update(INSERT_SQL,
                    entity.getUsernameEMP(),
                    entity.getPassword(),
                    entity.getNameEMP(),
                    entity.getPhone(),
                    entity.getBirthday(),
                    entity.getAddress(),
                    entity.getSex(),
                    entity.getEmail(),
                    entity.getImage()
                    );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(ENTITY_Employee entity) {
        try {
            JDBC.update(UPDATE_SQL,
                    entity.getPassword(),
                    entity.getNameEMP(),
                    entity.getPhone(),
                    entity.getBirthday(),
                    entity.getAddress(),
                    entity.getSex(),
                    entity.getEmail(),
                    entity.getImage(),
                    entity.getStatus(),
                    entity.getUsernameEMP());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String UsernameEMP) throws SQLException {
        JDBC.update(DELETE_SQL, UsernameEMP);
    }

    @Override
    public List<ENTITY_Employee> select() {
        return this.SelectBySQL(SELECT_ALL_SQL);
    }

    @Override
    public ENTITY_Employee findById(String UsernameEMP) {
        List<ENTITY_Employee> list = this.SelectBySQL(SELECT_BY_ID_SQL, UsernameEMP);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<ENTITY_Employee> SelectBySQL(String sql, Object... args) {
        List<ENTITY_Employee> list = new ArrayList<>();
        try {
            ResultSet rs = JDBC.query(sql, args);
            while (rs.next()) {
                ENTITY_Employee employee = new ENTITY_Employee();
              employee.setUsernameEMP(rs.getString("UsernameEMP"));
              employee.setPassword(rs.getString("Password"));
              employee.setNameEMP(rs.getString("NameEMP"));
              employee.setPhone(rs.getString("Phone"));
              employee.setBirthday(rs.getDate(5));
              employee.setAddress(rs.getString("Address"));
              employee.setSex(rs.getBoolean("Sex"));
              employee.setEmail(rs.getString("Email"));
              employee.setImage(rs.getString("Image"));
              employee.setStatus(rs.getBoolean(rs.getByte("Status")));
                list.add(employee);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

