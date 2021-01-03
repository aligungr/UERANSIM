/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.nas.impl.ies;

import tr.havelsan.ueransim.nas.core.ies.InformationElement4;
import tr.havelsan.ueransim.nas.impl.enums.EPduSessionType;
import tr.havelsan.ueransim.utils.OctetInputStream;
import tr.havelsan.ueransim.utils.OctetOutputStream;
import tr.havelsan.ueransim.utils.octets.OctetString;

public class IEPduAddress extends InformationElement4 {
    public EPduSessionType sessionType;
    public OctetString pduAddressInformation;

    public IEPduAddress() {
    }

    public IEPduAddress(EPduSessionType sessionType, OctetString pduAddressInformation) {
        this.sessionType = sessionType;
        this.pduAddressInformation = pduAddressInformation;
    }

    @Override
    protected IEPduAddress decodeIE4(OctetInputStream stream, int length) {
        var res = new IEPduAddress();
        res.sessionType = EPduSessionType.fromValue(stream.readOctetI() & 0b111);
        res.pduAddressInformation = stream.readOctetString(length - 1);
        return res;
    }

    @Override
    public void encodeIE4(OctetOutputStream stream) {
        stream.writeOctet(sessionType.intValue());
        stream.writeOctetString(pduAddressInformation);
    }
}
