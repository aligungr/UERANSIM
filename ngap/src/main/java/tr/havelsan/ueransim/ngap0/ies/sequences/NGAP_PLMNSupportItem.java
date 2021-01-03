/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.ies.sequences;

import tr.havelsan.ueransim.ngap0.core.NGAP_Sequence;
import tr.havelsan.ueransim.ngap0.ies.octet_strings.NGAP_PLMNIdentity;
import tr.havelsan.ueransim.ngap0.ies.sequence_ofs.NGAP_SliceSupportList;

public class NGAP_PLMNSupportItem extends NGAP_Sequence {

    public NGAP_PLMNIdentity pLMNIdentity;
    public NGAP_SliceSupportList sliceSupportList;

    @Override
    public String getAsnName() {
        return "PLMNSupportItem";
    }

    @Override
    public String getXmlTagName() {
        return "PLMNSupportItem";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"pLMNIdentity", "sliceSupportList"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"pLMNIdentity", "sliceSupportList"};
    }
}
