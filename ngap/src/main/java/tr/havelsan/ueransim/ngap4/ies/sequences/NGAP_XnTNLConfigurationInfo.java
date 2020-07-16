package tr.havelsan.ueransim.ngap4.ies.sequences;

import tr.havelsan.ueransim.ngap4.core.NgapSequence;
import tr.havelsan.ueransim.ngap4.ies.sequence_ofs.NGAP_XnExtTLAs;
import tr.havelsan.ueransim.ngap4.ies.sequence_ofs.NGAP_XnTLAs;

public class NGAP_XnTNLConfigurationInfo extends NgapSequence {

    public NGAP_XnTLAs xnTransportLayerAddresses;
    public NGAP_XnExtTLAs xnExtendedTransportLayerAddresses;

    @Override
    protected String getAsnName() {
        return "XnTNLConfigurationInfo";
    }

    @Override
    protected String getXmlTagName() {
        return "XnTNLConfigurationInfo";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"xnTransportLayerAddresses", "xnExtendedTransportLayerAddresses"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"xnTransportLayerAddresses", "xnExtendedTransportLayerAddresses"};
    }
}
