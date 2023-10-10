package model;


public class Corp {
    private int corpPrice;
    private String corpName;

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
