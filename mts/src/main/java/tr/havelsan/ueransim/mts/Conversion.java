/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.mts;

import java.util.Objects;

public class Conversion<T> {
    public final ConversionLevel level;
    public final T value;
    public final int depth;

    public Conversion(ConversionLevel level, T value, int depth) {
        this.level = level;
        this.value = value;
        this.depth = depth;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Conversion<?> that = (Conversion<?>) o;
        return level == that.level &&
                Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(level, value);
    }
}
