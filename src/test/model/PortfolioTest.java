package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PortfolioTest {



    Coin coin1;
    Coin coin2;
    Portfolio portfolio1;

    @BeforeEach
    public void setFsc() {
        portfolio1 = new Portfolio();
        coin1 = new Coin("coin1",100,100);
        coin2 = new Coin("coin2",1000,1000);
    }





    // test method for addCoinToList
    @Test
    public void addCoinToListTest() {
        portfolio1.addCoinToList(coin1);
        System.out.println(portfolio1.getCoinList());
        assertEquals(coin1, portfolio1.getCoinList().get(0));
        portfolio1.addCoinToList(coin2);
        assertEquals(coin2, portfolio1.getCoinList().get(1));
        portfolio1.getCoinList().clear();
    }

    // test method for getAmountOfCoinHeld
    @Test
    public void getAmountOfCoinHeldTest() {

        portfolio1.addCoinToList(coin1);
        portfolio1.addCoinToList(coin2);
        System.out.println(coin1.getName());
        System.out.println(coin2.getName());
        assertEquals(100, portfolio1.getAmountOfCoinHeld("coin1"));
        assertEquals(1000, portfolio1.getAmountOfCoinHeld("coin2"));

        portfolio1.getCoinList().clear();
    }

    // test method for addCoins
    @Test
    public void addCoinsTest() {
        portfolio1.addCoinToList(coin1);
        portfolio1.addCoinToList(coin2);
        portfolio1.addCoins("coin1",900);
        portfolio1.addCoins("coin2",900);

        assertEquals(1000, coin1.getAmountHeld());
        assertEquals(1900, coin2.getAmountHeld());
        portfolio1.addCoins("coin1",-900);
        assertEquals(100, coin1.getAmountHeld());

        portfolio1.getCoinList().clear();

    }


    // test method for containsCoin
    @Test
    public void containsCoinTest() {
        assertFalse(portfolio1.containsCoin("coin1"));
        assertFalse(portfolio1.containsCoin("coin2"));
        portfolio1.addCoinToList(coin1);
        assertTrue(portfolio1.containsCoin("coin1"));
        assertFalse(portfolio1.containsCoin("coin2"));
        portfolio1.addCoinToList(coin2);
        assertTrue(portfolio1.containsCoin("coin1"));
        assertTrue(portfolio1.containsCoin("coin2"));

    }

    // REQUIRES:
    // MODIFIES:
    // EFFECTS:

}