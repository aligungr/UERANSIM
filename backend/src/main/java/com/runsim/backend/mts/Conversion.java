package com.runsim.backend.mts;

import java.util.Objects;

public class Conversion<T> {
    public final ConversionLevel level;
    public final T value;

    public Conversion(ConversionLevel level, T value) {
        this.level = level;
        this.value = value;
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
