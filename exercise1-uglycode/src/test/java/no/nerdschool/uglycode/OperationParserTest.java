package no.nerdschool.uglycode;

import org.junit.Assert;
import org.junit.Test;

public class OperationParserTest {

    @Test(expected = StringIndexOutOfBoundsException.class)
    public void shouldThrowWithoutWhitespace() {
        OperationParser.parse("1");
    }

    @Test(expected = StringIndexOutOfBoundsException.class)
    public void shouldThrowWithOneWhitespace() {
        OperationParser.parse("1 1");
    }

    @Test
    public void shouldParseOperation() {
        Operation expectedOperation = new Operation(1, 1, "+");
        Operation parsedOperation = OperationParser.parse("1 1 +");
        Assert.assertEquals(expectedOperation, parsedOperation);
    }
}