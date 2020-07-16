package tr.havelsan.ueransim.ngap4.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap4.core.NgapSequenceOf;
import tr.havelsan.ueransim.ngap4.ies.sequences.NGAP_CompletedCellsInTAI_EUTRA_Item;

public class NGAP_CompletedCellsInTAI_EUTRA extends NgapSequenceOf<NGAP_CompletedCellsInTAI_EUTRA_Item> {

    @Override
    protected String getAsnName() {
        return "CompletedCellsInTAI-EUTRA";
    }

    @Override
    protected String getXmlTagName() {
        return "CompletedCellsInTAI-EUTRA";
    }

    @Override
    public Class<NGAP_CompletedCellsInTAI_EUTRA_Item> getItemType() {
        return NGAP_CompletedCellsInTAI_EUTRA_Item.class;
    }
}
