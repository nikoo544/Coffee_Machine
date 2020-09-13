package machine;


import java.util.Scanner;

public class CoffeeMachine {

    int milk, water, coffeeBeans, money, disposableCups;

    CoffeeMachine(int water, int milk, int coffeeBeans, int money, int disposableCups) {

        this.water = water;
        this.milk = milk;
        this.coffeeBeans = coffeeBeans;
        this.money = money;
        this.disposableCups = disposableCups;

    }

    public void loadMachine(int water, int milk, int coffeeBeans, int disposableCups) {

        this.water = this.water + water;
        this.milk = this.milk + milk;
        this.coffeeBeans = this.coffeeBeans + coffeeBeans;
        this.disposableCups = this.disposableCups + disposableCups;

    }

    public int calculateCups(int water, int milk, int coffeeBeans, int disposableCups, CoffeeCup variety) {

        int cups = 0;

        while (water >= variety.water && milk >= variety.milk && coffeeBeans >= variety.coffeeBeans && disposableCups >= 1) {

            water = water - 200;
            milk = milk - 50;
            coffeeBeans = coffeeBeans - 15;
            disposableCups = disposableCups - 1;

            cups++;
        }
        return cups;
    }

    public String calculateIngredients(CoffeeCup variety) {

        if (variety.milk == 0 ) {

            if ((this.water / variety.water) < 1) {
                return "water";
            }
            if ((this.coffeeBeans / variety.coffeeBeans) < 1) {
                return "coffee beans";
            }
            if (this.disposableCups < 1) {
                return "cups";
            }

        }

        if (variety.milk > 0) {

            if ((this.water / variety.water) < 1) {
                return "water";
            }
            if ((this.milk / variety.milk) < 1) {
                return "milk";
            }
            if ((this.coffeeBeans / variety.coffeeBeans) < 1) {
                return "coffee beans";
            }
            if (this.disposableCups < 1) {
                return "cups";
            }
        }

        return "";

    }

    public void makeCoffee(CoffeeCup variety) {

        this.water = this.water - variety.water;
        this.milk = this.milk - variety.milk;
        this.coffeeBeans = this.coffeeBeans - variety.coffeeBeans;
        this.disposableCups = this.disposableCups - 1;
        this.money = this.money + variety.money;

    }

    public void status() {

        System.out.println("The coffee machine has:");
        System.out.println(this.water + " of water");
        System.out.println(this.milk + " of milk");
        System.out.println(this.coffeeBeans + " of coffee beans");
        System.out.println(this.disposableCups + " of disposable cups");
        System.out.println("$"+this.money + " of money");
    }

    public void canMake(int query, int capacity, String result) {

        if (query <= capacity) {
            System.out.println("I have enough resources, making you a coffee!");
        } else if (query > capacity) {
            System.out.println("Sorry, not enough " + result + "!");
        }
    }

    public void sell(String CoffeeType) {

        String result;
        int capacity;

        switch (CoffeeType) {
            case "1":
                //espresso
                CoffeeCup espresso = new CoffeeCup(250, 0, 16, 4);
                result = calculateIngredients(espresso);
                capacity = calculateCups(this.water, this.milk,this.coffeeBeans,this.disposableCups,espresso);
                canMake(1,capacity, result);
                if (capacity > 0 ) makeCoffee(espresso);
                break;

            case "2":
                //latte
                CoffeeCup latte = new CoffeeCup(350, 75, 20, 7);
                result = calculateIngredients(latte);
                capacity = calculateCups(this.water, this.milk,this.coffeeBeans,this.disposableCups,latte);
                canMake(1,capacity,result);
                if (capacity > 0 ) makeCoffee(latte);
                break;

            case "3":
                //cappuccino
                CoffeeCup cappuccino = new CoffeeCup(200, 100, 12, 6);
                result = calculateIngredients(cappuccino);
                capacity = calculateCups(this.water, this.milk,this.coffeeBeans,this.disposableCups,cappuccino);
                canMake(1,capacity,result);
                if (capacity > 0 ) makeCoffee(cappuccino);
                break;
        }
    }

    public void take() {
        System.out.println("I gave you $" + this.money);
        this.money = 0;
    }

    public static void main(String[] args) {
        int milk, water, coffeeBeans, cups;
        String cup;
        boolean work = true;
        String action;

        Scanner scanner = new Scanner(System.in);

        //Start
        CoffeeMachine coffeeLand = new CoffeeMachine(400, 540, 120, 550, 9);
        // User input
        while(work){
        System.out.println("Write action (buy, fill, take, remaining, exit):");
        action = scanner.next();
        switch (action) {

            case "buy":
                System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
                cup = scanner.next();
                scanner.reset();
                coffeeLand.sell(cup);
                break;

            case "fill":
                System.out.println("Write how many ml of water do you want to add:");
                water = scanner.nextInt();
                System.out.println("Write how many ml of milk do you want to add:");
                milk = scanner.nextInt();
                System.out.println("Write how many grams of coffee beans do you want to add:");
                coffeeBeans = scanner.nextInt();
                System.out.println("Write how many disposable cups of coffee do you want to add:");
                cups = scanner.nextInt();
                coffeeLand.loadMachine(water, milk, coffeeBeans, cups);
                break;
            case "take":
                coffeeLand.take();
                break;
            case "remaining":
                coffeeLand.status();
                break;
            case "exit":
                work = false;
                break;
        }
        }
        System.exit(0);
    }
}
