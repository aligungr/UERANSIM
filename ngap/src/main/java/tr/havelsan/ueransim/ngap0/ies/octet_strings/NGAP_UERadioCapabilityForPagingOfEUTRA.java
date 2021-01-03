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

public class NGAP_UERadioCapabilityForPagingOfEUTRA extends NGAP_OctetString {

    public NGAP_UERadioCapabilityForPagingOfEUTRA(OctetString value) {
        super(value);
    }

    public NGAP_UERadioCapabilityForPagingOfEUTRA(BitString value) {
        super(value);
    }

    public NGAP_UERadioCapabilityForPagingOfEUTRA(Octet[] octets) {
        super(octets);
    }

    public NGAP_UERadioCapabilityForPagingOfEUTRA(int[] octetInts) {
        super(octetInts);
    }

    public NGAP_UERadioCapabilityForPagingOfEUTRA(byte[] octetBytes) {
        super(octetBytes);
    }

    public NGAP_UERadioCapabilityForPagingOfEUTRA(String hex) {
        super(hex);
    }

    @Override
    public String getAsnName() {
        return "UERadioCapabilityForPagingOfEUTRA";
    }

    @Override
    public String getXmlTagName() {
        return "UERadioCapabilityForPagingOfEUTRA";
    }
}
