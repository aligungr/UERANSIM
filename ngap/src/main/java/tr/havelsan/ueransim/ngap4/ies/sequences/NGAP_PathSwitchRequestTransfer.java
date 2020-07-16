package tr.havelsan.ueransim.ngap4.ies.sequences;

import tr.havelsan.ueransim.ngap4.core.NgapSequence;
import tr.havelsan.ueransim.ngap4.ies.choices.NGAP_UPTransportLayerInformation;
import tr.havelsan.ueransim.ngap4.ies.enumerations.NGAP_DL_NGU_TNLInformationReused;
import tr.havelsan.ueransim.ngap4.ies.sequence_ofs.NGAP_QosFlowAcceptedList;

public class NGAP_PathSwitchRequestTransfer extends NgapSequence {

    public NGAP_UPTransportLayerInformation dL_NGU_UP_TNLInformation;
    public NGAP_DL_NGU_TNLInformationReused dL_NGU_TNLInformationReused;
    public NGAP_UserPlaneSecurityInformation userPlaneSecurityInformation;
    public NGAP_QosFlowAcceptedList qosFlowAcceptedList;

    @Override
    protected String getAsnName() {
        return "PathSwitchRequestTransfer";
    }

    @Override
    protected String getXmlTagName() {
        return "PathSwitchRequestTransfer";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"dL-NGU-UP-TNLInformation", "dL-NGU-TNLInformationReused", "userPlaneSecurityInformation", "qosFlowAcceptedList"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"dL_NGU_UP_TNLInformation", "dL_NGU_TNLInformationReused", "userPlaneSecurityInformation", "qosFlowAcceptedList"};
    }
}
