package model;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class CoinTest {

    Coin coin1 = new Coin("TestCoin", 100, 0);

    // test for the method addCoin
    @Test
    public void addCoinTest() {
        coin1.addCoin(10);
        assertEquals(10, coin1.getAmountHeld());
    }

    @Test
    public void getPriceTest() {
        assertEquals(100, coin1.getPrice());
    }

    @Test
    public void getNameTest() {
        assertEquals("TestCoin", coin1.getName());
    }
}
