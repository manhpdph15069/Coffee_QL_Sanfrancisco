/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS_IServices;

import java.util.Date;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author phamd
 */
public interface IQLStatistical_Service {
    List<Object[]> getListOfArray(String sql, String[] cols, Object... args);
    public List<Object[]> getListByTKNgay(Date ngayDate);
    public void setDataNgay(JPanel pnlNgay,Date jdateNgay);
    public List<Object[]> getListByTKThang(int thang);
     public void setDataThang(JPanel pnlNgay, int thang);
     public List<Object[]> getListByTKNam(int nam);
     public void setDataNam(JPanel pnlNgay, int nam);
}
