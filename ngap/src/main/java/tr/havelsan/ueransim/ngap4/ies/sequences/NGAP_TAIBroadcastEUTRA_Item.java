package tr.havelsan.ueransim.ngap4.ies.sequences;

import tr.havelsan.ueransim.ngap4.core.NgapSequence;
import tr.havelsan.ueransim.ngap4.ies.sequence_ofs.NGAP_CompletedCellsInTAI_EUTRA;

public class NGAP_TAIBroadcastEUTRA_Item extends NgapSequence {

    public NGAP_TAI tAI;
    public NGAP_CompletedCellsInTAI_EUTRA completedCellsInTAI_EUTRA;

    @Override
    protected String getAsnName() {
        return "TAIBroadcastEUTRA-Item";
    }

    @Override
    protected String getXmlTagName() {
        return "TAIBroadcastEUTRA-Item";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"tAI", "completedCellsInTAI-EUTRA"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"tAI", "completedCellsInTAI_EUTRA"};
    }
}
