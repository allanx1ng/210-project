package ui;

import model.Coin;
//import model.Interactions;
import model.Portfolio;

import java.util.Scanner;

// The main class where all the user interactions such as buying and selling happen
// Right now, there is a lot of code because
public class Main {

    private static Portfolio portfolio;
    private static Coin btc;
    private static Coin eth;
    private static Coin usd;


    public static void main(String[] args) {
        portfolio = new Portfolio();
        setCoins();
        new Main();
    }

    // EFFECTS: initiates the different coins that the user can buy
    private static void setCoins() {
        btc = new Coin("Bitcoin", 60000, 0);
        eth = new Coin("Ethereum", 4000, 0);
        usd = new Coin("USD", 1, 0);
    }


    // EFFECTS: the main method that runs all the UI
    public Main() {

        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.println("Welcome to Cpsc 210's crypto exchange! "
                    + "What would you like to do? (Buy, Sell, DepositUSD, Withdraw, CheckBalance, Close)");
            String nextAction = input.next();
            if (nextAction.equals("Buy")) {
                buy();
            } else if (nextAction.equals("Sell")) {
                sell();
            } else if (nextAction.equals("DepositUSD")) {
                addMoney();
            } else if (nextAction.equals("Withdraw")) {
                withdraw();
            } else if (nextAction.equals("Close")) {
                return;
            } else if (nextAction.equals("CheckBalance")) {
                checkBalance();
            } else {
                System.out.println("Please enter a valid action");
            }
        }
    }


    // EFFECTS: prints the amount of each coin in user's portfolio
    private void checkBalance() {
        if (portfolio.getCoinList().size() != 0) {
            for (Coin coin : portfolio.getCoinList()) {
                System.out.println(coin.getName() + " balance is: " + portfolio.getAmountOfCoinHeld(coin.getName()));
            }
        } else {
            System.out.println("Your portfolio is empty");
        }
    }

    // EFFECTS: takes in user input to see what coin they want to buy
    public static void buy() {

        Scanner input = new Scanner(System.in);
        String nextAction;

        System.out.println("What would you like to buy? (BTC, ETH)");
        nextAction = input.next();
        if (nextAction.equals("BTC")) {
            buyBTC();
        } else if (nextAction.equals("ETH")) {
            buyETH();
        } else {
            System.out.println("Please enter a valid input");
        }
    }


    // EFFECTS: calls the executeBuy() method with the amount of ethereum to be purchased if a valid input is given
    public static void buyETH() {
        Scanner input = new Scanner(System.in);
        int nextAction;
        System.out.println("How much ETH would you like to buy?");

        try {

            nextAction = Integer.parseInt(input.next());
            executeBuy("Ethereum", eth, nextAction);

        } catch (NumberFormatException e) {
            System.out.println("Please enter a positive integer");
        }
    }


    // EFFECTS: calls the executeBuy() method with the amount of bitcoin to be purchased if a valid input is given
    public static void buyBTC() {

        Scanner input = new Scanner(System.in);
        int nextAction;
        System.out.println("How much BTC would you like to buy?");

        try {
            nextAction = Integer.parseInt(input.next());

            executeBuy("Bitcoin", btc, nextAction);

        } catch (NumberFormatException e) {
            System.out.println("Please enter a positive integer");
        }
    }


    // MODIFIES: portfolio, usd
    // EFFECTS: user inputs how much USD they want to deposit into their wallet
    // - checks to see if USD is already a part of user's portfolio, if not, then it is added
    public static void addMoney() {
        Scanner input = new Scanner(System.in);
        int nextAction;
        System.out.println("How much USD would you like to add?");

        try {
            nextAction = Integer.parseInt(input.next());
            if (nextAction >= 0) {
                if (portfolio.containsCoin("USD") == false) {
                    portfolio.addCoinToList(usd);
                }
                portfolio.addCoins("USD", nextAction);
                System.out.println("Your new USD balance is: " + portfolio.getAmountOfCoinHeld("USD"));
            } else {
                System.out.println("Please enter a valid input");
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter a positive integer");
        }
    }


    // EFFECTS: User selects which coin they want to withdraw
    public static void withdraw() {
        Scanner input = new Scanner(System.in);
        String nextAction;

        System.out.println("What would you like to withdraw? (BTC, ETH, USD)");
        nextAction = input.next();
        if (nextAction.equals("BTC")) {
            executeWithdraw("Bitcoin", btc);
        } else if (nextAction.equals("ETH")) {
            executeWithdraw("Ethereum", eth);
        } else if (nextAction.equals("USD")) {
            executeWithdraw("USD", usd);
        } else {
            System.out.println("Please enter a valid input");
        }
    }

    // REQUIRES: the valid name of a coin and the actual coin
    // MODIFIES: portfolio, coin
    // EFFECTS: Withdraws the amount of the selected coin; if the new balance in user's portfolio of select
    // coin is 0 then the coin is removed from portfolio
    private static void executeWithdraw(String name, Coin c) {
        Scanner input = new Scanner(System.in);
        int nextAction;
        System.out.println("How much " + name + " would you like to withdraw?");

        try {
            nextAction = Integer.parseInt(input.next());
            if (nextAction >= 0) {
                if (portfolio.getAmountOfCoinHeld(name) >= nextAction) {
                    portfolio.addCoins(name, 0 - nextAction);
                    System.out.println("Withdrew " + nextAction + " " + name);
                    System.out.println("Your new " + name + " balance is: " + portfolio.getAmountOfCoinHeld(name));
                }

            } else {
                System.out.println("Please enter a valid input");
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter a positive integer");
        }
    }


    // REQUIRES: valid name of a coin, the coin itself, integer amount >=0
    // MODIFIES: portfolio, coin
    // EFFECTS: if the amount of USD is sufficient to buy amount*selected coin, amount* selected coin is added into
    // the user's portfolio and the equivalent value of USD is subtracted. If the user doesnt have enough USD,
    // they are told that there is insufficient USD for the transaction.
    public static void executeBuy(String name, Coin c, int amount) {
        if (amount >= 0) {

            if (portfolio.getAmountOfCoinHeld("USD") >= amount * c.getPrice()) {
                if (portfolio.containsCoin(name) == false) {
                    portfolio.addCoinToList(c);
                }
                portfolio.addCoins(name, amount);
                portfolio.addCoins("USD", 0 - amount * c.getPrice());
                System.out.println("Bought " + amount + " " + name);
                System.out.println("Your new " + name + " balance is: " + portfolio.getAmountOfCoinHeld(name));
                System.out.println("Your new USD balance is: " + portfolio.getAmountOfCoinHeld("USD"));
            } else {
                System.out.println("Not enough money");
            }
        } else {
            System.out.println("Please enter a valid input");
        }
    }


    // EFFECTS: prompts the user to choose which coin to sell
    public void sell() {
        Scanner input = new Scanner(System.in);
        String nextAction;

        System.out.println("What would you like to sell? (BTC, ETH)");
        nextAction = input.next();
        if (nextAction.equals("BTC")) {
            sellBTC();
        } else if (nextAction.equals("ETH")) {
            sellETH();
        } else {
            System.out.println("Please enter a valid input");
        }
    }

    // EFFECTS: prompts the user to enter how much bitcoin to sell
    public void sellBTC() {
        Scanner input = new Scanner(System.in);
        int nextAction;
        System.out.println("How much BTC would you like to sell?");

        try {
            nextAction = Integer.parseInt(input.next());

            executeSell("Bitcoin", btc, nextAction);

        } catch (NumberFormatException e) {
            System.out.println("Please enter a positive integer");
        }
    }

    // EFFECTS: prompts the user to enter how much ethereum to sell
    public void sellETH() {
        Scanner input = new Scanner(System.in);
        int nextAction;
        System.out.println("How much ETH would you like to sell?");

        try {

            nextAction = Integer.parseInt(input.next());
            executeSell("Ethereum", eth, nextAction);

        } catch (NumberFormatException e) {
            System.out.println("Please enter a positive integer");
        }
    }

    // REQUIRES: valid coin name, the coin itself and integer amount >=0
    // MODIFIES: portfolio, coin
    // EFFECTS: subtracts amount*coin from portfolio and adds the equivalent value of USD to portfolio
    // - if the new balance of selected coin is 0, it is removed from portfolio
    private void executeSell(String name, Coin c, int amount) {
        if (amount >= 0) {

            if (portfolio.getAmountOfCoinHeld(name) >= amount) {
                portfolio.addCoins(name, 0 - amount);
                portfolio.addCoins("USD", amount * c.getPrice());
                System.out.println("Sold " + amount + " " + name);
                System.out.println("Your new " + name + " balance is: " + portfolio.getAmountOfCoinHeld(name));
                System.out.println("Your new USD balance is: " + portfolio.getAmountOfCoinHeld("USD"));
                if (portfolio.getAmountOfCoinHeld(name) == 0) {
                    portfolio.getCoinList().remove(c);
                }
            } else {
                System.out.println("Not enough money");
            }
        } else {
            System.out.println("Please enter a valid input");
        }
    }
}




/*
    public static void buy() {

        Scanner input = new Scanner(System.in);
        String nextAction;

        System.out.println("What would you like to buy? (BTC, ETH)");
        nextAction = input.next();
        if (nextAction.equals("BTC")) {
            buyBTC();
        } else if (nextAction.equals("ETH")) {
            buyETH();
        } else {
            System.out.println("Please enter a valid input");
        }
    }

    public static void buyBTC() {
        Scanner input = new Scanner(System.in);
        int nextAction;
        System.out.println("How much BTC would you like to buy?");
        nextAction = Integer.parseInt(input.next());

        if (portfolio.getUsdValue() >= nextAction * Coin.btc.getPrice()) {
            portfolio.addBTC(nextAction);
            portfolio.addCash(0 - nextAction * Coin.btc.getPrice());
            System.out.println("Bought " + nextAction +  "BTC");
            System.out.println("Your new BTC balance is: " + portfolio.getBtcValue());
            System.out.println("Your new USD balance is: " + portfolio.getUsdValue());
        } else {
            System.out.println("Not enough money");
        }
    }

    public static void buyETH() {
        Scanner input = new Scanner(System.in);
        int nextAction;
        System.out.println("How much ETH would you like to buy?");
        nextAction = Integer.parseInt(input.next());

        if (portfolio.getUsdValue() >= nextAction * Coin.eth.getPrice()) {
            portfolio.addETH(nextAction);
            portfolio.addCash(0 - nextAction * Coin.eth.getPrice());
            System.out.println("Bought " + nextAction +  "ETH");
            System.out.println("Your new ETH balance is: " + portfolio.getEthValue());
            System.out.println("Your new USD balance is: " + portfolio.getUsdValue());
        } else {
            System.out.println("Not enough money");
        }
    }

    public static void addMoney() {
        Scanner input = new Scanner(System.in);
        int nextAction;
        System.out.println("How much money would you like to add?");
        nextAction = Integer.parseInt(input.next());
        portfolio.addCash(nextAction);
        System.out.println("Your new USD balance is: " + portfolio.getUsdValue());
    }

     public Portfolio getPortfolio() {
        return portfolio;
    }
 */
