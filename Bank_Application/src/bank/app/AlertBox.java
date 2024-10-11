/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bank.app;


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
import javafx.stage.Modality;

/**
 *
 * @author Aananth
 */
public class AlertBox {
    /*
    Alert Box functions like a pop-up window,  must be closed to re-access initial window
    It will be used to send action messages or error messages 
    */
    
    public void display (String title, String message){
        Stage window = new Stage();
        
        window.initModality(Modality.APPLICATION_MODAL);//this feature means Alert Box must be dealt with before returning to window
        window.setTitle(title);
        window.setMinWidth(250);
        
        Label l = new Label(message);
        
        Button close = new Button("Close");
        
        close.setOnAction(e -> window.close());
        
        VBox layout = new VBox(10);
        layout.getChildren().addAll(l,close);
        layout.setAlignment(Pos.CENTER);
        
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();//used in conjuction with initModiality
    }
    
}
