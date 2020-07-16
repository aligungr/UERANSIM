package tr.havelsan.ueransim.ngap4.ies.choices;

import tr.havelsan.ueransim.ngap4.core.NgapBitString;
import tr.havelsan.ueransim.ngap4.core.NgapChoice;

public class NGAP_UEIdentityIndexValue extends NgapChoice {

    public NgapBitString indexLength10;

    @Override
    protected String getAsnName() {
        return "UEIdentityIndexValue";
    }

    @Override
    protected String getXmlTagName() {
        return "UEIdentityIndexValue";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"indexLength10"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"indexLength10"};
    }
}
