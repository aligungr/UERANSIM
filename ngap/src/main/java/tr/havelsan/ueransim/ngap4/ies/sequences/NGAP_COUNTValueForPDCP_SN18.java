package tr.havelsan.ueransim.ngap4.ies.sequences;

import tr.havelsan.ueransim.ngap4.core.NgapSequence;

public class NGAP_COUNTValueForPDCP_SN18 extends NgapSequence {

    public long pDCP_SN18;
    public long hFN_PDCP_SN18;

    @Override
    protected String getAsnName() {
        return "COUNTValueForPDCP-SN18";
    }

    @Override
    protected String getXmlTagName() {
        return "COUNTValueForPDCP-SN18";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"pDCP-SN18", "hFN-PDCP-SN18"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"pDCP_SN18", "hFN_PDCP_SN18"};
    }
}
