package tr.havelsan.ueransim.ngap4.ies.octet_strings;

import tr.havelsan.ueransim.ngap4.core.*;
import tr.havelsan.ueransim.ngap4.pdu.*;
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

public class NGAP_UERadioCapability extends NGAP_OctetString {

    public NGAP_UERadioCapability(OctetString value) {
        super(value);
    }

    public NGAP_UERadioCapability(BitString value) {
        super(value);
    }

    public NGAP_UERadioCapability(Octet[] octets) {
        super(octets);
    }

    public NGAP_UERadioCapability(int[] octetInts) {
        super(octetInts);
    }

    public NGAP_UERadioCapability(byte[] octetBytes) {
        super(octetBytes);
    }

    public NGAP_UERadioCapability(String hex) {
        super(hex);
    }

    @Override
    public String getAsnName() {
        return "UERadioCapability";
    }

    @Override
    public String getXmlTagName() {
        return "UERadioCapability";
    }
}
