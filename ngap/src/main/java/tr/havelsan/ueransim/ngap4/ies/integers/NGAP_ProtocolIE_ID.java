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

public class NGAP_ProtocolIE_ID extends NgapInteger {

    public NGAP_ProtocolIE_ID(Octet value) {
        super(value);
    }

    public NGAP_ProtocolIE_ID(Octet2 value) {
        super(value);
    }

    public NGAP_ProtocolIE_ID(Octet3 value) {
        super(value);
    }

    public NGAP_ProtocolIE_ID(Octet4 value) {
        super(value);
    }

    public NGAP_ProtocolIE_ID(long value) {
        super(value);
    }

    @Override
    protected String getAsnName() {
        return "ProtocolIE-ID";
    }

    @Override
    protected String getXmlTagName() {
        return "ProtocolIE-ID";
    }
}
