package tr.havelsan.ueransim.ngap4.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap4.core.NgapSequenceOf;
import tr.havelsan.ueransim.ngap4.ies.sequences.NGAP_CompletedCellsInEAI_EUTRA_Item;

public class NGAP_CompletedCellsInEAI_EUTRA extends NgapSequenceOf<NGAP_CompletedCellsInEAI_EUTRA_Item> {

    @Override
    protected String getAsnName() {
        return "CompletedCellsInEAI-EUTRA";
    }

    @Override
    protected String getXmlTagName() {
        return "CompletedCellsInEAI-EUTRA";
    }

    @Override
    public Class<NGAP_CompletedCellsInEAI_EUTRA_Item> getItemType() {
        return NGAP_CompletedCellsInEAI_EUTRA_Item.class;
    }
}
