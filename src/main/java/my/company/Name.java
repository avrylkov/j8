package my.company;

public class Name {
    public final String firstName;
    public final String lastName;

    public Name() {
        super();
        firstName = null;
        lastName = null;
    }

    public Name(String first, String last) {
        firstName = first;
        lastName = last;
    }

    //  необходимо только для связанного в цепочку компаратора (chained comparator)
    public String getFirstName() {
        return firstName;
    }

    // необходимо только для chained comparator
    public String getLastName() {
        return lastName;
    }

    // необходимо только для direct comparator (а не для chained comparator)
    public int compareTo(Name other) {
        int diff = lastName.compareTo(other.lastName);
        if (diff == 0) {
            diff = firstName.compareTo(other.firstName);
        }
        return diff;
    }

    public static String initials(Name name) {
        return name.lastName.charAt(0) + "." + name.firstName.charAt(0) + ".";
    }

    @Override
    public String toString() {
        return lastName + " " + firstName;
    }
    //...
}