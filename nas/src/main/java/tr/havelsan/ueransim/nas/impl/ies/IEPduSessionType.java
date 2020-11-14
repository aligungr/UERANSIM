/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.nas.impl.ies;

import tr.havelsan.ueransim.nas.core.ies.InformationElement1;
import tr.havelsan.ueransim.nas.impl.enums.EPduSessionType;
import tr.havelsan.ueransim.utils.bits.Bit4;

public class IEPduSessionType extends InformationElement1 {
    public EPduSessionType pduSessionType;

    public IEPduSessionType() {
    }

    public IEPduSessionType(EPduSessionType pduSessionType) {
        this.pduSessionType = pduSessionType;
    }

    @Override
    public IEPduSessionType decodeIE1(Bit4 value) {
        var res = new IEPduSessionType();
        res.pduSessionType = EPduSessionType.fromValue(value.intValue() & 0b111);
        return res;
    }

    @Override
    public int encodeIE1() {
        return pduSessionType.intValue();
    }
}
