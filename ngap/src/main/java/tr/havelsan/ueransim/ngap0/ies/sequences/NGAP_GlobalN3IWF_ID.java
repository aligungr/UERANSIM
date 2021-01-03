/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.ies.sequences;

import tr.havelsan.ueransim.ngap0.core.NGAP_Sequence;
import tr.havelsan.ueransim.ngap0.ies.choices.NGAP_N3IWF_ID;
import tr.havelsan.ueransim.ngap0.ies.octet_strings.NGAP_PLMNIdentity;

public class NGAP_GlobalN3IWF_ID extends NGAP_Sequence {

    public NGAP_PLMNIdentity pLMNIdentity;
    public NGAP_N3IWF_ID n3IWF_ID;

    @Override
    public String getAsnName() {
        return "GlobalN3IWF-ID";
    }

    @Override
    public String getXmlTagName() {
        return "GlobalN3IWF-ID";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"pLMNIdentity", "n3IWF-ID"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"pLMNIdentity", "n3IWF_ID"};
    }
}
