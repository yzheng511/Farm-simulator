package model;

// Represent a corp having corp name and corp price
public class Corp {
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

}
