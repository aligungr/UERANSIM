/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.ies.sequences;

import tr.havelsan.ueransim.ngap0.core.NGAP_Sequence;
import tr.havelsan.ueransim.ngap0.ies.integers.NGAP_AMF_UE_NGAP_ID;
import tr.havelsan.ueransim.ngap0.ies.integers.NGAP_RAN_UE_NGAP_ID;

public class NGAP_UE_associatedLogicalNG_connectionItem extends NGAP_Sequence {

    public NGAP_AMF_UE_NGAP_ID aMF_UE_NGAP_ID;
    public NGAP_RAN_UE_NGAP_ID rAN_UE_NGAP_ID;

    @Override
    public String getAsnName() {
        return "UE-associatedLogicalNG-connectionItem";
    }

    @Override
    public String getXmlTagName() {
        return "UE-associatedLogicalNG-connectionItem";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"aMF-UE-NGAP-ID", "rAN-UE-NGAP-ID"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"aMF_UE_NGAP_ID", "rAN_UE_NGAP_ID"};
    }
}
