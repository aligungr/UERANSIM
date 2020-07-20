package tr.havelsan.ueransim.ngap0.ies.sequences;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.ies.choices.*;

public class NGAP_SourceRANNodeID extends NGAP_Sequence {

    public NGAP_GlobalRANNodeID globalRANNodeID;
    public NGAP_TAI selectedTAI;

    @Override
    public String getAsnName() {
        return "SourceRANNodeID";
    }

    @Override
    public String getXmlTagName() {
        return "SourceRANNodeID";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"globalRANNodeID", "selectedTAI"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"globalRANNodeID", "selectedTAI"};
    }
}
