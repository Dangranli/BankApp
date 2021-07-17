package com.assign.BankApp;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BankAccountDAOImpl implements BankAccountDAO {
    List<BankAccount> bankAccounts = new ArrayList();
    int newAccountNum = 3;

    public BankAccountDAOImpl() {
        BankAccount bankAccount1 = new BankAccount("Alice", "1@gmail.com", 111111, 1, 1000.0D);
        BankAccount bankAccount2 = new BankAccount("Bob", "2@gmail.com", 22222, 2, 2000.0D);
        this.bankAccounts.add(bankAccount1);
        this.bankAccounts.add(bankAccount2);
    }

    public void addUser(String name, String email, int phoneNum) {
        BankAccount bankAccount = new BankAccount(name, email, phoneNum, this.newAccountNum++, 0.0D);
        this.bankAccounts.add(bankAccount);
    }

    public void updateUserName(BankAccount bankAccount) {
        System.out.println(bankAccount.getAccountNum());
        ((BankAccount)this.bankAccounts.get(bankAccount.getAccountNum() - 1)).setName(bankAccount.getName());
        System.out.println("Bank account No. : " + bankAccount.getAccountNum() + ", update username in the database.");
    }

    public void updateUserEmail(BankAccount bankAccount) {
        ((BankAccount)this.bankAccounts.get(bankAccount.getAccountNum() - 1)).setEmail(bankAccount.getEmail());
        System.out.println("Bank account No. : " + bankAccount.getAccountNum() + ", update email in the database.");
    }

    public void updateUserPhoneNum(BankAccount bankAccount) {
        ((BankAccount)this.bankAccounts.get(bankAccount.getAccountNum() - 1)).setPhoneNum(bankAccount.getPhoneNum());
        System.out.println("Bank account No. : " + bankAccount.getAccountNum() + ", update phone number in the database.");
    }

    public void deleteBankAccount(BankAccount bankAccount) {
        int accNum = bankAccount.getAccountNum();
        this.bankAccounts.remove(bankAccount.getAccountNum() - 1);
        Iterator var3 = this.bankAccounts.iterator();

        while(var3.hasNext()) {
            BankAccount user = (BankAccount)var3.next();
            if (user.getAccountNum() > accNum) {
                user.setAccountNum(user.getAccountNum() - 1);
            }
        }

        --this.newAccountNum;
        System.out.println("Bank account No. : " + bankAccount.getAccountNum() + ", deleted from database.");
    }

    public List<BankAccount> getAllUsers() {
        return this.bankAccounts;
    }

    public BankAccount getUserInfo(int accountNum) {
        return (BankAccount)this.bankAccounts.get(accountNum);
    }

    public void deposit(BankAccount bankAccount) {
        ((BankAccount)this.bankAccounts.get(bankAccount.getAccountNum() - 1)).setBalance(bankAccount.getBalance());
        System.out.println("Deposit success");
    }

    public void withdraw(BankAccount bankAccount) {
        ((BankAccount)this.bankAccounts.get(bankAccount.getAccountNum() - 1)).setBalance(bankAccount.getBalance());
        System.out.println("Withdraw success");
    }

    public void showBalance(BankAccount bankAccount) {
        System.out.println("Account balance is: " + bankAccount.getBalance());
    }
}