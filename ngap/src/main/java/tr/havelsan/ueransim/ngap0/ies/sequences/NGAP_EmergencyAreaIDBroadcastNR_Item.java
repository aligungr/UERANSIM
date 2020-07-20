package tr.havelsan.ueransim.ngap0.ies.sequences;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.ies.octet_strings.*;
import tr.havelsan.ueransim.ngap0.ies.sequence_ofs.*;

public class NGAP_EmergencyAreaIDBroadcastNR_Item extends NGAP_Sequence {

    public NGAP_EmergencyAreaID emergencyAreaID;
    public NGAP_CompletedCellsInEAI_NR completedCellsInEAI_NR;

    @Override
    public String getAsnName() {
        return "EmergencyAreaIDBroadcastNR-Item";
    }

    @Override
    public String getXmlTagName() {
        return "EmergencyAreaIDBroadcastNR-Item";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"emergencyAreaID", "completedCellsInEAI-NR"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"emergencyAreaID", "completedCellsInEAI_NR"};
    }
}
