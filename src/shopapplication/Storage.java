package shopapplication;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Storage {
    
    private final ArrayList<Ware> storage;

    public ArrayList<Ware> getStorage() {
        return storage;
    }
    
    public Ware getWareByName(String name){
        Ware foundWare = null;
        for(int i = 0; i < storage.size(); i++){
            if(storage.get(i).getName().equals(name)){
                foundWare = storage.get(i);
            }
        }
        return foundWare;
    }
    
    public Ware getWareFromStorage(int i){
        return storage.get(i);
    }
    
    public Storage() throws FileNotFoundException, IOException{
        storage = new ArrayList();
        File wares = new File("wares.txt");
        BufferedReader in = new BufferedReader(new FileReader(wares));
        String line = new String();
        while(in.ready()){
            line = in.readLine();
            String[] data = line.split(";");
            storage.add(new Ware(data[0],Integer.parseInt(data[1]), data[2], Integer.parseInt(data[3])));
        }
    }
    
    public void jTableFill(javax.swing.table.DefaultTableModel table){
        for(int i = 0; i < storage.size(); i++){
        table.addRow(
                     new Object[]{
                     storage.get(i).getName(), storage.get(i).getPrice(), storage.get(i).getCategory(), storage.get(i).getQuantity()
                     }
            );
        }
    }
    
    public void jTableSearchByName(javax.swing.table.DefaultTableModel table, javax.swing.JTextField textField){
        
        int count = table.getRowCount();
        for(int i = 0; i < count; i++){
            table.removeRow(0);
        }
        
        for(int i = 0; i < storage.size(); i++){
            if(storage.get(i).getName().toLowerCase().startsWith(textField.getText().toLowerCase())){
                table.addRow(
                        new Object[]{
                        storage.get(i).getName(), storage.get(i).getPrice(), storage.get(i).getCategory(), storage.get(i).getQuantity()
                        }
                );
            }
        }
    }
    
    public void jTableSearchByPrice(javax.swing.table.DefaultTableModel table, javax.swing.JTextField textField){
        
        int count = table.getRowCount();
        for(int i = 0; i < count; i++){
            table.removeRow(0);
        }
        try{
            int number = Integer.parseInt(textField.getText());
            for(int i = 0; i < storage.size(); i++){
            
            if(storage.get(i).getPrice() >= number){
                
                table.addRow(
                        new Object[]{
                        storage.get(i).getName(), storage.get(i).getPrice(), storage.get(i).getCategory(), storage.get(i).getQuantity()
                            }
                    );
                } 
        }} catch(NumberFormatException ex){
            jTableFill(table);
        }
        
        
    }
    
    public void jTableSearchByCategory(javax.swing.table.DefaultTableModel table, javax.swing.JTextField textField){
        
        int count = table.getRowCount();
        for(int i = 0; i < count; i++){
            table.removeRow(0);
        }
        
        for(int i = 0; i < storage.size(); i++){
            if(storage.get(i).getCategory().toLowerCase().startsWith(textField.getText().toLowerCase())){
                table.addRow(
                        new Object[]{
                        storage.get(i).getName(), storage.get(i).getPrice(), storage.get(i).getCategory(), storage.get(i).getQuantity()
                        }
                );
            }
        }
    }
    
    public void jTableSearchByQuantity(javax.swing.table.DefaultTableModel table, javax.swing.JTextField textField){
        
        int count = table.getRowCount();
        for(int i = 0; i < count; i++){
            table.removeRow(0);
        }
        try{
            int number = Integer.parseInt(textField.getText());
            for(int i = 0; i < storage.size(); i++){
            
            if(storage.get(i).getQuantity() >= number){
                
                table.addRow(
                        new Object[]{
                        storage.get(i).getName(), storage.get(i).getPrice(), storage.get(i).getCategory(), storage.get(i).getQuantity()
                            }
                    );
                } 
        }} catch(NumberFormatException ex){
            jTableFill(table);
        }
        
        
    }

    
}
