package tr.havelsan.ueransim.ngap0.ies.sequences;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.ies.octet_strings.*;
import tr.havelsan.ueransim.ngap0.ies.sequence_ofs.*;

public class NGAP_EmergencyAreaIDBroadcastEUTRA_Item extends NGAP_Sequence {

    public NGAP_EmergencyAreaID emergencyAreaID;
    public NGAP_CompletedCellsInEAI_EUTRA completedCellsInEAI_EUTRA;

    @Override
    public String getAsnName() {
        return "EmergencyAreaIDBroadcastEUTRA-Item";
    }

    @Override
    public String getXmlTagName() {
        return "EmergencyAreaIDBroadcastEUTRA-Item";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"emergencyAreaID", "completedCellsInEAI-EUTRA"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"emergencyAreaID", "completedCellsInEAI_EUTRA"};
    }
}
