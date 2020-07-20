package tr.havelsan.ueransim.ngap0.ies.sequences;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.ies.bit_strings.*;
import tr.havelsan.ueransim.ngap0.ies.octet_strings.*;

public class NGAP_EUTRA_CGI extends NGAP_Sequence {

    public NGAP_PLMNIdentity pLMNIdentity;
    public NGAP_EUTRACellIdentity eUTRACellIdentity;

    @Override
    public String getAsnName() {
        return "EUTRA-CGI";
    }

    @Override
    public String getXmlTagName() {
        return "EUTRA-CGI";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"pLMNIdentity", "eUTRACellIdentity"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"pLMNIdentity", "eUTRACellIdentity"};
    }
}
