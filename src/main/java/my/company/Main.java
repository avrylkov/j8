package my.company;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;


public class Main {

    private static final Name[] NAMES = new Name[]{
            new Name("Smith", "Sally"),
            new Name("Mith", "Gally"),
            new Name("Pith", "Bally"),
            new Name("Tith", "Aally"),
    };

    /**
     * Print names
     *
     * @param caption
     * @param names
     */
    private static void printNames(String caption, Name[] names) {
        System.out.println(caption);
        Arrays.asList(names).forEach(name -> System.out.println(name.toString()));
    }

    private static void printNames(String caption, List<Name> names) {
        System.out.println(caption);
        names.forEach(name -> System.out.println(name.toString()));
    }

    public static void main(String[] args) {
        Name[] copy = Arrays.copyOf(NAMES, NAMES.length);
        //printNames("----- Исходный список ------", copy);

        List<Name> names = Arrays.asList(copy);
        names.sort((a, b) -> a.compareTo(b));
        //Arrays.sort(copy, (a, b) -> a.compareTo(b));
        printNames("----- Сортированный список ------", names);
        //
        Predicate<Name> p1 = n -> "Aally".equals(n.lastName);
        UnaryOperator<Name> uo = n -> {
           return new Name(n.firstName.toUpperCase(),n.lastName.toUpperCase());
        };
        UpperName<Name, String> myUo = n -> {
           return n.firstName.toUpperCase() + " " + n.lastName.toUpperCase();
        };
        //
        names.forEach(name -> {
            if (p1.test(name)) {
                UpperName<Name, String> myUo2 = name::initials;
                System.out.println("Predicate ok: " + name);
                System.out.println("UnaryOperator ok: " + uo.apply(name));
                System.out.println("myUnaryOperator ok: " + myUo.upper(name));
                System.out.println("myUnaryOperator2 ok: " + myUo2.upper(name));
            }
        });
        //System.out.println(p1.test(copy[1]));
    }
}
