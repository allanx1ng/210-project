package persistence;

import model.Coin;
import model.Portfolio;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

//Some of the code in this class was taken from the provided JSON program
class JsonWriterTest extends JsonTest {
    //NOTE TO CPSC 210 STUDENTS: the strategy in designing tests for the JsonWriter is to
    //write data to a file and then use the reader to read it back in and check that we
    //read in a copy of what was written out.

    @Test
    void testWriterInvalidFile() {
        try {
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyPortfolio() {
        try {
            Portfolio p = new Portfolio();
            JsonReader reader = new JsonReader("./data/testWriterEmptyWorkroom.json");

            assertFalse(JsonReader.findCoin("Bitcoin"));
            assertFalse(JsonReader.findCoin("Ethereum"));
            assertFalse(JsonReader.findCoin("USD"));

            Coin testCoin = new Coin("TestCoin",10,10);
            p.addCoinToList(testCoin);


            JsonWriter writer = new JsonWriter("./data/testWriterEmptyWorkroom.json");
            writer.open();
            writer.write(p);
            writer.close();

            assertFalse(JsonReader.findCoin("Bitcoin"));
            assertFalse(JsonReader.findCoin("Ethereum"));
            assertFalse(JsonReader.findCoin("USD"));

            assertTrue(JsonReader.findCoin("TestCoin"));
            assertEquals(10,reader.getAmount("TestCoin"));


        } catch (IOException e) {
            fail("Exception should not have been thrown");
        } finally {
            resetFile("e");
        }
    }

    @Test
    void testWriterGeneralPortfolio() {
        try {
            Portfolio p = new Portfolio();
            JsonReader reader = new JsonReader("./data/testWriterGeneralWorkroom.json");

            assertTrue(JsonReader.findCoin("Bitcoin"));
            assertTrue(JsonReader.findCoin("Ethereum"));
            assertTrue(JsonReader.findCoin("USD"));

            Coin testCoin = new Coin("TestCoin",10,10);
            p.addCoinToList(testCoin);


            JsonWriter writer = new JsonWriter("./data/testWriterGeneralWorkroom.json");
            writer.open();
            writer.write(p);
            writer.close();

            assertFalse(JsonReader.findCoin("Bitcoin"));
            assertFalse(JsonReader.findCoin("Ethereum"));
            assertFalse(JsonReader.findCoin("USD"));

            assertTrue(JsonReader.findCoin("TestCoin"));
            assertEquals(10,reader.getAmount("TestCoin"));


        } catch (IOException e) {
            fail("Exception should not have been thrown");
        } finally {
            resetFile("g");
        }
    }

    private void resetFile(String f) {
        if (f.equals("e")) {
            Portfolio p = new Portfolio();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyWorkroom.json");
            try {
                writer.open();
                writer.write(p);
                writer.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }


        } else if (f.equals("g")) {
            Portfolio p = new Portfolio();
            Coin btc = new Coin("Bitcoin", 60000, 10);
            Coin eth = new Coin("Ethereum", 4000, 1);
            Coin usd = new Coin("USD", 1, 10000);
            p.addCoinToList(btc);
            p.addCoinToList(eth);
            p.addCoinToList(usd);

            JsonWriter writer = new JsonWriter("./data/testWriterGeneralWorkroom.json");
            try {
                writer.open();
                writer.write(p);
                writer.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

    }


}