package tr.havelsan.ueransim.ngap4.ies.sequences;

import tr.havelsan.ueransim.ngap4.core.NgapSequence;
import tr.havelsan.ueransim.ngap4.ies.choices.NGAP_SONInformation;

public class NGAP_SONConfigurationTransfer extends NgapSequence {

    public NGAP_TargetRANNodeID targetRANNodeID;
    public NGAP_SourceRANNodeID sourceRANNodeID;
    public NGAP_SONInformation sONInformation;
    public NGAP_XnTNLConfigurationInfo xnTNLConfigurationInfo;

    @Override
    protected String getAsnName() {
        return "SONConfigurationTransfer";
    }

    @Override
    protected String getXmlTagName() {
        return "SONConfigurationTransfer";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"targetRANNodeID", "sourceRANNodeID", "sONInformation", "xnTNLConfigurationInfo"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"targetRANNodeID", "sourceRANNodeID", "sONInformation", "xnTNLConfigurationInfo"};
    }
}
