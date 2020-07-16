package tr.havelsan.ueransim.ngap4.ies.sequences;

import tr.havelsan.ueransim.ngap4.core.NgapSequence;

public class NGAP_SONInformationReply extends NgapSequence {

    public NGAP_XnTNLConfigurationInfo xnTNLConfigurationInfo;

    @Override
    protected String getAsnName() {
        return "SONInformationReply";
    }

    @Override
    protected String getXmlTagName() {
        return "SONInformationReply";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"xnTNLConfigurationInfo"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"xnTNLConfigurationInfo"};
    }
}
