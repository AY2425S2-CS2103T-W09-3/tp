package seedu.address.model.person;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import seedu.address.commons.util.ToStringBuilder;

/**
 * Represents a Visit of a particular Person.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Visit {
    // Visit details: who, when, why
    public final Person person;
    public final DateTime dateTime;
    public final Remark remark;

    /**
     * Every parameter must present and be non-null.
     * @param person
     * @param dateTime
     * @param remark
     */
    Visit(Person person, DateTime dateTime, Remark remark) {
        requireAllNonNull(person, dateTime, remark);
        this.person = person;
        this.dateTime = dateTime;
        this.remark = remark;
    }

    public Person getPerson() {
        return this.person;
    }

    public Remark getRemark() {
        return this.remark;
    }

    /**
     * Returns true if and only if the other object is an instance of Visit
     * and the other visit has same visit details.
     */
    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        } else if (object instanceof Visit other) {
            return other.person.equals(this.person)
                    && other.dateTime.equals(this.dateTime)
                    && other.remark.equals(this.remark);
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("person", person)
                .add("visit time", dateTime)
                .add("remark", remark)
                .toString();
    }
}
