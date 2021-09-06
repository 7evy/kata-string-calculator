import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import parsers.IntegerParser;

public class StringCalculatorTest {

    StringCalculator calculator;
    StringCalculatorTest() {
        calculator = new StringCalculator(new IntegerParser());
    }

    @Test
    public void add_EmptyString_ReturnZero() {
        Assertions.assertEquals(0, calculator.add(""));
    }

    @Test
    public void add_SingleNumber_ReturnThatNumber() {
        Assertions.assertEquals(1, calculator.add("1"));
    }

    @Test
    public void add_SeveralNumbers_ReturnSum() {
        Assertions.assertEquals(15, calculator.add("1,2,3,4,5"));
    }

    @Test
    public void add_TrailingDelimiter_IsValid() {
        Assertions.assertEquals(3, calculator.add("1,2,"));
    }

    @Test
    public void add_NewlineDelimiter_IsValid() {
        Assertions.assertEquals(6, calculator.add("1,2\n3"));
    }

    @Test
    public void add_CustomDelimiterValidSyntax_IsValid() {
        Assertions.assertEquals(6, calculator.add("//;\n1;2;3"));
    }

    @Test
    public void add_CustomDelimiterRegexCharacter_IsValid() {
        Assertions.assertEquals(6, calculator.add("//.\n1.2.3"));
    }
}
