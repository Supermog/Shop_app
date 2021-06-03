package shopapplication;

import java.util.ArrayList;

public class Admin {
    
    private final ArrayList<Order> admin;
    
    public Admin(){
        admin = new ArrayList();
    }
    
    public void addToAdmin(Order order){
        admin.add(order);
    }
    
    public ArrayList<Order> getAdmin(){
        return this.admin;
    }
    
    
    
}
