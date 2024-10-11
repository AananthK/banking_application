/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package bank.app;

import bank.app.AlertBox;
import bank.app.Customer;
import bank.app.Manager;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.control.*;
import javafx.event.EventHandler;
import javafx.geometry.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.*;

/**
 *
 * @author Aananth
 */

//in JavaFX, window is called 'stage'. Everything inside window is called 'scene'.

public class GUI extends Application{
    Stage window;
    Scene Act, Create, Delete;
    //Button button;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception{
        
        window = primaryStage;
        window.setTitle("Bank");
        
        Manager m = Manager.getManager();//creating manager object to be able apply manager methods
        m.makeFile(m.getUsername(), m.getPassword(), m.role);//making text file for manager
        
        //LOGIN SCREEN
        
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10,10,10,10));
        grid.setVgap(8);
        grid.setHgap(10);
        
        //Title
        final Label title = new Label("The Bank");

        title.setFont(Font.font("Helvetica", FontWeight.BOLD, FontPosture.ITALIC, 30));
        title.setTextAlignment(TextAlignment.CENTER);
        GridPane.setConstraints(title, 2,0);
        
        
        //Username Label
        Label uN = new Label("Username:");
        GridPane.setConstraints(uN, 1,5);
        
        //Username Box for user to type in
        TextField uNinput = new TextField();
        GridPane.setConstraints(uNinput, 2,5);
        
        //Password Label
        Label pW = new Label("Password:");
        GridPane.setConstraints(pW, 1,6);
        
        //Password Box for user to type in
        TextField pWinput = new TextField();
        GridPane.setConstraints(pWinput, 2,6);
        
        //Login Button for user to press
        Button lgIn = new Button("Log In");
        GridPane.setConstraints(lgIn, 3,7);
        
        
        grid.getChildren().addAll(title, uN, pW, uNinput, pWinput, lgIn);//adds all components to grid
        
        Scene scene = new Scene(grid, 600, 500);
        window.setScene(scene);
        window.show();//displays winfow to user
        
        
        
        lgIn.setOnAction(new EventHandler<ActionEvent>() {//when user presses Log In button
            @Override
            public void handle (ActionEvent event){
                
                //Converting TextFields to Strings and storing them in other string variables for ease of reference
                String uName = uNinput.getText();
                String pWord = pWinput.getText();
                
                //These Booleans determine the next window
                Boolean managerAccess = false;
                Boolean customerAccess = false;
                
                try{ //Must wrap Log In interface around try-catch statement to allow scanning files
                    File f = new File ("/Users/owner/Downloads/coe528/project/BankAccounts/src/coe528/project/" + uName + ".txt"); //file to point to manager
                    File f2 = new File ("/Users/owner/Downloads/coe528/project/BankAccounts/src/coe528/project/" + uName + ".txt");//file to point to customer
                    
                    Scanner s = new Scanner(f);//scanner to inspect manager file
                    Scanner s2 = new Scanner(f2);//scanner to inspect customer file

                    
                        while(s.hasNextLine()){ //this while loop will basically go on forever, needs a break statement
                            
                            //Verifying Login for Manager
                            if (s.nextLine().equals(uName) && s.nextLine().equals(pWord) && s.nextLine().equals("Manager")) {
                                
                                    managerAccess = true;
                                    System.out.println("Manager Logged In!");
                                    break;//exits while statement
                                
                            }
                            
                            //Verifying Login for Customer
                            if (s2.nextLine().equals(uName) && s2.nextLine().equals(pWord) && s2.nextLine().equals("Customer")) {
                                
                                    customerAccess = true;
                                    System.out.println("Customer Logged In!");
                                    break;//exits while statement
                                
                            }
                            
                        }
                        
/***************************************************** MANAGER GUI ****************************************************************************/
                        if(managerAccess){
                            
                        //ACTIONS PAGE 
                        window.close();
                        
                        final Label title = new Label("Weclome Manager!");

                        title.setFont(Font.font("Helvetica", FontWeight.BOLD, FontPosture.ITALIC, 20));
                        title.setTextAlignment(TextAlignment.CENTER);
                        GridPane.setConstraints(title, 2,0);
                        
                        Label label = new Label("What would you like to do today?.");
                        VBox pane = new VBox(10); //sets up components vertically
                        
                        Button addCustomer = new Button("Add Customer");
                        
                        Button deleteCustomer = new Button("Delete Customer");
                        
                        Button logOut = new Button("Logout");
                        
                        pane.setAlignment(Pos.CENTER);
                        pane.getChildren().addAll(title, label,addCustomer, deleteCustomer, logOut);
                       
                        Scene scene2 = new Scene(pane, 600, 500);
                        
                        
                        Stage action = new Stage();
                        action.setTitle("Welcome " + uNinput.getText());
                        action.setScene(scene2);
                        action.show();
                        
                        
                            logOut.setOnAction( new EventHandler<ActionEvent>(){//When Log Out is pressed
                                @Override
                                public void handle(ActionEvent e){
                                    uNinput.clear();//.clear() makes sure username and password fieldws are empty when user logs out
                                    pWinput.clear();
                                    action.close();
                                    
                                    AlertBox o = new AlertBox();
                                    o.display("Logged Out", "You have been logged out");
                                    
                                    window.show();
                                }
                            });


                            addCustomer.setOnAction(new EventHandler<ActionEvent>(){//When Add Customer is pressed
                                @Override
                                public void handle(ActionEvent e){
                                    action.close();

                                    Label customer = new Label("Enter Customer Name: ");
                                    TextField addC = new TextField();

                                    Label password = new Label("Enter Password: ");
                                    TextField addC2 = new TextField();

                                    Button confirm = new Button("Confirm");
                                    Label blank = new Label("");

                                    Button back = new Button("Return");
                                    addC.setPrefWidth(100);
                                    addC.setMaxWidth(150);
                                    addC2.setPrefWidth(100);
                                    addC2.setMaxWidth(150);

                                    VBox pane = new VBox(10);

                                    pane.setAlignment(Pos.CENTER);
                                    pane.getChildren().addAll(customer, addC, password, addC2, blank, confirm, back);
                                    
                                    Scene scene2b = new Scene(pane, 600, 500);
                                    
                                    Stage addingCustomer = new Stage();
                                    addingCustomer.setScene(scene2b);
                                    addingCustomer.show();
                                    
                                        confirm.setOnAction(new EventHandler<ActionEvent>(){//When Add Customer is pressed
                                            @Override
                                            public void handle (ActionEvent e){
                                                
                                                String uName = addC.getText();
                                                String pWord = addC2.getText();
                                                
                                                File f = new File("/Users/owner/Downloads/coe528/project/BankAccounts/src/coe528/project/" + uName + ".txt");
                                                
                                                if(f.exists()){
                                                AlertBox a = new AlertBox();
                                                a.display("Invalid Entry", "Customer already exists.");
                                                }
                                                else{
                                                m.addCustomer(uName, pWord);
                                                AlertBox b = new AlertBox();
                                                b.display("Confirmation", "Customer Added");
                                                }
                                            }

                                        });
                                        
                                        back.setOnAction(new EventHandler<ActionEvent>(){//When Add Customer is pressed
                                            @Override
                                            public void handle (ActionEvent e){
                                                
                                                addingCustomer.close();
                                                action.show();
                                         
                                            }

                                        });
                                }          
                            });
                            
                            deleteCustomer.setOnAction(new EventHandler<ActionEvent>(){//When Add Customer is pressed
                                @Override
                                public void handle(ActionEvent e){
                                    action.close();

                                    Label customer = new Label("Enter Customer Name: ");
                                    TextField remC = new TextField();

                                    Button confirm = new Button("Confirm");
                                    Label blank = new Label("");

                                    Button back = new Button("Return");
                                    remC.setPrefWidth(100);
                                    remC.setMaxWidth(150);

                                    VBox pane = new VBox(10);

                                    pane.setAlignment(Pos.CENTER);
                                    pane.getChildren().addAll(customer, remC, blank, confirm, back);
                                    
                                    Scene scene2c = new Scene(pane, 600, 500);
                                    
                                    Stage removingCustomer = new Stage();
                                    removingCustomer.setScene(scene2c);
                                    removingCustomer.show();
                                    
                                        confirm.setOnAction(new EventHandler<ActionEvent>(){//When Add Customer is pressed
                                            @Override
                                            public void handle (ActionEvent e){
                                                
                                                String uName = remC.getText();
                                                
                                                File f = new File("/Users/owner/Downloads/coe528/project/BankAccounts/src/coe528/project/" + uName + ".txt");
                                                
                                                if(!f.exists() || uName.equals("admin")){ //file of customer to be deleted must exist and cannot be manager file
                                                AlertBox a = new AlertBox();
                                                a.display("Invalid Entry", "Customer does not exist");
                                                }
                                                else{
                                                m.removeCustomer(uName);
                                                AlertBox b = new AlertBox();
                                                b.display("Confirmation", "Customer Removed");
                                                }
                                   
                                            }

                                        });
                                        
                                        back.setOnAction(new EventHandler<ActionEvent>(){//When Add Customer is pressed
                                            @Override
                                            public void handle (ActionEvent e){
                                                
                                                removingCustomer.close();
                                                action.show();
                                         
                                            }
                                        });
                                }    
                            });    
                        }
                       
/***************************************************** CUSTOMER GUI ****************************************************************************/
                        if(customerAccess){
                            
                        //ACTIONS PAGE 
                        
                        //Creating customer object to update throughout customer GUI
                        File customer = new File("/Users/owner/Downloads/coe528/project/BankAccounts/src/coe528/project/" + uName + ".txt");
                        Scanner cScan = new Scanner(customer);
                        cScan.nextLine();
                        cScan.nextLine();
                        cScan.nextLine();
                        cScan.nextLine();
                        
                        Customer c = new Customer(uName, pWord);
                        double setBal = Double.parseDouble(cScan.nextLine());
                        c.getAccount().setBalance(setBal);
                        
                        
                        window.close();
                        
                        final Label title = new Label("Weclome " + uName + "!");

                        title.setFont(Font.font("Helvetica", FontWeight.BOLD, FontPosture.ITALIC, 20));
                        title.setTextAlignment(TextAlignment.CENTER);
                        GridPane.setConstraints(title, 2,0);
                        
                        Label label = new Label("What would you like to do today?");
                        
                        VBox pane = new VBox(10); //sets up components vertically
                        
                        Button view = new Button("View Account");
                        
                        Button deposit = new Button("Deposit");
                        
                        Button withdraw = new Button("Withdraw");
                        
                        Button payON = new Button("Make Online Payment");
                        
                        Button logOut = new Button("Logout");
                        
                        pane.setAlignment(Pos.CENTER);
                        pane.getChildren().addAll(title, label,view, deposit, withdraw, payON, logOut);
                       
                        Scene scene3 = new Scene(pane, 600, 500);
                        
                        
                        Stage action = new Stage();
                        action.setTitle("Welcome " + uNinput.getText());
                        action.setScene(scene3);
                        action.show();
                        
                        
                            logOut.setOnAction( new EventHandler<ActionEvent>(){//When Log Out is pressed
                                @Override
                                public void handle(ActionEvent e){
                                    uNinput.clear();//.clear() makes sure username and password fieldws are empty when user logs out
                                    pWinput.clear();
                                    action.close();
                                    
                                    AlertBox o = new AlertBox();
                                    o.display("Logged Out", "You have been logged out");
                                    
                                    window.show();
                                }
                            });
                            
                            view.setOnAction( new EventHandler<ActionEvent>(){//When View Account is pressed
                                @Override
                                public void handle(ActionEvent e){
                                    
                                    action.close();
                                    
                                    Label label = new Label("Account Details");
                                    VBox pane = new VBox(10); //sets up components vertically

                                    Label accLvl = new Label("Account Level: " + c.getAccount().getLevel().toString());
                                    
                                    Label accBalance = new Label("Current Balance: $" + String.format("%.2f", c.getBalance()));
                                    

                                    Button back = new Button("Back");

                                    pane.setAlignment(Pos.CENTER);
                                    pane.getChildren().addAll(label, accLvl, accBalance, back);

                                    Scene scene3a = new Scene(pane, 600, 500);


                                    Stage vAccount = new Stage();
                                    vAccount.setTitle("Account: " + uNinput.getText());
                                    vAccount.setScene(scene3a);
                                    vAccount.show();
                                    
                                    back.setOnAction(new EventHandler<ActionEvent>(){//When Back is pressed
                                            @Override
                                            public void handle (ActionEvent e){
                                                
                                                vAccount.close();
                                                action.show();
                                         
                                            }
                                    });
                                    
                                    
                                }
                                
                            });
                            
                            deposit.setOnAction( new EventHandler<ActionEvent>(){//Deposit is pressed
                                @Override
                                public void handle(ActionEvent e){
                                    
                                    action.close();
                                    
                                    Label label = new Label("Deposit Amount: $");
                                    VBox pane = new VBox(10); //sets up components vertically

                                    TextField val = new TextField();
                                    val.setPrefWidth(100);
                                    val.setMaxWidth(150);
                                    
                                    Button confirm = new Button("Confirm");

                                    Button back = new Button("Back");
                                    
                                    Label space = new Label ("");

                                    pane.setAlignment(Pos.CENTER);
                                    pane.getChildren().addAll(label, val, space, confirm, back);

                                    Scene scene3b = new Scene(pane, 600, 500);


                                    Stage dAccount = new Stage();
                                    dAccount.setTitle("Deposit: " + uNinput.getText());
                                    dAccount.setScene(scene3b);
                                    dAccount.show();
                                    
                                    confirm.setOnAction(new EventHandler<ActionEvent>(){//When Confrim is pressed
                                            @Override
                                            public void handle (ActionEvent e){
                                                 double money = Double.parseDouble(val.getText());
                                                 
                                                 if (money <= 0){
                                                     AlertBox a = new AlertBox();
                                                     a.display("Invalid Entry", "Please enter a valid deposit amount.");
                                                 }
                                                 
                                                 else{
                                                     c.deposit(money);
                                                     double currentBal = c.getAccount().getBalance();
                                                     m.updateCustomer(uName, pWord, currentBal);
                                                     
                                                     AlertBox b = new AlertBox();
                                                     b.display("Confrimation", "Deposit Made.");
                                                     dAccount.close();
                                                     action.show();
                                                 }
                                         
                                            }
                                    });
                                    
                                    back.setOnAction(new EventHandler<ActionEvent>(){//When Back is pressed
                                            @Override
                                            public void handle (ActionEvent e){
                                                
                                                dAccount.close();
                                                action.show();
                                         
                                            }
                                    });
                                    
                                    
                                    
                                    
                                }
                                
                            });
                            
                            withdraw.setOnAction( new EventHandler<ActionEvent>(){//Withdraw is pressed
                                @Override
                                public void handle(ActionEvent e){
                                    
                                    action.close();
                                    
                                    Label label = new Label("Withdraw Amount: $");
                                    VBox pane = new VBox(10); //sets up components vertically
                                    
                                    TextField val = new TextField();
                                    val.setPrefWidth(100);
                                    val.setMaxWidth(150);
                                    
                                    Button confirm = new Button("Confirm");

                                    Button back = new Button("Back");
                                    
                                    Label space = new Label ("");

                                    pane.setAlignment(Pos.CENTER);
                                    pane.getChildren().addAll(label, val, space, confirm, back);

                                    Scene scene3b = new Scene(pane, 600, 500);


                                    Stage wAccount = new Stage();
                                    wAccount.setTitle("Withdrawal " + uNinput.getText());
                                    wAccount.setScene(scene3b);
                                    wAccount.show();
                                    
                                    confirm.setOnAction(new EventHandler<ActionEvent>(){//When Confrim is pressed
                                            @Override
                                            public void handle (ActionEvent e){
                                                 double money = Double.parseDouble(val.getText());
                                                 
                                                 if (money <= 0){
                                                     AlertBox a = new AlertBox();
                                                     a.display("Invalid Entry", "Please enter a valid withdrawal amount.");
                                                 }
                                                 
                                                 if (money > c.getBalance()){
                                                     AlertBox a = new AlertBox();
                                                     a.display("Invalid Entry", "Not enough money in account.");
                                                 }
                                                 
                                                 else{
                                                     c.withdraw(money);
                                                     double currentBal = c.getBalance();
                                                     m.updateCustomer(uName, pWord, currentBal);
                                                     
                                                     AlertBox b = new AlertBox();
                                                     b.display("Confrimation", "Withdrawal Made.");
                                                     wAccount.close();
                                                     action.show();
                                                 }
                                         
                                            }
                                    });
                                    
                                    back.setOnAction(new EventHandler<ActionEvent>(){//When Back is pressed
                                            @Override
                                            public void handle (ActionEvent e){
                                                
                                                wAccount.close();
                                                action.show();
                                         
                                            }
                                    });
                                    
                                    
                                    
                                    
                                }
                                
                            });
                            
                            payON.setOnAction( new EventHandler<ActionEvent>(){//Deposit is pressed
                                @Override
                                public void handle(ActionEvent e){
                                    
                                    action.close();
                                    
                                    Label accType = new Label("Your current account status(Level/Balance): "+ c.getAccount().toString());
                                    accType.setFont(Font.font("Sans Serif", FontWeight.BOLD, FontPosture.ITALIC, 15));
                                    
                                    Label label = new Label("Make Payment: $");
                                    
                                    VBox pane = new VBox(10); //sets up components vertically
                                    
                                    TextField val = new TextField();
                                    val.setPrefWidth(100);
                                    val.setMaxWidth(150);
                                    
                                    Button confirm = new Button("Confirm");

                                    Button back = new Button("Back");
                                    
                                    Label space = new Label ("");

                                    pane.setAlignment(Pos.CENTER);
                                    pane.getChildren().addAll(accType, label, val, space, confirm, back);

                                    Scene scene3b = new Scene(pane, 600, 500);


                                    Stage pAccount = new Stage();
                                    pAccount.setTitle("Make Payment: " + uNinput.getText());
                                    pAccount.setScene(scene3b);
                                    pAccount.show();
                                    
                                    confirm.setOnAction(new EventHandler<ActionEvent>(){//When Confrim is pressed
                                            @Override
                                            public void handle (ActionEvent e){
                                                 double money = Double.parseDouble(val.getText());
                                                 
                                                 if (money < 50){
                                                     AlertBox a = new AlertBox();
                                                     a.display("Invalid Entry", "Please enter a valid online payment amount.\n(Online payments should at least be $50.00)");
                                                 }
                                                 
                                                 else if (c.getAccount().getLevel().toString().equals("Silver") && (money + 20) > c.getBalance()){
                                                     AlertBox b = new AlertBox();
                                                     b.display("Invalid Entry", "Not enough money in account.\n(NOTE* Online Purchase Fee for Silver clients: $20)");
                                                 }
                                                 
                                                 else if (c.getAccount().getLevel().toString().equals("Gold") && (money + 10) > c.getBalance()){
                                                     AlertBox c = new AlertBox();
                                                     c.display("Invalid Entry", "Not enough money in account.\n(NOTE* Online Purchase Fee for Gold clients: $10)");
                                                 }
                                                 
                                                 else if (c.getAccount().getLevel().toString().equals("Platinum") && money > c.getBalance()){
                                                     AlertBox d = new AlertBox();
                                                     d.display("Invalid Entry", "Not enough money in account.");
                                                 }
                                                 
                                                 else{
                                                     c.getAccount().onlinePurchase(money);
                                                     double currentBal = c.getBalance();
                                                     m.updateCustomer(uName, pWord, currentBal);
                                                     
                                                     AlertBox b = new AlertBox();
                                                     b.display("Confrimation", "Online Payment Made.");
                                                     pAccount.close();
                                                     action.show();
                                                 }
                                         
                                            }
                                    });
                                    
                                    back.setOnAction(new EventHandler<ActionEvent>(){//When Back is pressed
                                            @Override
                                            public void handle (ActionEvent e){
                                                
                                                pAccount.close();
                                                action.show();
                                         
                                            }
                                    });
                                    
                                    
                                    
                                    
                                }
                                
                            });
                        
                        }
                }catch(FileNotFoundException e1){//This catch block excecutes when there is no file that matches the login inputs
                    AlertBox a = new AlertBox();
                    a.display("Invalid Entry", "Please enter correct username and password.");
                }
                
            }});
        
        
    }//YOU'VE REACHED THE END!
    
    

    
    
}
