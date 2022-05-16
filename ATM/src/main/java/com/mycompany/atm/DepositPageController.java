/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.atm;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author chriscanenguez
 */
public class DepositPageController implements Initializable {

    @FXML
    TextField depositTextField;

    private Parent root;

    public Account[] accounts;

    Account currAccount;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        // TODO
    }
    
    /**
     * deposit - used when user deposits money(int) into account.
     */
    @FXML
    public void deposit() 
    {
        try 
        {
            // Get textField text.
            int deposit = Integer.valueOf(depositTextField.getText());

            // Get the MainMenu Controller.
            FXMLLoader loader1 = new FXMLLoader(getClass().getResource("LoginPage.fxml"));
            Parent root1 = loader1.load();

            LoginPageController loginPageController = loader1.getController();

            Account[] accounts = loginPageController.readFromJson();

            // Get the MainMenu Controller.
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MainMenu.fxml"));
            Parent root2 = loader.load();

            MainMenuController mainMenuController = loader.getController();

            String acctNum = mainMenuController.acctNumLabel.getText();

            for (int i = 0; i < accounts.length; i++) 
            {
                if (accounts[i].getId().equals(acctNum)) 
                {
                    currAccount = accounts[i];
                } // End if.
            } // End for.
            
            currAccount.deposit(deposit);

        } // End try.
        catch (IOException ex) 
        {
            Logger.getLogger(DepositPageController.class.getName()).log(Level.SEVERE, null, ex);
        } // End catch.
    } // End deposit.
} // End DepositPageController.
