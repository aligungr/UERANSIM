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
import tr.havelsan.ueransim.ngap0.ies.sequence_ofs.NGAP_CompletedCellsInEAI_EUTRA;

public class NGAP_EmergencyAreaIDBroadcastEUTRA_Item extends NGAP_Sequence {

    public NGAP_EmergencyAreaID emergencyAreaID;
    public NGAP_CompletedCellsInEAI_EUTRA completedCellsInEAI_EUTRA;

    @Override
    public String getAsnName() {
        return "EmergencyAreaIDBroadcastEUTRA-Item";
    }

    @Override
    public String getXmlTagName() {
        return "EmergencyAreaIDBroadcastEUTRA-Item";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"emergencyAreaID", "completedCellsInEAI-EUTRA"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"emergencyAreaID", "completedCellsInEAI_EUTRA"};
    }
}
