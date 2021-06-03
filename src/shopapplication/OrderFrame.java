/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shopapplication;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Nagy-Zsugya Bálint
 */
public final class OrderFrame extends javax.swing.JFrame {

    /**
     * Creates new form OrderFrame
     */
    
    public OrderFrame() {
        initComponents(); 
    }
    
    public OrderFrame(Cart mainCart, Storage mainStorage, JTable waresTable, Admin admin) throws IOException {
        initComponents();
        order(mainCart, mainStorage, waresTable, admin);
        int total = 0;
        for(int i = 0; i < mainCart.getCart().size(); i++){
            total += mainCart.getWare(i).getPrice() * mainCart.getWare(i).getQuantity();
        }
        priceLabel.setText(String.valueOf(total));
    }
    
    public void order(Cart mainCart, Storage mainStorage, JTable waresTable, Admin admin) throws IOException{
        orderButton.addActionListener((ActionEvent e) -> {
            if(!cardNumberTextField1.getText().equals("") &&
           !cardNumberTextField2.getText().equals("") &&
           !cardNumberTextField3.getText().equals("") &&
           !cardNumberTextField4.getText().equals("") &&
           !cardholdersNameTextField.getText().equals("") &&
           !cardholdersAddressTextField.getText().equals("")){
            ArrayList orderedWares = new ArrayList();
            for(int i = 0; i < mainCart.getCart().size(); i++){
                orderedWares.add(mainCart.getCart().get(i));
            }
            String cardHoldersName = cardholdersNameTextField.getText();
            String cardHoldersAddress = cardholdersAddressTextField.getText();
            String cardNumber = cardNumberTextField1.getText() + "-" + cardNumberTextField2.getText() + "-" + cardNumberTextField3.getText() + "-" + cardNumberTextField4.getText();
            Order currentOrder = new Order(cardNumber, cardHoldersName, cardHoldersAddress, orderedWares);
            admin.addToAdmin(currentOrder);
            File orders = new File("orders.txt");
            try {
                FileWriter orderWriter = new FileWriter(orders);
                for(int i = 0; i < admin.getAdmin().size(); i++){
                    orderWriter.write(admin.getAdmin().get(i).getCardHoldersName() + ";" + admin.getAdmin().get(i).getCardHoldersAddress() + ";" + admin.getAdmin().get(i).getCardNumber() + "\n");
                    for(int j = 0; j < admin.getAdmin().get(i).getOrderedWares().size(); j++){
                        if(j != admin.getAdmin().get(i).getOrderedWares().size() - 1){
                        orderWriter.write(admin.getAdmin().get(i).getOrderedWares().get(j).getName() + ";" +
                                          admin.getAdmin().get(i).getOrderedWares().get(j).getCategory() + ";" + 
                                          admin.getAdmin().get(i).getOrderedWares().get(j).getPrice() + ";"
                                        + admin.getAdmin().get(i).getOrderedWares().get(j).getQuantity()+ ";" + admin.getAdmin().get(i).getOrderedWares().get(j).getPrice()*admin.getAdmin().get(i).getOrderedWares().get(j).getQuantity() + ":");
                            } else {
                            orderWriter.write(admin.getAdmin().get(i).getOrderedWares().get(j).getName() + ";" +
                                          admin.getAdmin().get(i).getOrderedWares().get(j).getCategory() + ";" + 
                                          admin.getAdmin().get(i).getOrderedWares().get(j).getPrice() + ";"
                                        + admin.getAdmin().get(i).getOrderedWares().get(j).getQuantity()+ ";" + admin.getAdmin().get(i).getOrderedWares().get(j).getPrice()*admin.getAdmin().get(i).getOrderedWares().get(j).getQuantity());
                        }
                        }
                    orderWriter.write("\n");
                }
                orderWriter.close();
                for(int i = 0; i < mainCart.getCart().size(); i++){
                    Ware currentWare = mainStorage.getWareByName(mainCart.getWare(i).getName());
                    mainStorage.getWareByName(mainCart.getWare(i).getName()).setQuantity(currentWare.getQuantity() - mainCart.getWare(i).getQuantity());
                }   
                DefaultTableModel model = (DefaultTableModel) waresTable.getModel();
                model.setRowCount(0);
                mainStorage.jTableFill(model);
                int size = mainCart.getCart().size();
                for(int i = 0; i < size; i++){
                    mainCart.removeById(0);
                }
                orderWriter = new FileWriter("cart.txt");
                orderWriter.close();
                orderWriter = new FileWriter("wares.txt");
                for(int i = 0;i < mainStorage.getStorage().size(); i++){
                    orderWriter.write(mainStorage.getWareFromStorage(i).getName() + ";" + mainStorage.getWareFromStorage(i).getPrice() + ";" + mainStorage.getWareFromStorage(i).getCategory() + ";" + mainStorage.getWareFromStorage(i).getQuantity() + "\n");
                }
                orderWriter.close();
            } catch (IOException ex) {
                Logger.getLogger(OrderFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
            } else {
                System.out.println("You didn't type in the necessary pieces of information!");
                this.dispose();
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
//    @SuppressWarnings("unchecked");
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cardNumberLabel = new javax.swing.JLabel();
        cardholdersNameLabel = new javax.swing.JLabel();
        cardholdersAddressLabel = new javax.swing.JLabel();
        cardNumberTextField1 = new javax.swing.JTextField();
        cardholdersNameTextField = new javax.swing.JTextField();
        cardholdersAddressTextField = new javax.swing.JTextField();
        orderButton = new javax.swing.JButton();
        separatorLabel1 = new javax.swing.JLabel();
        cardNumberTextField2 = new javax.swing.JTextField();
        separatorLabel2 = new javax.swing.JLabel();
        cardNumberTextField3 = new javax.swing.JTextField();
        seperateLabel3 = new javax.swing.JLabel();
        cardNumberTextField4 = new javax.swing.JTextField();
        totalPriceLabel = new javax.swing.JLabel();
        priceLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        cardNumberLabel.setText("Card number:");

        cardholdersNameLabel.setText("Cardholder's name:");

        cardholdersAddressLabel.setText("Cardholder's address:");

        orderButton.setText("Order");
        orderButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                orderButtonActionPerformed(evt);
            }
        });

        separatorLabel1.setText("-");

        separatorLabel2.setText("-");

        seperateLabel3.setText("-");

        totalPriceLabel.setText("Total price:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(152, 152, 152)
                        .addComponent(orderButton, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(cardholdersAddressLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE)
                                    .addComponent(cardholdersNameLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cardNumberLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(cardNumberTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(separatorLabel1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cardNumberTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(separatorLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cardNumberTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(seperateLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cardNumberTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(cardholdersNameTextField)
                                    .addComponent(cardholdersAddressTextField)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(totalPriceLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(priceLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(94, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cardNumberLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cardNumberTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(separatorLabel1)
                    .addComponent(cardNumberTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(separatorLabel2)
                    .addComponent(cardNumberTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(seperateLabel3)
                    .addComponent(cardNumberTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cardholdersNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cardholdersNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cardholdersAddressLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cardholdersAddressTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(priceLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(totalPriceLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                .addComponent(orderButton, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(57, 57, 57))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void orderButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_orderButtonActionPerformed
        // TODO add your handling code here:
        if(!cardNumberTextField1.getText().equals("") &&
           !cardNumberTextField2.getText().equals("") &&
           !cardNumberTextField3.getText().equals("") &&
           !cardNumberTextField4.getText().equals("") &&
           !cardholdersNameTextField.getText().equals("") &&
           !cardholdersAddressTextField.getText().equals("")){
            this.dispose();
        }
    }//GEN-LAST:event_orderButtonActionPerformed

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
            java.util.logging.Logger.getLogger(OrderFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(OrderFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(OrderFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(OrderFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new OrderFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel cardNumberLabel;
    private javax.swing.JTextField cardNumberTextField1;
    private javax.swing.JTextField cardNumberTextField2;
    private javax.swing.JTextField cardNumberTextField3;
    private javax.swing.JTextField cardNumberTextField4;
    private javax.swing.JLabel cardholdersAddressLabel;
    private javax.swing.JTextField cardholdersAddressTextField;
    private javax.swing.JLabel cardholdersNameLabel;
    private javax.swing.JTextField cardholdersNameTextField;
    private javax.swing.JButton orderButton;
    private javax.swing.JLabel priceLabel;
    private javax.swing.JLabel separatorLabel1;
    private javax.swing.JLabel separatorLabel2;
    private javax.swing.JLabel seperateLabel3;
    private javax.swing.JLabel totalPriceLabel;
    // End of variables declaration//GEN-END:variables
}
