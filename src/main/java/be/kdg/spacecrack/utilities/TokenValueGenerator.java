package be.kdg.spacecrack.utilities;

import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.Random;

/**
 * Created by Tim on 4/02/14.
 */
@Component("generator")
public class TokenValueGenerator implements ITokenValueGenerator{
    long seed;
 Random random;
    public TokenValueGenerator(long seed) {
        random = new Random(seed);
    }

    public TokenValueGenerator() {
         random = new Random();
    }


    @Override
    public String generateToken() {
        return new BigInteger(130, random ).toString(32);
    }
}
