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

public class NGAP_S_NSSAI extends NgapSequence {

    public NGAP_SST sST;
    public NGAP_SD sD;

    @Override
    protected String getAsnName() {
        return "S-NSSAI";
    }

    @Override
    protected String getXmlTagName() {
        return "S-NSSAI";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"sST", "sD"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"sST", "sD"};
    }
}
