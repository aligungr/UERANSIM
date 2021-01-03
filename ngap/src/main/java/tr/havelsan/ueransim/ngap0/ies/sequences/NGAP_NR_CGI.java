/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.ies.sequences;

import tr.havelsan.ueransim.ngap0.core.NGAP_Sequence;
import tr.havelsan.ueransim.ngap0.ies.bit_strings.NGAP_NRCellIdentity;
import tr.havelsan.ueransim.ngap0.ies.octet_strings.NGAP_PLMNIdentity;

public class NGAP_NR_CGI extends NGAP_Sequence {

    public NGAP_PLMNIdentity pLMNIdentity;
    public NGAP_NRCellIdentity nRCellIdentity;

    @Override
    public String getAsnName() {
        return "NR-CGI";
    }

    @Override
    public String getXmlTagName() {
        return "NR-CGI";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"pLMNIdentity", "nRCellIdentity"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"pLMNIdentity", "nRCellIdentity"};
    }
}
