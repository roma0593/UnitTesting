package json_parser_test;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import parser.JsonParser;
import shop.Cart;
import shop.RealItem;
import shop.VirtualItem;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class PositiveJSonParserTest {
    private static final JsonParser jsonParser = new JsonParser();
    private static Cart cart;
    private static VirtualItem virtualItem;
    private static RealItem realItem;
    private static File cartFile;

    @BeforeAll
    static void createCartWithItems(){
        cart = new Cart("new_cart");

        virtualItem = new VirtualItem();
        virtualItem.setName("world of tanks");
        virtualItem.setSizeOnDisk(30.50);
        virtualItem.setPrice(15.00);

        realItem = new RealItem();
        realItem.setName("beer");
        realItem.setWeight(300.00);
        realItem.setPrice(3.90);

        cart.addVirtualItem(virtualItem);
        cart.addRealItem(realItem);
    }

    @AfterAll
    static void deleteJsonFile(){
        cartFile.deleteOnExit();
    }

    @Test
    void writeToFileTest(){
        jsonParser.writeToFile(cart);

        cartFile = new File("src/main/resources/" + cart.getCartName() + ".json");

        assertTrue(cartFile.exists());
    }

    @Disabled
    void readFromFileTest(){

        Cart readFromJsonCart = jsonParser.readFromFile(cartFile);

        assertAll(
                () -> assertEquals(cart.getCartName(), readFromJsonCart.getCartName()),
                () -> assertEquals(cart.getTotalPrice(), readFromJsonCart.getTotalPrice())
        );
    }

    @Test
    void writeToFileCartWithEmptyFields(){
        VirtualItem emptyVirtualItem = new VirtualItem();
        RealItem emptyRealItem = new RealItem();
        cart.addVirtualItem(emptyVirtualItem);
        cart.addRealItem(emptyRealItem);

        jsonParser.writeToFile(cart);

        cartFile = new File("src/main/resources/" + cart.getCartName() + ".json");

        assertTrue(cartFile.exists());
    }

}
