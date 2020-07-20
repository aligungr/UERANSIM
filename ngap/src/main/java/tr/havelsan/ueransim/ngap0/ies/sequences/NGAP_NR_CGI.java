package tr.havelsan.ueransim.ngap0.ies.sequences;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.ies.bit_strings.*;
import tr.havelsan.ueransim.ngap0.ies.octet_strings.*;

public class NGAP_NR_CGI extends NGAP_Sequence {

    public NGAP_PLMNIdentity pLMNIdentity;
    public NGAP_NRCellIdentity nRCellIdentity;

    @Override
    public String getAsnName() {
        return "NR-CGI";
    }

    @Override
    public String getXmlTagName() {
        return "NR-CGI";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"pLMNIdentity", "nRCellIdentity"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"pLMNIdentity", "nRCellIdentity"};
    }
}
