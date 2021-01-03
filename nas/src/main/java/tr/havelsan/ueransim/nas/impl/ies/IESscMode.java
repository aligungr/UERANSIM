/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.nas.impl.ies;

import tr.havelsan.ueransim.nas.core.ProtocolEnum;
import tr.havelsan.ueransim.nas.core.ies.InformationElement1;
import tr.havelsan.ueransim.utils.bits.Bit4;

public class IESscMode extends InformationElement1 {
    public ESscMode sscMode;

    public IESscMode() {
    }

    public IESscMode(ESscMode sscMode) {
        this.sscMode = sscMode;
    }

    @Override
    public IESscMode decodeIE1(Bit4 value) {
        var res = new IESscMode();
        res.sscMode = ESscMode.fromValue(value.intValue() & 0b111);
        return res;
    }

    @Override
    public int encodeIE1() {
        return sscMode.intValue();
    }

    public static class ESscMode extends ProtocolEnum {
        public static final ESscMode SSC_MODE_1
                = new ESscMode(0b001, "SSC mode 1");
        public static final ESscMode SSC_MODE_2
                = new ESscMode(0b010, "SSC mode 2");
        public static final ESscMode SSC_MODE_3
                = new ESscMode(0b011, "SSC mode 3");
        public static final ESscMode UNUSED_1
                = new ESscMode(0b100, "unused; shall be interpreted as \"SSC mode 1\", if received by the network");
        public static final ESscMode UNUSED_2
                = new ESscMode(0b101, "unused; shall be interpreted as \"SSC mode 2\", if received by the network");
        public static final ESscMode UNUSED_3
                = new ESscMode(0b110, "unused; shall be interpreted as \"SSC mode 3\", if received by the network");

        private ESscMode(int value, String name) {
            super(value, name);
        }

        public static ESscMode fromValue(int value) {
            var val = fromValueGeneric(ESscMode.class, value, null);
            if (val.equals(UNUSED_1)) return SSC_MODE_1;
            if (val.equals(UNUSED_2)) return SSC_MODE_2;
            if (val.equals(UNUSED_3)) return SSC_MODE_3;
            return val;
        }
    }
}
