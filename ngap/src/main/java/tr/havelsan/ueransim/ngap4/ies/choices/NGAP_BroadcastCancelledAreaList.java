package tr.havelsan.ueransim.ngap4.ies.choices;

import tr.havelsan.ueransim.ngap4.core.NgapChoice;
import tr.havelsan.ueransim.ngap4.ies.sequence_ofs.*;

public class NGAP_BroadcastCancelledAreaList extends NgapChoice {

    public NGAP_CellIDCancelledEUTRA cellIDCancelledEUTRA;
    public NGAP_TAICancelledEUTRA tAICancelledEUTRA;
    public NGAP_EmergencyAreaIDCancelledEUTRA emergencyAreaIDCancelledEUTRA;
    public NGAP_CellIDCancelledNR cellIDCancelledNR;
    public NGAP_TAICancelledNR tAICancelledNR;
    public NGAP_EmergencyAreaIDCancelledNR emergencyAreaIDCancelledNR;

    @Override
    protected String getAsnName() {
        return "BroadcastCancelledAreaList";
    }

    @Override
    protected String getXmlTagName() {
        return "BroadcastCancelledAreaList";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"cellIDCancelledEUTRA", "tAICancelledEUTRA", "emergencyAreaIDCancelledEUTRA", "cellIDCancelledNR", "tAICancelledNR", "emergencyAreaIDCancelledNR"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"cellIDCancelledEUTRA", "tAICancelledEUTRA", "emergencyAreaIDCancelledEUTRA", "cellIDCancelledNR", "tAICancelledNR", "emergencyAreaIDCancelledNR"};
    }
}
