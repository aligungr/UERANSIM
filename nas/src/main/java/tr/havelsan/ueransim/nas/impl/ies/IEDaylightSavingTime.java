/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.nas.impl.ies;

import tr.havelsan.ueransim.nas.core.ProtocolEnum;
import tr.havelsan.ueransim.nas.core.ies.InformationElement4;
import tr.havelsan.ueransim.utils.OctetInputStream;
import tr.havelsan.ueransim.utils.OctetOutputStream;

public class IEDaylightSavingTime extends InformationElement4 {
    public EDaylightSavingTime daylightSavingTime;

    public IEDaylightSavingTime() {
    }

    public IEDaylightSavingTime(EDaylightSavingTime daylightSavingTime) {
        this.daylightSavingTime = daylightSavingTime;
    }

    @Override
    protected IEDaylightSavingTime decodeIE4(OctetInputStream stream, int length) {
        var res = new IEDaylightSavingTime();
        res.daylightSavingTime = EDaylightSavingTime.fromValue(stream.readOctetI() & 0b11);
        return res;
    }

    @Override
    public void encodeIE4(OctetOutputStream stream) {
        stream.writeOctet(daylightSavingTime.intValue());
    }

    public static class EDaylightSavingTime extends ProtocolEnum {
        public static final EDaylightSavingTime NO_ADJUSTMENT
                = new EDaylightSavingTime(0b00, "No adjustment for Daylight Saving Time");
        public static final EDaylightSavingTime PLUS_ONE
                = new EDaylightSavingTime(0b01, "+1 hour adjustment for Daylight Saving Time");
        public static final EDaylightSavingTime PLUS_TWO
                = new EDaylightSavingTime(0b10, "+2 hours adjustment for Daylight Saving Time");

        private EDaylightSavingTime(int value, String name) {
            super(value, name);
        }

        public static EDaylightSavingTime fromValue(int value) {
            return fromValueGeneric(EDaylightSavingTime.class, value, null);
        }
    }
}
