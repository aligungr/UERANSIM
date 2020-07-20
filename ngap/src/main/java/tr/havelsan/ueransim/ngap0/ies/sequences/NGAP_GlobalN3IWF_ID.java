package tr.havelsan.ueransim.ngap0.ies.sequences;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.ies.octet_strings.*;
import tr.havelsan.ueransim.ngap0.ies.choices.*;

public class NGAP_GlobalN3IWF_ID extends NGAP_Sequence {

    public NGAP_PLMNIdentity pLMNIdentity;
    public NGAP_N3IWF_ID n3IWF_ID;

    @Override
    public String getAsnName() {
        return "GlobalN3IWF-ID";
    }

    @Override
    public String getXmlTagName() {
        return "GlobalN3IWF-ID";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"pLMNIdentity", "n3IWF-ID"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"pLMNIdentity", "n3IWF_ID"};
    }
}
