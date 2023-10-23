package model;

import org.json.JSONObject;
import persistence.Writable;

// Represent a corp having corp name and corp price
public class Corp implements Writable {
    private int corpPrice;
    private String corpName;

    /* Requires: name has non-zero length
     * Effects: set corp name to name, corp price to price
     */
    public Corp(String name, int price) {
        corpName = name;
        corpPrice = price;
    }

    public int getRevenue() {
        return corpPrice;
    }

    public String getCorpName() {
        return corpName;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", corpName);
        json.put("price", corpPrice);
        return json;
    }
}
