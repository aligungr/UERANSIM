package tr.havelsan.ueransim.ngap0.ies.sequences;

import tr.havelsan.ueransim.ngap0.core.*;

public class NGAP_DRBStatusDL18 extends NGAP_Sequence {

    public NGAP_COUNTValueForPDCP_SN18 dL_COUNTValue;

    @Override
    public String getAsnName() {
        return "DRBStatusDL18";
    }

    @Override
    public String getXmlTagName() {
        return "DRBStatusDL18";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"dL-COUNTValue"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"dL_COUNTValue"};
    }
}
