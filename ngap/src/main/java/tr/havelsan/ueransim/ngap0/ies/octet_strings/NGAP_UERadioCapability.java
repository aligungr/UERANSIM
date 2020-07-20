package tr.havelsan.ueransim.ngap0.ies.octet_strings;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.utils.bits.*;
import tr.havelsan.ueransim.utils.octets.*;

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
