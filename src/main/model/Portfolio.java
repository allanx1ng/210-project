package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

// This class deals with the workings of your Portfolio and the associated methods to
// access its contents
public class Portfolio implements Writable {


    private List<Coin> coinList;

    // EFFECTS: Constructs a Portfolio that contains an arraylist
    public Portfolio() {
        coinList = new ArrayList<>();
    }

    // REQUIRES: Coin
    // MODIFIES: this
    // EFFECTS: adds Coin to coinList
    public void addCoinToList(Coin coin) {
        coinList.add(coin);
        EventLog.getInstance().logEvent(new Event("Added " + coin.getName() + " to portfolio"));
    }

    public void removeCoinFromList(Coin c) {
        coinList.remove(c);
        EventLog.getInstance().logEvent(new Event("Removed " + c.getName() + " from portfolio"));
    }

    // REQUIRES: valid name of a coin
    // EFFECTS: returns the amount of coins held of this name
    public int getAmountOfCoinHeld(String name) {
        for (Coin coin : coinList) {
            if (coin.getName().equals(name)) {
                return coin.getAmountHeld();
            }
        }
        return 0;
    }

    // REQUIRES: valid name of a coin, integer amount
    // MODIFIES: Coin
    // EFFECTS: adds amount to the number of coins held in portfolio
    public void addCoins(String name, int amount) {
        for (Coin coin : coinList) {
            if (coin.getName().equals(name)) {
                coin.addCoin(amount);
            }
        }
    }

    // REQUIRES: valid name of a coin
    // EFFECTS: returns true if the entered coin is part of Portfolio, false otherwise
    public boolean containsCoin(String name) {
        for (Coin coin : coinList) {
            if (coin.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public List<Coin> getCoinList() {
        return coinList;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        for (Coin c : coinList) {
            json.put("Coins:", coinsToJson());
        }
        return json;
    }

    // EFFECTS: returns things in this workroom as a JSON array
    private JSONArray coinsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Coin c : coinList) {
            jsonArray.put(c.toJson());
        }

        return jsonArray;
    }


}
