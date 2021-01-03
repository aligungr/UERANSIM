/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.ies.sequences;

import tr.havelsan.ueransim.ngap0.core.NGAP_Sequence;
import tr.havelsan.ueransim.ngap0.ies.enumerations.NGAP_DLForwarding;
import tr.havelsan.ueransim.ngap0.ies.integers.NGAP_E_RAB_ID;

public class NGAP_E_RABInformationItem extends NGAP_Sequence {

    public NGAP_E_RAB_ID e_RAB_ID;
    public NGAP_DLForwarding dLForwarding;

    @Override
    public String getAsnName() {
        return "E-RABInformationItem";
    }

    @Override
    public String getXmlTagName() {
        return "E-RABInformationItem";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"e-RAB-ID", "dLForwarding"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"e_RAB_ID", "dLForwarding"};
    }
}
