package com.assign.BankApp;

class BankAccount {
    private String name;
    private String email;
    private int phoneNum;
    private int accountNum;
    private double balance;

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    BankAccount(String name, String email, int phoneNum, int accountNum, double balance) {
        this.name = name;
        this.email = email;
        this.phoneNum = phoneNum;
        this.accountNum = accountNum;
        this.balance = balance;
    }


    public String getName () {
        return name;
    }

    public void setName (String name){
        this.name = name;
    }

    public String getEmail () {
        return email;
    }

    public void setEmail (String email){
        this.email = email;
    }

    public int getPhoneNum () {
        return phoneNum;
    }

    public void setPhoneNum ( int phoneNum){
        this.phoneNum = phoneNum;
    }

    public int getAccountNum () {
        return accountNum;
    }

    public void setAccountNum ( int accountNum){
        this.accountNum = accountNum;

    }
}