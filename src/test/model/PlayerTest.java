package model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class PlayerTest {
    private Player testPlayer;
    private Corp testCorp;

    @BeforeEach
    void runBefore(){
        testPlayer = new Player("Player1", 2000);
        testCorp = new Corp("Corn", (int) (Math.random() * 3 + 4));
    }

    @Test
    void testConstructor(){
        assertEquals("Player1", testPlayer.getName());
        assertEquals(2000,testPlayer.getMoney());
        assertTrue(testPlayer.getLands().isEmpty());
    }

    @Test
    void testPurchaseLand(){
        testPlayer.purchaseLand(testPlayer.createLand(1));
        assertEquals(1, testPlayer.getLands().size());
        assertEquals(1800, testPlayer.getMoney());
    }

    @Test
    void testPlantCorp(){
        assertFalse(testPlayer.plantCorp(testCorp));
        Land l = testPlayer.createLand(1);
        testPlayer.purchaseLand(l);
        assertTrue(testPlayer.plantCorp(testCorp));
        assertFalse(testPlayer.plantCorp(testCorp));

    }

    @Test
    void testGetProfit(){
        Land l = testPlayer.createLand(1);
        testPlayer.purchaseLand(l);
        testPlayer.getProfit();
        assertEquals(1800+l.getLandProfit(), testPlayer.getMoney());
        testPlayer.plantCorp(testCorp);
        testPlayer.getProfit();
        assertEquals(1800+l.getLandProfit(), testPlayer.getMoney());
    }

    @Test
    void testAddYear() {
        testPlayer.addYear();
        assertEquals(2, testPlayer.getYear());
    }
}