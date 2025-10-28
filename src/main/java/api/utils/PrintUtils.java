package api.utils;

import java.util.List;

public class PrintUtils {
    public static <T> void printList(List<T> list) {
        for (T item : list) {
            System.out.println(item);
        }
    }
}
