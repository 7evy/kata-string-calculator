import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StringCalculatorTest {

    StringCalculator calculator;
    StringCalculatorTest() {
        calculator = new StringCalculator();
    }

    @Test
    public void Add_EmptyString_ReturnZero() throws Exception {
        Assertions.assertEquals(calculator.Add(""), 0);
    }

    @Test
    public void Add_MissingNumber_ThrowException() {
        Assertions.assertThrows(Exception.class, () -> calculator.Add("1,,2"));
    }

    @Test
    public void Add_SingleNumber_ReturnThatNumber() throws Exception {
        Assertions.assertEquals(calculator.Add("1"), 1);
    }

    @Test
    public void Add_SeveralNumbers_ReturnSum() throws Exception {
        Assertions.assertEquals(calculator.Add("1,2,3,4,5"), 15);
    }

    @Test
    public void Add_StartingDelimiter_ThrowException() {
        Assertions.assertThrows(Exception.class, () -> calculator.Add(",1,2"));
    }

    @Test
    public void Add_TrailingDelimiter_IsValid() throws Exception {
        Assertions.assertEquals(calculator.Add("1,2,"), 3);
    }

    @Test
    public void Add_NewlineDelimiter_IsValid() throws Exception {
        Assertions.assertEquals(calculator.Add("1,2\n3"), 6);
    }

    @Test
    public void Add_InvalidDelimiter_ThrowException() {
        Assertions.assertThrows(Exception.class, () -> calculator.Add("1/2"));
    }

    @Test
    public void Add_CustomDelimiterValidSyntax_IsValid() throws Exception {
        Assertions.assertEquals(calculator.Add("//;\n1;2;3"), 6);
    }

    @Test
    public void Add_DefaultDelimiterWithACustomOne_ThrowException() {
        Assertions.assertThrows(Exception.class, () -> calculator.Add("//;\n1,2\n3"));
    }

    @Test
    public void Add_CustomDelimiterBadSyntax_ThrowException() {
        Assertions.assertThrows(Exception.class, () -> calculator.Add("//;1;2;3"));
        Assertions.assertThrows(Exception.class, () -> calculator.Add("/;\n1;2;3"));
    }

    @Test
    public void Add_NumberAsCustomDelimiter_ThrowException() {
        Assertions.assertThrows(Exception.class, () -> calculator.Add("//0\n10203"));
    }

    @Test
    public void Add_NegativeNumbers_ThrowException() {
        Assertions.assertThrows(Exception.class, () -> calculator.Add("-1"));
    }

    @Test
    public void Add_NegativeNumbers_AllNegativesAppearInExceptionMessage() {
        try {
            calculator.Add("-1,-2");
        } catch (Exception e) {
            Assertions.assertEquals("Negatives are not allowed: [-1, -2]", e.getMessage());
        }
    }
}
