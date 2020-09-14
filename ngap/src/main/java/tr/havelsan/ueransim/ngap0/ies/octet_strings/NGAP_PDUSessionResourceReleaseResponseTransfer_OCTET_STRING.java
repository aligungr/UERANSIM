package tr.havelsan.ueransim.ngap0.ies.octet_strings;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.pdu.*;
import tr.havelsan.ueransim.utils.bits.*;
import tr.havelsan.ueransim.utils.octets.*;
import tr.havelsan.ueransim.ngap0.ies.bit_strings.*;
import tr.havelsan.ueransim.ngap0.ies.octet_strings.*;
import tr.havelsan.ueransim.ngap0.ies.printable_strings.*;
import tr.havelsan.ueransim.ngap0.ies.sequences.*;
import tr.havelsan.ueransim.ngap0.ies.sequence_ofs.*;
import tr.havelsan.ueransim.ngap0.ies.choices.*;
import tr.havelsan.ueransim.ngap0.ies.integers.*;
import tr.havelsan.ueransim.ngap0.ies.enumerations.*;

import java.util.List;

public class NGAP_PDUSessionResourceReleaseResponseTransfer_OCTET_STRING extends NGAP_OctetString {

    public NGAP_PDUSessionResourceReleaseResponseTransfer_OCTET_STRING(OctetString value) {
        super(value);
    }

    public NGAP_PDUSessionResourceReleaseResponseTransfer_OCTET_STRING(BitString value) {
        super(value);
    }

    public NGAP_PDUSessionResourceReleaseResponseTransfer_OCTET_STRING(Octet[] octets) {
        super(octets);
    }

    public NGAP_PDUSessionResourceReleaseResponseTransfer_OCTET_STRING(int[] octetInts) {
        super(octetInts);
    }

    public NGAP_PDUSessionResourceReleaseResponseTransfer_OCTET_STRING(byte[] octetBytes) {
        super(octetBytes);
    }

    public NGAP_PDUSessionResourceReleaseResponseTransfer_OCTET_STRING(String hex) {
        super(hex);
    }

    @Override
    public String getAsnName() {
        return "PDUSessionResourceReleaseResponseTransfer-OCTET-STRING";
    }

    @Override
    public String getXmlTagName() {
        return "PDUSessionResourceReleaseResponseTransfer-OCTET-STRING";
    }
}
