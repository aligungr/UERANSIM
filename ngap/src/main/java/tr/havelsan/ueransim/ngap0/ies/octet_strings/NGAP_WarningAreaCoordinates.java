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

public class NGAP_WarningAreaCoordinates extends NGAP_OctetString {

    public NGAP_WarningAreaCoordinates(OctetString value) {
        super(value);
    }

    public NGAP_WarningAreaCoordinates(BitString value) {
        super(value);
    }

    public NGAP_WarningAreaCoordinates(Octet[] octets) {
        super(octets);
    }

    public NGAP_WarningAreaCoordinates(int[] octetInts) {
        super(octetInts);
    }

    public NGAP_WarningAreaCoordinates(byte[] octetBytes) {
        super(octetBytes);
    }

    public NGAP_WarningAreaCoordinates(String hex) {
        super(hex);
    }

    @Override
    public String getAsnName() {
        return "WarningAreaCoordinates";
    }

    @Override
    public String getXmlTagName() {
        return "WarningAreaCoordinates";
    }
}
