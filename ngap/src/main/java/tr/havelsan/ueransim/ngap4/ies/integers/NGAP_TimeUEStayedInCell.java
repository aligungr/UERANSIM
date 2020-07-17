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

import java.util.List;

public class NGAP_TimeUEStayedInCell extends NgapInteger {

    public NGAP_TimeUEStayedInCell(long value) {
        super(value);
    }

    public NGAP_TimeUEStayedInCell(Octet value) {
        super(value);
    }

    public NGAP_TimeUEStayedInCell(Octet2 value) {
        super(value);
    }

    public NGAP_TimeUEStayedInCell(Octet3 value) {
        super(value);
    }

    public NGAP_TimeUEStayedInCell(Octet4 value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "TimeUEStayedInCell";
    }

    @Override
    public String getXmlTagName() {
        return "TimeUEStayedInCell";
    }
}
