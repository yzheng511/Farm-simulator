package model;

public class Land {
    private Corp plant;
    private final int cost = 200;
    private int landCost;
    private String name;
    private int productionFactor;

    public Land(String landName) {
        name = landName;
        plant = null;
        landCost = cost;
        productionFactor = (int) (Math.random() * 21 + 10);

    }


    public int getCost() {
        return landCost;
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return productionFactor;
    }

    public Corp getPlant() {
        return plant;
    }

    public String getPlantName() {
        if (getPlant() == null) {
            return "no plant";
        } else {
            return plant.getCorpName();
        }

    }

    public void setPlant(Corp corp) {
        plant = corp;
    }

    public int getLandProfit() {
        if (getPlant() == null) {
            return 0;
        } else {
            return productionFactor * plant.getRevenue();
        }
    }
}
