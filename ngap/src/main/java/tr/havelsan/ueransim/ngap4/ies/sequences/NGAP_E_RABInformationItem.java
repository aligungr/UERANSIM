package tr.havelsan.ueransim.ngap4.ies.sequences;

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

public class NGAP_E_RABInformationItem extends NgapSequence {

    public NGAP_E_RAB_ID e_RAB_ID;
    public NGAP_DLForwarding dLForwarding;

    @Override
    protected String getAsnName() {
        return "E-RABInformationItem";
    }

    @Override
    protected String getXmlTagName() {
        return "E-RABInformationItem";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"e-RAB-ID", "dLForwarding"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"e_RAB_ID", "dLForwarding"};
    }
}
