package tr.havelsan.ueransim.ngap4.ies.sequences;

import tr.havelsan.ueransim.ngap4.core.NgapSequence;
import tr.havelsan.ueransim.ngap4.ies.choices.NGAP_Cause;
import tr.havelsan.ueransim.ngap4.ies.choices.NGAP_NGRAN_CGI;
import tr.havelsan.ueransim.ngap4.ies.integers.NGAP_TimeUEStayedInCell;
import tr.havelsan.ueransim.ngap4.ies.integers.NGAP_TimeUEStayedInCellEnhancedGranularity;

public class NGAP_LastVisitedNGRANCellInformation extends NgapSequence {

    public NGAP_NGRAN_CGI globalCellID;
    public NGAP_CellType cellType;
    public NGAP_TimeUEStayedInCell timeUEStayedInCell;
    public NGAP_TimeUEStayedInCellEnhancedGranularity timeUEStayedInCellEnhancedGranularity;
    public NGAP_Cause hOCauseValue;

    @Override
    protected String getAsnName() {
        return "LastVisitedNGRANCellInformation";
    }

    @Override
    protected String getXmlTagName() {
        return "LastVisitedNGRANCellInformation";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"globalCellID", "cellType", "timeUEStayedInCell", "timeUEStayedInCellEnhancedGranularity", "hOCauseValue"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"globalCellID", "cellType", "timeUEStayedInCell", "timeUEStayedInCellEnhancedGranularity", "hOCauseValue"};
    }
}
