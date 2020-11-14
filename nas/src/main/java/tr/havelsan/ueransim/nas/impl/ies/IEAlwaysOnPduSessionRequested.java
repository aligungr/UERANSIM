/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.nas.impl.ies;

import tr.havelsan.ueransim.nas.core.ProtocolEnum;
import tr.havelsan.ueransim.nas.core.ies.InformationElement1;
import tr.havelsan.ueransim.utils.bits.Bit4;

public class IEAlwaysOnPduSessionRequested extends InformationElement1 {
    public EAlwaysOnPduSessionRequested aprs;

    public IEAlwaysOnPduSessionRequested() {
    }

    public IEAlwaysOnPduSessionRequested(EAlwaysOnPduSessionRequested aprs) {
        this.aprs = aprs;
    }

    @Override
    public InformationElement1 decodeIE1(Bit4 value) {
        var res = new IEAlwaysOnPduSessionRequested();
        res.aprs = EAlwaysOnPduSessionRequested.fromValue(value.getBitI(0));
        return res;
    }

    @Override
    public int encodeIE1() {
        return aprs.intValue();
    }

    public static class EAlwaysOnPduSessionRequested extends ProtocolEnum {
        public static final EAlwaysOnPduSessionRequested NOT_REQUESTED
                = new EAlwaysOnPduSessionRequested(0b0, "Always-on PDU session not requested");
        public static final EAlwaysOnPduSessionRequested REQUESTED
                = new EAlwaysOnPduSessionRequested(0b1, "Always-on PDU session requested");

        private EAlwaysOnPduSessionRequested(int value, String name) {
            super(value, name);
        }

        public static EAlwaysOnPduSessionRequested fromValue(int value) {
            return fromValueGeneric(EAlwaysOnPduSessionRequested.class, value, null);
        }
    }
}
