package tr.havelsan.ueransim.ngap4.ies.sequences;

import tr.havelsan.ueransim.ngap4.core.NgapSequence;
import tr.havelsan.ueransim.ngap4.ies.bit_strings.NGAP_EUTRACellIdentity;
import tr.havelsan.ueransim.ngap4.ies.octet_strings.NGAP_PLMNIdentity;

public class NGAP_EUTRA_CGI extends NgapSequence {

    public NGAP_PLMNIdentity pLMNIdentity;
    public NGAP_EUTRACellIdentity eUTRACellIdentity;

    @Override
    protected String getAsnName() {
        return "EUTRA-CGI";
    }

    @Override
    protected String getXmlTagName() {
        return "EUTRA-CGI";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"pLMNIdentity", "eUTRACellIdentity"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"pLMNIdentity", "eUTRACellIdentity"};
    }
}
