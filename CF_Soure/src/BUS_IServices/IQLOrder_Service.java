/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS_IServices;

import BUS_Models.SPChiTiet;
import DAL_Models.ENTITY_Area;
import DAL_Models.ENTITY_BILL;
import DAL_Models.ENTITY_Product;
import java.awt.Panel;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author phamd
 */
public interface IQLOrder_Service {

    public void taoTable(JPanel that, int cbbkhu, JButton btnVaoBan, JLabel lblBan, JTable tblOder, JTable tblLichSu, JPanel PanlPanelLS, JPanel Oder, JTextField txtMaHD, JTextField txtMaKH, JTextField txtNameEMP, JLabel TimeOrder,JTextField txtTong,JPanel PanCac);

    public ArrayList<ENTITY_Area> getkhu();

    public ArrayList<ENTITY_Product> getsp();

    public void hienTableSP(JTable tblSP);

    public void hienTableOder(JTable tblOD);

    public void bill(JTextField txtMaHD, JTextField txtNameEMP, JTextField txtMaKH, JLabel TimeOrder, JTable tblOrder);

    public void timSP(JTextField txt, JTable tbl);

    public void taoHD(JTextField txt);

    public void updatebnGuoi();
    public void updatebnThanhToan();

    public void thanhToan(JTextField txtMaHD);

    public void insertOr(JTextField txt);

    public void insertOderDe(ENTITY_BILL bill);
    public void updateOderDe(ENTITY_BILL bill);

    public void lichsuOrder(JPanel PanlPanelLS, JTable tblLichSu);

    public int dongC();
    public double tongTien(JTextField txttong,JTable tblOder);
}
