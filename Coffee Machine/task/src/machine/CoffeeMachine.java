package machine;

import java.util.Locale;
import java.util.Scanner;

public class CoffeeMachine {


    public static void main(String[] args) {

        CoffeeMachineInst coffeeMachine = new CoffeeMachineInst(400, 540, 120, 9, 550);

        coffeeMachine.printMenu();
    }

}

class CoffeeMachineInst {
    private final Scanner scanner = new Scanner(System.in);

    private int water;
    private int milk;
    private int coffee;
    private int cups;
    private int income;

    public CoffeeMachineInst(int water, int milk, int coffee, int cups, int income) {
        this.water = water;
        this.milk = milk;
        this.coffee = coffee;
        this.cups = cups;
        this.income = income;
    }

    public void printState() {
        System.out.printf("The coffee machine has:\n" +
                "%d of water\n" +
                "%d of milk\n" +
                "%d of coffee beans\n" +
                "%d of disposable cups\n" +
                "%d of money\n", water, milk, coffee, cups, income);
    }

    public void printMenu() {
        String option = "";
        while (!"exit".equals(option = scanner.nextLine().toLowerCase(Locale.US))) {
            System.out.println("Write action (buy, fill, take, remaining, exit):");
            switch (option) {
                case "buy":
                    buy();
                    break;
                case "fill":
                    fill();
                    break;
                case "take":
                    take();
                    break;
                case "remaining":
                    printState();
                    break;
                default:
                    System.out.println("Unsupported operation!");
            }
        }
    }

    public void buy() {
        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
        int needCup = 1;
        String option = scanner.nextLine().toLowerCase(Locale.US);
        switch(option) {
            case "1":
                makeCoffee(250, 0, 16, needCup, 4);
                break;
            case "2":
                makeCoffee(350, 75, 20, needCup, 7);
                break;
            case "3":
                makeCoffee(200, 100, 12, needCup, 6);
                break;
            case "back":
                return;
        }
    }

    public boolean checkIngredients(int needWater, int needMilk, int needCoffee, int needCup) {
        if (this.water < needWater) {
            System.out.println("Sorry, not enough water!");
            return false;
        } else if (this.milk < needMilk) {
            System.out.println("Sorry, not enough milk!");
            return false;
        } else if (this.coffee < needCoffee) {
            System.out.println("Sorry, not enough coffee!");
            return false;
        } else if (this.cups < needCup) {
            System.out.println("Sorry, not enough disposable cups!");
            return false;
        }

        System.out.println("I have enough resources, making you a coffee!");
        return true;
    }

    public void makeCoffee(int needWater, int needMilk, int needCoffee, int needCup, int income) {
        if (checkIngredients(needWater, needMilk, needCoffee, needCup)) {
            this.water -= needWater;
            this.milk -= needMilk;
            this.coffee -= needCoffee;
            this.cups -= needCup;
            this.income += income;
        }
    }

    public void fill() {
        System.out.println("Write how many ml of water do you want to add:");
        this.water += scanner.nextInt();
        System.out.println("Write how many ml of milk do you want to add:");
        this.milk += scanner.nextInt();
        System.out.println("Write how many grams of coffee beans do you want to add:");
        this.coffee += scanner.nextInt();
        System.out.println("Write how many disposable cups of coffee do you want to add: ");
        this.cups += scanner.nextInt();
    }

    public void take() {
        System.out.printf("I gave you $%d", this.income);
        this.income = 0;
    }

}