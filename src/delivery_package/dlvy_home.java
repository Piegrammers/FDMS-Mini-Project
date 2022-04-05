package delivery_package;

import fdms.dbconn;
import fdms.main_login;
import java.awt.Color;
import java.awt.Image;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

public class dlvy_home extends javax.swing.JFrame {

    dbconn db;
    String user,cid,rid,oid;
    
    public dlvy_home(String u) {
        initComponents();
        user=u;
        db=new dbconn();
        refresh();
    }

    public void refresh()
    {
        ResultSet rs,rs1;
        
        try{
            rs=db.stmt.executeQuery("SELECT name,status FROM DeliveryBoy WHERE username='"+user+"'");
            if(rs.next())
            {
                tdname.setText(rs.getString(1).trim());
                btnavail.setVisible(true);
                
                ImageIcon img=new ImageIcon("src\\imgs\\xtras\\dlvy_icon_1.png");
                Image image = img.getImage();
                Image newimg = image.getScaledInstance(135, 135,  java.awt.Image.SCALE_SMOOTH);
                ImageIcon newIcon = new ImageIcon(newimg);
                tdimg.setIcon(newIcon);
                
                //tdimg.setHorizontalAlignment(SwingConstants.RIGHT);
                if(rs.getString(2).equals("Online"))
                {
                    btnavail.setSelected(true);
                    btnavail.setText("Online");
                    btnavail.setBackground(Color.GREEN);
                }
                else if(rs.getString(2).equals("Offline"))
                {
                    btnavail.setSelected(false);
                    btnavail.setText("Offline");
                    btnavail.setBackground(Color.RED);
                }
                else if(rs.getString(2).equals("Busy"))
                {
                    btnavail.setVisible(false);    
                }
            }
            rs=db.stmt.executeQuery("SELECT orderid,custid,restid,status FROM Orders WHERE DeliveryId='"+user+"' and status!='Delivered'");
            if(rs.next())
            {
                oid=rs.getString(1);
                cid=rs.getString(2);
                rid=rs.getString(3);
                txtorderid.setText(oid);
                if(rs.getString(4).equals("Ready"))
                {
                    txtordstatus.setText("Waiting for pick up");
                    btnpickup.setVisible(true);
                    btndeliver.setVisible(false);
                }
                else if(rs.getString(4).equals("Picked Up"))
                {
                    txtordstatus.setText("Order picked up");
                    btnpickup.setVisible(false);
                    btndeliver.setVisible(true);
                }
                rs1=db.stmt.executeQuery("SELECT name,address,phone FROM Customer WHERE username='"+cid+"'");
                if(rs1.next())
                {
                    tcname.setText(rs1.getString(1));
                    tcaddr.setText("<html><p style=\"text-align:left;\">"+rs1.getString(2)+"</p></html>");
                    tcphone.setText(rs1.getString(3));
                }
                rs1=db.stmt.executeQuery("SELECT name,address,phone FROM Restaurant WHERE restid='"+rid+"'");
                if(rs1.next())
                {
                    trname.setText(rs1.getString(1));
                    traddr.setText("<html><p style=\"text-align:left;\">"+rs1.getString(2)+"</p></html>");
                    trphone.setText(rs1.getString(3));
                }
                btnbill.setVisible(true);
                
                txtdelivery.setVisible(true);
                txtrest.setVisible(true);
                txtstatus.setVisible(true);
                txtordstatus.setVisible(true);
                
                tcname.setVisible(true);
                tcaddr.setVisible(true);
                tcphone.setVisible(true);
                
                trname.setVisible(true);
                traddr.setVisible(true);
                trphone.setVisible(true);
            }
            else
            {
                txtorderid.setText("No order assigned");
                txtdelivery.setVisible(false);
                txtrest.setVisible(false);
                txtstatus.setVisible(false);
                txtordstatus.setVisible(false);
                
                btnpickup.setVisible(false);
                btndeliver.setVisible(false);
                btnbill.setVisible(false);
                
                tcname.setVisible(false);
                tcaddr.setVisible(false);
                tcphone.setVisible(false);
                
                trname.setVisible(false);
                traddr.setVisible(false);
                trphone.setVisible(false);
            }
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tdimg = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        ctorder = new javax.swing.JLabel();
        txtdelivery = new javax.swing.JLabel();
        tcname = new javax.swing.JLabel();
        tcaddr = new javax.swing.JLabel();
        tcphone = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        txtrest = new javax.swing.JLabel();
        trname = new javax.swing.JLabel();
        traddr = new javax.swing.JLabel();
        trphone = new javax.swing.JLabel();
        txtstatus = new javax.swing.JLabel();
        btnbill = new javax.swing.JButton();
        txtorderid = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        btnavail = new javax.swing.JToggleButton();
        btnbusy = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        btnpickup = new javax.swing.JButton();
        btndeliver = new javax.swing.JButton();
        txtordstatus = new javax.swing.JLabel();
        btnback = new javax.swing.JButton();
        btnrefresh = new javax.swing.JButton();
        tdname = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(750, 680));
        setResizable(false);

        tdimg.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tdimg.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Availability");

        ctorder.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        ctorder.setText("Current order:");

        txtdelivery.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtdelivery.setText("Delivery details:");

        tcname.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        tcaddr.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        tcphone.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        txtrest.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtrest.setText("Restaurant details:");

        trname.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        traddr.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        trphone.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        txtstatus.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtstatus.setText("Status:");

        btnbill.setText("View bill");
        btnbill.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbillActionPerformed(evt);
            }
        });

        txtorderid.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtorderid.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnavail.setBackground(new java.awt.Color(0, 204, 0));
        btnavail.setText("Online");
        btnavail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnavailActionPerformed(evt);
            }
        });
        jPanel2.add(btnavail, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 70, 30));

        btnbusy.setBackground(new java.awt.Color(255, 102, 0));
        btnbusy.setText("Busy");
        jPanel2.add(btnbusy, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 70, 30));

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnpickup.setBackground(new java.awt.Color(255, 153, 0));
        btnpickup.setText("Picked up");
        btnpickup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnpickupActionPerformed(evt);
            }
        });
        jPanel3.add(btnpickup, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 112, 43));

        btndeliver.setBackground(new java.awt.Color(51, 204, 0));
        btndeliver.setText("Delivered");
        btndeliver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btndeliverActionPerformed(evt);
            }
        });
        jPanel3.add(btndeliver, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 112, 43));

        txtordstatus.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        btnback.setText("Back");
        btnback.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbackActionPerformed(evt);
            }
        });

        btnrefresh.setText("Refresh");
        btnrefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnrefreshActionPerformed(evt);
            }
        });

        tdname.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tdname.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tdname.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tdname, javax.swing.GroupLayout.DEFAULT_SIZE, 750, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(555, 555, 555)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnback)
                                .addGap(226, 226, 226)
                                .addComponent(tdimg, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 675, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtdelivery, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(ctorder, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtrest, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(txtstatus, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tcname, javax.swing.GroupLayout.PREFERRED_SIZE, 474, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tcphone, javax.swing.GroupLayout.PREFERRED_SIZE, 474, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(trphone, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(trname, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 474, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txtordstatus, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(33, 33, 33)
                                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addComponent(btnbill, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtorderid, javax.swing.GroupLayout.PREFERRED_SIZE, 382, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(47, 47, 47)
                                .addComponent(btnrefresh))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(179, 179, 179)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(traddr, javax.swing.GroupLayout.PREFERRED_SIZE, 530, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tcaddr, javax.swing.GroupLayout.PREFERRED_SIZE, 530, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(15, 15, 15))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(btnback))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(tdimg, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tdname, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ctorder, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtorderid, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtdelivery, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tcname, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tcaddr, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tcphone, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtrest, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(trname, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(traddr, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(trphone, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(btnbill, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtordstatus, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtstatus, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(114, 114, 114)
                        .addComponent(btnrefresh)))
                .addContainerGap(60, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnavailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnavailActionPerformed
        if(btnavail.isSelected())
        {
            try
            {
                db.stmt.execute("UPDATE DeliveryBoy SET status='Online' WHERE username='"+user+"'");
                btnavail.setBackground(Color.GREEN);
                btnavail.setText("Online");
                
            } catch (SQLException ex) {
                Logger.getLogger(dlvy_home.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else
        {
            try
            {
                db.stmt.execute("UPDATE DeliveryBoy SET status='Offilne' WHERE username='"+user+"'");
                btnavail.setBackground(Color.RED);
                btnavail.setText("Offline");
                
            } catch (SQLException ex) {
                Logger.getLogger(dlvy_home.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnavailActionPerformed

    private void btnpickupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnpickupActionPerformed
        
        try
            {
                db.stmt.execute("UPDATE ORDERS SET status='Picked Up' WHERE orderid='"+oid+"'");
                btnpickup.setVisible(false);
                btndeliver.setVisible(true);
                txtordstatus.setText("Order picked up");
            } catch (SQLException ex) {
                Logger.getLogger(dlvy_home.class.getName()).log(Level.SEVERE, null, ex);
            }
    }//GEN-LAST:event_btnpickupActionPerformed

    private void btndeliverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndeliverActionPerformed
        try
            {
                db.stmt.execute("UPDATE ORDERS SET status='Delivered' WHERE orderid='"+oid+"'");
                btndeliver.setVisible(true);
                db.stmt.execute("UPDATE DeliveryBoy SET status='Online' WHERE username='"+user+"'");
                btnavail.setBackground(Color.GREEN);
                btnavail.setText("Online");
                btnavail.setSelected(true);
                btnavail.setVisible(true);
                btndeliver.setVisible(false);
                txtordstatus.setText("Order delivered");
            } catch (SQLException ex) {
                Logger.getLogger(dlvy_home.class.getName()).log(Level.SEVERE, null, ex);
            }
    }//GEN-LAST:event_btndeliverActionPerformed

    private void btnbillActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbillActionPerformed
        try {
                db.end();
            } catch (SQLException ex) {
                Logger.getLogger(main_login.class.getName()).log(Level.SEVERE, null, ex);
            }
        dlvy_bill bill=new dlvy_bill(oid,user,2);
        dispose();
        bill.setVisible(true);
    }//GEN-LAST:event_btnbillActionPerformed

    private void btnbackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbackActionPerformed
        try {
                db.end();
            } catch (SQLException ex) {
                Logger.getLogger(main_login.class.getName()).log(Level.SEVERE, null, ex);
            }
        main_login ml=new main_login(2);
        dispose();
        ml.setVisible(true);
    }//GEN-LAST:event_btnbackActionPerformed

    private void btnrefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnrefreshActionPerformed
        refresh();
    }//GEN-LAST:event_btnrefreshActionPerformed

    /**
     * @param args the command line arguments
     */
    public void main(String args[]) {
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
//            java.util.logging.Logger.getLogger(dlvy_home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(dlvy_home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(dlvy_home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(dlvy_home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//        System.out.println("main");
//        /* Create and display the form */
////        java.awt.EventQueue.invokeLater(new Runnable() {
////            public void run() {
////                new dlvy_home().setVisible(true);
////            }
////        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton btnavail;
    private javax.swing.JButton btnback;
    private javax.swing.JButton btnbill;
    private javax.swing.JButton btnbusy;
    private javax.swing.JButton btndeliver;
    private javax.swing.JButton btnpickup;
    private javax.swing.JButton btnrefresh;
    private javax.swing.JLabel ctorder;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel tcaddr;
    private javax.swing.JLabel tcname;
    private javax.swing.JLabel tcphone;
    private javax.swing.JLabel tdimg;
    private javax.swing.JLabel tdname;
    private javax.swing.JLabel traddr;
    private javax.swing.JLabel trname;
    private javax.swing.JLabel trphone;
    private javax.swing.JLabel txtdelivery;
    private javax.swing.JLabel txtorderid;
    private javax.swing.JLabel txtordstatus;
    private javax.swing.JLabel txtrest;
    private javax.swing.JLabel txtstatus;
    // End of variables declaration//GEN-END:variables
}
