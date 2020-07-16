package tr.havelsan.ueransim.ngap4.ies.choices;

import tr.havelsan.ueransim.ngap4.core.NgapBitString;
import tr.havelsan.ueransim.ngap4.core.NgapChoice;

public class NGAP_GNB_ID extends NgapChoice {

    public NgapBitString gNB_ID;

    @Override
    protected String getAsnName() {
        return "GNB-ID";
    }

    @Override
    protected String getXmlTagName() {
        return "GNB-ID";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"gNB-ID"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"gNB_ID"};
    }
}
