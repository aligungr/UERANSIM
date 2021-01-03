/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.ies.sequences;

import tr.havelsan.ueransim.ngap0.core.NGAP_Sequence;
import tr.havelsan.ueransim.ngap0.ies.octet_strings.NGAP_EmergencyAreaID;
import tr.havelsan.ueransim.ngap0.ies.sequence_ofs.NGAP_CancelledCellsInEAI_NR;

public class NGAP_EmergencyAreaIDCancelledNR_Item extends NGAP_Sequence {

    public NGAP_EmergencyAreaID emergencyAreaID;
    public NGAP_CancelledCellsInEAI_NR cancelledCellsInEAI_NR;

    @Override
    public String getAsnName() {
        return "EmergencyAreaIDCancelledNR-Item";
    }

    @Override
    public String getXmlTagName() {
        return "EmergencyAreaIDCancelledNR-Item";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"emergencyAreaID", "cancelledCellsInEAI-NR"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"emergencyAreaID", "cancelledCellsInEAI_NR"};
    }
}
