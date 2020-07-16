package tr.havelsan.ueransim.ngap4.ies.sequences;

import tr.havelsan.ueransim.ngap4.core.NgapSequence;
import tr.havelsan.ueransim.ngap4.ies.choices.NGAP_UPTransportLayerInformation;

public class NGAP_PathSwitchRequestAcknowledgeTransfer extends NgapSequence {

    public NGAP_UPTransportLayerInformation uL_NGU_UP_TNLInformation;
    public NGAP_SecurityIndication securityIndication;

    @Override
    protected String getAsnName() {
        return "PathSwitchRequestAcknowledgeTransfer";
    }

    @Override
    protected String getXmlTagName() {
        return "PathSwitchRequestAcknowledgeTransfer";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"uL-NGU-UP-TNLInformation", "securityIndication"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"uL_NGU_UP_TNLInformation", "securityIndication"};
    }
}
