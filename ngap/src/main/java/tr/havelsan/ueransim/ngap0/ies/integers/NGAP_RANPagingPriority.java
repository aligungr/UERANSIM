package tr.havelsan.ueransim.ngap0.ies.integers;

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

public class NGAP_RANPagingPriority extends NGAP_Integer {

    public NGAP_RANPagingPriority(long value) {
        super(value);
    }

    public NGAP_RANPagingPriority(Octet value) {
        super(value);
    }

    public NGAP_RANPagingPriority(Octet2 value) {
        super(value);
    }

    public NGAP_RANPagingPriority(Octet3 value) {
        super(value);
    }

    public NGAP_RANPagingPriority(Octet4 value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "RANPagingPriority";
    }

    @Override
    public String getXmlTagName() {
        return "RANPagingPriority";
    }
}
