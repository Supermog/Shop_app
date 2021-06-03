/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shopapplication;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Nagy-Zsugya Bálint
 */
public final class WishListFrame extends javax.swing.JFrame {

    /**
     * Creates new form WishListFrame
     */
    public WishListFrame() {
        initComponents();
    }
    
    public WishListFrame(WishList mainWishList, Cart mainCart, Storage mainStorage) {
        initComponents();
        if(mainWishList.getWishList().size() > 0){
            DefaultTableModel model = (DefaultTableModel) wishListContentTable.getModel();
            model.setRowCount(0);
            mainWishList.jTableFill(model);
        }
        removeByButton(mainWishList);
        addToCartByButton(mainCart, mainStorage, mainWishList);
    }
    
    public void addToCartByButton(Cart mainCart, Storage mainStorage, WishList mainWishList){
        addToCartButton.addMouseListener(new MouseListener(){
        @Override
        public void mouseClicked(MouseEvent e){
            File wishList = new File("wishlist.txt");
            File cart = new File("cart.txt");
            DefaultTableModel model = (DefaultTableModel) wishListContentTable.getModel();
            int wareId = wishListContentTable.getSelectedRow();
            if(wareId >= 0){
                if(!mainWishList.getWishList().isEmpty()){
                    mainCart.addToCart(mainWishList.getWare(wareId));
                    mainWishList.removeById(wareId);
                    model.setRowCount(0);
                    mainWishList.jTableFill(model);
                } else {
                    System.out.println("You didn't select anything!");
                    }
            }
            else {
                model.setRowCount(0);
                    model.addRow(
                                    new Object[]{
                                        "nothing", null, null, null, null
                                    }
                        );
                }
            try {
                FileWriter wishListWriter = new FileWriter(wishList);
                if(!mainWishList.getWishList().isEmpty()){
                for(int i = 0; i < mainWishList.getWishList().size(); i++){
                    wishListWriter.write(mainWishList.getWishList().get(i).getName() + ";" + mainWishList.getWishList().get(i).getPrice() + ";" + mainWishList.getWishList().get(i).getCategory() + ";" + mainWishList.getWishList().get(i).getQuantity() + "\n");
                    }
                }
                wishListWriter.close();
            } catch (IOException ex) {
                Logger.getLogger(AddToCartJFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                FileWriter cartWriter = new FileWriter(cart);
                if(!mainCart.getCart().isEmpty()){
                for(int i = 0; i < mainCart.getCart().size(); i++){
                    cartWriter.write(mainCart.getCart().get(i).getName() + ";" + mainCart.getCart().get(i).getPrice() + ";" + mainCart.getCart().get(i).getCategory() + ";" + mainCart.getCart().get(i).getQuantity() + "\n");
                    }
                }
                cartWriter.close();
                }  catch (IOException ex) {
                Logger.getLogger(AddToCartJFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
    }
    
    public void removeByButton(WishList mainWishList){
        removeButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try{
                mainWishList.removeById(wishListContentTable.getSelectedRow());
                File cart = new File("cart.txt");
                try {
                FileWriter wishListWriter = new FileWriter("wishlist.txt");
                FileWriter cartWriter = new FileWriter(cart);
                if(!mainWishList.getWishList().isEmpty()){
                for(int i = 0; i < mainWishList.getWishList().size(); i++){
                    wishListWriter.write(mainWishList.getWishList().get(i).getName() + ";" + mainWishList.getWishList().get(i).getPrice() + ";" + mainWishList.getWishList().get(i).getCategory() + ";" + mainWishList.getWishList().get(i).getQuantity() + "\n");
                    }
                }
                wishListWriter.close();
//                if(!mainWishList.getWishList().isEmpty()){
//                for(int i = 0; i < mainWishList.getWishList().size(); i++){
//                    cartWriter.write(mainWishList.getWishList().get(i).getName() + ";" + mainWishList.getWishList().get(i).getPrice() + ";" + mainWishList.getWishList().get(i).getCategory() + ";" + mainWishList.getWishList().get(i).getQuantity() + "\n");
//                    }
//                }
//                cartWriter.close();
                }  catch (IOException ex) {
                Logger.getLogger(AddToCartJFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
                
                DefaultTableModel model = (DefaultTableModel) wishListContentTable.getModel();
                if(mainWishList.getWishList().size() > 0){
                    model.setRowCount(0);
                    mainWishList.jTableFill(model);
                } else {
                    model.setRowCount(0);
                    model.addRow(
                                    new Object[]{
                                        "nothing", null, null, null, null
                                    }
                        );
                    
                    }
                } catch (IndexOutOfBoundsException ex){
                    System.out.println("You can't remove nothing!");
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        greetingLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        wishListContentTable = new javax.swing.JTable();
        addToCartButton = new javax.swing.JButton();
        removeButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        greetingLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        greetingLabel.setText("The following items are on your wishlist:");

        wishListContentTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Nothing", null, null, null, null}
            },
            new String [] {
                "Name", "Category", "Quantity", "Price(one)", "Price(summed)"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(wishListContentTable);

        addToCartButton.setText("Add to cart");
        addToCartButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addToCartButtonActionPerformed(evt);
            }
        });

        removeButton.setText("Remove");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(addToCartButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(removeButton))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(greetingLabel)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 5, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(greetingLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addToCartButton)
                    .addComponent(removeButton))
                .addGap(10, 10, 10)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addToCartButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addToCartButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_addToCartButtonActionPerformed

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
            java.util.logging.Logger.getLogger(WishListFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(WishListFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(WishListFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(WishListFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new WishListFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addToCartButton;
    private javax.swing.JLabel greetingLabel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton removeButton;
    private javax.swing.JTable wishListContentTable;
    // End of variables declaration//GEN-END:variables
}
