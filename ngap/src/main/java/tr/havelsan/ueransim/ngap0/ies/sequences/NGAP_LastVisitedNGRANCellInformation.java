package tr.havelsan.ueransim.ngap0.ies.sequences;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.ies.choices.*;
import tr.havelsan.ueransim.ngap0.ies.integers.*;

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
