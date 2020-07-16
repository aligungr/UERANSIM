package tr.havelsan.ueransim.ngap4.ies.sequences;

import tr.havelsan.ueransim.ngap4.core.NgapSequence;
import tr.havelsan.ueransim.ngap4.ies.choices.NGAP_GlobalRANNodeID;

public class NGAP_TargetRANNodeID extends NgapSequence {

    public NGAP_GlobalRANNodeID globalRANNodeID;
    public NGAP_TAI selectedTAI;

    @Override
    protected String getAsnName() {
        return "TargetRANNodeID";
    }

    @Override
    protected String getXmlTagName() {
        return "TargetRANNodeID";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"globalRANNodeID", "selectedTAI"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"globalRANNodeID", "selectedTAI"};
    }
}
