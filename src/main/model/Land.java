package model;

import org.json.JSONObject;
import persistence.Writable;

//Representing a class containing land Id, land cost, land size, and crop in land
public class Land implements Writable {
    private Corp plant;
    private final int cost = 200;
    private int landCost;
    private int id;
    private int size;

    /* Effects: id is set to landId, plant is set to null, landCost is set to cost, and
     * size is set to integer between 10-30
     */
    public Land(int landId) {
        id = landId;
        plant = null;
        landCost = cost;
        size = (int) (Math.random() * 21 + 10);

    }


    public int getCost() {
        return landCost;
    }

    public int getID() {
        return id;
    }

    public int getSize() {
        return size;
    }

    public Corp getPlant() {
        return plant;
    }

    //Effects: if this is plant in land, return plant name, otherwise, return "no plant"
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

    public void setSize(int s) {
        size = s;
    }

    // Effects: if there is plant in land, return product of plant price and land size,
    //          otherwise, return 0
    public int getLandProfit() {
        if (getPlant() == null) {
            return 0;
        } else {
            return size * plant.getRevenue();
        }
    }


    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("id", id);
        json.put("plant", plant.toJson());
        json.put("landCost", cost);
        json.put("size", size);
        return json;
    }
}
