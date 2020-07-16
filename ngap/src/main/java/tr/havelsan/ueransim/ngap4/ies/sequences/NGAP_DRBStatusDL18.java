package tr.havelsan.ueransim.ngap4.ies.sequences;

import tr.havelsan.ueransim.ngap4.core.NgapSequence;

public class NGAP_DRBStatusDL18 extends NgapSequence {

    public NGAP_COUNTValueForPDCP_SN18 dL_COUNTValue;

    @Override
    protected String getAsnName() {
        return "DRBStatusDL18";
    }

    @Override
    protected String getXmlTagName() {
        return "DRBStatusDL18";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"dL-COUNTValue"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"dL_COUNTValue"};
    }
}
