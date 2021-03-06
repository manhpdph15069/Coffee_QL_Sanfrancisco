/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS_Services;

import BUS_IServices.*;
import BUS_Models.ThongKeSP;
import DAL_Models.ENTITY_Product;
import DAL_Services.Admin;
import DAL_Services.Product_Service;
import Utils.JDBC;
import Utils.ThongBao;
import Utils.dateHelper;
import Utils.mailHelper;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 * @author phamd
 */
public class QLStatistical_Service implements IQLStatistical_Service {

    List<Object[]> msList = new ArrayList<>();
    SimpleDateFormat format = new SimpleDateFormat("hh:mm:ss aa");
    SimpleDateFormat formatThang = new SimpleDateFormat("dd-MM-yyyy");

    SimpleDateFormat formatNam = new SimpleDateFormat("yyyy");
    QLHoaDOn_Service daoHD = new QLHoaDOn_Service();
    Product_Service daoSP = new Product_Service();
    private NumberFormat n = new DecimalFormat("#,###");
    private static String url = "data.txt";
    private static FileInputStream fileInputStream = null;
    private static BufferedReader bufferedReader = null;
    private static String user = "";
    private static String pass = "";

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
    public List<Object[]> getListTongMonvaHDNgay(Date date) {
        String sql = "{CALL getTongMvaTongHD(?)}";
        String[] cols = {"tongM", "TongHD"};
        return this.getListOfArray(sql, cols, date);
    }

    public List<Object[]> getListTongMonvaHDTC() {
        String sql = "{CALL getTongMvaTongHDTC}";
        String[] cols = {"tongM", "TongHD"};
        return this.getListOfArray(sql, cols);
    }

    @Override
    public void setTongMonNgay(JLabel lblTM, JLabel lblHD, Date ngay) {
        List<Object[]> list = getListTongMonvaHDNgay(ngay);
        if (list != null) {
            for (Object[] o : list) {
                lblTM.setText(String.valueOf(o[0]));
                lblHD.setText(String.valueOf(o[1]));

            }
        }
    }

    @Override
    public void setTongMonvaHDTC(JLabel lblTM, JLabel lblHD) {
        List<Object[]> list = getListTongMonvaHDTC();
        if (list != null) {
            for (Object[] o : list) {
                lblTM.setText(String.valueOf(o[0]));
                lblHD.setText(String.valueOf(o[1]));

            }
        }
    }

    @Override
    public List<Object[]> getListTongMonvaHDThang(int date) {
        String sql = "{CALL getTongMvaTongHDThang(?)}";
        String[] cols = {"tongM", "TongHD"};
        return this.getListOfArray(sql, cols, date);
    }

    @Override
    public void setTongMonThang(JLabel lblTM, JLabel lblHD, int thang) {
        List<Object[]> list = getListTongMonvaHDThang(thang);
        if (list != null) {
            for (Object[] o : list) {
                lblTM.setText(String.valueOf(o[0]));
                lblHD.setText(String.valueOf(o[1]));
            }
        }
    }

    @Override
    public List<Object[]> getListTongMonvaHDNam(int date) {
        String sql = "{CALL getTongMvaTongHDNam(?)}";
        String[] cols = {"tongM", "TongHD"};
        return this.getListOfArray(sql, cols, date);
    }

    @Override
    public List<Object[]> getListTongMonvaHDKhoang(Date ngayBD, Date ngayKT) {
        String sql = "{CALL getTongMvaTongHDKhoang(?,?)}";
        String[] cols = {"tongM", "TongHD"};
        return this.getListOfArray(sql, cols, ngayBD, ngayKT);
    }

    @Override
    public void setTongMonKhoang(JLabel lblTM, JLabel lblHD, Date ngayBD, Date ngayKT) {
        List<Object[]> list = getListTongMonvaHDKhoang(ngayBD, ngayKT);
        if (list != null) {
            for (Object[] o : list) {
                lblTM.setText(String.valueOf(o[0]));
                lblHD.setText(String.valueOf(o[1]));

            }
        }
    }

    @Override
    public void setTongMonNam(JLabel lblTM, JLabel lblHD, int nam) {
        List<Object[]> list = getListTongMonvaHDNam(nam);
        if (list != null) {
            for (Object[] o : list) {
                lblTM.setText(String.valueOf(o[0]));
                lblHD.setText(String.valueOf(o[1]));

            }
        }
    }

    @Override
    public List<Object[]> getListByTKNgay(Date ngay) {
        String sql = "{CALL DT_THONGKENGAY(?)}";
        String[] cols = {"Tien", "TimeOder"};
        return this.getListOfArray(sql, cols, ngay);
    }

    public List<Object[]> getListByTK() {
        String sql = "{CALL DT_THONGKE}";
        String[] cols = {"Tien", "DateOrder"};
        return this.getListOfArray(sql, cols);
    }

    @Override
    public void setDataNgay(JPanel pnlNgay, Date jdateNgay, JLabel lbltien) {

        List<Object[]> list = getListByTKNgay(jdateNgay);
        DefaultCategoryDataset data = new DefaultCategoryDataset();
        float tong=0;
        if (list != null) {
            for (Object[] o : list) {
                String s = String.valueOf(o[0]);
                float so = Float.valueOf(s);
                String gio = format.format(o[1]);
                data.addValue(so, "S??? ti???n", gio);
                tong += Float.parseFloat(String.valueOf(o[0]));
            }
        }
         lbltien.setText(n.format(tong) + " VN??");
        JFreeChart barChart = ChartFactory.createBarChart("Th???ng k?? doanh thu ng??y".toUpperCase(), "Th???i gian", "S??? Ti???n", data,
                PlotOrientation.VERTICAL, false, true, false);

        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new Dimension(600, 321));

        pnlNgay.removeAll();
        pnlNgay.setLayout(new CardLayout());
        pnlNgay.add(chartPanel);
        pnlNgay.validate();
        pnlNgay.repaint();
    }

    @Override
    public void setDataTKTONG(JPanel pnlNgay, JLabel lbltien) {

        List<Object[]> list = getListByTK();
        DefaultCategoryDataset data = new DefaultCategoryDataset();
        float tong = 0;
        if (list != null) {
            for (Object[] o : list) {
                String s = String.valueOf(o[0]);
                float so = Float.valueOf(s);
                String gio = formatThang.format(o[1]);
                data.addValue(so, "S??? ti???n", gio);
                tong += Float.parseFloat(String.valueOf(o[0]));
            }
            lbltien.setText(n.format(tong) + " VN??");
        }
        JFreeChart barChart = ChartFactory.createBarChart("Doanh thu".toUpperCase(), "Th???i gian", "S??? Ti???n", data,
                PlotOrientation.VERTICAL, false, true, false);

        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new Dimension(600, 321));

        pnlNgay.removeAll();
        pnlNgay.setLayout(new CardLayout());
        pnlNgay.add(chartPanel);
        pnlNgay.validate();
        pnlNgay.repaint();
    }

    @Override
    public List<Object[]> getListByTKThang(int thang, int nam) {
        String sql = "{CALL DT_THONGKETHANG(?,?)}";
        String[] cols = {"Tien", "Ngay"};
        return this.getListOfArray(sql, cols, thang, nam);
    }

    @Override
    public void setDataThang(JPanel pnlNgay, int thang, int nam, JLabel lbltien) {
        try {
            List<Object[]> list = getListByTKThang(thang, nam);
            DefaultCategoryDataset data = new DefaultCategoryDataset();
            float tong =0;
            if (list != null) {
                for (Object[] o : list) {
                    String t = String.valueOf(o[0]);
                    float tien = Float.valueOf(t);
                    String ngay = String.valueOf(formatThang.format(o[1]));
                    data.addValue(tien, "S??? ti???n", ngay);
                    tong += Float.parseFloat(String.valueOf(o[0]));
                }
            }
             lbltien.setText(n.format(tong) + " VN??");
            JFreeChart barChart = ChartFactory.createBarChart("Th???ng k?? doanh thu th??ng".toUpperCase(), "Th???i gian", "S??? Ti???n", data,
                    PlotOrientation.VERTICAL, false, true, false);

            ChartPanel chartPanel = new ChartPanel(barChart);
            chartPanel.setPreferredSize(new Dimension(pnlNgay.getWidth(), 321));

            pnlNgay.removeAll();
            pnlNgay.setLayout(new CardLayout());
            pnlNgay.add(chartPanel);
            pnlNgay.validate();
            pnlNgay.repaint();
        } catch (Exception e) {
            //  e.printStackTrace();
        }
    }

    @Override
    public List<Object[]> getListByTKNam(int nam) {
        String sql = "{CALL DT_THONGKENAM(?)}";
        String[] cols = {"Tien", "Thang"};
        return this.getListOfArray(sql, cols, nam);
    }

    @Override
    public void setDataNam(JPanel pnlNgay, int nam, JLabel lbltien) {

        List<Object[]> list = getListByTKNam(nam);
        DefaultCategoryDataset data = new DefaultCategoryDataset();
        float tong=0;
        if (list != null) {
            for (Object[] o : list) {
                String s = String.valueOf(o[0]);
                float so = Float.valueOf(s);
                String thang = String.valueOf(o[1]);
                data.addValue(so, "S??? ti???n", "Th??ng " + thang);
                tong += Float.parseFloat(String.valueOf(o[0]));
            }
        }
         lbltien.setText(n.format(tong) + " VN??");
        JFreeChart barChart = ChartFactory.createBarChart("Th???ng k?? doanh thu n??m".toUpperCase(), "Th???i gian", "S??? Ti???n", data,
                PlotOrientation.VERTICAL, false, true, false);

        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new Dimension(pnlNgay.getWidth(), 321));

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
        return this.getListOfArray(sql, cols, ngayBD, ngayKT);
    }

    @Override
    public void setDataKhoang(JPanel pnlNgay, Date ngayBD, Date ngayKT, JLabel lbltien) {
        List<Object[]> list = getListByTKKhoangList(ngayBD, ngayKT);
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        float tong=0;
        if (list != null) {
            for (Object[] o : list) {
                String s = String.valueOf(o[0]);
                float so = Float.valueOf(s);
                String ngay = formatThang.format(o[1]);
                dataset.addValue(so, "S??? ti???n", ngay);
                tong += Float.parseFloat(String.valueOf(o[0]));
            }
        }
         lbltien.setText(n.format(tong) + " VN??");
        JFreeChart barChart = ChartFactory.createBarChart("Th???ng k?? doanh thu theo kho???ng".toUpperCase(), "Th???i gian", "S??? Ti???n", dataset,
                PlotOrientation.VERTICAL, false, true, false);

        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new Dimension(pnlNgay.getWidth(), 321));

        pnlNgay.removeAll();
        pnlNgay.setLayout(new CardLayout());
        pnlNgay.add(chartPanel);
        pnlNgay.validate();
        pnlNgay.repaint();
    }

    public List<Object[]> getListBysendMail(int nam) {
        String sql = "{CALL sendmailNam(?)}";
        String[] cols = {"IDHD", "UsernameEMP", "DateOrder", "TimeOder", "Reason"};
        return this.getListOfArray(sql, cols, nam);
    }

    @Override
    public void sendmail(String message) {
        try {
            fileInputStream = new FileInputStream(url);
            bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
            String line = bufferedReader.readLine();
            int i = 0;
            while (line != null) {
                if (i == 0) {
                    user = line.substring(line.indexOf(':') + 1);
                }
                if (i == 1) {
                    pass = line.substring(line.indexOf(':') + 1);
                }
                i++;
                line = bufferedReader.readLine();
            }
            // System.out.println(user);
            // System.out.println(pass);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(mailHelper.class.getName())
                    .log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(mailHelper.class.getName())
                    .log(Level.SEVERE, null, ex);
        } finally {
            try {
                bufferedReader.close();
                fileInputStream.close();
            } catch (IOException ex) {
                Logger.getLogger(mailHelper.class.getName())
                        .log(Level.SEVERE, null, ex);
            }
        }
        try {
            Admin d = new Admin();
            String host = "smtp.gmail.com";
            String user = QLStatistical_Service.user;
            String pass = QLStatistical_Service.pass;
            String to = d.select().getEmail();
            String subject = "B??o c??o ng??y " + formatThang.format(dateHelper.now());

            boolean sessionDebug = false;
            //!.T???o 1 d???i t?????ng Properties
            Properties pros = new Properties();
            pros.put("mail.smtp.auth", "true");
            pros.put("mail.smtp.starttls.enable", "true");
            pros.put("mail.smtp.host", "smtp.gmail.com");//2.Ch??? ra m??y ch??? mail c???a gg
            pros.put("mail.smtp.port", 587);//3.Ch??? ra port : 587 C???ng v??o ra d??? li???u
            pros.put("mail.smtp.starttls.required", "true");
            java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());

            Session mailSession = Session.getInstance(pros,
                    new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(user, pass);//T??i kho???n Gmail c???a b???n v?? pass
                }
            }
            );
            mailSession.setDebug(sessionDebug);
            Message msg = new MimeMessage(mailSession);
            //G??n gi?? tr??? cho c??c thu???c t??nh ????i t?????ng msg
            msg.setFrom(new InternetAddress(user));//5.T??? ?????a ch??? Gmail n??o g?????i ??i
            //            InternetAddress[] address = {new InternetAddress(to)};
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));//T?? Gmail g?????i ?????n mai n??o
            msg.setSubject(subject);//Ti??u ????? th??
            msg.setText(message);//N???i dung th??
////            Transport.send(msg);
            Transport transport = mailSession.getTransport("smtp");
            transport.connect(host, user, pass);
            transport.sendMessage(msg, msg.getAllRecipients());
            transport.close();

            ThongBao.alert(null, "G???i b??o c??o th??nh c??ng");
        } catch (Exception e) {
        }
    }

    public List<Object[]> getBillHuyNam(int nam) {
        String sql = "{CALL getBillHuyNam(?)}";
        String[] cols = {"tongBillHuy"};
        return this.getListOfArray(sql, cols, nam);
    }

    @Override
    public void guiBCN(int nam) {
        float tien = 0;
        String message = "";
        try {
            List<Object[]> list1 = getListBysendMail(nam);
            List<Object[]> list2 = getListTongMonvaHDNam(nam);
            List<Object[]> list3 = getBillHuyNam(nam);
            List<Object[]> list4 = getListByTKNam(nam);
            String tongMon = "";
            if (list1 != null) {
                for (Object[] o : list2) {
                    if (String.valueOf(o[0]).equalsIgnoreCase("Null")) {
                        tongMon = "Kh??ng c??";
                    } else {
                        tongMon = String.valueOf(o[0]);
                    }
                    if (list2 != null) {
                        if (list4 != null) {
                            for (Object[] oT : list4) {
                                tien += Float.parseFloat(String.valueOf(oT[0]));
                            }
                            message = message + "\t\t\t\tB??o c??o trong n??m " + nam + "\n"
                                    + "|---------------------------------------------------------------------------|\n"
                                    + "                                                   \t\t\t\t\t\n"
                                    + "\t\tT???ng m??n b??n trong n??m: " + tongMon + "\t\t\n"
                                    + "\t\tT???ng bill trong n??m: " + String.valueOf(o[1]) + "\t\t\n"
                                    + "\t\tT???ng ti???n trong n??m: " + tien + "VN??" + "\t\t\n";
                            if (list3 != null) {
                                for (Object[] oh : list3) {
                                    message = message + "\t\tT???ng bill b??? h???y: " + String.valueOf(oh[0]) + "\t\t\n\n"
                                            + "\t" + "Danh s??ch ????n b??? h???y: \n";
                                }
                            }
                            for (Object[] oo : list1) {
                                message = message + "\t\n"
                                        + "\t\tM?? h??a ????n b??? h???y l??: " + String.valueOf(oo[0] + "\t\n"
                                                + "\t\tM?? nh??n vi??n order: " + String.valueOf(oo[1])) + "\t\n"
                                        + "\t\tNg??y :  " + String.valueOf(oo[2] + "\t\n"
                                                + "\t\tGi???: " + String.valueOf(oo[3]) + "\t\n"
                                                + "\t\tL?? do: " + String.valueOf(oo[4]) + "\t\n"
                                                + "\t\n"
                                        );

                            }
                        }

                    }
                }
            }
            sendmail(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Object[]> getListBysendMailNgay(Date ngay) {
        String sql = "{CALL sendmailNgay(?)}";
        String[] cols = {"IDHD", "UsernameEMP", "DateOrder", "TimeOder", "Reason"};
        return this.getListOfArray(sql, cols, ngay);
    }

    public List<Object[]> getListBysendMailThang(int thang, int nam) {
        String sql = "{CALL sendmailThang(?,?)}";
        String[] cols = {"IDHD", "UsernameEMP", "DateOrder", "TimeOder", "Reason"};
        return this.getListOfArray(sql, cols, thang, nam);
    }

    public List<Object[]> getBillHuyNgay(Date ngay) {
        String sql = "{CALL getBillHuyNgay(?)}";
        String[] cols = {"tongBillHuy"};
        return this.getListOfArray(sql, cols, ngay);
    }

    public List<Object[]> getBillHuyThang(int thang, int nam) {
        String sql = "{CALL getBillHuyThang(?,?)}";
        String[] cols = {"tongBillHuy"};
        return this.getListOfArray(sql, cols, thang, nam);
    }

    @Override
    public void guiBCThang(int thang, int nam) {
        String message = "";
        try {
            List<Object[]> list1 = getListBysendMailThang(thang, nam);
            List<Object[]> list2 = getListTongMonvaHDThang(thang);
            List<Object[]> list3 = getBillHuyThang(thang, nam);
            List<Object[]> list4 = getListByTKThang(thang, nam);

            float tien = 0;
            String doUong = "";
            String maNV = "";
            String tongMon = "";
            for (Object[] o : list2) {
                if (String.valueOf(o[0]).equalsIgnoreCase("Null")) {
                    tongMon = "Kh??ng c??";
                } else {
                    tongMon = String.valueOf(o[0]);
                }
                message = message + "\t\t\t\tB??o c??o trong th??ng " + thang + "\n"
                        + "|---------------------------------------------------------------------------|\n"
                        + "                                                   \t\t\t\t\t\n"
                        + "\t\tT???ng m??n b??n trong th??ng: " + tongMon + "\t\t\n"
                        + "\t\tT???ng h??a ????n trong th??ng: " + String.valueOf(o[1]) + "\t\t\n";

                for (Object[] oh : list3) {
                    message = message + "\t\tT???ng h??a ????n b??? h???y trong th??ng: " + String.valueOf(oh[0]) + "\t\t\n";
                    if (list4.size() >= 1) {

                        for (Object[] oT : list4) {
                            tien += Float.parseFloat(String.valueOf(oT[0]));
                        }
                        message = message + "\t\tDoanh thu trong th??ng: " + n.format(tien) + "VN??" + "\t\t\n\n"
                                + "\t" + "Danh s??ch ????n b??? h???y: \n";
                    }
                }
                for (Object[] oo : list1) {
                    List<ENTITY_Product> listDU = daoHD.getListDoUong(String.valueOf(oo[0]));
                    doUong = "";
                    for (ENTITY_Product oDU : listDU) {
                        doUong = doUong + oDU.getProductName() + ",";
                    }
                    if (String.valueOf(oo[1]).equalsIgnoreCase("Null")) {
                        maNV = "Admin";
                    } else {
                        maNV = String.valueOf(oo[1]);
                    }
                    message = message + "\n"
                            + "\t\tM?? h??a ????n b??? h???y l??: " + String.valueOf(oo[0]) + "\t\n"
                            + "\t\tM?? nh??n vi??n order: " + maNV + "\t\n"
                            + "\t\tNg??y: " + String.valueOf(oo[2]) + "\t\n"
                            + "\t\tGi???: " + String.valueOf(oo[3]) + "\t\n"
                            + "\t\t????? u???ng: " + doUong + "\t\n"
                            + "\t\tL?? do: " + String.valueOf(oo[4]) + "\t\n"
                            + "\n";

                }
            }

            sendmail(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void guiBCNgay(Date ngay) {
        String message = "";
        try {
            List<Object[]> list1 = getListBysendMailNgay(ngay);
            List<Object[]> list2 = getListTongMonvaHDNgay(ngay);
            List<Object[]> list3 = getBillHuyNgay(ngay);
            List<Object[]> list4 = getListByTKNgay(ngay);

            float tien = 0;
            String doUong = "";
            String maNV = "";
            String tongMon = "";
            for (Object[] o : list2) {
                if (String.valueOf(o[0]).equalsIgnoreCase("Null")) {
                    tongMon = "Kh??ng c??";
                } else {
                    tongMon = String.valueOf(o[0]);
                }
                message = message + "\t\t\t\tB??o c??o trong ng??y " + "\n "
                        + "|---------------------------------------------------------------------------|\n"
                        + "                                                   \t\t\t\t\t\n"
                        + "\t\tT???ng m??n b??n trong ng??y: " + tongMon + "\t\t\n"
                        + "\t\tT???ng h??a ????n trong ng??y: " + String.valueOf(o[1]) + "\t\t\n";

                for (Object[] oh : list3) {
                    message = message + "\t\tT???ng h??a ????n b??? h???y trong ng??y: " + String.valueOf(oh[0]) + "\t\t\n";
                    if (list4.size() >= 1) {

                        for (Object[] oT : list4) {
                            tien += Float.parseFloat(String.valueOf(oT[0]));
                        }
                        message = message + "\t\tDoanh thu trong ng??y: " + n.format(tien) + "VN??" + "\t\t\n\n"
                                + "\t" + "Danh s??ch ????n b??? h???y: \n";
                    }
                }
                for (Object[] oo : list1) {
                    List<ENTITY_Product> listDU = daoHD.getListDoUong(String.valueOf(oo[0]));
                    doUong = "";
                    for (ENTITY_Product oDU : listDU) {
                        doUong = doUong + oDU.getProductName() + ",";
                    }
                    if (String.valueOf(oo[1]).equalsIgnoreCase("Null")) {
                        maNV = "Admin";
                    } else {
                        maNV = String.valueOf(oo[1]);
                    }
                    message = message + "\n"
                            + "\t\tM?? h??a ????n b??? h???y l??: " + String.valueOf(oo[0]) + "\t\n"
                            + "\t\tM?? nh??n vi??n order: " + maNV + "\t\n"
                            + "\t\tNg??y: " + String.valueOf(oo[2]) + "\t\n"
                            + "\t\tGi???: " + String.valueOf(oo[3]) + "\t\n"
                            + "\t\t????? u???ng: " + doUong + "\t\n"
                            + "\t\tL?? do: " + String.valueOf(oo[4]) + "\t\n"
                            + "\n";

                }
            }

            sendmail(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //---------------------------------------------
    public List<ThongKeSP> tkSanPhamDB() {
        String sql = "select p.ProductName,count(*)as Soluong,p.Price,o.Reason,o.Status,od.DiscountOrder from product p join OrderDetail o on p.IDProduct=o.IDProduct \n"
                + "                 join [Order] od on od.IDOrder=o.IDOrder\n"
                + "                 group by p.ProductName,p.Price,o.Status,o.Reason,od.DiscountOrder";
        return this.layTK_Products(sql);
    }

    public List<ThongKeSP> tkSanPhamDBNGAY(String date) {
        String sql = "select p.ProductName,count(*)as Soluong,p.Price,o.Reason,o.Status,od.DiscountOrder from product p join OrderDetail o on p.IDProduct=o.IDProduct \n"
                + "join [Order] od on od.IDOrder=o.IDOrder\n"
                + "where od.DateOrder=?\n"
                + "group by p.ProductName,p.Price,o.Status,o.Reason,od.DiscountOrder";
        return this.layTK_Products(sql, date);
    }

    public List<ThongKeSP> tkSanPhamDBTHANG(int thang) {
        String sql = "select p.ProductName,count(*)as Soluong,p.Price,o.Reason,o.Status,od.DiscountOrder from product p join OrderDetail o on p.IDProduct=o.IDProduct \n"
                + "join [Order] od on od.IDOrder=o.IDOrder\n"
                + "where Month(od.DateOrder)=?\n"
                + "group by p.ProductName,p.Price,o.Status,o.Reason,od.DiscountOrder";
        return this.layTK_Products(sql, thang);
    }

    public List<ThongKeSP> tkSanPhamDBNAM(int nam) {
        String sql = "select p.ProductName,count(*)as Soluong,p.Price,o.Reason,o.Status,od.DiscountOrder from product p join OrderDetail o on p.IDProduct=o.IDProduct \n"
                + "join [Order] od on od.IDOrder=o.IDOrder\n"
                + "where Year(od.DateOrder)=?\n"
                + "group by p.ProductName,p.Price,o.Status,o.Reason,od.DiscountOrder";
        return this.layTK_Products(sql, nam);
    }

    public List<ThongKeSP> layTK_Products(String sql, Object... args) {
        List<ThongKeSP> list = new ArrayList<>();
        try {
            ResultSet rs = JDBC.query(sql, args);
            while (rs.next()) {
                ThongKeSP table = new ThongKeSP();
                table.setProductName(rs.getString(1));
                table.setSoLuong(rs.getInt(2));
                table.setGia(rs.getFloat(3));
                table.setLyDo(rs.getString(4));
                table.setStatus(rs.getBoolean(5));
                table.setDiscont(rs.getInt(6));

                list.add(table);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void fillTableSanPham(JTable tbl, JTable tbl2, JLabel lbl, JLabel lblTM, JLabel lbl2, JLabel lblTM2) {
        DefaultTableModel model = (DefaultTableModel) tbl.getModel();
        model.setRowCount(0);
        DefaultTableModel model2 = (DefaultTableModel) tbl2.getModel();
        model2.setRowCount(0);
        float tong = 0;
        int tongM = 0;
        float tong2 = 0;
        int tongM2 = 0;
        float tien = 0;
        List<ThongKeSP> list2 = tkSanPhamDB();
        for (ThongKeSP tk : list2) {
            if (tk.isStatus() == false) {
                if (tk.getDiscont() == 0) {
                    tien = (tk.getGia() * tk.getSoLuong());

                } else {
                    tien = (tk.getGia() * tk.getSoLuong()) - ((tk.getGia() * tk.getSoLuong()) * tk.getDiscont()) / 100;
                }
                tong += tien;
                tongM += tk.getSoLuong();
                Object[] row = new Object[]{
                    tk.getProductName(),
                    tk.getSoLuong(),
                    n.format(tk.getGia()) + " VN??",
                    n.format(tien) + " VN??",
                    tk.getDiscont() + " %",
                    "???? b??n"
                };
                model.addRow(row);
            } else {
                tong2 += (tk.getGia() * tk.getSoLuong());
                tongM2 += tk.getSoLuong();
                Object[] row = new Object[]{
                    tk.getProductName(),
                    tk.getSoLuong(),
                    n.format(tk.getGia()) + " VN??",
                    n.format((tk.getGia() * tk.getSoLuong())) + " VN??",
                    tk.getLyDo(),
                    "???? H???y"
                };
                model2.addRow(row);
            }
        }

        lbl.setText(n.format(tong) + " VN??");
        lblTM.setText(String.valueOf(tongM));
        lbl2.setText(n.format(tong2) + " VN??");
        lblTM2.setText(String.valueOf(tongM2));

    }

    @Override
    public void fillTableSanPhamNGAY(String ngay, JTable tbl, JTable tbl2, JLabel lbl, JLabel lblTM, JLabel lbl2, JLabel lblTM2) {
        DefaultTableModel model = (DefaultTableModel) tbl.getModel();
        model.setRowCount(0);
        DefaultTableModel model2 = (DefaultTableModel) tbl2.getModel();
        model2.setRowCount(0);
        float tong = 0;
        int tongM = 0;
        float tong2 = 0;
        int tongM2 = 0;
        float tien = 0;
        List<ThongKeSP> list2 = tkSanPhamDBNGAY(ngay);
        if (list2.size() != 0) {

            for (ThongKeSP tk : list2) {
                if (tk.isStatus() == false) {
                    if (tk.getDiscont() == 0) {
                        tien = (tk.getGia() * tk.getSoLuong());

                    } else {
                        tien = (tk.getGia() * tk.getSoLuong()) - ((tk.getGia() * tk.getSoLuong()) * tk.getDiscont()) / 100;
                    }
                    tong += tien;
                    tongM += tk.getSoLuong();
                    Object[] row = new Object[]{
                        tk.getProductName(),
                        tk.getSoLuong(),
                        n.format(tk.getGia()) + " VN??",
                        n.format(tien) + " VN??",
                        tk.getDiscont() + " %",
                        "???? b??n"
                    };
                    model.addRow(row);
                } else {
                    tong2 += (tk.getGia() * tk.getSoLuong());
                    tongM2 += tk.getSoLuong();
                    Object[] row = new Object[]{
                        tk.getProductName(),
                        tk.getSoLuong(),
                        n.format(tk.getGia()) + " VN??",
                        n.format((tk.getGia() * tk.getSoLuong())) + " VN??",
                        tk.getLyDo(),
                        "???? H???y"
                    };
                    model2.addRow(row);
                }
            }

            lbl.setText(n.format(tong) + " VN??");
            lblTM.setText(String.valueOf(tongM));
            lbl2.setText(n.format(tong2) + " VN??");
            lblTM2.setText(String.valueOf(tongM2));
        } else {
            lbl.setText(n.format(tong) + " VN??");
            lblTM.setText(String.valueOf(tongM));
            lbl2.setText(n.format(tong2) + " VN??");
            lblTM2.setText(String.valueOf(tongM2));
            ThongBao.alert(null, "Ng??y n??y kh??ng b??n ???????c g??");
        }

    }

    @Override
    public void fillTableSanPhamTHANG(int thang, JTable tbl, JTable tbl2, JLabel lbl, JLabel lblTM, JLabel lbl2, JLabel lblTM2) {
        DefaultTableModel model = (DefaultTableModel) tbl.getModel();
        model.setRowCount(0);
        DefaultTableModel model2 = (DefaultTableModel) tbl2.getModel();
        model2.setRowCount(0);
        float tong = 0;
        int tongM = 0;
        float tong2 = 0;
        int tongM2 = 0;
        float tien = 0;
        List<ThongKeSP> list2 = tkSanPhamDBTHANG(thang);
        if (list2.size() != 0) {

            for (ThongKeSP tk : list2) {
                if (tk.isStatus() == false) {
                    if (tk.getDiscont() == 0) {
                        tien = (tk.getGia() * tk.getSoLuong());

                    } else {
                        tien = (tk.getGia() * tk.getSoLuong()) - ((tk.getGia() * tk.getSoLuong()) * tk.getDiscont()) / 100;
                    }
                    tong += tien;
                    tongM += tk.getSoLuong();
                    Object[] row = new Object[]{
                        tk.getProductName(),
                        tk.getSoLuong(),
                        n.format(tk.getGia()) + " VN??",
                        n.format(tien) + " VN??",
                        tk.getDiscont() + " %",
                        "???? b??n"
                    };
                    model.addRow(row);
                } else {
                    tong2 += (tk.getGia() * tk.getSoLuong());
                    tongM2 += tk.getSoLuong();
                    Object[] row = new Object[]{
                        tk.getProductName(),
                        tk.getSoLuong(),
                        n.format(tk.getGia()) + " VN??",
                        n.format((tk.getGia() * tk.getSoLuong())) + " VN??",
                        tk.getLyDo(),
                        "???? H???y"
                    };
                    model2.addRow(row);
                }
            }
            lbl.setText(n.format(tong) + " VN??");
            lblTM.setText(String.valueOf(tongM));
            lbl2.setText(n.format(tong2) + " VN??");
            lblTM2.setText(String.valueOf(tongM2));
        } else {
            lbl.setText(n.format(tong) + " VN??");
            lblTM.setText(String.valueOf(tongM));
            lbl2.setText(n.format(tong2) + " VN??");
            lblTM2.setText(String.valueOf(tongM2));
            ThongBao.alert(null, "Th??ng n??y n??y ??? kh??ng b??n ???????c g?? =))");
        }

    }

    @Override
    public void fillTableSanPhamNAM(int nam, JTable tbl, JTable tbl2, JLabel lbl, JLabel lblTM, JLabel lbl2, JLabel lblTM2) {
        DefaultTableModel model = (DefaultTableModel) tbl.getModel();
        model.setRowCount(0);
        DefaultTableModel model2 = (DefaultTableModel) tbl2.getModel();
        model2.setRowCount(0);
        float tong = 0;
        int tongM = 0;
        float tong2 = 0;
        int tongM2 = 0;
        float tien = 0;
        List<ThongKeSP> list2 = tkSanPhamDBNAM(nam);
        if (list2.size() != 0) {

            for (ThongKeSP tk : list2) {
                if (tk.isStatus() == false) {
                    if (tk.getDiscont() == 0) {
                        tien = (tk.getGia() * tk.getSoLuong());

                    } else {
                        tien = (tk.getGia() * tk.getSoLuong()) - ((tk.getGia() * tk.getSoLuong()) * tk.getDiscont()) / 100;
                    }
                    tong += tien;
                    tongM += tk.getSoLuong();
                    Object[] row = new Object[]{
                        tk.getProductName(),
                        tk.getSoLuong(),
                        n.format(tk.getGia()) + " VN??",
                        n.format(tien) + " VN??",
                        tk.getDiscont() + " %",
                        "???? b??n"
                    };
                    model.addRow(row);
                } else {
                    tong2 += (tk.getGia() * tk.getSoLuong());
                    tongM2 += tk.getSoLuong();
                    Object[] row = new Object[]{
                        tk.getProductName(),
                        tk.getSoLuong(),
                        n.format(tk.getGia()) + " VN??",
                        n.format((tk.getGia() * tk.getSoLuong())) + " VN??",
                        tk.getLyDo(),
                        "???? H???y"
                    };
                    model2.addRow(row);
                }
            }
            lbl.setText(n.format(tong) + " VN??");
            lblTM.setText(String.valueOf(tongM));
            lbl2.setText(n.format(tong2) + " VN??");
            lblTM2.setText(String.valueOf(tongM2));

        } else {
            lbl.setText(n.format(tong) + " VN??");
            lblTM.setText(String.valueOf(tongM));
            lbl2.setText(n.format(tong2) + " VN??");
            lblTM2.setText(String.valueOf(tongM2));
            ThongBao.alert(null, "Ch???c ????ng c???a r???i! N??m n??y kh??ng b??n ???????c g??");
        }
    }

}
