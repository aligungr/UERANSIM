package tr.havelsan.ueransim.utils;

import java.util.function.Consumer;

public interface Fun {
    void run();

    default boolean run(Consumer<Exception> onError) {
        try {
            run();
            return true;
        } catch (Exception e) {
            if (onError != null)
                onError.accept(e);
            return false;
        }
    }
}
