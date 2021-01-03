/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.nas.core.ies;

import tr.havelsan.ueransim.utils.OctetInputStream;
import tr.havelsan.ueransim.utils.OctetOutputStream;

/**
 * Information elements of format LV-E or TLV-E with value part consisting of zero, one or more octets and a maximum of 65535 octets.
 */
public abstract class InformationElement6 extends InformationElement {

    protected abstract InformationElement6 decodeIE6(OctetInputStream stream, int length);

    @Override
    public final InformationElement decodeIE(OctetInputStream stream) {
        int length = stream.readOctet2I();
        return decodeIE6(stream, length);
    }

    public abstract void encodeIE6(OctetOutputStream stream);
}
