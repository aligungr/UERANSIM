package tr.havelsan.ueransim.ngap0.ies.sequences;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.ies.bit_strings.*;
import tr.havelsan.ueransim.ngap0.ies.integers.*;

public class NGAP_SecurityContext extends NGAP_Sequence {

    public NGAP_NextHopChainingCount nextHopChainingCount;
    public NGAP_SecurityKey nextHopNH;

    @Override
    public String getAsnName() {
        return "SecurityContext";
    }

    @Override
    public String getXmlTagName() {
        return "SecurityContext";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"nextHopChainingCount", "nextHopNH"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"nextHopChainingCount", "nextHopNH"};
    }
}
