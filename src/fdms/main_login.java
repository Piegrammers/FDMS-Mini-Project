package fdms;

import java.sql.ResultSet;
import customer_package.cust_home;
import delivery_package.dlvy_home;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
public class main_login extends javax.swing.JFrame {

    dbconn db = null;
    int usertype;
    public main_login(int u) {
        usertype=u;
        db = new dbconn();
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        userfield = new javax.swing.JTextField();
        passfield = new javax.swing.JPasswordField();
        jLabel1 = new javax.swing.JLabel();
        label = new javax.swing.JLabel();
        lregister = new javax.swing.JLabel();
        btnback = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Password");

        jButton1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton1.setText("LOGIN");
        jButton1.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("LOGIN");
        jLabel3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        userfield.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        passfield.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Username");

        label.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        label.setForeground(new java.awt.Color(255, 0, 0));
        label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        lregister.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lregister.setText("Don't have an account? Registrer now!");
        lregister.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lregisterMouseClicked(evt);
            }
        });

        btnback.setText("Back");
        btnback.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbackActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lregister, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(75, 75, 75))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(184, 184, 184)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(label, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2))
                                .addGap(62, 62, 62)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(passfield, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(userfield, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(btnback)))
                .addContainerGap(191, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(btnback)
                .addGap(43, 43, 43)
                .addComponent(jLabel3)
                .addGap(74, 74, 74)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(userfield, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(passfield, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lregister, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(94, 94, 94)
                .addComponent(label, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(105, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String user = userfield.getText().trim();
        String pass = passfield.getText().trim();
        String table="";
        if(usertype==1)
            table="CustLogin";
        else if(usertype==2)
            table="DeliveryLogin";
        String query= "SELECT PASSWORD FROM "+table+" WHERE username='"+user+"'";
        try
        {
          ResultSet rs = db.stmt.executeQuery(query);
          if(rs.next())
          {
              if(pass.hashCode()==rs.getInt(1))
              {
                  db.end();
                  dispose();
                  if(usertype==1)
                  {
                    cust_home ch = new cust_home(user);
                    ch.setVisible(true);
                    dispose();
                  }
                  else if(usertype==2)
                  {
                      dlvy_home dh=new dlvy_home(user);
                      dh.setVisible(true);
                  }
              }
              else
                  label.setText("Wrong password");
          }
          else
          {
              label.setText("Invalid username");
          }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void lregisterMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lregisterMouseClicked

        if(usertype==1)
        {
            try {
                db.end();
            } catch (SQLException ex) {
                Logger.getLogger(main_login.class.getName()).log(Level.SEVERE, null, ex);
            }
            cust_signup cs=new cust_signup();
            dispose();
            cs.setVisible(true);
        }
        else if(usertype==2)
        {
            try {
                db.end();
            } catch (SQLException ex) {
                Logger.getLogger(main_login.class.getName()).log(Level.SEVERE, null, ex);
            }
            dlvy_signup ds=new dlvy_signup();
            dispose();
            ds.setVisible(true);
        }
    }//GEN-LAST:event_lregisterMouseClicked

    private void btnbackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbackActionPerformed

        try {
                db.end();
            } catch (SQLException ex) {
                Logger.getLogger(main_login.class.getName()).log(Level.SEVERE, null, ex);
            }
        user_type ut=new user_type();
        ut.setVisible(true);
        dispose();
    }//GEN-LAST:event_btnbackActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnback;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel label;
    private javax.swing.JLabel lregister;
    private javax.swing.JPasswordField passfield;
    private javax.swing.JTextField userfield;
    // End of variables declaration//GEN-END:variables
}
