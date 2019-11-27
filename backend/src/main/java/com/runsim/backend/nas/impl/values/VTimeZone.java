package com.runsim.backend.nas.impl.values;

import com.runsim.backend.exceptions.ReservedOrInvalidValueException;
import com.runsim.backend.nas.core.NasValue;
import com.runsim.backend.utils.OctetInputStream;
import com.runsim.backend.utils.OctetOutputStream;
import com.runsim.backend.utils.octets.Octet;

public class VTimeZone extends NasValue {
    public Octet rawOctet;

    public static VTimeZone decode(OctetInputStream stream) {
        return fromOctet(stream.readOctet());
    }

    public static VTimeZone fromOctet(Octet octet) {
        valueControl(octet);
        var res = new VTimeZone();
        res.rawOctet = octet;
        return res;
    }

    public static VTimeZone fromOctet(int octet) {
        return fromOctet(new Octet(octet));
    }

    public static VTimeZone fromHourDifference(int difference) {
        return fromQuarterDifference(difference * 4);
    }

    public static VTimeZone fromQuarterDifference(int difference) {
        return fromDifference(difference < 0, Math.abs(difference));
    }

    public static VTimeZone fromDifference(boolean isNegative, int difference) {
        var octet = new Octet();
        octet = octet.setBit(7, isNegative ? 1 : 0);

        int nibble0 = difference % 10;
        int nibble1 = difference / 10;
        if (nibble1 > 7)
            throw new ReservedOrInvalidValueException("invalid difference at timezone value: " + difference);

        octet = octet.setBitRange(0, 3, nibble1);
        octet = octet.setBitRange(4, 6, nibble0);

        var res = new VTimeZone();
        res.rawOctet = octet;
        return res;
    }

    private static void valueControl(Octet rawOctet) {
        int nibble1 = rawOctet.getBitRangeI(0, 3);
        int nibble0 = rawOctet.getBitRangeI(4, 6);

        if (nibble0 > 9) throw new ReservedOrInvalidValueException("invalid timezone value: " + rawOctet.toHexString());
        if (nibble1 > 7) throw new ReservedOrInvalidValueException("invalid timezone value: " + rawOctet.toHexString());
    }

    public int getSign() {
        return rawOctet.getBitI(7) == 0 ? 1 : -1;
    }

    public int getDifferenceInQuarters() {
        // (They are swapped nibble)
        int nibble1 = rawOctet.getBitRangeI(0, 3);
        int nibble0 = rawOctet.getBitRangeI(4, 6);
        return nibble1 * 10 + nibble0;
    }

    @Override
    public void encode(OctetOutputStream stream) {
        stream.writeOctet(rawOctet);
    }

    @Override
    public String toString() {
        int quarters = getDifferenceInQuarters();
        int whole = quarters / 4;
        int remainder = quarters % 4;

        var diffHour = whole + "";
        if (remainder == 1) diffHour += ":15";
        else if (remainder == 2) diffHour += ":30";
        else if (remainder == 3) diffHour += ":45";

        return "GMT " + (getSign() == 1 ? "+" : "-") + diffHour;
    }
}
