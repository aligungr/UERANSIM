package tr.havelsan.ueransim.ngap4.ies.sequences;

import tr.havelsan.ueransim.ngap4.core.NgapSequence;

public class NGAP_DRBStatusDL12 extends NgapSequence {

    public NGAP_COUNTValueForPDCP_SN12 dL_COUNTValue;

    @Override
    protected String getAsnName() {
        return "DRBStatusDL12";
    }

    @Override
    protected String getXmlTagName() {
        return "DRBStatusDL12";
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
