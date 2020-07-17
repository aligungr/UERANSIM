package tr.havelsan.ueransim.ngap4.ies.sequences;

import tr.havelsan.ueransim.ngap4.core.*;
import tr.havelsan.ueransim.utils.bits.*;
import tr.havelsan.ueransim.utils.octets.*;
import tr.havelsan.ueransim.ngap4.ies.bit_strings.*;
import tr.havelsan.ueransim.ngap4.ies.octet_strings.*;
import tr.havelsan.ueransim.ngap4.ies.printable_strings.*;
import tr.havelsan.ueransim.ngap4.ies.sequences.*;
import tr.havelsan.ueransim.ngap4.ies.sequence_ofs.*;
import tr.havelsan.ueransim.ngap4.ies.choices.*;
import tr.havelsan.ueransim.ngap4.ies.integers.*;
import tr.havelsan.ueransim.ngap4.ies.enumerations.*;

import java.util.List;

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
