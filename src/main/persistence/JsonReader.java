package persistence;

import model.Coin;
import model.Portfolio;
import org.json.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import static java.lang.Integer.parseInt;
import static java.lang.String.valueOf;

// Represents a reader that reads workroom from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    /*
    public void read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
    }*/

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }



    // EFFECTS: returns true if the coin with given names exists in JSON file, false otherwise
    public boolean findCoin(String name) throws IOException {

        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        JSONArray jsonArray = jsonObject.getJSONArray("Coins:");

        for (Object json : jsonArray) {
            JSONObject nextCoin = (JSONObject) json;
            if (nextCoin.getString("name").equals(name)) {
                return true;
            }
        }
        return false;
    }

    // REQUIRES: coin with name exists in jsonObject
    // EFFECTS: returns the amount held of given coin in JSON
    public int getAmount(String name) throws IOException {

        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);

        JSONArray jsonArray = jsonObject.getJSONArray("Coins:");
        for (Object json : jsonArray) {
            JSONObject nextCoin = (JSONObject) json;
            if (nextCoin.getString("name").equals(name)) {
                return nextCoin.getInt("amount held");
            }

        }
        return 0;
    }



}
