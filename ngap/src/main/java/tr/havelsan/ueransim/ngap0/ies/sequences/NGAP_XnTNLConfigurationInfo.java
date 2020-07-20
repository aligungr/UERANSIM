package tr.havelsan.ueransim.ngap0.ies.sequences;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.ies.sequence_ofs.*;

public class NGAP_XnTNLConfigurationInfo extends NGAP_Sequence {

    public NGAP_XnTLAs xnTransportLayerAddresses;
    public NGAP_XnExtTLAs xnExtendedTransportLayerAddresses;

    @Override
    public String getAsnName() {
        return "XnTNLConfigurationInfo";
    }

    @Override
    public String getXmlTagName() {
        return "XnTNLConfigurationInfo";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"xnTransportLayerAddresses", "xnExtendedTransportLayerAddresses"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"xnTransportLayerAddresses", "xnExtendedTransportLayerAddresses"};
    }
}
