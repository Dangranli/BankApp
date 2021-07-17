
package com.assign.BankApp;

import java.util.List;

public interface BankAccountDAO {
    void addUser(String var1, String var2, int var3);

    void updateUserName(BankAccount var1);

    void updateUserEmail(BankAccount var1);

    void updateUserPhoneNum(BankAccount var1);

    void deleteBankAccount(BankAccount var1);

    List<BankAccount> getAllUsers();

    BankAccount getUserInfo(int var1);

    void deposit(BankAccount var1);

    void withdraw(BankAccount var1);

    void showBalance(BankAccount var1);
}