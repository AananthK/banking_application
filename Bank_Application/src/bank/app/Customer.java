package bank.app;

import java.util.*;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;

/**
 *
 * @author Aananth
 */
public class Customer extends User{
    
    private Account account;
    
    protected Customer (String uName, String pWord) {
        super(uName, pWord);
        role = "Customer";
        account = new Account(100.00);
    }
    
    public Account getAccount(){
        return this.account;
    }
    
    public void withdraw(double money){
        account.setBalance(account.getBalance() - money);
    }
    
    public void deposit(double money){
        account.setBalance(account.getBalance() + money);
    }
    
    public double getBalance(){
        return account.getBalance();
    }
    
    public void onlinePurchase(double money){
        account.onlinePurchase(money);
    }
    
}
