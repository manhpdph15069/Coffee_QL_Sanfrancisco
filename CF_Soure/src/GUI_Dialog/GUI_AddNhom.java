/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI_Dialog;

import BUS_IServices.IQLOrder_Service;
import BUS_Services.QLOrder_Service;
import DAL_Models.ENTITY_Table;
import Utils.JDBC;
import java.sql.ResultSet;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author Tran Van Thanh
 */
public class GUI_AddNhom extends javax.swing.JDialog {

    private IQLOrder_Service ql;
    private JPanel that;
    private JButton btnVaoBan;
    private JLabel lblBan;
    private JTable tblOder;
    private JTable tblLichSu;
    private JPanel PanlPanelLS;
    private JPanel Oder;
    private JTextField txtMaHD;
    private JTextField txtMaKH;
    private JTextField txtNameEMP;
    private JLabel TimeOrder;
    private JTextField txtTong;
    private JPanel PanCac;
    private ENTITY_Table ban;

    /**
     * Creates new form GUI_AddNhom
     */
    public GUI_AddNhom(JPanel parent, boolean moda, ENTITY_Table tbl, JPanel that, JButton btnVaoBan, JLabel lblBan, JTable tblOder, JTable tblLichSu, JPanel PanlPanelLS, JPanel Oder, JTextField txtMaHD, JTextField txtMaKH, JTextField txtNameEMP, JLabel TimeOrder, JTextField txtTong, JPanel PanCac) {
        initComponents();
        finCbbNhom();
        this.ql = new QLOrder_Service(that, btnVaoBan, lblBan, tblOder, tblLichSu, PanlPanelLS, Oder, txtMaHD, txtMaKH, txtNameEMP, TimeOrder, txtTong, PanCac);
        this.ban = tbl;
        this.that = that;
        this.btnVaoBan = btnVaoBan;
        this.lblBan = lblBan;
        this.tblOder = tblOder;
        this.tblLichSu = tblLichSu;
        this.PanlPanelLS = PanlPanelLS;
        this.Oder = Oder;
        this.txtMaHD = txtMaHD;
        this.txtMaKH = txtMaKH;
        this.txtNameEMP = txtNameEMP;
        this.TimeOrder = TimeOrder;
        this.txtTong = txtTong;
        this.PanCac = PanCac;
        this.setLocationRelativeTo(null);
    }

    public void finCbbNhom() {
        String sql = "SELECT TableGroup FROM [Table]";
        this.cbbTenNhom.removeAllItems();
        this.cbbTenNhom.addItem("Chưa có nhóm");
        try {
            ResultSet rs = JDBC.query(sql);
            while (rs.next()) {
                if (rs.getString("TableGroup") != null) {
                    this.cbbTenNhom.removeItem("Chưa có nhóm");
                    this.cbbTenNhom.addItem(rs.getString("TableGroup"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cbbTenNhom = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(153, 255, 255));

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel1.setText("Thêm vào nhóm");

        jLabel2.setText("Tên nhóm :");

        cbbTenNhom.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jButton1.setText("Xác nhận");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(145, 145, 145)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(cbbTenNhom, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(154, 154, 154)
                        .addComponent(jButton1)))
                .addContainerGap(117, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cbbTenNhom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addComponent(jButton1)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        this.ql.taoNhom(this.cbbTenNhom.getSelectedItem().toString(), ban.getIDTable());
        this.ql.taoTable(that, ban.getIDArea(), btnVaoBan, lblBan, tblOder, tblLichSu, PanlPanelLS, Oder, txtMaHD, txtMaKH, txtNameEMP, TimeOrder, txtTong, PanCac,null,null,null);
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(GUI_AddNhom.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(GUI_AddNhom.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(GUI_AddNhom.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(GUI_AddNhom.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the dialog */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                GUI_AddNhom dialog = new GUI_AddNhom(new javax.swing.JFrame(), true);
//                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
//                    @Override
//                    public void windowClosing(java.awt.event.WindowEvent e) {
//                        System.exit(0);
//                    }
//                });
//                dialog.setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cbbTenNhom;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
