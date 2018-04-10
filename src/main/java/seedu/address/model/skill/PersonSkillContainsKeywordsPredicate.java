package seedu.address.model.skill;

import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.person.Person;
//@@author KevinCJH
/**
 * Tests that a {@code Person}'s {@code Skill} matches any of the keywords given.
 */
public class PersonSkillContainsKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;

    public PersonSkillContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        Iterator tagsIterator = person.getSkills().iterator();
        StringBuilder sb = new StringBuilder();
        sb.append(tagsIterator.next());
        while (tagsIterator.hasNext()) {
            sb.append(" " + tagsIterator.next());
        }
        String tagLists = sb.toString()
                .replace("[", "")
                .replace("]", "");
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(tagLists, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PersonSkillContainsKeywordsPredicate // instanceof handles nulls
                && this.keywords.equals(((PersonSkillContainsKeywordsPredicate) other).keywords)); // state check
    }

}