package bank.app;

/**
 *
 * @author Aananth
 */
public class Account {
    //OVERVIEW
    //An Account object is an instance variable of the Customer class making it part of every Customer.
    //Account is the context class in this program's State Design Pattern implementation. 
    //Behaviour of Account's onlinePurchase() method is altered by it's concrete state (Level) at the time it is called
    //Each Account object has a balance and a level, with both accessible thoughout the package using methods.
    
    //Account class is mutuable as its state can be altered depending on its balance (which can also be altered).
    
    
    private double balance;
    private Level lvl;
    
    // The abstraction function is:
    // AF(c) = an Account with an existing level and positive balance
    //
    // The rep invariant is:
    // c.denom > 0
    
    // The rep invariant is:
    // RI(c) = true if (c.getType != null && c.getBalance() >= 0)
    //       = false if otherwise

    //constructor
    protected Account (double bal){
        balance = bal;
        
    }
    
    
    
    
    //EFFECTS: Returns the level of the Account
    protected Level getLevel(){
        if (balance < 10000){
            return lvl = new Silver();
        }
        if (balance >= 10000 && balance < 20000){
            return lvl = new Gold();
        }
        return lvl = new Platinum();
    }
    
    //REQUIRES: lvl to be non-null
    //EFFECTS: Changes the level of the Account
    protected void setLevel(Level lvl){
        this.lvl = lvl;
    }
    
    //EFFECTS: Returns the balance of the Account
    protected double getBalance(){
        return balance;
    }
    
    //REQUIRES: Variable money must be greater than 0
    //EFFECTS: Changes balance of Account
    protected void setBalance(double money){
        if (money > 0){
            balance = money;
        }
    }
    
    //REQUIRES: Variable money must be greater than 50 (Specified in subclasses of Level class)
    //EFFECTS: Returns the level of the Account
    public void onlinePurchase(double money){
        
        this.getLevel().onlinePurchase(this, money);
    }
    
    //EFFECTS: Prints String representation of Account
    @Override
    public String toString(){
        return getLevel().toString() + ": $" + String.format("%.2f", balance);
    }
    
    //EFFECTS: Returns true if rep invariant holds for object; otherwise it returns false
    public boolean repOk(){
        
        if (lvl != null && balance >= 0){
            return true;
        }
            return false;
    
    }
    
}
