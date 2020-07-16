package tr.havelsan.ueransim.ngap4.ies.sequences;

import tr.havelsan.ueransim.ngap4.core.NgapSequence;
import tr.havelsan.ueransim.ngap4.ies.sequence_ofs.NGAP_CancelledCellsInTAI_EUTRA;

public class NGAP_TAICancelledEUTRA_Item extends NgapSequence {

    public NGAP_TAI tAI;
    public NGAP_CancelledCellsInTAI_EUTRA cancelledCellsInTAI_EUTRA;

    @Override
    protected String getAsnName() {
        return "TAICancelledEUTRA-Item";
    }

    @Override
    protected String getXmlTagName() {
        return "TAICancelledEUTRA-Item";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"tAI", "cancelledCellsInTAI-EUTRA"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"tAI", "cancelledCellsInTAI_EUTRA"};
    }
}
