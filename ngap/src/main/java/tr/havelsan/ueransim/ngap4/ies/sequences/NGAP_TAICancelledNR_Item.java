package tr.havelsan.ueransim.ngap4.ies.sequences;

import tr.havelsan.ueransim.ngap4.core.NgapSequence;
import tr.havelsan.ueransim.ngap4.ies.sequence_ofs.NGAP_CancelledCellsInTAI_NR;

public class NGAP_TAICancelledNR_Item extends NgapSequence {

    public NGAP_TAI tAI;
    public NGAP_CancelledCellsInTAI_NR cancelledCellsInTAI_NR;

    @Override
    protected String getAsnName() {
        return "TAICancelledNR-Item";
    }

    @Override
    protected String getXmlTagName() {
        return "TAICancelledNR-Item";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"tAI", "cancelledCellsInTAI-NR"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"tAI", "cancelledCellsInTAI_NR"};
    }
}
