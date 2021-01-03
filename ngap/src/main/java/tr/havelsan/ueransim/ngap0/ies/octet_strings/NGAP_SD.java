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

public class NGAP_SD extends NGAP_OctetString {

    public NGAP_SD(OctetString value) {
        super(value);
    }

    public NGAP_SD(BitString value) {
        super(value);
    }

    public NGAP_SD(Octet[] octets) {
        super(octets);
    }

    public NGAP_SD(int[] octetInts) {
        super(octetInts);
    }

    public NGAP_SD(byte[] octetBytes) {
        super(octetBytes);
    }

    public NGAP_SD(String hex) {
        super(hex);
    }

    @Override
    public String getAsnName() {
        return "SD";
    }

    @Override
    public String getXmlTagName() {
        return "SD";
    }
}
