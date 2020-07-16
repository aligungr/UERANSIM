package tr.havelsan.ueransim.ngap4.ies.sequences;

import tr.havelsan.ueransim.ngap4.core.NgapSequence;
import tr.havelsan.ueransim.ngap4.ies.bit_strings.NGAP_NRCellIdentity;
import tr.havelsan.ueransim.ngap4.ies.octet_strings.NGAP_PLMNIdentity;

public class NGAP_NR_CGI extends NgapSequence {

    public NGAP_PLMNIdentity pLMNIdentity;
    public NGAP_NRCellIdentity nRCellIdentity;

    @Override
    protected String getAsnName() {
        return "NR-CGI";
    }

    @Override
    protected String getXmlTagName() {
        return "NR-CGI";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"pLMNIdentity", "nRCellIdentity"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"pLMNIdentity", "nRCellIdentity"};
    }
}
