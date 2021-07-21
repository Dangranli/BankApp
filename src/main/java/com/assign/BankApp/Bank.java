package com.assign.BankApp;

import java.util.Scanner;

public class Bank {
    public static void main(String[] args) {
        BankAccountDAO bankAccountDAO = new BankAccountDAOImpl();
        Scanner input = new Scanner(System.in);

        int option;
        int userAccountNum ;
        String username;
        String userEmail;
        int userPhoneNum;
        double money;
        do {
            System.out.println("Please choose a valid selection to continue.\n" +
                    "1.List all user.\n" +
                    "2.Add new user.\n" +
                    "3.Update user information.\n" +
                    "4.Delete user.\n" +
                    "5.Deposit.\n" +
                    "6.Withdraw.\n" +
                    "7.Balance.\n" +
                    "8.Exit.");
            option = input.nextInt();
            switch (option) {

                case 1:
                    for (BankAccount bankAccount : bankAccountDAO.getAllUsers()) {
                        System.out.println("AccountNo.: " + bankAccount.getAccountNum() + ",Name: " + bankAccount.getName());
                    }
                    System.out.println("Please input name to display user's information: ");
                    username = input.next();

                    for (BankAccount bankAccount : bankAccountDAO.getAllUsers()) {
                        if(bankAccount.getName().equals(username)) {
                            userAccountNum = bankAccount.getAccountNum();
                            BankAccount bankAccountInfo = bankAccountDAO.getUserInfo(userAccountNum - 1);
                            System.out.println("Bank account: [Account No.: " + bankAccountInfo.getAccountNum() +
                                    ", Name: " + bankAccountInfo.getName() +
                                    ", Email: " + bankAccountInfo.getEmail() +
                                    ", Phone: " + bankAccountInfo.getPhoneNum() +
                                    ", Balance: " +bankAccountInfo.getBalance() + " ]");
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
                    do{
                        System.out.println("Please input account number.");
                        userAccountNum = input.nextInt();
                    }while(userAccountNum > bankAccountDAO.getAllUsers().size() || userAccountNum < 0);
                    System.out.println(("Please choose a valid selection to continue.\n" +
                            "1.Update user's name.\n" +
                            "2.Update user's email.\n" +
                            "3.Update user's phone number." ));
                    option = input.nextInt();
                    switch (option) {
                        case 1:
                            BankAccount bankAccountUpdateName = bankAccountDAO.getAllUsers().get(userAccountNum - 1);
                            System.out.println("Current name is: " + bankAccountUpdateName.getName());
                            System.out.println("Please input new name: ");
                            username = input.next();
                            bankAccountUpdateName.setName(username);
                            bankAccountDAO.updateUserName(bankAccountUpdateName);
                            break;
                        case 2:
                            BankAccount bankAccountUpdateEmail = bankAccountDAO.getAllUsers().get(userAccountNum - 1);
                            System.out.println("Current email is: " + bankAccountUpdateEmail.getEmail());
                            System.out.println("Please input new email: ");
                            userEmail = input.next();
                            bankAccountUpdateEmail.setEmail(userEmail);
                            bankAccountDAO.updateUserEmail(bankAccountUpdateEmail);
                            break;
                        case 3:
                            BankAccount bankAccountUpdatePhone = bankAccountDAO.getAllUsers().get(userAccountNum - 1);
                            System.out.println("Current phone number is: " + bankAccountUpdatePhone.getPhoneNum());
                            System.out.println("Please input new phone number: ");
                            userPhoneNum = input.nextInt();
                            bankAccountUpdatePhone.setPhoneNum(userPhoneNum);
                            bankAccountDAO.updateUserPhoneNum(bankAccountUpdatePhone);
                            break;
                        default:
                            System.out.println("Input error, please try again.");
                            break;
                    }
                    break;
                case 4:
                    do{
                        System.out.println("Please input account number to delete a user.");
                        userAccountNum = input.nextInt();
                    }while(userAccountNum > bankAccountDAO.getAllUsers().size() || userAccountNum < 0);
                    BankAccount bankAccountDelete = bankAccountDAO.getAllUsers().get(userAccountNum - 1);
                    bankAccountDAO.deleteBankAccount(bankAccountDelete);
                    break;
                case 5:
                    do{
                        System.out.println("Please input account number to deposit.");
                        userAccountNum = input.nextInt();
                    }while(userAccountNum > bankAccountDAO.getAllUsers().size() || userAccountNum < 0);

                    BankAccount bankAccountDeposit = bankAccountDAO.getAllUsers().get(userAccountNum-1);
                    System.out.println("Please enter the deposit amount.");
                    money = input.nextDouble();
                    if(money < 5 || money > 10000){
                        do{
                            System.out.println("Please try again. (We only allow user deposit between $5 - $10000.)");
                            money = input.nextDouble();
                        }while(money < 5 || money > 10000);
                    }
                    bankAccountDeposit.setBalance(bankAccountDeposit.getBalance() + money);
                    bankAccountDAO.deposit(bankAccountDeposit);
                    break;
                case 6:
                    do{
                        System.out.println("Please input account number to withdraw.");
                        userAccountNum = input.nextInt();
                    }while(userAccountNum > bankAccountDAO.getAllUsers().size() || userAccountNum < 0);

                    BankAccount bankAccountWithdraw = bankAccountDAO.getAllUsers().get(userAccountNum - 1);
                    System.out.println("Please enter the withdraw amount.");
                    money = input.nextDouble();
                    if(money > bankAccountWithdraw.getBalance() || money <= 0){
                        do{
                            System.out.println("Please try again. (" + bankAccountWithdraw.getBalance() + " available)");
                            money = input.nextDouble();
                        }while(money > bankAccountWithdraw.getBalance() || money <= 0);
                    }
                    bankAccountWithdraw.setBalance(bankAccountWithdraw.getBalance() - money);
                    bankAccountDAO.withdraw(bankAccountWithdraw);
                    break;
                case 7:
                    do{
                        System.out.println("Please input account number to show balance.");
                        userAccountNum = input.nextInt();
                    }while(userAccountNum > bankAccountDAO.getAllUsers().size() || userAccountNum < 0);

                    BankAccount bankAccountBalance = bankAccountDAO.getAllUsers().get(userAccountNum - 1);
                    bankAccountDAO.showBalance(bankAccountBalance);
                    break;
                case 8:
                    break;
                default:
                    System.out.println("Input error, please try again.");
                    break;
            }
        } while (option != 8);
    }
}