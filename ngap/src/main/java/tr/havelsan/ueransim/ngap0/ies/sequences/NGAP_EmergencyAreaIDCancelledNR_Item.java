package tr.havelsan.ueransim.ngap0.ies.sequences;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.ies.octet_strings.*;
import tr.havelsan.ueransim.ngap0.ies.sequence_ofs.*;

public class NGAP_EmergencyAreaIDCancelledNR_Item extends NGAP_Sequence {

    public NGAP_EmergencyAreaID emergencyAreaID;
    public NGAP_CancelledCellsInEAI_NR cancelledCellsInEAI_NR;

    @Override
    public String getAsnName() {
        return "EmergencyAreaIDCancelledNR-Item";
    }

    @Override
    public String getXmlTagName() {
        return "EmergencyAreaIDCancelledNR-Item";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"emergencyAreaID", "cancelledCellsInEAI-NR"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"emergencyAreaID", "cancelledCellsInEAI_NR"};
    }
}
