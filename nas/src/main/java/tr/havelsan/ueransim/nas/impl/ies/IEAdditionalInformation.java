/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.nas.impl.ies;

import tr.havelsan.ueransim.nas.core.ies.InformationElement4;
import tr.havelsan.ueransim.utils.OctetInputStream;
import tr.havelsan.ueransim.utils.OctetOutputStream;
import tr.havelsan.ueransim.utils.octets.OctetString;

public class IEAdditionalInformation extends InformationElement4 {
    public OctetString data;

    public IEAdditionalInformation() {
    }

    public IEAdditionalInformation(OctetString data) {
        this.data = data;
    }

    @Override
    protected IEAdditionalInformation decodeIE4(OctetInputStream stream, int length) {
        var res = new IEAdditionalInformation();
        res.data = stream.readOctetString(length);
        return res;
    }

    @Override
    public void encodeIE4(OctetOutputStream stream) {
        stream.writeOctetString(data);
    }
}
