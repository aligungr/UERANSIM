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

public class IESmsIndication extends InformationElement1 {
    public ESmsAvailabilityIndication sai;

    public IESmsIndication() {
    }

    public IESmsIndication(ESmsAvailabilityIndication sai) {
        this.sai = sai;
    }

    @Override
    public IESmsIndication decodeIE1(Bit4 value) {
        var res = new IESmsIndication();
        res.sai = ESmsAvailabilityIndication.fromValue(value.getBitI(0));
        return res;
    }

    @Override
    public int encodeIE1() {
        return sai.intValue();
    }

    public static class ESmsAvailabilityIndication extends ProtocolEnum {
        public static final ESmsAvailabilityIndication NOT_AVAILABLE
                = new ESmsAvailabilityIndication(0b0, "SMS over NAS not available");
        public static final ESmsAvailabilityIndication AVAILABLE
                = new ESmsAvailabilityIndication(0b1, "SMS over NAS available");

        private ESmsAvailabilityIndication(int value, String name) {
            super(value, name);
        }

        public static ESmsAvailabilityIndication fromValue(int value) {
            return fromValueGeneric(ESmsAvailabilityIndication.class, value, null);
        }
    }
}
