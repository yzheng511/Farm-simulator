package model;

import java.util.List;
import java.util.ArrayList;

public class Player {
    private String name;
    private List<Land> lands;
    private int money;


    public Player(String playerName, int initialMoney) {
        name = playerName;
        lands = new ArrayList<Land>();
        money = initialMoney;
    }

    public Land createLand(String s) {
        Land l = new Land(s);
        return l;
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


    public int getProfit() {
        for (Land l : lands) {
            if (l.getPlant() != null) {
                money += l.getLandProfit();
            }
        }
        return money;
    }

    public int getMoney() {
        return money;
    }

    public List<Land> getLands() {
        return lands;
    }

}
