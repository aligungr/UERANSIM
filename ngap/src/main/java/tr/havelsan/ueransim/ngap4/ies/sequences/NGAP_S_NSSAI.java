package tr.havelsan.ueransim.ngap4.ies.sequences;

import tr.havelsan.ueransim.ngap4.core.NgapSequence;
import tr.havelsan.ueransim.ngap4.ies.octet_strings.NGAP_SD;
import tr.havelsan.ueransim.ngap4.ies.octet_strings.NGAP_SST;

public class NGAP_S_NSSAI extends NgapSequence {

    public NGAP_SST sST;
    public NGAP_SD sD;

    @Override
    protected String getAsnName() {
        return "S-NSSAI";
    }

    @Override
    protected String getXmlTagName() {
        return "S-NSSAI";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"sST", "sD"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"sST", "sD"};
    }
}
