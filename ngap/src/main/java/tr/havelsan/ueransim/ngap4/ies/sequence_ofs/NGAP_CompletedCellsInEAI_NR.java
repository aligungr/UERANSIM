package tr.havelsan.ueransim.ngap4.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap4.core.NgapSequenceOf;
import tr.havelsan.ueransim.ngap4.ies.sequences.NGAP_CompletedCellsInEAI_NR_Item;

public class NGAP_CompletedCellsInEAI_NR extends NgapSequenceOf<NGAP_CompletedCellsInEAI_NR_Item> {

    @Override
    protected String getAsnName() {
        return "CompletedCellsInEAI-NR";
    }

    @Override
    protected String getXmlTagName() {
        return "CompletedCellsInEAI-NR";
    }

    @Override
    public Class<NGAP_CompletedCellsInEAI_NR_Item> getItemType() {
        return NGAP_CompletedCellsInEAI_NR_Item.class;
    }
}
