/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.atm;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import static java.lang.System.getLogger;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author chriscanenguez
 */
public class LoginPageController implements Initializable {

    private Parent root;

    FileReader fileReader;
    String databaseURL = "";
    Connection conn = null;
    String sql;
    public Account[] Accounts;
    private Account currentAccount;
    public String acctNum;

    @FXML
    TextField accountNumTextField;

    @FXML
    TextField pinCodeTextField;

    @FXML
    Label wrongPinLabel;

    // Initialize accounts.
    public LoginPageController() throws FileNotFoundException 
    {
        this.Accounts = readFromJson();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        // TODO
    } // End initialize.

    /**
     * closeMenuButton - allows to exit the application through the menu item.
     *
     * @throws IOException
     */
    @FXML
    private void closeMenuButton() throws IOException 
    {
        Platform.exit();
    } // End closeMenuButton.

    /**
     * Read all the objects from the json file, creating Account objects and
     * store them in array.
     *
     * @return an Account array
     * @throws FileNotFoundException if json file not found
     */
    @FXML
    public Account[] readFromJson() throws FileNotFoundException 
    {
        // Set up builder objects to read from the json file.
        GsonBuilder builder = new GsonBuilder();

        builder.setPrettyPrinting();

        Gson gson = builder.create();

        // Open the json file.
        fileReader = new FileReader("accounts.json");

        // Place all objects from json file into array of type Account.
        Account[] accountArray = gson.fromJson(fileReader, Account[].class);

        // Print Account objects to console using lamda and stream.
        Arrays.stream(accountArray)
                .forEach(e -> System.out.print(e.toString() + "\n" + "\n"));

        // Return array of Account objects.
        return accountArray;
    } // End method.

    /**
     * login - allows the user to enter the account based on the the info they
     * enter. will also update the textfield in the main menu to that of the
     * account holder.
     */
    @FXML
    public void login() {
        
        // Use a thread to import JSON file into the Accounts array when you press the login button.
        // Alternate method if JSON isnt imported through the menu item in menu bar.
        Thread t = new Thread(() -> {
            try 
            {
                this.Accounts = readFromJson();
            } // End try.
            catch (FileNotFoundException ex) 
            {
                Logger.getLogger(LoginPageController.class.getName()).log(Level.SEVERE, null, ex);
            } // End catch.
        }); // End thread.
        
        // Start thread.
        t.start();
        
        // Get the data that are entered into the login text fields.
        acctNum = accountNumTextField.getText();
        String pinNum = pinCodeTextField.getText();

        // Based on the account Number that is entered into the text field,
        // Retrieve the designated account based on the string entered.
        currentAccount = Account.search(Accounts, acctNum);

        // Get the name on the account.
        String acctName = currentAccount.getName();

        // If the pin number entered doesn't match that of the pin on the account,
        // retry entering the pin code until they match.
        if (!currentAccount.getPin().equals(pinNum)) 
        {
            // Set wrong pin number text visibile if both pin numbers don't match.
            wrongPinLabel.setVisible(true);
        } // End if.
        else 
        {
            // Retrieve the controller for MainMenu in order to update the text field to show the name.
            try 
            {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MainMenu.fxml"));
                root = fxmlLoader.load();

                // Gets the MainMenuController.
                MainMenuController mainMenuController = fxmlLoader.getController();

                // Updates name for that of the account holder.
                mainMenuController.updateName(acctName);
                mainMenuController.updateNum(acctNum);

                // Sets stage.
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();
            } // End try.
            catch (IOException ex) 
            {
                Logger.getLogger(LoginPageController.class.getName()).log(Level.SEVERE, null, ex);
            } // End catch.
        } // End else.       
    } // End login.

    /**
     * Method will be used whenever a JDBC connection must be established to
     * connect to Microsoft Access Database.
     */
    public void openJDBC() 
    {
        try 
        {
            databaseURL = "jdbc:ucanaccess://.//Accounts.accdb"; // Access the Bank DB in Microsoft Access.

            conn = java.sql.DriverManager.getConnection(databaseURL);
        } // End try.
        catch (java.sql.SQLException ex) 
        {
            Logger.getLogger(LoginPageController.class.getName()).log(Level.SEVERE, null, ex);
        } // End catch.
    } // End openJDBC.

    /**
     * Gather the information from the Account array, traverse through it and
     * insert data into DB.
     *
     * @throws java.io.FileNotFoundException
     * @throws java.sql.SQLException
     */
    @FXML
    public void transferToDB() throws FileNotFoundException, SQLException 
    {
        // Establish JDBC connection to Microsoft Access database.
        databaseURL = "";

        conn = null;

        // Establish JDBC connection.
        openJDBC();

        // Get the array that is populated with VideoGame objects.
        Accounts = readFromJson();

        // Initiate variables for attributes.
        String name = null;
        String id = null;
        String pin = null;
        int balance = 0;

        // Before inserting into DB, delete all table rows.
        sql = "DELETE FROM Accounts";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        int rowsDeleted = preparedStatement.executeUpdate();

        String sql2 = "INSERT INTO Accounts (Name, Id, Pin, Balance) VALUES (?, ?, ?, ?)";

        // Traverse through each element in the array, pull each attribute from the object to insert into database row.
        for (int i = 0; i < Accounts.length; i++) {
            name = Accounts[i].getName();
            id = Accounts[i].getId();
            pin = Accounts[i].getPin();
            balance = Accounts[i].getBal();

            try {
                PreparedStatement ps = conn.prepareStatement(sql2);

                ps.setString(1, name);

                ps.setString(2, id);

                ps.setString(3, pin);

                ps.setInt(4, balance);

                int row = ps.executeUpdate();

                if (row > 0) {
                    System.out.println("Row inserted");
                }
            } 
            catch (SQLException e) 
            {
            }
        } // End for.
    } // End transferToJDBC.

    /**
     * getCurrentAccount - get current account.
     * @return 
     */
    public Account getCurrentAccount() 
    {
        return this.currentAccount;
    } // End getCurrentAccount.
} // End LoginPageController.
