package tr.havelsan.ueransim.ngap0.ies.choices;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.ies.sequences.*;

public class NGAP_UEPagingIdentity extends NGAP_Choice {

    public NGAP_FiveG_S_TMSI fiveG_S_TMSI;

    @Override
    public String getAsnName() {
        return "UEPagingIdentity";
    }

    @Override
    public String getXmlTagName() {
        return "UEPagingIdentity";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"fiveG-S-TMSI"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"fiveG_S_TMSI"};
    }
}
