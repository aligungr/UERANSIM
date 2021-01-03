/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.ies.choices;

import tr.havelsan.ueransim.ngap0.core.NGAP_Choice;
import tr.havelsan.ueransim.ngap0.ies.sequences.NGAP_TargetRANNodeID;
import tr.havelsan.ueransim.ngap0.ies.sequences.NGAP_TargeteNB_ID;

public class NGAP_TargetID extends NGAP_Choice {

    public NGAP_TargetRANNodeID targetRANNodeID;
    public NGAP_TargeteNB_ID targeteNB_ID;

    @Override
    public String getAsnName() {
        return "TargetID";
    }

    @Override
    public String getXmlTagName() {
        return "TargetID";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"targetRANNodeID", "targeteNB-ID"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"targetRANNodeID", "targeteNB_ID"};
    }
}
