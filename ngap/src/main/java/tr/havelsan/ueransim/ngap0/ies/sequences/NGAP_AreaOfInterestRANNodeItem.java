package tr.havelsan.ueransim.ngap0.ies.sequences;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.ies.choices.*;

public class NGAP_AreaOfInterestRANNodeItem extends NGAP_Sequence {

    public NGAP_GlobalRANNodeID globalRANNodeID;

    @Override
    public String getAsnName() {
        return "AreaOfInterestRANNodeItem";
    }

    @Override
    public String getXmlTagName() {
        return "AreaOfInterestRANNodeItem";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"globalRANNodeID"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"globalRANNodeID"};
    }
}
