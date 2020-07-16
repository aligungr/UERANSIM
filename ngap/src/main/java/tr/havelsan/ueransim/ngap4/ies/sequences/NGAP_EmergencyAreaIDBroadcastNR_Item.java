package tr.havelsan.ueransim.ngap4.ies.sequences;

import tr.havelsan.ueransim.ngap4.core.NgapSequence;
import tr.havelsan.ueransim.ngap4.ies.octet_strings.NGAP_EmergencyAreaID;
import tr.havelsan.ueransim.ngap4.ies.sequence_ofs.NGAP_CompletedCellsInEAI_NR;

public class NGAP_EmergencyAreaIDBroadcastNR_Item extends NgapSequence {

    public NGAP_EmergencyAreaID emergencyAreaID;
    public NGAP_CompletedCellsInEAI_NR completedCellsInEAI_NR;

    @Override
    protected String getAsnName() {
        return "EmergencyAreaIDBroadcastNR-Item";
    }

    @Override
    protected String getXmlTagName() {
        return "EmergencyAreaIDBroadcastNR-Item";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"emergencyAreaID", "completedCellsInEAI-NR"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"emergencyAreaID", "completedCellsInEAI_NR"};
    }
}
