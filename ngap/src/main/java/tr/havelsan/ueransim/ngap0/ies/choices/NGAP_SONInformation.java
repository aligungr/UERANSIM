package tr.havelsan.ueransim.ngap0.ies.choices;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.ies.sequences.*;
import tr.havelsan.ueransim.ngap0.ies.enumerations.*;

public class NGAP_SONInformation extends NGAP_Choice {

    public NGAP_SONInformationRequest sONInformationRequest;
    public NGAP_SONInformationReply sONInformationReply;

    @Override
    public String getAsnName() {
        return "SONInformation";
    }

    @Override
    public String getXmlTagName() {
        return "SONInformation";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"sONInformationRequest", "sONInformationReply"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"sONInformationRequest", "sONInformationReply"};
    }
}
