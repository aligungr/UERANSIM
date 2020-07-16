package tr.havelsan.ueransim.ngap4.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap4.core.NgapSequenceOf;
import tr.havelsan.ueransim.ngap4.ies.sequences.NGAP_E_RABInformationItem;

public class NGAP_E_RABInformationList extends NgapSequenceOf<NGAP_E_RABInformationItem> {

    @Override
    protected String getAsnName() {
        return "E-RABInformationList";
    }

    @Override
    protected String getXmlTagName() {
        return "E-RABInformationList";
    }

    @Override
    public Class<NGAP_E_RABInformationItem> getItemType() {
        return NGAP_E_RABInformationItem.class;
    }
}
