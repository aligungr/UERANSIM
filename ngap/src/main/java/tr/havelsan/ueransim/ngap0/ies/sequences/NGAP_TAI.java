package tr.havelsan.ueransim.ngap0.ies.sequences;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.ies.octet_strings.*;

public class NGAP_TAI extends NGAP_Sequence {

    public NGAP_PLMNIdentity pLMNIdentity;
    public NGAP_TAC tAC;

    @Override
    public String getAsnName() {
        return "TAI";
    }

    @Override
    public String getXmlTagName() {
        return "TAI";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"pLMNIdentity", "tAC"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"pLMNIdentity", "tAC"};
    }
}
