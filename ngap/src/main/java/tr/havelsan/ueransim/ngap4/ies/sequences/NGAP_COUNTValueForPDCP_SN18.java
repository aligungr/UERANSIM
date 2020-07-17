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

public class NGAP_COUNTValueForPDCP_SN18 extends NgapSequence {

    public long pDCP_SN18;
    public long hFN_PDCP_SN18;

    @Override
    public String getAsnName() {
        return "COUNTValueForPDCP-SN18";
    }

    @Override
    public String getXmlTagName() {
        return "COUNTValueForPDCP-SN18";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"pDCP-SN18", "hFN-PDCP-SN18"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"pDCP_SN18", "hFN_PDCP_SN18"};
    }
}
