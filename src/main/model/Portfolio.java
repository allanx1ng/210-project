package model;

import java.util.ArrayList;
import java.util.List;

// This class deals with the workings of your Portfolio and the associated methods to
// access its contents
public class Portfolio {


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
    }

    // REQUIRES: valid name of a coin
    // EFFECTS: returns the amount of coins held of this name
    public int getAmountOfCoinHeld(String name) {
        for (Coin coin : coinList) {
            if (coin.getName() == name) {
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
            if (coin.getName() == name) {
                Coin.addCoin(coin, amount);
            }
        }
    }

    // REQUIRES: valid name of a coin
    // EFFECTS: returns true if the entered coin is part of Portfolio, false otherwise
    public boolean containsCoin(String name) {
        for (Coin coin : coinList) {
            if (coin.getName() == name) {
                return true;
            }
        }
        return false;
    }

    public List<Coin> getCoinList() {
        return coinList;
    }
}
