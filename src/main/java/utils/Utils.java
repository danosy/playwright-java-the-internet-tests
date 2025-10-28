package utils;

import java.util.function.Predicate;
import java.util.function.Supplier;

public class Utils {
    public static <T> T poll(Supplier<T> supplier, Predicate<T> isReady, int timeoutMillis, int pollIntervalMs) throws InterruptedException {
        int waited = 0;
        while (waited < timeoutMillis) {
            T result = supplier.get();
            if (isReady.test(result)) {
                return result;
            }
            Thread.sleep(pollIntervalMs);
            waited += pollIntervalMs;
        }
        return null; // or throw
    }
}
