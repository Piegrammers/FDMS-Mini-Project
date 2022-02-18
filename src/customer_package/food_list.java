
package customer_package;

import fdms.dbconn;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class food_list extends javax.swing.JFrame {
    
    Map<String, ImageIcon> imageMap;
    JList listdata;
    dbconn db;
    ArrayList<String> foodIdList;
    ResultSet foodNames;
    ArrayList<order_item> items;
    String selectedRest;
    String custId;
    int total;
    private JList calc(){
        imageMap = createImageMap(foodIdList);
        listdata = new JList(foodIdList.toArray());
        listdata.setCellRenderer(new food_list.FoodRenderer());
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
        Font font = new Font("helvitica", Font.BOLD, 16);
        @Override
        public Component getListCellRendererComponent(
                JList list, Object value, int index,
                boolean isSelected, boolean cellHasFocus) {
            
            String query="SELECT NAME FROM FOOD WHERE FOODID = '"+value.toString()+"'";
            try {
                foodNames=db.stmt.executeQuery(query);
            } catch (SQLException ex) {
                Logger.getLogger(food_list.class.getName()).log(Level.SEVERE, null, ex);
            }
            JLabel label=null;
            try {
                if(foodNames.next())
                {
                    label = (JLabel) super.getListCellRendererComponent(
                            list, foodNames.getString(1), index, isSelected, cellHasFocus);
                    label.setIcon(imageMap.get((String) value));
                    label.setHorizontalTextPosition(JLabel.RIGHT);
                    label.setFont(font);
                }
       
            } catch (SQLException ex) {
                Logger.getLogger(food_list.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
            return label;
        }

        
    }
    
    public void set_list()
    {   
        String query="SELECT FOODID FROM FOOD";
        try {
            ResultSet s=db.stmt.executeQuery(query);
            while(s.next())
            {
               foodIdList.add(s.getString(1));
        
            }
        } catch (SQLException ex) {
            Logger.getLogger(food_list.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public void total()
    {
        int count=0;
        total=0;
        for(order_item o:items)
        {
            String query="SELECT price FROM FOOD where foodid='"+o.foodid+"'";
            
            try {
            ResultSet s=db.stmt.executeQuery(query);
            if(s.next())
            {
               total+=(Integer.parseInt(s.getString(1))*o.quantity);
               count+=o.quantity;
        
            }
        } catch (SQLException ex) {
            Logger.getLogger(food_list.class.getName()).log(Level.SEVERE, null, ex);
        }
        String st=count + " items  | ₹"+ total;
        TCount.setText(st);
        
        }
        if(total>0)
            TPanel.setVisible(true);
        else
            TPanel.setVisible(false);
    }
    
    public void set_header(String s)
    {
        ImageIcon img=new ImageIcon("src/imgs/rest_imgs/"+s+".jpg");
        Image image = img.getImage();
        Image newimg = image.getScaledInstance(100, 100,  java.awt.Image.SCALE_SMOOTH);
        ImageIcon newIcon = new ImageIcon(newimg);
        RImg.setIcon(newIcon);
        
        String query="SELECT NAME FROM RESTAURANT WHERE RESTID = '"+s+"'";
        try {
            ResultSet rs=db.stmt.executeQuery(query);
            if(rs.next())
            {
                RName.setText(rs.getString(1));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(food_list.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public food_list(String restId,String custid) {
        db=new dbconn();
        foodIdList=new ArrayList<String>();
        items=new ArrayList<order_item>();
        selectedRest=restId;
        custId=custid;
        set_list();
        calc();
        initComponents();
        set_header(restId);
        
        
        
        FName.setVisible(false);
        FPrice.setVisible(false);
        FType.setVisible(false);
        quantity.setVisible(false);
        FName.setVisible(false);
        itemdesc.setVisible(false);
        BAdd.setVisible(false);
        TPanel.setVisible(false);
        Tlogo.setVisible(false);
        listdata.addMouseListener(new MouseAdapter() {
        public void mouseClicked(MouseEvent evt) {
        JList list = (JList)evt.getSource();
        set_details((String)list.getSelectedValue());
       
    }});
        
        quantity.addChangeListener(new ChangeListener(){
        public void stateChanged(ChangeEvent e) {
            for(order_item o : items)
            {
                if(((String)listdata.getSelectedValue()).equals(o.foodid))
                        {
                            o.quantity=(int)quantity.getValue();
                            set_desc(o);
                            
                            if(o.quantity==0)
                            {
                                items.remove(o);
                                quantity.setVisible(false);
                                BAdd.setVisible(true);
                                itemdesc.setVisible(false);
                            }
                            
                            break;
                        }
                
            }
            total();
        }
        });
 
    }
    
    void set_details(String st)
    {
        String query="SELECT * FROM FOOD where foodId='"+st+"'";
        try {
            ResultSet s=db.stmt.executeQuery(query);
            if(s.next())
            {
                int flag=0;
                FName.setText(s.getString(2));
                FPrice.setText("₹"+s.getString(3));
                ImageIcon img2=new ImageIcon("src/imgs/xtras/"+s.getString(4)+".jpg");
                FType.setText(s.getString(4).toUpperCase());
                
                Image image2 = img2.getImage();
                Image newimg2 = image2.getScaledInstance(20, 20,  java.awt.Image.SCALE_SMOOTH);
                ImageIcon newIcon2 = new ImageIcon(newimg2);
                Tlogo.setIcon(newIcon2);
                
                ImageIcon img=new ImageIcon("src/imgs/food_imgs/"+s.getString(1)+".jpg");
                Image image = img.getImage();
                Image newimg = image.getScaledInstance(189, 189,  java.awt.Image.SCALE_SMOOTH);
                ImageIcon newIcon = new ImageIcon(newimg);
                FImg.setIcon(newIcon);
                
                for(order_item o: items)
               {
                    if(o.foodid.equals(s.getString(1)))
                    {
                        quantity.setValue(o.quantity);
                        quantity.setVisible(true);
                        set_desc(o);
                        BAdd.setVisible(false);
                        flag=1;
                        break;
                    }
               
               }
                if(flag==0)
                {
                    BAdd.setVisible(true);
                    quantity.setVisible(false);
                    itemdesc.setVisible(false);
                }
                FName.setVisible(true);
                FPrice.setVisible(true);
                FType.setVisible(true);
                BAdd.setVisible(true);
                Tlogo.setVisible(true);
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(food_list.class.getName()).log(Level.SEVERE, null, ex);
        }
        total();
    }
    public void set_desc(order_item o)
    {
    String d;
        String query="SELECT Name,Price FROM FOOD where foodId='"+o.foodid+"'";
        try {
        ResultSet s=db.stmt.executeQuery(query);
        if(s.next())
        {
        d="In Cart :"+o.quantity+"x "+s.getString(1)+"  :₹"+(Integer.parseInt(s.getString(2))*o.quantity);
        itemdesc.setText(d);
        itemdesc.setVisible(true);

        }
        } catch (SQLException ex) {
        Logger.getLogger(food_list.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        foodPane = new javax.swing.JScrollPane(listdata);
        jPanel1 = new javax.swing.JPanel();
        RImg = new javax.swing.JLabel();
        RName = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        FImg = new javax.swing.JLabel();
        FName = new javax.swing.JLabel();
        FPrice = new javax.swing.JLabel();
        itemdesc = new javax.swing.JLabel();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        quantity = new javax.swing.JSpinner();
        BAdd = new javax.swing.JButton();
        TPanel = new javax.swing.JPanel();
        TCount = new javax.swing.JLabel();
        TCart = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        FType = new javax.swing.JLabel();
        Tlogo = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();

        jMenuItem1.setText("jMenuItem1");

        jMenuItem2.setText("jMenuItem2");

        jMenuItem3.setText("jMenuItem3");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(750, 750));
        setPreferredSize(new java.awt.Dimension(750, 750));
        setResizable(false);

        foodPane.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        RImg.setMaximumSize(new java.awt.Dimension(100, 100));
        RImg.setPreferredSize(new java.awt.Dimension(100, 100));
        RImg.setVerifyInputWhenFocusTarget(false);

        RName.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        RName.setText("jLabel2");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(RImg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(RName, javax.swing.GroupLayout.PREFERRED_SIZE, 362, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(RImg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(RName)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setMaximumSize(new java.awt.Dimension(440, 446));
        jPanel2.setMinimumSize(new java.awt.Dimension(450, 446));
        jPanel2.setPreferredSize(new java.awt.Dimension(450, 446));

        FImg.setMaximumSize(new java.awt.Dimension(189, 189));
        FImg.setMinimumSize(new java.awt.Dimension(189, 189));
        FImg.setPreferredSize(new java.awt.Dimension(189, 189));

        FName.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        FName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        FName.setText("jLabel2");
        FName.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        FPrice.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        FPrice.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        FPrice.setText("jLabel3");

        itemdesc.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        itemdesc.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        itemdesc.setText("2x Maasala Dosa : 50rs");
        itemdesc.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLayeredPane1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        quantity.setValue(1);
        quantity.setVerifyInputWhenFocusTarget(false);
        quantity.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                quantityMousePressed(evt);
            }
        });
        jLayeredPane1.add(quantity, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 0, 70, -1));

        BAdd.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        BAdd.setText("ADD");
        BAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BAddActionPerformed(evt);
            }
        });
        jLayeredPane1.add(BAdd, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 0, -1, -1));

        TPanel.setBackground(new java.awt.Color(0, 153, 0));
        TPanel.setForeground(new java.awt.Color(0, 0, 0));
        TPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                go_to_cart(evt);
            }
        });

        TCount.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        TCount.setText("jLabel1");

        TCart.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        TCart.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        TCart.setText("Cart");

        javax.swing.GroupLayout TPanelLayout = new javax.swing.GroupLayout(TPanel);
        TPanel.setLayout(TPanelLayout);
        TPanelLayout.setHorizontalGroup(
            TPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TPanelLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(TCount, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 153, Short.MAX_VALUE)
                .addComponent(TCart, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );
        TPanelLayout.setVerticalGroup(
            TPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(TCount, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(TCart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        FType.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        FType.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        FType.setText("jLabel4");
        jPanel3.add(FType);
        jPanel3.add(Tlogo);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(124, 124, 124)
                .addComponent(FImg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLayeredPane1)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(FName, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 382, Short.MAX_VALUE))
                            .addComponent(itemdesc, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(FPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 388, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(FImg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(FName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(FPrice)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(itemdesc)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(TPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        FImg.getAccessibleContext().setAccessibleDescription("");

        jMenuBar1.setMaximumSize(new java.awt.Dimension(750, 23));
        jMenuBar1.setPreferredSize(new java.awt.Dimension(750, 23));

        jMenu1.setText("Home");
        jMenu1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu1MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu1);

        jMenu2.setEnabled(false);
        jMenu2.setFocusable(false);
        jMenu2.setMinimumSize(new java.awt.Dimension(678, 6));
        jMenu2.setPreferredSize(new java.awt.Dimension(650, 6));
        jMenuBar1.add(jMenu2);

        jMenu3.setText("Cart");
        jMenu3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu3MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator1)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(foodPane, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 392, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(foodPane, javax.swing.GroupLayout.DEFAULT_SIZE, 563, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 563, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void go_to_cart(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_go_to_cart
        try {
            db.end();
        } catch (SQLException ex) {
            Logger.getLogger(food_list.class.getName()).log(Level.SEVERE, null, ex);
        }
        cart c=new cart(items,total,selectedRest,custId);
        dispose();
    }//GEN-LAST:event_go_to_cart

    private void BAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BAddActionPerformed
        quantity.setValue(1);
        order_item item=new order_item((String)listdata.getSelectedValue(),(int)quantity.getValue());
        BAdd.setVisible(false);
        set_desc(item);
        quantity.setVisible(true);
        items.add(item);
        item.display();
        total();
    }//GEN-LAST:event_BAddActionPerformed

    private void quantityMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_quantityMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_quantityMousePressed

    private void jMenu3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu3MouseClicked
        try {
            db.end();
        } catch (SQLException ex) {
            Logger.getLogger(food_list.class.getName()).log(Level.SEVERE, null, ex);
        }
        cart c=new cart(items,total,selectedRest,custId);
        dispose();

    }//GEN-LAST:event_jMenu3MouseClicked

    private void jMenu1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu1MouseClicked
        try {
            db.end();
        } catch (SQLException ex) {
            Logger.getLogger(food_list.class.getName()).log(Level.SEVERE, null, ex);
        }
        cust_home ch=new cust_home(custId);
        ch.setVisible(true);
        dispose();
    }//GEN-LAST:event_jMenu1MouseClicked

    /**
     * @param args the command line arguments
     */


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BAdd;
    private javax.swing.JLabel FImg;
    private javax.swing.JLabel FName;
    private javax.swing.JLabel FPrice;
    private javax.swing.JLabel FType;
    private javax.swing.JLabel RImg;
    private javax.swing.JLabel RName;
    private javax.swing.JLabel TCart;
    private javax.swing.JLabel TCount;
    private javax.swing.JPanel TPanel;
    private javax.swing.JLabel Tlogo;
    private javax.swing.JScrollPane foodPane;
    private javax.swing.JLabel itemdesc;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSpinner quantity;
    // End of variables declaration//GEN-END:variables
}
