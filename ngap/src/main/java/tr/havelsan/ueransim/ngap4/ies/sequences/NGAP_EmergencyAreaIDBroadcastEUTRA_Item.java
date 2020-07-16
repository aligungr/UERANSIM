package tr.havelsan.ueransim.ngap4.ies.sequences;

import tr.havelsan.ueransim.ngap4.core.NgapSequence;
import tr.havelsan.ueransim.ngap4.ies.octet_strings.NGAP_EmergencyAreaID;
import tr.havelsan.ueransim.ngap4.ies.sequence_ofs.NGAP_CompletedCellsInEAI_EUTRA;

public class NGAP_EmergencyAreaIDBroadcastEUTRA_Item extends NgapSequence {

    public NGAP_EmergencyAreaID emergencyAreaID;
    public NGAP_CompletedCellsInEAI_EUTRA completedCellsInEAI_EUTRA;

    @Override
    protected String getAsnName() {
        return "EmergencyAreaIDBroadcastEUTRA-Item";
    }

    @Override
    protected String getXmlTagName() {
        return "EmergencyAreaIDBroadcastEUTRA-Item";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"emergencyAreaID", "completedCellsInEAI-EUTRA"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"emergencyAreaID", "completedCellsInEAI_EUTRA"};
    }
}
