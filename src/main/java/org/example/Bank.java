package org.example;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.lang.reflect.Type;
import java.util.List;

public class Bank {

    private List<BankAccount> accounts;

    public Bank() {
        // Load accounts from JSON when Bank is created
        accounts = loadAccountsFromJson();
    }

    // Nested BankAccount class as before
    public class BankAccount {
        private double balance;

        public BankAccount() {
            balance = 0;
        }

        BankAccount(double balance) {
            this.balance = balance;
        }

        public double getBalance() {
            return balance;
        }

        public void setBalance(double balance) {
            this.balance = balance;
        }


        //-------------------------------------------------------------------//
        public void deposit(double amount) {
            if (amount > 0) {
                balance += amount;
            } else {
                throw new IllegalArgumentException("Deposit amount must be positive");
            }
        }


        //-------------------------------------------------------------------//
        public void withdraw(double amount) {
            if (amount > 0 && amount <= this.balance) {
                balance -= amount;
            } else {
                throw new IllegalArgumentException("Withdraw amount is invalid or exceeds balance");
            }
        }


        //-------------------------------------------------------------------//
        public void printBalance() {
            System.out.println("Current balance: " + balance);
        }


        //-------------------------------------------------------------------//
        public void transfer(BankAccount newAccount, double amount) {
            if (amount > 0 && amount <= this.balance) {
                this.balance -= amount;
                newAccount.deposit(amount);
            } else {
                throw new IllegalArgumentException("Transfer amount is invalid or exceeds balance");
            }
        }
    }



//---------------------------------------------------------------------//



    // Method to load accounts from JSON file
    public List<BankAccount> loadAccountsFromJson() {
        try {
            Gson gson = new Gson();
            Reader reader = new FileReader("accounts.json"); // Specify the location of the JSON file
            Type accountListType = new TypeToken<List<BankAccount>>(){}.getType();
            return gson.fromJson(reader, accountListType);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Method to save accounts to JSON file
    public void saveAccountsToJson() {
        try {
            Gson gson = new Gson();
            Writer writer = new FileWriter("accounts.json");
            gson.toJson(accounts, writer);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<BankAccount> getAccounts() {
        return accounts;
    }
}
