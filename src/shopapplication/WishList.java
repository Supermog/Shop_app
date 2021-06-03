package shopapplication;

import java.util.ArrayList;

public class WishList {
    
    private final ArrayList<Ware> wishList;

    public WishList() {
        wishList = new ArrayList();
    }
    
    public ArrayList<Ware> getWishList() {
        return wishList;
    }
    
    public void addToWishList(Ware ware){
        wishList.add(ware);
    }
    
    public void removeById(int i){
        wishList.remove(i);
    }
    
    public Ware getWare(int i){
        return wishList.get(i);
    }
    
    public void jTableFill(javax.swing.table.DefaultTableModel table){
        if(wishList.size() > 0){
        for(int i = 0; i < wishList.size(); i++){
        table.addRow(
                     new Object[]{
                     wishList.get(i).getName(), wishList.get(i).getCategory(), wishList.get(i).getQuantity(), wishList.get(i).getPrice(), wishList.get(i).getPrice()*wishList.get(i).getQuantity()
                     }
                );
            }
        }
    }
    
}
