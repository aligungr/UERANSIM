/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.mts;

public enum ConversionLevel {
    LEVEL_NULL_CONVERSION(0),
    SAME_TYPE(1),
    ASSIGNABLE_TYPE(2),
    NUMERIC_CONVERSION(3);

    private final int level;

    ConversionLevel(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }
}
