package real_item_test;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import shop.RealItem;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PositiveRealItemTest {
    private static RealItem realItem;

    @BeforeAll
    static void createRItemObject(){
        realItem = new RealItem();
    }

    @Test
    void setValuesForRealItemTest(){
        realItem.setWeight(1500.55);
        realItem.setName("car");
        realItem.setPrice(3000.99);

        assertAll(
                () -> assertEquals(1500.55, realItem.getWeight()),
                () -> assertEquals(3000.99, realItem.getPrice()),
                () -> assertEquals("car", realItem.getName())
        );
    }
}
