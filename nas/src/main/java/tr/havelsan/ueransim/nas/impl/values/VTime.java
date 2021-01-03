/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.nas.impl.values;

import tr.havelsan.ueransim.nas.core.NasValue;
import tr.havelsan.ueransim.utils.OctetInputStream;
import tr.havelsan.ueransim.utils.OctetOutputStream;
import tr.havelsan.ueransim.utils.Utils;
import tr.havelsan.ueransim.utils.exceptions.ReservedOrInvalidValueException;
import tr.havelsan.ueransim.utils.octets.Octet;

public class VTime extends NasValue {
    public Octet year;
    public Octet month;
    public Octet day;
    public Octet hour;
    public Octet minute;
    public Octet second;

    public VTime() {
    }

    public VTime(Octet year, Octet month, Octet day, Octet hour, Octet minute, Octet second) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }

    public VTime(String hex) {
        var bytes = Utils.hexStringToByteArray(Utils.padLeft(hex, 12, '0'));
        this.year = new Octet(bytes[0] & 0xFF);
        this.month = new Octet(bytes[1] & 0xFF);
        this.day = new Octet(bytes[2] & 0xFF);
        this.hour = new Octet(bytes[3] & 0xFF);
        this.minute = new Octet(bytes[4] & 0xFF);
        this.second = new Octet(bytes[5] & 0xFF);
    }

    public VTime(Integer year, Integer month, Integer day, Integer hour, Integer minute, Integer second) {
        if (year == null) year = 0;
        if (month == null) month = 0;
        if (day == null) day = 0;
        if (hour == null) hour = 0;
        if (minute == null) minute = 0;
        if (second == null) second = 0;
        this.year = new Octet(year);
        this.month = new Octet(month);
        this.day = new Octet(day);
        this.hour = new Octet(hour);
        this.minute = new Octet(minute);
        this.second = new Octet(second);
    }

    @Override
    public VTime decode(OctetInputStream stream) {
        return fromOctets(stream.readOctet(), stream.readOctet(), stream.readOctet(), stream.readOctet(), stream.readOctet(), stream.readOctet());
    }

    public static VTime fromOctets(Octet year, Octet month, Octet day, Octet hour, Octet minute, Octet second) {
        return fromOctets(year.intValue(), month.intValue(), day.intValue(), hour.intValue(), minute.intValue(), second.intValue());
    }

    public static VTime fromOctets(int year, int month, int day, int hour, int minute, int second) {
        if (toInt(new Octet(year)) > 99)
            throw new ReservedOrInvalidValueException("invalid year: " + toInt(new Octet(year)));
        if (toInt(new Octet(month)) > 12)
            throw new ReservedOrInvalidValueException("invalid month: " + toInt(new Octet(month)));
        if (toInt(new Octet(day)) > 31)
            throw new ReservedOrInvalidValueException("invalid day: " + toInt(new Octet(day)));
        if (toInt(new Octet(hour)) > 23)
            throw new ReservedOrInvalidValueException("invalid hour: " + toInt(new Octet(hour)));
        if (toInt(new Octet(minute)) > 59)
            throw new ReservedOrInvalidValueException("invalid minute: " + toInt(new Octet(minute)));
        if (toInt(new Octet(second)) > 59)
            throw new ReservedOrInvalidValueException("invalid second: " + toInt(new Octet(second)));

        var res = new VTime();
        res.year = new Octet(year);
        res.month = new Octet(month);
        res.day = new Octet(day);
        res.hour = new Octet(hour);
        res.minute = new Octet(minute);
        res.second = new Octet(second);
        return res;
    }

    public static VTime fromTime(int year, int month, int day, int hour, int minute, int second) {
        return fromOctets(fromInt(year), fromInt(month), fromInt(day), fromInt(hour), fromInt(minute), fromInt(second));
    }

    private static int toInt(Octet octet) {
        // (They are swapped nibble)
        int nibble1 = octet.getBitRangeI(0, 3);
        int nibble0 = octet.getBitRangeI(4, 7);
        return nibble1 * 10 + nibble0;
    }

    private static Octet fromInt(int value) {
        int nibble0 = value % 10;
        int nibble1 = value / 10;

        var octet = new Octet();
        octet = octet.setBitRange(0, 3, nibble1);
        octet = octet.setBitRange(4, 7, nibble0);
        return octet;
    }

    @Override
    public void encode(OctetOutputStream stream) {
        stream.writeOctet(year);
        stream.writeOctet(month);
        stream.writeOctet(day);
        stream.writeOctet(hour);
        stream.writeOctet(minute);
        stream.writeOctet(second);
    }

    @Override
    public String toString() {
        // Normally year refers to only last to digit of the actual year.
        // But, we make this more intuitive herein.
        int year = toInt(this.year);
        year = 2000 + year;

        int month = toInt(this.month);
        int day = toInt(this.day);
        int hour = toInt(this.hour);
        int minute = toInt(this.minute);
        int second = toInt(this.second);

        return year +
                "-" +
                Utils.padLeft(month + "", 2, '0') +
                "-" +
                Utils.padLeft(day + "", 2, '0') +
                " " +
                Utils.padLeft(hour + "", 2, '0') +
                ":" +
                Utils.padLeft(minute + "", 2, '0') +
                ":" +
                Utils.padLeft(second + "", 2, '0');
    }
}
