/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.nas.impl.ies;

import tr.havelsan.ueransim.utils.OctetOutputStream;

import java.nio.charset.StandardCharsets;

public class IENsiMobileIdentity extends IESuciMobileIdentity {
    public String suciNai;

    public IENsiMobileIdentity() {
    }

    public IENsiMobileIdentity(String suciNai) {
        this.suciNai = suciNai;
    }

    @Override
    public void encodeIE6(OctetOutputStream stream) {
        stream.writeOctet(0b00010001); // Flags for SUCI with SUPI format NSI
        stream.writeOctets(suciNai.getBytes(StandardCharsets.UTF_8));
    }
}
