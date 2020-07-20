package tr.havelsan.ueransim.ngap0.ies.sequences;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.ies.sequence_ofs.*;

public class NGAP_TAIBroadcastEUTRA_Item extends NGAP_Sequence {

    public NGAP_TAI tAI;
    public NGAP_CompletedCellsInTAI_EUTRA completedCellsInTAI_EUTRA;

    @Override
    public String getAsnName() {
        return "TAIBroadcastEUTRA-Item";
    }

    @Override
    public String getXmlTagName() {
        return "TAIBroadcastEUTRA-Item";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"tAI", "completedCellsInTAI-EUTRA"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"tAI", "completedCellsInTAI_EUTRA"};
    }
}
