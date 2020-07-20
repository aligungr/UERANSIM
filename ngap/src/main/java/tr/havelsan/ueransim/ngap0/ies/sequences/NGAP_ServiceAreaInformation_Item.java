package tr.havelsan.ueransim.ngap0.ies.sequences;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.ies.octet_strings.*;
import tr.havelsan.ueransim.ngap0.ies.sequence_ofs.*;

public class NGAP_ServiceAreaInformation_Item extends NGAP_Sequence {

    public NGAP_PLMNIdentity pLMNIdentity;
    public NGAP_AllowedTACs allowedTACs;
    public NGAP_NotAllowedTACs notAllowedTACs;

    @Override
    public String getAsnName() {
        return "ServiceAreaInformation-Item";
    }

    @Override
    public String getXmlTagName() {
        return "ServiceAreaInformation-Item";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"pLMNIdentity", "allowedTACs", "notAllowedTACs"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"pLMNIdentity", "allowedTACs", "notAllowedTACs"};
    }
}
