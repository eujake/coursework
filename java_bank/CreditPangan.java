/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs6203_courseproject_pangan_eugenejake;
import java.util.Scanner;
public class CreditPangan {
    private String name;
    private long creditAccNo;
    private double creditLimit, creditBalance;
    Scanner scan = new Scanner(System.in);
    public CreditPangan(String name, long creditAccNo, double creditLimit){
        this.name           =   name;
        this.creditAccNo    =   creditAccNo;
        this.creditLimit    =   creditLimit;
    }
    
     // setters & getters
    public void set_name(String name){
        this.name = name;
    }
    public String get_name(){
        return name;
    }
    
    public void set_creditAccNo(long creditAccNo){
        this.creditAccNo = creditAccNo;
    }
    public long get_creditAccNo(){
        return creditAccNo;
    }
    
    public void set_creditBalance(double creditBalance){
        this.creditBalance  =   creditBalance;
    }
    public double get_creditBalance(){
        return creditBalance;
    }
    public void set_creditLimit(double setCreditLimit){
        creditLimit = setCreditLimit;
    }
    public double get_creditLimit(){
        return creditLimit;
    }
    
    public double purchase(double amount, double fixedCreditLimit){
        System.out.println("Credit limit: " + get_creditLimit() );
        double previousBalance = get_creditBalance();
        creditBalance += amount;
        double interest;
        interest = 0.03 * creditBalance;
        creditBalance += interest;
        double allowable = fixedCreditLimit - creditBalance;
        
        if(allowable <= 0){
            creditBalance = previousBalance;
            System.out.println("The current balance exceeds the credit limit, cannot purchase further.");
            System.out.println("Balance: " + previousBalance);
            
            return creditBalance;
        }
        else{
            System.out.println("Balance: " + creditBalance);
            return creditBalance;
        }
        
    }
    
    public void payCredit(double amount, double fixedCreditLimit){
        
        double previousBalance = get_creditBalance();
        
        if(amount > fixedCreditLimit){
            creditBalance = previousBalance;
            System.out.println("The payment exceeds the credit limit.");
            System.out.println("Balance: " + creditBalance);
        }
        else{
            creditBalance -= amount;
            System.out.println("Balance: " + creditBalance);
        }
    }
    public void closeAccount(){
        if( get_creditBalance() == 0){
            set_name(null);
            set_creditLimit(0);
            set_creditAccNo(0);
            System.out.println("You successfully close the account.");
        }else{
            System.out.println("Please pay the remaining balance.");

        }
    }

    public void inquireCreditBalance(){
        System.out.println("Account Number: " + creditAccNo );
        System.out.println("Account Name: " + name );
        System.out.println("Credit Limit: " + creditLimit );
        System.out.println("Credit Balance: " + creditBalance );
    }
    
    public boolean validateAccNo(int accNo){

        return accNo == creditAccNo; //then check if input == creditAccNo
    }
    

 
}