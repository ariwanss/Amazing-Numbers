package numbers;

import java.util.*;
import java.util.stream.Collectors;

public class Application {

    private final Scanner scanner = new Scanner(System.in);

    public Application() {
        System.out.println("Welcome to Amazing Numbers!");
        System.out.println();
        System.out.println("Supported requests:");
        System.out.println("- enter a natural number to know its properties;");
        System.out.println("- enter two natural numbers to obtain the properties of the list:");
        System.out.println("  * the first parameter represents a starting number;");
        System.out.println("  * the second parameter shows how many consecutive numbers are to be processed;");
        System.out.println("- two natural numbers and properties to search for;");
        System.out.println("- a property preceded by minus must not be present in numbers;");
        System.out.println("- separate the parameters with one space;");
        System.out.println("- enter 0 to exit.");
    }

    public boolean run() {
        System.out.print("Enter a request: ");
        String response = scanner.nextLine().toUpperCase();
        if (response.matches("0")) {
             return mode0();
        } else if (response.matches("-?[0-9]+")) {
            return mode1(response);
        } else if (response.matches("-?[0-9]+\\s-?[0-9]+")) {
            return mode2(response);
        } else if (response.matches("-?[0-9]+\\s-?[0-9]+\\s.+")) {
            return mode3(response);
        }
        return false;
    }

    private boolean mode0() {
        System.out.println("Goodbye!");
        return false;
    }

    private boolean mode1(String response) {
        if (Long.parseLong(response) < 0) {
            System.out.println("The first parameter should be a natural number or zero");
            return true;
        }
        Number number = new Number(response, Set.of());
        System.out.println(number.properties());
        return true;
    }

    private boolean mode2(String response) {
        String[] responseList = response.split("\\s");
        long startNumber = Long.parseLong(responseList[0]);
        int howMany = Integer.parseInt(responseList[1]);
        if (startNumber < 0) {
            System.out.println("The first parameter should be a natural number or zero.");
            return true;
        }
        if (howMany < 0) {
            System.out.println("The second parameter should be a natural number or zero.");
            return true;
        }
        for (int i = 0; i < howMany; i++) {
            Number number = new Number(startNumber + i, Set.of());
            System.out.println(number.describe());
        }
        return true;
    }

    private boolean mode3(String response) {
        List<String> responseList = Arrays.stream(response.split("\\s")).collect(Collectors.toList());
        long startNumber = Long.parseLong(responseList.get(0));
        int howManyToPrint = Integer.parseInt(responseList.get(1));
        Set<String> propertiesToLook = new HashSet<>(responseList.subList(2, responseList.size()));

        Set<NumberProperty> numberPropertiesToLook = new HashSet<>();
        try {
            propertiesToLook.stream().map(NumberProperty::numberPropertyOf).forEach(numberPropertiesToLook::add);
        } catch (IllegalArgumentException E) {
            Set<String> wrongProperties = propertiesToLook.stream()
                    .filter(x -> !x.matches("-?(BUZZ|DUCK|PALINDROMIC|GAPFUL|SPY|EVEN|ODD|SQUARE|SUNNY|JUMPING)"))
                    .collect(Collectors.toSet());
            System.out.println("The " + (wrongProperties.size() > 1 ? "properties " : "property ") +
                    wrongProperties + (wrongProperties.size() > 1 ? " are " : " is ") + "wrong.");
            System.out.println("Available properties: " +
                    "[EVEN, ODD, BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY, JUMPING, HAPPY, SAD]");
            return true;
        }
        Set<String> propertiesThatMustExist = numberPropertiesToLook.stream().map(NumberProperty::getProperty)
                .collect(Collectors.toSet());
        Set<String> propertiesThatCannotExist = new HashSet<>();
        numberPropertiesToLook.stream().map(NumberProperty::getExclude).forEach(propertiesThatCannotExist::addAll);

        Set<String> intersection = new HashSet<>(propertiesThatMustExist);
        intersection.retainAll(propertiesThatCannotExist);
        if (!intersection.isEmpty()) {
            System.out.println("The request contains mutually exclusive properties: " +
                    intersection);
            System.out.println("There are no numbers with these properties.");
            return true;
        }
        if (startNumber < 0) {
            System.out.println("The first parameter should be a natural number or zero.");
            return true;
        }
        if (howManyToPrint < 0) {
            System.out.println("The second parameter should be a natural number or zero.");
            return true;
        }
        int i = 0;

        while (i < howManyToPrint) {
            Number number = new Number(startNumber++, numberPropertiesToLook);
            if (number.isWhatWeAreLookingFor()) {
                System.out.println(number.describe());
                i++;
            }
        }
        return true;
    }

}
