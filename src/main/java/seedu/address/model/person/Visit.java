package seedu.address.model.person;
import seedu.address.commons.util.ToStringBuilder;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

public class Visit {
    public final Person person ;
    public final DateTime dateTime;
    public final Remark remark;

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
