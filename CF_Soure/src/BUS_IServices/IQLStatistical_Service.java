/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS_IServices;

import java.util.Date;
import java.util.List;

/**
 *
 * @author phamd
 */
public interface IQLStatistical_Service {
    List<Object[]> getListOfArray(String sql, String[] cols, Object... args);
    public List<Object[]> getListByTKNgay(String ngay);
}
