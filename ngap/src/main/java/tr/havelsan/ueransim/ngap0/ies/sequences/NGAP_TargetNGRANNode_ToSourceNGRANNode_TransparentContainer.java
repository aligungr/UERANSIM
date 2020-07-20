package tr.havelsan.ueransim.ngap0.ies.sequences;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.ies.octet_strings.*;

public class NGAP_TargetNGRANNode_ToSourceNGRANNode_TransparentContainer extends NGAP_Sequence {

    public NGAP_RRCContainer rRCContainer;

    @Override
    public String getAsnName() {
        return "TargetNGRANNode-ToSourceNGRANNode-TransparentContainer";
    }

    @Override
    public String getXmlTagName() {
        return "TargetNGRANNode-ToSourceNGRANNode-TransparentContainer";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"rRCContainer"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"rRCContainer"};
    }
}
