/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Eugene
 */
import java.util.Scanner;
import java.util.Random;

public class ClientPangan{

    public static void main(String[] args) {
        String name;
        double annualIncome, creditLimit;
        long accNo;
        CreditPangan[] cp = new CreditPangan[100];
        Scanner scan = new Scanner(System.in);
        boolean loop = true;//false = stop loop
        System.out.println("Choose below");
        int inputMainMenu;
        int index = 0;//to sort the account number

        boolean[] storage = new boolean[100];//use to locate the account name;
        do{
            displayMainMenu();//display main menu
            inputMainMenu=inputInt();//enter integer & check if valid
            if(inputMainMenu > 7 || inputMainMenu < 1){
                System.out.println("Please select from Main Menu only [1-6]");
            }else{
                boolean checkAccountNo = false;//false=Display Invalid accountNo
                switch( inputMainMenu ){
                    case 1:{
                        // create account method
                        System.out.println("Enter your name: ");
                        name = scan.nextLine();
                        System.out.println("Enter the annual income: ");
                        annualIncome = inputDouble();
                        if(annualIncome <= 200000){
                            creditLimit = 30000;
                        }else if( annualIncome > 200000 && annualIncome <= 300000){
                            creditLimit = 50000;
                        }else if(annualIncome > 300000 && annualIncome < 500000){
                            creditLimit = 80000;
                        }else{
                            creditLimit = 100000;
                        }
                        Random rand = new Random();
                        accNo = rand.nextInt(9999 - 999) + 999;// generate 4-random number
                        System.out.println("Account number: " + accNo);
                        cp[index] = new CreditPangan(name, accNo, creditLimit);
                        storage[index] = true;
                        System.out.println("Credit limit: " + cp[index].get_creditLimit());
                        index++;
                        break;
                    }
                    case 2:{
                        // Credit Inquiry
                        System.out.println("Enter your credit account number: ");
                        int inputAccountNo = inputInt();
                        for(int i=0; i<index; i++){
                            if(storage[i] == true){
                                if( cp[i].validateAccNo(inputAccountNo)){
                                    cp[i].inquireCreditBalance();
                                    checkAccountNo = true;
                                }
                            }
                        }if(!checkAccountNo){
                            System.out.println("Credit account number may invalid or not exist.");
                        }
                        break;
                    }
                    case 3:{
                        // Purchases Payment
                        System.out.println("Enter your credit account number: ");
                        int inputAccountNo = inputInt();
                        for(int i=0; i<index; i++){
                            if(storage[i] == true){
                                if( cp[i].validateAccNo(inputAccountNo)){
                                    System.out.println("Enter the purchase amount: ");
                                    double amount = checkIfNotZero();//Input with checking(not less than 1 & numerical only)
                                    double balance = cp[i].purchase( amount, cp[i].get_creditLimit());
                                    cp[i].set_creditBalance( balance );
                                    checkAccountNo = true;
                                }
                            }
                        }if(!checkAccountNo){
                            System.out.println("Credit account number may invalid or not exist.");
                        }
                        break;
                    }
                    case 4:{
                        // Payment
                        System.out.println("Enter your credit account number: ");
                        int inputAccountNo = inputInt();
                        for(int i=0; i<index; i++){
                            if(storage[i] == true){
                                if( cp[i].validateAccNo(inputAccountNo)){
                                    System.out.println("Enter the purchase amount: ");
                                    double amount = checkIfNotZero();//Input with checking(not less than 1 & numerical only)
                                    cp[i].payCredit(amount, cp[i].get_creditLimit() );
                                    checkAccountNo = true;
                                }
                            }
                        }if(!checkAccountNo){
                            System.out.println("Credit account number may invalid or not exist.");
                        }
                        break;
                    }

                    case 5:{
                        // Close Account
                        System.out.println("Enter your credit account number: ");
                        int inputAccountNo = inputInt();
                        for(int i=0; i<index; i++){
                            if(storage[i] == true){
                                if( cp[i].validateAccNo(inputAccountNo)){
                                    int userConfirmation;
                                    System.out.println("Do you want to close your account? Enter [0 - No, 1 - yes].");
                                    userConfirmation = inputInt();
                                    switch (userConfirmation) {
                                        case 0:{
                                            System.out.println("Closing account cancelled.");
                                            break;
                                        }
                                        case 1: {
                                            cp[i].closeAccount();
                                            break;
                                        }

                                        default:
                                            System.out.println("Please select between 1 and 0 only.");
                                            break;
                                    }
                                    checkAccountNo = true;
                                }
                            }if(!checkAccountNo){
                                System.out.println("Credit account number may invalid or not exist.");
                            }
                        }
                        break;
                    }
                    case 6:{
                        // end program
                        System.out.println("Program ends.");
                        loop = false;
                        break;
                    }
                }
            }
        }while( loop == true);
    }
    public static double inputDouble(){
        Scanner scan = new Scanner(System.in);
        double inputDouble=-99999;
        boolean loop = true;//false = stop loop
        do{
            try{
                inputDouble = scan.nextDouble();
            }catch(Exception e){
                System.out.println("Invalid INPUT! Enter again:");
                scan.next();//restart input
            }
            if( inputDouble != -99999){
                loop = false;
            }
        }while(loop == true);
        return inputDouble;
    }
    public static int inputInt(){
        Scanner scan = new Scanner(System.in);
        int inputInt=-99999;//ini
        boolean loop = true;
        do{
            try{
                inputInt = scan.nextInt();
            }catch(Exception e){
                System.out.println("Invalid INPUT! Enter again:");
                scan.next();
            }
            if( inputInt != -99999){
                loop = false;
            }
            
        }while(loop == true);
        return inputInt;
    }

    public static void displayMainMenu(){
        System.out.println("Main Menu");
        System.out.println("[1] New Credit Account");
        System.out.println("[2] Credit Inquiry");
        System.out.println("[3] Purchases");
        System.out.println("[4] Payment");
        System.out.println("[5] Close Credit Account");
        System.out.println("[6] Exit");
    }
    public static double checkIfNotZero(){
        boolean loop = true;
        double number;
        do{
            number = inputDouble();
            if(number < 1){
                System.out.println("Please enter not less than Php 1: ");
            }else{
                loop = false;
            }
        }while(loop == true);
        return number;
    }
}
