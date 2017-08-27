package my.company;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;


public class Main {

    private static final Name[] NAMES = new Name[]{
            new Name("Smith", "Sally"),
            new Name("Mith", "Gally"),
            new Name("Pith", "Bally"),
            new Name("Tith", "Aally"),
            new Name("Pith", "Sally"),
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
            return new Name(n.firstName.toUpperCase(), n.lastName.toUpperCase());
        };
        UpperName<Name, String> myUo = n -> {
            return n.firstName.toUpperCase() + " " + n.lastName.toUpperCase();
        };
        //
        Comparator<Name> cmp2 = (c1, c2) -> c1.lastName.compareTo(c2.lastName);
        System.out.println(cmp2.compare(NAMES[0], NAMES[4]) == 0 ? "n[0]==n[4]" : "n[0]<>n[4]");
        //
        Number num = null;
        Optional<Number> optional = Optional.ofNullable(num);
        System.out.println("num is null = " + optional.orElse(0));
        //
        UpperName<Name, String> myUo2 = Name::initials;
        names.forEach(name -> {
            if (p1.test(name)) {
                System.out.println("Predicate ok: " + name);
                System.out.println("UnaryOperator ok: " + uo.apply(name));
                System.out.println("myUnaryOperator ok: " + myUo.upper(name));
                System.out.println("myUnaryOperator2 ok: " + myUo2.upper(name));
            }
        });
        //
        System.out.println("========== Stream test ===============");
        names.stream().filter(s -> s.lastName.startsWith("S"))
                .forEach(n -> System.out.println(n));
        //
        System.out.println("========== Predicat test ===============");
        names.stream().filter(p1)
                .forEach(n -> System.out.println(n));
        System.out.println("========== Sorted test ===============");
        names.stream().sorted((c1, c2) -> c1.compareTo(c2))
                .forEach(n -> System.out.println(n));
        System.out.println("========== Map test ===============");
        //Function<Name, String> fun = f -> f.initials(f);
        names.stream().map(f -> f.initials(f))
                .forEach(n -> System.out.println(n));
        System.out.println("========== Map test 2 ===============");
        names.stream().map(Name::initials)
                .forEach(n -> System.out.println(n));


    }
}
