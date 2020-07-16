package tr.havelsan.ueransim.ngap4.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap4.core.NgapSequenceOf;
import tr.havelsan.ueransim.ngap4.ies.sequences.NGAP_UnavailableGUAMIItem;

public class NGAP_UnavailableGUAMIList extends NgapSequenceOf<NGAP_UnavailableGUAMIItem> {

    @Override
    protected String getAsnName() {
        return "UnavailableGUAMIList";
    }

    @Override
    protected String getXmlTagName() {
        return "UnavailableGUAMIList";
    }

    @Override
    public Class<NGAP_UnavailableGUAMIItem> getItemType() {
        return NGAP_UnavailableGUAMIItem.class;
    }
}
