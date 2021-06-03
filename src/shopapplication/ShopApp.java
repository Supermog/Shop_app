/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shopapplication;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Nagy-Zsugya Bálint
 */
public class ShopApp extends javax.swing.JFrame {

    /**
     * Creates new form ShopApp
     * @throws java.io.IOException
     */
    
    Cart mainCart;
    Storage mainStorage;
    WishList mainWishList;
    Admin admin;
    
    public ShopApp() throws IOException {
        initComponents();
        mainStorage = new Storage();
        mainCart = new Cart();
        admin = new Admin();
        mainWishList = new WishList();
        mainStorage.jTableFill((DefaultTableModel) waresTable.getModel());
        search(mainStorage);
        cartInitiate();
        wishListInitiate();
        adminInitiate();
    }
    
    private void adminInitiate() throws FileNotFoundException{
        File admin = new File("orders.txt");
        if(admin.length() != 0){
            Scanner scan = new Scanner(admin);
            String firstLine = "";
            String secondLine = "";
            while(scan.hasNextLine()){
                firstLine = scan.nextLine();
                secondLine = scan.nextLine();
                String[] datas = firstLine.split(";");
                String cardHoldersName = datas[0];
                String cardHoldersAddress = datas[1];
                String cardNumber = datas[2];
                String[] wares = secondLine.split(":");
                ArrayList<Ware> orderedWares = new ArrayList();
                for(int i = 0; i < wares.length; i++){
                    String[] wareDatas = wares[i].split(";");
                    Ware currentWare = new Ware(wareDatas[0], Integer.parseInt(wareDatas[2]), wareDatas[1], Integer.parseInt(wareDatas[3]));
                    orderedWares.add(currentWare);
                }
                this.admin.addToAdmin(new Order(cardNumber, cardHoldersName, cardHoldersAddress, orderedWares));
            }
        }
    }
    
    private void cartInitiate() throws FileNotFoundException{
        File cart = new File("cart.txt");
        if(cart.length() != 0){
        Scanner scan = new Scanner(cart);
        String line = "";
        while(scan.hasNextLine()){
            line = scan.nextLine();
            String[] data = line.split(";");
            mainCart.addToCart(new Ware(data[0], Integer.parseInt(data[1]), data[2], Integer.parseInt(data[3])));
        }
        scan.close();
        }
    }
    
    private void wishListInitiate() throws FileNotFoundException{
        File wishList = new File("wishlist.txt");
        if(wishList.length() != 0){
        Scanner scan = new Scanner(wishList);
        String line = "";
        while(scan.hasNextLine()){
            line = scan.nextLine();
            String[] data = line.split(";");
            mainWishList.addToWishList(new Ware(data[0], Integer.parseInt(data[1]), data[2], Integer.parseInt(data[3])));
        }
        scan.close();
        }
    }
    
    
    
    private void search(Storage storage1){
        searchTextField.addKeyListener(new KeyAdapter(){
            @Override
            public void keyReleased(KeyEvent e){
                switch (byWhatBox.getSelectedItem().toString()){
                    case "By name":
                        storage1.jTableSearchByName((DefaultTableModel) waresTable.getModel(), searchTextField);
                        break;
                    case "By price":
                        storage1.jTableSearchByPrice((DefaultTableModel) waresTable.getModel(), searchTextField);
                        break;
                    case "By category":
                        storage1.jTableSearchByCategory((DefaultTableModel) waresTable.getModel(), searchTextField);
                        break;
                    case "By quantity":
                        storage1.jTableSearchByQuantity((DefaultTableModel) waresTable.getModel(), searchTextField);
                        break;
                }
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
        scrollPanelWaresTable = new javax.swing.JScrollPane();
        waresTable = new javax.swing.JTable();
        searchTextField = new javax.swing.JTextField();
        searchLabel = new javax.swing.JLabel();
        byWhatBox = new javax.swing.JComboBox<>();
        cartButton = new javax.swing.JButton();
        addToCartButton = new javax.swing.JButton();
        orderButton = new javax.swing.JButton();
        wishListButton = new javax.swing.JButton();
        addToWishListButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        greetingLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        greetingLabel.setText("Welcome to the Random Webshop");

        waresTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "Price", "Category", "Quantity"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        scrollPanelWaresTable.setViewportView(waresTable);

        searchTextField.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));

        searchLabel.setText("Search:");

        byWhatBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "By name", "By price", "By category", "By quantity" }));

        cartButton.setText("Cart");
        cartButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cartButtonActionPerformed(evt);
            }
        });

        addToCartButton.setText("Add to cart");
        addToCartButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addToCartButtonActionPerformed(evt);
            }
        });

        orderButton.setText("Order");
        orderButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                orderButtonActionPerformed(evt);
            }
        });

        wishListButton.setText("Wishlist");
        wishListButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                wishListButtonActionPerformed(evt);
            }
        });

        addToWishListButton.setText("Add to wishlist");
        addToWishListButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addToWishListButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrollPanelWaresTable, javax.swing.GroupLayout.DEFAULT_SIZE, 685, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(addToCartButton, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(addToWishListButton)
                                .addGap(43, 43, 43)
                                .addComponent(greetingLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(byWhatBox, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(searchLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(searchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(cartButton, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(orderButton, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(wishListButton, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchLabel)
                    .addComponent(cartButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(orderButton)
                    .addComponent(wishListButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(byWhatBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addToCartButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(greetingLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(addToWishListButton))
                .addGap(18, 18, 18)
                .addComponent(scrollPanelWaresTable, javax.swing.GroupLayout.PREFERRED_SIZE, 415, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        searchTextField.getAccessibleContext().setAccessibleName("");
        searchTextField.getAccessibleContext().setAccessibleDescription("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cartButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cartButtonActionPerformed
        // TODO add your handling code here:
        CartJFrame cartWindow = new CartJFrame(this.mainCart);
        cartWindow.setVisible(true);
    }//GEN-LAST:event_cartButtonActionPerformed

    private void addToCartButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addToCartButtonActionPerformed
        // TODO add your handling code here:
        
        int wareId = waresTable.getSelectedRow();
        if(wareId >= 0){
        AddToCartJFrame addToCartWindow = new AddToCartJFrame(this.mainCart, mainStorage.getWareFromStorage(wareId), this.mainStorage, waresTable);
        addToCartWindow.setVisible(true);
        } else {
            System.out.println("You didn't select anything!");
        }
    }//GEN-LAST:event_addToCartButtonActionPerformed

    private void orderButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_orderButtonActionPerformed
        // TODO add your handling code here:
        if(this.mainCart.getCart().size() > 0){
        OrderFrame orderWindow = null;
        try {
            orderWindow = new OrderFrame(this.mainCart, this.mainStorage, waresTable, this.admin);
        } catch (IOException ex) {
            Logger.getLogger(ShopApp.class.getName()).log(Level.SEVERE, null, ex);
        }
        orderWindow.setVisible(true);
        } else {
            System.out.println("Cart is empty!");
        }
    }//GEN-LAST:event_orderButtonActionPerformed

    private void addToWishListButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addToWishListButtonActionPerformed
        // TODO add your handling code here:
        int wareId = waresTable.getSelectedRow();
        if(wareId >= 0){
        AddToWishListFrame addToWishListWindow = new AddToWishListFrame(this.mainWishList, mainStorage.getWareFromStorage(wareId), this.mainStorage, waresTable);
        addToWishListWindow.setVisible(true);
        } else {
            System.out.println("You didn't select anything!");
        }
    }//GEN-LAST:event_addToWishListButtonActionPerformed

    private void wishListButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_wishListButtonActionPerformed
        // TODO add your handling code here:
        WishListFrame wishListWindow = new WishListFrame(this.mainWishList, this.mainCart, this.mainStorage);
        wishListWindow.setVisible(true);
    }//GEN-LAST:event_wishListButtonActionPerformed

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    
    public static void main(String args[]) throws IOException {
        
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ShopApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            try {
                new ShopApp().setVisible(true);
            } catch (IOException ex) {
                Logger.getLogger(ShopApp.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addToCartButton;
    private javax.swing.JButton addToWishListButton;
    private javax.swing.JComboBox<String> byWhatBox;
    private javax.swing.JButton cartButton;
    private javax.swing.JLabel greetingLabel;
    private javax.swing.JButton orderButton;
    private javax.swing.JScrollPane scrollPanelWaresTable;
    private javax.swing.JLabel searchLabel;
    private javax.swing.JTextField searchTextField;
    private javax.swing.JTable waresTable;
    private javax.swing.JButton wishListButton;
    // End of variables declaration//GEN-END:variables
}
