package tr.havelsan.ueransim.ngap4.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap4.core.NgapSequenceOf;
import tr.havelsan.ueransim.ngap4.ies.octet_strings.NGAP_TAC;

public class NGAP_NotAllowedTACs extends NgapSequenceOf<NGAP_TAC> {

    @Override
    protected String getAsnName() {
        return "NotAllowedTACs";
    }

    @Override
    protected String getXmlTagName() {
        return "NotAllowedTACs";
    }

    @Override
    public Class<NGAP_TAC> getItemType() {
        return NGAP_TAC.class;
    }
}
