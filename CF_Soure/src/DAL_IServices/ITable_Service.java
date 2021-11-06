/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL_IServices;

import DAL_Models.ENTITY_Table;

import java.util.ArrayList;

/*
* @author : ThongPro
* @since : 11/6/2021 11:30 AM
* @description :
* @update :
*
* */
public interface ITable_Service {
    public void insert(ENTITY_Table entity);

    public void update(ENTITY_Table entity);

    public void delete(String IDTable);

    public ArrayList<ENTITY_Table> select();

    public ENTITY_Table findById(String IDTable);
}