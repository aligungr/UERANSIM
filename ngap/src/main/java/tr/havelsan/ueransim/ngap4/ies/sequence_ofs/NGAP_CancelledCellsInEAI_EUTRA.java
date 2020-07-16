package tr.havelsan.ueransim.ngap4.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap4.core.NgapSequenceOf;
import tr.havelsan.ueransim.ngap4.ies.sequences.NGAP_CancelledCellsInEAI_EUTRA_Item;

public class NGAP_CancelledCellsInEAI_EUTRA extends NgapSequenceOf<NGAP_CancelledCellsInEAI_EUTRA_Item> {

    @Override
    protected String getAsnName() {
        return "CancelledCellsInEAI-EUTRA";
    }

    @Override
    protected String getXmlTagName() {
        return "CancelledCellsInEAI-EUTRA";
    }

    @Override
    public Class<NGAP_CancelledCellsInEAI_EUTRA_Item> getItemType() {
        return NGAP_CancelledCellsInEAI_EUTRA_Item.class;
    }
}
