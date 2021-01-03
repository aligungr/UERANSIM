/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.mts;

import java.math.BigDecimal;
import java.math.BigInteger;

public class NumberInfo {
    private final BigDecimal bd;

    public NumberInfo(Number number) {
        if (number == null)
            throw new IllegalArgumentException();
        this.bd = new BigDecimal(number.toString());
    }

    public NumberInfo(String number) {
        if (number == null)
            throw new IllegalArgumentException();
        this.bd = new BigDecimal(number);
    }

    public boolean isWholeNumber() {
        try {
            var v = bd.toBigIntegerExact();
        } catch (Exception ignored) {
            return false;
        }
        return true;
    }

    public boolean isFractionalNumber() {
        return !isWholeNumber();
    }

    public boolean isLong() {
        try {
            var v = bd.longValueExact();
        } catch (Exception ignored) {
            return false;
        }
        return true;
    }

    public boolean isInt() {
        try {
            var v = bd.intValueExact();
        } catch (Exception ignored) {
            return false;
        }
        return true;
    }

    public boolean isShort() {
        try {
            var v = bd.shortValueExact();
        } catch (Exception ignored) {
            return false;
        }
        return true;
    }

    public boolean isByte() {
        try {
            var v = bd.byteValueExact();
        } catch (Exception ignored) {
            return false;
        }
        return true;
    }

    public boolean isDouble() {
        return new BigDecimal(Double.toString(bd.doubleValue())).equals(bd);
    }

    public boolean isFloat() {
        return isDouble() && (bd.floatValue() == bd.doubleValue());
    }

    public byte byteValue() {
        return bd.byteValue();
    }

    public short shortValue() {
        return bd.shortValue();
    }

    public int intValue() {
        return bd.intValue();
    }

    public long longValue() {
        return bd.longValue();
    }

    public float floatValue() {
        return bd.floatValue();
    }

    public double doubleValue() {
        return bd.doubleValue();
    }

    public BigInteger bigIntegerValue() {
        return bd.toBigInteger();
    }

    public BigDecimal bigDecimalValue() {
        return bd;
    }

    public String stringValue() {
        return toString();
    }

    @Override
    public String toString() {
        return bd.toString();
    }
}
