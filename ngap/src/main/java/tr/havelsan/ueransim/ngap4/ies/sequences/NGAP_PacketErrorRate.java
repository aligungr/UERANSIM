package tr.havelsan.ueransim.ngap4.ies.sequences;

import tr.havelsan.ueransim.ngap4.core.NgapSequence;

public class NGAP_PacketErrorRate extends NgapSequence {

    public long pERScalar;
    public long pERExponent;

    @Override
    protected String getAsnName() {
        return "PacketErrorRate";
    }

    @Override
    protected String getXmlTagName() {
        return "PacketErrorRate";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"pERScalar", "pERExponent"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"pERScalar", "pERExponent"};
    }
}
