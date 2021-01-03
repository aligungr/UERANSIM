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

public class IEServiceType extends InformationElement1 {
    public EServiceType serviceType;

    public IEServiceType() {
    }

    public IEServiceType(EServiceType serviceType) {
        this.serviceType = serviceType;
    }

    @Override
    public IEServiceType decodeIE1(Bit4 value) {
        var res = new IEServiceType();
        res.serviceType = EServiceType.fromValue(value.intValue());
        return res;
    }

    @Override
    public int encodeIE1() {
        return serviceType.intValue();
    }

    public static class EServiceType extends ProtocolEnum {
        public static final EServiceType SIGNALLING
                = new EServiceType(0b0000, "signalling");
        public static final EServiceType DATA
                = new EServiceType(0b0001, "data");
        public static final EServiceType MOBILE_TERMINATED_SERVICES
                = new EServiceType(0b0010, "mobile terminated services");
        public static final EServiceType EMERGENCY_SERVICES
                = new EServiceType(0b0011, "emergency services");
        public static final EServiceType EMERGENCY_SERVICES_FALLBACK
                = new EServiceType(0b0100, "emergency services fallback");
        public static final EServiceType HIGH_PRIORITY_ACCESS
                = new EServiceType(0b0101, "high priority access");
        public static final EServiceType UNUSED_SIGNALLING_1
                = new EServiceType(0b0110, "unused; shall be interpreted as \"signalling\", if received by the network");
        public static final EServiceType UNUSED_SIGNALLING_2
                = new EServiceType(0b0111, "unused; shall be interpreted as \"signalling\", if received by the network");
        public static final EServiceType UNUSED_SIGNALLING_3
                = new EServiceType(0b1000, "unused; shall be interpreted as \"signalling\", if received by the network");
        public static final EServiceType UNUSED_DATA_1
                = new EServiceType(0b1001, "unused; shall be interpreted as \"data\", if received by the network");
        public static final EServiceType UNUSED_DATA_2
                = new EServiceType(0b1010, "unused; shall be interpreted as \"data\", if received by the network");
        public static final EServiceType UNUSED_DATA_3
                = new EServiceType(0b1011, "unused; shall be interpreted as \"data\", if received by the network");

        private EServiceType(int value, String name) {
            super(value, name);
        }

        public static EServiceType fromValue(int value) {
            return fromValueGeneric(EServiceType.class, value, null);
        }
    }
}
