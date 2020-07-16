package tr.havelsan.ueransim.ngap4.ies.choices;

import tr.havelsan.ueransim.ngap4.core.NgapBitString;
import tr.havelsan.ueransim.ngap4.core.NgapChoice;

public class NGAP_N3IWF_ID extends NgapChoice {

    public NgapBitString n3IWF_ID;

    @Override
    protected String getAsnName() {
        return "N3IWF-ID";
    }

    @Override
    protected String getXmlTagName() {
        return "N3IWF-ID";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"n3IWF-ID"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"n3IWF_ID"};
    }
}
