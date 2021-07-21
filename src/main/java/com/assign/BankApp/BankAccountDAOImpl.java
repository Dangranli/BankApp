package com.assign.BankApp;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BankAccountDAOImpl implements BankAccountDAO{
    List<BankAccount> bankAccounts;
    int newAccountNum = 1;
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://testdatabase-1.c6puqizoklxj.us-west-1.rds.amazonaws.com:3306/BankDB";
    static final String USER = "admin";
    static final String PASS = "12345678";
    private static final String QUERY = "SELECT name, email, phoneNum, accountNum,balance FROM UserInfo";

    public BankAccountDAOImpl(){
        bankAccounts = new ArrayList<BankAccount>();
    }

    @Override
    public void addUser(String name, String email, int phoneNum) {
        try(
                Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                Statement stmt = conn.createStatement()
        ) {
            // Execute a query
            System.out.println("Inserting records into the table...");
            String sql = "INSERT INTO UserInfo VALUES ('"+name+"' ,'"+email+"' , '"+phoneNum+"' ,  '"+(newAccountNum++)+"' , 0.0)";
            stmt.executeUpdate(sql);

            System.out.println("Inserted records into the table...");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateUserName(BankAccount bankAccount) {
        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();
        ) {
            String sql = "UPDATE UserInfo " +
                    "SET name = '"+bankAccount.getName()+"' WHERE accountNum in ('"+bankAccount.getAccountNum()+"')";
            stmt.executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Bank account No. : " + bankAccount.getAccountNum() + ", update username in the database.");

    }

    @Override
    public void updateUserEmail(BankAccount bankAccount) {
        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();
        ) {
            String sql = "UPDATE UserInfo " +
                    "SET email = '"+bankAccount.getEmail()+"' WHERE accountNum in ('"+bankAccount.getAccountNum()+"')";
            stmt.executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("Bank account No. : " + bankAccount.getAccountNum() + ", update email in the database.");
    }

    @Override
    public void updateUserPhoneNum(BankAccount bankAccount) {
        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();
        ) {
            String sql = "UPDATE UserInfo " +
                    "SET phoneNum = '"+bankAccount.getPhoneNum()+"' WHERE accountNum in ('"+bankAccount.getAccountNum()+"')";
            stmt.executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Bank account No. : " + bankAccount.getAccountNum() + ", update phone number in the database.");
    }

    @Override
    public void deleteBankAccount(BankAccount bankAccount) {
        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        ) {
            String sql = "DELETE FROM UserInfo " +
                    "WHERE accountNum = '"+bankAccount.getAccountNum()+"'";
            stmt.executeUpdate(sql);

            ResultSet rs = stmt.executeQuery(QUERY);
            while(rs.next()) {
                int i = rs.getInt("accountNum");
                if (rs.getInt("accountNum") > bankAccount.getAccountNum()) {
                    rs.updateInt("accountNum",i-1);
                    rs.updateRow();
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

//        newAccountNum -= 1;
        System.out.println("Bank account No. : " + bankAccount.getAccountNum() + ", deleted from database.");
    }

    @Override
    public List<BankAccount> getAllUsers() {
        bankAccounts = new ArrayList<BankAccount>();
        newAccountNum = 1;
        try(

                Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(QUERY);
        ) {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Connecting to database...");
//            newAccountNum = 1;
            while(rs.next()){
                //Display values
                BankAccount bankAccount = new BankAccount(rs.getString("name"), rs.getString("email"), rs.getInt("phoneNum"),rs.getInt("accountNum"), rs.getDouble("balance"));
                bankAccounts.add(bankAccount);
                newAccountNum++;
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return bankAccounts;
    }

    @Override
    public BankAccount getUserInfo(int accountNum) {
        return bankAccounts.get(accountNum);
    }

    @Override
    public void deposit(BankAccount bankAccount) {
        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();
        ) {
            String sql = "UPDATE UserInfo " +
                    "SET balance = '"+bankAccount.getBalance()+"' WHERE accountNum in ('"+bankAccount.getAccountNum()+"')";
            stmt.executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Deposit success");
    }

    @Override
    public void withdraw(BankAccount bankAccount) {
        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();
        ) {
            String sql = "UPDATE UserInfo " +
                    "SET balance = '"+bankAccount.getBalance()+"' WHERE accountNum in ('"+bankAccount.getAccountNum()+"')";
            stmt.executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Withdraw success");
    }

    @Override
    public void showBalance(BankAccount bankAccount) {
        System.out.println("Account balance is: " + bankAccount.getBalance());
    }
}