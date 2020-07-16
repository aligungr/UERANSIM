package tr.havelsan.ueransim.ngap4.ies.sequences;

import tr.havelsan.ueransim.ngap4.core.NgapSequence;
import tr.havelsan.ueransim.ngap4.ies.choices.NGAP_GNB_ID;
import tr.havelsan.ueransim.ngap4.ies.octet_strings.NGAP_PLMNIdentity;

public class NGAP_GlobalGNB_ID extends NgapSequence {

    public NGAP_PLMNIdentity pLMNIdentity;
    public NGAP_GNB_ID gNB_ID;

    @Override
    protected String getAsnName() {
        return "GlobalGNB-ID";
    }

    @Override
    protected String getXmlTagName() {
        return "GlobalGNB-ID";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"pLMNIdentity", "gNB-ID"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"pLMNIdentity", "gNB_ID"};
    }
}
