package tr.havelsan.ueransim.ngap4.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap4.core.NgapSequenceOf;
import tr.havelsan.ueransim.ngap4.ies.bit_strings.NGAP_TransportLayerAddress;

public class NGAP_XnTLAs extends NgapSequenceOf<NGAP_TransportLayerAddress> {

    @Override
    protected String getAsnName() {
        return "XnTLAs";
    }

    @Override
    protected String getXmlTagName() {
        return "XnTLAs";
    }

    @Override
    public Class<NGAP_TransportLayerAddress> getItemType() {
        return NGAP_TransportLayerAddress.class;
    }
}
