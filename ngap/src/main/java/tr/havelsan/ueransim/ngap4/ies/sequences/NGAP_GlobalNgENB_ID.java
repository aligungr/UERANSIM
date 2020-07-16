package tr.havelsan.ueransim.ngap4.ies.sequences;

import tr.havelsan.ueransim.ngap4.core.NgapSequence;
import tr.havelsan.ueransim.ngap4.ies.choices.NGAP_NgENB_ID;
import tr.havelsan.ueransim.ngap4.ies.octet_strings.NGAP_PLMNIdentity;

public class NGAP_GlobalNgENB_ID extends NgapSequence {

    public NGAP_PLMNIdentity pLMNIdentity;
    public NGAP_NgENB_ID ngENB_ID;

    @Override
    protected String getAsnName() {
        return "GlobalNgENB-ID";
    }

    @Override
    protected String getXmlTagName() {
        return "GlobalNgENB-ID";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"pLMNIdentity", "ngENB-ID"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"pLMNIdentity", "ngENB_ID"};
    }
}
