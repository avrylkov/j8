package my.company;

public class Name {
    private final String firstName;
    private final String lastName;

    private final Integer salary;

    public Name() {
        super();
        firstName = null;
        lastName = null;
        salary = null;
    }

    public Name(String first, String last, Integer salary) {
        this.firstName = first;
        this.lastName = last;
        this.salary = salary;
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

    public static int compareTo2(Name n1, Name n2) {
        int diff = n1.lastName.compareTo(n2.lastName);
        if (diff == 0) {
            diff = n1.firstName.compareTo(n2.firstName);
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
    public Integer getSalary() {
        return salary;
    }

}