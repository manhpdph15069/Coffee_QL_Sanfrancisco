/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS_Services;

import BUS_IServices.*;
import BUS_Models.SanPham;
import DAL_IServices.IProduct_Service;
import DAL_Models.ENTITY_Product;
import DAL_Services.Product_Service;
import Utils.JDBC;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTextField;

/**
 *
 * @author phamd
 */
public class QLMenu_Service implements IQLMenu_Service {

    private IProduct_Service ips;

    String sql_all = "SELECT [IDProduct],ProductName,Price,Image,Status,TypeName,Size FROM [Product] "
            + "Join ProductType on Product.IDType = ProductType.IDType";
    String SELECT_BY_TypeName = "Select DISTINCT TypeName from ProductType";
    String SELECT_BY_Size = "Select DISTINCT TypeName ,Size  from ProductType where TypeName = ?";
    String them="INSERT INTO [Product]([IDProduct], [ProductName], [Price], [Image],[Status],[IDType]) VALUES (?,?, ?, ?, ?,?)";
    public QLMenu_Service() {
        this.ips = new Product_Service();
    }

    @Override
    public ArrayList<ENTITY_Product> getSanPham() {
        return (ArrayList<ENTITY_Product>) this.ips.select();
    }

    @Override
    public void taoID(JTextField txt) {
        try {
            ResultSet rs = JDBC.query("Select MAX(IDProduct) From Product");
            rs.next();
            rs.getString(1);
            if (rs.getString(1) == null) {
                txt.setText("SP01");
            } else {
                long id = Long.parseLong(rs.getString(1).substring(2, rs.getString(1).length()));
                id++;
                txt.setText("SP" + String.format("%03d", id));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void updateSP(SanPham pro) {
        String sql="UPDATE [Product] SET [ProductName] = ?, [Price] = ?, [Image] = ?,[IDType]=? WHERE [IDProduct] = ?";
        try {
            JDBC.update(sql,pro.getTypeName(),pro.getSize());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insert(SanPham pro) {
        try {
            JDBC.update(them,
                    pro.getIDProduct(),
                    pro.getProductName(),
                    pro.getPrice(),
                    pro.getImage(),
                    pro.isStatus(),
                    pro.getTypeName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String IDProduct) {
                String sql="UPDATE [Product] SET [Status]=0 WHERE [IDProduct] = ?";
        try {
            JDBC.update(sql,IDProduct);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<SanPham> select() {
        return (ArrayList<SanPham>) this.SelectBySQL(sql_all);
    }

    public List<SanPham> SelectBySQL(String sql, Object... args) {
        List<SanPham> list = new ArrayList<>();
        try {
            ResultSet rs = JDBC.query(sql, args);
            while (rs.next()) {
                SanPham table = new SanPham();
                table.setIDProduct(rs.getString(1));
                table.setProductName(rs.getString(2));
                table.setPrice(rs.getInt(3));
                table.setImage(rs.getString(4));
                table.setStatus(rs.getBoolean(5));
                list.add(table);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public List<SanPham> SelectByType(String sql, Object... args) {
        List<SanPham> list = new ArrayList<>();
        try {
            ResultSet rs = JDBC.query(sql, args);
            while (rs.next()) {
                SanPham table = new SanPham();
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
    public List<SanPham> SelectBySize(String sql, Object... args) {
        List<SanPham> list = new ArrayList<>();
        try {
            ResultSet rs = JDBC.query(sql, args);
            while (rs.next()) {
                SanPham table = new SanPham();
                table.setSize(rs.getString("Size"));
                list.add(table);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public List<SanPham> selectByKeyword(String keyword) {
        String sql = "Select * from Product where ProductName LIKE ?";
        return this.SelectBySQL(sql, "%" + keyword + "%");
    }

    public List<SanPham> selectTypeName() {
        try {
            return this.SelectByType(SELECT_BY_TypeName);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<SanPham> selectSize(String type) {
        try {
            return this.SelectBySize(SELECT_BY_Size,type);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
