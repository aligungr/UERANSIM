package tr.havelsan.ueransim.ngap4.ies.sequences;

import tr.havelsan.ueransim.ngap4.core.NgapSequence;
import tr.havelsan.ueransim.ngap4.ies.octet_strings.NGAP_EmergencyAreaID;
import tr.havelsan.ueransim.ngap4.ies.sequence_ofs.NGAP_CancelledCellsInEAI_NR;

public class NGAP_EmergencyAreaIDCancelledNR_Item extends NgapSequence {

    public NGAP_EmergencyAreaID emergencyAreaID;
    public NGAP_CancelledCellsInEAI_NR cancelledCellsInEAI_NR;

    @Override
    protected String getAsnName() {
        return "EmergencyAreaIDCancelledNR-Item";
    }

    @Override
    protected String getXmlTagName() {
        return "EmergencyAreaIDCancelledNR-Item";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"emergencyAreaID", "cancelledCellsInEAI-NR"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"emergencyAreaID", "cancelledCellsInEAI_NR"};
    }
}
