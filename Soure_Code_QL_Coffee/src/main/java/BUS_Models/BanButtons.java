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

    public BanButtons(JButton btnBan, String IDTalbe, int Status) {
        this.btnBan = btnBan;
        this.IDTalbe = IDTalbe;
        this.Status = Status;
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
}
