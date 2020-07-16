package tr.havelsan.ueransim.ngap4.ies.choices;

import tr.havelsan.ueransim.ngap4.core.NgapChoice;
import tr.havelsan.ueransim.ngap4.ies.integers.NGAP_AMF_UE_NGAP_ID;
import tr.havelsan.ueransim.ngap4.ies.sequences.NGAP_UE_NGAP_ID_pair;

public class NGAP_UE_NGAP_IDs extends NgapChoice {

    public NGAP_UE_NGAP_ID_pair uE_NGAP_ID_pair;
    public NGAP_AMF_UE_NGAP_ID aMF_UE_NGAP_ID;

    @Override
    protected String getAsnName() {
        return "UE-NGAP-IDs";
    }

    @Override
    protected String getXmlTagName() {
        return "UE-NGAP-IDs";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"uE-NGAP-ID-pair", "aMF-UE-NGAP-ID"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"uE_NGAP_ID_pair", "aMF_UE_NGAP_ID"};
    }
}
