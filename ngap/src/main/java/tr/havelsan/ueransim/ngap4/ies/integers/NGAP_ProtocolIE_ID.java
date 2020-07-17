package tr.havelsan.ueransim.ngap4.ies.integers;

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

public class NGAP_ProtocolIE_ID extends NGAP_Integer {

    public NGAP_ProtocolIE_ID(long value) {
        super(value);
    }

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

    @Override
    public String getAsnName() {
        return "ProtocolIE-ID";
    }

    @Override
    public String getXmlTagName() {
        return "ProtocolIE-ID";
    }
}
