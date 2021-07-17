package com.assign.BankApp;

import java.util.ArrayList;
import java.util.List;

public class BankAccountDAOImpl implements BankAccountDAO{
    List<BankAccount> bankAccounts;
    int newAccountNum = 3;
    public BankAccountDAOImpl(){
        bankAccounts = new ArrayList<BankAccount>();
        BankAccount bankAccount1 = new BankAccount("Alice", "1@gmail.com", 111111,1, 1000);
        BankAccount bankAccount2 = new BankAccount("Bob", "2@gmail.com", 22222,2, 2000);
        bankAccounts.add(bankAccount1);
        bankAccounts.add(bankAccount2);
    }

    @Override
    public void addUser(String name, String email, int phoneNum) {
        BankAccount bankAccount = new BankAccount(name, email, phoneNum, newAccountNum++, 0.0);
        bankAccounts.add(bankAccount);
    }

    @Override
    public void updateUserName(BankAccount bankAccount) {
        System.out.println(bankAccount.getAccountNum());
        bankAccounts.get(bankAccount.getAccountNum() - 1).setName(bankAccount.getName());
        System.out.println("Bank account No. : " + bankAccount.getAccountNum() + ", update username in the database.");
    }

    @Override
    public void updateUserEmail(BankAccount bankAccount) {
        bankAccounts.get(bankAccount.getAccountNum() - 1).setEmail(bankAccount.getEmail());
        System.out.println("Bank account No. : " + bankAccount.getAccountNum() + ", update email in the database.");
    }

    @Override
    public void updateUserPhoneNum(BankAccount bankAccount) {
        bankAccounts.get(bankAccount.getAccountNum() - 1).setPhoneNum(bankAccount.getPhoneNum());
        System.out.println("Bank account No. : " + bankAccount.getAccountNum() + ", update phone number in the database.");
    }

    @Override
    public void deleteBankAccount(BankAccount bankAccount) {
        int accNum = bankAccount.getAccountNum();
        bankAccounts.remove(bankAccount.getAccountNum() - 1);
        for (BankAccount user : bankAccounts){
            if(user.getAccountNum() > accNum){
                user.setAccountNum(user.getAccountNum() - 1);
            }
        }
        newAccountNum -= 1;
        System.out.println("Bank account No. : " + bankAccount.getAccountNum() + ", deleted from database.");
    }

    @Override
    public List<BankAccount> getAllUsers() {
        return bankAccounts;
    }

    @Override
    public BankAccount getUserInfo(int accountNum) {
        return bankAccounts.get(accountNum);
    }

    @Override
    public void deposit(BankAccount bankAccount) {
        bankAccounts.get(bankAccount.getAccountNum() - 1).setBalance(bankAccount.getBalance());
        System.out.println("Deposit success");
    }

    @Override
    public void withdraw(BankAccount bankAccount) {
        bankAccounts.get(bankAccount.getAccountNum() - 1).setBalance(bankAccount.getBalance());
        System.out.println("Withdraw success");
    }

    @Override
    public void showBalance(BankAccount bankAccount) {
        System.out.println("Account balance is: " + bankAccount.getBalance());
    }
}