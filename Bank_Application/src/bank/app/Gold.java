/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bank.app;

/**
 *
 * @author Aananth
 */
public class Gold extends Level{
    String name = "Gold";

   
    @Override
    public void onlinePurchase(Account acc, double price){
        if (price >= 50.0 && (price + 10) <= acc.getBalance()){
            acc.setBalance((acc.getBalance() - (price + 10)));
        }
    }
    
    @Override
    public String toString(){
        return name;
    }
}
