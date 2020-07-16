package tr.havelsan.ueransim.ngap4.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap4.core.NgapSequenceOf;
import tr.havelsan.ueransim.ngap4.ies.sequences.NGAP_CancelledCellsInTAI_NR_Item;

public class NGAP_CancelledCellsInTAI_NR extends NgapSequenceOf<NGAP_CancelledCellsInTAI_NR_Item> {

    @Override
    protected String getAsnName() {
        return "CancelledCellsInTAI-NR";
    }

    @Override
    protected String getXmlTagName() {
        return "CancelledCellsInTAI-NR";
    }

    @Override
    public Class<NGAP_CancelledCellsInTAI_NR_Item> getItemType() {
        return NGAP_CancelledCellsInTAI_NR_Item.class;
    }
}
