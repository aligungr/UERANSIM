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

import java.util.List;

public class NGAP_EPS_TAI extends NgapSequence {

    public NGAP_PLMNIdentity pLMNIdentity;
    public NGAP_EPS_TAC ePS_TAC;

    @Override
    protected String getAsnName() {
        return "EPS-TAI";
    }

    @Override
    protected String getXmlTagName() {
        return "EPS-TAI";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"pLMNIdentity", "ePS-TAC"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"pLMNIdentity", "ePS_TAC"};
    }
}
