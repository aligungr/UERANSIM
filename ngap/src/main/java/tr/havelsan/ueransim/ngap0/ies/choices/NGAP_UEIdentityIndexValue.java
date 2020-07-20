package tr.havelsan.ueransim.ngap0.ies.choices;

import tr.havelsan.ueransim.ngap0.core.*;

public class NGAP_UEIdentityIndexValue extends NGAP_Choice {

    public NGAP_BitString indexLength10;

    @Override
    public String getAsnName() {
        return "UEIdentityIndexValue";
    }

    @Override
    public String getXmlTagName() {
        return "UEIdentityIndexValue";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"indexLength10"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"indexLength10"};
    }
}
