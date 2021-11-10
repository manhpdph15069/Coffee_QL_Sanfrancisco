/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS_Services;

import BUS_IServices.*;
import BUS_Models.BanButtons;
import DAL_IServices.ITable_Service;
import DAL_Models.ENTITY_Table;
import DAL_Services.Table_Service;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;

/**
 *
 * @author Trần Văn Thành
 */
public class QLOrder_Service implements IQLOrder_Service {

    private final int ICON_WIDTH = 48;
    private final int ICON_HEIGHT = 48;
    private ITable_Service qlb;
    private ITable_Service qlk;

    private Map<JButton, BanButtons> banButtonList = new HashMap<>();    
    private JPanel pnlMain;
    private ArrayList<ENTITY_Table> listBan;
    private JButton firstButton;

    public QLOrder_Service() {
        this.qlb = new Table_Service();
        this.listBan = new ArrayList<ENTITY_Table>();
        this.pnlMain = new JPanel();
    }

    @Override
    public void taoTable(JFrame that) {
        ClassLoader classLoader = that.getClass().getClassLoader();
        pnlMain.removeAll();
        pnlMain.setBounds(10, 100, 800, 500);
        pnlMain.setBorder(BorderFactory.createLineBorder(Color.black));
        for (ENTITY_Table ban : listBan) {
            URL imagePath = classLoader.getResource("logoban/" + ban.getLocation() + ".png");
            Image imgBan = new ImageIcon(imagePath).getImage();
            Icon iconBan = new ImageIcon(imgBan.getScaledInstance(ICON_WIDTH, ICON_HEIGHT, imgBan.SCALE_SMOOTH));
            
            JButton button = new JButton();
            button.setIcon(iconBan);
            button.setBorder(UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"));
            button.setPreferredSize(new Dimension(125, 60));
            button.setText("Bàn" + String.valueOf(ban.getLocation()));
            int cs = ban.getStatus() ? 1 : 0;
            switch (cs) {
                case 0:
                    button.setBackground(Color.GREEN);                    
                    break;
                case 1:
                    button.setBackground(Color.red);
                    break;
            }
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    TableSelectedHandler(e);
                }
            });
            pnlMain.add(button);
            BanButtons banButton = new BanButtons(button, ban.getIDTable(), cs);
            banButtonList.put(button, banButton);
        }
        that.add(pnlMain);
        // Clearing my JFrame and render again
        that.revalidate();
        that.repaint();
    }

    private void TableSelectedHandler(ActionEvent e) {
        if (e.getSource().getClass() == JButton.class) {
            JButton selectedButton = (JButton) e.getSource();

            BanButtons banButton = banButtonList.get(selectedButton);
            System.out.println("-Trạng Thái : " + banButton.getStatus() + " - IDTable: " + banButton.getIDTalbe());
            System.out.println("Bàn :" + banButton.getIDTalbe() + "vào bàn");
            if (firstButton == null) {
                firstButton = selectedButton;
                firstButton.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.red), UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border")));
            } else {
                int firstButtonIconIndex = banButtonList.get(firstButton).getStatus();
                int secondButtonIconIndex = banButtonList.get(selectedButton).getStatus();
                String firbtn = banButtonList.get(firstButton).getIDTalbe();
                String senbtn = banButtonList.get(selectedButton).getIDTalbe();
                if (firstButton != selectedButton && firstButtonIconIndex == secondButtonIconIndex) {
                    System.out.println("Gộp bàn");
                } else if (firstButton != selectedButton && firstButtonIconIndex != secondButtonIconIndex) {
                    System.out.println("Chuyển bàn");
                } else {
                    firstButton.setBorder(UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"));
                }
                if (firstButton == selectedButton) {
                    System.out.println("Chọn lại cc à ");
                    firstButton.setBorder(UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"));
                }
                firstButton = null;
            }
        }
    }

    @Override
    public ArrayList<Object> getkhu() {

    }

}
