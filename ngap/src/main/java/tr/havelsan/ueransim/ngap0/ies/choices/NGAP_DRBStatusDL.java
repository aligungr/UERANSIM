package tr.havelsan.ueransim.ngap0.ies.choices;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.ies.sequences.*;

public class NGAP_DRBStatusDL extends NGAP_Choice {

    public NGAP_DRBStatusDL12 dRBStatusDL12;
    public NGAP_DRBStatusDL18 dRBStatusDL18;

    @Override
    public String getAsnName() {
        return "DRBStatusDL";
    }

    @Override
    public String getXmlTagName() {
        return "DRBStatusDL";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"dRBStatusDL12", "dRBStatusDL18"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"dRBStatusDL12", "dRBStatusDL18"};
    }
}
