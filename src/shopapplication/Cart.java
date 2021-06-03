package shopapplication;

import java.util.ArrayList;

public class Cart {
    
    private final ArrayList<Ware> cart;

    public Cart() {
        cart = new ArrayList();
    }
    
    public ArrayList<Ware> getCart() {
        return cart;
    }
    
    public void addToCart(Ware ware){
        cart.add(ware);
    }
    
    public void removeById(int i){
        cart.remove(i);
    }
    
    public Ware getWare(int i){
        return cart.get(i);
    }
    
    public void jTableFill(javax.swing.table.DefaultTableModel table){
        if(cart.size() > 0){
        for(int i = 0; i < cart.size(); i++){
        table.addRow(
                     new Object[]{
                     cart.get(i).getName(), cart.get(i).getCategory(), cart.get(i).getQuantity(), cart.get(i).getPrice(), cart.get(i).getPrice()*cart.get(i).getQuantity()
                     }
                );
            }
        }
    }
    
}
