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

public class NGAP_WarningMessageContents extends NGAP_OctetString {

    public NGAP_WarningMessageContents(OctetString value) {
        super(value);
    }

    public NGAP_WarningMessageContents(BitString value) {
        super(value);
    }

    public NGAP_WarningMessageContents(Octet[] octets) {
        super(octets);
    }

    public NGAP_WarningMessageContents(int[] octetInts) {
        super(octetInts);
    }

    public NGAP_WarningMessageContents(byte[] octetBytes) {
        super(octetBytes);
    }

    public NGAP_WarningMessageContents(String hex) {
        super(hex);
    }

    @Override
    public String getAsnName() {
        return "WarningMessageContents";
    }

    @Override
    public String getXmlTagName() {
        return "WarningMessageContents";
    }
}
