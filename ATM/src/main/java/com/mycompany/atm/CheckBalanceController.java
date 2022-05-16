package com.mycompany.atm;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class CheckBalanceController {

    @FXML
    Label balanceLabel;
    
    /**
     * updateBalance - allows the user to enter a number into textfield, to be added to account balance.
     * @param balance 
     */
    @FXML
    public void updateBalance(int balance)
    {
        balanceLabel.setText(String.valueOf(balance));
    } // End updateBalance.
} // End CheckBalanceController.