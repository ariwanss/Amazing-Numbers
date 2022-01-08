package numbers;

import java.util.Arrays;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public enum NumberProperty {
    EVEN("EVEN", Set.of("ODD", "-EVEN")),
    ODD("ODD", Set.of("EVEN", "-ODD")),
    BUZZ("BUZZ", Set.of()),
    DUCK("DUCK", Set.of("SPY", "-DUCK")),
    PALINDROMIC("PALINDROMIC", Set.of()),
    GAPFUL("GAPFUL", Set.of()),
    SPY("SPY", Set.of("DUCK", "-SPY")),
    SQUARE("SQUARE", Set.of("SUNNY", "-SQUARE")),
    SUNNY("SUNNY", Set.of("SQUARE", "-SUNNY")),
    JUMPING("JUMPING", Set.of()),
    HAPPY("HAPPY", Set.of("SAD", "-HAPPY")),
    SAD("SAD", Set.of("HAPPY", "-SAD")),
    NOT_EVEN("-EVEN", Set.of("-ODD", "EVEN")),
    NOT_ODD("-ODD", Set.of("-EVEN", "ODD")),
    NOT_BUZZ("-BUZZ", Set.of()),
    NOT_DUCK("-DUCK", Set.of("-SPY", "DUCK")),
    NOT_PALINDROMIC("-PALINDROMIC", Set.of()),
    NOT_GAPFUL("-GAPFUL", Set.of()),
    NOT_SPY("-SPY", Set.of("-DUCK", "SPY")),
    NOT_SUNNY("-SUNNY", Set.of("-SQUARE", "SUNNY")),
    NOT_SQUARE("-SQUARE", Set.of("-SUNNY", "SQUARE")),
    NOT_JUMPING("-JUMPING", Set.of()),
    NOT_HAPPY("-HAPPY", Set.of("-SAD", "HAPPY")),
    NOT_SAD("-SAD", Set.of("-HAPPY", "SAD"));

    String property;
    Set<String> exclude;

    NumberProperty(String property, Set<String> exclude) {
        this.property = property;
        this.exclude = exclude;
    }

    public String getProperty() {
        return property;
    }

    public Set<String> getExclude() {
        return exclude;
    }

    public static NumberProperty numberPropertyOf(String property) {
        for (NumberProperty value : NumberProperty.values()) {
            if (property.equals(value.property)) {
                return value;
            }
        }
        throw new IllegalArgumentException();
    }
}
