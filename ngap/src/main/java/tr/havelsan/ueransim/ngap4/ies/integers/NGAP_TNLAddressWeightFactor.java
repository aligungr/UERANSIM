package tr.havelsan.ueransim.ngap4.ies.integers;

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

public class NGAP_TNLAddressWeightFactor extends NgapInteger {

    public NGAP_TNLAddressWeightFactor(Octet value) {
        super(value);
    }

    public NGAP_TNLAddressWeightFactor(Octet2 value) {
        super(value);
    }

    public NGAP_TNLAddressWeightFactor(Octet3 value) {
        super(value);
    }

    public NGAP_TNLAddressWeightFactor(Octet4 value) {
        super(value);
    }

    public NGAP_TNLAddressWeightFactor(long value) {
        super(value);
    }

    @Override
    protected String getAsnName() {
        return "TNLAddressWeightFactor";
    }

    @Override
    protected String getXmlTagName() {
        return "TNLAddressWeightFactor";
    }
}
