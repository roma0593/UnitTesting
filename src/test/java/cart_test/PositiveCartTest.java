package cart_test;

import org.junit.jupiter.api.*;
import shop.Cart;
import shop.RealItem;
import shop.VirtualItem;

import static org.junit.jupiter.api.Assertions.*;

class PositiveCartTest {
    private Cart cart;
    private static RealItem realItem;
    private static VirtualItem virtualItem;

    @BeforeAll
    static void createItems(){

        realItem = new RealItem();
        realItem.setName("apple");
        realItem.setWeight(0.30);
        realItem.setPrice(2.60);

        virtualItem = new VirtualItem();
        virtualItem.setName("FIFA22");
        virtualItem.setSizeOnDisk(6.50);
        virtualItem.setPrice(30.99);
    }

    @BeforeEach
    void createCart(){
        cart = new Cart("my cart");
    }

    @Test
    void cartEmptinessTest(){
        assertEquals(0.00, cart.getTotalPrice());
    }

    @Test
    void addRealItemTest(){
        cart.addRealItem(realItem);
        assertEquals(cart.getTotalPrice(), realItem.getPrice() * 1.20);
    }

    @Test
    void addVirtualItemTest(){
        cart.addVirtualItem(virtualItem);
        assertEquals(cart.getTotalPrice(), virtualItem.getPrice() * 1.20);
    }
}
