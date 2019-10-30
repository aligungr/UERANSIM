package com.runsim.backend.nas.core;

public abstract class NASValue {

    protected final static String INVALID_DATA = "Reserved or invalid data";

    public abstract void encode(BitOutputStream stream);

    public abstract void decode(BitInputStream stream);

    public abstract String display();

    @Override
    public final String toString() {
        return toString(0);
    }

    private String toString(int depth) {
        var sb = new StringBuilder();
        sb.append(" ".repeat(depth));
        sb.append("-");
        sb.append(display());
        sb.append('\n');

        var clazz = getClass();
        var allFields = clazz.getDeclaredFields();
        for (var field : allFields) {
            if (NASValue.class.isAssignableFrom(field.getType())) {
                field.setAccessible(true);
                try {
                    var value = (NASValue) field.get(this);
                    if (value != null)
                        sb.append(value.toString(depth + 1));
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return sb.toString();
    }

}
