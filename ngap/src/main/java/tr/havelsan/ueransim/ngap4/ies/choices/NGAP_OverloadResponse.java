package tr.havelsan.ueransim.ngap4.ies.choices;

import tr.havelsan.ueransim.ngap4.core.NgapChoice;
import tr.havelsan.ueransim.ngap4.ies.enumerations.NGAP_OverloadAction;

public class NGAP_OverloadResponse extends NgapChoice {

    public NGAP_OverloadAction overloadAction;

    @Override
    protected String getAsnName() {
        return "OverloadResponse";
    }

    @Override
    protected String getXmlTagName() {
        return "OverloadResponse";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"overloadAction"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"overloadAction"};
    }
}
