package tr.havelsan.ueransim.ngap0.ies.sequences;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.ies.sequence_ofs.*;
import tr.havelsan.ueransim.ngap0.ies.choices.*;
import tr.havelsan.ueransim.ngap0.ies.enumerations.*;

public class NGAP_PathSwitchRequestTransfer extends NGAP_Sequence {

    public NGAP_UPTransportLayerInformation dL_NGU_UP_TNLInformation;
    public NGAP_DL_NGU_TNLInformationReused dL_NGU_TNLInformationReused;
    public NGAP_UserPlaneSecurityInformation userPlaneSecurityInformation;
    public NGAP_QosFlowAcceptedList qosFlowAcceptedList;

    @Override
    public String getAsnName() {
        return "PathSwitchRequestTransfer";
    }

    @Override
    public String getXmlTagName() {
        return "PathSwitchRequestTransfer";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"dL-NGU-UP-TNLInformation", "dL-NGU-TNLInformationReused", "userPlaneSecurityInformation", "qosFlowAcceptedList"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"dL_NGU_UP_TNLInformation", "dL_NGU_TNLInformationReused", "userPlaneSecurityInformation", "qosFlowAcceptedList"};
    }
}
