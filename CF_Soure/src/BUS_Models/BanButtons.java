/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS_Models;

import javax.swing.JButton;

/**
 *
 * @author Tran Van Thanh
 */
public class BanButtons {

    private JButton btnBan;
    private String IDTalbe;
    private int Status;
    private int ten;

    public BanButtons() {
    }

    public BanButtons(JButton btnBan, String IDTalbe, int Status, int ten) {
        this.btnBan = btnBan;
        this.IDTalbe = IDTalbe;
        this.Status = Status;
        this.ten = ten;
    }

    public JButton getBtnBan() {
        return btnBan;
    }

    public String getIDTalbe() {
        return IDTalbe;
    }

    public int getStatus() {
        return Status;
    }

    public int getTen() {
        return ten;
    }


}
