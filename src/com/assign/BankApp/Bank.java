package com.assign.BankApp;

import java.util.Iterator;
import java.util.Scanner;

public class Bank {
    public Bank() {
    }

    public static void main(String[] args) {
        BankAccountDAO bankAccountDAO = new BankAccountDAOImpl();
        Scanner input = new Scanner(System.in);

        int option;
        do {
            System.out.println("Please choose a valid selection to continue.\n1.List all user.\n2.Add new user.\n3.Update user information.\n4.Delete user.\n5.Deposit.\n6.Withdraw.\n7.Balance.\n8.Exit.");
            option = input.nextInt();
            int userAccountNum;
            String username;
            String userEmail;
            int userPhoneNum;
            double money;
            BankAccount bankAccountUpdateName;
            BankAccount bankAccount;
            BankAccount bankAccountWithdraw;
            switch(option) {
                case 1:
                    Iterator var14 = bankAccountDAO.getAllUsers().iterator();

                    while(var14.hasNext()) {
                        bankAccount = (BankAccount)var14.next();
                        System.out.println("AccountNo.: " + bankAccount.getAccountNum() + ",Name: " + bankAccount.getName());
                    }

                    System.out.println("Please input name to display user's information: ");
                    username = input.next();
                    var14 = bankAccountDAO.getAllUsers().iterator();

                    while(var14.hasNext()) {
                        bankAccount = (BankAccount)var14.next();
                        if (bankAccount.getName().equals(username)) {
                            userAccountNum = bankAccount.getAccountNum();
                            bankAccountWithdraw = bankAccountDAO.getUserInfo(userAccountNum - 1);
                            System.out.println("Bank account: [Account No.: " + bankAccountWithdraw.getAccountNum() + ", Name: " + bankAccountWithdraw.getName() + ", Email: " + bankAccountWithdraw.getEmail() + ", Phone: " + bankAccountWithdraw.getPhoneNum() + ", Balance: " + bankAccountWithdraw.getBalance() + " ]");
                        }
                    }
                    break;
                case 2:
                    System.out.println("Please input your name: ");
                    username = input.next();
                    System.out.println("Please input your email: ");
                    userEmail = input.next();
                    System.out.println("Please input your phone number: ");
                    userPhoneNum = input.nextInt();
                    bankAccountDAO.addUser(username, userEmail, userPhoneNum);
                    System.out.println("Add user success!");
                    break;
                case 3:
                    do {
                        do {
                            System.out.println("Please input account number.");
                            userAccountNum = input.nextInt();
                        } while(userAccountNum > bankAccountDAO.getAllUsers().size());
                    } while(userAccountNum < 0);

                    System.out.println("Please choose a valid selection to continue.\n1.Update user's name.\n2.Update user's email.\n3.Update user's phone number.");
                    option = input.nextInt();
                    switch(option) {
                        case 1:
                            bankAccountUpdateName = (BankAccount)bankAccountDAO.getAllUsers().get(userAccountNum - 1);
                            System.out.println("Current name is: " + bankAccountUpdateName.getName());
                            System.out.println("Please input new name: ");
                            username = input.next();
                            bankAccountUpdateName.setName(username);
                            bankAccountDAO.updateUserName(bankAccountUpdateName);
                            continue;
                        case 2:
                            bankAccount = (BankAccount)bankAccountDAO.getAllUsers().get(userAccountNum - 1);
                            System.out.println("Current email is: " + bankAccount.getEmail());
                            System.out.println("Please input new email: ");
                            userEmail = input.next();
                            bankAccount.setEmail(userEmail);
                            bankAccountDAO.updateUserEmail(bankAccount);
                            continue;
                        case 3:
                            bankAccountWithdraw = (BankAccount)bankAccountDAO.getAllUsers().get(userAccountNum - 1);
                            System.out.println("Current phone number is: " + bankAccountWithdraw.getPhoneNum());
                            System.out.println("Please input new phone number: ");
                            userPhoneNum = input.nextInt();
                            bankAccountWithdraw.setPhoneNum(userPhoneNum);
                            bankAccountDAO.updateUserPhoneNum(bankAccountWithdraw);
                            continue;
                        default:
                            System.out.println("Input error, please try again.");
                            continue;
                    }
                case 4:
                    do {
                        do {
                            System.out.println("Please input account number to delete a user.");
                            userAccountNum = input.nextInt();
                        } while(userAccountNum > bankAccountDAO.getAllUsers().size());
                    } while(userAccountNum < 0);

                    bankAccountUpdateName = (BankAccount)bankAccountDAO.getAllUsers().get(userAccountNum - 1);
                    bankAccountDAO.deleteBankAccount(bankAccountUpdateName);
                    break;
                case 5:
                    do {
                        do {
                            System.out.println("Please input account number to deposit.");
                            userAccountNum = input.nextInt();
                        } while(userAccountNum > bankAccountDAO.getAllUsers().size());
                    } while(userAccountNum < 0);

                    bankAccount = (BankAccount)bankAccountDAO.getAllUsers().get(userAccountNum - 1);
                    System.out.println("Please enter the deposit amount.");
                    money = input.nextDouble();
                    if (money < 5.0D || money > 10000.0D) {
                        do {
                            do {
                                System.out.println("Please try again. (We only allow user deposit between $5 - $10000.)");
                                money = input.nextDouble();
                            } while(money < 5.0D);
                        } while(money > 10000.0D);
                    }

                    bankAccount.setBalance(bankAccount.getBalance() + money);
                    bankAccountDAO.deposit(bankAccount);
                    break;
                case 6:
                    do {
                        do {
                            System.out.println("Please input account number to withdraw.");
                            userAccountNum = input.nextInt();
                        } while(userAccountNum > bankAccountDAO.getAllUsers().size());
                    } while(userAccountNum < 0);

                    bankAccountWithdraw = (BankAccount)bankAccountDAO.getAllUsers().get(userAccountNum - 1);
                    System.out.println("Please enter the withdraw amount.");
                    money = input.nextDouble();
                    if (money > bankAccountWithdraw.getBalance() || money <= 0.0D) {
                        do {
                            do {
                                System.out.println("Please try again. (" + bankAccountWithdraw.getBalance() + " available)");
                                money = input.nextDouble();
                            } while(money > bankAccountWithdraw.getBalance());
                        } while(money <= 0.0D);
                    }

                    bankAccountWithdraw.setBalance(bankAccountWithdraw.getBalance() - money);
                    bankAccountDAO.withdraw(bankAccountWithdraw);
                    break;
                case 7:
                    do {
                        do {
                            System.out.println("Please input account number to show balance.");
                            userAccountNum = input.nextInt();
                        } while(userAccountNum > bankAccountDAO.getAllUsers().size());
                    } while(userAccountNum < 0);

                    BankAccount bankAccountBalance = (BankAccount)bankAccountDAO.getAllUsers().get(userAccountNum - 1);
                    bankAccountDAO.showBalance(bankAccountBalance);
                case 8:
                    break;
                default:
                    System.out.println("Input error, please try again.");
            }
        } while(option != 8);

    }
}