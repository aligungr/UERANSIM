package tr.havelsan.ueransim.ngap4.ies.sequences;

import tr.havelsan.ueransim.ngap4.core.NgapBitString;
import tr.havelsan.ueransim.ngap4.core.NgapSequence;

public class NGAP_DRBStatusUL18 extends NgapSequence {

    public NGAP_COUNTValueForPDCP_SN18 uL_COUNTValue;
    public NgapBitString receiveStatusOfUL_PDCP_SDUs;

    @Override
    protected String getAsnName() {
        return "DRBStatusUL18";
    }

    @Override
    protected String getXmlTagName() {
        return "DRBStatusUL18";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"uL-COUNTValue", "receiveStatusOfUL-PDCP-SDUs"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"uL_COUNTValue", "receiveStatusOfUL_PDCP_SDUs"};
    }
}
