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
        Assertions.assertEquals(calculator.add(""), 0);
    }

    @Test
    public void add_SingleNumber_ReturnThatNumber() {
        Assertions.assertEquals(calculator.add("1"), 1);
    }

    @Test
    public void add_SeveralNumbers_ReturnSum() {
        Assertions.assertEquals(calculator.add("1,2,3,4,5"), 15);
    }

    @Test
    public void add_TrailingDelimiter_IsValid() {
        Assertions.assertEquals(calculator.add("1,2,"), 3);
    }

    @Test
    public void add_NewlineDelimiter_IsValid() {
        Assertions.assertEquals(calculator.add("1,2\n3"), 6);
    }

    @Test
    public void add_CustomDelimiterValidSyntax_IsValid() {
        Assertions.assertEquals(calculator.add("//;\n1;2;3"), 6);
    }
}
