package tr.havelsan.ueransim.ngap4.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap4.core.NgapSequenceOf;
import tr.havelsan.ueransim.ngap4.ies.bit_strings.NGAP_TransportLayerAddress;

public class NGAP_XnGTP_TLAs extends NgapSequenceOf<NGAP_TransportLayerAddress> {

    @Override
    protected String getAsnName() {
        return "XnGTP-TLAs";
    }

    @Override
    protected String getXmlTagName() {
        return "XnGTP-TLAs";
    }

    @Override
    public Class<NGAP_TransportLayerAddress> getItemType() {
        return NGAP_TransportLayerAddress.class;
    }
}
