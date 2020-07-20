package tr.havelsan.ueransim.ngap0.ies.sequences;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.ies.sequence_ofs.*;

public class NGAP_TAICancelledEUTRA_Item extends NGAP_Sequence {

    public NGAP_TAI tAI;
    public NGAP_CancelledCellsInTAI_EUTRA cancelledCellsInTAI_EUTRA;

    @Override
    public String getAsnName() {
        return "TAICancelledEUTRA-Item";
    }

    @Override
    public String getXmlTagName() {
        return "TAICancelledEUTRA-Item";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"tAI", "cancelledCellsInTAI-EUTRA"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"tAI", "cancelledCellsInTAI_EUTRA"};
    }
}
