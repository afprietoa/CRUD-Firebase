/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package access;



import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Item;
import services.Facade;

/**
 *
 * @author afprietoa
 */
public class ItemDAO {
    /**
     * 
     * @param items, list to save
     */
    public void saveItem(String id, Map<String, Object> items ){
        FirebaseDatabase connexion = null;
       try {
           connexion = Facade.getConnection();
           DatabaseReference ref = connexion.getReference("/");
           DatabaseReference childReference = ref.child("items/"+ id);

        
        childReference.setValue(items);
        Thread.sleep(2000);
        } catch (InterruptedException ex) {
           Logger.getLogger(ItemDAO.class.getName()).log(Level.SEVERE, null,
        ex);
        }
    };
    
     /**
     * 
     * @param id, item code to update
     * @param itemUpdate, item collection to save
     */
    public void updateItem(String id, Map<String, Object> itemUpdate){
        FirebaseDatabase connexion = null;
        try {
        connexion = Facade.getConnection();
        final DatabaseReference dataBase = connexion.getReference("/").child("items");
     
        dataBase.updateChildren(itemUpdate);
        Thread.sleep(2000);
        } catch (InterruptedException ex) {
        Logger.getLogger(ItemDAO.class.getName()).log(Level.SEVERE, null,
        ex);
        }
    }
            
    /**
     * 
     * @param id, item code to delete
     */
    public void deleteItem(String id){
        FirebaseDatabase connexion = null;
        try {
        connexion = Facade.getConnection();
        final DatabaseReference dataBase = connexion.getReference("/").child("items/"+id);
        dataBase.removeValue();
        Thread.sleep(2000);
        } catch (InterruptedException ex) {
        Logger.getLogger(ItemDAO.class.getName()).log(Level.SEVERE, null,
        ex);
        }
    }

    public void listItems(){
        FirebaseDatabase connexion = null;
        try {
        connexion = Facade.getConnection();
        DatabaseReference ref = connexion.getReference("/").child("items");
        ref.addValueEventListener(new ValueEventListener() {
        
            
            
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
        GenericTypeIndicator<Map<String, Item>> typeIndicator = new GenericTypeIndicator<Map<String, Item>>() {};
        Map<String, Item> items = dataSnapshot.getValue(typeIndicator);
        System.out.println("Item list: ");
        
        for (Map.Entry<String, Item> entry : items.entrySet()) {
            String key = entry.getKey();
            Item value = entry.getValue();
            
        System.out.println(
                "Item \n" 
                + "id=" + key + "\n"
                + "name=" + value.getName() + "\n" 
                + "Price=" + value.getPrice() + "\n" 
                + "Stock=" + value.getStock() + "\n"
        );
        }
        

        }
        @Override
        public void onCancelled(DatabaseError databaseError) {
        System.out.println("The read failed: " + databaseError.getCode());
        }
        });
        Thread.sleep(2000);
        } catch (InterruptedException ex) {
        Logger.getLogger(ItemDAO.class.getName()).log(Level.SEVERE, null,
        ex);
        }
 
    }
    
    public void recoverItem(String id){
                FirebaseDatabase connexion = null;
        try {
        connexion = Facade.getConnection();
        DatabaseReference ref = connexion.getReference("/").child("items");
        ref.addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
    GenericTypeIndicator<Map<String, Item>> typeIndicator = new GenericTypeIndicator<Map<String, Item>>() {};
    Map<String, Item> items = dataSnapshot.getValue(typeIndicator);

    System.out.println("Item list: ");

    if (items != null) {


        if (items.containsKey(id)) {
            Item item = items.get(id);

            System.out.println(
                "Item \n"
                + "id=" + id + "\n"
                + "name=" + item.getName() + "\n"
                + "Price=" + item.getPrice() + "\n"
                + "Stock=" + item.getStock() + "\n"
            );
        } else {
            System.out.println("The specified key doesn´t exist!");
        }
    } else {
        System.out.println("No data available.");
    }
        }
        @Override
        public void onCancelled(DatabaseError databaseError) {
        System.out.println("The read failed: " + databaseError.getCode());
        }
        });
        Thread.sleep(2000);
        } catch (InterruptedException ex) {
        Logger.getLogger(ItemDAO.class.getName()).log(Level.SEVERE, null,
        ex);
        }

    }
    
}
