package bank.app;

import java.util.*;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;

/**
 *
 * @author Aananth
 */
public class Manager extends User{
    //Since there is atleast one Manager, this class can only allow one object of this class to exist (singleton classs).
    
    private static Manager manager = null;
    
    private Manager (){
        super("admin", "admin");
        role = "Manager";
        }
    
    public static Manager getManager() {
        if (manager == null) {
            manager = new Manager();
        }
        return manager;
    }
    
    public void makeFile(String uName, String pWord, String position){
        try {
                File f = new File("/Users/owner/Downloads/coe528/project/BankAccounts/src/coe528/project/" + uName + ".txt");//Creates file in specified directory and sets name
                
                if (f.createNewFile()) {//returns true if file does not exist and is created
                  System.out.println("File created: " + f.getName());
                  
                  FileWriter myWriter = new FileWriter(f);//file-writer obejct that writes in text files
                  myWriter.write(uName + "\n" + pWord + "\n" + position);
                  myWriter.close();//closes file after openning it
                }
                else {
                  System.out.println("File already exists.");
                }
              } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
              }    
    }
    
    public void addCustomer(String uName, String pWord){
        
        Customer c = new Customer(uName, pWord); //creating customer object  
        
        try{//writing information for customer
        FileWriter myWriter = new FileWriter("/Users/owner/Downloads/coe528/project/BankAccounts/src/coe528/project/" + uName + ".txt");
        
                  myWriter.write(uName + "\n" + pWord + "\n"  + "Customer" + "\n" + c.getAccount().getLevel().toString() + "\n" + c.getBalance()  + "\n");
                  myWriter.close();//closes file after openning it
                  } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
              }    
    }
    
    public void removeCustomer(String uName){
        File f = new File("/Users/owner/Downloads/coe528/project/BankAccounts/src/coe528/project/" + uName + ".txt");
         if (f.delete()) { 
         System.out.println("Removed Customer: " + uName);
        } else {
          System.out.println("Could not remove customer");
        }
    }
    
    
    public void updateCustomer(String uName, String pWord, double bal){
        try{//writing extra information for customer
        
        Customer c = new Customer(uName, pWord);
        c.getAccount().setBalance(bal);
            
        FileWriter myWriter = new FileWriter("/Users/owner/Downloads/coe528/project/BankAccounts/src/coe528/project/" + uName + ".txt");//to edit existing customer file
        
                  myWriter.write(uName + "\n" + pWord + "\n"  + "Customer" + "\n" + c.getAccount().getLevel().toString() + "\n" + c.getBalance()  + "\n");
                  myWriter.close();//closes file after openning it
                  } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
              }    
    
    
    
    }
    
    
                
        
        
    
    
    

    
}
