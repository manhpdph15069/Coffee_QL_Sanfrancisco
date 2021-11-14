/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL_Models;

/*
* @author : ThongPro
* @since : 11/5/2021 10:50 PM
* @description : 
* @update : 
*
* */
public class ENTITY_Table {
private String IDTable;
private int Location;
private int Status;
private String IDArea;

    public ENTITY_Table() {
    }

    public String getIDTable() {
        return IDTable;
    }

    public void setIDTable(String IDTable) {
        this.IDTable = IDTable;
    }

    public int getLocation() {
        return Location;
    }

    public void setLocation(int location) {
        Location = location;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status = status;
    }

    public String getIDArea() {
        return IDArea;
    }

    public void setIDArea(String IDArea) {
        this.IDArea = IDArea;
    }
}
