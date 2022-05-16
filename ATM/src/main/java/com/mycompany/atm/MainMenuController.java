package com.mycompany.atm;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class MainMenuController 
{

    private Parent root;
    
    public Account[] accounts;

    public Account currAccount;

    @FXML
    Button closeButton;

    @FXML
    Label acctNameLabel;
    
    @FXML 
    Label acctNumLabel;

    /**
     * Initializes the controller class.
     */
    public void initialize(URL url, ResourceBundle rb) 
    {
        // TODO
    } // End initialize.
    
    /**
     * switchToCheckBalancePage - go to Check Balance window.
     * @throws IOException 
     */
    @FXML
    private void switchToCheckBalancePage() throws IOException 
    {
        
        // Get the LoginPage Controller.
        FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginPage.fxml"));
        Parent root2 = loader.load();

        LoginPageController loginPageController = loader.getController();
        
        accounts = loginPageController.readFromJson();
        
        Account currAcct = null;
        
        // Get the account number from the label.
        String acctNum = acctNumLabel.getText();
        
        // Search through account array for the account that matches the account number.
        for (int i = 0; i < accounts.length; i++)
        {
            if (accounts[i].getId().equals(acctNum))
                currAcct = accounts[i]; // Set account to this.
        } // End for.

        // Gets the CheckBalance Controller.
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CheckBalance.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();

        CheckBalanceController checkBalanceController = fxmlLoader.getController();

        // Updates name for that of the account holder.
        checkBalanceController.updateBalance(currAcct.getBal());

        // Sets stage.
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.show();
    } // End switchToCheckBalancePage.

    /**
     * switchToDepositPage - switch to deposit page.
     * @throws IOException 
     */
    @FXML
    private void switchToDepositPage() throws IOException 
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DepositPage.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.show();
    } // End switchToDepositPage.

    /**
     * switchToCheckInfoPage - switchToCheckInfoPage.
     * @throws IOException 
     */
    @FXML
    private void switchToCheckInfoPage() throws IOException 
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CheckInfoPage.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.show();
    } // End switchToCheckInfoPage.

    /**
     * switchToBalanceTransferPage
     * @throws IOException 
     */
    @FXML
    private void switchToBalanceTransferPage() throws IOException 
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("BalanceTransferPage.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.show();
    } // End switchToBalanceTransferPage.

    /**
     * switchToWithdrawPage
     * @throws IOException 
     */
    @FXML
    private void switchToWithdrawPage() throws IOException 
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("WithdrawPage.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.show();
    } // End switchToWithdrawPage.

    /**
     * closeMenu - closes the MainMenu page which allows the user to go back to the login page, and access another account.
     * @throws IOException 
     */
    @FXML
    private void closeMenu() throws IOException 
    {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    } // End closeMenu.

    /**
     * updateName - method that is used to update the name label to that of the current account that is being used.
     * @param acctName 
     */
    @FXML
    public void updateName(String acctName) 
    {
        acctNameLabel.setText(acctName);
    } // End updateName.
    
    /**
     * updateNum - method used to update the account number to that of the current account.
     * @param acctNum 
     */
    @FXML
    public void updateNum(String acctNum) 
    {
        acctNumLabel.setText(acctNum);
    } // End updateNum.

} // End MainMenuController.
