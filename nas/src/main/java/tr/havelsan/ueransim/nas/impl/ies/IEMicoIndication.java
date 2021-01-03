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

public class IEMicoIndication extends InformationElement1 {
    public ERegistrationAreaAllocationIndication raai;

    public IEMicoIndication() {
    }

    public IEMicoIndication(ERegistrationAreaAllocationIndication raai) {
        this.raai = raai;
    }

    @Override
    public IEMicoIndication decodeIE1(Bit4 value) {
        var res = new IEMicoIndication();
        res.raai = ERegistrationAreaAllocationIndication.fromValue(value.getBitI(0));
        return res;
    }

    @Override
    public int encodeIE1() {
        return raai.intValue();
    }

    public static class ERegistrationAreaAllocationIndication extends ProtocolEnum {
        public static final ERegistrationAreaAllocationIndication NOT_ALLOCATED
                = new ERegistrationAreaAllocationIndication(0b0, "all PLMN registration area not allocated");
        public static final ERegistrationAreaAllocationIndication ALLOCATED
                = new ERegistrationAreaAllocationIndication(0b1, "all PLMN registration area allocated");

        private ERegistrationAreaAllocationIndication(int value, String name) {
            super(value, name);
        }

        public static ERegistrationAreaAllocationIndication fromValue(int value) {
            return fromValueGeneric(ERegistrationAreaAllocationIndication.class, value, null);
        }
    }
}
