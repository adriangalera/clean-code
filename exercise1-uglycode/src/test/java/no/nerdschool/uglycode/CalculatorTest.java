package no.nerdschool.uglycode;

import org.junit.Assert;
import org.junit.Test;

public class CalculatorTest {

    @Test
    public void shouldReturnZeroIfNoString() {
        Assert.assertEquals(0, Calculator.calculate(0));
        Assert.assertEquals(0, Calculator.calculate(0f));
        Assert.assertEquals(0, Calculator.calculate(0d));
    }
}
