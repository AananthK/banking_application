/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bank.app;

/**
 *
 * @author Aananth
 */
public class Silver extends Level{
    public String name = "Silver";

    
 
    @Override
    public void onlinePurchase(Account acc, double price){
        if (price >= 50.0 && (price + 20) <= acc.getBalance()){
            acc.setBalance((acc.getBalance() - (price + 20)));
        }
    }
    
    @Override
    public String toString(){
        return name;
    }
    
}
