/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.nas.impl.ies;

import tr.havelsan.ueransim.nas.core.ies.InformationElement1;
import tr.havelsan.ueransim.nas.impl.enums.EIdentityType;
import tr.havelsan.ueransim.utils.bits.Bit4;

public class IE5gsIdentityType extends InformationElement1 {
    public EIdentityType value;

    public IE5gsIdentityType() {
    }

    public IE5gsIdentityType(EIdentityType value) {
        this.value = value;
    }

    @Override
    public IE5gsIdentityType decodeIE1(Bit4 value) {
        var req = new IE5gsIdentityType();
        req.value = EIdentityType.fromValue(value.intValue() & 0b111);
        return req;
    }

    @Override
    public int encodeIE1() {
        return value.intValue();
    }
}
