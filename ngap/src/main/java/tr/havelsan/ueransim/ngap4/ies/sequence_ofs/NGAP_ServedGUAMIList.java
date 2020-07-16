package tr.havelsan.ueransim.ngap4.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap4.core.NgapSequenceOf;
import tr.havelsan.ueransim.ngap4.ies.sequences.NGAP_ServedGUAMIItem;

public class NGAP_ServedGUAMIList extends NgapSequenceOf<NGAP_ServedGUAMIItem> {

    @Override
    protected String getAsnName() {
        return "ServedGUAMIList";
    }

    @Override
    protected String getXmlTagName() {
        return "ServedGUAMIList";
    }

    @Override
    public Class<NGAP_ServedGUAMIItem> getItemType() {
        return NGAP_ServedGUAMIItem.class;
    }
}
