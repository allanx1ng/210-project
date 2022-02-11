package model;

// This class is has everything to do with Coins
public class Coin {

    private int coinPrice;
    private String coinName;
    private int amountCoinHeld;

    public Coin(String name, int price, int amountHeld) {
        this.coinName = name;
        this.coinPrice = price;
        this.amountCoinHeld = amountHeld;
    }

    public int getPrice() {
        return coinPrice;
    }

    public String getName() {
        return coinName;
    }

    public int getAmountHeld() {
        return amountCoinHeld;
    }

    public static void addCoin(Coin c, int amount) {
        c.amountCoinHeld += amount;
    }

    public Coin getCoin(Coin c) {
        return c;
    }



}
