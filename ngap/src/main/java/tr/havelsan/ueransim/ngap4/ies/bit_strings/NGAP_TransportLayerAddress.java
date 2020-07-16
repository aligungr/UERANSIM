package tr.havelsan.ueransim.ngap4.ies.bit_strings;

import tr.havelsan.ueransim.ngap4.core.NgapBitString;

public class NGAP_TransportLayerAddress extends NgapBitString {

    @Override
    protected String getAsnName() {
        return "TransportLayerAddress";
    }

    @Override
    protected String getXmlTagName() {
        return "TransportLayerAddress";
    }
}
