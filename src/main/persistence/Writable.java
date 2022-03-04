package persistence;

import org.json.JSONObject;

//Some of the code in this class was taken from the provided JSON program
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
