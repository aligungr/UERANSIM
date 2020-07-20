package tr.havelsan.ueransim.ngap0.ies.octet_strings;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.utils.bits.*;
import tr.havelsan.ueransim.utils.octets.*;

public class NGAP_UERadioCapabilityForPagingOfEUTRA extends NGAP_OctetString {

    public NGAP_UERadioCapabilityForPagingOfEUTRA(OctetString value) {
        super(value);
    }

    public NGAP_UERadioCapabilityForPagingOfEUTRA(BitString value) {
        super(value);
    }

    public NGAP_UERadioCapabilityForPagingOfEUTRA(Octet[] octets) {
        super(octets);
    }

    public NGAP_UERadioCapabilityForPagingOfEUTRA(int[] octetInts) {
        super(octetInts);
    }

    public NGAP_UERadioCapabilityForPagingOfEUTRA(byte[] octetBytes) {
        super(octetBytes);
    }

    public NGAP_UERadioCapabilityForPagingOfEUTRA(String hex) {
        super(hex);
    }

    @Override
    public String getAsnName() {
        return "UERadioCapabilityForPagingOfEUTRA";
    }

    @Override
    public String getXmlTagName() {
        return "UERadioCapabilityForPagingOfEUTRA";
    }
}
