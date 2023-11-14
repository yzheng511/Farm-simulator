package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ImageObserver;
import java.awt.image.RenderedImage;
import java.awt.image.renderable.RenderableImage;
import java.text.AttributedCharacterIterator;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import model.Corp;
import model.Land;
import model.Player;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;


public class FarmingGameGUI extends JFrame {

    private static final String JSON_STORE = "./data/player.json";
    private JLabel moneyLabel;
    private JLabel yearLabel;
    private JLabel winCondLabel;
    private JLabel moneyLabelTitle;
    private JLabel yearLabelTitle;
    private JLabel winCondLabelTitle;
    private JPanel dataPanel;
    private JButton purchaseButton;
    private JTextField idInput;
    private Player player;
    private Corp corn;
    private Corp cocoa;
    private Corp banana;
    private int winCond;
    private int year;
    private JsonReader jsonReader;
    private JsonWriter jsonWriter;
    private Graphics2D landST;
    private Graphics2D landC;
    private Graphics2D landB;
    private Graphics graphics;
    private Map<Land, List<Integer>> landLocation;
    private int landAreaX;
    private int landAreaY;


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
        purchaseLand();
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
        corn = new Corp("Corn", (int) (Math.random() * 3 + 4));
        cocoa = new Corp("Cocoa", (int) (Math.random() * 10 + 1));
        banana = new Corp("Banana", (int) (Math.random() * 7 + 2));
        winCond = 50000;
        year = 1;
        landAreaX = 400;
        landAreaY = 5;
    }

    //Modifies: this;
    //Effects: initialize data label
    private void initDataLabel() {
        moneyLabel = new JLabel(Integer.toString(player.getMoney()));
        moneyLabel.setSize(200, 15);
        moneyLabel.setLocation(48, 5);
        yearLabel = new JLabel(Integer.toString(year));
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
    public void purchaseLand() {
        purchaseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == purchaseButton) {
                    int id = Integer.parseInt(idInput.getText());
                    player.createLand(id);
                    if (player.getMoney() >= player.createLand(id).getCost()) {
                        player.purchaseLand(player.createLand(id));
                        drawLand(graphics);
                    } else {
                        System.out.println("Money not enough...\n");
                    }
                }
            }
        });
    }

    public void initPurchase() {
        purchaseButton = new JButton("Purchase Land");
        purchaseButton.setActionCommand("purchase");
        purchaseButton.setLocation(130, 300);
        purchaseButton.setSize(130, 15);
        idInput = new JTextField();
        idInput.setSize(new Dimension(120, 15));
        idInput.setLocation(0, 300);
        dataPanel.add(purchaseButton);
        dataPanel.add(idInput);
    }

    private void drawLand(Graphics graphics) {


    }

    public static void main(String[] args) {
        new FarmingGameGUI();
    }
}

