/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS_IServices;

import DAL_Models.ENTITY_Table;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author phamd
 */
public interface IQLTable_Service {

    void fillTable(JTable tbl, String Area);

    void taoIDTable(JTextField maTable);

    void insertMATABLE(ENTITY_Table entity);
    
    void deleteTABLE(String ma);
}
