package no.nerdschool.uglycode;

public class OperationParser {

    public static Operation parse(String inputText) {
        final int skipper = 1;
        final char whiteSpaceChar = ' ';
        int firstOperand = Integer.parseInt(inputText.substring(0, 1 + inputText.indexOf(whiteSpaceChar) - skipper));
        int secondOperand = Integer.parseInt(inputText.substring(skipper + inputText.indexOf(whiteSpaceChar),
                                                                 inputText.indexOf(whiteSpaceChar,
                                                                                   inputText.indexOf(whiteSpaceChar)
                                                                                       + 1)));
        final int beginIndex = inputText.indexOf(whiteSpaceChar, 1 + inputText.indexOf(whiteSpaceChar)) + skipper;
        final String operator = inputText.substring(beginIndex);

        return new Operation(firstOperand, secondOperand, operator);
    }
}
