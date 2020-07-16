package tr.havelsan.ueransim.ngap4.ies.bit_strings;

import tr.havelsan.ueransim.ngap4.core.*;
import tr.havelsan.ueransim.utils.bits.*;
import tr.havelsan.ueransim.utils.octets.*;
import tr.havelsan.ueransim.ngap4.ies.bit_strings.*;
import tr.havelsan.ueransim.ngap4.ies.octet_strings.*;
import tr.havelsan.ueransim.ngap4.ies.printable_strings.*;
import tr.havelsan.ueransim.ngap4.ies.sequences.*;
import tr.havelsan.ueransim.ngap4.ies.sequence_ofs.*;
import tr.havelsan.ueransim.ngap4.ies.choices.*;
import tr.havelsan.ueransim.ngap4.ies.integers.*;
import tr.havelsan.ueransim.ngap4.ies.enumerations.*;

import java.util.List;

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
