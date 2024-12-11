package christmas.utils;

import christmas.view.OutputViewer;
import java.util.function.Function;
import java.util.function.Supplier;

public class RecoveryUtils {

    private static final OutputViewer viewer = new OutputViewer();

    private RecoveryUtils() {
    }

    public static <T, R> R executeWithRetry(Supplier<T> inputSupplier, Function<T, R> processFunction) {
        while (true) {
            try {
                return processFunction.apply(inputSupplier.get());
            } catch (IllegalArgumentException e) {
                viewer.printError(e);
            }
        }
    }


}