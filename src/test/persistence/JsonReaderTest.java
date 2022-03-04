package persistence;

import model.Coin;
import model.Portfolio;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

//Some of the code in this class was taken from the provided JSON program
class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            JsonReader.findCoin("Bitcoin");
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyPortfolio() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyWorkRoom.json");
        try {
            assertFalse(JsonReader.findCoin("Bitcoin"));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderEmptyFile() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyWorkRoom.json");
        try {
            assertFalse(JsonReader.findCoin("Bitcoin"));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralWorkRoom.json");
        try {
            assertTrue(JsonReader.findCoin("Bitcoin"));
            assertEquals(10, reader.getAmount("Bitcoin"));
            assertTrue(JsonReader.findCoin("Ethereum"));
            assertEquals(1, reader.getAmount("Ethereum"));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    // this is never supposed to happen, but I'm making it for test coverage purposes
    void testReaderGetAmountCoinDNE() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralWorkRoom.json");
        try {
            assertTrue(JsonReader.findCoin("Bitcoin"));
            assertEquals(10, reader.getAmount("Bitcoin"));
            assertTrue(JsonReader.findCoin("Ethereum"));
            assertEquals(0, reader.getAmount("random coin"));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}