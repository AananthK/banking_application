/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bank.app;

/**
 *
 * @author Aananth
 */
public class Platinum extends Level{
    String name = "Platinum";

   
    @Override
    public void onlinePurchase(Account acc, double price){
        if (price >= 50.0 && price <= acc.getBalance()){
            acc.setBalance(acc.getBalance() - price);
        }
    }
    
    @Override
    public String toString(){
        return name;
    }
}
