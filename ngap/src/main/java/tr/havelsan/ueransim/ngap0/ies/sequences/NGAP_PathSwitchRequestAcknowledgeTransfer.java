package tr.havelsan.ueransim.ngap0.ies.sequences;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.ies.choices.*;

public class NGAP_PathSwitchRequestAcknowledgeTransfer extends NGAP_Sequence {

    public NGAP_UPTransportLayerInformation uL_NGU_UP_TNLInformation;
    public NGAP_SecurityIndication securityIndication;

    @Override
    public String getAsnName() {
        return "PathSwitchRequestAcknowledgeTransfer";
    }

    @Override
    public String getXmlTagName() {
        return "PathSwitchRequestAcknowledgeTransfer";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"uL-NGU-UP-TNLInformation", "securityIndication"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"uL_NGU_UP_TNLInformation", "securityIndication"};
    }
}
