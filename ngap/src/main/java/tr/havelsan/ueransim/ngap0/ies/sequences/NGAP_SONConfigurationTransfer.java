package tr.havelsan.ueransim.ngap0.ies.sequences;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.ies.choices.*;

public class NGAP_SONConfigurationTransfer extends NGAP_Sequence {

    public NGAP_TargetRANNodeID targetRANNodeID;
    public NGAP_SourceRANNodeID sourceRANNodeID;
    public NGAP_SONInformation sONInformation;
    public NGAP_XnTNLConfigurationInfo xnTNLConfigurationInfo;

    @Override
    public String getAsnName() {
        return "SONConfigurationTransfer";
    }

    @Override
    public String getXmlTagName() {
        return "SONConfigurationTransfer";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"targetRANNodeID", "sourceRANNodeID", "sONInformation", "xnTNLConfigurationInfo"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"targetRANNodeID", "sourceRANNodeID", "sONInformation", "xnTNLConfigurationInfo"};
    }
}
