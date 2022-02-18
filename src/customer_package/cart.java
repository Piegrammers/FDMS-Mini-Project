/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package customer_package;

import fdms.dbconn;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.NO_OPTION;
import static javax.swing.JOptionPane.YES_OPTION;

/**
 *
 * @author manuk
 */
public class cart extends javax.swing.JFrame {

    Map<String, ImageIcon> imageMap;
    JList listdata;
    dbconn db;
    ArrayList<String> foodIdList;
    ResultSet foodNames;
    String restId,custId,deliveryId;
    ArrayList<order_item> items;
    long orderId;
    float OTotal;
    public cart(ArrayList<order_item> items,int total,String restid,String custid) {
        setVisible(true);
        db=new dbconn();
        foodIdList=new ArrayList<String>();
        this.items=items;
        restId=restid;
        custId=custid;
        set_list(items);
        calc();
        initComponents();
        OTotal=total;
        JTotal.setText("₹"+total);
        String query="SELECT NAME FROM RESTAURANT WHERE RESTID = '"+restId+"'";
        try {
            ResultSet rs=db.stmt.executeQuery(query);
            if(rs.next())
            {
                RName.setText(rs.getString(1));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(cart.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
     private JList calc(){
        imageMap = createImageMap(foodIdList);
        listdata = new JList(foodIdList.toArray());
        listdata.setCellRenderer(new cart.FoodRenderer());
        return listdata;
    }
     
     private Map<String, ImageIcon> createImageMap(ArrayList<String> list) {
        Map<String, ImageIcon> map = new HashMap<>();
        for (String s : list) {
            ImageIcon img=new ImageIcon("src/imgs/food_imgs/"+s+".jpg");
            Image image = img.getImage();
            Image newimg = image.getScaledInstance(70, 70,  java.awt.Image.SCALE_SMOOTH);
            ImageIcon newIcon = new ImageIcon(newimg);
            map.put(s,newIcon);
        }  
        return map;
    }
     
     public class FoodRenderer extends DefaultListCellRenderer
    {
        Font font = new Font("consolas", Font.PLAIN, 16);
        @Override
        public Component getListCellRendererComponent(
                JList list, Object value, int index,
                boolean isSelected, boolean cellHasFocus) {
            
            String query="SELECT NAME,Price FROM FOOD WHERE FOODID = '"+value.toString()+"'";
            try {
                foodNames=db.stmt.executeQuery(query);
            } catch (SQLException ex) {
                Logger.getLogger(food_list.class.getName()).log(Level.SEVERE, null, ex);
            }
            JLabel label=null;
            int qty=0;
            String s;
            try {
                if(foodNames.next())
                {
                    for(order_item o:items)
                    {
                        if(o.foodid.equals(value.toString()))
                        {
                            qty=o.quantity;
                            break;
                        }
                        
                        
                    }
                    s=get_string(foodNames.getString(1),qty,Integer.parseInt(foodNames.getString(2))*qty);
                    label = (JLabel) super.getListCellRendererComponent(
                            list, s, index, isSelected, cellHasFocus);
                    label.setIcon(imageMap.get((String) value));
                    label.setHorizontalTextPosition(JLabel.RIGHT);
                    label.setFont(font);
           
                }
       
            } catch (SQLException ex) {
                Logger.getLogger(cart.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
            return label;
        }
     }
     
     public void set_list(ArrayList<order_item> items)
    {   
        
        for(order_item o : items)
        {
            foodIdList.add(o.foodid);
        }
        
         
    }
     
    public String get_string(String name,int quantity,int price)
    {
        String s=name+"  x"+quantity;
        int l=s.length();
        for(int i=0;i<60-l;i++)
            s+=" ";
        s=s+":  ₹"+price;
        return s;
        
    }
    
    public void place_order()
    {
        int dialogButton = JOptionPane.YES_NO_OPTION;
        int dialogResult = JOptionPane.showConfirmDialog (this, "Confirm this order?","Confirmation",dialogButton);
        if(dialogResult == JOptionPane.NO_OPTION){
          return;
        }
        String query="SELECT * FROM ORDERS ORDER BY ORDERID DESC";
        ResultSet rs,ds,cs;
        try {
            rs = db.stmt.executeQuery(query);
            if(rs.next())
            {
                orderId=Long.parseLong(rs.getString(1));
                orderId+=1;
            }
            else
                orderId=889100;
        
        } catch (SQLException ex) {
            Logger.getLogger(cart.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("read orders");
        query="SELECT * FROM DELIVERYBOY WHERE STATUS='Online' AND ROWNUM=1";
        try {
            ds=db.stmt.executeQuery(query);
            if(ds.next())
                deliveryId=ds.getString(1);
        } catch (SQLException ex) {
            Logger.getLogger(cart.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Delivery id :"+deliveryId);
        try {
            cs=db.stmt.executeQuery("Select * from Customer where USERNAME='"+custId+"'");
        } catch (SQLException ex) {
            Logger.getLogger(cart.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            db.stmt.executeQuery("INSERT INTO ORDERS VALUES('"+orderId+"','"+custId+"','"+deliveryId+"','"+restId+"',SYSDATE,"+OTotal+",'Ready')");
            
            
            for(order_item o:items)
            {
 
            
                try {
                ResultSet s=db.stmt.executeQuery("SELECT PRICE FROM FOOD WHERE FOODID='"+o.foodid+"'");
                if(s.next())
                {
                   float price=(Integer.parseInt(s.getString(1))*o.quantity);
                   db.stmt.executeQuery("INSERT INTO ORDERDETAILS VALUES('"+orderId+"','"+o.foodid+"',"+o.quantity+","+price+")");

                }
                } catch (SQLException ex) {
                    Logger.getLogger(food_list.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(cart.class.getName()).log(Level.SEVERE, null, ex);
        }

          
        JOptionPane.showMessageDialog(this, "Order confirmed!! Your order will be delivered soon.");
        orders o=new orders(custId);
        o.setVisible(true);
        dispose();
        
        }
        
        


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane(listdata);
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        RName = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        JTotal = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(750, 750));
        setResizable(false);

        jLabel1.setIcon(new javax.swing.ImageIcon("src/imgs/xtras/back.png"));
        jLabel1.setMaximumSize(new java.awt.Dimension(20, 20));
        jLabel1.setMinimumSize(new java.awt.Dimension(20, 20));

        RName.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        RName.setText("Rest Name");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(RName, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(RName)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Your Order :");

        jPanel3.setBackground(new java.awt.Color(0, 102, 51));
        jPanel3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jPanel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel3MouseClicked(evt);
            }
        });
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        JTotal.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        JTotal.setForeground(new java.awt.Color(255, 255, 255));
        JTotal.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        JTotal.setText("00");
        jPanel3.add(JTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 100, 20));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(153, 153, 153));
        jLabel5.setText("Total");
        jPanel3.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 37, 10));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Place Order");
        jPanel3.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 10, -1, -1));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(548, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(32, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator1)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(12, 12, 12)
                    .addComponent(jScrollPane1)
                    .addGap(12, 12, 12)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 565, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(98, 98, 98)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 553, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(99, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jPanel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel3MouseClicked
        place_order();
    }//GEN-LAST:event_jPanel3MouseClicked

    /**
     * @param args the command line arguments
     */


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel JTotal;
    private javax.swing.JLabel RName;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    // End of variables declaration//GEN-END:variables
}
