package tr.havelsan.ueransim.ngap4.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap4.core.NgapSequenceOf;
import tr.havelsan.ueransim.ngap4.ies.octet_strings.NGAP_TAC;

public class NGAP_AllowedTACs extends NgapSequenceOf<NGAP_TAC> {

    @Override
    protected String getAsnName() {
        return "AllowedTACs";
    }

    @Override
    protected String getXmlTagName() {
        return "AllowedTACs";
    }

    @Override
    public Class<NGAP_TAC> getItemType() {
        return NGAP_TAC.class;
    }
}
