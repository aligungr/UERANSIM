/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.ies.sequences;

import tr.havelsan.ueransim.ngap0.core.NGAP_Sequence;
import tr.havelsan.ueransim.ngap0.ies.choices.NGAP_GNB_ID;
import tr.havelsan.ueransim.ngap0.ies.octet_strings.NGAP_PLMNIdentity;

public class NGAP_GlobalGNB_ID extends NGAP_Sequence {

    public NGAP_PLMNIdentity pLMNIdentity;
    public NGAP_GNB_ID gNB_ID;

    @Override
    public String getAsnName() {
        return "GlobalGNB-ID";
    }

    @Override
    public String getXmlTagName() {
        return "GlobalGNB-ID";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"pLMNIdentity", "gNB-ID"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"pLMNIdentity", "gNB_ID"};
    }
}
