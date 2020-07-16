package tr.havelsan.ueransim.ngap4.ies.sequences;

import tr.havelsan.ueransim.ngap4.core.NgapSequence;
import tr.havelsan.ueransim.ngap4.ies.octet_strings.NGAP_PLMNIdentity;
import tr.havelsan.ueransim.ngap4.ies.octet_strings.NGAP_TAC;

public class NGAP_TAI extends NgapSequence {

    public NGAP_PLMNIdentity pLMNIdentity;
    public NGAP_TAC tAC;

    @Override
    protected String getAsnName() {
        return "TAI";
    }

    @Override
    protected String getXmlTagName() {
        return "TAI";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"pLMNIdentity", "tAC"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"pLMNIdentity", "tAC"};
    }
}
