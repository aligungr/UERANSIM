package tr.havelsan.ueransim.ngap4.ies.sequences;

import tr.havelsan.ueransim.ngap4.core.NgapSequence;

public class NGAP_COUNTValueForPDCP_SN12 extends NgapSequence {

    public long pDCP_SN12;
    public long hFN_PDCP_SN12;

    @Override
    protected String getAsnName() {
        return "COUNTValueForPDCP-SN12";
    }

    @Override
    protected String getXmlTagName() {
        return "COUNTValueForPDCP-SN12";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"pDCP-SN12", "hFN-PDCP-SN12"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"pDCP_SN12", "hFN_PDCP_SN12"};
    }
}
