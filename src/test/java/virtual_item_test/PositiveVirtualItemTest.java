package virtual_item_test;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import shop.VirtualItem;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PositiveVirtualItemTest {
    private static VirtualItem vItem;

    @BeforeAll
    static void createVItemObject(){
        vItem = new VirtualItem();
    }

    @Test
    void setValuesForVirtualItemTest(){
        vItem.setSizeOnDisk(100.00);
        vItem.setName("photo");
        vItem.setPrice(2.99);

        assertAll(
                () -> assertEquals(100.00, vItem.getSizeOnDisk(), "Sizes are not equal"),
                () -> assertEquals("photo", vItem.getName(), "Names are not equal"),
                () -> assertEquals(2.99, vItem.getPrice(), "Prices are not equal")
        );
    }
}
