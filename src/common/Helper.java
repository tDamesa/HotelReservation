package common;

import java.util.Collection;

/**
 * @author Tigist
 */
public class Helper {
    public static <T> void printGrid(Collection<T> collection, String records) {
        if (collection.isEmpty()) {
            System.out.println("No " + records + " found");
        }
        collection.forEach(System.out::println);
    }
}
