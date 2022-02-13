/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package customer_package;

import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import java.sql.*; 
import fdms.dbconn;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class cust_home extends javax.swing.JFrame {
    
    Map<String, ImageIcon> imageMap;
    JList listdata;
    dbconn db;
    ArrayList<String> nameList;
    ResultSet RestNames;
    
    private JList calc(){
        
        imageMap = createImageMap(nameList);
        listdata = new JList(nameList.toArray());
        listdata.setCellRenderer(new ResaturentRenderer());
        return listdata;
    }
    public cust_home() {
        db=new dbconn();
        nameList=new ArrayList<String>();
        
        set_list();
        calc();
        initComponents();
        RName.setVisible(false);
        RPhone.setVisible(false);
        RDesc.setVisible(false);
        RRating.setVisible(false);
        RType.setVisible(false);
        OrderButton.setVisible(false);
        listdata.addMouseListener(new MouseAdapter() {
        public void mouseClicked(MouseEvent evt) {
        JList list = (JList)evt.getSource();
        set_details((String)list.getSelectedValue());
    }});
        
 
    }
    
    public void set_details(String st)
    {
        String query="SELECT * FROM RESTAURANT where RESTID='"+st+"'";
        try {
            ResultSet s=db.stmt.executeQuery(query);
            if(s.next())
            {
               RName.setText(s.getString(2));
               RPhone.setText(s.getString(3));
               RDesc.setText(s.getString(4));
               RRating.setText(s.getString(6));
               RType.setText(s.getString(5));
               
       
               
               if(RType.getText().equals("Veg"))
                   RType.setForeground(new Color(0, 46, 1));
               else
                   RType.setForeground(new Color(105, 24, 0));
               
               if(Float.parseFloat(s.getString(6))<4)
                   RRating.setForeground(Color.red);
               else if(Float.parseFloat(s.getString(6))<8)
                   RRating.setForeground(new Color(105, 24, 0));
               else
                   RRating.setForeground(new Color(0, 46, 1));
                   
                   
               
               
               RName.setVisible(true);
               RPhone.setVisible(true);
               RDesc.setVisible(true);
               RRating.setVisible(true);
               RType.setVisible(true);
               OrderButton.setVisible(true);

                ImageIcon img=new ImageIcon("src/imgs/"+s.getString(1)+".jpg");
                Image image = img.getImage();
                Image newimg = image.getScaledInstance(189, 189,  java.awt.Image.SCALE_SMOOTH);
                ImageIcon newIcon = new ImageIcon(newimg);
                Rlogo.setIcon(newIcon);
            }
        } catch (SQLException ex) {
            Logger.getLogger(cust_home.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public void set_list()
    {   
        String query="SELECT RESTID FROM RESTAURANT";
        try {
            ResultSet s=db.stmt.executeQuery(query);
            while(s.next())
            {
               nameList.add(s.getString(1));
         
               //System.out.println(s.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(cust_home.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    private Map<String, ImageIcon> createImageMap(ArrayList<String> list) {
        Map<String, ImageIcon> map = new HashMap<>();
        for (String s : list) {
            ImageIcon img=new ImageIcon("src/imgs/"+s+".jpg");
            Image image = img.getImage();
            Image newimg = image.getScaledInstance(70, 70,  java.awt.Image.SCALE_SMOOTH);
            ImageIcon newIcon = new ImageIcon(newimg);
            map.put(s,newIcon);

         
        }  
        return map;
    }
    
    public class ResaturentRenderer extends DefaultListCellRenderer
    {
        Font font = new Font("helvitica", Font.BOLD, 16);
        @Override
        public Component getListCellRendererComponent(
                JList list, Object value, int index,
                boolean isSelected, boolean cellHasFocus) {
            
            String query="SELECT NAME FROM RESTAURANT WHERE RESTID = '"+value.toString()+"'";
            try {
                RestNames=db.stmt.executeQuery(query);
            } catch (SQLException ex) {
                Logger.getLogger(cust_home.class.getName()).log(Level.SEVERE, null, ex);
            }
            JLabel label=null;
            try {
                if(RestNames.next())
                {
                    label = (JLabel) super.getListCellRendererComponent(
                            list, RestNames.getString(1), index, isSelected, cellHasFocus);
                    label.setIcon(imageMap.get((String) value));
                    label.setHorizontalTextPosition(JLabel.RIGHT);
                    label.setFont(font);
                }
       
            } catch (SQLException ex) {
                Logger.getLogger(cust_home.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
            return label;
        }

        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane3 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        custPane = new javax.swing.JScrollPane(listdata);
        jPanel1 = new javax.swing.JPanel();
        Rlogo = new javax.swing.JLabel();
        RName = new javax.swing.JLabel();
        RDesc = new javax.swing.JLabel();
        RType = new javax.swing.JLabel();
        RRating = new javax.swing.JLabel();
        OrderButton = new javax.swing.JButton();
        RPhone = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(new java.awt.Dimension(750, 750));

        custPane.setViewportBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        custPane.setPreferredSize(new java.awt.Dimension(100, 750));

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel1.setMinimumSize(new java.awt.Dimension(294, 441));

        Rlogo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        RName.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        RName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        RName.setText("DOMINOS");
        RName.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        RName.setName(""); // NOI18N

        RDesc.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        RDesc.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        RDesc.setText("jLabel2");
        RDesc.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        RType.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        RType.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        RType.setText("RType");

        RRating.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        RRating.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        RRating.setText("jLabel4");

        OrderButton.setFont(new java.awt.Font("Calisto MT", 1, 12)); // NOI18N
        OrderButton.setText("Order Food");
        OrderButton.setToolTipText("");
        OrderButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OrderButtonActionPerformed(evt);
            }
        });

        RPhone.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        RPhone.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        RPhone.setText("jLabel1");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(RType, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(OrderButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(RRating, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(RName, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(28, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(RDesc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(Rlogo, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(RPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(84, 84, 84))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(Rlogo, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(RName, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(RDesc, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(RType, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(RRating, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(RPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39)
                        .addComponent(OrderButton)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jMenuBar1.setPreferredSize(new java.awt.Dimension(723, 30));

        jMenu1.setText("Home");
        jMenu1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jMenu1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jMenu1.setPreferredSize(new java.awt.Dimension(60, 22));
        jMenuBar1.add(jMenu1);

        jMenu2.setEnabled(false);
        jMenu2.setFocusable(false);
        jMenu2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jMenu2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jMenu2.setPreferredSize(new java.awt.Dimension(625, 6));
        jMenu2.setRequestFocusEnabled(false);
        jMenu2.setRolloverEnabled(false);
        jMenu2.setVerifyInputWhenFocusTarget(false);
        jMenuBar1.add(jMenu2);

        jMenu3.setText("Cart");
        jMenu3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jMenu3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jMenu3.setPreferredSize(new java.awt.Dimension(65, 22));
        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(custPane, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(custPane, javax.swing.GroupLayout.DEFAULT_SIZE, 708, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void OrderButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OrderButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_OrderButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(cust_home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(cust_home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(cust_home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(cust_home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                    new cust_home().setVisible(true);
                
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton OrderButton;
    private javax.swing.JLabel RDesc;
    private javax.swing.JLabel RName;
    private javax.swing.JLabel RPhone;
    private javax.swing.JLabel RRating;
    private javax.swing.JLabel RType;
    public javax.swing.JLabel Rlogo;
    private javax.swing.JScrollPane custPane;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
