import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StringCalculatorTest {

    StringCalculator calculator;
    StringCalculatorTest() {
        calculator = new StringCalculator();
    }

    @Test
    public void addEmptyStringShouldBeZero() throws Exception {
        Assertions.assertEquals(calculator.Add(""), 0);
    }

    @Test
    public void addOneNumberShouldReturnIt() throws Exception {
        Assertions.assertEquals(calculator.Add("1"), 1);
    }

    @Test
    public void addTwoNumbersShouldReturnSum() throws Exception {
        Assertions.assertEquals(calculator.Add("1,2"), 3);
    }

    @Test
    public void addSeveralNumbersShouldReturnSum() throws Exception {
        Assertions.assertEquals(calculator.Add("1,2,3,4,5"), 15);
    }

    @Test
    public void addNewLineIsValidDelimiter() throws Exception {
        Assertions.assertEquals(calculator.Add("1,2\n3"), 6);
    }

    @Test
    public void addTestCustomDelimiter() throws Exception {
        Assertions.assertEquals(calculator.Add("//;\n1;2;3"), 6);
    }

    @Test
    public void addNegativeNumbersThrowException() {
        Assertions.assertThrows(Exception.class, () -> calculator.Add("-1"));
    }

    @Test
    public void addNegativeNumbersAllAppearInExceptionMessage() {
        try {
            calculator.Add("-1, -2");
        } catch (Exception e) {
            Assertions.assertEquals("Negatives are not allowed: [-1, -2]", e.getMessage());
        }
    }
}
