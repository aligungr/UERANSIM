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

public class NGAP_UERadioCapabilityForPagingOfNR extends NGAP_OctetString {

    public NGAP_UERadioCapabilityForPagingOfNR(OctetString value) {
        super(value);
    }

    public NGAP_UERadioCapabilityForPagingOfNR(BitString value) {
        super(value);
    }

    public NGAP_UERadioCapabilityForPagingOfNR(Octet[] octets) {
        super(octets);
    }

    public NGAP_UERadioCapabilityForPagingOfNR(int[] octetInts) {
        super(octetInts);
    }

    public NGAP_UERadioCapabilityForPagingOfNR(byte[] octetBytes) {
        super(octetBytes);
    }

    public NGAP_UERadioCapabilityForPagingOfNR(String hex) {
        super(hex);
    }

    @Override
    public String getAsnName() {
        return "UERadioCapabilityForPagingOfNR";
    }

    @Override
    public String getXmlTagName() {
        return "UERadioCapabilityForPagingOfNR";
    }
}
