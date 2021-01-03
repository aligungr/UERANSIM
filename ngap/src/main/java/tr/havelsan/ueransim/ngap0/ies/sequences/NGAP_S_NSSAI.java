/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.ies.sequences;

import tr.havelsan.ueransim.ngap0.core.NGAP_Sequence;
import tr.havelsan.ueransim.ngap0.ies.octet_strings.NGAP_SD;
import tr.havelsan.ueransim.ngap0.ies.octet_strings.NGAP_SST;

public class NGAP_S_NSSAI extends NGAP_Sequence {

    public NGAP_SST sST;
    public NGAP_SD sD;

    @Override
    public String getAsnName() {
        return "S-NSSAI";
    }

    @Override
    public String getXmlTagName() {
        return "S-NSSAI";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"sST", "sD"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"sST", "sD"};
    }
}
