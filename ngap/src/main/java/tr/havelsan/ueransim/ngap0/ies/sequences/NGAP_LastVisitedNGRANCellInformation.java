/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.ies.sequences;

import tr.havelsan.ueransim.ngap0.core.NGAP_Sequence;
import tr.havelsan.ueransim.ngap0.ies.choices.NGAP_Cause;
import tr.havelsan.ueransim.ngap0.ies.choices.NGAP_NGRAN_CGI;
import tr.havelsan.ueransim.ngap0.ies.integers.NGAP_TimeUEStayedInCell;
import tr.havelsan.ueransim.ngap0.ies.integers.NGAP_TimeUEStayedInCellEnhancedGranularity;

public class NGAP_LastVisitedNGRANCellInformation extends NGAP_Sequence {

    public NGAP_NGRAN_CGI globalCellID;
    public NGAP_CellType cellType;
    public NGAP_TimeUEStayedInCell timeUEStayedInCell;
    public NGAP_TimeUEStayedInCellEnhancedGranularity timeUEStayedInCellEnhancedGranularity;
    public NGAP_Cause hOCauseValue;

    @Override
    public String getAsnName() {
        return "LastVisitedNGRANCellInformation";
    }

    @Override
    public String getXmlTagName() {
        return "LastVisitedNGRANCellInformation";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"globalCellID", "cellType", "timeUEStayedInCell", "timeUEStayedInCellEnhancedGranularity", "hOCauseValue"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"globalCellID", "cellType", "timeUEStayedInCell", "timeUEStayedInCellEnhancedGranularity", "hOCauseValue"};
    }
}
