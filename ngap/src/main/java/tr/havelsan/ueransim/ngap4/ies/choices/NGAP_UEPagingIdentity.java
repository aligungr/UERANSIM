package tr.havelsan.ueransim.ngap4.ies.choices;

import tr.havelsan.ueransim.ngap4.core.NgapChoice;
import tr.havelsan.ueransim.ngap4.ies.sequences.NGAP_FiveG_S_TMSI;

public class NGAP_UEPagingIdentity extends NgapChoice {

    public NGAP_FiveG_S_TMSI fiveG_S_TMSI;

    @Override
    protected String getAsnName() {
        return "UEPagingIdentity";
    }

    @Override
    protected String getXmlTagName() {
        return "UEPagingIdentity";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"fiveG-S-TMSI"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"fiveG_S_TMSI"};
    }
}
