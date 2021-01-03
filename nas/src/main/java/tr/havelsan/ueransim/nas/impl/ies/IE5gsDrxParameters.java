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

public class IE5gsDrxParameters extends InformationElement4 {
    public EDrxValue drxValue;

    public IE5gsDrxParameters() {
    }

    public IE5gsDrxParameters(EDrxValue drxValue) {
        this.drxValue = drxValue;
    }

    @Override
    protected IE5gsDrxParameters decodeIE4(OctetInputStream stream, int length) {
        var res = new IE5gsDrxParameters();
        res.drxValue = EDrxValue.fromValue(stream.readOctetI() & 0xF);
        return res;
    }

    @Override
    public void encodeIE4(OctetOutputStream stream) {
        stream.writeOctet(drxValue.intValue());
    }

    public static class EDrxValue extends ProtocolEnum {
        public static final EDrxValue NOT_SPECIFIED
                = new EDrxValue(0b0000, "DRX value not specified");
        public static final EDrxValue T32
                = new EDrxValue(0b0001, "DRX cycle parameter T = 32");
        public static final EDrxValue T64
                = new EDrxValue(0b0010, "DRX cycle parameter T = 64");
        public static final EDrxValue T128
                = new EDrxValue(0b0011, "DRX cycle parameter T = 128");
        public static final EDrxValue T256
                = new EDrxValue(0b0100, "DRX cycle parameter T = 256");

        private EDrxValue(int value, String name) {
            super(value, name);
        }

        public static EDrxValue fromValue(int value) {
            return fromValueGeneric(EDrxValue.class, value, null);
        }
    }
}
