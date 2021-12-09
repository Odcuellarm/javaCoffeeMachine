package com.company;
//package machine;
import java.util.Scanner;

public class Main {
    static boolean usingMachine = true;
    static int water = 400;
    static int coffeeBeans = 120;
    static int milk = 540;
    static int cups = 9;
    static int money = 550;

    public static void main(String[] args) {
        CoffeeMachine coffee = new CoffeeMachine();
        do {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Write action (buy, fill, take, remaining, exit): ");
            coffee.setMachineState(scanner.nextLine());

        } while (usingMachine);
    }
}

class CoffeeMachine {
    //buy, fill, take, remaining, exit;

    static void setMachineState (String userInput) {
        MachineState coffeeMachine =  MachineState.RECEIVING_INPUT;
        //System.out.println("setting machine state" +  userInput);
        switch (userInput) {
            case "buy":
                coffeeMachine = MachineState.BUYING_COFFEE;
                System.out.println(coffeeMachine);
                break;

            case "fill":
                coffeeMachine = MachineState.FILLING_MACHINE;
                System.out.println(coffeeMachine);
                break;

            case "take":
                coffeeMachine = MachineState.TAKING_CASH;
                System.out.println(coffeeMachine);
                break;

            case "remaining":
                coffeeMachine = MachineState.SHOW_REMAINING;
                System.out.println(coffeeMachine);
                break;

            case "exit":
                coffeeMachine = MachineState.EXIT;
                System.out.println(coffeeMachine);
                break;
        }

        switch (coffeeMachine) {
            case BUYING_COFFEE:
                buyCoffee();
                break;

            case FILLING_MACHINE:
                fillMachine();
                break;

            case TAKING_CASH:
                takeCash();
                break;

            case SHOW_REMAINING:
                showRemaining();
                break;

            case EXIT:
                exit();
                break;
        }
    }

    static void buyCoffee() {

        Scanner userSelection = new Scanner(System.in);
        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu: ");
        String typeOfCoffee = userSelection.next();

        int _water;
        int _coffeeBeans;
        int _milk;
        int cost;

        switch (typeOfCoffee) {
            case "1":
                //check for resources
                _water= 250;
                _coffeeBeans = 16;
                _milk = 0;
                cost = 4;

                if(checkIngredients(_water, _milk, _coffeeBeans)){
                    //update ingredients
                    subtractIngredients(_water, _milk, _coffeeBeans);
                    //update money
                    Main.money += cost;
                }
                break;

            case "2":
                _water = 350;
                _milk = 75;
                _coffeeBeans = 20;
                cost = 7;

                if(checkIngredients(_water, _milk, _coffeeBeans)){
                    //update ingredients
                    subtractIngredients(_water, _milk, _coffeeBeans);
                    //update money
                    Main.money += cost;
                }
                break;

            case "3":
                _water = 200;
                _milk = 100;
                _coffeeBeans = 12;
                cost = 6;

                if(checkIngredients(_water, _milk, _coffeeBeans)){
                    //update ingredients
                    subtractIngredients(_water, _milk, _coffeeBeans);
                    //update money
                    Main.money += cost;
                }
                break;
            case "back":
                break;
        }
    }

    static boolean checkIngredients(int _water, int _milk, int _coffeeBeans){

        if( Main.water <  _water){
            System.out.println("Sorry, not enough water!");
            return false;
        }else if ( Main.milk < _milk){
            System.out.println("Sorry, not enough milk!");
            return false;
        }else if( Main.coffeeBeans < _coffeeBeans){
            System.out.println("Sorry, not enough coffee beans!");
            return false;
        }else if ( Main.cups < 1){
            System.out.println("Sorry, not enough cups!");
            return false;
        }else {
            System.out.println("I have enough resources, making you a coffee!");
            return true;
        }
    }

    static void subtractIngredients(int _water, int _milk, int _coffeeBeans){
        Main.water -= _water;
        Main.milk -= _milk;
        Main.coffeeBeans -= _coffeeBeans;
        Main.cups--;
    }

    static void  fillMachine() {
        Scanner userResources = new Scanner(System.in);

        System.out.println("Write how many ml of water you want to add: ");
        Main.water += userResources.nextInt();
        System.out.println("Write how many ml of milk you want to add: ");
        Main.milk += userResources.nextInt();
        System.out.println("Write how many grams of coffee beans you want to add: ");
        Main.coffeeBeans += userResources.nextInt();
        System.out.println("Write how many disposable cups of coffee you want to add: ");
        Main.cups += userResources.nextInt();
    }

    static void  takeCash() {
        System.out.println("I gave you $" + Main.money);
        Main.money = 0;
    }

    static void showRemaining() {
        System.out.println("\nThe coffee machine has:");
        System.out.println(Main.water + " ml of water");
        System.out.println(Main.milk + " ml of milk");
        System.out.println(Main.coffeeBeans + " g of coffee beans");
        System.out.println(Main.cups + " disposable cups");
        System.out.println("$" + Main.money + " of money");
    }

    static void exit() {
        Main.usingMachine = false;
    }
}

enum MachineState {
    RECEIVING_INPUT,
    BUYING_COFFEE,
    FILLING_MACHINE,
    TAKING_CASH,
    SHOW_REMAINING,
    EXIT
}

