package tr.havelsan.ueransim.ngap0.ies.sequences;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.ies.octet_strings.*;

public class NGAP_S_NSSAI extends NGAP_Sequence {

    public NGAP_SST sST;
    public NGAP_SD sD;

    @Override
    public String getAsnName() {
        return "S-NSSAI";
    }

    @Override
    public String getXmlTagName() {
        return "S-NSSAI";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"sST", "sD"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"sST", "sD"};
    }
}
