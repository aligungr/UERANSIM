/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.ies.choices;

import tr.havelsan.ueransim.ngap0.core.NGAP_Choice;
import tr.havelsan.ueransim.ngap0.ies.sequence_ofs.NGAP_EUTRA_CGIListForWarning;
import tr.havelsan.ueransim.ngap0.ies.sequence_ofs.NGAP_EmergencyAreaIDList;
import tr.havelsan.ueransim.ngap0.ies.sequence_ofs.NGAP_NR_CGIListForWarning;
import tr.havelsan.ueransim.ngap0.ies.sequence_ofs.NGAP_TAIListForWarning;

public class NGAP_WarningAreaList extends NGAP_Choice {

    public NGAP_EUTRA_CGIListForWarning eUTRA_CGIListForWarning;
    public NGAP_NR_CGIListForWarning nR_CGIListForWarning;
    public NGAP_TAIListForWarning tAIListForWarning;
    public NGAP_EmergencyAreaIDList emergencyAreaIDList;

    @Override
    public String getAsnName() {
        return "WarningAreaList";
    }

    @Override
    public String getXmlTagName() {
        return "WarningAreaList";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"eUTRA-CGIListForWarning", "nR-CGIListForWarning", "tAIListForWarning", "emergencyAreaIDList"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"eUTRA_CGIListForWarning", "nR_CGIListForWarning", "tAIListForWarning", "emergencyAreaIDList"};
    }
}
