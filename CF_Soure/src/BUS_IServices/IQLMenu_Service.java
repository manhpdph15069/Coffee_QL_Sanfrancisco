/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS_IServices;

import BUS_Models.SanPham;
import DAL_Models.ENTITY_Product;
import java.util.ArrayList;
import javax.swing.JTextField;

/**
 *
 * @author phamd
 */
public interface IQLMenu_Service {
    public ArrayList<ENTITY_Product> getSanPham();
    
    public void taoID(JTextField txt);

    public void updateSP(SanPham pro);

    public void insert(SanPham pro);
    public void delete(String IDProduct);
}
