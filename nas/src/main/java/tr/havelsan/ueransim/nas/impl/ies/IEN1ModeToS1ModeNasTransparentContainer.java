/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.nas.impl.ies;

import tr.havelsan.ueransim.nas.core.ies.InformationElement3;
import tr.havelsan.ueransim.utils.OctetInputStream;
import tr.havelsan.ueransim.utils.OctetOutputStream;
import tr.havelsan.ueransim.utils.octets.Octet;

public class IEN1ModeToS1ModeNasTransparentContainer extends InformationElement3 {
    public Octet sequenceNumber;

    public IEN1ModeToS1ModeNasTransparentContainer() {
    }

    public IEN1ModeToS1ModeNasTransparentContainer(Octet sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    @Override
    protected InformationElement3 decodeIE3(OctetInputStream stream) {
        return new IEN1ModeToS1ModeNasTransparentContainer(stream.readOctet());
    }

    @Override
    public void encodeIE3(OctetOutputStream stream) {
        stream.writeOctet(sequenceNumber);
    }
}
