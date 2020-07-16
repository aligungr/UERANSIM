package tr.havelsan.ueransim.ngap4.ies.choices;

import tr.havelsan.ueransim.ngap4.core.NgapChoice;
import tr.havelsan.ueransim.ngap4.ies.sequences.NGAP_DRBStatusUL12;
import tr.havelsan.ueransim.ngap4.ies.sequences.NGAP_DRBStatusUL18;

public class NGAP_DRBStatusUL extends NgapChoice {

    public NGAP_DRBStatusUL12 dRBStatusUL12;
    public NGAP_DRBStatusUL18 dRBStatusUL18;

    @Override
    protected String getAsnName() {
        return "DRBStatusUL";
    }

    @Override
    protected String getXmlTagName() {
        return "DRBStatusUL";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"dRBStatusUL12", "dRBStatusUL18"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"dRBStatusUL12", "dRBStatusUL18"};
    }
}
