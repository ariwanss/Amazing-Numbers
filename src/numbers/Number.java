package numbers;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Number {

    String stringNumber;
    long longNumber;
    String reversedStringNumber;
    int numberOfDigits;
    int concatenationOfFirstDigitAndLastDigit;
    //long sumOfAllDigits;
    //long productOfAllDigits;
    Set<NumberProperty> propertiesToLook;

    public Number(String stringNumber, Set<NumberProperty> properties) {
        this.stringNumber = stringNumber;
        this.longNumber = Long.parseLong(stringNumber);
        this.reversedStringNumber = new StringBuilder(stringNumber).reverse().toString();
        this.numberOfDigits = stringNumber.length();
        String firstDigit = String.valueOf(stringNumber.charAt(0));
        String lastDigit = String.valueOf(stringNumber.charAt(numberOfDigits - 1));
        this.concatenationOfFirstDigitAndLastDigit = Integer.parseInt(firstDigit + lastDigit);
        this.propertiesToLook = properties;
    }

    public Number(long intNumber, Set<NumberProperty> properties) {
        this.stringNumber = Long.toString(intNumber);
        this.longNumber = intNumber;
        this.reversedStringNumber = new StringBuilder(stringNumber).reverse().toString();
        this.numberOfDigits = stringNumber.length();
        String firstDigit = String.valueOf(stringNumber.charAt(0));
        String lastDigit = String.valueOf(stringNumber.charAt(numberOfDigits - 1));
        this.concatenationOfFirstDigitAndLastDigit = Integer.parseInt(firstDigit + lastDigit);
        this.propertiesToLook = properties;
    }

    public boolean isEven() {
        return longNumber % 2 == 0;
    }

    public boolean isBuzz() {
        return longNumber % 7 == 0 || stringNumber.endsWith("7");
    }

    public boolean isDuck() {
        return stringNumber.matches("[0-9]+0+[0-9]*");
    }

    public boolean isNatural() {
        return longNumber >= 0;
    }

    public boolean isPalindromic() {
        return Objects.equals(stringNumber, reversedStringNumber);
    }

    public boolean isGapful() {
        return numberOfDigits >= 3 && longNumber % concatenationOfFirstDigitAndLastDigit == 0;
    }

    public boolean isSpy() {
        int sumOfAllDigits = stringNumber.chars()
                .mapToObj(Character::getNumericValue)
                .reduce(0, Integer::sum);
        int productOfAllDigits = stringNumber.chars()
                .mapToObj(Character::getNumericValue)
                .reduce(1, (a, b) -> a * b);
        return sumOfAllDigits == productOfAllDigits;
    }

    public boolean isSquare() {
        return Math.sqrt(longNumber) % 1 == 0;
    }

    public boolean isSunny() {
        return Math.sqrt(longNumber + 1) % 1 == 0;
    }

    public boolean isJumping() {
        boolean interDigitDifferenceIsOne = true;
        List<Integer> numbers = stringNumber.chars()
                .mapToObj(Character::getNumericValue).collect(Collectors.toList());
        for (int i = 0; i < numbers.size() - 1; i++) {
            interDigitDifferenceIsOne = interDigitDifferenceIsOne && Math.abs(numbers.get(i) - numbers.get(i + 1)) == 1;
        }
        return stringNumber.length() == 1 || interDigitDifferenceIsOne;
    }

    public boolean isHappy() {
        String number = stringNumber;
        do {
            int sequence = number.chars().mapToObj(Character::getNumericValue)
                    .map(x -> (int) Math.pow(x, 2)).reduce(0, Integer::sum);
            number = String.valueOf(sequence);
        } while (number.length() != 1);
        return number.equals("1");
    }

    public boolean isWhatWeAreLookingFor() {
        boolean isIt = true;
        for (NumberProperty property : propertiesToLook) {
            switch (property) {
                case EVEN:
                case NOT_ODD:
                    isIt = isIt && isEven();
                    break;
                case ODD:
                case NOT_EVEN:
                    isIt = isIt && !isEven();
                    break;
                case BUZZ:
                    isIt = isIt && isBuzz();
                    break;
                case DUCK:
                    isIt = isIt && isDuck();
                    break;
                case PALINDROMIC:
                    isIt = isIt && isPalindromic();
                    break;
                case GAPFUL:
                    isIt = isIt && isGapful();
                    break;
                case SPY:
                    isIt = isIt && isSpy();
                    break;
                case SQUARE:
                    isIt = isIt && isSquare();
                    break;
                case SUNNY:
                    isIt = isIt && isSunny();
                    break;
                case JUMPING:
                    isIt = isIt && isJumping();
                    break;
                case HAPPY:
                case NOT_SAD:
                    isIt = isIt && isHappy();
                    break;
                case SAD:
                case NOT_HAPPY:
                    isIt = isIt && !isHappy();
                    break;
                case NOT_SPY:
                    isIt = isIt && !isSpy();
                    break;
                case NOT_DUCK:
                    isIt = isIt && !isDuck();
                    break;
                case NOT_BUZZ:
                    isIt = isIt && !isBuzz();
                    break;
                case NOT_GAPFUL:
                    isIt = isIt && !isGapful();
                    break;
                case NOT_JUMPING:
                    isIt = isIt && !isJumping();
                    break;
                case NOT_PALINDROMIC:
                    isIt = isIt && !isPalindromic();
                    break;
                case NOT_SQUARE:
                    isIt = isIt && !isSquare();
                    break;
                case NOT_SUNNY:
                    isIt = isIt && !isSunny();
                    break;
            }
        }
        return isIt;
    }

    public String properties() {
        return "Properties of " + stringNumber + "\n" +
                "buzz: " + isBuzz() + "\n" +
                "duck: " + isDuck() + "\n" +
                "palindromic: " + isPalindromic() + "\n" +
                "gapful: " + isGapful() + "\n" +
                "spy: " + isSpy() + "\n" +
                "square: " + isSquare() + "\n" +
                "sunny: " + isSunny() + "\n" +
                "jumping: " + isJumping() + "\n" +
                "even: " + isEven() + "\n" +
                "odd: " + !isEven() + "\n" +
                "happy: " + isHappy() + "\n" +
                "sad: " + !isHappy();
    }

    public String describe() {
        return stringNumber + " is " +
                (isBuzz() ? "buzz, " : "") +
                (isDuck() ? "duck, " : "") +
                (isPalindromic() ? "palindrome, " : "") +
                (isGapful() ? "gapful, " : "") +
                (isSpy() ? "spy, " : "") +
                (isSquare() ? "square, " : "") +
                (isSunny() ? "sunny, " : "") +
                (isJumping() ? "jumping, " : "") +
                (isHappy() ? "happy, " : "sad, ") +
                (isEven() ? "even" : "odd");
    }
}
