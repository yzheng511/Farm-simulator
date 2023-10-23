package persistence;

import org.junit.jupiter.api.Test;
import model.Player;
import model.Land;
import model.Corp;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest extends JsonTest{

    @Test
    void testWriterInvalidFile() {
        try {
            Player pl = new Player("Player1", 2000);
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterPlayerWithNoAction() {
        try {
            Player pl = new Player("Player1", 2000);
            JsonWriter writer = new JsonWriter("./data/testWriterPlayerWithNoAction.json");
            writer.open();
            writer.write(pl);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterPlayerWithNoAction.json");
            pl = reader.read();
            assertEquals("Player1", pl.getName());
            assertEquals(2000, pl.getMoney());
            assertEquals(0, pl.getLands().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralPlayer() {
        try {
            Player pl = new Player("Player1", 2000);
            Land l1 = new Land(1);
            Land l2 = new Land(2);
            Land l3 = new Land(3);
            Corp corn = new Corp("Corn", (int) (Math.random() * 3 + 4));
            Corp cocoa = new Corp("Cocoa", (int) (Math.random() * 10 + 1));
            Corp banana = new Corp("Banana", (int) (Math.random() * 7 + 2));
            pl.purchaseLand(l1);
            pl.purchaseLand(l2);
            pl.purchaseLand(l3);
            pl.plantCorp(corn);
            pl.plantCorp(cocoa);
            pl.plantCorp(banana);
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralPlayer.json");
            writer.open();
            writer.write(pl);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralPlayer.json");
            pl = reader.read();
            assertEquals("Player1", pl.getName());
            assertEquals(1400, pl.getMoney());
            List<Land> lands = pl.getLands();
            assertEquals(3, lands.size());
            checkLand(1 , lands.get(0).getSize() ,corn, lands.get(0));
            checkLand(2 , lands.get(1).getSize() ,cocoa, lands.get(1));
            checkLand(3 , lands.get(2).getSize() ,banana, lands.get(2));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
