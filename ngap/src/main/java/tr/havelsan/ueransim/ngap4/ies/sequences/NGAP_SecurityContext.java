package tr.havelsan.ueransim.ngap4.ies.sequences;

import tr.havelsan.ueransim.ngap4.core.NgapSequence;
import tr.havelsan.ueransim.ngap4.ies.bit_strings.NGAP_SecurityKey;
import tr.havelsan.ueransim.ngap4.ies.integers.NGAP_NextHopChainingCount;

public class NGAP_SecurityContext extends NgapSequence {

    public NGAP_NextHopChainingCount nextHopChainingCount;
    public NGAP_SecurityKey nextHopNH;

    @Override
    protected String getAsnName() {
        return "SecurityContext";
    }

    @Override
    protected String getXmlTagName() {
        return "SecurityContext";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"nextHopChainingCount", "nextHopNH"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"nextHopChainingCount", "nextHopNH"};
    }
}
