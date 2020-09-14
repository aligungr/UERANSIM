package tr.havelsan.ueransim.ngap0.ies.sequences;

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

public class NGAP_COUNTValueForPDCP_SN12 extends NGAP_Sequence {

    public NGAP_Integer pDCP_SN12;
    public NGAP_Integer hFN_PDCP_SN12;

    @Override
    public String getAsnName() {
        return "COUNTValueForPDCP-SN12";
    }

    @Override
    public String getXmlTagName() {
        return "COUNTValueForPDCP-SN12";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"pDCP-SN12", "hFN-PDCP-SN12"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"pDCP_SN12", "hFN_PDCP_SN12"};
    }
}
