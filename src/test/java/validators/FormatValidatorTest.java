package validators;

import exceptions.BadDelimiterException;
import exceptions.NegativeNumberException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class FormatValidatorTest {

    FormatValidator formatValidator;
    FormatValidatorTest() {
        formatValidator = new FormatValidator();
    }

    @Test
    public void checkDelimiter_BadCustomDelimiter_ThrowException() {
        Assertions.assertThrows(BadDelimiterException.class, () -> formatValidator.checkDelimiter("0"));
        Assertions.assertThrows(BadDelimiterException.class, () -> formatValidator.checkDelimiter("-"));
    }

    @Test
    public void checkNegatives_NegativeNumbers_ThrowException() {
        List<Integer> numbers = new ArrayList<>();
        numbers.add(1);
        numbers.add(-1);
        Assertions.assertThrows(NegativeNumberException.class, () -> formatValidator.checkNegatives(numbers));
    }

    @Test
    public void checkNegatives_SomeNegativeNumbers_AllNegativesAppearInExceptionMessage() {
        List<Integer> numbers = new ArrayList<>();
        numbers.add(-1);
        numbers.add(1);
        numbers.add(-2);
        try {
            formatValidator.checkNegatives(numbers);
        } catch (NegativeNumberException e) {
            Assertions.assertTrue(e.getMessage().contains("-1") && e.getMessage().contains("-2"));
        }
    }
}
