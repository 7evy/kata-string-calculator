import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import parsers.IntegerParser;

public class StringCalculatorTest {

    StringCalculator calculator;
    StringCalculatorTest() {
        calculator = new StringCalculator(new IntegerParser());
    }

    @Test
    public void Add_EmptyString_ReturnZero() {
        Assertions.assertEquals(calculator.Add(""), 0);
    }

    @Test
    public void Add_SingleNumber_ReturnThatNumber() {
        Assertions.assertEquals(calculator.Add("1"), 1);
    }

    @Test
    public void Add_SeveralNumbers_ReturnSum() {
        Assertions.assertEquals(calculator.Add("1,2,3,4,5"), 15);
    }

    @Test
    public void Add_TrailingDelimiter_IsValid() {
        Assertions.assertEquals(calculator.Add("1,2,"), 3);
    }

    @Test
    public void Add_NewlineDelimiter_IsValid() {
        Assertions.assertEquals(calculator.Add("1,2\n3"), 6);
    }

    @Test
    public void Add_CustomDelimiterValidSyntax_IsValid() {
        Assertions.assertEquals(calculator.Add("//;\n1;2;3"), 6);
    }
}
