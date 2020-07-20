package tr.havelsan.ueransim.ngap0.ies.choices;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.ies.enumerations.*;

public class NGAP_OverloadResponse extends NGAP_Choice {

    public NGAP_OverloadAction overloadAction;

    @Override
    public String getAsnName() {
        return "OverloadResponse";
    }

    @Override
    public String getXmlTagName() {
        return "OverloadResponse";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"overloadAction"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"overloadAction"};
    }
}
