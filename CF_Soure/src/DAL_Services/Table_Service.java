/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL_Services;

import DAL_IServices.ITable_Service;
import DAL_Models.*;

import java.util.ArrayList;

/*
* @author : ThongPro
* @since : 11/6/2021 12:07 PM
* @description :
* @update :
*
* */
public class Table_Service implements ITable_Service {
    String INSERT_SQL = "INSERT INTO NhanVien(MaNV, MatKhau, HoTen, VaiTro) VALUES (?, ?, ?, ?)";
    String UPDATE_SQL = "UPDATE NhanVien SET MatKhau = ?, HoTen = ?, VaiTro = ? WHERE MaNV = ?";
    String DELETE_SQL = "DELETE FROM NhanVien WHERE MaNV = ?";
    String SELECT_ALL_SQL = "SELECT * FROM NhanVien";
    String SELECT_BY_ID_SQL = "SELECT * FROM NhanVien WHERE MaNV = ?";

    @Override
    public void insert(ENTITY_Table entity) {

    }

    @Override
    public void update(ENTITY_Table entity) {

    }

    @Override
    public void delete(String IDTable) {

    }

    @Override
    public ArrayList<ENTITY_Table> select() {
        return null;
    }

    @Override
    public ENTITY_Table findById(String IDTable) {
        return null;
    }
}
