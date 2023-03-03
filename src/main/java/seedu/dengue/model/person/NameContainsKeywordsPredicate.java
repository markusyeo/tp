package seedu.dengue.model.person;

import java.util.List;
import java.util.function.Predicate;

import seedu.dengue.commons.util.StringUtil;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class NameContainsKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;

    public NameContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        Predicate<String> containsName =
                keyword -> StringUtil.containsSubstringIgnoreCase(person.getName().fullName, keyword);
        Predicate<String> containsPostal =
                keynum -> StringUtil.startsWithSubstringIgnoreCase(person.getPostal().value, keynum);
        Predicate<String> containsPostalOrName = containsPostal.or(containsName);
        return keywords.stream()
                .anyMatch(containsPostalOrName);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NameContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((NameContainsKeywordsPredicate) other).keywords)); // state check
    }
}