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

public class IE5gsRegistrationResult extends InformationElement4 {
    public ESmsOverNasTransportAllowed smsOverNasAllowed;
    public E5gsRegistrationResult registrationResult;

    public IE5gsRegistrationResult() {
    }

    public IE5gsRegistrationResult(ESmsOverNasTransportAllowed smsOverNasAllowed, E5gsRegistrationResult registrationResult) {
        this.smsOverNasAllowed = smsOverNasAllowed;
        this.registrationResult = registrationResult;
    }

    @Override
    protected InformationElement4 decodeIE4(OctetInputStream stream, int length) {
        int value = stream.readOctetI();

        var res = new IE5gsRegistrationResult();
        res.registrationResult = E5gsRegistrationResult.fromValue(value & 0b111);
        res.smsOverNasAllowed = ESmsOverNasTransportAllowed.fromValue(value >> 3 & 0b1);
        return res;
    }

    @Override
    public void encodeIE4(OctetOutputStream stream) {
        stream.writeOctet(smsOverNasAllowed.intValue() << 3 | registrationResult.intValue());
    }

    public static class ESmsOverNasTransportAllowed extends ProtocolEnum {
        public static final ESmsOverNasTransportAllowed NOT_ALLOWED
                = new ESmsOverNasTransportAllowed(0b0, "SMS over NAS not allowed");
        public static final ESmsOverNasTransportAllowed ALLOWED
                = new ESmsOverNasTransportAllowed(0b1, "SMS over NAS allowed");

        private ESmsOverNasTransportAllowed(int value, String name) {
            super(value, name);
        }

        public static ESmsOverNasTransportAllowed fromValue(int value) {
            return fromValueGeneric(ESmsOverNasTransportAllowed.class, value, null);
        }
    }

    public static class E5gsRegistrationResult extends ProtocolEnum {
        public static final E5gsRegistrationResult THREEGPP_ACCESS
                = new E5gsRegistrationResult(0b001, "3GPP access");
        public static final E5gsRegistrationResult NON_THREEGPP_ACCESS
                = new E5gsRegistrationResult(0b010, "Non-3GPP access");
        public static final E5gsRegistrationResult THREEGPP_ACCESS_AND_NON_THREEGPP_ACCESS
                = new E5gsRegistrationResult(0b011, "3GPP access and non-3GPP access");

        private E5gsRegistrationResult(int value, String name) {
            super(value, name);
        }

        public static E5gsRegistrationResult fromValue(int value) {
            return fromValueGeneric(E5gsRegistrationResult.class, value, null);
        }
    }
}
