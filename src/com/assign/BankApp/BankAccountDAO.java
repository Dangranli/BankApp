package com.assign.BankApp;

import java.util.List;

public interface BankAccountDAO {
    void addUser(String name, String email, int phoneNum);

    void updateUserName(BankAccount bankAccount);

    void updateUserEmail(BankAccount bankAccount);

    void updateUserPhoneNum(BankAccount bankAccount);

    void deleteBankAccount(BankAccount bankAccount);

    List<BankAccount> getAllUsers();

    BankAccount getUserInfo(int accountNum);

    void deposit(BankAccount bankAccount);

    void withdraw(BankAccount bankAccount);

    void showBalance(BankAccount bankAccount);

}