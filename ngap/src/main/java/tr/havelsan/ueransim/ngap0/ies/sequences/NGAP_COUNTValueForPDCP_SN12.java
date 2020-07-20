package tr.havelsan.ueransim.ngap0.ies.sequences;

import tr.havelsan.ueransim.ngap0.core.*;

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
