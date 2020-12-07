/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.utils;

public class Timing {
    private long lastAccepted;

    public Timing(boolean initWithCurrent) {
        lastAccepted = initWithCurrent ? System.currentTimeMillis() : 0;
    }

    public Timing() {
        this(false);
    }

    public boolean check(int period) {
        var current = System.currentTimeMillis();
        var delta = current - lastAccepted;
        if (delta >= period) {
            lastAccepted = current;
            return true;
        }
        return false;
    }
}
