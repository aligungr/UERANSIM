package tr.havelsan.ueransim.ngap4.ies.sequences;

import tr.havelsan.ueransim.ngap4.core.NgapSequence;
import tr.havelsan.ueransim.ngap4.ies.choices.NGAP_UPTransportLayerInformation;
import tr.havelsan.ueransim.ngap4.ies.integers.NGAP_DRB_ID;

public class NGAP_DataForwardingResponseDRBItem extends NgapSequence {

    public NGAP_DRB_ID dRB_ID;
    public NGAP_UPTransportLayerInformation dLForwardingUP_TNLInformation;
    public NGAP_UPTransportLayerInformation uLForwardingUP_TNLInformation;

    @Override
    protected String getAsnName() {
        return "DataForwardingResponseDRBItem";
    }

    @Override
    protected String getXmlTagName() {
        return "DataForwardingResponseDRBItem";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"dRB-ID", "dLForwardingUP-TNLInformation", "uLForwardingUP-TNLInformation"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"dRB_ID", "dLForwardingUP_TNLInformation", "uLForwardingUP_TNLInformation"};
    }
}
