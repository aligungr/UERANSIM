/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.nas.impl.ies;

import tr.havelsan.ueransim.nas.core.ies.InformationElement4;
import tr.havelsan.ueransim.nas.impl.enums.EEpcNasSupported;
import tr.havelsan.ueransim.nas.impl.enums.EHandoverAttachSupported;
import tr.havelsan.ueransim.nas.impl.enums.ELtePositioningProtocolCapability;
import tr.havelsan.ueransim.utils.OctetInputStream;
import tr.havelsan.ueransim.utils.OctetOutputStream;

public class IE5gMmCapability extends InformationElement4 {
    public EEpcNasSupported s1Mode;
    public EHandoverAttachSupported hoAttach;
    public ELtePositioningProtocolCapability lpp;

    public IE5gMmCapability() {
    }

    public IE5gMmCapability(EEpcNasSupported s1Mode, EHandoverAttachSupported hoAttach, ELtePositioningProtocolCapability lpp) {
        this.s1Mode = s1Mode;
        this.hoAttach = hoAttach;
        this.lpp = lpp;
    }

    @Override
    protected IE5gMmCapability decodeIE4(OctetInputStream stream, int length) {
        var res = new IE5gMmCapability();
        var octet = stream.readOctet();
        res.s1Mode = EEpcNasSupported.fromValue(octet.getBitI(0));
        res.hoAttach = EHandoverAttachSupported.fromValue(octet.getBitI(1));
        res.lpp = ELtePositioningProtocolCapability.fromValue(octet.getBitI(2));

        // other octets are spare (if any)
        stream.readOctetString(length - 1);

        return res;
    }

    @Override
    public void encodeIE4(OctetOutputStream stream) {
        int octet = 0;
        octet |= lpp.intValue();
        octet <<= 1;
        octet |= hoAttach.intValue();
        octet <<= 1;
        octet |= s1Mode.intValue();
        stream.writeOctet(octet);
    }
}
