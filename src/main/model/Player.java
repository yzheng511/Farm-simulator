package model;

import java.util.List;
import java.util.ArrayList;

public class Player {
    private String name;
    private List<Land> lands;
    private int money;


    public Player(String playerName, int initialMoney) {
        name = playerName;
        lands = new ArrayList<>();
        money = initialMoney;
    }

    public Land createLand(int i) {
        return new Land(i);
    }

    public void purchaseLand(Land land) {
        lands.add(land);
        money -= land.getCost();

    }

    public boolean plantCorp(Corp corp) {
        boolean hasEmptyLand = false;
        for (Land l : lands) {
            if (l.getPlant() == null) {
                l.setPlant(corp);
                hasEmptyLand = true;
                break;
            }
        }
        return hasEmptyLand;
    }


    public void getProfit() {
        for (Land l : lands) {
            if (l.getPlant() != null) {
                money += l.getLandProfit();
            }
        }
    }

    public String getName() {
        return name;
    }

    public int getMoney() {
        return money;
    }

    public List<Land> getLands() {
        return lands;
    }

}
