package tr.havelsan.ueransim.ngap4.ies.octet_strings;

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

public class NGAP_NRPPa_PDU extends NgapOctetString {

    public NGAP_NRPPa_PDU(OctetString value) {
        super(value);
    }

    public NGAP_NRPPa_PDU(BitString value) {
        super(value);
    }

    public NGAP_NRPPa_PDU(Octet... octets) {
        super(octets);
    }

    public NGAP_NRPPa_PDU(int[] octetInts) {
        super(octetInts);
    }

    public NGAP_NRPPa_PDU(byte[] octetBytes) {
        super(octetBytes);
    }

    public NGAP_NRPPa_PDU(String hex) {
        super(hex);
    }

    @Override
    protected String getAsnName() {
        return "NRPPa-PDU";
    }

    @Override
    protected String getXmlTagName() {
        return "NRPPa-PDU";
    }
}
