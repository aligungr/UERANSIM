package tr.havelsan.ueransim.ngap4.ies.sequences;

import tr.havelsan.ueransim.ngap4.core.NgapSequence;
import tr.havelsan.ueransim.ngap4.ies.octet_strings.NGAP_EmergencyAreaID;
import tr.havelsan.ueransim.ngap4.ies.sequence_ofs.NGAP_CancelledCellsInEAI_EUTRA;

public class NGAP_EmergencyAreaIDCancelledEUTRA_Item extends NgapSequence {

    public NGAP_EmergencyAreaID emergencyAreaID;
    public NGAP_CancelledCellsInEAI_EUTRA cancelledCellsInEAI_EUTRA;

    @Override
    protected String getAsnName() {
        return "EmergencyAreaIDCancelledEUTRA-Item";
    }

    @Override
    protected String getXmlTagName() {
        return "EmergencyAreaIDCancelledEUTRA-Item";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"emergencyAreaID", "cancelledCellsInEAI-EUTRA"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"emergencyAreaID", "cancelledCellsInEAI_EUTRA"};
    }
}
