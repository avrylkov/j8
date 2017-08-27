package my.company;

@FunctionalInterface
public interface UpperName<T, String> {
    String upper(T name);
}
