package com.mycompany.atm;

import com.google.gson.annotations.SerializedName;

/**
 * Account Class - Create Account objects.
 *
 * @author (Christopher Canenguez)
 * @version (3.21.2022)
 */
public class Account implements Comparable<Account>
{
    // Instance Variables.
    @SerializedName("name")
    private String theName;
    
    @SerializedName("id")
    private String theId;
    
    @SerializedName("balance")
    private int theBalance;
    
    @SerializedName("pin")
    private String thePin;
    
    /**
     * Constructor for objects of class Account
     * based on name of person, id, and how much money is in the account
     * 
     * @name - name of person
     * @id - id of account
     * @pinCode - pin for account
     * @balance - how much money in account in dollars only.
     */
    public Account(String name, String id, String pinCode, int balance)
    {
       this.theName = name;
       this.theId = id;
       this.thePin = pinCode;
       this.theBalance = balance;
    } // End Account.
    
    /**
     * getName - return name of account.
     * 
     * @return -- name of account
     */
    public String getName()
    {
        return this.theName;
    } // End getName.
    
    /**
     * getID - return Id of the account.
     * 
     * @return -- Id of account
     */
    public String getId()
    {
        return this.theId;
    } // End getId.
    
    /**
     * getPin - return the pin of the account.
     * @return -- Pin of account.
     */
    public String getPin()
    {
        return this.thePin;
    } // End getPin.
    
    /**
     * getBal - return the current balance of the account in dollars.
     * 
     * @return -- Money balance of account
     */
    public int getBal()
    {
        return this.theBalance;
    } // End getBal.
    
    /**
     * deposit - put money into account
     *
     * @moneyDeposit - how much money to be added into account
     */
    public void deposit(int moneyDeposit)
    {
        this.theBalance = this.theBalance + moneyDeposit;
    } // End deposit.
    
    /**
     * withdraw - take money out of current account.
     * 
     * @moneyWithdrawn -- how much money will be taken out of the account
     */
    public void withdraw (int moneyWithdrawn)
    {
        this.theBalance = this.theBalance - moneyWithdrawn;
    } // End withdraw.
    
    /**
     * transfer - move amount of money from one account to another.
     * 
     * @thatAccount -- account that money will be transferred to.
     * @transferAmount -- amount deposited into that account.
     */
    public void transfer (Account thatAccount, int transferAmount)
    {
        // Take transferAmount out of current account.
        withdraw(transferAmount); 
        
        // Put money taken out into this account.
        thatAccount.deposit(transferAmount); 
    } // End transfer.
    
    /**
     * toString: string representation of account.
     * 
     * @returns -- returns string representation of account
     */
    @Override
    public String toString()
    {
        String result = "Account Name: " + this.getName() + "\n" + 
                        "Account ID: " + this.getId() + "\n" +
                        "Account Balance: " + this.getBal() + "\n" + 
                        "Account Pin: " + this.getPin();
                        
        return result;                
    } // End toString;
    
    /**
     * equals - checks if two accounts are the same or not.
     * 
     * @param thatAccount
     * @return -- boolean, whether both account are equal or not.
     */
    public boolean equals(Account thatAccount)
    {
        if(this.theName.equals(this.getId().compareTo(thatAccount.getId()) != 0))
        {
            return true;
        } // End if.
        else
        {
            return false;
        } // End else.
    } // End equals.
    
    // compareTo: compare two Account objects.
    // Precondition: parameter o is an Object (of type Account)
    // Postcondition: return 0 if this.id is the same as o.id, -1 if this.id < o.id, 1 if this.id > o.id.
    @Override 
    public int compareTo(Account account)
    {
        return (this.getId().compareTo(account.getId()));
    } // End compareTo.
    
    /**
     * search - will search through array of Accounts if an account with id exists.
     * @param Bank - Array of accounts.
     * @param id - id
     * @return - account with id, if not, null.
     */
    public static Account search(Account[] Bank, String id)
    {
        for (int i = 0; i < Bank.length; i++)
        {
            if (Bank[i].getId().equals(id))
            {
                return Bank[i];
            } // End if.
        } // End for.
        return null;
    } // End search.
} // End Account.
