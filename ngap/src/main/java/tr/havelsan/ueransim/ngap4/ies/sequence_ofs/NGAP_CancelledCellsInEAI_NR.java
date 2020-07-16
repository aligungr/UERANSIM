package tr.havelsan.ueransim.ngap4.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap4.core.NgapSequenceOf;
import tr.havelsan.ueransim.ngap4.ies.sequences.NGAP_CancelledCellsInEAI_NR_Item;

public class NGAP_CancelledCellsInEAI_NR extends NgapSequenceOf<NGAP_CancelledCellsInEAI_NR_Item> {

    @Override
    protected String getAsnName() {
        return "CancelledCellsInEAI-NR";
    }

    @Override
    protected String getXmlTagName() {
        return "CancelledCellsInEAI-NR";
    }

    @Override
    public Class<NGAP_CancelledCellsInEAI_NR_Item> getItemType() {
        return NGAP_CancelledCellsInEAI_NR_Item.class;
    }
}
