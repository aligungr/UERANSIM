package tr.havelsan.ueransim.ngap0.ies.sequences;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.ies.sequence_ofs.*;

public class NGAP_TAICancelledNR_Item extends NGAP_Sequence {

    public NGAP_TAI tAI;
    public NGAP_CancelledCellsInTAI_NR cancelledCellsInTAI_NR;

    @Override
    public String getAsnName() {
        return "TAICancelledNR-Item";
    }

    @Override
    public String getXmlTagName() {
        return "TAICancelledNR-Item";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"tAI", "cancelledCellsInTAI-NR"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"tAI", "cancelledCellsInTAI_NR"};
    }
}
