/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.atm;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author canen
 */
public class AccountTest {
    
    /**
     * Account Testing
     * Account (String name, String id, String pinCode, int balance)
     */
    
    public AccountTest() {
    }

    /**
     * Test of getName method, of class Account.
     */
    @org.junit.jupiter.api.Test
    public void testGetName() {
        Account account1 = new Account("Chris Canenguez", "1234", "0246", 1500);
        
        String expectedName = "Chris Canenguez";
        
        String actualName = account1.getName();
        
        assertEquals(expectedName, actualName);
    } // End testGetName

    /**
     * Test of getId method, of class Account.
     */
    @org.junit.jupiter.api.Test
    public void testGetId() {
        Account account1 = new Account("Chris Canenguez", "1234", "0246", 1500);
        
        String expectedId = "1234";
        
        String actualId = account1.getId();
        
        assertEquals(expectedId, actualId);
    } // End testGetId.

    /**
     * Test of getPin method, of class Account.
     */
    @org.junit.jupiter.api.Test
    public void testGetPin() {
        Account account1 = new Account("Chris Canenguez", "1234", "0246", 1500);
        
        String expectedPin = "0246";
        
        String actualPin = account1.getPin();
        
        assertEquals(expectedPin, actualPin);
    } // End testGetPin.

    /**
     * Test of getBal method, of class Account.
     */
    @org.junit.jupiter.api.Test
    public void testGetBal() {
        Account account1 = new Account("Chris Canenguez", "1234", "0246", 1500);
        
        int expectedBal = 1500;
        
        int actualBal = account1.getBal();
        
        assertEquals(expectedBal, actualBal);
    } // End testGetBal.

    /**
     * Test of deposit method, of class Account.
     */
    @org.junit.jupiter.api.Test
    public void testDeposit() {
        Account account1 = new Account("Chris Canenguez", "1234", "0246", 1500);
        
        int expectedBalAfterDeposit = 2000;
        
        account1.deposit(500);
        
        int actualBalAfterDeposit = account1.getBal();
        
        assertEquals(expectedBalAfterDeposit, actualBalAfterDeposit);
    } // End testDeposit.

    /**
     * Test of withdraw method, of class Account.
     */
    @org.junit.jupiter.api.Test
    public void testWithdraw() {
        Account account1 = new Account("Chris Canenguez", "1234", "0246", 1500);
        
        int expectedBalAfterWithdraw = 1250;
        
        account1.withdraw(250);
        
        int actualBalAfterWithdraw = account1.getBal();
        
        assertEquals(expectedBalAfterWithdraw, actualBalAfterWithdraw);
    } // End testWithdraw.

    /**
     * Test of transfer method, of class Account.
     */
    @org.junit.jupiter.api.Test
    public void testTransfer() {
        Account account1 = new Account("Chris Canenguez", "1234", "0246", 1500);
        Account account2 = new Account("Jessie Pinkman", "2345", "1111", 750);
        
        account1.transfer(account2, 100);
        
        int expectedBalAccount1 = 1400;
        int actualBalAccount1 = account1.getBal();
        
        assertEquals(expectedBalAccount1, actualBalAccount1);
        
        int expectedBalAccount2 = 850;
        int actualBalAccount2 = account2.getBal();
        
        assertEquals(expectedBalAccount2, actualBalAccount2);
    } // End testTransfer.

    /**
     * Test of toString method, of class Account.
     */
    @org.junit.jupiter.api.Test
    public void testToString() {
        Account account1 = new Account("Chris Canenguez", "1234", "0246", 1500);
        
        String expectedString = "Account Name: Chris Canenguez" + "\n" + 
                                "Account ID: 1234" + "\n" +
                                "Account Balance: " + 1500 + "\n" + 
                                "Account Pin: 0246";
        
        String actualString = account1.toString();
        
        assertEquals(expectedString, actualString);
    } // End testToString.

    /**
     * Test of equals method, of class Account.
     */
    @org.junit.jupiter.api.Test
    public void testEquals() {
        Account account1 = new Account("Chris Canenguez", "1234", "0246", 1500);
        Account account2 = new Account("Chris Canenguez", "1234", "1234", 1500);
        
        boolean expected = false;
        boolean actual = account1.equals(account2);
        
        assertEquals(expected, actual);
    } // End testEquals.
    
    /**
     * Test of search method, of class Account.
     */
    @org.junit.jupiter.api.Test
    public void testSearch() {
        Account account1 = new Account("Chris Canenguez", "1234", "0000", 1000);
        Account account2 = new Account("Jessie Pinkman", "2345", "1111", 2000);
        Account account3 = new Account("Walter White", "3456", "2222", 5000);
        
        Account[] Bank = {account1, account2, account3};
        
        Account expectedResult = new Account("Jessie Pinkman", "2345", "1111", 2000);
        Account actualResult = Account.search(Bank, "2345");
        
        assertEquals(expectedResult.getId(), actualResult.getId());
    } // End testEquals.
}
