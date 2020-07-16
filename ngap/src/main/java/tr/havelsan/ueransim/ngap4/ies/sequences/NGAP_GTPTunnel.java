package tr.havelsan.ueransim.ngap4.ies.sequences;

import tr.havelsan.ueransim.ngap4.core.NgapSequence;
import tr.havelsan.ueransim.ngap4.ies.bit_strings.NGAP_TransportLayerAddress;
import tr.havelsan.ueransim.ngap4.ies.octet_strings.NGAP_GTP_TEID;

public class NGAP_GTPTunnel extends NgapSequence {

    public NGAP_TransportLayerAddress transportLayerAddress;
    public NGAP_GTP_TEID gTP_TEID;

    @Override
    protected String getAsnName() {
        return "GTPTunnel";
    }

    @Override
    protected String getXmlTagName() {
        return "GTPTunnel";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"transportLayerAddress", "gTP-TEID"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"transportLayerAddress", "gTP_TEID"};
    }
}
