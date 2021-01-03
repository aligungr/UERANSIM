/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.nas.impl.ies;

import tr.havelsan.ueransim.nas.core.ies.InformationElement1;
import tr.havelsan.ueransim.nas.impl.enums.ETypeOfSecurityContext;
import tr.havelsan.ueransim.utils.bits.Bit3;
import tr.havelsan.ueransim.utils.bits.Bit4;

public class IENasKeySetIdentifier extends InformationElement1 {
    /*
     * 'no key is available' for UE to network
     * 'reserved' for network to UE
     */
    public static final Bit3 NOT_AVAILABLE_OR_RESERVED = new Bit3(0b111);

    public ETypeOfSecurityContext tsc;
    public Bit3 nasKeySetIdentifier;

    public IENasKeySetIdentifier() {
    }

    public IENasKeySetIdentifier(ETypeOfSecurityContext tsc, Bit3 nasKeySetIdentifier) {
        this.tsc = tsc;
        this.nasKeySetIdentifier = nasKeySetIdentifier;
    }

    @Override
    public InformationElement1 decodeIE1(Bit4 value) {
        int val = value.intValue();

        var res = new IENasKeySetIdentifier();
        res.tsc = ETypeOfSecurityContext.fromValue(val >> 3 & 0b1);
        res.nasKeySetIdentifier = new Bit3(val & 0b111);
        return res;
    }

    @Override
    public int encodeIE1() {
        return tsc.intValue() << 3 | nasKeySetIdentifier.intValue();
    }
}
