package persistence;

import model.Land;
import model.Player;
import model.Corp;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

//Represents a reader that reads player from JSON data stored in file
public class JsonReader {

    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads player from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Player read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parsePlayer(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses player from JSON object and returns it
    private Player parsePlayer(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        int money = jsonObject.getInt("money");
        Player pl = new Player(name, money);
        addLands(pl, jsonObject);
        return pl;
    }

    // MODIFIES: pl
    // EFFECTS: parses lands from JSON object and adds them to player
    private void addLands(Player pl, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("lands");
        for (Object json : jsonArray) {
            JSONObject nextLand = (JSONObject) json;
            addLand(pl, nextLand);
        }
    }

    // MODIFIES: pl
    // EFFECTS: parses land from JSON object and adds it to player
    private void addLand(Player pl, JSONObject jsonObject) {
        int id = jsonObject.getInt("id");
        Land land = new Land(id);
        JSONObject jsonPlant = jsonObject.getJSONObject("plant");
        addPlant(land, jsonPlant);
        int size = jsonObject.getInt("size");
        land.setSize(size);
        pl.addLand(land);
    }

    //Modifies: land
    //Effects: parses plant from JSON object and adds it to land
    private void addPlant(Land l, JSONObject jsonObject) {
        String corpName = jsonObject.getString("name");
        int corpPrice = jsonObject.getInt("price");
        Corp plant = new Corp(corpName, corpPrice);
        l.setPlant(plant);
    }

}

