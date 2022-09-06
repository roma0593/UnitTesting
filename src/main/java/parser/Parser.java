package parser;

import shop.Cart;

import java.io.File;
import java.io.IOException;

public interface Parser {

    void writeToFile(Cart cart);
    Cart readFromFile(File file);
}
