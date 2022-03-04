package model;


import org.json.JSONObject;
import persistence.Writable;

// This class is has everything to do with Coins
public class Coin implements Writable {

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

    public void addCoin(int amount) {
        amountCoinHeld += amount;
    }


    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", coinName);
        json.put("amount held", amountCoinHeld);
        return json;
    }




}
