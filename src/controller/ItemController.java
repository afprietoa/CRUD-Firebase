/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import access.ItemDAO;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.UUID;
import model.Item;
/**
 *
 * @author afprietoa
 */
public class ItemController {
    Scanner scStr = new Scanner(System.in);
    Scanner scInt = new Scanner(System.in);
    Scanner scDbl = new Scanner(System.in);
    Scanner scLng = new Scanner(System.in);
    
    public void handleItems(){
        int opcion;
        do{
            System.out.println("Item Management".toUpperCase());
            System.out.println("1. Add item");
            System.out.println("2. recover item");
            System.out.println("3. update item");
            System.out.println("4. delete item");
            System.out.println("5. List items");
            System.out.println("6. exit");
            System.out.print("Option ? : ");
            opcion = scInt.nextInt();
            scInt.nextLine();
            switch(opcion){
                case 1: saveItem(); break;
                case 2: getItem(); break;
                case 3: editItem(); break;
                case 4: removeItem(); break;
                case 5: getItems(); break;
            }
            
        }while( opcion != 6);
    }
    
    private void saveItem(){

        int op;
        do{
            ItemDAO itemDao = new ItemDAO();
            System.out.println("ADD Item".toUpperCase());
            System.out.print("Enter 0 to end or another key to continue : ");
            op = scInt.nextInt();

             
          if (op != 0){  

            System.out.println("Enter Item:");

            Long stock = (long) (Math.random() + 30);
            
            System.out.print("Item name: ");
            String name = scStr.nextLine();
            
            System.out.print("Item price: ");
            Double price = scDbl.nextDouble();
            
            String id = UUID.randomUUID().toString().substring(0, 6);
            
            Map<String, Object> items = new HashMap<String, Object>(){
            {
             put("name" , name);
             put("price" , price);
             put("stock" , stock);   
          }
        };
          
           
            itemDao.saveItem(id, items);
            System.out.println("Item saved successfully!");
          }     
            
        }while (op != 0);
        
        
        
    }
    
    private void getItem(){
        ItemDAO itemDao = new ItemDAO();
        System.out.println("RECOVER Item".toUpperCase());
        
        System.out.println("code Item:");
        String code = scStr.nextLine();
        
        itemDao.recoverItem(code);
    }
    
    private void editItem(){
        ItemDAO itemDao = new ItemDAO();
        System.out.println("UPDATE Item".toUpperCase());
        
        System.out.print("code Item:");
        String code = scStr.nextLine();
    
        System.out.print("Item name: ");
        String name = scStr.nextLine();
            
        System.out.print("Item price: ");
        Double price = scDbl.nextDouble();
        
        System.out.print("Item stock: ");
        Long stock = scLng.nextLong();
        
        Map<String, Object> items = new HashMap<String, Object>(){
            {
             put(code + "/name" , name);
             put(code + "/price" , price);
             put(code + "/stock" , stock);   
          }
        };
        
        itemDao.updateItem( code,items);
        System.out.println("Item updated successfully!");
    }
    
    private void removeItem(){
        ItemDAO itemDao = new ItemDAO();
        System.out.println("DELETE Item".toUpperCase());
        
        System.out.println("code Item:");
        String code = scStr.nextLine();
        
        itemDao.deleteItem(code);
        System.out.println("Item deleted successfully!");
    }
    
    private void getItems(){
        ItemDAO itemDao = new ItemDAO();
        System.out.println("LIST Item".toUpperCase());
        itemDao.listItems();
    }
}
