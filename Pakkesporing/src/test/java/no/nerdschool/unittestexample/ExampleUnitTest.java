package no.nerdschool.unittestexample;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ExampleUnitTest {
    @Test
    public void testReturnTwo() {
        int returnValue = Example.returnTwo();
        assertEquals(2, returnValue);
    }
}
