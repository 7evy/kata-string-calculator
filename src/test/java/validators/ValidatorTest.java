package validators;

import exceptions.BadDelimiterException;
import exceptions.NegativeNumberException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

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
        Assertions.assertThrows(NegativeNumberException.class, () -> validator.checkNegatives(new Integer[]{1, -1}));
    }

    @Test
    public void checkNegatives_SomeNegativeNumbers_AllNegativesAppearInExceptionMessage() {
        try {
            validator.checkNegatives(new Integer[]{-1, 1, -2});
        } catch (NegativeNumberException e) {
            Assertions.assertTrue(e.getMessage().contains("-1") && e.getMessage().contains("-2"));
        }
    }
}
