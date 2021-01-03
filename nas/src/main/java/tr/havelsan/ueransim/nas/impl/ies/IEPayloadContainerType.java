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

public class IEPayloadContainerType extends InformationElement1 {
    public EPayloadContainerType payloadContainerType;

    public IEPayloadContainerType() {
    }

    public IEPayloadContainerType(EPayloadContainerType payloadContainerType) {
        this.payloadContainerType = payloadContainerType;
    }

    @Override
    public IEPayloadContainerType decodeIE1(Bit4 value) {
        var res = new IEPayloadContainerType();
        res.payloadContainerType = EPayloadContainerType.fromValue(value.intValue() & 0b1111);
        return res;
    }

    @Override
    public int encodeIE1() {
        return payloadContainerType.intValue();
    }

    public static class EPayloadContainerType extends ProtocolEnum {
        public static final EPayloadContainerType N1_SM_INFORMATION
                = new EPayloadContainerType(0b0001, "N1 SM information");
        public static final EPayloadContainerType SMS
                = new EPayloadContainerType(0b0010, "SMS");
        public static final EPayloadContainerType LPP_MESSAGE
                = new EPayloadContainerType(0b0011, "LTE Positioning Protocol (LPP) message container");
        public static final EPayloadContainerType SOR_TRANSPARENT_CONTAINER
                = new EPayloadContainerType(0b0100, "SOR transparent container");
        public static final EPayloadContainerType UE_POLICY_CONTAINER
                = new EPayloadContainerType(0b0101, "UE policy container");
        public static final EPayloadContainerType UE_PARAMETERS_UPDATE_TRANSPARENT_CONTAINER
                = new EPayloadContainerType(0b0110, "UE parameters update transparent container");
        public static final EPayloadContainerType MULTIPLE_PAYLOADS
                = new EPayloadContainerType(0b1111, "Multiple payloads");

        private EPayloadContainerType(int value, String name) {
            super(value, name);
        }

        public static EPayloadContainerType fromValue(int value) {
            return fromValueGeneric(EPayloadContainerType.class, value, null);
        }
    }
}
