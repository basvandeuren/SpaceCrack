package be.kdg.spacecrack;

import be.kdg.spacecrack.utilities.ITokenValueGenerator;
import be.kdg.spacecrack.utilities.TokenValueGenerator;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Tim on 4/02/14.
 */
public class TokenValueGeneratorTests {

    @Test
    public void testGenerator() throws Exception {
        ITokenValueGenerator tokenValueGenerator = new TokenValueGenerator(1234L);
        String actual = tokenValueGenerator.generateToken();
        assertEquals("38gqit55s18bd2r37jf3lsgf7l", actual);


    }
}
