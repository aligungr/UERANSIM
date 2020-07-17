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

import java.util.List;

public class NGAP_NASSecurityParametersFromNGRAN extends NgapOctetString {

    public NGAP_NASSecurityParametersFromNGRAN(OctetString value) {
        super(value);
    }

    public NGAP_NASSecurityParametersFromNGRAN(BitString value) {
        super(value);
    }

    public NGAP_NASSecurityParametersFromNGRAN(Octet[] octets) {
        super(octets);
    }

    public NGAP_NASSecurityParametersFromNGRAN(int[] octetInts) {
        super(octetInts);
    }

    public NGAP_NASSecurityParametersFromNGRAN(byte[] octetBytes) {
        super(octetBytes);
    }

    public NGAP_NASSecurityParametersFromNGRAN(String hex) {
        super(hex);
    }

    @Override
    public String getAsnName() {
        return "NASSecurityParametersFromNGRAN";
    }

    @Override
    public String getXmlTagName() {
        return "NASSecurityParametersFromNGRAN";
    }
}
