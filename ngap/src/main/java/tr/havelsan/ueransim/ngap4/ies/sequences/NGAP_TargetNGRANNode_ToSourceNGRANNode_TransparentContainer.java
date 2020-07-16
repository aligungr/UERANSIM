package tr.havelsan.ueransim.ngap4.ies.sequences;

import tr.havelsan.ueransim.ngap4.core.NgapSequence;
import tr.havelsan.ueransim.ngap4.ies.octet_strings.NGAP_RRCContainer;

public class NGAP_TargetNGRANNode_ToSourceNGRANNode_TransparentContainer extends NgapSequence {

    public NGAP_RRCContainer rRCContainer;

    @Override
    protected String getAsnName() {
        return "TargetNGRANNode-ToSourceNGRANNode-TransparentContainer";
    }

    @Override
    protected String getXmlTagName() {
        return "TargetNGRANNode-ToSourceNGRANNode-TransparentContainer";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"rRCContainer"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"rRCContainer"};
    }
}
