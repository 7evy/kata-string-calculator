package parsers;

import exceptions.BadFormatException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class IntegerParserTest {

    IntegerParser parser;
    IntegerParserTest() {
        parser = new IntegerParser();
    }

    @Test
    public void parse_MissingNumber_ThrowException() {
        Assertions.assertThrows(BadFormatException.class, () -> parser.parse("1,,2"));
    }

    @Test
    public void parse_StartingDelimiter_ThrowException() {
        Assertions.assertThrows(BadFormatException.class, () -> parser.parse(",1,2"));
    }

    @Test
    public void parse_InvalidDelimiter_ThrowException() {
        Assertions.assertThrows(BadFormatException.class, () -> parser.parse("1/2"));
    }

    @Test
    public void parse_CustomDelimiterBadSyntax_ThrowException() {
        Assertions.assertThrows(BadFormatException.class, () -> parser.parse("//;1;2;3"));
        Assertions.assertThrows(BadFormatException.class, () -> parser.parse("/;\n1;2;3"));
    }

    @Test
    public void parse_DefaultDelimiterWithACustomOne_ThrowException() {
        Assertions.assertThrows(BadFormatException.class, () -> parser.parse("//;\n1,2\n3"));
    }
}
