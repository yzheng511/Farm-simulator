package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import model.Corp;
import model.Land;
import model.Player;
import persistence.JsonReader;
import persistence.JsonWriter;

//Code influenced by the C3-LectureLabStarter https://github.students.cs.ubc.ca/CPSC210/C3-LectureLabStarter.git
//Images borrowed from https://www.iconarchive.com/show/noto-emoji-food-drink-icons-by-google/32362-ear-of-corn-icon.html,
//https://www.iconarchive.com/show/noto-emoji-food-drink-icons-by-google/32362-ear-of-corn-icon.html,
//https://clipart-library.com/clipart/1653745.htm

//Farming game application with graphical user interface
public class FarmingGameGUI extends JFrame {

    private static final String JSON_STORE = "./data/player.json";
    private JLabel moneyLabel;
    private JLabel yearLabel;
    private JLabel winCondLabel;
    private JLabel moneyLabelTitle;
    private JLabel yearLabelTitle;
    private JPanel dataPanel;
    private JButton purchaseButton;
    private JButton cornButton;
    private JButton cocoaButton;
    private JButton bananaButton;
    private JButton proceedButton;
    private JButton saveButton;
    private JButton loadButton;
    private JTextField idInput;
    private Player player;
    private Corp corn;
    private Corp cocoa;
    private Corp banana;
    private int winCond;
    private JsonReader jsonReader;
    private JsonWriter jsonWriter;
    private ImageIcon landST;
    private ImageIcon landCocoa;
    private ImageIcon landBanana;
    private ImageIcon landCorn;
    private Map<Land, List<Integer>> landLocation;
    private int landAreaX;
    private int landAreaY;
    private int instantX;
    private int instantY;
    private int landIndex;
    private Land empLand;
    private List<JLabel> addedLand;


    //Effects: run farming game graphical user interface
    public FarmingGameGUI() {
        super("Farming UI");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        init();
        dataPanel = new JPanel();
        dataPanel.setOpaque(true);
        dataPanel.setBackground(Color.YELLOW);
        dataPanel.setLayout(null);
        initDataLabel();
        initPurchase();
        initPlant();
        purchaseLand();
        plantCorn();
        plantBanana();
        plantCocoa();
        doNextYear();
        savePlayer();
        loadPlayer();
        setContentPane(dataPanel);
        setSize(800, 400);
        setLocationByPlatform(true);
        setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: initializes player and farm setting
    private void init() {
        player = new Player("Player1", 2000);
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        landLocation = new HashMap<>();
        corn = new Corp("Corn", (int) (Math.random() * 3 + 4));
        cocoa = new Corp("Cocoa", (int) (Math.random() * 10 + 1));
        banana = new Corp("Banana", (int) (Math.random() * 7 + 2));
        winCond = 50000;
        landAreaX = 400;
        landAreaY = 5;
        instantX = 0;
        instantY = 0;
        loadImages();
        empLand = null;
        landIndex = 0;
        addedLand = new ArrayList<>();
    }

    //Modifies: this;
    //Effects: initialize data label
    private void initDataLabel() {
        moneyLabel = new JLabel(Integer.toString(player.getMoney()));
        moneyLabel.setSize(200, 15);
        moneyLabel.setLocation(48, 5);
        yearLabel = new JLabel(Integer.toString(player.getYear()));
        yearLabel.setSize(200, 15);
        yearLabel.setLocation(35, 20);
        moneyLabelTitle = new JLabel("Money:");
        moneyLabelTitle.setSize(200, 15);
        moneyLabelTitle.setLocation(0, 5);
        yearLabelTitle = new JLabel("Year:");
        yearLabelTitle.setSize(200, 15);
        yearLabelTitle.setLocation(0, 20);
        winCondLabel = new JLabel("Winning Condition: " + Integer.toString(winCond));
        winCondLabel.setSize(200, 15);
        winCondLabel.setLocation(120, 5);
        dataPanel.add(moneyLabel);
        dataPanel.add(yearLabel);
        dataPanel.add(moneyLabelTitle);
        dataPanel.add(yearLabelTitle);
        dataPanel.add(winCondLabel);
    }

    //Modifies: this
    //Effects: create new land and deduct land cost
    private void purchaseLand() {
        purchaseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == purchaseButton) {
                    int id = Integer.parseInt(idInput.getText());
                    Land l = player.createLand(id);
                    if (player.getMoney() >= l.getCost()) {
                        player.purchaseLand(l);
                        drawLand();
                        repaint();
                        List<Integer> numLocation = new ArrayList<>();
                        numLocation.add(instantX);
                        numLocation.add(instantY);
                        landLocation.put(l, numLocation);
                    } else {
                        System.out.println("Money not enough...\n");
                    }
                }
            }
        });
    }

    //Modifies: this
    //Effects: plant corn in nearest empty land and show in screen
    private void plantCorn() {
        cornButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == cornButton) {
                    for (Land l : player.getLands()) {
                        if (l.getPlant() == null) {
                            empLand = l;
                            landIndex = player.getLands().indexOf(l);
                            break;
                        }
                    }
                    if (player.getLands().isEmpty()) {
                        System.out.println("No available land...\n");
                    } else {
                        if (player.plantCorp(corn)) {
                            drawCorn();
                        } else {
                            System.out.println("Land full...\n");
                        }
                    }
                }
            }
        });
    }

    //Modifies: this
    //Effects: plant banana in nearest empty land and show in screen
    private void plantBanana() {
        bananaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == bananaButton) {
                    for (Land l : player.getLands()) {
                        if (l.getPlant() == null) {
                            empLand = l;
                            landIndex = player.getLands().indexOf(l);
                            break;
                        }
                    }
                    if (player.getLands().isEmpty()) {
                        System.out.println("No available land...\n");
                    } else {
                        if (player.plantCorp(banana)) {
                            drawBanana();
                        } else {
                            System.out.println("Land full...\n");
                        }
                    }
                }
            }
        });
    }

    //Modifies: this
    //Effects: plant cocoa in nearest empty land and show in screen
    private void plantCocoa() {
        cocoaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == cocoaButton) {
                    for (Land l : player.getLands()) {
                        if (l.getPlant() == null) {
                            empLand = l;
                            landIndex = player.getLands().indexOf(l);
                            break;
                        }
                    }
                    if (player.getLands().isEmpty()) {
                        System.out.println("No available land...\n");
                    } else {
                        if (player.plantCorp(cocoa)) {
                            drawCocoa();
                        } else {
                            System.out.println("Land full...\n");
                        }
                    }
                }
            }
        });
    }

    //Modifies: this
    //Effects: update player data to next year and refresh graphically in screen
    private void doNextYear() {
        proceedButton = new JButton("Proceed to Next Year");
        proceedButton.setActionCommand("proceed to next year");
        proceedButton.setLocation(5, 275);
        proceedButton.setSize(160, 15);
        dataPanel.add(proceedButton);
        proceedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == proceedButton) {
                    player.getProfit();
                    player.addYear();
                    moneyLabel.setText(Integer.toString(player.getMoney()));
                    yearLabel.setText(Integer.toString(player.getYear()));
                }
            }
        });
    }

    //Modifies: this
    //Effects: save player data
    private void savePlayer() {
        saveButton = new JButton("Save Data");
        saveButton.setActionCommand("save data");
        saveButton.setLocation(5, 125);
        saveButton.setSize(100, 15);
        dataPanel.add(saveButton);
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == saveButton) {
                    try {
                        jsonWriter.open();
                        jsonWriter.write(player);
                        jsonWriter.close();
                        System.out.println("Saved " + player.getName() + " to " + JSON_STORE);
                    } catch (FileNotFoundException ex) {
                        System.out.println("Unable to write to file: " + JSON_STORE);
                    }
                }
            }
        });
    }

    //Modifies: this
    //load player data from file
    private void loadPlayer() {
        loadButton = new JButton("Load Data");
        loadButton.setActionCommand("load data");
        loadButton.setLocation(5, 150);
        loadButton.setSize(100, 15);
        dataPanel.add(loadButton);
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == loadButton) {
                    try {
                        player = jsonReader.read();
                        moneyLabel.setText(Integer.toString(player.getMoney()));
                        yearLabel.setText(Integer.toString(player.getYear()));
                        restoreLand();
                        repaint();
                        System.out.println("Loaded " + player.getName() + " from " + JSON_STORE);
                    } catch (IOException ex) {
                        System.out.println("Unable to read from file: " + JSON_STORE);
                    }
                }
            }
        });
    }

    //Modifies: this
    //Effects: initialize graphical representation for methods needed for making purchase
    private void initPurchase() {
        purchaseButton = new JButton("Purchase Land");
        purchaseButton.setActionCommand("purchase");
        purchaseButton.setLocation(130, 300);
        purchaseButton.setSize(130, 15);
        idInput = new JTextField();
        idInput.setSize(new Dimension(120, 15));
        idInput.setLocation(5, 300);
        dataPanel.add(purchaseButton);
        dataPanel.add(idInput);
    }

    //Modifies: this
    //Effects: initialize graphical representation for methods for making planting
    private void initPlant() {
        cornButton = new JButton("Plant Corn");
        cornButton.setActionCommand("plant corn");
        cornButton.setLocation(5, 200);
        cornButton.setSize(120, 15);
        cocoaButton = new JButton("Plant Cocoa");
        cocoaButton.setActionCommand("plant cocoa");
        cocoaButton.setLocation(5, 225);
        cocoaButton.setSize(120, 15);
        bananaButton = new JButton("Plant Banana");
        bananaButton.setActionCommand("plant banana");
        bananaButton.setLocation(5, 250);
        bananaButton.setSize(120, 15);
        dataPanel.add(cornButton);
        dataPanel.add(cocoaButton);
        dataPanel.add(bananaButton);
    }

    //Modifies: this
    //Effects: make graphical representation of land in screen
    private void drawLand() {
        if (landAreaX >= 785) {
            JLabel landImage0 = new JLabel(landST);
            landImage0.setSize(30, 30);
            landImage0.setLocation(400, landAreaY + 35);
            dataPanel.add(landImage0);
            instantX = 400;
            instantY = landAreaY + 35;
            landAreaX = 435;
            landAreaY += 35;
            moneyLabel.setText(Integer.toString(player.getMoney()));
            addedLand.add(landImage0);
        } else {
            JLabel landImage0 = new JLabel(landST);
            landImage0.setSize(30, 30);
            landImage0.setLocation(landAreaX, landAreaY);
            dataPanel.add(landImage0);
            instantX = landAreaX;
            instantY = landAreaY;
            landAreaX += 35;
            moneyLabel.setText(Integer.toString(player.getMoney()));
            addedLand.add(landImage0);
        }
    }

    //Modifies: this
    //Effects: making graphical representation of corn in screen
    private void drawCorn() {
        int xcoord = landLocation.get(empLand).get(0);
        int ycoord = landLocation.get(empLand).get(1);
        dataPanel.remove(addedLand.get(landIndex));
        JLabel landImage1 = new JLabel(landCorn);
        landImage1.setSize(30, 30);
        landImage1.setLocation(xcoord, ycoord);
        dataPanel.add(landImage1);
        repaint();
    }

    //Modifies: this
    //Effects: making graphical representation of banana in screen
    private void drawBanana() {
        int xcoord = landLocation.get(empLand).get(0);
        int ycoord = landLocation.get(empLand).get(1);
        dataPanel.remove(addedLand.get(landIndex));
        JLabel landImage2 = new JLabel(landBanana);
        landImage2.setSize(30, 30);
        landImage2.setLocation(xcoord, ycoord);
        dataPanel.add(landImage2);
        repaint();
    }

    //Modifies: this
    //Effects: making graphical representation of cocoa in screen
    private void drawCocoa() {
        int xcoord = landLocation.get(empLand).get(0);
        int ycoord = landLocation.get(empLand).get(1);
        dataPanel.remove(addedLand.get(landIndex));
        JLabel landImage3 = new JLabel(landCocoa);
        landImage3.setSize(30, 30);
        landImage3.setLocation(xcoord, ycoord);
        dataPanel.add(landImage3);
        repaint();
    }

    //Effects: initialize images in the program
    private void loadImages() {
        String sep = System.getProperty("file.separator");
        landST = new ImageIcon(System.getProperty("user.dir") + sep
                + "image" + sep + "210st.jpg");
        landBanana = new ImageIcon(System.getProperty("user.dir") + sep
                + "image" + sep + "210banana.png");
        landCocoa = new ImageIcon(System.getProperty("user.dir") + sep
                + "image" + sep + "210cocoa.png");
        landCorn = new ImageIcon(System.getProperty("user.dir") + sep
                + "image" + sep + "210corn.png");
    }

    //Modifies: this
    //Effects: show graphical representation in screen according to previous user data
    private void restoreLand() {
        for (Land l : player.getLands()) {
            if (landAreaX >= 785) {
                landAreaX = 400;
                landAreaY += 35;
                generateIcon(l);
                landIndex = player.getLands().indexOf(l);
                landAreaX = 435;
                landAreaY += 35;
            } else {
                generateIcon(l);
                landIndex = player.getLands().indexOf(l);
                landAreaX += 35;
            }
        }
    }

    //Modifies: this
    //Effects: generate different graphical representations in screen according to user data
    private void generateIcon(Land l) {
        if (l.getPlantName().equals(corn.getCorpName())) {
            JLabel landImage1 = new JLabel(landCorn);
            landImage1.setSize(30, 30);
            landImage1.setLocation(landAreaX, landAreaY);
            dataPanel.add(landImage1);
            addedLand.add(landImage1);
            repaint();
        } else if (l.getPlantName().equals(cocoa.getCorpName())) {
            JLabel landImage3 = new JLabel(landCocoa);
            landImage3.setSize(30, 30);
            landImage3.setLocation(landAreaX, landAreaY);
            dataPanel.add(landImage3);
            addedLand.add(landImage3);
            repaint();
        } else if (l.getPlantName().equals(banana.getCorpName())) {
            JLabel landImage2 = new JLabel(landBanana);
            landImage2.setSize(30, 30);
            landImage2.setLocation(landAreaX, landAreaY);
            dataPanel.add(landImage2);
            addedLand.add(landImage2);
            repaint();
        }
    }

    public static void main(String[] args) {
        new FarmingGameGUI();
    }
}

