import java.util.*;

public class CollectionUtils {

    public static <T> void addElement(List<T> list, T element) {
        list.add(element);
    }

    public static <T> void removeElement(List<T> list, T element) {
        list.remove(element);
    }

    public static <T> Set<T> getUniqueElements(List<T> list) {
        return new HashSet<>(list);
    }
}
