package tr.havelsan.ueransim.ngap4.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap4.core.NgapSequenceOf;
import tr.havelsan.ueransim.ngap4.ies.sequences.NGAP_CompletedCellsInTAI_NR_Item;

public class NGAP_CompletedCellsInTAI_NR extends NgapSequenceOf<NGAP_CompletedCellsInTAI_NR_Item> {

    @Override
    protected String getAsnName() {
        return "CompletedCellsInTAI-NR";
    }

    @Override
    protected String getXmlTagName() {
        return "CompletedCellsInTAI-NR";
    }

    @Override
    public Class<NGAP_CompletedCellsInTAI_NR_Item> getItemType() {
        return NGAP_CompletedCellsInTAI_NR_Item.class;
    }
}
