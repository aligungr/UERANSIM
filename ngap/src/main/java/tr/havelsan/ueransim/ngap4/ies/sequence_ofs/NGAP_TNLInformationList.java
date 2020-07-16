package tr.havelsan.ueransim.ngap4.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap4.core.NgapSequenceOf;
import tr.havelsan.ueransim.ngap4.ies.sequences.NGAP_TNLInformationItem;

public class NGAP_TNLInformationList extends NgapSequenceOf<NGAP_TNLInformationItem> {

    @Override
    protected String getAsnName() {
        return "TNLInformationList";
    }

    @Override
    protected String getXmlTagName() {
        return "TNLInformationList";
    }

    @Override
    public Class<NGAP_TNLInformationItem> getItemType() {
        return NGAP_TNLInformationItem.class;
    }
}
