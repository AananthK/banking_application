package bank.app;

/**
 *
 * @author Aananth Kuganendra
 */
public abstract class Level {
    
    //In state design, the absract method is the behavoir that changes based on the state of the object in the context class (Account)

    public abstract void onlinePurchase(Account account, double price);
    
}
