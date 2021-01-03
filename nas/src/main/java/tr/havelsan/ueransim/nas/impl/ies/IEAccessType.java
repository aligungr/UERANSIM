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

public class IEAccessType extends InformationElement1 {
    public EAccessType accessType;

    public IEAccessType() {
    }

    public IEAccessType(EAccessType accessType) {
        this.accessType = accessType;
    }

    @Override
    public IEAccessType decodeIE1(Bit4 value) {
        var res = new IEAccessType();
        res.accessType = EAccessType.fromValue(value.intValue() & 0b11);
        return res;
    }

    @Override
    public int encodeIE1() {
        return accessType.intValue();
    }

    public static class EAccessType extends ProtocolEnum {
        public static final EAccessType THREEGPP_ACCESS
                = new EAccessType(0b01, "3GPP access");
        public static final EAccessType NON_THREEGPP_ACCESS
                = new EAccessType(0b10, "Non-3GPP access");

        private EAccessType(int value, String name) {
            super(value, name);
        }

        public static EAccessType fromValue(int value) {
            return fromValueGeneric(EAccessType.class, value, null);
        }
    }
}
