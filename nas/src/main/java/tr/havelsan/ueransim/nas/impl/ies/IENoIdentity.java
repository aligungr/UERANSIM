/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.nas.impl.ies;

import tr.havelsan.ueransim.utils.OctetOutputStream;

public class IENoIdentity extends IE5gsMobileIdentity {

    @Override
    public void encodeIE6(OctetOutputStream stream) {
        stream.writeOctet(0xFF); // flags for no identity
    }
}
