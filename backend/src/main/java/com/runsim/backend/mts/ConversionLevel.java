package com.runsim.backend.mts;

public enum ConversionLevel {
    LEVEL_NO_CONVERSION(-1),
    LEVEL_NULL_CONVERSION(0),
    LEVEL_EXACT_MATCH(1),
    LEVEL_ASSIGNABLE(2),
    LEVEL_NUMBER_CONVERT(3),
    LEVEL_DEEP_CONVERT(4);

    private final int level;

    ConversionLevel(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }
}
