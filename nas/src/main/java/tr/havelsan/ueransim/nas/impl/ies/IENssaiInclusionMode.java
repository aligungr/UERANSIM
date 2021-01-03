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

public class IENssaiInclusionMode extends InformationElement1 {
    public ENssaiInclusionMode nssaiInclusionMode;

    public IENssaiInclusionMode() {
    }

    public IENssaiInclusionMode(ENssaiInclusionMode nssaiInclusionMode) {
        this.nssaiInclusionMode = nssaiInclusionMode;
    }

    @Override
    public IENssaiInclusionMode decodeIE1(Bit4 value) {
        var res = new IENssaiInclusionMode();
        res.nssaiInclusionMode = ENssaiInclusionMode.fromValue(value.intValue() & 0b11);
        return res;
    }

    @Override
    public int encodeIE1() {
        return nssaiInclusionMode.intValue();
    }

    public static class ENssaiInclusionMode extends ProtocolEnum {
        public static final ENssaiInclusionMode A
                = new ENssaiInclusionMode(0b00, "NSSAI inclusion mode A");
        public static final ENssaiInclusionMode B
                = new ENssaiInclusionMode(0b01, "NSSAI inclusion mode B");
        public static final ENssaiInclusionMode C
                = new ENssaiInclusionMode(0b10, "NSSAI inclusion mode C");
        public static final ENssaiInclusionMode D
                = new ENssaiInclusionMode(0b11, "NSSAI inclusion mode D");


        private ENssaiInclusionMode(int value, String name) {
            super(value, name);
        }

        public static ENssaiInclusionMode fromValue(int value) {
            return fromValueGeneric(ENssaiInclusionMode.class, value, null);
        }
    }
}
