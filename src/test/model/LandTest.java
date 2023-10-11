package model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class LandTest {
    private Land testLand;
    private Corp testCorp;

    @BeforeEach
    void runBefore(){
        testLand = new Land(1);
        testCorp = new Corp("Corn", (int) (Math.random() * 3 + 4));
    }

    @Test
    void testConstructor(){
        assertEquals(1, testLand.getID());
        assertEquals(200, testLand.getCost());
        assertNull(testLand.getPlant());
    }

    @Test
    void testGetPlantName(){
        assertEquals("no plant",testLand.getPlantName());
        testLand.setPlant(testCorp);
        assertEquals("Corn", testLand.getPlantName());
    }

    @Test
    void testGetLandProfit(){
        assertEquals(0, testLand.getLandProfit());
        testLand.setPlant(testCorp);
        assertEquals(testLand.getSize()* testCorp.getRevenue(), testLand.getLandProfit());
    }
}
