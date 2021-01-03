/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
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
