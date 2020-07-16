package tr.havelsan.ueransim.ngap4.ies.bit_strings;

import tr.havelsan.ueransim.ngap4.core.NgapBitString;
import tr.havelsan.ueransim.utils.bits.BitString;
import tr.havelsan.ueransim.utils.octets.Octet;
import tr.havelsan.ueransim.utils.octets.OctetString;

public class NGAP_TransportLayerAddress extends NgapBitString {

    public NGAP_TransportLayerAddress(BitString value) {
        super(value);
    }

    public NGAP_TransportLayerAddress(OctetString octetString, int bitLength) {
        super(octetString, bitLength);
    }

    public NGAP_TransportLayerAddress(OctetString octetString) {
        super(octetString);
    }

    public NGAP_TransportLayerAddress(Octet[] octets, int bitLength) {
        super(octets, bitLength);
    }

    public NGAP_TransportLayerAddress(Octet[] octets) {
        super(octets);
    }

    public NGAP_TransportLayerAddress(byte[] octets, int bitLength) {
        super(octets, bitLength);
    }

    public NGAP_TransportLayerAddress(byte[] octets) {
        super(octets);
    }

    public NGAP_TransportLayerAddress(String hex, int bitLength) {
        super(hex, bitLength);
    }

    public NGAP_TransportLayerAddress(String hex) {
        super(hex);
    }

    @Override
    protected String getAsnName() {
        return "TransportLayerAddress";
    }

    @Override
    protected String getXmlTagName() {
        return "TransportLayerAddress";
    }
}
