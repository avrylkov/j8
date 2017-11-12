package my.company;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;


public class Main {

    private static final Name[] NAMES = new Name[]{
            new Name("Smith", "Sally", 100),
            new Name("Mith", "Gally", 120),
            new Name("Pith", "Bally", 150),
            new Name("Tith", "Aally", 125),
            new Name("Pith", "Sally", 160),
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


    public static int compareName(Name n1, Name n2, Comparator<Name> cmp) {
        return cmp.compare(n1, n2);
    }

    public static List<Name> findName(List<Name> names, Predicate<Name> predicate) {
        return names.stream().filter(predicate).collect(Collectors.toList());
    }


    public static void main(String[] args) {
        Name[] copy = Arrays.copyOf(NAMES, NAMES.length);
        //printNames("----- Исходный список ------", copy);

        List<Name> names = Arrays.asList(copy);
        names.sort((a, b) -> a.compareTo(b));
        //Arrays.sort(copy, (a, b) -> a.compareTo(b));
        printNames("----- Сортированный список ------", names);
        //
        Predicate<Name> p1 = n -> "Aally".equals(n.getLastName());
        UnaryOperator<Name> uo = n -> {
            return new Name(n.getFirstName().toUpperCase(), n.getLastName().toUpperCase(), 0);
        };
        UpperName<Name, String> myUo = n -> {
            return n.getFirstName().toUpperCase() + " " + n.getLastName().toUpperCase();
        };
        System.out.println("FunctionalInterface: " + myUo.upper(names.get(0)));
        //
        Comparator<Name> cmp2 = (c1, c2) -> c1.getLastName().compareTo(c2.getLastName());
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
                System.out.println("FunctionalInterface myUnaryOperator ok: " + myUo.upper(name));
                System.out.println("FunctionalInterface myUnaryOperator2 ok: " + myUo2.upper(name));
            }
        });
        //
        System.out.println("========== Stream test ===============");
        names.stream().filter(s -> s.getLastName().startsWith("S"))
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
        // count
        System.out.println(
                "count startWith S=" + names.stream().filter(f -> f.getLastName().startsWith("S")).count()
        );
        System.out.println(
                "sum startWith(\"S\")=" + names.stream().filter(f -> f.getLastName().startsWith("S")).mapToInt(Name::getSalary).sum()
        );

        //names.sort((n1,n2)-> n1.compareTo(n2));
        compareName(names.get(0), names.get(1), (n1, n2) -> n1.compareTo(n2));
        System.out.println(
                names.get(0).toString() + " " +
                        (compareName(names.get(0), names.get(1), Name::compareTo2) == 1 ? ">" : "<") + " " +
                        names.get(1).toString()

        );
        //
        List<Name> list = findName(names, n -> n.getSalary() == 150);
        System.out.println("find Name for salary=150 :" + Arrays.toString(list.toArray()));
        //
        list = findName(names, n -> n.getLastName() == "Sally");
        System.out.println("find Name for lasnName Sally:" + Arrays.toString(list.toArray()));

    }
}
