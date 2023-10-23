package persistence;

import model.Land;
import model.Corp;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {

    protected void checkLand(int id, int size, Corp plant, Land land){
        assertEquals(id, land.getID());
        assertEquals(size, land.getSize());
        assertEquals(plant.getCorpName(), land.getPlant().getCorpName());
        assertEquals(plant.getRevenue(), land.getPlant().getRevenue());
    }
}
