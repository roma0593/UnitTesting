package json_parser_test;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import parser.JsonParser;
import parser.NoSuchFileException;
import shop.Cart;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class NegativeJSonParserTest {
    private static final JsonParser jsonParser = new JsonParser();
    private static Cart cart;
    private static File emptyFile;

    @BeforeAll
    static void createEmptyJsonFile() throws IOException {
        emptyFile = new File("src/main/resources/new_cart.json");
        emptyFile.createNewFile();
    }

    @AfterAll
    static void removeEmptyFile(){
        emptyFile.deleteOnExit();
    }

    @ParameterizedTest
    @ValueSource(strings = {"src/main/resources/cart.json"})
    void readFromAbsentFileTest(String path) {
        File absentFile = new File(path);
        Exception exception = assertThrows(NoSuchFileException.class, () -> {
           jsonParser.readFromFile(absentFile);
        });

        String expectedMessage = String.format("File %s.json not found!", absentFile);
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    @ParameterizedTest
    @ValueSource(strings = {"cart?", "cart/", "cart*", "cart|", "cart<"})
    void writeToFileWithInvalidCartNameTest(String cartName){
        cart = new Cart(cartName);

        assertThrows(FileNotFoundException.class, () -> {
           jsonParser.writeToFile(cart);
        });
    }

    @Test
    void readFromEmptyJsonFileTest(){
        assertThrows(NullPointerException.class, () ->
                jsonParser.readFromFile(emptyFile)
        );
    }
}
