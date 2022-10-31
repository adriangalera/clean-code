package no.nerdschool.uglycode;

public class Calculator {

    public static int calculate(Object argument) {
        String inputText;
        if (notString(argument)) {
            return 0;
        }
        inputText = (String) argument;
        return calculate(inputText);
    }

    public static int calculate(String inputText) {
        Operation operation = OperationParser.parse(inputText);
        return operation.execute();
    }

    private static boolean notString(Object argument) {
        return !(argument instanceof String);
    }
}
