package tr.havelsan.ueransim.ngap0.ies.sequences;

import tr.havelsan.ueransim.ngap0.core.*;

public class NGAP_PacketErrorRate extends NGAP_Sequence {

    public NGAP_Integer pERScalar;
    public NGAP_Integer pERExponent;

    @Override
    public String getAsnName() {
        return "PacketErrorRate";
    }

    @Override
    public String getXmlTagName() {
        return "PacketErrorRate";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"pERScalar", "pERExponent"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"pERScalar", "pERExponent"};
    }
}
