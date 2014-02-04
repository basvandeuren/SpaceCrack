package be.kdg.spacecrack.utilities;

import org.springframework.stereotype.Component;

/**
 * Created by Tim on 4/02/14.
 */
@Component("generator")
public class TokenValueGenerator implements ITokenValueGenerator{
    @Override
    public String generateToken() {
        return "blabla";
    }
}
