package tr.havelsan.ueransim.ngap0.ies.sequences;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.ies.octet_strings.*;
import tr.havelsan.ueransim.ngap0.ies.choices.*;

public class NGAP_GlobalNgENB_ID extends NGAP_Sequence {

    public NGAP_PLMNIdentity pLMNIdentity;
    public NGAP_NgENB_ID ngENB_ID;

    @Override
    public String getAsnName() {
        return "GlobalNgENB-ID";
    }

    @Override
    public String getXmlTagName() {
        return "GlobalNgENB-ID";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"pLMNIdentity", "ngENB-ID"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"pLMNIdentity", "ngENB_ID"};
    }
}
