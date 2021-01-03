/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
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
