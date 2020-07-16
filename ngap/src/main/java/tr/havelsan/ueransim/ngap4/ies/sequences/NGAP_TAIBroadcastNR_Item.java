package tr.havelsan.ueransim.ngap4.ies.sequences;

import tr.havelsan.ueransim.ngap4.core.NgapSequence;
import tr.havelsan.ueransim.ngap4.ies.sequence_ofs.NGAP_CompletedCellsInTAI_NR;

public class NGAP_TAIBroadcastNR_Item extends NgapSequence {

    public NGAP_TAI tAI;
    public NGAP_CompletedCellsInTAI_NR completedCellsInTAI_NR;

    @Override
    protected String getAsnName() {
        return "TAIBroadcastNR-Item";
    }

    @Override
    protected String getXmlTagName() {
        return "TAIBroadcastNR-Item";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"tAI", "completedCellsInTAI-NR"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"tAI", "completedCellsInTAI_NR"};
    }
}
