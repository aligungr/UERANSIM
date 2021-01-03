/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.nas.impl.ies;

import tr.havelsan.ueransim.utils.OctetOutputStream;

public class IENoIdentity extends IE5gsMobileIdentity {

    @Override
    public void encodeIE6(OctetOutputStream stream) {
        stream.writeOctet(0xFF); // flags for no identity
    }
}
