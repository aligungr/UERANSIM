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

public class IEUeStatus extends InformationElement4 {
    public EEmmRegistrationStatus s1ModeReg;
    public E5gMmRegistrationStatus n1ModeReg;

    public IEUeStatus() {
    }

    public IEUeStatus(EEmmRegistrationStatus s1ModeReg, E5gMmRegistrationStatus n1ModeReg) {
        this.s1ModeReg = s1ModeReg;
        this.n1ModeReg = n1ModeReg;
    }

    @Override
    protected IEUeStatus decodeIE4(OctetInputStream stream, int length) {
        int octet = stream.readOctetI();

        var res = new IEUeStatus();
        res.s1ModeReg = EEmmRegistrationStatus.fromValue(octet & 0b1);
        res.n1ModeReg = E5gMmRegistrationStatus.fromValue(octet >> 1 & 0b1);
        return res;
    }

    @Override
    public void encodeIE4(OctetOutputStream stream) {
        int octet = s1ModeReg.intValue() | (n1ModeReg.intValue() << 1);
        stream.writeOctet(octet);
    }

    public static class E5gMmRegistrationStatus extends ProtocolEnum {
        public static final E5gMmRegistrationStatus NOT_REGISTERED
                = new E5gMmRegistrationStatus(0b0, "UE is not in 5GMM-REGISTERED state");
        public static final E5gMmRegistrationStatus REGISTERED
                = new E5gMmRegistrationStatus(0b1, "UE is in 5GMM-REGISTERED state");

        private E5gMmRegistrationStatus(int value, String name) {
            super(value, name);
        }

        public static E5gMmRegistrationStatus fromValue(int value) {
            return fromValueGeneric(E5gMmRegistrationStatus.class, value, null);
        }
    }

    public static class EEmmRegistrationStatus extends ProtocolEnum {
        public static final EEmmRegistrationStatus NOT_REGISTERED
                = new EEmmRegistrationStatus(0b0, "UE is not in EMM-REGISTERED state");
        public static final EEmmRegistrationStatus REGISTERED
                = new EEmmRegistrationStatus(0b1, "UE is in EMM-REGISTERED state");

        private EEmmRegistrationStatus(int value, String name) {
            super(value, name);
        }

        public static EEmmRegistrationStatus fromValue(int value) {
            return fromValueGeneric(EEmmRegistrationStatus.class, value, null);
        }
    }
}
