package tr.havelsan.ueransim.ngap4.ies.sequences;

import tr.havelsan.ueransim.ngap4.core.NgapSequence;
import tr.havelsan.ueransim.ngap4.ies.choices.NGAP_GlobalRANNodeID;

public class NGAP_AreaOfInterestRANNodeItem extends NgapSequence {

    public NGAP_GlobalRANNodeID globalRANNodeID;

    @Override
    protected String getAsnName() {
        return "AreaOfInterestRANNodeItem";
    }

    @Override
    protected String getXmlTagName() {
        return "AreaOfInterestRANNodeItem";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"globalRANNodeID"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"globalRANNodeID"};
    }
}
