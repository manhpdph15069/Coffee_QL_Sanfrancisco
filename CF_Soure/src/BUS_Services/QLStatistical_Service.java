/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS_Services;

import BUS_IServices.*;
import Utils.JDBC;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author phamd
 */
public class QLStatistical_Service implements IQLStatistical_Service {

    @Override
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
            throw new RuntimeException(e);
        }
    }


    @Override
    public List<Object[]> getListByTKNgay(String ngay) {
        String sql = "SELECT\n" +
                "		(hv.[Quantity]*[Price]) as Tien,\n" +
                "		[TimeOder]\n" +
                "	FROM [Order] od\n" +
                "		JOIN [OrderDetail] hv ON od.IDOrder=hv.IDOrder\n" +
                "		JOIN [Product] cd ON cd.IDProduct=hv.IDProduct\n" +
                "		where [DateOrder]=@NGay\n" +
                "	GROUP BY [TimeOder],(hv.[Quantity]*[Price])";
        String[] cols = {"Tien", "[TimeOder]"};
        return this.getListOfArray(sql, cols, ngay);
    }
}
