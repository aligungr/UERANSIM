package tr.havelsan.ueransim.ngap4.ies.sequences;

import tr.havelsan.ueransim.ngap4.core.NgapSequence;
import tr.havelsan.ueransim.ngap4.ies.choices.NGAP_N3IWF_ID;
import tr.havelsan.ueransim.ngap4.ies.octet_strings.NGAP_PLMNIdentity;

public class NGAP_GlobalN3IWF_ID extends NgapSequence {

    public NGAP_PLMNIdentity pLMNIdentity;
    public NGAP_N3IWF_ID n3IWF_ID;

    @Override
    protected String getAsnName() {
        return "GlobalN3IWF-ID";
    }

    @Override
    protected String getXmlTagName() {
        return "GlobalN3IWF-ID";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"pLMNIdentity", "n3IWF-ID"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"pLMNIdentity", "n3IWF_ID"};
    }
}
