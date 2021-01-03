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

public class IEAlwaysOnPduSessionIndication extends InformationElement1 {
    public EAlwaysOnPduSessionIndication apsi;

    public IEAlwaysOnPduSessionIndication() {
    }

    public IEAlwaysOnPduSessionIndication(EAlwaysOnPduSessionIndication apsi) {
        this.apsi = apsi;
    }

    @Override
    public IEAlwaysOnPduSessionIndication decodeIE1(Bit4 value) {
        var res = new IEAlwaysOnPduSessionIndication();
        res.apsi = EAlwaysOnPduSessionIndication.fromValue(value.getBitI(0));
        return res;
    }

    @Override
    public int encodeIE1() {
        return apsi.intValue();
    }

    public static class EAlwaysOnPduSessionIndication extends ProtocolEnum {
        public static final EAlwaysOnPduSessionIndication NOT_ALLOWED
                = new EAlwaysOnPduSessionIndication(0b0, "Always-on PDU session not allowed");
        public static final EAlwaysOnPduSessionIndication REQUIRED
                = new EAlwaysOnPduSessionIndication(0b1, "Always-on PDU session required");

        private EAlwaysOnPduSessionIndication(int value, String name) {
            super(value, name);
        }

        public static EAlwaysOnPduSessionIndication fromValue(int value) {
            return fromValueGeneric(EAlwaysOnPduSessionIndication.class, value, null);
        }
    }
}
