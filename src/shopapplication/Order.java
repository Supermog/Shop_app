package shopapplication;

import java.util.ArrayList;

public class Order {
    
    private final String cardNumber;
    private final String cardHoldersName;
    private final String cardHoldersAddress;
    private final ArrayList<Ware> orderedWares;

    public Order(String cardNumber, String cardHoldersName, String cardHoldersAddress, ArrayList<Ware> orderedWares) {
        this.cardNumber = cardNumber;
        this.cardHoldersName = cardHoldersName;
        this.cardHoldersAddress = cardHoldersAddress;
        this.orderedWares = orderedWares;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getCardHoldersName() {
        return cardHoldersName;
    }

    public String getCardHoldersAddress() {
        return cardHoldersAddress;
    }
    
    public ArrayList<Ware> getOrderedWares(){
        return this.orderedWares;
    }
    
}
