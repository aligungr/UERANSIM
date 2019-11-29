package com.runsim.backend.mts;

public enum ConversionLevel {
    LEVEL_NO_CONVERSION(-2),
    LEVEL_NULL_CONVERSION(-1),
    SAME_TYPE(0),
    ASSIGNABLE_TYPE(1),
    NUMERIC_CONVERSION(2);

    private final int level;

    ConversionLevel(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }
}
