package tr.havelsan.ueransim.ngap0.ies.choices;

import tr.havelsan.ueransim.ngap0.core.*;

public class NGAP_GNB_ID extends NGAP_Choice {

    public NGAP_BitString gNB_ID;

    @Override
    public String getAsnName() {
        return "GNB-ID";
    }

    @Override
    public String getXmlTagName() {
        return "GNB-ID";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"gNB-ID"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"gNB_ID"};
    }
}
