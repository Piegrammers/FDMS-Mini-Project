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
       
    }
    public void set_list()
    {   
        String query="SELECT RESTID FROM RESTAURANT";
        try {
            ResultSet s=db.stmt.executeQuery(query);
            while(s.next())
            {
               nameList.add(s.getString(1));
               System.out.println(s.getString(1));
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
            Image newimg = image.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH);
            ImageIcon newIcon = new ImageIcon(newimg);
            map.put(s,newIcon);

         
        }  
        return map;
    }
    
    public class ResaturentRenderer extends DefaultListCellRenderer
    {
        Font font = new Font("helvitica", Font.PLAIN, 16);
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

        jScrollPane1 = new javax.swing.JScrollPane(listdata);
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(750, 750));
        setResizable(false);
        setSize(new java.awt.Dimension(750, 750));

        jScrollPane1.setPreferredSize(new java.awt.Dimension(100, 750));

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
        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(338, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 708, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
