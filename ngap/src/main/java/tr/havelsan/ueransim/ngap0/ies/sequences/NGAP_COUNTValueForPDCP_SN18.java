package tr.havelsan.ueransim.ngap0.ies.sequences;

import tr.havelsan.ueransim.ngap0.core.*;

public class NGAP_COUNTValueForPDCP_SN18 extends NGAP_Sequence {

    public NGAP_Integer pDCP_SN18;
    public NGAP_Integer hFN_PDCP_SN18;

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
