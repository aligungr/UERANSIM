package tr.havelsan.ueransim.ngap4.ies.choices;

import tr.havelsan.ueransim.ngap4.core.NgapChoice;
import tr.havelsan.ueransim.ngap4.ies.sequences.NGAP_DRBStatusDL12;
import tr.havelsan.ueransim.ngap4.ies.sequences.NGAP_DRBStatusDL18;

public class NGAP_DRBStatusDL extends NgapChoice {

    public NGAP_DRBStatusDL12 dRBStatusDL12;
    public NGAP_DRBStatusDL18 dRBStatusDL18;

    @Override
    protected String getAsnName() {
        return "DRBStatusDL";
    }

    @Override
    protected String getXmlTagName() {
        return "DRBStatusDL";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"dRBStatusDL12", "dRBStatusDL18"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"dRBStatusDL12", "dRBStatusDL18"};
    }
}
