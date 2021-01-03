/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.ies.choices;

import tr.havelsan.ueransim.ngap0.core.NGAP_Choice;
import tr.havelsan.ueransim.ngap0.ies.integers.NGAP_AMF_UE_NGAP_ID;
import tr.havelsan.ueransim.ngap0.ies.sequences.NGAP_UE_NGAP_ID_pair;

public class NGAP_UE_NGAP_IDs extends NGAP_Choice {

    public NGAP_UE_NGAP_ID_pair uE_NGAP_ID_pair;
    public NGAP_AMF_UE_NGAP_ID aMF_UE_NGAP_ID;

    @Override
    public String getAsnName() {
        return "UE-NGAP-IDs";
    }

    @Override
    public String getXmlTagName() {
        return "UE-NGAP-IDs";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"uE-NGAP-ID-pair", "aMF-UE-NGAP-ID"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"uE_NGAP_ID_pair", "aMF_UE_NGAP_ID"};
    }
}
