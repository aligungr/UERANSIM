package tr.havelsan.ueransim.ngap4.ies.sequences;

import tr.havelsan.ueransim.ngap4.core.NgapSequence;
import tr.havelsan.ueransim.ngap4.ies.integers.NGAP_AMF_UE_NGAP_ID;
import tr.havelsan.ueransim.ngap4.ies.integers.NGAP_RAN_UE_NGAP_ID;

public class NGAP_UE_NGAP_ID_pair extends NgapSequence {

    public NGAP_AMF_UE_NGAP_ID aMF_UE_NGAP_ID;
    public NGAP_RAN_UE_NGAP_ID rAN_UE_NGAP_ID;

    @Override
    protected String getAsnName() {
        return "UE-NGAP-ID-pair";
    }

    @Override
    protected String getXmlTagName() {
        return "UE-NGAP-ID-pair";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"aMF-UE-NGAP-ID", "rAN-UE-NGAP-ID"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"aMF_UE_NGAP_ID", "rAN_UE_NGAP_ID"};
    }
}
