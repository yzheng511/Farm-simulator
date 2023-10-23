package ui;

import model.Corp;
import model.Land;
import model.Player;
import persistence.JsonReader;
import persistence.JsonWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

//Farming game application
public class FarmingGame {

    private static final String JSON_STORE = "./data/player.json";
    private Player player;
    private Corp corn;
    private Corp cocoa;
    private Corp banana;
    private Scanner input;
    private int winCond;
    private int year;
    private JsonReader jsonReader;
    private JsonWriter jsonWriter;

    //Effects: run Farm application
    public FarmingGame() {
        runFarm();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runFarm() {
        boolean running = true;
        String command = null;

        init();

        while (running) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                running = false;
            } else if (player.getMoney() >= winCond) {
                System.out.println("\nWin!\n");
            } else {
                processCommand(command);
            }
        }
        System.out.println("\nEnd Game");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("c")) {
            doCornPlanting();
        } else if (command.equals("a")) {
            doCocoaPlanting();
        } else if (command.equals("b")) {
            doBananaPlanting();
        } else if (command.equals("p")) {
            doPurchasingLand();
        } else if (command.equals("n")) {
            doNextYear();
        } else if (command.equals("v")) {
            viewLand();
        } else if (command.equals("s")) {
            savePlayer();
        } else if (command.equals("l")) {
            loadPlayer();
        } else {
            System.out.println("Not a valid selection");
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes player and farm setting
    private void init() {
        player = new Player("Player1", 2000);
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        corn = new Corp("Corn", (int) (Math.random() * 3 + 4));
        cocoa = new Corp("Cocoa", (int) (Math.random() * 10 + 1));
        banana = new Corp("Banana", (int) (Math.random() * 7 + 2));
        winCond = 50000;
        year = 1;
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("Year: " + year);
        System.out.println("Money: $" + player.getMoney());
        System.out.println("Goal: $" + winCond);
        System.out.println("Options:");
        System.out.println("\tc -> plant corn(4-6 unit profit)");
        System.out.println("\ta -> plant cocoa(1-10 unit profit)");
        System.out.println("\tb -> plant banana(2-8 unit profit)");
        System.out.println("\tp -> purchase land(land size 10-30 units)");
        System.out.println("\tn -> proceed to next year");
        System.out.println("\tv -> view land list");
        System.out.println("\ts -> save player to file");
        System.out.println("\tl -> load player from file");
        System.out.println("\tq -> quit");
    }

    //Modifies: this
    //Effects: plant corn to empty land
    private void doCornPlanting() {
        if (player.getLands().isEmpty()) {
            System.out.println("No available land...\n");
        } else {
            if (player.plantCorp(corn)) {
                viewLand();
            } else {
                System.out.println("Land full...\n");
            }
        }
    }

    //Modifies: this
    //Effects: plant cocoa to empty land
    private void doCocoaPlanting() {
        if (player.getLands().isEmpty()) {
            System.out.println("No available land...\n");
        } else {
            if (player.plantCorp(cocoa)) {
                viewLand();
            } else {
                System.out.println("Land full...\n");
            }
        }
    }

    //Modifies: this
    //Effects: plant banana to empty land
    private void doBananaPlanting() {
        if (player.getLands().isEmpty()) {
            System.out.println("No available land...\n");
        } else {
            if (player.plantCorp(banana)) {
                viewLand();
            } else {
                System.out.println("Land full...\n");
            }
        }
    }

    //Modifies: this
    //Effects: add new land to player's land list if money enough
    private void doPurchasingLand() {
        System.out.print("Create land ID:\n");
        int landId = input.nextInt();
        player.createLand(landId);
        if (player.getMoney() >= player.createLand(landId).getCost()) {
            player.purchaseLand(player.createLand(landId));
        } else {
            System.out.println("Money not enough...\n");
        }
        viewLand();
    }

    //Modifies: this
    //Effects: add profit to money and proceed to next year
    private void doNextYear() {
        player.getProfit();
        year++;

    }

    //Effects: print out all land in land list
    private void viewLand() {
        System.out.println("Land list:");
        if (player.getLands().isEmpty()) {
            System.out.println("No available land\n");
        } else {
            System.out.println("ID" + " " + "Size" + " " + "Profit" + " " + "Corp");
            for (Land l : player.getLands()) {
                System.out.println(l.getID() + "  " + l.getSize() + "   " + l.getLandProfit() + "     "
                        + l.getPlantName());
            }
        }
        System.out.println("\n");
    }

    // EFFECTS: saves the player to file
    private void savePlayer() {
        try {
            jsonWriter.open();
            jsonWriter.write(player);
            jsonWriter.close();
            System.out.println("Saved " + player.getName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads player from file
    private void loadPlayer() {
        try {
            player = jsonReader.read();
            System.out.println("Loaded " + player.getName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

}
