package tr.havelsan.ueransim.ngap4.ies.choices;

import tr.havelsan.ueransim.ngap4.core.NgapChoice;
import tr.havelsan.ueransim.ngap4.ies.sequence_ofs.NGAP_EUTRA_CGIListForWarning;
import tr.havelsan.ueransim.ngap4.ies.sequence_ofs.NGAP_EmergencyAreaIDList;
import tr.havelsan.ueransim.ngap4.ies.sequence_ofs.NGAP_NR_CGIListForWarning;
import tr.havelsan.ueransim.ngap4.ies.sequence_ofs.NGAP_TAIListForWarning;

public class NGAP_WarningAreaList extends NgapChoice {

    public NGAP_EUTRA_CGIListForWarning eUTRA_CGIListForWarning;
    public NGAP_NR_CGIListForWarning nR_CGIListForWarning;
    public NGAP_TAIListForWarning tAIListForWarning;
    public NGAP_EmergencyAreaIDList emergencyAreaIDList;

    @Override
    protected String getAsnName() {
        return "WarningAreaList";
    }

    @Override
    protected String getXmlTagName() {
        return "WarningAreaList";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"eUTRA-CGIListForWarning", "nR-CGIListForWarning", "tAIListForWarning", "emergencyAreaIDList"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"eUTRA_CGIListForWarning", "nR_CGIListForWarning", "tAIListForWarning", "emergencyAreaIDList"};
    }
}
