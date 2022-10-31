package no.nerdschool.uglycode;

import org.junit.Assert;
import org.junit.Test;

public class OperationTest {

    @Test
    public void shouldSum() {
        Operation operation = new Operation(2, 2, "+");
        Assert.assertEquals(4, operation.execute());
    }

    @Test
    public void shouldMultiply() {
        Operation operation = new Operation(2, 2, "*");
        Assert.assertEquals(4, operation.execute());
    }

    @Test
    public void shouldSubtract() {
        Operation operation = new Operation(2, 2, "-");
        Assert.assertEquals(0, operation.execute());
    }

    @Test
    public void shouldDivide() {
        Operation operation = new Operation(10, 2, "/");
        Assert.assertEquals(5, operation.execute());
    }

    @Test
    public void shouldDivideByDefault() {
        Operation operation = new Operation(10, 2, "a");
        Assert.assertEquals(5, operation.execute());
    }
}