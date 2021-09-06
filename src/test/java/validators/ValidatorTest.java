package validators;

import exceptions.BadDelimiterException;
import exceptions.NegativeNumberException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class ValidatorTest {

    Validator validator;
    ValidatorTest() {
        validator = new Validator();
    }

    @Test
    public void checkDelimiter_BadCustomDelimiter_ThrowException() {
        Assertions.assertThrows(BadDelimiterException.class, () -> validator.checkDelimiter("0"));
        Assertions.assertThrows(BadDelimiterException.class, () -> validator.checkDelimiter("-"));
    }

    @Test
    public void checkNegatives_NegativeNumbers_ThrowException() {
        ArrayList<Integer> numbers = new ArrayList<>();
        numbers.add(1);
        numbers.add(-1);
        Assertions.assertThrows(NegativeNumberException.class, () -> validator.checkNegatives(numbers));
    }

    @Test
    public void checkNegatives_SomeNegativeNumbers_AllNegativesAppearInExceptionMessage() {
        ArrayList<Integer> numbers = new ArrayList<>();
        numbers.add(-1);
        numbers.add(1);
        numbers.add(-2);
        try {
            validator.checkNegatives(numbers);
        } catch (NegativeNumberException e) {
            Assertions.assertTrue(e.getMessage().contains("-1") && e.getMessage().contains("-2"));
        }
    }
}
