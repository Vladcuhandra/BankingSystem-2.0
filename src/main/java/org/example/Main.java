package org.example;

import java.util.List;
import java.util.ArrayList;
public class Main {

    public static void main(String[] args) {

        // Create an instance of the Bank, which loads accounts from JSON
        Bank bank = new Bank();

        // Get accounts from the bank
        List<Bank.BankAccount> accounts = bank.getAccounts();

        // If you have accounts in the JSON file, print initial balances
        if (accounts != null && accounts.size() > 0) {
            System.out.println("Initial Balances:");
            for (Bank.BankAccount account : accounts) {
                account.printBalance();
            }

            // Perform some transactions
            accounts.get(0).deposit(30.6);
            accounts.get(1).withdraw(59.3);
            accounts.get(3).withdraw(58.2);

            // Transfer money between accounts
            accounts.get(0).transfer(accounts.get(1), 200);
            accounts.get(4).transfer(accounts.get(5), 200);

            // Print balances after transactions
            System.out.println("\nBalances after transactions:");
            for (Bank.BankAccount account : accounts) {
                account.printBalance();
            }

            // Save the updated accounts to JSON
            bank.saveAccountsToJson();
        } else {
            System.out.println("No accounts available in the JSON file.");
        }

    }
}
