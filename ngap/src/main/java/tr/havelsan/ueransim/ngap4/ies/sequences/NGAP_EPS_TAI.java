package tr.havelsan.ueransim.ngap4.ies.sequences;

import tr.havelsan.ueransim.ngap4.core.NgapSequence;
import tr.havelsan.ueransim.ngap4.ies.octet_strings.NGAP_EPS_TAC;
import tr.havelsan.ueransim.ngap4.ies.octet_strings.NGAP_PLMNIdentity;

public class NGAP_EPS_TAI extends NgapSequence {

    public NGAP_PLMNIdentity pLMNIdentity;
    public NGAP_EPS_TAC ePS_TAC;

    @Override
    protected String getAsnName() {
        return "EPS-TAI";
    }

    @Override
    protected String getXmlTagName() {
        return "EPS-TAI";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"pLMNIdentity", "ePS-TAC"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"pLMNIdentity", "ePS_TAC"};
    }
}
