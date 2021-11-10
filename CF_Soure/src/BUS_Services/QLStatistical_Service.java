/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS_Services;

import BUS_IServices.*;
import Utils.JDBC;
import java.awt.CardLayout;
import java.awt.Dimension;

import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 * @author phamd
 */
public class QLStatistical_Service implements IQLStatistical_Service {

    SimpleDateFormat format = new SimpleDateFormat("hh:mm aa");
    SimpleDateFormat formatThang = new SimpleDateFormat("dd-MM-yyyy");
    SimpleDateFormat formatNam = new SimpleDateFormat("yyyy");

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
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Object[]> getListByTKNgay(Date ngay) {
        String sql = "{CALL DT_THONGKENGAY(?)}";
        String[] cols = {"Tien", "TimeOder"};
        return this.getListOfArray(sql, cols, ngay);
    }

    @Override
    public void setDataNgay(JPanel pnlNgay, Date jdateNgay) {

        List<Object[]> list = getListByTKNgay(jdateNgay);
        DefaultCategoryDataset data = new DefaultCategoryDataset();
        if (list != null) {
            for (Object[] o : list) {
                String s = String.valueOf(o[0]);
                float so = Float.valueOf(s);
                String gio = format.format(o[1]);
                System.out.println("" + so + gio);
                data.addValue(so, "Số tiền", gio);
            }
        }
        JFreeChart barChart = ChartFactory.createBarChart("Thống kê doanh thu ngày".toUpperCase(), "Thời gian", "Số Tiền", data,
                PlotOrientation.VERTICAL, false, true, false);

        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new Dimension(pnlNgay.getWidth(), 700));

        pnlNgay.removeAll();
        pnlNgay.setLayout(new CardLayout());
        pnlNgay.add(chartPanel);
        pnlNgay.validate();
        pnlNgay.repaint();
    }

    @Override
    public List<Object[]> getListByTKThang(int thang) {
        String sql = "{CALL DT_THONGKETHANG(?)}";
        String[] cols = {"Tien", "Ngay"};
        return this.getListOfArray(sql, cols, thang);
    }

    @Override
    public void setDataThang(JPanel pnlNgay, int thang) {
        List<Object[]> list = getListByTKThang(thang);
        DefaultCategoryDataset data = new DefaultCategoryDataset();
        if (list != null) {
            for (Object[] o : list) {
                String t = String.valueOf(o[0]);
                float tien = Float.valueOf(t);
                String ngay = String.valueOf(formatThang.format(o[1]));

                data.addValue(tien, "Số tiền", ngay);
            }
        }
        JFreeChart barChart = ChartFactory.createBarChart("Thống kê doanh thu tháng".toUpperCase(), "Thời gian", "Số Tiền", data,
                PlotOrientation.VERTICAL, false, true, false);

        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new Dimension(pnlNgay.getWidth(), 700));

        pnlNgay.removeAll();
        pnlNgay.setLayout(new CardLayout());
        pnlNgay.add(chartPanel);
        pnlNgay.validate();
        pnlNgay.repaint();
    }

    @Override
    public List<Object[]> getListByTKNam(int nam) {
        String sql = "{CALL DT_THONGKENAM(?)}";
        String[] cols = {"Tien", "Thang"};
        return this.getListOfArray(sql, cols, nam);
    }

    @Override
    public void setDataNam(JPanel pnlNgay, int nam) {
        
        List<Object[]> list = getListByTKNam(nam);
        DefaultCategoryDataset data = new DefaultCategoryDataset();
        if (list != null) {
            for (Object[] o : list) {
                String s = String.valueOf(o[0]);
                float so = Float.valueOf(s);
                String thang = String.valueOf(o[1]);
                System.out.println("" + so + thang);
                data.addValue(so, "Số tiền", "Tháng "+thang);
            }
        }
        JFreeChart barChart = ChartFactory.createBarChart("Thống kê doanh thu năm".toUpperCase(), "Thời gian", "Số Tiền", data,
                PlotOrientation.VERTICAL, false, true, false);

        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new Dimension(pnlNgay.getWidth(), 700));

        pnlNgay.removeAll();
        pnlNgay.setLayout(new CardLayout());
        pnlNgay.add(chartPanel);
        pnlNgay.validate();
        pnlNgay.repaint();
    }
@Override
    public List<Object[]> getListByTKKhoangList(Date ngayBD, Date ngayKT) {
    String sql = "{CALL DT_THONGKEKHOANG(?,?)}";
        String[] cols = {"Tien", "Ngay"};
        return this.getListOfArray(sql, cols, ngayBD,ngayKT); 
    }

    @Override
    public void setDataKhoang(JPanel pnlNgay, Date ngayBD, Date ngayKT) {
        List<Object[]> list = getListByTKKhoangList(ngayBD, ngayKT);
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        if (list!=null) {
            for (Object[] o : list) {
                String s = String.valueOf(o[0]);
                float so = Float.valueOf(s);
                String ngay = formatThang.format(o[1]);

                   System.out.println("" + so + ngay);
                dataset.addValue(so, "Số tiền", ngay);
            }
        }
        JFreeChart barChart = ChartFactory.createBarChart("Thống kê doanh thu theo khoảng".toUpperCase(), "Thời gian", "Số Tiền", dataset,
                PlotOrientation.VERTICAL, false, true, false);

        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new Dimension(pnlNgay.getWidth(), 700));

        pnlNgay.removeAll();
        pnlNgay.setLayout(new CardLayout());
        pnlNgay.add(chartPanel);
        pnlNgay.validate();
        pnlNgay.repaint();
    }
}
