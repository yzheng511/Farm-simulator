package ui;

import model.Corp;
import model.Land;
import model.Player;

import java.util.Scanner;

public class FarmingGame {

    private Player player;
    private Corp corn;
    private Corp cocoa;
    private Corp banana;
    private Scanner input;
    private int winCond;
    private int year;

    public FarmingGame() {
        runFarm();
    }

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
                System.out.println("\nWin!");
            } else {
                processCommand(command);
            }
        }
        System.out.println("\nEnd Game");
    }

    private void processCommand(String command) {
        if (command.equals("c")) {
            doCornPlanting();
        } else if (command.equals("a")) {
            doCocoaPlanting();
        } else if (command.equals("b")) {
            doBananaPlanting();
        } else if (command.equals("l")) {
            doPurchasingLand();
        } else if (command.equals("n")) {
            doNextYear();
        } else if (command.equals("v")) {
            viewLand();
        } else {
            System.out.println("Not a valid selection");
        }
    }

    private void init() {
        player = new Player("Player1", 2000);
        corn = new Corp("Corn", (int) (Math.random() * 3 + 4));
        cocoa = new Corp("Cocoa", (int) (Math.random() * 10 + 1));
        banana = new Corp("Banana", (int) (Math.random() * 7 + 2));
        winCond = 50000;
        year = 0;
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    private void displayMenu() {
        System.out.println("Year: \n" + year);
        System.out.println("Money: $\n" + player.getMoney());
        System.out.println("Goal: $\n" + winCond);
        System.out.println("\nOptions:");
        System.out.println("\tc -> plant corn(4-6 unit profit)");
        System.out.println("\ta -> plant cocoa(1-10 unit profit)");
        System.out.println("\tb -> plant banana(2-8 unit profit)");
        System.out.println("\tl -> purchase land(land size 10-30 units)");
        System.out.println("\tn -> proceed to next year");
        System.out.println("\tv -> view land list");
        System.out.println("\tq -> quit");
    }

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

    private void doPurchasingLand() {
        System.out.print("Enter land name:\n");
        String landName = input.nextLine();
        player.createLand(landName);
        if (player.getMoney() >= player.createLand(landName).getCost()) {
            player.purchaseLand(player.createLand(landName));
        } else {
            System.out.println("Money not enough...\n");
        }
        viewLand();
    }

    private void doNextYear() {
        player.getProfit();
        year++;

    }

    private void viewLand() {
        if (player.getLands().isEmpty()) {
            System.out.println("No available land\n");
        } else {
            System.out.println("Land Name" + " " + "Land Size" + " " + "Land Profit" + " " + "Corp Name");
            for (Land l : player.getLands()) {
                System.out.println(l.getName() + " " + l.getSize() + " " + l.getLandProfit() + " " + l.getPlantName());
            }
        }
    }

}
