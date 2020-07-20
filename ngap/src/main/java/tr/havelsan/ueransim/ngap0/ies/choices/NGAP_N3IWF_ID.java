package tr.havelsan.ueransim.ngap0.ies.choices;

import tr.havelsan.ueransim.ngap0.core.*;

public class NGAP_N3IWF_ID extends NGAP_Choice {

    public NGAP_BitString n3IWF_ID;

    @Override
    public String getAsnName() {
        return "N3IWF-ID";
    }

    @Override
    public String getXmlTagName() {
        return "N3IWF-ID";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"n3IWF-ID"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"n3IWF_ID"};
    }
}
