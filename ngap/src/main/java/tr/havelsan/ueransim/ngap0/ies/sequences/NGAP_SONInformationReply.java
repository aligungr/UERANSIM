package tr.havelsan.ueransim.ngap0.ies.sequences;

import tr.havelsan.ueransim.ngap0.core.*;

public class NGAP_SONInformationReply extends NGAP_Sequence {

    public NGAP_XnTNLConfigurationInfo xnTNLConfigurationInfo;

    @Override
    public String getAsnName() {
        return "SONInformationReply";
    }

    @Override
    public String getXmlTagName() {
        return "SONInformationReply";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"xnTNLConfigurationInfo"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"xnTNLConfigurationInfo"};
    }
}
