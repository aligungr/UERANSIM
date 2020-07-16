package tr.havelsan.ueransim.ngap4.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap4.core.NgapSequenceOf;
import tr.havelsan.ueransim.ngap4.ies.sequences.NGAP_CancelledCellsInTAI_EUTRA_Item;

public class NGAP_CancelledCellsInTAI_EUTRA extends NgapSequenceOf<NGAP_CancelledCellsInTAI_EUTRA_Item> {

    @Override
    protected String getAsnName() {
        return "CancelledCellsInTAI-EUTRA";
    }

    @Override
    protected String getXmlTagName() {
        return "CancelledCellsInTAI-EUTRA";
    }

    @Override
    public Class<NGAP_CancelledCellsInTAI_EUTRA_Item> getItemType() {
        return NGAP_CancelledCellsInTAI_EUTRA_Item.class;
    }
}
