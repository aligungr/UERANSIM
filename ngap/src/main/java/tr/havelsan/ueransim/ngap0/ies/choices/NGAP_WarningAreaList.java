package tr.havelsan.ueransim.ngap0.ies.choices;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.ies.sequence_ofs.*;

public class NGAP_WarningAreaList extends NGAP_Choice {

    public NGAP_EUTRA_CGIListForWarning eUTRA_CGIListForWarning;
    public NGAP_NR_CGIListForWarning nR_CGIListForWarning;
    public NGAP_TAIListForWarning tAIListForWarning;
    public NGAP_EmergencyAreaIDList emergencyAreaIDList;

    @Override
    public String getAsnName() {
        return "WarningAreaList";
    }

    @Override
    public String getXmlTagName() {
        return "WarningAreaList";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"eUTRA-CGIListForWarning", "nR-CGIListForWarning", "tAIListForWarning", "emergencyAreaIDList"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"eUTRA_CGIListForWarning", "nR_CGIListForWarning", "tAIListForWarning", "emergencyAreaIDList"};
    }
}
