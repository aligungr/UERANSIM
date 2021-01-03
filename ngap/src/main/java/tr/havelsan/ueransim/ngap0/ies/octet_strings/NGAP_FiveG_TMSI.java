/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.ies.octet_strings;

import tr.havelsan.ueransim.ngap0.core.NGAP_OctetString;
import tr.havelsan.ueransim.utils.bits.BitString;
import tr.havelsan.ueransim.utils.octets.Octet;
import tr.havelsan.ueransim.utils.octets.OctetString;

public class NGAP_FiveG_TMSI extends NGAP_OctetString {

    public NGAP_FiveG_TMSI(OctetString value) {
        super(value);
    }

    public NGAP_FiveG_TMSI(BitString value) {
        super(value);
    }

    public NGAP_FiveG_TMSI(Octet[] octets) {
        super(octets);
    }

    public NGAP_FiveG_TMSI(int[] octetInts) {
        super(octetInts);
    }

    public NGAP_FiveG_TMSI(byte[] octetBytes) {
        super(octetBytes);
    }

    public NGAP_FiveG_TMSI(String hex) {
        super(hex);
    }

    @Override
    public String getAsnName() {
        return "FiveG-TMSI";
    }

    @Override
    public String getXmlTagName() {
        return "FiveG-TMSI";
    }
}
