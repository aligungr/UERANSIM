package tr.havelsan.ueransim.ngap4.ies.choices;

import tr.havelsan.ueransim.ngap4.core.NgapChoice;
import tr.havelsan.ueransim.ngap4.ies.enumerations.NGAP_SONInformationRequest;
import tr.havelsan.ueransim.ngap4.ies.sequences.NGAP_SONInformationReply;

public class NGAP_SONInformation extends NgapChoice {

    public NGAP_SONInformationRequest sONInformationRequest;
    public NGAP_SONInformationReply sONInformationReply;

    @Override
    protected String getAsnName() {
        return "SONInformation";
    }

    @Override
    protected String getXmlTagName() {
        return "SONInformation";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"sONInformationRequest", "sONInformationReply"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"sONInformationRequest", "sONInformationReply"};
    }
}
