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
import tr.havelsan.ueransim.utils.bits.Bit3;
import tr.havelsan.ueransim.utils.bits.Bit4;

public class IEAllowedSscMode extends InformationElement1 {
    public ESsc1 ssc1;
    public ESsc2 ssc2;
    public ESsc3 ssc3;

    public IEAllowedSscMode() {
    }

    public IEAllowedSscMode(ESsc1 ssc1, ESsc2 ssc2, ESsc3 ssc3) {
        this.ssc1 = ssc1;
        this.ssc2 = ssc2;
        this.ssc3 = ssc3;
    }

    @Override
    public IEAllowedSscMode decodeIE1(Bit4 value) {
        var res = new IEAllowedSscMode();
        res.ssc1 = ESsc1.fromValue(value.getBitI(0));
        res.ssc2 = ESsc2.fromValue(value.getBitI(1));
        res.ssc3 = ESsc3.fromValue(value.getBitI(2));
        return res;
    }

    @Override
    public int encodeIE1() {
        return new Bit3(ssc1.intValue(), ssc2.intValue(), ssc3.intValue()).intValue();
    }

    public static class ESsc1 extends ProtocolEnum {
        public static final ESsc1 NOT_ALLOWED
                = new ESsc1(0b0, "SSC mode 1 not allowed");
        public static final ESsc1 ALLOWED
                = new ESsc1(0b1, "SSC mode 1 allowed");

        private ESsc1(int value, String name) {
            super(value, name);
        }

        public static ESsc1 fromValue(int value) {
            return fromValueGeneric(ESsc1.class, value, null);
        }
    }

    public static class ESsc2 extends ProtocolEnum {
        public static final ESsc2 NOT_ALLOWED
                = new ESsc2(0b0, "SSC mode 2 not allowed");
        public static final ESsc2 ALLOWED
                = new ESsc2(0b1, "SSC mode 2 allowed");

        private ESsc2(int value, String name) {
            super(value, name);
        }

        public static ESsc2 fromValue(int value) {
            return fromValueGeneric(ESsc2.class, value, null);
        }
    }

    public static class ESsc3 extends ProtocolEnum {
        public static final ESsc3 NOT_ALLOWED
                = new ESsc3(0b0, "SSC mode 3 not allowed");
        public static final ESsc3 ALLOWED
                = new ESsc3(0b1, "SSC mode 3 allowed");

        private ESsc3(int value, String name) {
            super(value, name);
        }

        public static ESsc3 fromValue(int value) {
            return fromValueGeneric(ESsc3.class, value, null);
        }
    }
}
