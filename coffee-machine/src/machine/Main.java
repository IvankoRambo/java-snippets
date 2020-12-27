package machine;

import java.util.Scanner;

/**
 * Main class which represents interface to send actions to Coffee Machine
 */
public class Main {
    public static void main(String[] args) {
        int waterInStock = 400;
        int milkInStock = 540;
        int beansInStock = 120;
        int cupsInStock = 9;
        int moneyInStock = 550;

        useCoffeeMachine(waterInStock, milkInStock, beansInStock, cupsInStock, moneyInStock);
    }

    public static void useCoffeeMachine(int waterInStock, int milkInStock, int beansInStock, int cupsInStock, int moneyInStock) {
        Scanner scanner = new Scanner(System.in);
        CoffeeMachineStatus status = CoffeeMachineStatus.INITIAL;
        CoffeeMachine coffeeMachine = new CoffeeMachine(waterInStock, milkInStock, beansInStock, cupsInStock, moneyInStock, status);
        String infoMessage = "";

        do {
            switch (status) {
                case INITIAL:
                    infoMessage = "Write action (buy, fill, take, remaining, exit):";
                    break;
                case COFFEE_TYPE:
                    infoMessage = "\r\nWhat do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:";
                    break;
                case ADD_WATER:
                    infoMessage = "\r\nWrite how many ml of water do you want to add:";
                    break;
                case ADD_MILK:
                    infoMessage = "\r\nWrite how many ml of milk do you want to add:";
                    break;
                case ADD_BEANS:
                    infoMessage = "\r\nWrite how many grams of coffee beans do you want to add:";
                    break;
                case ADD_CUPS:
                    infoMessage = "\r\nWrite how many disposable cups of coffee do you want to add:";
                    break;
                case EXIT:
                default:
                    infoMessage = "";
                    break;
            }

            if (!infoMessage.isEmpty()) {
                System.out.println(infoMessage);
            }

            String action = scanner.next();
            status = coffeeMachine.doAction(action);
        } while (!CoffeeMachineStatus.EXIT.equals(status));
    }
}

enum CoffeeMachineStatus {
    INITIAL, EXIT, COFFEE_TYPE, ADD_WATER, ADD_MILK, ADD_BEANS, ADD_CUPS
}

/**
 * Coffee Machine equipment class, which does actions depending on user
 * input from external interface
 */
class CoffeeMachine {
    int waterInStock;
    int milkInStock;
    int beansInStock;
    int cupsInStock;
    int moneyInStock;
    CoffeeMachineStatus coffeeMachineStatus;

    int espressoWater = 250;
    int espressoMilk = 0;
    int espressoBeans = 16;
    int espressoPrice = 4;
    int latteWater = 350;
    int latteMilk = 75;
    int latteBeans = 20;
    int lattePrice = 7;
    int cappuccinoWater = 200;
    int cappuccinoMilk = 100;
    int cappuccinoBeans = 12;
    int cappuccinoPrice = 6;
    int coffeeCups = 1;

    /**
     * Constructor to initialize initial params
     * @param waterInStock - how much water coffee machine has
     * @param milkInStock - how much milk coffee machine has
     * @param beansInStock - how much beans coffee machine has
     * @param cupsInStock - how much cups coffee machine has
     * @param moneyInStock - how much money could be taken from coffee machine
     * @param coffeeMachineStatus - current coffee machine status, which could be changed depending on user actions
     */
    public CoffeeMachine(int waterInStock, int milkInStock, int beansInStock, int cupsInStock, int moneyInStock, CoffeeMachineStatus coffeeMachineStatus) {
        this.waterInStock = waterInStock;
        this.milkInStock = milkInStock;
        this.beansInStock = beansInStock;
        this.cupsInStock = cupsInStock;
        this.moneyInStock = moneyInStock;
        this.coffeeMachineStatus = coffeeMachineStatus;
    }

    private void printCoffeeMachineStock() {
        System.out.println("The coffee machine has:");
        System.out.println(this.waterInStock + " of water");
        System.out.println(this.milkInStock + " of milk");
        System.out.println(this.beansInStock + " of coffee beans");
        System.out.println(this.cupsInStock + " of disposable cups");
        System.out.println(this.moneyInStock + " of money");
    }

    private boolean isEnoughWater(int waterNeeded) {
        int waterRemaining = this.waterInStock - waterNeeded;
        return waterRemaining >= 0;
    }

    private boolean isEnoughMilk(int milkNeeded) {
        int milkRemaining = this.milkInStock - milkNeeded;
        return milkRemaining >= 0;
    }

    private boolean isEnoughBeans(int beansNeeded) {
        int beansRemaining = this.beansInStock - beansNeeded;
        return beansRemaining >= 0;
    }

    private boolean isEnoughCups() {
        int cupsNeeded = 1;
        int cupsRemaining = this.cupsInStock - cupsNeeded;
        return cupsRemaining >= 0;
    }

    private void handleInitialCase(String action) {
        switch (action) {
            case "buy":
                this.coffeeMachineStatus = CoffeeMachineStatus.COFFEE_TYPE;
                break;
            case "fill":
                this.coffeeMachineStatus = CoffeeMachineStatus.ADD_WATER;
                break;
            case "take":
                System.out.print("\r\n");
                System.out.println("I gave you " + this.moneyInStock);
                System.out.print("\r\n");

                this.moneyInStock -= this.moneyInStock;
                break;
            case "remaining":
                System.out.print("\r\n");
                this.printCoffeeMachineStock();
                System.out.print("\r\n");
                break;
            case "exit":
            default:
                this.coffeeMachineStatus = CoffeeMachineStatus.EXIT;
                break;
        }
    }

    private void handleCoffeeTypeCase(String action) {
        switch (action) {
            case "1":
                if (!this.isEnoughWater(this.espressoWater)) {
                    System.out.println("Sorry, not enough water!");
                } else if (!this.isEnoughMilk(this.espressoMilk)) {
                    System.out.println("Sorry, not enough milk!");
                } else if (!this.isEnoughBeans(this.espressoBeans)) {
                    System.out.println("Sorry, not enough beans!");
                } else if (!this.isEnoughCups()) {
                    System.out.println("Sorry, not enough cups!");
                } else {
                    this.waterInStock -= this.espressoWater;
                    this.milkInStock -= this.espressoMilk;
                    this.beansInStock -= this.espressoBeans;
                    this.moneyInStock += this.espressoPrice;
                    this.cupsInStock -= this.coffeeCups;

                    System.out.println("I have enough resources, making you a coffee!");
                }
                this.coffeeMachineStatus = CoffeeMachineStatus.INITIAL;
                break;
            case "2":
                if (!this.isEnoughWater(this.latteWater)) {
                    System.out.println("Sorry, not enough water!");
                } else if (!this.isEnoughMilk(this.latteMilk)) {
                    System.out.println("Sorry, not enough milk!");
                } else if (!this.isEnoughBeans(this.latteBeans)) {
                    System.out.println("Sorry, not enough beans!");
                } else if (!this.isEnoughCups()) {
                    System.out.println("Sorry, not enough cups!");
                } else {
                    this.waterInStock -= this.latteWater;
                    this.milkInStock -= this.latteMilk;
                    this.beansInStock -= this.latteBeans;
                    this.moneyInStock += this.lattePrice;
                    this.cupsInStock -= this.coffeeCups;

                    System.out.println("I have enough resources, making you a coffee!");
                }
                this.coffeeMachineStatus = CoffeeMachineStatus.INITIAL;
                break;
            case "3":
                if (!this.isEnoughWater(this.cappuccinoWater)) {
                    System.out.println("Sorry, not enough water!");
                } else if (!this.isEnoughMilk(this.cappuccinoMilk)) {
                    System.out.println("Sorry, not enough milk!");
                } else if (!this.isEnoughBeans(this.cappuccinoBeans)) {
                    System.out.println("Sorry, not enough beans!");
                } else if (!this.isEnoughCups()) {
                    System.out.println("Sorry, not enough cups!");
                } else {
                    this.waterInStock -= this.cappuccinoWater;
                    this.milkInStock -= this.cappuccinoMilk;
                    this.beansInStock -= this.cappuccinoBeans;
                    this.moneyInStock += this.cappuccinoPrice;
                    this.cupsInStock -= this.coffeeCups;

                    System.out.println("I have enough resources, making you a coffee!");
                }
                this.coffeeMachineStatus = CoffeeMachineStatus.INITIAL;
                break;
            case "back":
                this.coffeeMachineStatus = CoffeeMachineStatus.INITIAL;
                break;
            case "exit":
                this.coffeeMachineStatus = CoffeeMachineStatus.EXIT;
            default:
                break;
        }
    }

    private void handleAddWaterCase(String action) {
        int waterToAdd = Integer.parseInt(action);
        this.waterInStock += waterToAdd;
        this.coffeeMachineStatus = CoffeeMachineStatus.ADD_MILK;
    }

    private void handleAddMilkCase(String action) {
        int milkToAdd = Integer.parseInt(action);
        this.milkInStock += milkToAdd;
        this.coffeeMachineStatus = CoffeeMachineStatus.ADD_BEANS;
    }

    private void handleAddBeansCase(String action) {
        int beansToAdd = Integer.parseInt(action);
        this.beansInStock += beansToAdd;
        this.coffeeMachineStatus = CoffeeMachineStatus.ADD_CUPS;
    }

    private void handleAddCupsCase(String action) {
        int cupsToAdd = Integer.parseInt(action);
        this.cupsInStock += cupsToAdd;

        System.out.print("\r\n");

        this.coffeeMachineStatus = CoffeeMachineStatus.INITIAL;
    }

    /**
     * Main coffee machine method which receives action to do and delegates on
     * Coffee Machine components
     * @param action - represents an action to do defined by coffee machine user
     * @return status of coffee machine after user action
     */
    public CoffeeMachineStatus doAction(String action) {
        switch (this.coffeeMachineStatus) {
            case INITIAL:
                this.handleInitialCase(action);
                break;
            case COFFEE_TYPE:
                this.handleCoffeeTypeCase(action);
                break;
            case ADD_WATER:
                this.handleAddWaterCase(action);
                break;
            case ADD_MILK:
                this.handleAddMilkCase(action);
                break;
            case ADD_BEANS:
                this.handleAddBeansCase(action);
                break;
            case ADD_CUPS:
                this.handleAddCupsCase(action);
                break;
            case EXIT:
                this.coffeeMachineStatus = CoffeeMachineStatus.EXIT;
            default:
                break;
        }

        return this.coffeeMachineStatus;
    }
}
